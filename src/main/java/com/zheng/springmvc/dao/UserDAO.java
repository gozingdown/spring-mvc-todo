package com.zheng.springmvc.dao;

import java.util.List;

import com.zheng.springmvc.entity.UserEntity;

public interface UserDAO {

    public void addUser(UserEntity user);
    public void updateUser(UserEntity user);
    public UserEntity getUser(int id);
    public void deleteUser(int id);
    public List<UserEntity> getUsers();
    public UserEntity getUserByName(String username);
}
