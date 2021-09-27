package com.sushiCRM.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		return new SushiUserDetailsService();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
		
	}
	
	//overrides required authentication
	
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http.authorizeRequests().anyRequest().permitAll();
	 * 
	 * }
	 */
	 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(authenticationProvider());
		
	}

	
	  @Override protected void configure(HttpSecurity http) throws Exception {
	  
		  http.authorizeRequests().antMatchers("/users/**").hasAuthority("Admin").antMatchers("/categories/**").hasAnyAuthority("Admin", "Assistant").antMatchers("/menu/**").hasAnyAuthority("Admin", "Assistant").anyRequest().authenticated().and().formLogin().
		  loginPage("/login").usernameParameter("email").permitAll().and().logout().permitAll().
		  and().rememberMe().key("Lasagna").tokenValiditySeconds(7 * 24 * 60 * 60);
	  
	 }
	 

	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
		
	}
	
	

}