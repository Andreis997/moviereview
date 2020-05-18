package com.moviereview.moviereview.controller;

import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.moviereview.moviereview.dao.UserDAO;
import com.moviereview.moviereview.model.User;

@RestController
public class AdminController {

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("adminPage");
		List<User> users = UserDAO.getInstance().getUsers();

		modelAndView.addObject("users", users);

		return modelAndView;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public ModelAndView setRole(@ModelAttribute("id") String id, @RequestParam("role") String role,
			BindingResult result, ModelMap model) {
		
		UserDAO.getInstance().setUserRole(Integer.valueOf(id), role);
		
		return new ModelAndView("redirect:admin");
	}

}
