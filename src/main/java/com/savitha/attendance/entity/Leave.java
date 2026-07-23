package com.savitha.attendance.entity;

import java.time.LocalDate;

import com.savitha.attendance.enums.LeaveStatus;
import com.savitha.attendance.enums.LeaveType;

import jakarta.persistence.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;
    private String reason;  
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
        
}