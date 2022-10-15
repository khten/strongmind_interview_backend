package com.burke.pizzamaker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burke.pizzamaker.exceptions.DuplicateToppingException;
import com.burke.pizzamaker.models.Topping;
import com.burke.pizzamaker.services.ToppingService;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/toppings")
public class ToppingController {
	
	private final ToppingService topServ;
	
	public ToppingController(ToppingService topServ) {
		this.topServ = topServ;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Topping>> getAvailableToppings (){	
		List<Topping> toppings = topServ.getAvailableToppings();
		return new ResponseEntity<>(toppings, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Topping> addTopping(@RequestBody Topping t){
		try{
			Topping topping = topServ.addTopping(t);
			return new ResponseEntity<>(topping, HttpStatus.CREATED);
		}catch (DuplicateToppingException e) {
			return new ResponseEntity<>(t, HttpStatus.BAD_REQUEST);
		}

	}
	
	@DeleteMapping("/delete/{name}")
	public ResponseEntity<Topping> deleteTopping(@PathVariable("name") String name){
		Topping topping = topServ.getToppingByName(name);
	    topServ.deleteTopping(topping);
		return new ResponseEntity<>(topping, HttpStatus.OK);
		
	}

	@PutMapping("/update")
	public ResponseEntity<Topping> updateTopping(@RequestBody Topping t){
		t.setName(t.getName().trim());
		try{
			Topping topping = topServ.updateTopping(t);
	        return new ResponseEntity<>(topping, HttpStatus.OK);
		} catch (DuplicateToppingException e) {
			return new ResponseEntity<>(t, HttpStatus.BAD_REQUEST);
		}
	}
	
}
