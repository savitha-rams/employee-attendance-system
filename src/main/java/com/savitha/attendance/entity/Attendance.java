package com.savitha.attendance.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.savitha.attendance.enums.AttendanceStatus;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "attendance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long attendanceId;
		
		private LocalDate attendanceDate;
		
		@Column(columnDefinition = "TIME")
		private LocalTime checkInTime;
		@Column(columnDefinition = "TIME")
		private LocalTime checkOutTime;
		
		@Enumerated(EnumType.STRING)
		private AttendanceStatus status;
		
		@NotNull(message = "Employee is mandatory")
		@ManyToOne
		@JoinColumn(name = "employee_id")
		private Employee employee;

}

