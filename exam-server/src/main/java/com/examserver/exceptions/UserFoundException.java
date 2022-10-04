package com.examserver.exceptions;

public class UserFoundException extends Exception {
  public UserFoundException() {
	  super("User Already Found, Try different Username!");
  }
  public UserFoundException(String msg) {
	  super(msg);
  }
}
