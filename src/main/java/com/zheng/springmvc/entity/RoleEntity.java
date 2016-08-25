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

import com.zheng.springmvc.model.Role;
import com.zheng.springmvc.model.User;

/*
 * When you have a bidirectional association, one side is the owner side (the side without the mappedBy attribute), 
 * and the other is the inverse side (the one with the mappedBy attribute).
 * Hibernate only considers the owner side to know if there is an association between two entities. 
 * So, if A is the owner side in the relation between A and B, you must add B instances to A's collection to make an association persistent. Adding A instances to B's collection has no effect.
 * In general, you are responsible for maintaining the coherence of the object graph, and thus you should make sure that the owner side (at least) is always updated.
 */
@Entity
@Table(name="roles")
public class RoleEntity {
     
    private Integer id;
    private String name;
	private Set<UserEntity> userEntities;
	
    public RoleEntity() {
	}
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="name", unique=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="roleEntities")
	public Set<UserEntity> getUserEntities() {
		return userEntities;
	}

	public void setUserEntities(Set<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}
	
	public Role toRole() {
		Role role = new Role();
		role.setId(id);
		role.setName(name);
		return role;
	}

}