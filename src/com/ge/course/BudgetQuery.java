package com.ge.course;

import java.time.LocalDate;


public class BudgetQuery {
    private final BudgetDao budgetDao;

    public BudgetQuery(BudgetDao budgetDao) {
        this.budgetDao = budgetDao;
    }

    public long query(LocalDate start, LocalDate end) {
        if (budgetDao.findAll().isEmpty())
            return 0;

        Budget budget = budgetDao.findAll().get(0);
        return 1 * new Period(start, end).getOverlappingDayCount(budget.getPeriod());
    }

}
