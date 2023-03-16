package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Employee;
import com.app.repo.EmployeeDao;

@Service
public class EmployeeServiceimpl implements EmployeeService {
	
	@Autowired
	private  EmployeeDao employeedao;
	
	
	@Override
	public Employee employeeRegister(Employee emp) throws Exception {
		//before Register User Check User Is Alreday Register or NOt
		Optional<Employee> optEmp=employeedao.findByEmail(emp.getEmail());
		if (optEmp.isPresent()) {
			throw new Exception(emp.getEmail() +" this Email is alreday Register !");
		}
		return employeedao.save(emp);
	}


	@Override
	public Employee getempEmployee(String email) throws Exception {
		return employeedao.findByEmail(email).orElseThrow(()-> new Exception(email+ " this mail is Wrong !"));
	}


	@Override
	public List<Employee> getAllEmployee() throws Exception {
		return employeedao.findAll();
	}

}
