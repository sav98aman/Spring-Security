package com.app.springSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.app.dao.EmployeeDao;
import com.app.model.Employee;
import com.app.model.MyUserDeatils;


public class MyUserDeatilsService implements UserDetailsService{

	@Autowired
	private EmployeeDao employeedao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Employee IsEmployee=	employeedao.findByUserName(username).get();
		if (IsEmployee == null) {
			throw new UsernameNotFoundException(" User Is Exist Please Check User ");
		}
		return new MyUserDeatils(IsEmployee);
		
		
	}

}
