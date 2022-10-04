package com.examserver.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice.This;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Users")
public class User implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String userName;
 private String password;
 private String firstName;
 private String lastName;
 private String email;
 private String profile;
 private boolean enabled=true;
 private Long phone;
 
 @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "user")
 @JsonIgnore
 private Set<UserRoles> userRole= new HashSet<>();

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	Set<Authority> authorities = new HashSet<>();
	userRole.forEach((role)->{
		authorities.add(new Authority(role.getRoles().getName()));
	});
	return authorities;
}

@Override
public String getUsername() {
	return this.userName;
}

@Override
public boolean isAccountNonExpired() {
	return true;
}

@Override
public boolean isAccountNonLocked() {
	return true;
}

@Override
public boolean isCredentialsNonExpired() {
	return true;
}
@Override
public boolean isEnabled() {
	return this.enabled;
}
 }
