package com.moviereview.moviereview.controller;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.moviereview.moviereview.model.Movie;
import com.moviereview.moviereview.util.MovieApi;


@RestController 
public class MovieController {
		
	@RequestMapping(value = "/movieDetail", method = RequestMethod.GET)
	public ModelAndView movieDetail(@ModelAttribute("id") String id, BindingResult result, ModelMap model) {
	    
		if(result.hasErrors()) {
			System.out.println("err");
		}
	    ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("movieDetail");

		Movie movie = MovieApi.getMovie(id);
		modelAndView.addObject("currentMovie", movie);
		
		return modelAndView;
	}

}
