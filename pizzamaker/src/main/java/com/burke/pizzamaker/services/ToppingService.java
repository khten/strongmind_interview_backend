package com.burke.pizzamaker.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.burke.pizzamaker.exceptions.DuplicateToppingException;
import com.burke.pizzamaker.exceptions.InvalidToppingException;
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
	
	public Topping findById(int id) {
		return topRepo.findById(id).orElseThrow(()-> new ToppingNotFoundException("Unable to find topping with id: " + id));
	}
	public void deleteTopping(int id) {
		Topping t = topRepo.findById(id).orElseThrow(()-> new ToppingNotFoundException("Unable to find topping with id: " + id));
		topRepo.delete(t);
	}
	
	public Topping updateTopping(Topping t) {
		if(t.getName().isBlank()) throw new InvalidToppingException("Topping must have a name");
		if(topRepo.findByName(t.getName().trim()).isPresent()) {
			throw new DuplicateToppingException ("Topping with that name already exists");
		}
		return topRepo.save(t); 
	}
	
	public Topping addTopping(String name) {
		//trim the names
		Topping t = new Topping();
		t.setName(name.trim());
		if(t.getName().isBlank())throw new InvalidToppingException("Topping must have a name");
		if(topRepo.findByName(t.getName()).isPresent()) throw new DuplicateToppingException("Topping with name " + "\"" + t.getName() + "\" already exists");
		
		return topRepo.save(t);
	}

}
