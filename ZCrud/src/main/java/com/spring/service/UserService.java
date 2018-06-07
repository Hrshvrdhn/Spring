package com.spring.service;

import com.spring.model.UserDetails;

public interface UserService {

	public UserDetails save(UserDetails user);
	public String delete(long id);
}
