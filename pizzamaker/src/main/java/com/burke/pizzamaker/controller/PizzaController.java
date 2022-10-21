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

import com.burke.pizzamaker.exceptions.DuplicatePizzaException;
import com.burke.pizzamaker.models.Pizza;
import com.burke.pizzamaker.services.PizzaService;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/pizza")
public class PizzaController {

	private final PizzaService pizzaServ;

	public PizzaController(PizzaService pizzaServ) {
		this.pizzaServ = pizzaServ;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Pizza>> getAllPizzas(){
		List<Pizza> pizzas = pizzaServ.getAllPizzas();
		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}


	@PostMapping("/create-new-pizza")
	public ResponseEntity<Pizza> createPizza(@RequestBody Pizza p){

		try{ 
			Pizza pizza = pizzaServ.createPizza(p);
			return new ResponseEntity<>(pizza, HttpStatus.CREATED);
		}catch(DuplicatePizzaException e) {
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update-pizza")
	public ResponseEntity<Pizza> updatePizza(@RequestBody Pizza p){
		try{
			Pizza pizza = pizzaServ.updatePizza(p);
			return new ResponseEntity<>(pizza, HttpStatus.OK);

		}catch(DuplicatePizzaException e) {
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Pizza> deletePizza(@PathVariable("id") int id){

		pizzaServ.deletePizza(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
