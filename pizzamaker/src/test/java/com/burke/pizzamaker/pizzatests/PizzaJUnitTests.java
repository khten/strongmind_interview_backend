package com.burke.pizzamaker.pizzatests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.burke.pizzamaker.models.Pizza;

@SpringBootTest
public class PizzaJUnitTests {

			
		//************************************************
	    // Pizza Unit Tests
		//************************************************
			private  Pizza p;
			
			@BeforeEach
			public void initPizza() {
				 p = new Pizza(1, "Bob", "Pepperoni");
			}
			
			@Test
			void pizzaGetIdTeSt() {
				assertEquals(1, p.getId());
			}
			@Test
			void pizzaSetIdTest() {
				p.setId(2);
				int actual = p.getId();
				assertEquals(2, actual);	
			}
			
			@Test
			void pizzaGetNameTest() {
				assertEquals("Bob", p.getName());
				
			}
			
			@Test
			void pizzaSetNameTest() {
			
			    p.setName("Tracy");
				assertEquals("Tracy", p.getName());	
			}
			
			@Test 
			void pizzaGetToppingsTest(){
				assertEquals("Pepperoni", p.getToppings());
			}
			
			@Test
			void pizzaSetToppingsTest() {
				p.setToppings("olives");
				assertEquals("olives", p.getToppings());
			}

	

	
}
