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

import com.burke.dto.ToppingDTO;
import com.burke.pizzamaker.exceptions.DuplicateToppingException;
import com.burke.pizzamaker.models.Topping;
import com.burke.pizzamaker.services.ToppingService;

@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
@RequestMapping("/toppings")
public class ToppingController {
	
	private final ToppingService topServ;
	
	
	public ToppingController(ToppingService topServ) {
		this.topServ = topServ;
	}
	
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/all")
	public ResponseEntity<List<Topping>> getAvailableToppings (){	
		List<Topping> toppings = topServ.getAvailableToppings();
		return new ResponseEntity<>(toppings, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Topping> addTopping(@RequestBody ToppingDTO tDTO){
		try{
			Topping t = new Topping();
			t.setName(tDTO.getName().trim());
			Topping topping = topServ.addTopping(t);
			return new ResponseEntity<>(topping, HttpStatus.CREATED);
		}catch (DuplicateToppingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Topping> deleteTopping(@PathVariable("id") int id){
	    topServ.deleteTopping(id);
		return new ResponseEntity<>( HttpStatus.OK);
		
	}

	@PutMapping("/update")
	public ResponseEntity<Topping> updateTopping(@RequestBody ToppingDTO tDTO){
			
		try{
			Topping t = new Topping();
			t.setName(tDTO.getName().trim());		
			Topping topping = topServ.updateTopping(t);
	        return new ResponseEntity<>(topping, HttpStatus.OK);
		} catch (DuplicateToppingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
