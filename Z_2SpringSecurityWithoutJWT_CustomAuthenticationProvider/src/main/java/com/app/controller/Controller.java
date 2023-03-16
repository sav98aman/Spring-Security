package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.model.Employee;
import com.app.service.EmployeeService;

@RestController
public class Controller {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	
	@GetMapping(value = "/welcome")
	public String welcome() {
		return "Welcome to THis Page";
	}
	@GetMapping(value = "/welcome2")
	public String welcome2() {
		return "Welcome to THis user";
	}
	
	@PostMapping(value = "/employees")
	public ResponseEntity<Employee> registerEmployeeHandaller(@RequestBody Employee employee) throws Exception{
		employee.setPassword(passwordencoder.encode(employee.getPassword()));
		return new ResponseEntity<Employee>(employeeService.employeeRegister(employee),HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/employees/{email}")
	public ResponseEntity<Employee> getEmployeeDetailsHandaller(@PathVariable String email) throws Exception{
		return new ResponseEntity<Employee>(employeeService.getempEmployee(email),HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/employees")
	public ResponseEntity<List<Employee>> getAllEmployeeDetailsHandaller() throws Exception{
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployee(),HttpStatus.CREATED);
	}
	
	
}
