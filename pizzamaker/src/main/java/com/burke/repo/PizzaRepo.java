package com.burke.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burke.pizzamaker.models.Pizza;

@Repository
public interface PizzaRepo extends JpaRepository<Pizza, String> {

	public Pizza findByPizzaName(String pizzaName);
}
