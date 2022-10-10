package com.burke.pizzamaker.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.burke.pizzamaker.exceptions.ToppingNotFoundException;
import com.burke.pizzamaker.models.Topping;
import com.burke.repo.ToppingRepo;

@Service
public class ToppingService {

	private ToppingRepo topRepo;
	
	public ToppingService(ToppingRepo topRepo) {
		this.topRepo = topRepo;
	}
	
	public List<Topping> getAvailableToppings(){
		return topRepo.findAll();
	}
	
	public Topping getToppingByName(String s) {
		return topRepo.findById(s)
				.orElseThrow(()->new ToppingNotFoundException("topping: " + s + " was not found"));
	}
	
	public void deleteTopping(Topping t) {
		topRepo.delete(t);
	}
	
	public Topping updateTopping(Topping t) {
		return topRepo.save(t);
	}
	
	public Topping addTopping(Topping t) {
		return topRepo.save(t);
	}
}
