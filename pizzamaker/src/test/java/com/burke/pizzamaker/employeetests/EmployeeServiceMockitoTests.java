package com.burke.pizzamaker.employeetests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.burke.pizzamaker.enums.Role;
import com.burke.pizzamaker.models.Employee;
import com.burke.pizzamaker.services.EmployeeService;
import com.burke.repo.EmployeeRepo;

@SpringBootTest(classes=EmployeeServiceMockitoTests.class)
public class EmployeeServiceMockitoTests {

	@Autowired
	private EmployeeService service;
	
	@MockBean
	private EmployeeRepo repo;
	
	
	
	
	
	@Test
	public void addEmployeeTest() {
		Employee emp = new Employee(1L, "Dan", Role.CHEF);
		when(repo.save(emp)).thenReturn(emp);
		
		assertEquals(emp, service.addEmployee(emp));
	}
	
	@Test
	public void deleteEmployeeTest() {
		Employee emp = new Employee(1L, "Dan", Role.CHEF);
		service.deleteEmployee(emp);
		verify(repo,times(1)).delete(emp);
	}
	@Test
	public void getEmployeeByIdTest() {
		Long id = 2L;
        Employee emp = new Employee(id, "Dan", Role.CHEF);
		when(repo.findById(id)).thenReturn(Optional.of(emp));
		
		assertEquals(emp, service.getEmployeeById(id));
		
	}
	
	@Test
	public void updateEmployeeTest() {
		Employee emp = new Employee(1L, "Dan", Role.CHEF);
		when(repo.save(emp)).thenReturn(emp);
		
		assertEquals(emp, service.updateEmployee(emp));
	}
	
	@Test
	public void findAllEmployeesTest() {
		when(repo.findAll()).thenReturn(Stream.of(new Employee(1L, "Dan", Role.OWNER), new Employee(2L, "Bob", Role.CHEF), new Employee(3L, "Sara", Role.CHEF)).collect(Collectors.toList()));
		
		assertEquals(3, service.findAllEmployees().size());
	}
	
	
}
