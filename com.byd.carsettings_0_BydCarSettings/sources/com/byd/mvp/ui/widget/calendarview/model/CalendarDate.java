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

    public boolean equals(int i, int i2, int i3) {
        return this.year == i && this.month == i2 && this.day == i3;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.year), Integer.valueOf(this.month), Integer.valueOf(this.day));
    }

    public boolean lessThan(CalendarDate calendarDate) {
        int i = this.year;
        int i2 = calendarDate.year;
        return i < i2 || (i2 == i && this.month < calendarDate.month) || (i2 == i && calendarDate.month == this.month && this.day < calendarDate.day);
    }

    public boolean moreThan(int i, int i2, int i3) {
        int i4 = this.year;
        return i4 > i || (i == i4 && this.month > i2) || (i == i4 && i2 == this.month && this.day > i3);
    }

    public void setDate(int i, int i2, int i3) {
        this.year = i;
        this.month = i2;
        this.day = i3;
    }

    public String toString() {
        return this.year + "/" + this.month + "/" + this.day;
    }

    public CalendarDate(int i, int i2, int i3) {
        this.year = i;
        this.month = i2;
        this.day = i3;
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
        int i = this.year;
        int i2 = calendarDate.year;
        return i > i2 || (i2 == i && this.month > calendarDate.month) || (i2 == i && calendarDate.month == this.month && this.day > calendarDate.day);
    }
}
