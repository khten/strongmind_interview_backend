package com.burke.pizzamaker.exceptions;

public class DuplicatePizzaException extends RuntimeException{
      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicatePizzaException(String msg) {
    	  super(msg);
      }
}
