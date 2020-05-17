package com.moviereview.moviereview.model;

public class Actor {
	
	private int id;
	private String name;
	private String known_for_department;
	private String profile_path; 
	
	
	public Actor(int id, String name, String known_for_department, String profile_path) {
		this.id = id;
		this.name = name;
		this.known_for_department = known_for_department;
		this.profile_path = profile_path;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKnown_for_department() {
		return known_for_department;
	}

	public void setKnown_for_department(String known_for_department) {
		this.known_for_department = known_for_department;
	}

	public String getProfile_path() {
		return "https://image.tmdb.org/t/p/w200"+profile_path;
	}

	public void setProfile_path(String profile_path) {
		this.profile_path = profile_path;
	}
	
}