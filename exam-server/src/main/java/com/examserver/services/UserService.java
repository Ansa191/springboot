package com.examserver.services;

import java.util.Set;

import com.examserver.models.User;
import com.examserver.models.UserRoles;

public interface UserService {

	//create user
	public User createUser(User user,Set<UserRoles> userRoles) throws Exception;
	
	//delete
	public void deleteUser(String username);
	//update
	public User updateUser(User user, String username);
	//get
	public User getUser(String username);
}
