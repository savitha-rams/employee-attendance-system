package com.savitha.attendance.dto;

import java.time.LocalDate;

import com.savitha.attendance.enums.LeaveStatus;
import com.savitha.attendance.enums.LeaveType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveDTO {
	
	private Long leaveId;
    
    @NotNull(message = "Start Date is mandatory")
    private LocalDate startDate;
    
    @NotNull(message = "End Date is mandatory")
    private LocalDate endDate;
    
    @NotNull(message = "Leave Type is mandatory")
    private LeaveType leaveType;
    
    @NotBlank(message = "Reason is mandatory")
    private String reason;
    
    @NotNull(message = "Employee ID is mandatory")
    private Long employeeId;
    
    private LeaveStatus status;
}