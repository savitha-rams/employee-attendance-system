package com.savitha.attendance.mapper;

import org.springframework.stereotype.Component;

import com.savitha.attendance.dto.LeaveDTO;
import com.savitha.attendance.entity.Employee;
import com.savitha.attendance.entity.Leave;

@Component
public class LeaveMapper {

	public LeaveDTO toDTO(Leave leave) {
        LeaveDTO dto = new LeaveDTO();
        
        dto.setLeaveId(leave.getLeaveId());
        dto.setEmployeeId(leave.getEmployee().getEmployeeId());
        dto.setLeaveType(leave.getLeaveType());
        dto.setStartDate(leave.getStartDate());
        dto.setEndDate(leave.getEndDate());
        dto.setReason(leave.getReason());
        dto.setStatus(leave.getStatus());
        return dto;
    }
	
	public Leave toEntity(LeaveDTO dto) {
        Leave leave = new Leave();
        
        Employee employee = new Employee();
        employee.setEmployeeId(dto.getEmployeeId());
        
        leave.setEmployee(employee);        
        leave.setLeaveId(dto.getLeaveId());
        leave.setLeaveType(dto.getLeaveType());
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setReason(dto.getReason());
        return leave;
    }
}
