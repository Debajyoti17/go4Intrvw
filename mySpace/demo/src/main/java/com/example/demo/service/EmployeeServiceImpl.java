/**
 * 
 */
package com.example.demo.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

/**
 * @author dmohanty
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;
	@Override
	public Employee updateEmp(Long empId, Employee empDetails) {
        Employee emp = employeeRepository.getOne(empId);
      /*  emp.setAddress(empDetails.getAddress());
        emp.setAge(empDetails.getAge());
        emp.setDept(empDetails.getDept());
        emp.setName(empDetails.getName());*/
        empDetails.setUpdatedAt(new Timestamp(new Date().getTime()));
		return employeeRepository.saveAndFlush(empDetails);
	}
	@Override
	public void deleteEmp(Employee emp) {
		emp.setStatus(false);
		employeeRepository.save(emp);
	}
	@Override
	public List<Employee> findAllEmps() {
		return employeeRepository.findAllByStatus(true);
	}

}
