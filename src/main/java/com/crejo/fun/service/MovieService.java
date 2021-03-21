package com.crejo.fun.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.crejo.fun.dto.MovieDTO;

public interface MovieService {

	void addMovie(MovieDTO movie) throws DataIntegrityViolationException;

	void reviewMovie(String userName, String movieName, int rating) throws Exception;

	List<String> topMoviesByCritics(int n, String genre);
	
	double avgReviewScorePerYear(int year);
	
	double avgReviewScoreOfMovie(String movieName);
}
