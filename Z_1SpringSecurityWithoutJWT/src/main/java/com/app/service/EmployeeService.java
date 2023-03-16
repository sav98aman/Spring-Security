package com.app.service;

import java.util.List;

import com.app.model.Employee;

public interface EmployeeService {
	
	public Employee employeeRegister(Employee emp) throws Exception;
	
	public Employee getempEmployee(String email)throws Exception;
	
	// admin  get all EMployee Deatials
		
	public List<Employee> getAllEmployee()throws Exception;
	
	
}
