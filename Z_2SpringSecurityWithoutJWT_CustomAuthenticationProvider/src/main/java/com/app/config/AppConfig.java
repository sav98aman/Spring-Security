package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig {
	
	@Bean
	public SecurityFilterChain appSecurityfilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
			.requestMatchers(HttpMethod.POST, "/employees").permitAll()
			.requestMatchers(HttpMethod.GET,"/welcome").permitAll()
			.requestMatchers(HttpMethod.GET, "/employees").hasRole("ADMIN")
//			.requestMatchers("/employees").anonymous()
			.anyRequest()
			.authenticated()
			.and()
			.csrf().disable()
			.formLogin()
			.and()
			.httpBasic();
			
		return http.build();
		
	}
	
	@Bean
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
}
