package com.ge.course;

import org.junit.Test;

import java.util.Arrays;

import static java.time.LocalDate.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BudgetQueryTest {

    BudgetDao stubBudgetDao = mock(BudgetDao.class);
    BudgetQuery budgetQuery = new BudgetQuery(stubBudgetDao);

    @Test
    public void no_budget() {
        givenBudgets();

        int actual = budgetQuery.query(
                of(2018, 8, 10),
                of(2018, 8, 10));

        assertEquals(0, actual);
    }

    @Test
    public void start_and_end_is_same_date() {
        givenBudgets(budget(2018, 8, 31));

        int actual = budgetQuery.query(
                of(2018, 8, 10),
                of(2018, 8, 10));

        assertEquals(1, actual);
    }

    private Budget budget(int year, int month, int amount) {
        return new Budget(of(year, month, 1), amount);
    }

    private void givenBudgets(Budget... budgets) {
        when(stubBudgetDao.findAll()).thenReturn(Arrays.asList(budgets));
    }

}
