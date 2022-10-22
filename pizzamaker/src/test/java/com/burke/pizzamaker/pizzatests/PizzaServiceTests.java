package com.burke.pizzamaker.pizzatests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.burke.pizzamaker.exceptions.InvalidToppingsException;
import com.burke.pizzamaker.exceptions.PizzaNotFoundError;
import com.burke.pizzamaker.models.Pizza;
import com.burke.pizzamaker.services.PizzaService;
import com.burke.repo.PizzaRepo;

@SpringBootTest
public class PizzaServiceTests {

	@Autowired
	private PizzaService service;
	
	@MockBean
	private PizzaRepo repo;
	
	private Pizza p;
	@BeforeEach
	public void init() {
		p = new Pizza(1, "Monster", "pepperoni");
	}
	
	@Test
	public void createPizzaTest() {
		when(repo.save(p)).thenReturn(p);
		assertEquals(p, service.createPizza(p));
	}
	
	@Test
	public void createPizzaWithExceptionInvalidToppings() {
		p.setToppings("");
		when(repo.save(p)).thenThrow(InvalidToppingsException.class);
		
		InvalidToppingsException exception = assertThrows(InvalidToppingsException.class, () -> repo.save(p));
		
		 assertNotNull(exception);
		
	}
	
	@Test
	public void getAllPizzasTest() {
		when(repo.findAll()).thenReturn(Stream.of(new Pizza(1, "monster", "pep"), new Pizza(2, "pepperoni", "pepper"), new Pizza(3, "olive", "olive")).collect(Collectors.toList()));
		assertEquals(3, service.getAllPizzas().size() );
		
	}
	
	@Test
	public void getPizzasByNameTest() {	
		when(repo.findByName("Monster")).thenReturn(Optional.of(p));
		assertEquals(p, service.getPizzaByName("Monster"));
	}
	
	@Test
	public void getPizzasByNameExceptionTest() {
		when(repo.findByName("Monster")).thenThrow(PizzaNotFoundError.class);
		PizzaNotFoundError exception = assertThrows(PizzaNotFoundError.class, () -> repo.findByName("Monster"));
		
	    assertNotNull(exception);
		
	}
	
	@Test
	public void getPizzaToppingsTest() {	
		when(repo.findById(1)).thenReturn(Optional.of(p));
		assertEquals(p.getToppings(), service.getPizzaToppings(1));
	}
	
	@Test
	public void getPizzaToppingsExceptionTest() {
		when(repo.findById(-1)).thenThrow(PizzaNotFoundError.class);
		PizzaNotFoundError exception = assertThrows(PizzaNotFoundError.class, () -> repo.findById(-1));
		
	    assertNotNull(exception);
		
	}
	
	@Test
	public void updatePizzaTest() {
		when(repo.findAll()).thenReturn(Stream.of(new Pizza(1, "monster", "pep"), new Pizza(2, "pepperoni", "pepper"), new Pizza(3, "olive", "olive")).collect(Collectors.toList()));
		when(repo.save(p)).thenReturn(p);
		
		assertEquals(p, service.updatePizza(p));
	}
	
	@Test
	public void deletePizzaTest() {
		
		service.deletePizza(p.getId());
		verify(repo,times(1)).deleteById(p.getId());
	}
	
	
	
	
}
