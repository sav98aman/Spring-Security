package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.app.model.Employee;
import com.app.repo.EmployeeDao;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {

	@Autowired
	private EmployeeDao employeedao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Employee> optEmp=employeedao.findByEmail(username);
		if(optEmp.isPresent()) {
			Employee emp=optEmp.get();
			
			List<GrantedAuthority> authorites=new ArrayList<>();
			authorites.add(new SimpleGrantedAuthority(emp.getRole()));
	
			return new User(emp.getEmail(), emp.getPassword(), authorites);
			
			
		}else {
			throw new UsernameNotFoundException(username+" this USer Name is Not Found ");
		}
	}

}
