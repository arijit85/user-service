package com.ari.demo.userservice.builder;

import com.ari.demo.userservice.entity.User;

public class UserBuilder {
	private String firstName;
	private String lastName; 
	
	public UserBuilder buildFirsName(String firstName){
		this.firstName = firstName;
		return this;
	}
	
	public UserBuilder buildLastName(String lastName){
		this.lastName = lastName;
		return this;
	}
	
	public User build(){
		return new User(firstName, lastName);
	}

}
