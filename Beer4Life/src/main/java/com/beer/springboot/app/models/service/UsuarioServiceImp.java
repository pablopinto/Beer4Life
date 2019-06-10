package com.beer.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beer.springboot.app.models.dao.IFacturaDao;
import com.beer.springboot.app.models.dao.IPopulateDao;
import com.beer.springboot.app.models.dao.IUsuarioDao;
import com.beer.springboot.app.models.entity.Factura;
import com.beer.springboot.app.models.entity.Populate;
import com.beer.springboot.app.models.entity.Usuario;


@Service
public class UsuarioServiceImp implements IUsuarioService {
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IFacturaDao facturaDao;
	
	@Autowired
	private IPopulateDao populateDao;

	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioDao.findAll(pageable);
	}

	@Override
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	public Usuario findOne(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	public Usuario fetchByIdWithFacturas(Long id) {
		return usuarioDao.fetchByIdWithFacturas(id);
	}

	@Override
	public void delete(Long id) {
		usuarioDao.deleteById(id);;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Populate> findByNombre(String term) {
		return populateDao.findByNameLikeIgnoreCase("%"+term+"%");
	}

	@Override
	public void saveFactura(Factura factura) {
		facturaDao.save(factura);
	}

	@Override
	public Populate findProductoById(Long id) {
		return populateDao.findById(id).orElse(null);
	}

	@Override
	public Factura findFacturaById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	public void deleteFactura(Long id) {
		facturaDao.deleteById(id);
	}

	@Override
	public Factura fetchFacturaByIdWithUsuarioWithItemFacturaWithProducto(Long id) {
		return facturaDao.fetchByIdWithUsuarioWithItemFacturaWithProducto(id);
	}

}
