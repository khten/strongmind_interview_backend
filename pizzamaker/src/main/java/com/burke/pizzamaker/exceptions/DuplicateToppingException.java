package com.burke.pizzamaker.exceptions;

public class DuplicateToppingException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateToppingException(String msg){
		super(msg);
	}
}
