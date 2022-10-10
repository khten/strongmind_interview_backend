package com.burke.pizzamaker.services;

import org.springframework.stereotype.Service;

import com.burke.pizzamaker.exceptions.UserNotFoundException;
import com.burke.pizzamaker.models.Employee;
import com.burke.repo.EmployeeRepo;

@Service
public class EmployeeService {
	private EmployeeRepo empRepo;
	
	public EmployeeService(EmployeeRepo empRepo) {
		this.empRepo = empRepo;
	}
	
	public Employee addEmployee(Employee e) {
		return empRepo.save(e);
	}
	
	public void deleteEmployee(Employee e) {
		 empRepo.delete(e);
	}
	
	public Employee findEmployeeById(long id) {
		return empRepo.findById(id).orElseThrow(
				()-> new UserNotFoundException("Employee with id: " + id + " was not found."));
	}
	
	public Employee updateEmployee(Employee e) {
		return empRepo.save(e);
	}
}
