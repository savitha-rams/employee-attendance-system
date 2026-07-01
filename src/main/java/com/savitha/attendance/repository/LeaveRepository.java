package com.savitha.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.savitha.attendance.entity.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Long>{
	
}

