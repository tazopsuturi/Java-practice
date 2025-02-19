package com.epam.rd.qa.aggregation;

import java.math.BigDecimal;

public class SpecialDeposit extends Deposit {
	
	public SpecialDeposit(BigDecimal depositAmount, int depositPeriod) {
		super(depositAmount, depositPeriod);
	}
	
	@Override
	public BigDecimal income() {
		BigDecimal amount = getAmount();
		
		for (int month = 1; month <= getPeriod(); month++) {
			BigDecimal monthlyIncome = amount.multiply(new BigDecimal(month).multiply(new BigDecimal("0.01"))).setScale(2, BigDecimal.ROUND_HALF_UP); // Month-based percentage
			amount = amount.add(monthlyIncome);
		}
		
		return amount.subtract(getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
