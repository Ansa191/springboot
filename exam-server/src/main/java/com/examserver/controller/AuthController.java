package com.examserver.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examserver.models.JwtRequest;
import com.examserver.models.JwtResponse;
import com.examserver.models.User;
import com.examserver.security.JwtTokenHelper;
import com.examserver.services.UserService;



@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private UserService userService;
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
    
	@PostMapping("login/")
	public ResponseEntity<?> createToken(@RequestBody JwtRequest request) throws Exception{
		this.authenticate(request.getUserName(),request.getPassword());
	   UserDetails userDetails= this.userDetailsService.loadUserByUsername(request.getUserName());
	String token=jwtTokenHelper.generateToken(userDetails);
	JwtResponse jwtAuthResponse= new JwtResponse();
	jwtAuthResponse.setToken(token);
		return ResponseEntity.ok(jwtAuthResponse);
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	//get current login user details
	
	@GetMapping("/current-login")
	public User getCurrentUser(Principal p) {
		User user =(User) this.userDetailsService.loadUserByUsername(p.getName());
		System.out.println(user);
		return user;
	}
	
}

