package com.burke.pizzamaker.exceptions;

public class InvalidToppingsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidToppingsException(String msg) {
		super(msg);
	}
}
