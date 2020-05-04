package com.moviereview.moviereview.model;

import java.util.Date;

public class Review {

	private int id;
	private int movieIdApi;
    private User user;
    private String content;
    private Date createdAt;
	
    public Review(int id, int movieIdApi, User user, String content, Date createdAt) {
		this.id = id;
		this.movieIdApi = movieIdApi;
		this.user = user;
		this.content = content;
		this.createdAt = createdAt;
	}
    
	public int getMovieIdApi() {
		return movieIdApi;
	}
	
	public User getUser() {
		return user;
	}
	
	public String getContent() {
		return content;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setMovieIdApi(int movieIdApi) {
		this.movieIdApi = movieIdApi;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
