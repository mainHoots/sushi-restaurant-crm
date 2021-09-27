package com.sushiCRM.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncodingTest {
	
	@Test
	public void testEncodePassword() {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = "lasagna2021";
		String encodedPassword = passwordEncoder.encode(rawPassword);
		
		System.out.println(encodedPassword);
		
		/* System.out.println("Hello?  Is this working?"); */
		
		boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
		
		assertThat(matches).isTrue();
		
	}

}
