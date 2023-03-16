package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Employee;
import com.app.repo.EmployeeDao;

@RestController
public class LoginController {

	@Autowired
	private EmployeeDao employeedao;
	@GetMapping(value = "/signIn")
	public ResponseEntity<Employee> getloggedEmployee(Authentication auth){
		System.out.println(auth);
		Employee emp=employeedao.findByEmail(auth.getName()).orElseThrow(()-> new BadCredentialsException("Invalid Username or password " ));
		return new ResponseEntity<Employee>(emp,HttpStatus.ACCEPTED);
	}
}
