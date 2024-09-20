package com.example.rewardsapp.service;

import java.util.List;

import com.example.rewardsapp.entity.Reward;
import com.example.rewardsapp.entity.TotalPoints;

public interface RewardService {

	Reward saveReward(Reward reward);
	List<Reward> getRewardsByCustomerId(int customerId);
	List<Reward> getAllRewards();
	List<TotalPoints> getTotalRewards();
	
}
