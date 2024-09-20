package com.example.rewardsapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.rewardsapp.controller.TransactionController;
import com.example.rewardsapp.entity.Reward;
import com.example.rewardsapp.entity.Transaction;
import com.example.rewardsapp.event.RewardEvent;
import com.example.rewardsapp.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	RewardService rewardService;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Override
	public Transaction saveTransaction(Transaction transaction) {
		logger.debug("transaction:" + transaction);
		logger.debug("transaction.getDate().getMonthValue() : " + transaction.getDate().getMonth());
		logger.debug("transaction.getDate().getYear() : " + transaction.getDate().getYear());
		
		int rewardPoints = 0;
		int amount = transaction.getAmount();
		if(amount > 100) {
			int temp = amount - 100;
			rewardPoints = 50 + (temp * 2);
		}
		else if(amount >50 && amount <=100) {
			int temp = amount - 50;
			rewardPoints = temp * 1;
		}
		
		Reward reward = new Reward();
		reward.setCustomerId(transaction.getCustomerId());
		reward.setRewardMonth(transaction.getDate().getMonth().name());
		reward.setRewardYear(transaction.getDate().getYear());
		reward.setPoints(rewardPoints);
		
//		rewardService.saveReward(reward);
		applicationEventPublisher.publishEvent(new RewardEvent(this, reward));
		
		return transactionRepository.save(transaction);
	}

	@Override
	public Transaction getTransactionById(int transactionId) {
		return transactionRepository.findByTransactionId(transactionId);
	}

	@Override
	public Transaction updateTransaction(Transaction transaction) {
		
		Transaction existingTransaction = transactionRepository.findByTransactionId(transaction.getTransactionId());
		if(existingTransaction != null) {
			existingTransaction.setAmount(transaction.getAmount());
			existingTransaction.setCustomerId(transaction.getCustomerId());
			existingTransaction.setDate(transaction.getDate());
			existingTransaction.setSpentDetails(transaction.getSpentDetails());
			return transactionRepository.save(existingTransaction);
		}
		else
			return transactionRepository.save(transaction);
	}

	@Override
	public void deleteById(int transactionId) {
		transactionRepository.deleteById(transactionId);
	}

	
	
}
