package com.ge.course;

import java.time.LocalDate;


public class BudgetQuery {
    private final BudgetDao budgetDao;

    public BudgetQuery(BudgetDao budgetDao) {
        this.budgetDao = budgetDao;
    }

    public long query(LocalDate start, LocalDate end) {
        return budgetDao.findAll().stream()
                .mapToInt(budget -> (int) budget.getOverlappingAmount(new Period(start, end)))
                .sum();
    }

}
