package com.kof.snake.exceptions;

public class SomethingsWrongException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public SomethingsWrongException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
