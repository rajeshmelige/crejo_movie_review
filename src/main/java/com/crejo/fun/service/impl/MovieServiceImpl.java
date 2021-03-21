package com.crejo.fun.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.crejo.fun.dao.MovieDao;
import com.crejo.fun.dao.ReviewDao;
import com.crejo.fun.dao.UserDao;
import com.crejo.fun.dto.MovieDTO;
import com.crejo.fun.entity.Movie;
import com.crejo.fun.entity.User;
import com.crejo.fun.exceptions.MovieNotReleasedException;
import com.crejo.fun.exceptions.MultipleReviewException;
import com.crejo.fun.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieDao movieDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ReviewDao reviewDao;

	@Override
	@Transactional
	public void addMovie(MovieDTO movie) throws DataIntegrityViolationException {
		movieDao.addMovie(movie);
	}

	@Override
	@Transactional
	public void reviewMovie(String userName, String movieName, int rating) throws Exception {
		User user = userDao.getUserByName(userName);
		Movie movie = movieDao.getMovieByName(movieName);
		if(hasUserAlreadyReviewed(user,movie)) {
			throw new MultipleReviewException("Multiple reviews not allowed");
		} else if(isMovieNotReleased(movie)) {
			throw new MovieNotReleasedException("Movie yet to be released");
		} else {
			reviewDao.addReview(user,movie,rating);
			userDao.updateUser(user);
		}
	}
	
	@Override
	@Transactional
	public List<String> topMoviesByCritics(int n, String genre) {
		List<String> movies = reviewDao.topMoviesByCritics(n, genre);
		return movies;
	}
	
	@Override
	@Transactional
	public double avgReviewScorePerYear(int year) {
		double avg = reviewDao.avgReviewScorePerYear(year);
		return avg;
	}
	
	@Override
	@Transactional
	public double avgReviewScoreOfMovie(String movieName) {
		double avg = reviewDao.avgReviewScoreOfMovie(movieName);
		return avg;
	}
	
	private boolean isMovieNotReleased(Movie movie) {
		if(movie.getReleaseYear() <= 2020)
			return false;
		return true;
	}

	private boolean hasUserAlreadyReviewed(User user, Movie movie) {
		return reviewDao.hasUserAlreadyReviewed(user,movie);
	}
	
}
