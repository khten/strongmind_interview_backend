package com.burke.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burke.pizzamaker.models.Topping;

@Repository
public interface ToppingRepo extends JpaRepository<Topping, String> {

	
	
}
