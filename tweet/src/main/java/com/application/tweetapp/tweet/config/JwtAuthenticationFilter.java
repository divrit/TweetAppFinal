package com.application.tweetapp.tweet.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.application.tweetapp.tweet.helper.JwtUtil;
import com.application.tweetapp.tweet.service.CustomUserDetailService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private JwtUtil jwtutil;
	
	
	@Autowired
	private CustomUserDetailService customUserDetailsServices;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String requestTokenHeader=request.getHeader("Authorization");
		String username=null;
		String jwtToken=null;
		
		
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
			
			
			jwtToken=requestTokenHeader.substring(7);
			
			
			try {
				username=jwtutil.extractUsername(jwtToken);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			UserDetails userdeatils=customUserDetailsServices.loadUserByUsername(username);
			
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userdeatils, null,userdeatils.getAuthorities());
			
			    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
			}else {
				System.out.println("token is not valid");
			}

			
		}
		
		

		filterChain.doFilter(request, response);
		
	}

	
	
	
}
