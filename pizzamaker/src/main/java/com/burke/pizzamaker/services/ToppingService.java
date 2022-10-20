package com.burke.pizzamaker.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.burke.pizzamaker.exceptions.DuplicateToppingException;
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
	
	
	public void deleteTopping(int id) {
		Topping t = topRepo.findById(id).orElseThrow(()-> new ToppingNotFoundException("Unable to find topping with id: " + id));
		topRepo.delete(t);
	}
	
	public Topping updateTopping(Topping t) {
		//trim the names
		t.setName(t.getName().trim());
		if(topRepo.findByName(t.getName()).isPresent()) 
			throw new DuplicateToppingException("Topping with name " + "\"" + t.getName() + "\" already exists");
		return topRepo.save(t);
	}
	
	public Topping addTopping(Topping t) {
		//trim the names
		t.setName(t.getName().trim());
		if(topRepo.findByName(t.getName()).isPresent()) throw new DuplicateToppingException("Topping with name " + "\"" + t.getName() + "\" already exists");
		return topRepo.save(t);
	}

}
