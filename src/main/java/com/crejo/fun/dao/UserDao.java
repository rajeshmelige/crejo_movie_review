package com.crejo.fun.dao;

import org.springframework.dao.DataIntegrityViolationException;

import com.crejo.fun.dto.UserDTO;
import com.crejo.fun.entity.User;

public interface UserDao {

	void addUser(UserDTO user) throws DataIntegrityViolationException;

	User getUserByName(String userName);

	void updateUser(User user) throws Exception;

}
