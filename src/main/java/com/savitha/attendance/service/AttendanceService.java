package com.savitha.attendance.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.savitha.attendance.entity.Attendance;
import com.savitha.attendance.entity.Employee;
import com.savitha.attendance.enums.AttendanceStatus;
import com.savitha.attendance.exception.CheckInNotFoundException;
import com.savitha.attendance.exception.DuplicateAttendanceException;
import com.savitha.attendance.exception.DuplicateCheckOutException;
import com.savitha.attendance.exception.ResourceNotFoundException;
import com.savitha.attendance.repository.AttendanceRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AttendanceService {

	private final AttendanceRepository attendanceRepository;
	private final EmployeeService employeeService;


	public Attendance checkIn(Attendance attendance) {

		Long employeeId = attendance.getEmployee().getEmployeeId();
		Employee employee = employeeService.getEmployeeById(employeeId);
		attendance.setEmployee(employee);
		LocalDate today = LocalDate.now();
		Optional<Attendance> existingAttendance = attendanceRepository.findByEmployeeAndAttendanceDate(employee, today);
		if(existingAttendance.isPresent())
			throw new DuplicateAttendanceException("Employee has already checked in today");
		attendance.setAttendanceDate(today);
		attendance.setCheckInTime(LocalTime.now());
		attendance.setStatus(AttendanceStatus.PRESENT);
		return attendanceRepository.save(attendance);		
	}


	public Attendance checkOut(Long employeeId) {

		Employee employee = employeeService.getEmployeeById(employeeId);
		LocalDate today = LocalDate.now();
		Attendance attendance =  attendanceRepository.findByEmployeeAndAttendanceDate(employee, today)
				.orElseThrow(() -> new CheckInNotFoundException("Employee has not checked in today"));

		if(attendance.getCheckOutTime() != null)
			throw new DuplicateCheckOutException("Employee has already checked out today");
		attendance.setCheckOutTime(LocalTime.now());

		return attendanceRepository.save(attendance);	
	}


	public List<Attendance> getAllAttendances() {
		return attendanceRepository.findAll();
	}


	public Attendance getAttendanceById(Long id) {
		return attendanceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Attendance not found with the id : " + id));
	}

}






