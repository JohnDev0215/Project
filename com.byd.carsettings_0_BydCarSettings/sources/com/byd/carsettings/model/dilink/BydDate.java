package com.byd.carsettings.model.dilink;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class BydDate implements Serializable {
    private int day;
    private int hour;
    private int minute;
    private int month;
    private int second;
    private int year;
    private boolean h24 = true;
    private boolean am = true;

    public BydDate() {
    }

    public int getDay() {
        return this.day;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getMonth() {
        return this.month;
    }

    public int getSecond() {
        return this.second;
    }

    public int getYear() {
        return this.year;
    }

    public boolean isAm() {
        return this.am;
    }

    public boolean isH24() {
        return this.h24;
    }

    public void setAm(boolean z) {
        this.am = z;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public void setH24(boolean z) {
        this.h24 = z;
    }

    public void setHour(int i) {
        this.hour = i;
    }

    public void setMinute(int i) {
        this.minute = i;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public void setSecond(int i) {
        this.second = i;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public String toString() {
        return "BydDate{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", h24=" + this.h24 + ", am=" + this.am + '}';
    }

    public BydDate(int i, int i2, int i3, int i4, int i5, int i6) {
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.hour = i4;
        this.minute = i5;
        this.second = i6;
    }
}
