package com.moviereview.moviereview.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.moviereview.moviereview.dao.UserDAO;
import com.moviereview.moviereview.model.Actor;
import com.moviereview.moviereview.model.User;
import com.moviereview.moviereview.util.MovieApi;

@RestController 
public class ActorsController {

	@GetMapping({"/", "/actors"})
	public ModelAndView index() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails s = ((UserDetails) principal);
		User user = UserDAO.getInstance().findUserByUsername(s.getUsername());		
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("actors");

		List<Actor> actors = MovieApi.getPopularActors();
		modelAndView.addObject("actors", actors);
		modelAndView.addObject("user", user);
		
		return modelAndView;
	}
}