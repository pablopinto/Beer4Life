package com.beer.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beer.springboot.app.models.entity.Populate;

public interface IPopulateDao extends PagingAndSortingRepository<Populate,Long> {
	
	@Query("select p from Populate p where p.name like %?1%")
	public List<Populate> findByName(String term);	
	public List<Populate> findByNameLikeIgnoreCase(String term);

}
