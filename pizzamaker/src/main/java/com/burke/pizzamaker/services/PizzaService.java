package com.burke.pizzamaker.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.burke.pizzamaker.exceptions.PizzaNotFoundError;
import com.burke.pizzamaker.models.Pizza;
import com.burke.repo.PizzaRepo;

@Service
public class PizzaService {

	private PizzaRepo pizzaRepo;
	
	
	public PizzaService(PizzaRepo pizzaRepo) {
		this.pizzaRepo = pizzaRepo;
	}
	//TODO: check for adding duplicate pizzas
	public Pizza createPizza(Pizza p) {
		return pizzaRepo.save(p);
	}
	public List<Pizza> getAllPizzas(){
		return pizzaRepo.findAll();
	}
	
	public Pizza getPizzaByName(String s) {
		return pizzaRepo.findByPizzaName(s).orElseThrow(()->new PizzaNotFoundError("Unable to find pizza: " + s));
	}
	
	
	public Pizza updatePizza(Pizza p) {
		return pizzaRepo.save(p);
	}
	
	
	public void deletePizza(Pizza p) {
		 pizzaRepo.delete(p);
	}
	

}
