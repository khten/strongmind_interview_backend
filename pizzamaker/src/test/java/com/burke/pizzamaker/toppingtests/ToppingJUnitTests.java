package com.burke.pizzamaker.toppingtests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.burke.pizzamaker.models.Topping;


class ToppingJUnitTests {

	//************************************'
	// Topping Unit Tests
	//*************************************


	private Topping t;

	@BeforeEach
	void initTopping() {
		t = new Topping(1, "pepperoni");
	}

	@Test
	void toppingGetIdTest() {
		assertEquals(1, t.getId());
	}

	@Test
	void toppingSetIdTest() {
		t.setId(2);
		assertEquals(2, t.getId());
	}

	@Test 
	void toppingGetNameTest() {
		assertEquals("pepperoni", t.getName());
	}

	@Test 
	void toppingSetNameTest() {
		t.setName("olives");
		assertEquals("olives", t.getName());
	}

}
