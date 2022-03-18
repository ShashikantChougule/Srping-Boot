package com.training.employeeservice.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ResponseError {
	
	private String message;
	private int code;
	private LocalDateTime timestamp;

}
