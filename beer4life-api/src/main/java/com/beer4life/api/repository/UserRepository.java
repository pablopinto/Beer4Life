package com.beer4life.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beer4life.api.model.Beer;

@Repository
public interface UserRepository extends JpaRepository<Beer, Long> {}
