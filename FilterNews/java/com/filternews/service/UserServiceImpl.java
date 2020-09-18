package com.filternews.service;

import javax.validation.ValidationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filternews.entity.User;
import com.filternews.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	private static Logger logger = LogManager.getLogger();
	
	@Override
	public User registerUser(User user) throws ValidationException {
		logger.info("Finding user by email or phone no....");
		User user1 = null;
		
		if(user.getPhoneNo()!= null) {
			logger.info("Both are not null");
			user1 = userRepository.findByEmailOrPhoneNo(user.getEmail(),user.getPhoneNo());
		}
		else { 
			logger.info("phoneNo is null.... finding by email");
			user1 = userRepository.findByEmail(user.getEmail());
		}
		
		if(user1!=null) {
		
		logger.info(user1.toString());	
			
		logger.error("User with Mobile Number/Email Id is Already Registered");
		throw new ValidationException("User with Mobile Number/Email Id is Already Registered");
		}
		logger.info("Saving new user in db...");
		return userRepository.saveAndFlush(user);
	
	}

}
