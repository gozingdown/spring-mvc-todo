package com.zheng.springmvc.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import com.zheng.springmvc.model.Role;
import com.zheng.springmvc.model.User;

@Entity
@Table(name="users")
public class UserEntity {
	
	private int id;
	private String username;
	private String password;
	private Set<RoleEntity> roleEntities;
	
	//it won't work without a default no-arg constructor
	public UserEntity() {
	}
	
	public UserEntity(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="username", unique=true)
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User toUser() {
		User user = new User(username, password);
		user.setId(id);
		Set<Role> roleSet = new HashSet<>();
		if(roleEntities != null && !roleEntities.isEmpty()) {
			for(RoleEntity roleEntity : roleEntities) {
				roleSet.add(roleEntity.toRole());
			}
		}
		user.setRoles(roleSet);
		return user;
	}
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="user_role", 
				joinColumns={@JoinColumn(name="user_id",referencedColumnName="user_id")}, 
				inverseJoinColumns={@JoinColumn(name="role_id",referencedColumnName="role_id")})
	public Set<RoleEntity> getRoleEntities() {
		Hibernate.initialize(roleEntities);
		return roleEntities;
	}
	
	public void setRoleEntities(Set<RoleEntity> roleEntities) {
		this.roleEntities = roleEntities;
	}

}
