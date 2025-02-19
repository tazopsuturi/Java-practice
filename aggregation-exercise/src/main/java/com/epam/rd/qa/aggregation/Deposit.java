package com.epam.rd.qa.aggregation;

import java.math.BigDecimal;

public abstract class Deposit {
	protected final BigDecimal amount;
	protected final int period;
	
	protected Deposit(BigDecimal depositAmount, int depositPeriod) {
		if (depositAmount == null || depositAmount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Amount must be greater than zero and not null.");
		}
		if (depositPeriod <= 0) {
			throw new IllegalArgumentException("Period must be greater than zero.");
		}
		
		this.amount = depositAmount;
		this.period = depositPeriod;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public int getPeriod() {
		return period;
	}
	
	public abstract BigDecimal income();
}
