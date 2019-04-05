package com.kuehnenagel.coinclient.exception;

public class CurrencyNotSupportedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6943450022740796140L;

	
	public CurrencyNotSupportedException(String message) {
		super(message);
	}
}
