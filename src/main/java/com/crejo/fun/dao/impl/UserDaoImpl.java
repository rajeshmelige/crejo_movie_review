package com.crejo.fun.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.crejo.fun.dao.UserDao;
import com.crejo.fun.dto.UserDTO;
import com.crejo.fun.entity.User;
import com.crejo.fun.exceptions.NotAnUserException;

@Repository
public class UserDaoImpl implements UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addUser(UserDTO user) throws DataIntegrityViolationException {
		User ue = new User();
		ue.setName(user.getName());
		ue.setRole("Viewer");
			entityManager.persist(ue);
	}

	@Override
	public User getUserByName(String userName) {
		User user = null;
		try {
			user =(User) entityManager.createQuery("SELECT u FROM User u where u.name = :userName")
                .setParameter("userName",userName).getSingleResult();
		} catch(NoResultException e) {
			throw new NotAnUserException("Not a valid user! Please register");
		}
		return user;
	}

	@Override
	public void updateUser(User user) {
		user.setReviewCount(user.getReviewCount() + 1);
		if(user.getReviewCount() >= 3) {
			user.setRole("Critic");
		} 
		entityManager.merge(user);
	}
}
