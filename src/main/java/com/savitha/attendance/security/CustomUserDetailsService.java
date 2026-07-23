package com.savitha.attendance.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.savitha.attendance.entity.Employee;
import com.savitha.attendance.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Employee employee = employeeRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Employee not found with email: " + username));

        return new EmployeeUserDetails(employee);
	}

}
