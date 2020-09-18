package com.filternews.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.filternews.dto.Response;
import com.filternews.entity.News;
import com.filternews.entity.User;
import com.filternews.service.UserServiceImpl;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@PostMapping("/register")
	public ResponseEntity<Integer> registerNewUser(@RequestBody User user,BindingResult errors){
		Response<Integer> response = null;
		logger.info("Inside registerNewUser()");
		if(errors.hasErrors()) {			
			logger.error("Invalid request!..caught by Binding Result");
			response =new Response<Integer>( 2001,"Request body is not valid with reason",null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		if(StringUtils.isEmpty(user.getFirstName()) || StringUtils.isEmpty(user.getPassword())|| 
				StringUtils.isEmpty(user.getLastName()) || (StringUtils.isEmpty(user.getEmail()))) {
//			if(StringUtils.isEmpty(user.getEmail())) logger.info("email is empty");
//			if(StringUtils.isEmpty(user.getFirstName())) logger.info("firstName is empty");
//			if(StringUtils.isEmpty(user.getLastName())) logger.info("lastName is empty");
//			if(StringUtils.isEmpty(user.getPassword())) logger.info("password is empty");
//			if(StringUtils.isEmpty(user.getEmail()) && StringUtils.isEmpty(user.getPhoneNo())) logger.info("both are empty");
			
			logger.error("Invalid request");
			response =new Response<Integer>(2002,"Request body is not valid",null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		User createdUser;
		try {
			createdUser = userServiceImpl.registerUser(user);
			
			if(createdUser == null) {
				logger.error("Couldn't register new user");
				response =new Response<Integer>(2003,"Request body is not valid",null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			logger.info("User successfully registered!");
			response =new Response<Integer>(2000,"Customer successfully registered",null);
			response.setData(createdUser.getId());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(createdUser.getId());
			} catch (Exception e) {
				logger.catching(e);
				response =new Response<>(2003,e.getMessage(),null);
				return ResponseEntity.badRequest().body(null);
			}
		}
	/*
		@PostMapping("/addNews/{userId}")
		public ResponseEntity<Response<News>> addNews(@Valid @RequestBody News newNews, BindingResult errors, @RequestParam(name = "userId") Integer userId){
			
			Response<News> response = null;
			
			if(errors.hasErrors()) {
				response =new Response<>(2001,"Request body is not valid",null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
			
			if(userId == null) {
				response = new Response<>(2001, "Invalid Parameters", null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			} 
			
			if(StringUtils.isEmpty(newNews.getHeadLine()) || StringUtils.isEmpty(newNews.get))
			
			
			return null;
		}
	*/
	
	//@PutMapping("/login")
	//public ResponseEntity<Boolean> loginUser(@RequestParam(name = "username") Strng )
	
	
}
