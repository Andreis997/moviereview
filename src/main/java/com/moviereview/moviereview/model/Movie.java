package com.moviereview.moviereview.model;

import java.util.ArrayList;
import java.util.List;

import com.moviereview.moviereview.dao.ReviewDAO;

public class Movie {
	
	private int id;
	private String title;
	private String voteAverage;
	private String overview; 
	private String release_date;
	private String posterPath;
	private List<Review> reviews;
	private List<ExternalReview> externalReviews;
	
	public Movie(int id, String title, String voteAverage, String overview, String release_date, String posterPath) {
		this(id,title, voteAverage,overview, release_date, posterPath, null);
	}
	
	public Movie(int id, String title, String voteAverage, String overview, String release_date, String posterPath, List<ExternalReview> externalReviews) {
		this.id = id;
		this.title = title;
		this.voteAverage = voteAverage;
		this.overview = overview;
		this.release_date = release_date;
		this.posterPath = posterPath;
		this.reviews = new ArrayList<>();
		this.externalReviews = externalReviews;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getVoteAverage() {
		return voteAverage;
	}


	public void setvoteAverage(String voteAverage) {
		this.voteAverage = voteAverage;
	}


	public String getOverview() {
		return overview;
	}


	public void setOverview(String overview) {
		this.overview = overview;
	}


	public String getRelease_date() {
		return release_date;
	}


	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}


	public String getPosterPath() {
		return "https://image.tmdb.org/t/p/w200" + posterPath;
	}


	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
	
	public List<Review> getReviews() {
		if (reviews.isEmpty()) {
			reviews = ReviewDAO.getInstance().getAllReviewsOfMovie(this.id);
		}
		return reviews;
	}
	
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public List<ExternalReview> getExternalReviews() {
		return externalReviews;
	}
	
	public void setExternalReviews(List<ExternalReview> externalReviews) {
		this.externalReviews = externalReviews;
	}
	
	public String getAverageStyle() {
		double a = Double.parseDouble(this.voteAverage) * 10;
		return  "width: " + a + "%";
	}
	
}