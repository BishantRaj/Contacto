package com.sts.exception;

public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(Long id) {
		super("Could Not Found The User With Given ID");
	}
}
