package com.spring.dao;

import com.spring.model.UserDetails;

public interface UserDao {
	
	public UserDetails save(UserDetails user);
	public String delete(long id);

}
