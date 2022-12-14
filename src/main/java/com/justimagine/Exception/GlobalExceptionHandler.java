package com.justimagine.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value= {BookAlreadyExistsException.class})
	public ResponseEntity<ApiError> handleBookAlreadyExistsException(BookAlreadyExistsException baee)
	{
		String expMsg=baee.getMessage();
		
		String expCode="EXP-406";
				
				ApiError error=new ApiError();
		
		
					error.setMsg(expMsg);
					error.setCode(expCode);
					
					return new ResponseEntity<ApiError>(error,HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	@ExceptionHandler(value= {NoSuchBookExistsException.class})
	public ResponseEntity<ApiError> handleNoSuchBookExistsException(NoSuchBookExistsException nsbee)
	{
		
		String expMsg=nsbee.getMessage();
		
		String expCode="Exp-404";
		
		ApiError error=new ApiError();
		
		error.setMsg(expMsg);
		error.setCode(expCode);
		
		
		return new ResponseEntity<ApiError>(error,HttpStatus.NOT_FOUND);
				
	}
}
