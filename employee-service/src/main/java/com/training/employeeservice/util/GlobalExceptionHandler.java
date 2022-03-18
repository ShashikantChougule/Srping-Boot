package com.training.employeeservice.util;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.training.employeeservice.exception.EmployeeNotFoundException;
import com.training.employeeservice.model.ResponseError;

@ControllerAdvice
public class GlobalExceptionHandler {

	/*@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}*/
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ResponseError> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		
		ResponseError error = new ResponseError(ex.getMessage(), 404, LocalDateTime.now());
		return new ResponseEntity<ResponseError>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ResponseError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		
		/*System.out.println("Name of parameter => " + ex.getName());
		System.out.println("Error Message => " + ex.getMessage());
		System.out.println("Expected type of argument => " + ex.getRequiredType().getSimpleName()); 
		System.out.println("Actual argument => " + ex.getValue());*/
		
		String name = ex.getName();
		String expectedType = ex.getRequiredType().getSimpleName();
		String value = (String) ex.getValue();
		String message = String.format("%s should be valid '%s' but '%s' isn't", name, expectedType, value);
		// id should be valid int but seven isn't.
		
		ResponseError error = new ResponseError(message, 400, LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}



















