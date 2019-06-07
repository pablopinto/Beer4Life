package com.beer.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beer.springboot.app.models.entity.Role;

public interface IRegisterService {

	
	public List<Role> findAll();

	public Page<Role> findAll(Pageable pageable);

	public void save(Role role);

	public Role findOne(Long id);

	public void delete(Long id);
}
