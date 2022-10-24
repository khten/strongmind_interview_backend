package com.burke.pizzamaker.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.burke.pizzamaker.exceptions.DuplicatePizzaException;
import com.burke.pizzamaker.exceptions.InvalidPizzaException;

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
		if(p.getToppings().isBlank()) throw new InvalidPizzaException("Pizza must have at least 1 topping"); 
		//check to make sure that such a pizza does not already exist
		checkForDuplicatePizzas(p);
		return pizzaRepo.save(p);
	}
	public List<Pizza> getAllPizzas(){
		return pizzaRepo.findAll();
		
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
		//check for blank toppings
		if(p.getToppings().isBlank()) throw new InvalidPizzaException("Pizza must have at least 1 topping");
		if(p.getName().isBlank()) throw new InvalidPizzaException("Pizza must have a name");
		//remove leading and trailing spaces in the pizza name
		p.setName(p.getName().trim());
		
		List<Pizza> pizzaList = pizzaRepo.findAll();
		for(Pizza op : pizzaList) {
			//do not compare it to itself
			if(p.getId() == op.getId()) continue;
			//check to make sure name is unique and toppings are unique
			if(p.getName().equals(op.getName())) throw new DuplicatePizzaException("Duplicate pizza found");
			if(p.getToppings().equals(op.getToppings())) throw new DuplicatePizzaException("Another pizza exists with those toppings");
		}
		
		return pizzaRepo.save(p);
	}
	
	
	public void deletePizza(int id) {
		 pizzaRepo.deleteById(id);
	}
	

}
