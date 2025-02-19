package com.epam.rd.qa.inheritance;

import java.math.BigDecimal;

public class Company {
    
    private Employee[] employees;

    public Company(Employee[] employees) {
        if (employees == null || employees.length == 0) {
            throw new IllegalArgumentException("Employee array is null or empty");
        }
        
        this.employees = employees;
    }

    public void giveEverybodyBonus(BigDecimal companyBonus) {
        
        if (companyBonus == null || companyBonus.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Company bonus must be greater than zero.");
        }
        
        for (Employee employee : employees) {
            employee.setBonus(companyBonus);
        }
    }

    public BigDecimal totalToPay() {
        BigDecimal total = BigDecimal.ZERO;
        
        for (Employee employee : employees) {
            total = total.add(employee.toPay());
        }
        return total;
    }

    public String nameMaxSalary() {
        if (employees.length == 0) {
            throw new IllegalArgumentException("There is no employees");
        }
        
        Employee maxSalaryEmployee = employees[0];
        BigDecimal maxSalary = maxSalaryEmployee.toPay();
        
        for (Employee employee : employees) {
            BigDecimal employeeSalary = employee.toPay();
            if (employeeSalary.compareTo(maxSalary) > 0) {
                maxSalary = employeeSalary;
                maxSalaryEmployee = employee;
            }
        }
        return maxSalaryEmployee.getName();
    }
}
