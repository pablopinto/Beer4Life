package com.beer.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beer.springboot.app.models.entity.Factura;
import com.beer.springboot.app.models.entity.Populate;
import com.beer.springboot.app.models.entity.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();

	public Page<Usuario> findAll(Pageable pageable);

	public void save(Usuario usuario);

	public Usuario findOne(Long id);
	
	public Usuario findByUsername(String term);

	public Usuario fetchByIdWithFacturas(Long id);

	public void delete(Long id);

	public List<Populate> findByNombre(String term);

	public void saveFactura(Factura factura);

	public Populate findProductoById(Long id);

	public Factura findFacturaById(Long id);

	public void deleteFactura(Long id);

	public Factura fetchFacturaByIdWithUsuarioWithItemFacturaWithProducto(Long id);

}
