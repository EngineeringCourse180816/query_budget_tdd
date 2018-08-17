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

        long actual = budgetQuery.query(
                of(2018, 8, 10),
                of(2018, 8, 10));

        assertEquals(0, actual);
    }

    @Test
    public void start_and_end_is_same_date() {
        givenBudgets(budget(2018, 8, 31));

        long actual = budgetQuery.query(
                of(2018, 8, 10),
                of(2018, 8, 10));

        assertEquals(1, actual);
    }

    @Test
    public void start_and_end_are_two_days() {
        givenBudgets(budget(2018, 8, 31));

        long actual = budgetQuery.query(
                of(2018, 8, 10),
                of(2018, 8, 11));

        assertEquals(2, actual);
    }

    @Test
    public void start_is_before_budget_start() {
        givenBudgets(budget(2018, 8, 31));

        long actual = budgetQuery.query(
                of(2018, 7, 20),
                of(2018, 8, 11));

        assertEquals(11, actual);
    }

    @Test
    public void end_is_after_budget_end() {
        givenBudgets(budget(2018, 8, 31));

        long actual = budgetQuery.query(
                of(2018, 8, 25),
                of(2018, 9, 11));

        assertEquals(7, actual);
    }

    @Test
    public void end_is_before_budget_start() {
        givenBudgets(budget(2018, 8, 31));

        long actual = budgetQuery.query(
                of(2018, 7, 25),
                of(2018, 7, 29));

        assertEquals(0, actual);
    }

    @Test
    public void start_is_after_budget_end() {
        givenBudgets(budget(2018, 8, 31));

        long actual = budgetQuery.query(
                of(2018, 9, 25),
                of(2018, 9, 29));

        assertEquals(0, actual);
    }

    private Budget budget(int year, int month, int amount) {
        return new Budget(of(year, month, 1), amount);
    }

    private void givenBudgets(Budget... budgets) {
        when(stubBudgetDao.findAll()).thenReturn(Arrays.asList(budgets));
    }

}
