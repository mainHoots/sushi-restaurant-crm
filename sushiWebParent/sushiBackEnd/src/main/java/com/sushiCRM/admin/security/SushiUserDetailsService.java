package com.sushiCRM.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sushiCRM.admin.user.UserRepository;
import com.sushiCRM.common.entity.User;

public class SushiUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepo.getUserByEmail(email);
		
		if (user != null) {
			
			return new SushiUserDetails(user);
			
		}
		
		throw new UsernameNotFoundException("Could not find user with email: " + email);
		
	}

}
