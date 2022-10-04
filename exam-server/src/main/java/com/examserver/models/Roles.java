package com.examserver.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Roles {
	@Id
 private Long id;
 private String name;
 
 @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "roles")
 @JsonIgnore
 private Set<UserRoles> userRoles= new HashSet<>();
 
}
