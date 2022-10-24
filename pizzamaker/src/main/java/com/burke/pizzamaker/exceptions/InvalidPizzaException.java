package com.burke.pizzamaker.exceptions;

public class InvalidPizzaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidPizzaException(String msg) {
		super(msg);
	}

}
