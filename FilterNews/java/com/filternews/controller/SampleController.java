package com.filternews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filternews.dto.Response;
import com.filternews.entity.User;
import com.filternews.repository.UserRepository;

@RestController
@RequestMapping("/sample")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SampleController {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<Response<User>> getUserData(@PathVariable("id") Integer id) {
		
		Response<User> response = null;
		
		if(id == null) {
			response  = new Response<>(2001, "Request Body is empty", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		 
		User user = userRepository.findById(id).orElse(null);
		
		if(user == null) {
			
			response = new Response<>(2001, "user with this id doesn't exist", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		response = new Response<>(2000, "User Found", null);
		response.setData(user);
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping("/saveUser")
	public ResponseEntity<Response<String>> saveUser(@RequestBody User user, BindingResult error) {
		
		Response<String> response = null;
		
		if(error.hasErrors()) {
			response  = new Response<>(2001, "Request Body is not valid", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		 
		if(StringUtils.isEmpty(user.getFirstName())) {
			response  = new Response<>(2002, "Request Body is not valid", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		User createdUser = userRepository.saveAndFlush(user);
		
		response = new Response<>(2000, "User Successfully added!", null);
		response.setData("Created with id: "+createdUser.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	
}
