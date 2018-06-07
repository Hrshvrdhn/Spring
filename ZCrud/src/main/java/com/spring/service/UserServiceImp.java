package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.UserDao;
import com.spring.model.UserDetails;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	UserDao dao;

	@Override
	public UserDetails save(UserDetails user) {
		return dao.save(user);
	}

	@Override
	public String delete(long id) {
		return dao.delete(id);
	}

}
