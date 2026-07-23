package com.savitha.attendance.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savitha.attendance.dto.LeaveDTO;
import com.savitha.attendance.entity.Leave;
import com.savitha.attendance.mapper.LeaveMapper;
import com.savitha.attendance.service.LeaveService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/leaves")
@RestController
public class LeaveController {

	private final LeaveService leaveService;
	private final LeaveMapper leaveMapper;

	@PostMapping
	public ResponseEntity<LeaveDTO> applyLeave(@Valid @RequestBody LeaveDTO leaveDto) {
		Leave leave = leaveMapper.toEntity(leaveDto);
		Leave savedLeave =  leaveService.applyLeave(leave);
		LeaveDTO savedLeaveDto = leaveMapper.toDTO(savedLeave);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedLeaveDto);
	}

	@GetMapping
	public ResponseEntity<List<LeaveDTO>> getAllLeaves() {
		List<Leave> leaves = leaveService.getAllLeaves();
		List<LeaveDTO> leavesDto =
				leaves.stream()
				.map(leaveMapper::toDTO)
				.toList();
		return ResponseEntity.ok(leavesDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<LeaveDTO> getLeaveById(@PathVariable Long id) {
		Leave leave = leaveService.getLeaveById(id);
		LeaveDTO leaveDto = leaveMapper.toDTO(leave);
		return ResponseEntity.ok(leaveDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLeaveById(@PathVariable Long id) {
		leaveService.deleteLeave(id);
		return ResponseEntity.noContent().build();
	}
}
