package com.savitha.attendance.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savitha.attendance.dto.AttendanceDTO;
import com.savitha.attendance.entity.Attendance;
import com.savitha.attendance.mapper.AttendanceMapper;
import com.savitha.attendance.service.AttendanceService;

import jakarta.validation.Valid;

@RequestMapping("/attendance")
@RestController
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private AttendanceMapper attendanceMapper;

	@PostMapping("/check-in")
	public ResponseEntity<AttendanceDTO> checkIn(@Valid @RequestBody AttendanceDTO attendanceDto) {
		
		Attendance attendance = attendanceMapper.toEntity(attendanceDto);
		Attendance savedAttendance = attendanceService.checkIn(attendance);
		AttendanceDTO savedAttendanceDto = attendanceMapper.toDTO(savedAttendance);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedAttendanceDto);
	}

	@PostMapping("/check-out/{employeeId}")
	public ResponseEntity<AttendanceDTO> checkOut(@PathVariable Long employeeId) {

		Attendance updateedAttendance = attendanceService.checkOut(employeeId);
		AttendanceDTO updateedAttendanceDto = attendanceMapper.toDTO(updateedAttendance);
		
		return ResponseEntity.ok(updateedAttendanceDto);
	}

	@GetMapping
	public ResponseEntity<List<AttendanceDTO>> getAllAttendances() {
		List<Attendance> attendances = attendanceService.getAllAttendances();
		List<AttendanceDTO> attendanceDtos = new ArrayList<AttendanceDTO>();
		attendances.forEach(attendance -> attendanceDtos.add(attendanceMapper.toDTO(attendance)));
		return ResponseEntity.ok(attendanceDtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AttendanceDTO> getAttendanceById(@PathVariable Long id) {
		Attendance attendance = attendanceService.getAttendanceById(id);
		AttendanceDTO attendanceDto = attendanceMapper.toDTO(attendance); 
		return ResponseEntity.ok(attendanceDto);
	}

}







