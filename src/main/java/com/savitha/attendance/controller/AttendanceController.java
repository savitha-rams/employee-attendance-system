package com.savitha.attendance.controller;

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

import com.savitha.attendance.entity.Attendance;
import com.savitha.attendance.entity.Leave;
import com.savitha.attendance.service.AttendanceService;

import jakarta.validation.Valid;

@RequestMapping("/attendance")
@RestController
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@PostMapping("/check-in")
	public ResponseEntity<Attendance> checkIn(@Valid @RequestBody Attendance attendance) {

		Attendance savedAttendance = attendanceService.checkIn(attendance);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedAttendance);
	}

	@PostMapping("/check-out/{employeeId}")
	public ResponseEntity<Attendance> checkOut(@PathVariable Long employeeId) {

		Attendance updateedAttendance = attendanceService.checkOut(employeeId);
		return ResponseEntity.ok(updateedAttendance);
	}

	@GetMapping
	public ResponseEntity<List<Attendance>> getAllAttendances() {
		return ResponseEntity.ok(attendanceService.getAllAttendances());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Attendance> getAttendanceById(@PathVariable Long id) {
		return ResponseEntity.ok(attendanceService.getAttendanceById(id));
	}

}







