package com.application.tweetapp.tweet.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.application.tweetapp.tweet.document.User;
import com.application.tweetapp.tweet.model.CustomUserDetails;
import com.application.tweetapp.tweet.repository.DUserRepository;
import com.application.tweetapp.tweet.repository.UserRepository;


@Service
public class CustomUserDetailService  implements UserDetailsService{

	@Autowired
	private DUserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		if(username.equals("Divrit")) {
//			
//			return new User("Divrit", "Divrit123", new ArrayList<>());
//		}
//		else {
//			
//			throw new UsernameNotFoundException("Please enter a valid user");
//		}
		
		
		
		
		User user = userRepo.searchUserByLogin(username);
		
//		System.out.println(username);
//		System.out.println(user);
		
		if(user==null) {
			throw new UsernameNotFoundException("User not found");
		}
		else {
			return new CustomUserDetails(user);
		}
		
		
		
		
		
		
		
	}
	

}
