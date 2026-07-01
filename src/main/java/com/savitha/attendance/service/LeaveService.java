package com.savitha.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savitha.attendance.entity.Employee;
import com.savitha.attendance.entity.Leave;
import com.savitha.attendance.exception.InvalidLeaveRequestException;
import com.savitha.attendance.repository.LeaveRepository;

@Service
public class LeaveService {
	
	@Autowired
	private LeaveRepository leaveRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	public Leave applyLeave(Leave leave) {
		
		if (leave.getEndDate().isBefore(leave.getStartDate())) {
		    throw new InvalidLeaveRequestException("End date cannot be before start date");
		}
		
		Long employeeId = leave.getEmployee().getEmployeeId();

	    Employee employee = employeeService.getEmployeeById(employeeId);

	    leave.setEmployee(employee);
		
		leave.setStatus("APPLIED");
		
        return leaveRepository.save(leave);
    }

}
