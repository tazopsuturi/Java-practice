package com.epam.rd.qa.aggregation;

import java.math.BigDecimal;

public class Client {
    private final Deposit[] deposits; // Array to store deposits
    private int depositCount;         // Number of deposits currently added
    
    // Constructor
    public Client() {
        this.deposits = new Deposit[10]; // Initialize array with 10 elements
        this.depositCount = 0;
    }
    
    // Method to add a deposit
    public boolean addDeposit(Deposit deposit) {
        if (depositCount < deposits.length) {
            deposits[depositCount] = deposit;
            depositCount++;
            return true;
        }
        return false; // No space left in the array
    }
    
    // Method to calculate total income
    public BigDecimal totalIncome() {
        BigDecimal totalIncome = BigDecimal.ZERO;
        for (int i = 0; i < depositCount; i++) {
            totalIncome = totalIncome.add(deposits[i].income());
        }
        return totalIncome.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    // Method to find the maximum income
    public BigDecimal maxIncome() {
        BigDecimal totalIncome = BigDecimal.ZERO;
        for (int i = 0; i < depositCount; i++) {
            BigDecimal income = deposits[i].income().setScale(2, BigDecimal.ROUND_HALF_UP);
            totalIncome = totalIncome.add(income);
        }
        return totalIncome.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    // Method to get income by deposit number
    public BigDecimal getIncomeByNumber(int number) {
        if (number >= 0 && number < depositCount) {
            return deposits[number].income();
        }
        return BigDecimal.ZERO; // Invalid index
    }
}
