package com.savitha.attendance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.savitha.attendance.entity.Employee;
import com.savitha.attendance.exception.ResourceNotFoundException;
import com.savitha.attendance.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
    	employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException("Employee not found with id: " + id));
    }

    public Employee updateEmployee(Long id, Employee employee) {

    	Employee existingEmployee = getEmployeeById(id);
    	existingEmployee.setFirstName(employee.getFirstName());
    	existingEmployee.setLastName(employee.getLastName());
    	existingEmployee.setDepartment(employee.getDepartment());
    	existingEmployee.setEmail(employee.getEmail());
    	if (employee.getPassword() != null
    			&& !employee.getPassword().isBlank()) {
    		existingEmployee.setPassword(passwordEncoder.encode(employee.getPassword()));
    	}
    	existingEmployee.setRole(employee.getRole());
    	return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
    	Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}