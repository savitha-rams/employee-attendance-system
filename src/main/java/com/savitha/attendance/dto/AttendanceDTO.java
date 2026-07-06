package com.savitha.attendance.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.savitha.attendance.enums.AttendanceStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO {
	
		private Long attendanceId;
		private LocalDate attendanceDate;		
		private LocalTime checkInTime;
		private LocalTime checkOutTime;
		private AttendanceStatus status;		
		@NotNull(message = "Employee ID is mandatory")
		private Long employeeId;
}

