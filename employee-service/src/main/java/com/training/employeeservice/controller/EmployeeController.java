package com.training.employeeservice.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.training.employeeservice.exception.EmployeeNotFoundException;
import com.training.employeeservice.model.Employee;
import com.training.employeeservice.repository.EmployeeRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
// @CrossOrigin(origins= "http://localhost:4200")
// @CrossOrigin(origins= "*")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployeeDetails() {
		
		List<Employee> employees = employeeRepository.findAll();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("cookie", "secret");
		headers.add("token", "smabjhbjrjhdsdmnbmbnbmsslasl");
		headers.add("timestamp", LocalDateTime.now().toString());
		
		return new ResponseEntity<List<Employee>>(employees, headers, HttpStatus.OK);
	}
	
	@GetMapping("/employees/{id}")
	@ApiOperation(value = "Finds Employee by Id", 
	                       notes = "Provide an id to look up specific employee from the database.",
	                       response = Employee.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved employee details"),
			@ApiResponse(code = 404, message = "Employee record not found"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource")
	})
	public ResponseEntity<Employee> getEmployeeDetailsById(@ApiParam(value = "Id value for the employee that you need to retrieve")  @PathVariable int id) {

	    Employee employee = employeeRepository.findById(id)
	                                                                   .orElseThrow(() -> new EmployeeNotFoundException("Employee record does not exist."));
	    
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);	
	}
	
	/*@GetMapping("/employees/department/{department}")
	public ResponseEntity<List<Employee>> getEmployeeDetailsByDepartment(@PathVariable String department) {
		
		List<Employee> employees = employeeRepository.findByDepartment(department);
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	@GetMapping("/employees/department/{department}/{amount}")
	public ResponseEntity<List<Employee>> getEmployeeDetailsByDepartmentAndSalary(@PathVariable String department, @PathVariable int amount) {
		
		List<Employee> employees = employeeRepository.findEmployees(department, amount);
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}*/
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee) {
	
		/*Employee savedEmployee = employeeRepository.save(employee);		
		return new ResponseEntity<Employee>(savedEmployee, HttpStatus.CREATED);*/
		
		Employee savedEmployee = employeeRepository.save(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedEmployee.getId()).toUri();
		
		return ResponseEntity.created(location).body(savedEmployee);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable int id) {
		
		Employee employee = employeeRepository.findById(id)
		                                                               .orElseThrow(() -> new EmployeeNotFoundException("Employee Record does not exist."));
		
		employeeRepository.delete(employee);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		
		Employee updatedEmployee = employeeRepository.save(employee);
		return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
	}
	
	/*@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}*/
		
}





















