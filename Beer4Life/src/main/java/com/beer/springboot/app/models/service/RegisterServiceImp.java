package com.beer.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beer.springboot.app.models.dao.IRegisterDao;
import com.beer.springboot.app.models.entity.Role;


@Service
public class RegisterServiceImp implements IRegisterService {
	
	@Autowired
	private IRegisterDao registerDao;

	@Override
	@Transactional(readOnly = true)
	public List<Role> findAll() {
		return (List<Role>) registerDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Role> findAll(Pageable pageable) {
		return registerDao.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Role role) {
		registerDao.save(role);
	}

	@Override
	@Transactional(readOnly = true)
	public Role findOne(Long id) {
		return registerDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		registerDao.deleteById(id);
	}

}
