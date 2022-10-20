package com.justimagine.Exception;

public class NoSuchBookExistsException extends RuntimeException {
	
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String message;
		
		

		public NoSuchBookExistsException() {
			
		}



		public NoSuchBookExistsException(String message) {
			super();
			this.message = message;
		}

		
		

}
