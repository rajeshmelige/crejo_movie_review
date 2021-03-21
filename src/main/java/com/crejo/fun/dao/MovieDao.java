package com.crejo.fun.dao;

import org.springframework.dao.DataIntegrityViolationException;

import com.crejo.fun.dto.MovieDTO;
import com.crejo.fun.entity.Movie;

public interface MovieDao {

	void addMovie(MovieDTO movie) throws DataIntegrityViolationException;

	Movie getMovieByName(String movieName);

}
