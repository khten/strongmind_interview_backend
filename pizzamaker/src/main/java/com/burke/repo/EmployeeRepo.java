package com.burke.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burke.pizzamaker.models.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{

	
}
