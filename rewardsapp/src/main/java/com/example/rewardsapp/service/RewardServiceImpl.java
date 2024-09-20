package com.example.rewardsapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rewardsapp.controller.TransactionController;
import com.example.rewardsapp.entity.MonthlyTotalPoints;
import com.example.rewardsapp.entity.Reward;
import com.example.rewardsapp.entity.TotalPoints;
import com.example.rewardsapp.repository.RewardRepository;

import jakarta.persistence.Tuple;

@Service
public class RewardServiceImpl implements RewardService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	
	@Autowired
	RewardRepository rewardRepository;
	
	@Override
	public Reward saveReward(Reward reward) {
		return rewardRepository.save(reward);
	}

	@Override
	public List<Reward> getRewardsByCustomerId(int customerId) {
		return rewardRepository.findByCustomerId(customerId);
	}

	@Override
	public List<Reward> getAllRewards() {
		return rewardRepository.findAll();
	}

	@Override
	public List<TotalPoints> getTotalRewards() {
		
		
		List<Tuple> monthlyTuplesList = rewardRepository.findTotalPointsGroupByCustomerIdAndMonth();
		List<MonthlyTotalPoints> listOfMonthlyTotalPoints = new ArrayList<MonthlyTotalPoints>();
		Map<Integer, List<MonthlyTotalPoints>> mapOfMonthlyTotaPoints = new HashMap<>();
		MonthlyTotalPoints mtp = new MonthlyTotalPoints();
		for(Tuple tuple:monthlyTuplesList) {

			int customerId = tuple.get("CUSTOMERID", Integer.class).intValue();
			String rewardMonth = tuple.get("REWARDMONTH", String.class);
			int rewardYear = tuple.get("REWARDYEAR", Integer.class).intValue();
		    int monthlyToatlPoints = tuple.get("MONTHLYTOTALPOINTS", Long.class).intValue();
		    mtp = new MonthlyTotalPoints();
		    mtp.setCustomerId(customerId);
		    mtp.setRewardMonth(rewardMonth);
		    mtp.setRewardYear(rewardYear);
		    mtp.setMonthlyTotalPoints(monthlyToatlPoints);
		    listOfMonthlyTotalPoints.add(mtp);
		    if(mapOfMonthlyTotaPoints.containsKey(customerId)) {
		    	List<MonthlyTotalPoints> tempList  = mapOfMonthlyTotaPoints.get(customerId);
		    	tempList.add(mtp);
		    	mapOfMonthlyTotaPoints.put(customerId, tempList);
		    }else
		    {
		    	List<MonthlyTotalPoints> tempList = new ArrayList<MonthlyTotalPoints>();
		    	tempList.add(mtp);
		    	mapOfMonthlyTotaPoints.put(customerId, tempList);
		    }
		    mtp = null;
		}
		logger.info("listOfMonthlyTotalPoints : " + listOfMonthlyTotalPoints.toString());
		
		
		List<Tuple> tuplesList = rewardRepository.findTotalPointsGroupByCustomerId();
		List<TotalPoints> listOfTotalPoints = new ArrayList<TotalPoints>();
		TotalPoints tp = new TotalPoints();
		for(Tuple tuple:tuplesList) {

			int customerId = tuple.get("CUSTOMERID", Integer.class).intValue();
		    int toatlPoints = tuple.get("TOTALPOINTS", Long.class).intValue();
		    tp = new TotalPoints();
		    tp.setCustomerId(customerId);
		    tp.setTotalPoints(toatlPoints);
		    tp.setListOfMonthlyTotalPoints(mapOfMonthlyTotaPoints.get(customerId));
		    listOfTotalPoints.add(tp);
		    tp = null;
		}
		
		logger.info("listOfTotalPoints : " + listOfTotalPoints.toString());
		
		return listOfTotalPoints;
	}
}
