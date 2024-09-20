package com.example.rewardsapp.service;

import com.example.rewardsapp.entity.Transaction;

public interface TransactionService {

	Transaction saveTransaction(Transaction transaction);
	Transaction getTransactionById(int transactionId);
	Transaction updateTransaction(Transaction transaction);
	void deleteById(int transactionId);
	
}
