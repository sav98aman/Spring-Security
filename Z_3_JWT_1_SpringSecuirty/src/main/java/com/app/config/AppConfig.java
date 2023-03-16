package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.app.config.jwt.JwtTokenGeneratorFilter;
import com.app.config.jwt.JwtTokenValidatorFilter;

@Configuration
public class AppConfig {
	
	@Bean
	public SecurityFilterChain appSecurityfilterChain(HttpSecurity http) throws Exception {
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeHttpRequests()
			.requestMatchers(HttpMethod.POST, "/employees").permitAll()
			.requestMatchers(HttpMethod.GET,"/welcome").permitAll()
			.requestMatchers(HttpMethod.GET, "/employees").hasRole("ADMIN")
			.anyRequest().authenticated().and()
			.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
			.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
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
