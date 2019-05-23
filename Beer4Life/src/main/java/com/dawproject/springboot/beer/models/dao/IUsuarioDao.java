package com.dawproject.springboot.beer.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.dawproject.springboot.beer.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	
	public Usuario findByUsername(String username);
}
