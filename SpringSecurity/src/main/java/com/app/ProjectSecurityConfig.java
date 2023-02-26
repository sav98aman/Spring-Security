package com.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.*;

@Configuration
public class ProjectSecurityConfig {
	
		@Bean
		public SecurityFilterChain masaiSecurityConfig(HttpSecurity http) throws Exception {
			http.authorizeHttpRequests( (auth)->auth
					.antMatchers("/masai/welcomeP").authenticated()//asking Password for This Api// Not Use Is RealTime Production 
					.antMatchers("/masai/welcome").permitAll()
			).httpBasic();

			return http.build();
		}
		// creating admin login and Password By Deafults Login
		@Bean
		public InMemoryUserDetailsManager userDetails() {
			InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
			UserDetails admin = User.withUsername("admin").password("12345").authorities("admin").build();
		    UserDetails user = User.withUsername("user").password("12345").authorities("read").build();
		    userDetailsService.createUser(admin);
		    userDetailsService.createUser(user);
		    return userDetailsService;
		}
//		 @Bean
//		 public PasswordEncoder passwordEncoder() {
//		        return NoOpPasswordEncoder.getInstance(); //Not recommended
//		}
		
}
