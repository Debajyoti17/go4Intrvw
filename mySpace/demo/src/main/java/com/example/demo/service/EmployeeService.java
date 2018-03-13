package com.example.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;

import com.example.demo.model.Employee;

public interface EmployeeService {
	@Modifying(clearAutomatically=true)
	public Employee updateEmp(Long empId, Employee employee);

	public void deleteEmp(Employee emp);

	public List<Employee> findAllEmps();
}
