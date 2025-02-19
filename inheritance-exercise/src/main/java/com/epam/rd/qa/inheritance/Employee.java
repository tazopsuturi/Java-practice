package com.epam.rd.qa.inheritance;

import java.math.BigDecimal;

public class Employee {
    private final String name;
    private final BigDecimal salary;
    private BigDecimal bonus;
    
    // Constructor with a default value for bonus
    public Employee(String name, BigDecimal salary) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        
        if (salary == null || salary.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        
        this.name = name;
        this.salary = salary;
        this.bonus = bonus != null ? bonus : BigDecimal.ZERO; // Set to ZERO if bonus is null
    }
    
    // Setter for bonus
    public void setBonus(BigDecimal bonus) {
        if (bonus == null || bonus.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Bonus cannot be negative");
        }
        
        this.bonus = bonus;
    }
    
    // Method to calculate total pay
    public BigDecimal toPay() {
        return salary.add(bonus);
    }
    
    // Getter for name
    public String getName() {
        return name;
    }
    
    public BigDecimal getSalary() {
        return salary;
    }
    
}
