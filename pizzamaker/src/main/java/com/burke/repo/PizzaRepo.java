package com.burke.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burke.pizzamaker.models.Pizza;
import com.burke.pizzamaker.models.Topping;

@Repository
public interface PizzaRepo extends JpaRepository<Pizza, Integer> {

	Optional<Pizza> findByName(String s);

	
	

}
