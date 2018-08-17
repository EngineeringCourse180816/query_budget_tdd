package com.ge.course;

import java.time.LocalDate;


public class BudgetQuery {
    private final BudgetDao budgetDao;

    public BudgetQuery(BudgetDao budgetDao) {
        this.budgetDao = budgetDao;
    }

    public long query(LocalDate start, LocalDate end) {
        int sum = 0;
        for (Budget budget : budgetDao.findAll()) {
            sum += 1 * new Period(start, end).getOverlappingDayCount(budget.getPeriod());
        }
        return sum;
    }

}
