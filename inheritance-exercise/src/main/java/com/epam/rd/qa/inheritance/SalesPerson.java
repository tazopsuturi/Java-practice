package com.epam.rd.qa.inheritance;

import java.math.BigDecimal;

public class SalesPerson extends Employee {
    
    private final int percent;

    public SalesPerson(String name, BigDecimal salary, int percent) {
        super(name, salary);
		if (percent < 0){
            throw new IllegalArgumentException("Percent must be equal or greater than zero");
        }
        this.percent = percent;
    }
    
    @Override
    public void setBonus(BigDecimal bonus) {
        
        if (bonus == null || bonus.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Bonus must be greater than zero");
        }
        
        if (percent > 100 && percent <= 200){
            bonus = bonus.multiply(BigDecimal.valueOf(2));
        } else if (percent > 200) {
            bonus = bonus.multiply(BigDecimal.valueOf(3));
        }
        super.setBonus(bonus);
    }
}
