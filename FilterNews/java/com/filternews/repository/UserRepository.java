package com.filternews.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filternews.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmailOrPhoneNo(String email, String phoneNo);

	User findByPhoneNo(String phoneNo);

	User findByEmail(String email);

}
