package com.savitha.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.savitha.attendance.service.EmployeeService;

import jakarta.validation.Valid;


@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

//	@GetMapping
  /**  public String getEmployees() {
        return "Employee API Working";
    }*/
    
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    
    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }
    
    @PostMapping("/employees")
    public Employee saveEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
    
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        Employee updatedEmployee =  employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(updatedEmployee);
    }
    
    @DeleteMapping("/employees/{id}")
    public void deleteEmployeeById(@PathVariable Long id) {
    	employeeService.deleteEmployee(id);
    }
}