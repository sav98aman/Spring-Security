package com.app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.Repo.EmployeeDao;

@Service
public class MyUserDeatilsService implements UserDetailsService {

	@Autowired
	private EmployeeDao empdao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Employee isEmployee=empdao.findByUsername(username);
		
		if (isEmployee == null) {
			throw new UsernameNotFoundException( " User Is Not Found ");
		}
		
		return (new MyUserDetails(isEmployee));
		
	}

}
