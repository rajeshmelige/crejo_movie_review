package com.crejo.fun.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crejo.fun.dto.MovieDTO;
import com.crejo.fun.exceptions.MovieNameAlreadyExistsException;
import com.crejo.fun.service.MovieService;

@RestController
@RequestMapping("/movie")
@Validated
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping("/add")
	public ResponseEntity addMovie(@RequestBody MovieDTO movie) {
		try {
			movieService.addMovie(movie);
		} catch(DataIntegrityViolationException ex) {
			throw new MovieNameAlreadyExistsException("Movie name already in use");
		}
		return new ResponseEntity("ok",HttpStatus.CREATED);
	}
	
	@PostMapping("/review")
	public ResponseEntity reviewMovie(@RequestParam("userName") String userName,
			@RequestParam("movieName") String movieName, 
			@RequestParam("rating") @Min(1) @Max(10) int rating) throws Exception {
		movieService.reviewMovie(userName,movieName,rating);
		return new ResponseEntity("ok",HttpStatus.OK);
	}
	
	@GetMapping("/topmovies")
	public ResponseEntity topMoviesByCritics(@RequestParam("number") int n,
			@RequestParam("genre") String genre) {
		return new ResponseEntity(movieService.topMoviesByCritics(n, genre), HttpStatus.OK);
	}
	
	@GetMapping("/averageofyear/{year}")
	public ResponseEntity avgReviewScorePerYear(@PathVariable("year") int year) {
		return new ResponseEntity(movieService.avgReviewScorePerYear(year), HttpStatus.OK);
	}
	
	@GetMapping("/averageofmovie/{movie}")
	public ResponseEntity avgReviewScorePerYear(@PathVariable("movie") String movie) {
		return new ResponseEntity(movieService.avgReviewScoreOfMovie(movie), HttpStatus.OK);
	}
	
}
