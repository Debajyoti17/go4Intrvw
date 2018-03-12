package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {
	public Employee updateEmp(Long empId, Employee employee);

	public void deleteEmp(Employee emp);

	public List<Employee> findAllEmps();
}
