package com.beer4life.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beer4life.api.exception.ResourceNotFoundException;
import com.beer4life.api.model.Beer;
import com.beer4life.api.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class BeerController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/beer")
  public List<Beer> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/beer/{id}")
  public ResponseEntity<Beer> getUsersById(@PathVariable(value = "id") Long userId)
      throws ResourceNotFoundException {
    Beer user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
    return ResponseEntity.ok().body(user);
  }


}
