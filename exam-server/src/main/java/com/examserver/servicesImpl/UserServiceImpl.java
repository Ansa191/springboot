package com.examserver.servicesImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examserver.config.AppConstants;
import com.examserver.exceptions.UserFoundException;
import com.examserver.models.Roles;
import com.examserver.models.User;
import com.examserver.models.UserRoles;
import com.examserver.repositries.RoleRepo;
import com.examserver.repositries.UserRepo;
import com.examserver.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(User user, Set<UserRoles> userRoles) throws Exception {
		User local = userRepo.findByuserName(user.getUsername());
		

		if (local != null) {
			//System.out.println("User exits");
			throw new UserFoundException();
		} else {
			// user create
			for (UserRoles ur : userRoles) {
				roleRepo.save(ur.getRoles());
				
			}
			user.setProfile("default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.getUserRole().addAll(userRoles);
			local= userRepo.save(user);
		}
		return local;

	}

	@Override
	public void deleteUser(String username) {
	 User user=	this.userRepo.findByuserName(username);
	 userRepo.delete(user);
		
	}

	@Override
	public User updateUser(User user, String username) {
		User user2= this.userRepo.findByuserName(username);
		user2.setEmail(user.getEmail());
		user2.setLastName(user.getLastName());
		user2.setFirstName(user.getFirstName());
		user2.setPassword(user.getPassword());
		user2.setPhone(user.getPhone());
		User newUser=this.userRepo.save(user2);
		return newUser;
	}

	@Override
	public User getUser(String username) {
	User user=this.userRepo.findByuserName(username);
		return user;
	}

}
