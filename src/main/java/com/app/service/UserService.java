package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.User;
import com.app.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public User saveUser(User user) {
		return repo.save(user);
	}
	
	public boolean validUser(String email, String password) {
		User user = repo.findByEmail(email);
		if(user!=null && user.getPassword().equals(password)) {
			return true;
		}else 
			return false;
	}
	
	public boolean isPhoneNumberAlreadyExists(String phoneNumber) {
		User user=repo.findByPhoneNumber(phoneNumber);
		if(user!=null) {
			return true;
		}else {
			return false;
		}
	}
}
