package com.beer.springboot.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.beer.springboot.app.models.entity.Populate;
import com.beer.springboot.app.models.entity.Usuario;
import com.beer.springboot.app.models.service.IPopulateService;
import com.beer.springboot.app.models.service.IUsuarioService;
import com.beer.springboot.app.util.paginator.PageRender;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes("producto")
public class ProductoController {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private IPopulateService populateService;
	
	@Autowired
	private IUsuarioService userService;


	@RequestMapping(value = { "/main", "/" }, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			HttpServletRequest request)
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

		Object data = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Usuario usuario = userService.findByUsername((String) data);
		
		if(usuario == null) {
			usuario = new Usuario();
			usuario.setId((long) 0);
		}

		Pageable pageRequest = PageRequest.of(page, 6);

		Page<Populate> populate = populateService.findAll(pageRequest);

		PageRender<Populate> pageRender = new PageRender<Populate>("/main", populate);

		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("populate", populate);
		model.addAttribute("user", usuario);
		model.addAttribute("page", pageRender);
		model.addAttribute("data", data);

		return "main";

	}

	@RequestMapping(value = "/gestion-productos", method = RequestMethod.GET)
	public String listarAdmin(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			HttpServletRequest request) {

		Pageable pageRequest = PageRequest.of(page, 6);

		Page<Populate> populate = populateService.findAll(pageRequest);

		PageRender<Populate> pageRender = new PageRender<Populate>("/gestion-productos", populate);
		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("productos", populate);
		model.addAttribute("page", pageRender);

		return "gestion-productos";

	}

	@RequestMapping(value = "/form_producto/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Populate populate = null;

		if (id > 0) {
			populate = populateService.findOne(id);
			if (populate == null) {
				flash.addFlashAttribute("error", "El id del producto no existe en la base de datos");
				return "redirect:/gestion-productos";
			}
		} else {
			flash.addFlashAttribute("error", "El id del producto no puede ser cero");
			return "redirect:/gestion-productos";
		}
		model.put("producto", populate);
		model.put("titulo", "Editar Producto");
		return "form-producto";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form_producto", method = RequestMethod.POST)
	public String guardar_producto(@Valid Populate populate, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) throws IOException {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}

		String mensajeFlash = (populate.getId() != null) ? "Cliente editado con exito" : "Cliente creado con exito";

		populateService.save(populate);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:gestion-productos";

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar_producto/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			Populate populate = populateService.findOne(id);
			
			if (populate != null) {
				populateService.delete(id);
				flash.addFlashAttribute("success", "Producto eliminado con exito!");
			} else if(populate == null) {
				flash.addFlashAttribute("error", "El producto no existe!");
			}
		}

		return "redirect:/gestion-productos";
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(value = "/ver-producto/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Populate populate = populateService.findOne(id);

		if (populate == null) {
			flash.addFlashAttribute("error", "El producto no existe en la base de datos");
			return "redirect:/ver-producto";
		}

		model.put("populate", populate);
		model.put("titulo", "Detalles del producto " + populate.getName());

		return "ver-producto";

	}

	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public String shop(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			HttpServletRequest request)
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

		Object data = SecurityContextHolder.getContext().getAuthentication().getName();

		Pageable pageRequest = PageRequest.of(page, 9);

		Page<Populate> populate = populateService.findAll(pageRequest);

		PageRender<Populate> pageRender = new PageRender<Populate>("/shop", populate);

		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("populate", populate);
		model.addAttribute("page", pageRender);
		model.addAttribute("data", data);

		return "shop";

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/populate")
	public String populate(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			HttpServletRequest request)
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

		for (int i = 1; i < 310; i++) {

			final String uri = "http://localhost:8090/api/v1/beer/" + i;
			ObjectMapper mapper = new ObjectMapper();
			Populate obj = mapper.readValue(new URL(uri), Populate.class);
			model.addAttribute(obj);
			populateService.save(obj);

		}
		return "redirect:main";

	}

	@GetMapping(value = "/contact")
	public String contact(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			HttpServletRequest request) {
		return "contact";

	}

}
