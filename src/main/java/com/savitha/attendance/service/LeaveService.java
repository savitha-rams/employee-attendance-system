package com.savitha.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savitha.attendance.entity.Employee;
import com.savitha.attendance.entity.Leave;
import com.savitha.attendance.enums.LeaveStatus;
import com.savitha.attendance.exception.InvalidLeaveRequestException;
import com.savitha.attendance.exception.ResourceNotFoundException;
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
		
		leave.setStatus(LeaveStatus.APPLIED);
		
        return leaveRepository.save(leave);
    }

	public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
       }

	public Leave getLeaveById(Long id) {
		return leaveRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave not found with the id : " + id));
	}

	public void deleteLeave(Long id) {
		Leave leave = getLeaveById(id);
		leaveRepository.delete(leave);
		
	}

}
