package com.epam.rd.qa.aggregation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BaseDeposit extends Deposit{
	private final BigDecimal initialAmount;
	private final int period;
	
	public BaseDeposit(BigDecimal initialAmount, int period) {
		super(initialAmount, period);
		if (initialAmount.compareTo(BigDecimal.ZERO) <= 0 || period <= 0) {
			throw new IllegalArgumentException("Initial amount and period must be positive.");
		}
		this.initialAmount = initialAmount;
		this.period = period;
	}
	
	public BigDecimal income() {
		BigDecimal currentAmount = initialAmount;
		BigDecimal totalIncome = BigDecimal.ZERO;
		BigDecimal monthlyRate = new BigDecimal("0.05"); // 5% monthly interest
		
		for (int i = 0; i < period; i++) {
			BigDecimal interest = currentAmount.multiply(monthlyRate);
			interest = interest.setScale(2, RoundingMode.HALF_EVEN); // Round to hundredth
			totalIncome = totalIncome.add(interest);
			currentAmount = currentAmount.add(interest);
		}
		
		return totalIncome.setScale(2, RoundingMode.HALF_EVEN); // Final rounding
	}
}
