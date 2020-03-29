package com.moviereview.moviereview.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.moviereview.moviereview.dao.UserDAO;
import com.moviereview.moviereview.model.User;


@RestController 
public class AuthenticationController {

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user); 
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registerUserAccount (@ModelAttribute("user") @Valid User user, BindingResult result, WebRequest request, Errors errors) {    
		ModelAndView modelAndView = new ModelAndView();
		UserDAO userDAO = UserDAO.getInstance();
		boolean userExists = userDAO.userExists(user.getEmail());
		
		if (!result.hasErrors() && !userExists) {
			userDAO.createUser(user.getUsername(), user.getPassword(), user.getEmail());
			modelAndView = new ModelAndView("redirect:/login");
		} else {
			modelAndView.setViewName("register"); 
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@RequestMapping("/")
	public String index() {
		return "root";
	}
}
