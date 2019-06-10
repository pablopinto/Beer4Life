package com.beer.springboot.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.beer.springboot.app.models.entity.Factura;
import com.beer.springboot.app.models.entity.ItemFactura;
import com.beer.springboot.app.models.entity.Populate;
import com.beer.springboot.app.models.entity.Usuario;
import com.beer.springboot.app.models.service.IClienteService;
import com.beer.springboot.app.models.service.IUsuarioService;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IUsuarioService usuarioService;

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

		Factura factura = usuarioService.fetchFacturaByIdWithUsuarioWithItemFacturaWithProducto(id); // clienteService.findFacturaById(id);

		if (factura == null) {
			flash.addFlashAttribute("error", "La factura no existe en la base de datos");
			return "redirect:/listar";
		}
		
		model.addAttribute("factura", factura);
		model.addAttribute("titulo", "Factura :".concat(factura.getDescripcion()));
		
		return "factura/ver";

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/form/{clienteId}")
	public String crear(@PathVariable(value = "clienteId") Long clienteId, Map<String, Object> model,
			RedirectAttributes flash) {

		Usuario usuario = usuarioService.findOne(clienteId);

		if (usuario == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}

		Factura factura = new Factura();
		factura.setUsuario(usuario);

		model.put("factura", factura);
		model.put("titulo", "Crear Factura");

		return "factura/form";
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(value = "/cargar-productos/{term}", produces = { "application/json" })
	public @ResponseBody List<Populate> cargarProductos(@PathVariable String term) {
		return usuarioService.findByNombre(term);
	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/form")
	public String guardar(@Valid Factura factura, BindingResult result, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear Factura");
			return "factura/form";
		}

		if (itemId == null || itemId.length == 0) {
			model.addAttribute("titulo", "Crear Factura");
			model.addAttribute("error", "Error: La factura NO puede no tener líneas!");
			return "factura/form";
		}

		for (int i = 0; i < itemId.length; i++) {
			Populate populate = usuarioService.findProductoById(itemId[i]);

			ItemFactura linea = new ItemFactura();
			linea.setCantidad(cantidad[i]);
			linea.setProducto(populate);
			factura.addItemFactura(linea);

			log.info("ID: " + itemId[i].toString() + ", cantidad: " + cantidad[i].toString());
		}

		usuarioService.saveFactura(factura);
		status.setComplete();

		flash.addFlashAttribute("success", "Factura creada con éxito!");

		return "redirect:/ver/" + factura.getUsuario().getId();
	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id , RedirectAttributes flash) {
		
		Factura factura = usuarioService.findFacturaById(id);
		
		if(factura  != null) {
			clienteService.deleteFactura(id);
			flash.addFlashAttribute("success", "Factura eliminada satisfactoriamente");
			return "redirect:/ver/" + factura.getUsuario().getId();
		}
		
		flash.addAttribute("error", "La factura no existe en la base de datos , no se pudo eliminar");
		return "redirect:/listar";
	}

}
