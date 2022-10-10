package com.burke.pizzamaker.services;

import java.util.List;

import org.springframework.stereotype.Service;

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
