package com.savitha.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.savitha.attendance.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}