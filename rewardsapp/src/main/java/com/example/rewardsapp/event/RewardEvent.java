package com.example.rewardsapp.event;

import org.springframework.context.ApplicationEvent;

import com.example.rewardsapp.entity.Reward;

public class RewardEvent extends ApplicationEvent {

	private Reward reward;
	
	public RewardEvent(Object source, Reward reward) {
		super(source);
		this.reward = reward;
	}
	
	public Reward getReward() {
		return reward;
	}

}
