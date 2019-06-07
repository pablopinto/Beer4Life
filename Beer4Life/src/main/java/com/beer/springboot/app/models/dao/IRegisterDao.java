package com.beer.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.beer.springboot.app.models.entity.Role;

public interface IRegisterDao extends PagingAndSortingRepository<Role,Long>{
	
}
