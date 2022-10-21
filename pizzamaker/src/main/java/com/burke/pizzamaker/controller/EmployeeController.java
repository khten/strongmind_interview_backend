package com.burke.pizzamaker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burke.pizzamaker.models.Employee;
import com.burke.pizzamaker.services.EmployeeService;

import DTO.EmployeeDTO;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees (){
		
		List<Employee> employees = employeeService.findAllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	

	@GetMapping("/find/{id}")
	public ResponseEntity<Employee> getEmployeeById (@PathVariable("id") Long id){
		Employee emp = employeeService.getEmployeeById(id);
		return new ResponseEntity<>(emp, HttpStatus.OK);
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDTO empDTO){
	    Employee employee = new Employee();
	    employee.setEmp_role(empDTO.getRole());
	    employee.setName(empDTO.getName());
	    
		Employee emp = employeeService.addEmployee(employee);
		return new ResponseEntity<>(emp, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeDTO empDTO){
		Employee employee = new Employee();
	    employee.setEmp_role(empDTO.getRole());
	    employee.setName(empDTO.getName());
	    
		Employee emp = employeeService.updateEmployee(employee);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id){
		Employee e = employeeService.getEmployeeById(id);
		employeeService.deleteEmployee(e);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
}
