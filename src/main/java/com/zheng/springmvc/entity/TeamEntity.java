package com.zheng.springmvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="teams")
public class TeamEntity {
     
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
    private Integer id;
	@Column(name="name")
    private String name;
	@Column(name="rating")
    private Integer rating;
	
    public TeamEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public TeamEntity(String name, Integer rating) {
		super();
		this.name = name;
		this.rating = rating;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
 
}