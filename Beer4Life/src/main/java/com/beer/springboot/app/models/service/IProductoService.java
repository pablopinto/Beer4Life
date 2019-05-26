package com.beer.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beer.springboot.app.models.entity.Producto;

public interface IProductoService{
	
public List<Producto> findAll();
	
	public Page<Producto> findAll(Pageable pageable);
	
	public void save(Producto producto);
	
	public Producto findOne(Long id);
	
	public void delete(Long id);
	
	public List<Producto> findByNombre(String term);

}
