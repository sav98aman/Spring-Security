package com.app.service;

import com.app.model.Employee;

public interface EmployeeService {
	//admin Register
	public Employee registerAdmin(Employee employee)throws Exception;
	// user Register
	public Employee registerUser(Employee employee) throws Exception;
	
	//updateUserName
	
	public Employee updateUserName(String enail,String userName) throws Exception;
}
