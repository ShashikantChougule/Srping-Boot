package com.training.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.training.client.Employee;

@Controller
public class EmployeeController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/employees")
	public String getAllEmployees() {
		
		// Fetching employee details by id
		/*ResponseEntity<Employee> response = restTemplate.getForEntity("http://localhost:8090/api/v1/employees/689", Employee.class);
		System.out.println(response.getBody());
		System.out.println(response.getStatusCode());*/
				
		
		// Fetch all employee records.
		/*ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8090/api/v1/employees", List.class);
		List data = response.getBody();
		data.forEach(System.out::println);
		System.out.println(response.getStatusCode());*/

		// Add a new Employee
		/*Employee employee = new Employee("Mayuresh J", "HR", 45000);
		ResponseEntity<Employee> response = restTemplate.postForEntity("http://localhost:8090/api/v1/employees", employee, Employee.class);
		System.out.println(response.getBody());
		System.out.println(response.getStatusCode());*/
		
		// Delete an employee
		/*restTemplate.delete("http://localhost:8090/api/v1/employees/6");
		System.out.println("Employee record deleted successfully.");*/
		
		// Update Employee record.
		ResponseEntity<Employee> response = restTemplate.getForEntity("http://localhost:8090/api/v1/employees/9", Employee.class);
		Employee john = response.getBody();
		john.setSalary(87000);
		
		restTemplate.put("http://localhost:8090/api/v1/employees/9", john);
		System.out.println("Record updated successfully.");
		
		return "hii";
	}
}	
















