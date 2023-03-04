package com.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.app.dao.EmployeeDao;
import com.app.model.Employee;



@Component
public class GetCurrentUser {
	
	@Autowired
	private EmployeeDao employeedao;
	
	public Employee getloggedUserDeatils() {
		SecurityContext sc=SecurityContextHolder.getContext();
		
		Authentication auth=sc.getAuthentication();
		
		String userName=auth.getName();
		
		return employeedao.findByUserName(userName).get();
		
		
	}
}
