package com.crejo.fun.dao;

import java.util.List;

import com.crejo.fun.entity.Movie;
import com.crejo.fun.entity.User;

public interface ReviewDao {

	void addReview(User user, Movie movie, int rating);

	boolean hasUserAlreadyReviewed(User user, Movie movie);

	List<String> topMoviesByCritics(int n, String genre);

	double avgReviewScorePerYear(int year);
	
	double avgReviewScoreOfMovie(String name);
}
