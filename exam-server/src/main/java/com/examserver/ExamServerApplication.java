package com.examserver;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.examserver.config.AppConstants;
import com.examserver.models.Roles;
import com.examserver.models.User;
import com.examserver.models.UserRoles;
import com.examserver.repositries.RoleRepo;
import com.examserver.services.UserService;

@SpringBootApplication
public class ExamServerApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;
@Autowired
private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args)  {
		SpringApplication.run(ExamServerApplication.class, args);
		
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("123"));
//		
//		User user = new User();
//        user.setEmail("ansa@gmail.com");
//        user.setFirstName("ansa");
//        user.setLastName("ch");
//        user.setPassword("1234");
//        user.setPhone(1234L);
//        user.setUserName("rim12");
//        user.setProfile("default.png");
//       
//		Roles role1 = new Roles();
//		role1.setId(AppConstants.NORMAL_USER);
//		role1.setName("ROLE_NORMAL");
//		
//		Roles role2 = new Roles();
//		role2.setId(AppConstants.ADMIN_USER);
//		role2.setName("ROLE_ADMIN");
//		
//		Set<UserRoles> userRolesSet = new HashSet<>();
//		UserRoles userRoles1= new UserRoles();
//		userRoles1.setRoles(role1);
//		userRoles1.setUser(user);
//	
//		userRolesSet.add(userRoles1);
//		
//		UserRoles userRoles2= new UserRoles();
//		userRoles2.setRoles(role2);
//		userRoles2.setUser(user);
//
//		userRolesSet.add(userRoles2);
//		
//		User user2= userService.createUser(user, userRolesSet);
//	    System.out.println(userRoles1.getRoles());
//		
//		
	}

}
