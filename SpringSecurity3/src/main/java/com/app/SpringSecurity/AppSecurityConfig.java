package com.app.SpringSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfig  {

	@Bean
	public SecurityFilterChain appSecurityCongf(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests( (auth)->auth
				.antMatchers("/admin","/user").authenticated()
				.antMatchers("/user/update/{email}/{userName}").hasRole("USER")
				.antMatchers("/user/register","/admin/register").permitAll()
				).csrf().disable()
					.httpBasic();
			return http.build();
	}
	@Bean
	 public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	 }
}
