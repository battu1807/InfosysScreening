package com.example.rewardsapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.rewardsapp.entity.UserPrincipal;
import com.example.rewardsapp.controller.TransactionController;
import com.example.rewardsapp.entity.Customer;
import com.example.rewardsapp.repository.CustomerRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String customerName) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByCustomerName(customerName);
		if(customer == null) {
			logger.error("User not found!!");
			throw new UsernameNotFoundException("User not found!!");
		}
		
		return new UserPrincipal(customer);
	}

}
