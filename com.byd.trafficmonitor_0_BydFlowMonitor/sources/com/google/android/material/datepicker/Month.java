package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import c.b.a.a.x.o;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* JADX INFO: loaded from: classes.dex */
public final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Calendar f4695b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f4696c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f4697d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f4698e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f4699f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f4700g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f4701h;

    public static class a implements Parcelable.Creator<Month> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Month createFromParcel(Parcel parcel) {
            return Month.k(parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Month[] newArray(int i2) {
            return new Month[i2];
        }
    }

    public Month(Calendar calendar) {
        calendar.set(5, 1);
        Calendar calendarF = o.f(calendar);
        this.f4695b = calendarF;
        this.f4697d = calendarF.get(2);
        this.f4698e = calendarF.get(1);
        this.f4699f = calendarF.getMaximum(7);
        this.f4700g = calendarF.getActualMaximum(5);
        this.f4696c = o.v().format(calendarF.getTime());
        this.f4701h = calendarF.getTimeInMillis();
    }

    public static Month k(int i2, int i3) {
        Calendar calendarR = o.r();
        calendarR.set(1, i2);
        calendarR.set(2, i3);
        return new Month(calendarR);
    }

    public static Month l(long j) {
        Calendar calendarR = o.r();
        calendarR.setTimeInMillis(j);
        return new Month(calendarR);
    }

    public static Month m() {
        return new Month(o.p());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Month)) {
            return false;
        }
        Month month = (Month) obj;
        return this.f4697d == month.f4697d && this.f4698e == month.f4698e;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f4697d), Integer.valueOf(this.f4698e)});
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public int compareTo(Month month) {
        return this.f4695b.compareTo(month.f4695b);
    }

    public int n() {
        int firstDayOfWeek = this.f4695b.get(7) - this.f4695b.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f4699f : firstDayOfWeek;
    }

    public long o(int i2) {
        Calendar calendarF = o.f(this.f4695b);
        calendarF.set(5, i2);
        return calendarF.getTimeInMillis();
    }

    public String p() {
        return this.f4696c;
    }

    public long q() {
        return this.f4695b.getTimeInMillis();
    }

    public Month r(int i2) {
        Calendar calendarF = o.f(this.f4695b);
        calendarF.add(2, i2);
        return new Month(calendarF);
    }

    public int s(Month month) {
        if (this.f4695b instanceof GregorianCalendar) {
            return ((month.f4698e - this.f4698e) * 12) + (month.f4697d - this.f4697d);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f4698e);
        parcel.writeInt(this.f4697d);
    }
}
