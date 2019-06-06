package com.beer.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beer.springboot.app.models.entity.Populate;

public interface IPopulateDao extends PagingAndSortingRepository<Populate,Long> {

}
