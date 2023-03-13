package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Employee;
import com.app.repo.EmployeeDao;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeedao;
	
	
	@Override
	public Employee registerAdmin(Employee employee) throws Exception {
		
		//check User Is Already Register or Not By User Email Id
		Optional<Employee> empOpt=employeedao.findByEmail(employee.getEmail());
		if(empOpt.isPresent()) {
			throw new Exception(employee.getEmail() +" This Email IS All reday Register With this ROle "+empOpt.get().getRole());
		}
		return employeedao.save(employee);
	}
	//User Register
	@Override
	public Employee registerUser(Employee employee) throws Exception {
		
		//check User Is Already Register or Not By User Email Id
		Optional<Employee> empOpt=employeedao.findByEmail(employee.getEmail());
		if(empOpt.isPresent()) {
			throw new Exception(employee.getEmail() +" This Email IS All reday Register With this ROle "+empOpt.get().getRole());
		}
		return employeedao.save(employee);
	}
	@Override
	public Employee updateUserName(String email,String userName) throws Exception {
		// TODO Auto-generated method stub
		//first check User Is Login 
		
		// find Vaild User
		Optional<Employee> optEmployee=employeedao.findByEmail(email);
		if(optEmployee.isEmpty()) {
			throw new Exception( email+"  Email id Is Not vaild ");
		}
		Employee emp=optEmployee.get();
		emp.setEmployeeName(userName);
		return employeedao.save(emp);
		
	}

}
