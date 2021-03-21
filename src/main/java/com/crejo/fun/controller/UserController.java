package com.crejo.fun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crejo.fun.dto.UserDTO;
import com.crejo.fun.exceptions.UserNameAlreadyExistsException;
import com.crejo.fun.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/get")
	public ResponseEntity getUser() {
		return new ResponseEntity("OK", HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity addUser(@RequestBody UserDTO user) {
		try {
			userService.addUser(user);
		} catch(DataIntegrityViolationException ex) {
			throw new UserNameAlreadyExistsException("User name already in use");
		}
		return new ResponseEntity("OK", HttpStatus.CREATED);
	}
}
