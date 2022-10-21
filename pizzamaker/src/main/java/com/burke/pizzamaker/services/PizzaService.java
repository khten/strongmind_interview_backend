package com.burke.pizzamaker.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.burke.pizzamaker.exceptions.DuplicatePizzaException;
import com.burke.pizzamaker.exceptions.InvalidToppingsException;
import com.burke.pizzamaker.exceptions.PizzaNotFoundError;
import com.burke.pizzamaker.models.Pizza;
import com.burke.repo.PizzaRepo;

@Service
public class PizzaService {

	private PizzaRepo pizzaRepo;
	
	
	public PizzaService(PizzaRepo pizzaRepo) {
		this.pizzaRepo = pizzaRepo;
	}
	
	public Pizza createPizza(Pizza p) {
		//make sure that no leading or trailing spaces
		p.setName(p.getName().trim());
		if(p.getToppings().isBlank()) throw new InvalidToppingsException("Pizza must have at least 1 topping"); 
		//check to make sure that such a pizza does not already exist
		checkForDuplicatePizzas(p);
		return pizzaRepo.save(p);
	}
	public List<Pizza> getAllPizzas(){
		List<Pizza> pizzas  = pizzaRepo.findAll();
		return pizzas;
	}
	
	public Pizza getPizzaByName(String s) {
		return pizzaRepo.findByName(s).orElseThrow(()->new PizzaNotFoundError("Pizza with name " + s + " could not be found"));
	}
	
	public String getPizzaToppings(int pizzaId){
		Pizza p = pizzaRepo.findById(pizzaId).orElseThrow(()->new PizzaNotFoundError("Pizza with id " + pizzaId + " was not found" ));
		return p.getToppings();
	}
	private void checkForDuplicatePizzas(Pizza p) {
	
		List<Pizza> pizzaList = pizzaRepo.findAll();
	    for(Pizza op : pizzaList) {
	    	if(p.getName() == op.getName() || p.getToppings().equals(op.getToppings())) {
	    		throw new DuplicatePizzaException("Error: Pizza with the same name or same toppings found");
	    	}
	    }
	}
	public Pizza updatePizza(Pizza p) {
		p.setName(p.getName().trim());
		if(p.getToppings().isBlank()) throw new InvalidToppingsException("Pizza must have at least 1 topping");
		checkForDuplicatePizzas(p);
		return pizzaRepo.save(p);
	}
	
	
	public void deletePizza(int id) {
		 pizzaRepo.deleteById(id);
	}
	

}
