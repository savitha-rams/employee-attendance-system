package com.savitha.attendance.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.savitha.attendance.entity.Attendance;
import com.savitha.attendance.entity.Employee;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	Optional<Attendance> findByEmployeeAndAttendanceDate(
	        Employee employee,
	        LocalDate attendanceDate);
}
