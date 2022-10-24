package com.burke.pizzamaker.exceptions;

public class InvalidToppingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidToppingException(String msg) {
		super(msg);
	}
}
