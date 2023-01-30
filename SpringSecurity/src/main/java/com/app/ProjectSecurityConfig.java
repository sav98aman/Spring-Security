package com.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
	@Bean
	public SecurityFilterChain masaiSecurityConfig(HttpSecurity http) throws Exception {
			http.authorizeHttpRequests((auth)->auth
				.anyRequest().permitAll()// give to permit all Without any Security or lock all user acces 
			).httpBasic();
			return http.build();
			
	  }

	 
}
