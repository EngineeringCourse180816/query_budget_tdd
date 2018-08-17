package com.ge.course;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;


public class BudgetQuery {
    private final BudgetDao budgetDao;

    public BudgetQuery(BudgetDao budgetDao) {
        this.budgetDao = budgetDao;
    }

    public long query(LocalDate start, LocalDate end) {
        if (budgetDao.findAll().isEmpty())
            return 0;

        Budget budget = budgetDao.findAll().get(0);
        return queryOneMonth(new Period(start, end), budget);
    }

    private long queryOneMonth(Period period, Budget budget) {
        if (period.getEnd().isBefore(budget.getStart()))
            return 0;

        if (period.getStart().isBefore(budget.getStart()))
            return 1 * (DAYS.between(budget.getStart(), period.getEnd()) + 1);

        if (period.getEnd().isAfter(budget.getEnd()))
            return 1 * (DAYS.between(period.getStart(), budget.getEnd()) + 1);

        return 1 * period.getDayCount();
    }

}
