package com.beer.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.beer.springboot.app.models.entity.Usuario;

public interface IUsuarioDao extends PagingAndSortingRepository<Usuario, Long> {

	
	public Usuario findByUsername(String username);
	public Usuario findByEmail(String email);
}
