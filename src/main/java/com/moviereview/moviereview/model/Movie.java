package com.moviereview.moviereview.model;

public class Movie {
	
	private int id;
	private String title;
	private String voteAverage;
	private String overview; 
	private String release_date;
	private String posterPath;
	
	public Movie(int id, String title, String voteAverage, String overview, String release_date, String posterPath) {
		this.id = id;
		this.title = title;
		this.voteAverage = voteAverage;
		this.overview = overview;
		this.release_date = release_date;
		this.posterPath = posterPath;
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
		return posterPath;
	}


	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
}