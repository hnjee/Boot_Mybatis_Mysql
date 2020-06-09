package com.hj.s1;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorRestController {
	
	@ExceptionHandler(NumberFormatException.class)
	public String error1() {
		
		return "Error";
	}

}
