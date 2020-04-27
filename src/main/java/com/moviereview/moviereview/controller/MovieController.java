package com.moviereview.moviereview.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
