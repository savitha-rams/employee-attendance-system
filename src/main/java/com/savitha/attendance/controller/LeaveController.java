package com.savitha.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savitha.attendance.entity.Leave;
import com.savitha.attendance.service.LeaveService;

import jakarta.validation.Valid;

@RequestMapping("/leaves")
@RestController
public class LeaveController {

	@Autowired
	private LeaveService leaveService;

	@PostMapping
	public ResponseEntity<Leave> applyLeave(@Valid @RequestBody Leave leave) {
		Leave savedLeave =  leaveService.applyLeave(leave);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedLeave);
	}

	@GetMapping
	public ResponseEntity<List<Leave>> getAllLeaves() {
		return ResponseEntity.ok(leaveService.getAllLeaves());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Leave> getLeaveById(@PathVariable Long id) {
		return ResponseEntity.ok(leaveService.getLeaveById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLeaveById(@PathVariable Long id) {
		leaveService.deleteLeave(id);
		return ResponseEntity.noContent().build();
	}
}
