package com.filternews.service;

import javax.validation.ValidationException;

import com.filternews.entity.User;

public interface UserService {

	User registerUser(User user) throws ValidationException;
	
}
