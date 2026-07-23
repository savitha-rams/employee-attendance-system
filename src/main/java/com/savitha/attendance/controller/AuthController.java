package com.savitha.attendance.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savitha.attendance.dto.LoginRequestDTO;
import com.savitha.attendance.dto.LoginResponseDTO;
import com.savitha.attendance.security.JwtService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping	("/auth")
public class AuthController {
	
	 private final AuthenticationManager authenticationManager;
	 private final JwtService jwtService;
	 
	 @PostMapping("/login")
	 public ResponseEntity<LoginResponseDTO> login(
	         @Valid @RequestBody LoginRequestDTO loginRequest) {

	     Authentication authentication =
	             authenticationManager.authenticate(
	                     new UsernamePasswordAuthenticationToken(
	                             loginRequest.getEmail(),
	                             loginRequest.getPassword()));
	     String token = jwtService.generateToken(authentication.getName());

	     return ResponseEntity.ok(
	             new LoginResponseDTO(token));
	 }

}
