package com.zheng.springmvc.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.zheng.springmvc.entity.RoleEntity;
import com.zheng.springmvc.entity.UserEntity;

public class User implements UserDetails{
	private int id;
	@Size(min=4, message="Enter at least 4 character")
	private String username;
	@Size(min=4, message="Enter at least 4 character")
	private String password;
	private Set<Role> roles;
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	/* Spring Security related fields*/
    private List<Role> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
	
	public User() {
		super();
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.roles = new HashSet<>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserEntity toUserEntity() {
		UserEntity userEntity = new UserEntity(username, password);
		Set<RoleEntity> roleEntitySet = new HashSet<>();
		if(this.getRoles() != null && !this.getRoles().isEmpty())  {
			for(Role role : this.getRoles()) {
				roleEntitySet.add(role.toRoleEntity());
			}
		}
		userEntity.setRoleEntities(roleEntitySet);
		userEntity.setId(id);
		return userEntity;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return accountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return accountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return credentialsNonExpired;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
