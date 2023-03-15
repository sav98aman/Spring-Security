package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.config.filter.CustomAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityCofig {
	
	private final CustomAuthenticationFilter customAuthneticationFilter;
	
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
		
		return http
			.addFilterAt(customAuthneticationFilter, UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests().anyRequest().authenticated()//call any point tobe authneticate
			.and().build();
		
	}
}
