package com.savitha.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savitha.attendance.entity.Employee;
import com.savitha.attendance.repository.EmployeeRepository;


@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

//	@GetMapping
  /**  public String getEmployees() {
        return "Employee API Working";
    }*/
    
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElse(new Employee());
    }
    
    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
    
    @PutMapping("/employees/{id}")
    public Employee updateEmployeeById(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
    
    @DeleteMapping("/employees/{id}")
    public void deleteProductById(@PathVariable Long id) {
    	employeeRepository.deleteById(id);
    }
}