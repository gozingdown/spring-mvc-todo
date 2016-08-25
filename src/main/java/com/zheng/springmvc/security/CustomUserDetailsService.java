package com.zheng.springmvc.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zheng.springmvc.model.Role;
import com.zheng.springmvc.model.User;
import com.zheng.springmvc.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
    private UserService userService;
	
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserByName(username);
		if (user != null && user.getRoles() != null) {
			// hard-coded TODO create entity for Role
			List<Role> roles = user.getRoles().stream().collect(Collectors.toList());
			user.setAuthorities(roles);
		}
		return user;
	}
}
