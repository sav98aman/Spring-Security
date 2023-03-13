package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import lombok.var;


@Configuration
public class WebSecurityConfig {
	
	
	@Bean
	public UserDetailsService userdetailsService() {
		
		var uds=new InMemoryUserDetailsManager();
			
		var u1 =User.withUsername("USER")
				.password("12345")
				.authorities("read")
				.build();
		uds.createUser(u1);
		
		return uds;
		
	}
	//not User Real Production appl
	@Bean
	public PasswordEncoder passwordencoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
