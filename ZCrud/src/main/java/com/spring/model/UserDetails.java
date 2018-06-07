package com.spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDetails {

	@Id
	public String idVal;
	public String name;
	
	public String getId() {
		return idVal;
	}
	public void setId(String id) {
		this.idVal = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
