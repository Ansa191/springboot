package com.examserver.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examserver.config.AppConstants;
import com.examserver.exceptions.UserFoundException;
import com.examserver.models.Roles;
import com.examserver.models.User;
import com.examserver.models.UserRoles;
import com.examserver.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		Roles role1 = new Roles();
		role1.setId(AppConstants.NORMAL_USER);
		role1.setName("ROLE_NORMAL");
		UserRoles userRoles = new UserRoles();
		userRoles.setRoles(role1);
		userRoles.setUser(user);
		Set<UserRoles> userRolesSet = new HashSet<>();
		userRolesSet.add(userRoles);
		User user2 = this.userService.createUser(user, userRolesSet);
		return user2;

	}
	//get
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username)
	{
		User user = userService.getUser(username);
		return user;
	}
	@PreAuthorize("has_Role('ADMIN')")
	@DeleteMapping("/{username}")
	public String deleteUser(@PathVariable String username) {
		this.userService.deleteUser(username);
		return "Deleted";
	}
	@PreAuthorize("has_Role('ADMIN')")
	@PutMapping("/update/{username}")
	public User updateUser(@PathVariable String username, @RequestBody User user){
		User user2 = this.userService.updateUser(user, username);
		return user2;
	}
}
