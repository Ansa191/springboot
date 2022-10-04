package com.examserver.exceptions;

import net.bytebuddy.implementation.bind.annotation.Super;

public class UserNotFoundException extends Exception{
	public UserNotFoundException() {
		super("User not found with this username!");
	}
	public UserNotFoundException(String msg) {
		super(msg);
	}

	

}
