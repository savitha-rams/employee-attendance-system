package com.savitha.attendance.mapper;

import org.springframework.stereotype.Component;

import com.savitha.attendance.dto.AttendanceDTO;
import com.savitha.attendance.entity.Attendance;
import com.savitha.attendance.entity.Employee;

@Component
public class AttendanceMapper {

	public AttendanceDTO toDTO(Attendance attendance) {
		AttendanceDTO dto = new AttendanceDTO();

        dto.setAttendanceId(attendance.getAttendanceId());
		dto.setEmployeeId(attendance.getEmployee().getEmployeeId());
        dto.setAttendanceDate(attendance.getAttendanceDate());
        dto.setCheckInTime(attendance.getCheckInTime());
        dto.setCheckOutTime(attendance.getCheckOutTime());
        dto.setStatus(attendance.getStatus());

        return dto;
    }
	
	public Attendance toEntity(AttendanceDTO dto) {
		Attendance attendance = new Attendance();
		
        Employee employee = new Employee();
        employee.setEmployeeId(dto.getEmployeeId());
        
        attendance.setEmployee(employee);

        return attendance;
    }
}
