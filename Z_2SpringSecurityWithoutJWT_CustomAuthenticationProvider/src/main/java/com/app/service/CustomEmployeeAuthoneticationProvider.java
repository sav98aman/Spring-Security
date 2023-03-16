package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.model.Employee;
import com.app.repo.EmployeeDao;

@Service
public class CustomEmployeeAuthoneticationProvider implements AuthenticationProvider {

	@Autowired
	private EmployeeDao employeedao;
	
	@Autowired
	private PasswordEncoder passwordencoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username=authentication.getName();
		String password=authentication.getCredentials().toString();
		System.err.println(username+ " and"+password);
		
		Optional<Employee> opt=employeedao.findByEmail(username);
		if(opt.isEmpty()) {
			throw new BadCredentialsException( username+" this email id Wrong ");
		}
		Employee employee=opt.get();
		if(passwordencoder.matches(password, employee.getPassword())) {
			System.out.println("login succesfully");
			List<GrantedAuthority> authorities=new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(employee.getRole()));
			return new UsernamePasswordAuthenticationToken(username,password, authorities);
		}else {
			throw new BadCredentialsException("Invalid Password");
		}
			
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	

}
