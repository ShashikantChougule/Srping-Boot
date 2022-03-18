package com.training.employeeservice.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="details")
public class CustomEndpoint {
	
	@ReadOperation
	public Map<String, String> getDetails() {
		
		HashMap<String, String> map = new HashMap<>();
		map.put("version", "1.0");
		map.put("app-name", "Employee Service");
		map.put("description", "Demo application of Spring Boot - Employee Service");
		return map;
	}
	
	@WriteOperation
    public Map<String, String> postDetails() {
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "Post request executed successfully.");
		return map;
    }
}


