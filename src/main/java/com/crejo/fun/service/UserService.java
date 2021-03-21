package com.crejo.fun.service;

import org.springframework.dao.DataIntegrityViolationException;

import com.crejo.fun.dto.UserDTO;

public interface UserService {

	void addUser(UserDTO user) throws DataIntegrityViolationException;

}
