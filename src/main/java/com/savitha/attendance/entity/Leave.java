package com.savitha.attendance.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "leaves")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
	
	public enum LeaveType {

	    ANNUAL,
	    SICK,
	    CASUAL

	}
	
	public enum LeaveStatus {

	    APPLIED,
	    APPROVED,
	    REJECTED,
	    CANCELLED

	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;

    @NotNull(message = "Start Date is mandatory")
    private LocalDate startDate;
    
    @NotNull(message = "End Date is mandatory")
    private LocalDate endDate;
    
    @NotBlank(message = "Leave Type is mandatory")
  //  @Enumerated(EnumType.STRING)
    private String leaveType;
    
    @NotBlank(message = "Reason is mandatory")
    private String reason;
    
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    //@NotNull(message = "Employee is mandatory")
    private Employee employee;
        
}