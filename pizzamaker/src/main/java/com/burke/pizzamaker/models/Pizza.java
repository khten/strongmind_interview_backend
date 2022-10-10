package com.burke.pizzamaker.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {

	@Id
	@Column(unique=true, nullable=false)
	private String pizzaName;
	
	
	@JsonIgnore
	private List<Topping> toppings;
	
	
	
}
