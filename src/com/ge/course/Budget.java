package com.ge.course;

import java.time.LocalDate;

public class Budget {
    private final LocalDate date;
    private final int amount;

    public Budget(LocalDate date, int amount) {
        this.date = date;
        this.amount = amount;
    }

    public LocalDate getStart() {
        return date.withDayOfMonth(1);
    }

    public LocalDate getEnd() {
        return date.withDayOfMonth(date.lengthOfMonth());
    }

    public Period getPeriod() {
        return new Period(getStart(), getEnd());
    }

    public int getDailyAmount() {
        return amount / getStart().lengthOfMonth();
    }

    public long getOverlappingAmount(Period period) {
        return getDailyAmount() * period.getOverlappingDayCount(getPeriod());
    }
}
