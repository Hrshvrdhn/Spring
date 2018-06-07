package com.spring.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//import org.hibernate.SessionFactory;

import com.spring.model.UserDetails;

@Repository
public class UserDaoImp implements UserDao{
	
	@Autowired
	SessionFactory factory;

	@Override
	@Transactional
	public UserDetails save(UserDetails user) {
		System.out.println("user id : "+user.getId()+" : naem : "+user.getName());
		factory.getCurrentSession().save(user);
		return user;
	}

	@Override
	public String delete(long id) {
		UserDetails user = (UserDetails) factory.getCurrentSession().get(UserDetails.class, id);
		factory.getCurrentSession().delete(user);
		return user.name;
	}

}
