package com.examserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.examserver.security.CustomUserDetailsService;
import com.examserver.security.JWTAuthenticationEntryPoint;
import com.examserver.security.JWTAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
	@Autowired
	private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private JWTAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	public static final String[] PUBLIC_URLS= {
		      "/swagger-ui/**", "/login/","/v3/api-docs","/v2/api-docs",
		       "/swagger-resources/**", "/webjars/**","/user/"	
	};

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf()
		.disable()
		.cors()
		.disable()
		.authorizeHttpRequests()
		.antMatchers(PUBLIC_URLS).permitAll()
		.antMatchers(HttpMethod.OPTIONS).permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	    http.authenticationProvider(daoAuthenticationProvider());
		DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
		return defaultSecurityFilterChain;
	}
	
	/* This method will takes all userName that are in DB from customUserDeatilsService class
	also specify password encoder 
	*/
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.customUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}
	
	//will used to authenticate userName and password
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	
}
