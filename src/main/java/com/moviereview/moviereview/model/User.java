package com.moviereview.moviereview.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@NotNull
    @NotEmpty
	@Column(name = "username")
	private String username;
	
	@NotNull
    @NotEmpty
	@Column(name = "email")
	private String email;

	@NotNull
    @NotEmpty
	@Column(name = "password")
	private String password;

	@Column(name = "status")
	private String status;
	
	public User (int id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<String> getRole() {
		List<String> roles = new ArrayList<>();
		roles.add("admin");
		return roles;
	}
	
}
 