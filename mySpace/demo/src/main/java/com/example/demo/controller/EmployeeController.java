package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	EmployeeService employeeService;
    
	@GetMapping("/emps/pages")
    public ResponseEntity<Map<String, Object>> getAllPages() {
         @SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest(5, 2);
         Page<Employee> page =  employeeRepository.findAll(pageable);
         
         Map<String, Object> response = new HashMap<String, Object>();
         response.put("pageData", page.getContent());
         response.put("pageElemet", page.getTotalElements());
         
         return new ResponseEntity(response, HttpStatus.OK);
    }
	
    @GetMapping("/emps")
    public List<Employee> getAllEmps() {
        return employeeService.findAllEmps();
    }
    
    @PostMapping("/emps")
    public @Valid Employee addEmp(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/emps/{id}")
    public Object getEmpById(@PathVariable(value = "id") Long empId) {
        return employeeRepository.findById(empId);
    }

    @PutMapping("/emps/{id}")
    public Employee updateEmp(@PathVariable(value = "id") Long empId,
                                            @RequestBody Employee empDetails) {
        return employeeService.updateEmp(empId, empDetails);
    }

    @DeleteMapping("/emps/{id}")
    public ResponseEntity<?> deleteEmp(@PathVariable(value = "id") Long empId) {
        Employee emp = employeeRepository.getOne(empId);
        employeeService.deleteEmp(emp);

        return ResponseEntity.ok().build();
    }
}
