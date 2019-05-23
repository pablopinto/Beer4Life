package com.dawproject.springboot.beer.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawproject.springboot.beer.models.entity.Producto;
import com.dawproject.springboot.beer.models.service.IProductoService;
import com.dawproject.springboot.beer.util.paginator.PageRender;

@Controller
public class ProductoController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IProductoService productoService;
	
	@RequestMapping(value ="/main", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			HttpServletRequest request) {


		Pageable pageRequest = PageRequest.of(page, 4);

		Page<Producto> productos = productoService.findAll(pageRequest);

		PageRender<Producto> pageRender = new PageRender<Producto>("/main", productos);
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", productos);
		model.addAttribute("page", pageRender);
		return "listar";

	}

}
