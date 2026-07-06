package com.savitha.attendance.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.savitha.attendance.dto.EmployeeDTO;
import com.savitha.attendance.entity.Employee;
import com.savitha.attendance.mapper.EmployeeMapper;
import com.savitha.attendance.service.EmployeeService;

import jakarta.validation.Valid;

@RequestMapping("/employees")
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeMapper employeeMapper;

	//	@GetMapping
	/**  public String getEmployees() {
        return "Employee API Working";
    }*/

	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		List<EmployeeDTO> employeeDtos = new ArrayList<EmployeeDTO>();
		employees.forEach(employee -> employeeDtos.add(employeeMapper.toDTO(employee)));
		
		return ResponseEntity.ok(employeeDtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
		
		Employee employee = employeeService.getEmployeeById(id);
		EmployeeDTO employeeDto = employeeMapper.toDTO(employee);
		return ResponseEntity.ok(employeeDto);
	}

	@PostMapping
	public ResponseEntity<EmployeeDTO> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDto) {
		Employee employee = employeeMapper.toEntity(employeeDto);
		Employee savedEmployee = employeeService.saveEmployee(employee);
		EmployeeDTO savedEmployeeDto = employeeMapper.toDTO(savedEmployee);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployeeDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDto) {
		Employee employee = employeeMapper.toEntity(employeeDto);
		Employee updatedEmployee =  employeeService.updateEmployee(id, employee);
		EmployeeDTO updatedEmployeeDto = employeeMapper.toDTO(updatedEmployee);
		return ResponseEntity.ok(updatedEmployeeDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
}