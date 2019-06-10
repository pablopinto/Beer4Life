package com.beer.springboot.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.beer.springboot.app.models.dao.IUsuarioDao;
import com.beer.springboot.app.models.entity.Role;
import com.beer.springboot.app.models.entity.Usuario;
import com.beer.springboot.app.models.service.IRegisterService;
import com.beer.springboot.app.models.service.IUploadFileService;
import com.beer.springboot.app.models.service.IUsuarioService;
import com.beer.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IUploadFileService UploadFileService;

	@Autowired
	private IRegisterService registerService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

//	private final Logger log = LoggerFactory.getLogger(getClass()); // Para hacer debug de los sitios de directorio
//	private final static String UPLOADS_FOLDER = "uploads";

	@Secured("ROLE_USER")
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = UploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Object data = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Usuario usuario = usuarioService.findByUsername((String) data);
		if (id != usuario.getId()) {
			flash.addFlashAttribute("error", "No puede usted acceder a un perfil que no sea el suyo");
			return "redirect:/ver/{id}";
		}else if (id == usuario.getId()) {
		
		usuario = usuarioDao.fetchByIdWithFacturas(id); // clienteService.findOne(id);
		if (usuario == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}

		model.put("cliente", usuario);
		model.put("titulo", "Detalles del cliente " + usuario.getName());

		}
		return "ver";
	}

	@RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication, HttpServletRequest request) {

		if (authentication != null) {
			logger.info("Hola usuario , tu username es:".concat(authentication.getName()));
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			logger.info(
					"Utilizando forma estatica SecurityContextHolder.getContext().getAuthentication() -> Hola usuario , tu username es:"
							.concat(auth.getName()));
		}

		if (hasRole("ROLE_ADMIN")) {
			logger.info("Hola ".concat(auth.getName()).concat(" tienes acceso"));
		} else {
			logger.info("Hola ".concat(auth.getName()).concat(" NO tienes acceso"));
		}

		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request,
				"");

		if (securityContext.isUserInRole("ROLE_ADMIN")) {
			logger.info("Usando SecurityContextHolderAwareRequestWrapper -> Hola: ".concat(auth.getName())
					.concat(" tienes acceso"));
		} else {
			logger.info("Usando SecurityContextHolderAwareRequestWrapper -> Hola: ".concat(auth.getName())
					.concat(" NO tienes acceso"));
		}

		if (request.isUserInRole("ROLE_ADMIN")) {
			logger.info("Usando HttpServletRequest -> Hola: ".concat(auth.getName()).concat(" tienes acceso"));
		} else {
			logger.info("Usando HttpServletRequest -> Hola: ".concat(auth.getName()).concat(" NO tienes acceso"));
		}

		Pageable pageRequest = PageRequest.of(page, 4);

//		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		Page<Usuario> usuario = usuarioDao.findAll(pageRequest);

		PageRender<Usuario> pageRender = new PageRender<Usuario>("/listar", usuario);
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", usuario);
		model.addAttribute("page", pageRender);
		return "listar";

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Usuario usuario = new Usuario();
		model.put("cliente", usuario);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Usuario usuario = null;

		if (id > 0) {
			usuario = usuarioService.findOne(id);
			if (usuario == null) {
				flash.addFlashAttribute("error", "El id del cliente no existe en la base de datos");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El id del cliente no puede ser cero");
			return "redirect:/listar";
		}
		model.put("cliente", usuario);
		model.put("titulo", "Editar Cliente");
		return "form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Usuario usuario, @Valid Role roles, BindingResult result, Model model,
			@RequestParam(value = "password", required = true) String pass,
			@RequestParam(value = "enabled", required = true) Boolean enabled,
			@RequestParam(value = "rol", required = true) String rol, @RequestParam("file") MultipartFile foto,
			RedirectAttributes flash, SessionStatus status) throws IOException {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}

		if (!foto.isEmpty()) {

			if (usuario.getId() != null && usuario.getId() > 0 && usuario.getFoto() != null
					&& usuario.getFoto().length() > 0) {

				UploadFileService.delete(usuario.getFoto());
			}

			String uniqueFilename = UploadFileService.copy(foto);

			flash.addFlashAttribute("info", "Has subido correctamente '" + foto.getOriginalFilename() + "'");

			usuario.setFoto(uniqueFilename);

		}
		String bcryptPassword = passwordEncoder.encode(pass);

		String mensajeFlash = (usuario.getId() != null) ? "Cliente editado con exito" : "Cliente creado con exito";

		usuario.setEnabled(enabled);
		usuario.setPassword(bcryptPassword);
		usuarioDao.save(usuario);

//		Role role = new Role();
		roles.setUser_id(usuario.getId());
		roles.setAuthority(rol);
		registerService.save(roles);

		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			Usuario usuario = usuarioService.findOne(id);

			usuarioService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con exito!");
			
			if (usuario.getFoto() != null) {
				if (UploadFileService.delete(usuario.getFoto())) {
					flash.addFlashAttribute("info", "Foto" + usuario.getFoto() + "eliminada con exito!");
				}
			}
		}

		return "redirect:/listar";
	}

	private boolean hasRole(String role) {

		SecurityContext context = SecurityContextHolder.getContext();

		if (context == null) {
			return false;
		}
		Authentication auth = context.getAuthentication();

		if (auth == null) {
			return false;
		}

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		return authorities.contains(new SimpleGrantedAuthority(role));

//		for (GrantedAuthority authority : authorities) {
//			if (role.equals(authority.getAuthority())) {
//				logger.info("Usuario: ".concat(auth.getName()).concat(" Rol: ".concat(authority.getAuthority())));
//				return true;
//			}
//		}
//
//		return false;

	}

}
