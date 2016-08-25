package com.zheng.springmvc.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.zheng.springmvc.entity.RoleEntity;
import com.zheng.springmvc.entity.UserEntity;

public class Role implements GrantedAuthority{
    
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getAuthority() {
        return this.name;
    }
    
    public RoleEntity toRoleEntity() {
    	RoleEntity roleEntity = new RoleEntity();
    	roleEntity.setId(id);
    	roleEntity.setName(name);
    	return roleEntity;
    }
}