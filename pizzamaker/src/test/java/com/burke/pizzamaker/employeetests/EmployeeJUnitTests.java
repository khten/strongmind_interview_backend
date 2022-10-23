package com.burke.pizzamaker.employeetests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.burke.pizzamaker.enums.Role;
import com.burke.pizzamaker.models.Employee;


class EmployeeJUnitTests {
	
	//************************************************
		//Employee Tests
		//************************************************
		private  Employee e;
		
		@BeforeEach
		 void initEmployee() {
			e = new Employee(1L, "Bob", Role.OWNER);
		}
		
		@Test
		void employeeGetIdTeSt() {
			assertEquals(1L, e.getId());
		}
		@Test
		void employeeSetIdTest() {
			Long actual = e.getId();
			assertEquals(1L, actual);	
		}
		
		@Test
		void employeeGetNameTest() {
			assertEquals("Bob", e.getName());
			
		}
		
		@Test
		void employeeSetNameTest() {
		
		    e.setName("Tracy");
			assertEquals("Tracy", e.getName());	
		}
		
		@Test 
		void employeeGetEmployeeRoleTest(){
			assertEquals(Role.OWNER, e.getEmployeeRole());
		}
		
		@Test
		void employeeSetEmployeeRoleTest() {
			e.setEmployeeRole(Role.CHEF);
			assertEquals(Role.CHEF, e.getEmployeeRole());
		}

}
