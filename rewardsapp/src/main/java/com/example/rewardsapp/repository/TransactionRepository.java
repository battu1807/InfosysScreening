package com.example.rewardsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rewardsapp.entity.Customer;
import com.example.rewardsapp.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	Transaction save(Transaction transaction);
	Transaction findByTransactionId(int transactionId);
}
