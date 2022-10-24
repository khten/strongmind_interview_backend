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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.burke.pizzamaker.exceptions.InvalidToppingException;

import com.burke.pizzamaker.exceptions.PizzaNotFoundError;
import com.burke.pizzamaker.models.Pizza;
import com.burke.pizzamaker.services.PizzaService;
import com.burke.repo.PizzaRepo;

@ExtendWith(MockitoExtension.class)
class PizzaServiceTests {

	@Mock
	private PizzaRepo repo;

	@InjectMocks
	private PizzaService service;

	private Pizza p;
	@BeforeEach
	void init() {
		p = new Pizza(1, "Monster", "pepperoni");
	}

	@Test
	void createPizzaTest() {
		when(repo.save(p)).thenReturn(p);
		assertEquals(p, service.createPizza(p));
	}

	@Test
	void createPizzaWithExceptionInvalidToppings() {
		p.setToppings("");
		when(repo.save(p)).thenThrow(InvalidToppingException.class);

		InvalidToppingException exception = assertThrows(InvalidToppingException.class, () -> repo.save(p));

		assertNotNull(exception);

	}

	@Test
	void getAllPizzasTest() {
		when(repo.findAll()).thenReturn(Stream.of(new Pizza(1, "monster", "pep"), new Pizza(2, "pepperoni", "pepper"), new Pizza(3, "olive", "olive")).collect(Collectors.toList()));
		assertEquals(3, service.getAllPizzas().size() );

	}

	@Test
	void getPizzasByNameTest() {	
		when(repo.findByName("Monster")).thenReturn(Optional.of(p));
		assertEquals(p, service.getPizzaByName("Monster"));
	}

	@Test
	void getPizzasByNameExceptionTest() {
		when(repo.findByName("Monster")).thenThrow(PizzaNotFoundError.class);
		PizzaNotFoundError exception = assertThrows(PizzaNotFoundError.class, () -> repo.findByName("Monster"));

		assertNotNull(exception);

	}

	@Test
	void getPizzaToppingsTest() {	
		when(repo.findById(1)).thenReturn(Optional.of(p));
		assertEquals(p.getToppings(), service.getPizzaToppings(1));
	}

	@Test
	void getPizzaToppingsExceptionTest() {
		when(repo.findById(-1)).thenThrow(PizzaNotFoundError.class);
		PizzaNotFoundError exception = assertThrows(PizzaNotFoundError.class, () -> repo.findById(-1));

		assertNotNull(exception);

	}

	@Test
	void updatePizzaTest() {
		when(repo.findAll()).thenReturn(Stream.of(new Pizza(1, "monster", "pep"), new Pizza(2, "pepperoni", "pepper"), new Pizza(3, "olive", "olive")).collect(Collectors.toList()));
		when(repo.save(p)).thenReturn(p);

		assertEquals(p, service.updatePizza(p));
	}

	@Test
	void deletePizzaTest() {

		service.deletePizza(p.getId());
		verify(repo,times(1)).deleteById(p.getId());
	}




}
