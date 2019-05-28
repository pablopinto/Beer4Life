package com.beer.springboot.app.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.beer.springboot.app.models.dao.IUsuarioDao;
import com.beer.springboot.app.models.entity.Cliente;
import com.beer.springboot.app.models.entity.Producto;
import com.beer.springboot.app.models.entity.Usuario;
import com.beer.springboot.app.models.service.IClienteService;
import com.beer.springboot.app.models.service.IProductoService;
import com.beer.springboot.app.models.service.JpaUserDetailService;
import com.beer.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("producto")
public class ProductoController {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private JpaUserDetailService userDetailsService; 
	
	@Autowired 
	IUsuarioDao usuariodao;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0")  int page,
			Model model,
			HttpServletRequest request) {

		Object data = SecurityContextHolder.getContext().getAuthentication().getName();

		
		logger.info(data);


		Pageable pageRequest = PageRequest.of(page, 6);

		Page<Producto> productos = productoService.findAll(pageRequest);

		PageRender<Producto> pageRender = new PageRender<Producto>("/main", productos);
		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("productos", productos);
		model.addAttribute("page", pageRender);
		model.addAttribute("data", data);

		return "main";

	}

	@RequestMapping(value = "/gestion-productos", method = RequestMethod.GET)
	public String listarAdmin(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			HttpServletRequest request) {

		Pageable pageRequest = PageRequest.of(page, 6);

		Page<Producto> productos = productoService.findAll(pageRequest);

		PageRender<Producto> pageRender = new PageRender<Producto>("/gestion-productos", productos);
		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("productos", productos);
		model.addAttribute("page", pageRender);

		return "gestion-productos";

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(value = "/ver-carrito/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Cliente cliente = clienteService.fetchByIdWithFacturas(id); 
//		Cliente cliente = clienteService.findOne(id);
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/main";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Detalles del cliente " + cliente.getNombre());

		return "ver-carrito";

	}
	
	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public String shop(@RequestParam(name = "page", defaultValue = "0")  int page,
			Model model,
			HttpServletRequest request) {

		Object data = SecurityContextHolder.getContext().getAuthentication().getName();

		
		logger.info(data);


		Pageable pageRequest = PageRequest.of(page, 6);

		Page<Producto> productos = productoService.findAll(pageRequest);

		PageRender<Producto> pageRender = new PageRender<Producto>("/main", productos);
		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("productos", productos);
		model.addAttribute("page", pageRender);
		model.addAttribute("data", data);

		return "shop";

	}

}
