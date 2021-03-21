package com.crejo.fun.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.crejo.fun.dao.ReviewDao;
import com.crejo.fun.entity.Movie;
import com.crejo.fun.entity.Review;
import com.crejo.fun.entity.User;
import com.crejo.fun.exceptions.MovieNameNotFoundException;

@Repository
public class ReviewDaoImpl implements ReviewDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addReview(User user, Movie movie, int rating) {
		Review review = new Review();
		review.setUser(user);
		review.setMovie(movie);
		if(user.getRole().equals("Critic")) {
			review.setRating(rating*2);
		} else {
			review.setRating(rating);
		}
		entityManager.persist(review);
	}

	@Override
	public boolean hasUserAlreadyReviewed(User user, Movie movie) {
		Review review = null;
		try {
			review =(Review) entityManager.createQuery("SELECT r FROM Review r where r.user = :user and r.movie = :movie")
                .setParameter("user",user)
                .setParameter("movie", movie)
                .getSingleResult();
		} catch(NoResultException e) {
			return false;
		}
		return true;
	}

	@Override
	public List<String> topMoviesByCritics(int n, String genre) {
		List<String> movies = null;
		String sql = "select m.name from Review r "
				+ "join Movie m on m.id=r.movieid "
				+ "join User u on u.id=r.userid "
				+ "where u.role='Critic' and m.genre = :genre "
				+ "group by m.name "
				+ "order by avg(r.rating) desc "
				+ "limit :n";
		try {
			Query query = entityManager.createNativeQuery(sql)
					.setParameter("genre", genre)
					.setParameter("n", n);
			movies = query.getResultList();
		} catch(Exception e) {
			
		}
		return movies;
	}

	@Override
	public double avgReviewScorePerYear(int year) {
		double avg = 0.0;
		String sql = "select AVG(r.rating) from Review r "
				+ "join Movie m on m.id = r.movieid "
				+ "where m.release_year = :releaseyear";
		Query query = entityManager.createNativeQuery(sql)
					.setParameter("releaseyear", year);
		try {
			avg = (double) query.getSingleResult();
		} catch(NullPointerException ex) {
			throw new MovieNameNotFoundException("No movie released in the year");
		}
		return avg;
	}
	
	@Override
	public double avgReviewScoreOfMovie(String name) {
		double avg = 0.0;
		String sql = "select AVG(r.rating) from Review r "
				+ "join Movie m on m.id = r.movieid "
				+ "where m.name = :moviename";
		Query query = entityManager.createNativeQuery(sql)
				.setParameter("moviename", name);
		try {
			avg = (double) query.getSingleResult();
		} catch(Exception e) {
			throw new MovieNameNotFoundException("Movie name doesn't exist");
		}
		return avg;
	}

}
