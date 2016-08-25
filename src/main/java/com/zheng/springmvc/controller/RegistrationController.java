package com.zheng.springmvc.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zheng.springmvc.model.Role;
import com.zheng.springmvc.model.User;
import com.zheng.springmvc.service.UserService;

@Controller
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegistrationPage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		//if user's logged in, log it out
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) { 
			 return "redirect:logout";
		}
		//!!!cannot use below commented-out code, it will cause csrf-validation failure.
//		// make sure everyone's logged out
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if(auth != null) {
//			new SecurityContextLogoutHandler().logout(request, response, auth);
//			request.getSession().invalidate();
//		}
		model.put("user",new User());
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@Valid User user, BindingResult bindingResult, ModelMap model) {
		if(bindingResult.hasErrors()) {
			return "register";
		}
		if(userService.hasUser(user.getUsername())) {
			model.put("error", "User already exists!");
			return "register";
		}
		//add default role
		Set<Role> roles = new HashSet<>();
		Role role = new Role();
		role.setId(1);
		role.setName("ROLE_USER");
		roles.add(role);
		user.setRoles(roles);
		userService.addUser(user);
		model.put("welcome_msg", "Registration successful! Please log in to continue browsing.");
		return "login";
	}
}
