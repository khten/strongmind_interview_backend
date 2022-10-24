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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.burke.pizzamaker.models.Topping;
import com.burke.pizzamaker.services.ToppingService;
import com.burke.repo.ToppingRepo;


@ExtendWith(MockitoExtension.class)
class ToppingServiceTests {


	private Topping t;

	@Mock
	private ToppingRepo repo;

	@InjectMocks
	private ToppingService service;




	@BeforeEach
	void init() {
		t = new Topping(1,"pepperoni");
	}

	@Test
	void getAvailableToppingsList() {
		when(repo.findAll()).thenReturn(Stream.of(new Topping(1, "olives"), new Topping(2, "pepperoni"), new Topping(3, "green chile")).collect(Collectors.toList()));
		assertEquals(3, service.getAvailableToppings().size());
	}

	@Test
	void deleteToppingTest() {

		when(repo.findById(t.getId())).thenReturn(Optional.of(t));
		service.deleteTopping(t.getId());
		verify(repo, times(1)).delete(t);
	}

	@Test
	void updateToppingTest() {
		when(repo.save(t)).thenReturn(t);
		assertEquals(t, service.updateTopping(t));
	}

	@Test
	void addToppingTest() {
		when(repo.save(t)).thenReturn(t);
		assertEquals(t, service.addTopping(t.getName()));
	}
}
