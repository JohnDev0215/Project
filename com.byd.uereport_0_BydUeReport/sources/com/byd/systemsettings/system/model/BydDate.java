package com.byd.systemsettings.system.model;

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

    public void setAm(boolean z7) {
        this.am = z7;
    }

    public void setDay(int i8) {
        this.day = i8;
    }

    public void setH24(boolean z7) {
        this.h24 = z7;
    }

    public void setHour(int i8) {
        this.hour = i8;
    }

    public void setMinute(int i8) {
        this.minute = i8;
    }

    public void setMonth(int i8) {
        this.month = i8;
    }

    public void setSecond(int i8) {
        this.second = i8;
    }

    public void setYear(int i8) {
        this.year = i8;
    }

    public String toString() {
        return "BydDate{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", h24=" + this.h24 + ", am=" + this.am + '}';
    }

    public BydDate(int i8, int i9, int i10, int i11, int i12, int i13) {
        this.year = i8;
        this.month = i9;
        this.day = i10;
        this.hour = i11;
        this.minute = i12;
        this.second = i13;
    }
}
