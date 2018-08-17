package com.ge.course;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Period {
    private final LocalDate start;
    private final LocalDate end;

    public Period(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public long getDayCount() {
        return DAYS.between(start, end) + 1;
    }

    public long getOverlappingDayCount(Period another) {
        LocalDate overlappingStart = start.isAfter(another.start) ? start : another.start;
        LocalDate overlappingEnd = end.isBefore(another.end) ? end : another.end;
        if (overlappingStart.isAfter(overlappingEnd))
            return 0;

        return new Period(overlappingStart, overlappingEnd).getDayCount();
    }
}
