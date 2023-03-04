package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.EmployeeDao;
import com.app.model.Employee;

@RestController
@RequestMapping(value = "/masai/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeDao employeedao;
	@Autowired
	private PasswordEncoder passwordencoder;
	
	
	@PostMapping(value = "/register")
	public ResponseEntity<Employee> RegisterUser(@RequestBody Employee emp){
		emp.setPassword(passwordencoder.encode(emp.getPassword()));//hashed values saved in database 
		Employee newEmployee=employeedao.save(emp);
		return new ResponseEntity<Employee>(newEmployee,HttpStatus.CREATED);
	}
	@GetMapping(value = "/admin")
	public ResponseEntity<String> admin(){
		return new ResponseEntity<String>(" Welcome To masai App for Admin",HttpStatus.ACCEPTED);
	}
}
