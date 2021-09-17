package com.application.tweetapp.tweet.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.tweetapp.tweet.helper.JwtUtil;
import com.application.tweetapp.tweet.model.Divrit;
import com.application.tweetapp.tweet.model.JwtRequest;
import com.application.tweetapp.tweet.model.JwtResponse;
import com.application.tweetapp.tweet.service.CustomUserDetailService;

import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@CrossOrigin(origins = "*")
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenicationManager;

	@Autowired
	private CustomUserDetailService customUserdetailservice;
	
	@Autowired
	private JwtUtil jwtutil;
	
	
	
	
	@PostMapping(path = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value="Gets all the users", response=JwtResponse.class)
	public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		System.out.println(jwtRequest);
		try {
			
			authenicationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
			
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
//			throw new Exception("Bad crdentials");
		}
		
		
		//after no exception area
		
		UserDetails userDetails = customUserdetailservice.loadUserByUsername(jwtRequest.getUsername());
		
		final String Token=jwtutil.generateToken(userDetails);
		
		System.out.println("JWT" + Token);
		
		JwtResponse js= new JwtResponse(Token);
		System.out.println(js);
		
		return new ResponseEntity(js, HttpStatus.OK);	
	}
	
	
	@RequestMapping(value = "/fire",  method = RequestMethod.GET,  headers="Accept=*/*")
	public ResponseEntity<?> get() {
		
		System.out.println("I am here");
		return ResponseEntity.ok(new Divrit("STRING"));
	}
	
	
}
