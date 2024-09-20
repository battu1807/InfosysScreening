package com.example.rewardsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rewardsapp.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer save(Customer customer);
	Customer findByCustomerId(int customerId);
	Customer findByCustomerName(String customerName);
}
