package com.burke.pizzamaker.toppingtests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.burke.pizzamaker.models.Topping;

@SpringBootTest
public class ToppingJUnitTests {

	//************************************'
	// Topping Unit Tests
	//*************************************
	
	 
	private Topping t;
	
	@BeforeEach
	public void initTopping() {
		t = new Topping(1, "pepperoni");
	}
	
	@Test
	public void toppingGetIdTest() {
		assertEquals(1, t.getId());
	}
	
	@Test
	public void toppingSetIdTest() {
		t.setId(2);
		assertEquals(2, t.getId());
	}
	
	@Test void toppingGetNameTest() {
		assertEquals("pepperoni", t.getName());
	}
	
	@Test void toppingSetNameTest() {
		t.setName("olives");
		assertEquals("olives", t.getName());
	}
	
}
