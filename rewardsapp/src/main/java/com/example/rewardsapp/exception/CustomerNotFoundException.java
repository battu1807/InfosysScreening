package com.example.rewardsapp.exception;

public class CustomerNotFoundException extends Exception {
	
	String message;
	
	public CustomerNotFoundException(String message){
		this.message = message;
	}

}
