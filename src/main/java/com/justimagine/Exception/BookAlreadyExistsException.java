package com.justimagine.Exception;

public class BookAlreadyExistsException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public BookAlreadyExistsException() {
	}

	public BookAlreadyExistsException(String message) {
		super();
		this.message = message;
	}
	
	

}
