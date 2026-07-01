package com.savitha.attendance.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "leaves")
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

	public Long getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
        
}