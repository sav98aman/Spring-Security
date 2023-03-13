package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.model.Employee;
import com.app.service.EmployeeService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private EmployeeService employeeservice;
	
	@GetMapping
	public String userWelcome() {
		return "Welcome to user ";
		
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<Employee> userRegister(@RequestBody Employee employee) throws Exception {
		return new ResponseEntity<Employee>(employeeservice.registerUser(employee),HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/update/{email}/{userName}")
	public ResponseEntity<Employee> updateUser(@PathVariable String email,String  userName) throws Exception {
		return new ResponseEntity<Employee>(employeeservice.updateUserName(email, userName),HttpStatus.ACCEPTED);
	}}
