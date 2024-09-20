package com.example.rewardsapp.service;

import org.springframework.http.ResponseEntity;

import com.example.rewardsapp.entity.Customer;

public interface CustomerService {
	
	Customer saveCustomer(Customer customer);
	ResponseEntity<String> verify(Customer customer);

}
