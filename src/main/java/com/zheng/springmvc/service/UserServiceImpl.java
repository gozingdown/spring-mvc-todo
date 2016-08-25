package com.zheng.springmvc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zheng.springmvc.dao.UserDAO;
import com.zheng.springmvc.entity.UserEntity;
import com.zheng.springmvc.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
     
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// TODO Auto-generated method stub
		userDAO.addUser(user.toUserEntity());
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDAO.updateUser(user.toUserEntity());
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return userDAO.getUser(id).toUser();
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userDAO.deleteUser(id);
	}

	@Override
	public List<User> getUsers() {
		return userDAO.getUsers().stream().map(x -> x.toUser()).collect(Collectors.toList());
	}

	@Override
	public User getUserByName(String username) {
		UserEntity userEntity = userDAO.getUserByName(username);
		if(userEntity != null) {
			return userEntity.toUser();
		}
		return null;
	}
	
	@Override
	public boolean hasUser(String username) {
		UserEntity userEntity = userDAO.getUserByName(username);
		if(userEntity != null) {
			return true;
		}
		return false;
	}
 
}
