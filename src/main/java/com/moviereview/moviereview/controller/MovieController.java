package com.moviereview.moviereview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController 
public class MovieController {
	
	@GetMapping({"/", "/movieDetail"})
	 public ModelAndView movieDetail() {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("movieDetail");
			return modelAndView;
	 }

}
