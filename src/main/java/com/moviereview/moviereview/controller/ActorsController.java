package com.moviereview.moviereview.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.moviereview.moviereview.model.Actor;
import com.moviereview.moviereview.util.MovieApi;

@RestController 
public class ActorsController {

	@GetMapping({"/", "/actors"})
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("actors");

		List<Actor> actors = MovieApi.getPopularActors();
		modelAndView.addObject("actors", actors);
		
		return modelAndView;
	}
}