package com.epam.rd.qa.inheritance;

import java.math.BigDecimal;

public class Manager extends Employee{
    
    private final int clientAmount;

    public Manager(String name, BigDecimal salary, int clientAmount) {
		super(name, salary);
		if (clientAmount < 0) {
            throw new IllegalArgumentException("Client amount cannot be negative");
        }
        this.clientAmount = clientAmount;
    }

    @Override
    public void setBonus(BigDecimal bonus) {
        
        if (bonus == null || bonus.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Bonus cannot be negative");
        }
        
        
        if (clientAmount > 100 && clientAmount <= 150){
            bonus = bonus.add(BigDecimal.valueOf(500));
        } else if (clientAmount > 150) {
            bonus = bonus.add(BigDecimal.valueOf(1000));
        }
        super.setBonus(bonus);
    }
}
