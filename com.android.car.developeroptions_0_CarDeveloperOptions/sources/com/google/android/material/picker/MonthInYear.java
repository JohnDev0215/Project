package com.google.android.material.picker;

import java.util.Arrays;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
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

    public static MonthInYear create(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        calendar.set(2, i2);
        return new MonthInYear(calendar);
    }

    public int daysFromStartOfWeekToFirstOfMonth() {
        int firstDayOfWeek = this.monthInYear.get(7) - this.monthInYear.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.daysInWeek : firstDayOfWeek;
    }

    public Calendar getDay(int i) {
        Calendar calendar = (Calendar) this.monthInYear.clone();
        calendar.set(5, i);
        return calendar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MonthInYear)) {
            return false;
        }
        MonthInYear monthInYear = (MonthInYear) obj;
        return this.month == monthInYear.month && this.year == monthInYear.year;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.month), Integer.valueOf(this.year)});
    }
}
