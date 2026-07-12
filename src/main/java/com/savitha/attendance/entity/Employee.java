package com.savitha.attendance.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.savitha.attendance.enums.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;    
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	private String department;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
}