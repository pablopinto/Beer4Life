package com.beer.springboot.app.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.beer.springboot.app.models.dao.IUsuarioDao;
import com.beer.springboot.app.models.entity.Role;
import com.beer.springboot.app.models.entity.Usuario;
import com.beer.springboot.app.models.service.IUploadFileService;
import com.beer.springboot.app.models.service.JpaUserDetailService;

@Controller
public class RegistrationController {
	
	@Autowired
	private JpaUserDetailService jpaUserDetail;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IUploadFileService UploadFileService;
	
	@RequestMapping(value = "/registration")
	public String crear(Map<String, Object> model) {

		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("titulo", "Formulario de Registro");
		return "registration";
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.POST)
	public String login(@Valid Usuario usuario,
			BindingResult result,
			Model model, 
			@RequestParam(value="password", required=true) String pass,
			@RequestParam("file") MultipartFile foto,  
			RedirectAttributes flash,
			SessionStatus status) throws IOException {
		
		if (result.hasErrors()) {
			result.getFieldError();
			System.out.println(result.getFieldError());
			model.addAttribute("titulo", "Formulario de Registro");
			return "registration";
		}
				
		if (!foto.isEmpty()) {

			if (usuario.getId() != null && usuario.getId() > 0 && usuario.getFoto() != null
					&& usuario.getFoto().length() > 0) {

				UploadFileService.delete(usuario.getFoto());
			}

			String uniqueFilename = null;
			try {
				uniqueFilename = UploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


//			Path  directorioRecursos = Paths.get("src//main//resources//static//upload"); Ubicacion de guardado de imagenes en ruta local del proyecto
//			String rootPath = directorioRecursos.toFile().getAbsolutePath();

//			String rootPath = "C://Temp//uploads"; // Ruta externa al proyecto

			flash.addFlashAttribute("info", "Has subido correctamente '" + foto.getOriginalFilename() + "'");
//			flash.addFlashAttribute("info" , "Has subido correctamente '" + rootAbsolutePath + "'"); // Para comprobar que efectivamente el archivo se esta subiendo con el UUID
			usuario.setFoto(uniqueFilename);

		}
		
		String bcryptPassword = passwordEncoder.encode(pass);
		
		String mensajeFlash = (usuario.getId() != null) ? "Cliente editado con exito" : "Cliente creado con exito";
		usuario.setEnabled(true);
		usuario.setPassword(bcryptPassword);
		List<String> role = new ArrayList<String>();
		role.add("ROLE_USER");
		
		usuario.setRoles(roles);
		usuarioDao.save(usuario);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);

		return "redirect:main";
	}
}
