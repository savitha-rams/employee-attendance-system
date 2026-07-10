package com.savitha.attendance.mapper;

import org.springframework.stereotype.Component;

import com.savitha.attendance.dto.EmployeeDTO;
import com.savitha.attendance.entity.Employee;

@Component
public class EmployeeMapper {

	public EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();

        dto.setEmployeeId(employee.getEmployeeId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setPassword(employee.getPassword());
        dto.setRole(employee.getRole());

        return dto;
    }
	
	public Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();

        employee.setEmployeeId(dto.getEmployeeId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setRole(dto.getRole());
        employee.setPassword(dto.getPassword());

        return employee;
    }
}
