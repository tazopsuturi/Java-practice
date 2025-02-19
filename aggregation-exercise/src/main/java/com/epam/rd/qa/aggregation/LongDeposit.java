package com.epam.rd.qa.aggregation;

import java.math.BigDecimal;

public class LongDeposit extends Deposit {
	private static final BigDecimal LONG_TERM_INTEREST_RATE = new BigDecimal("0.15"); // 15% monthly interest after 6 months
	
	public LongDeposit(BigDecimal depositAmount, int depositPeriod) {
		super(depositAmount, depositPeriod);
	}
	
	@Override
	public BigDecimal income() {
		BigDecimal amount = getAmount();
		for (int month = 1; month <= getPeriod(); month++) {
			if (month > 6) {
				BigDecimal interest = amount.multiply(LONG_TERM_INTEREST_RATE).setScale(2, BigDecimal.ROUND_HALF_UP);
				amount = amount.add(interest);
			}
		}
		return amount.subtract(getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
}
