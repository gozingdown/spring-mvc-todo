package com.zheng.springmvc.service;

import java.util.List;

import com.zheng.springmvc.entity.TeamEntity;
import com.zheng.springmvc.entity.UserEntity;
import com.zheng.springmvc.model.User;

public interface UserService {
     
    public void addUser(User user);
    public void updateUser(User user);
    public User getUser(int id);
    public void deleteUser(int id);
    public List<User> getUsers();
    public User getUserByName(String username);
    public boolean hasUser(String username);
    
}