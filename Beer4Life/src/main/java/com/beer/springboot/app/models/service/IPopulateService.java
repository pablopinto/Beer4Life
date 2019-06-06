package com.beer.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beer.springboot.app.models.entity.Populate;

public interface IPopulateService {
	
	public void save(Populate populate);

	public List<Populate> findAll();

	public Page<Populate> findAll(Pageable pageable);

	public Populate findOne(Long id);

	public void delete(Long id);

}
