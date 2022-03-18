package com.training.employeeservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
@ApiModel(description = "Details about an Employee")
public class Employee {
	
	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@ApiModelProperty(notes = "The unique Id of an employee ")
	private int id;
	@ApiModelProperty(notes = "Name of an employee")
	private String name;
	@ApiModelProperty(notes = "Department of an employee")
	private String department;
	@ApiModelProperty(notes = "Salary of an employee")
	private int salary;	
}
