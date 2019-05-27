package com.beer.springboot.app.controllers;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.beer.springboot.app.models.entity.Producto;
import com.beer.springboot.app.models.service.IProductoService;
import com.beer.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("producto")
public class ProductoController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IProductoService productoService;
	
	@RequestMapping(value ="/main", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			HttpServletRequest request) {

		Pageable pageRequest = PageRequest.of(page, 6);

		Page<Producto> productos = productoService.findAll(pageRequest);

		PageRender<Producto> pageRender = new PageRender<Producto>("/main", productos);
		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("productos", productos);
		model.addAttribute("page", pageRender);
		
		return "main";

	}
	
	
	@RequestMapping(value ="/gestion-productos", method = RequestMethod.GET)
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
//	<table class="table table-striped table-dark">
//	<thead>
//		<tr>
//			<th sec:authorize="hasRole('ROLE_USER')">id</th>
//			<th>nombre</th>
//			<th>apellido</th>
//			<th>email</th>
//			<th>fecha</th>
//			<th sec:authorize="hasRole('ROLE_ADMIN')">crear factura</th>
//			<th sec:authorize="hasRole('ROLE_ADMIN')">editar</th>
//			<th sec:authorize="hasRole('ROLE_ADMIN')">eliminar</th>
//		</tr>
//	</thead>
//
//	<tbody>
//		<tr th:each="productos: ${productos}">
//			<td sec:authorize="hasRole('ROLE_USER')"><a
//				class="btn btn-primary btn-xs"
//				th:href="@{/ver/} + ${productos.id}" th:text="${productos.id}"></a></td>
//			<td th:text="${productos.nombre}"></td>
//			
//		</tr>
//	</tbody>
//<div th:each="productos: ${productos}" class="row">
//	<p th:text="${productos.nombre}"></p>
//</div>
//</table>

}
