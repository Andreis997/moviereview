package com.moviereview.moviereview.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.moviereview.moviereview.dao.ReviewDAO;
import com.moviereview.moviereview.dao.UserDAO;
import com.moviereview.moviereview.model.Movie;
import com.moviereview.moviereview.model.User;
import com.moviereview.moviereview.util.MovieApi;

@RestController
public class MovieController {

	@RequestMapping(value = "/movieDetail", method = RequestMethod.GET)
	public ModelAndView movieDetail(@ModelAttribute("id") String id, BindingResult result, ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		UserDetails s = ((UserDetails) principal);
		User user = UserDAO.getInstance().findUserByUsername(s.getUsername());
		
		if (result.hasErrors()) {
			System.out.println("err");
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("movieDetail");

		Movie movie = MovieApi.getMovie(id);
		modelAndView.addObject("currentMovie", movie);
		modelAndView.addObject("movieId", id);
		modelAndView.addObject("user", user);

		return modelAndView;
	}

	@RequestMapping(value = "/addReview", method = RequestMethod.POST)
	public ModelAndView addReview(@ModelAttribute("id") String id, @RequestParam("review") String review, @RequestParam("rating") String rating,
			BindingResult result, ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		UserDetails s = ((UserDetails) principal);
		User u = UserDAO.getInstance().findUserByUsername(s.getUsername());
		ReviewDAO.getInstance().createReview(Integer.valueOf(id), u, review, rating);

		return new ModelAndView("redirect:movieDetail?id=" + id);
	}

}
