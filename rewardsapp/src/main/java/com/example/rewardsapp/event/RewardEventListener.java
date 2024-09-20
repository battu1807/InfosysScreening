package com.example.rewardsapp.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.rewardsapp.controller.TransactionController;
import com.example.rewardsapp.service.RewardService;


@Component
public class RewardEventListener {

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	RewardService rewardService;
	
	@Async
    @EventListener
	public void handleRewardEvent(RewardEvent rewardEvent) {
		logger.debug("rewardEvent.getReward() :" + rewardEvent.getReward());
		if(rewardEvent.getReward() != null) {
			rewardService.saveReward(rewardEvent.getReward());	
		}
		
	}
}
