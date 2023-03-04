package com.app.springSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurity {

    @Bean
    public SecurityFilterChain masaiSecurityConfig(HttpSecurity http) throws Exception {
    	http.authorizeHttpRequests( (auth)->auth
				.antMatchers("/masai/employee/","/masai/welcomeP").authenticated()
				.antMatchers("/masai/employee/admin").hasRole("ADMIN")
				.antMatchers("/masai/employee/register","/masai/welcome").permitAll()
				
		).csrf().disable()
		.httpBasic();
    	return http.build();
    }
	
	@Bean
	 public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	 }
	
}
