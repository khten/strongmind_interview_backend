package com.burke.pizzamaker.toppingtests;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.burke.pizzamaker.models.Topping;
import com.burke.pizzamaker.services.ToppingService;
import com.burke.repo.ToppingRepo;

@SpringBootTest
public class ToppingServiceTests {

	private Topping t;
	
	@Autowired
	private ToppingService service;
	
	@MockBean
	private ToppingRepo repo;
	
	
	@BeforeEach
	public void init() {
		t = new Topping(1,"pepperoni");
	}
	
	@Test
	public void getAvailableToppingsList() {
		when(repo.findAll()).thenReturn(Stream.of(new Topping(1, "olives"), new Topping(2, "pepperoni"), new Topping(3, "green chile")).collect(Collectors.toList()));
		assertEquals(3, service.getAvailableToppings().size());
	}
	
	@Test
	public void deleteToppingTest() {
		
		when(repo.findById(t.getId())).thenReturn(Optional.of(t));
		service.deleteTopping(t.getId());
		verify(repo, times(1)).delete(t);
	}
	
	@Test
	public void updateToppingTest() {
		when(repo.save(t)).thenReturn(t);
		assertEquals(t, service.updateTopping(t));
	}
	
	@Test
	public void addToppingTest() {
		when(repo.save(t)).thenReturn(t);
		assertEquals(t, service.addTopping(t));
	}
}
