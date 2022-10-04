package com.excel.mysql.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Audience {
	@Id
  private Integer numbers;
  private String username;
  private String name;
/**
 * @return the numbers
 */
public Integer getNumbers() {
	return numbers;
}
/**
 * @param numbers the numbers to set
 */
public void setNumbers(Integer numbers) {
	this.numbers = numbers;
}
/**
 * @return the username
 */
public String getUsername() {
	return username;
}
/**
 * @param username the username to set
 */
public void setUsername(String username) {
	this.username = username;
}
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}
public Audience(Integer numbers, String username, String name) {
	super();
	this.numbers = numbers;
	this.username = username;
	this.name = name;
}
  
  public Audience() {
	// TODO Auto-generated constructor stub
}
}
