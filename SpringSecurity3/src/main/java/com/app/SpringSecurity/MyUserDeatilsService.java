package com.app.SpringSecurity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.app.model.Employee;
import com.app.model.MyUserDeatils;
import com.app.repo.EmployeeDao;

public class MyUserDeatilsService implements UserDetailsService{

	@Autowired
	private Employee employee;
	@Autowired
	private EmployeeDao employeeDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//find EMployee
		Optional<Employee> optEmployee= employeeDao.findByEmail(username);
		if(optEmployee.isEmpty()) {
			throw new UsernameNotFoundException(username +" This User Is Not Persent ");
		}
		return (new MyUserDeatils(optEmployee.get()));
	}

}
