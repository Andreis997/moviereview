package com.moviereview.moviereview.model;

public class ExternalReview {
	private String id;
	private String author;
	private String content;
	private String url;
	
	public ExternalReview(String id, String author, String content, String url) {
		this.id = id;
		this.author = author;
		this.content = content;
		this.url = url;
	}
	
	public String getId() {
		return id;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getUrl() {
		return url;
	}
}
