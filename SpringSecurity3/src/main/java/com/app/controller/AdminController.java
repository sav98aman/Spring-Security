package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Employee;
import com.app.service.EmployeeService;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	private EmployeeService employeeservice;
	
	@GetMapping
	public String adminWelcome() {
		return "Welcome to admin ";
		
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<Employee> adminRegister(@RequestBody Employee employee) throws Exception {
		
		return new ResponseEntity<Employee>(employeeservice.registerAdmin(employee),HttpStatus.CREATED);
	}
	
}
