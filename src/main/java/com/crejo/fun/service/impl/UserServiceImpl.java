package com.crejo.fun.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.crejo.fun.dao.UserDao;
import com.crejo.fun.dto.UserDTO;
import com.crejo.fun.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public void addUser(UserDTO user) throws DataIntegrityViolationException {
		userDao.addUser(user);
	}
}
