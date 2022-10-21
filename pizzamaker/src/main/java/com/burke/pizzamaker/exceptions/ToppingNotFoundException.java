package com.burke.pizzamaker.exceptions;

public class ToppingNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ToppingNotFoundException(String msg){
		super(msg);
	}

}
