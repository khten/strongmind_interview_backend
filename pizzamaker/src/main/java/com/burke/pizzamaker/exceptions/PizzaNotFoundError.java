package com.burke.pizzamaker.exceptions;

public class PizzaNotFoundError extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PizzaNotFoundError(String msg){
		super(msg);
	}
}
