package com.training.employeeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.training.employeeservice.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>   {

	public List<Employee> findByDepartment(String department);	
	
	public List<Employee> findBySalaryLessThan(int amount);
	
	public List<Employee> findBySalaryBetween(int low, int high);

	public List<Employee> findByNameLike(String pattern);
	
	@Query(value = "FROM Employee WHERE department=?1 AND salary>=?2")
	public List<Employee> findEmployees(String department, int amount);
}
