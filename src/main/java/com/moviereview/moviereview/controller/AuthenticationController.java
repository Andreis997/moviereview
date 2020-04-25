package com.moviereview.moviereview.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.moviereview.moviereview.dao.UserDAO;
import com.moviereview.moviereview.model.User;
import com.moviereview.moviereview.service.SecurityServiceImpl;


@RestController 
public class AuthenticationController {
	
	@Autowired
    private SecurityServiceImpl securityService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	  @RequestMapping(value = "/movieDetail")
	  public ModelAndView movieDetail() {
			ModelAndView modelAndView = new ModelAndView();

			modelAndView.setViewName("movieDetail");
			return modelAndView;
	  }

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
			userDAO.createUser(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()), user.getEmail());
			securityService.autoLogin(user.getUsername(), user.getPassword());
			modelAndView = new ModelAndView("redirect:/home");
		} else {
			modelAndView.setViewName("register"); 
		}
		
		return modelAndView;
	}
	
	@GetMapping({"/", "/home"})
	public ModelAndView index() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		modelAndView.addObject("user", auth.getName());
		modelAndView.addObject("roles", auth.getAuthorities());
		
		return modelAndView;
	}
}
