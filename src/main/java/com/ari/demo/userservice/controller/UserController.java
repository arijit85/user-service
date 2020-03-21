package com.ari.demo.userservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ari.demo.userservice.entity.User;
import com.ari.demo.userservice.repository.UserRepo;

@RestController
@RequestMapping("/users")
public class UserController {
	
	public UserController(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	private final UserRepo userRepo;
	
	@GetMapping
	public List<User> getUsers(){
		return userRepo.findAll();
	}
}
