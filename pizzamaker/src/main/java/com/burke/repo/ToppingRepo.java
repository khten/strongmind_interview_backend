package com.burke.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burke.pizzamaker.models.Topping;

@Repository
public interface ToppingRepo extends JpaRepository<Topping, Integer> {

	Optional<Topping> findByName(String s);

	

	

	
    
	
	
}
