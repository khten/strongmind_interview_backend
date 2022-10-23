package com.burke.pizzamaker;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


import org.springframework.boot.test.context.SpringBootTest;

import com.burke.pizzamaker.employeetests.EmployeeJUnitTests;
import com.burke.pizzamaker.pizzatests.PizzaJUnitTests;
import com.burke.pizzamaker.toppingtests.ToppingJUnitTests;



@SpringBootTest(classes = {
		 PizzamakerApplicationTests.class, 
		 EmployeeJUnitTests.class,
		 PizzaJUnitTests.class,
		 ToppingJUnitTests.class
   })
class PizzamakerApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

	
}
