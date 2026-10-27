package com.google.android.material.picker;

import java.util.Arrays;
import java.util.Calendar;

/* JADX INFO: loaded from: classes8.dex */
public class MonthInYear {
    public final int daysInMonth;
    public final int daysInWeek;
    public final int month;
    private final Calendar monthInYear;
    public final int year;

    private MonthInYear(Calendar calendar) {
        this.monthInYear = calendar;
        calendar.set(5, 1);
        this.month = calendar.get(2);
        this.year = calendar.get(1);
        this.daysInWeek = this.monthInYear.getMaximum(7);
        this.daysInMonth = this.monthInYear.getActualMaximum(5);
    }

    public static MonthInYear create(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, year);
        calendar.set(2, month);
        return new MonthInYear(calendar);
    }

    public int daysFromStartOfWeekToFirstOfMonth() {
        int difference = this.monthInYear.get(7) - this.monthInYear.getFirstDayOfWeek();
        if (difference < 0) {
            return difference + this.daysInWeek;
        }
        return difference;
    }

    public Calendar getDay(int day) {
        Calendar calendar = (Calendar) this.monthInYear.clone();
        calendar.set(5, day);
        return calendar;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MonthInYear)) {
            return false;
        }
        MonthInYear that = (MonthInYear) o;
        return this.month == that.month && this.year == that.year;
    }

    public int hashCode() {
        Object[] hashedFields = {Integer.valueOf(this.month), Integer.valueOf(this.year)};
        return Arrays.hashCode(hashedFields);
    }
}
