package com.crejo.fun.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.crejo.fun.dao.MovieDao;
import com.crejo.fun.dto.MovieDTO;
import com.crejo.fun.entity.Movie;
import com.crejo.fun.exceptions.MovieNameNotFoundException;

@Repository
public class MovieDaoImpl implements MovieDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void addMovie(MovieDTO movie) throws DataIntegrityViolationException {
		Movie me = new Movie();
		me.setName(movie.getName());
		me.setReleaseYear(movie.getReleaseYear());
		me.setGenre(movie.getGenre());
		entityManager.persist(me);	
	}

	@Override
	public Movie getMovieByName(String movieName) {
		Movie movie = null;
		try {
			movie = (Movie) entityManager.createQuery("SELECT m FROM Movie m where m.name = :movieName")
	                .setParameter("movieName",movieName).getSingleResult();
		} catch(NoResultException ex) {
			throw new MovieNameNotFoundException("Movie name doesn't exist");
		}
		return movie;
	}
}
