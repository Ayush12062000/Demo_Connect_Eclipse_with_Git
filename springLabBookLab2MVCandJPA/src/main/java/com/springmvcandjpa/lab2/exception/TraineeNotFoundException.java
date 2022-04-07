package com.springmvcandjpa.lab2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TraineeNotFoundException extends Exception{
	public TraineeNotFoundException() {}
	public TraineeNotFoundException(String message)
	{
		super(message);
	}
}
