package com.burke.dto;

public class PizzaDTO {

	private int id;
	private String name;
	private String toppings;
	
	public PizzaDTO(String name, String toppings) {
		this.name = name;
		this.toppings = toppings;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getToppings() {
		return this.toppings;
	}
	
	public int getId() {
		return this.id;
	}
}
