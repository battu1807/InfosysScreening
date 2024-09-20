package com.example.rewardsapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.rewardsapp.controller.TransactionController;
import com.example.rewardsapp.entity.Customer;
import com.example.rewardsapp.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JWTService jwtService;

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public ResponseEntity<String> verify(Customer customer) {
		Authentication authentication = 
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(customer.getCustomerName(), customer.getPassword()));

		if(authentication.isAuthenticated()) 
			return new ResponseEntity<String>(jwtService.generateToken(customer.getCustomerName()), HttpStatus.OK);
		
		return new ResponseEntity<String>("Failed", HttpStatus.UNAUTHORIZED);
	}

}
