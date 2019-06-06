package com.beer.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beer.springboot.app.models.dao.IPopulateDao;
import com.beer.springboot.app.models.entity.Populate;

@Service
public class PopulateServiceImp implements IPopulateService{
	
	@Autowired
	private IPopulateDao populateDao;

	@Override
	@Transactional
	public void save(Populate populate) {
		populateDao.save(populate);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Populate> findAll() {
		return (List<Populate>) populateDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Populate> findAll(Pageable pageable) {
		return populateDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Populate findOne(Long id) {
		return populateDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		populateDao.deleteById(id);
	}

}
