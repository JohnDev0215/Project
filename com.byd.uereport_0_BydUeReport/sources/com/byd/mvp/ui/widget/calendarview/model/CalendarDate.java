package com.byd.mvp.ui.widget.calendarview.model;

import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class CalendarDate implements Serializable {
    public String chinaDay;
    public String chinaMonth;
    public int day;
    public boolean hasEvent;
    public int month;
    public int monthFlag;
    public int week;
    public int year;

    public CalendarDate() {
    }

    public boolean equals(int i8, int i9, int i10) {
        return this.year == i8 && this.month == i9 && this.day == i10;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.year), Integer.valueOf(this.month), Integer.valueOf(this.day));
    }

    public boolean lessThan(CalendarDate calendarDate) {
        int i8 = this.year;
        int i9 = calendarDate.year;
        return i8 < i9 || (i9 == i8 && this.month < calendarDate.month) || (i9 == i8 && calendarDate.month == this.month && this.day < calendarDate.day);
    }

    public boolean moreThan(int i8, int i9, int i10) {
        int i11 = this.year;
        return i11 > i8 || (i8 == i11 && this.month > i9) || (i8 == i11 && i9 == this.month && this.day > i10);
    }

    public void setDate(int i8, int i9, int i10) {
        this.year = i8;
        this.month = i9;
        this.day = i10;
    }

    public String toString() {
        return this.year + "/" + this.month + "/" + this.day;
    }

    public CalendarDate(int i8, int i9, int i10) {
        this.year = i8;
        this.month = i9;
        this.day = i10;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CalendarDate.class != obj.getClass()) {
            return false;
        }
        CalendarDate calendarDate = (CalendarDate) obj;
        return this.year == calendarDate.year && this.month == calendarDate.month && this.day == calendarDate.day;
    }

    public boolean moreThan(CalendarDate calendarDate) {
        int i8 = this.year;
        int i9 = calendarDate.year;
        return i8 > i9 || (i9 == i8 && this.month > calendarDate.month) || (i9 == i8 && calendarDate.month == this.month && this.day > calendarDate.day);
    }
}
