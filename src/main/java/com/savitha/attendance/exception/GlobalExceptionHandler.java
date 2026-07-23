package com.savitha.attendance.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private ResponseEntity<ApiErrorResponse> buildErrorResponse(
			String message,
			HttpStatus status) {

		ApiErrorResponse errorResponse = new ApiErrorResponse(
				LocalDateTime.now(),
				status.value(),
				status.getReasonPhrase(),
				message
				);

		return new ResponseEntity<>(errorResponse, status);
	}
	
	private ResponseEntity<ApiErrorResponse> buildValidationErrorResponse(
	        Map<String, String> validationErrors) {

	    ApiErrorResponse errorResponse = new ApiErrorResponse(
	            LocalDateTime.now(),
	            HttpStatus.BAD_REQUEST.value(),
	            HttpStatus.BAD_REQUEST.getReasonPhrase(),
	            "Validation failed.",
	            validationErrors
	    );

	    return new ResponseEntity<>(
	            errorResponse,
	            HttpStatus.BAD_REQUEST
	    );
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidLeaveRequestException.class)
	public ResponseEntity<ApiErrorResponse> handleInvalidLeaveRequestException(InvalidLeaveRequestException ex) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateAttendanceException.class)
	public ResponseEntity<ApiErrorResponse> handleDuplicateAttendanceException(DuplicateAttendanceException ex) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(DuplicateCheckOutException.class)
	public ResponseEntity<ApiErrorResponse> handleDuplicateCheckOutException(DuplicateCheckOutException ex) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(CheckInNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleCheckInNotFoundException(
			CheckInNotFoundException ex) { 
		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleValidationExceptions(
			MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error ->
		errors.put(error.getField(), error.getDefaultMessage())
				);

		return buildValidationErrorResponse(errors);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiErrorResponse> handleInvalidRequestBody(HttpMessageNotReadableException ex) {
		return buildErrorResponse(
				"Invalid request body. Please check enum values and data format.",
				HttpStatus.BAD_REQUEST
				);
	}

}
