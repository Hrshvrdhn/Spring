package com.spring.controller;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import com.spring.model.UserDetails;
import org.springframework.http.MediaType;
import com.spring.service.UserServiceImp;

@RestController
public class UserController {
	
	@Autowired
	UserServiceImp service;
	
	@RequestMapping(method=RequestMethod.GET,value="/welcome")
	public String getH(ModelMap map) {
		map.addAttribute("message","hello message");
		return "showMessage";
	}
	
	@PostMapping(value="/saveUser", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public UserDetails save(@RequestBody UserDetails user) {
		System.out.println("from json input : "+user.idVal+" : "+user.getName());
		service.save(user);
		JSONObject jsn = new JSONObject();
		jsn.put("Result", user.getName());
		return user;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/delUser/{id}")
	public String delete(@PathVariable("id") long id) {
		return service.delete(id);
	}
	
	

}
