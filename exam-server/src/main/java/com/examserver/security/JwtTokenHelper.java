package com.examserver.security;

import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenHelper {

	/**
	 * 1. Generate Token
	 * 2. Generate Token for specific User (using Generate Token method)
	 * 3. validate Token
	 * 4. Retrieving any information from token we need the secret key
	 * 5. Get Username from Token
	 * 6. Get Expiration Date from Token
	 * 7. Check token is Experied?
	 */
	 private static final long JWT_TOKEN_VALIDITY=5*60*60;
	 private String secret="jwtTokenKey";
	 
	 //retrieve user name from token
	 public String getUsernameFromToken(String token) {
		 return getClaimFromToken(token,Claims::getSubject);
	 }
	 
	 //retrieve expiration date from jwt token
	 public Date getExpirationDateFromToken(String token) {
		 return getClaimFromToken(token,Claims::getExpiration);
	 }
	 public <T> T getClaimFromToken(String token,Function<Claims,T> claimsResolver) {
		 final Claims claims =getAllClaimsFromToken(token);
		 return claimsResolver.apply(claims);
	 }
	 //retrieving any information from token we need the secret key
	 private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	 //check if token has expired
	 
	public boolean isTokenExpired(String token) {
		final Date expiration= getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	//generate Token for User
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims= new HashMap<>();
		return doGenerateToken(claims,userDetails.getUsername());
	}
	
	//while creating the token
	//1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	//2. Sign the JWT using HS512 algorithm and secret key
	//3. according to the JWT compact serialization
	//compaction of the JWT to a URL-safe string

	public String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY *100)
						).signWith(SignatureAlgorithm.HS512, secret).compact();
		
	}
	//validate token
	public boolean validateToken(String token , UserDetails userDetails) {
		final String username=getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		
	}
}

