package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.ni;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* JADX INFO: loaded from: classes.dex */
public final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new a();
    public final Calendar b;
    public final String c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;
    public final long h;

    public static class a implements Parcelable.Creator<Month> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Month createFromParcel(Parcel parcel) {
            return Month.g(parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Month[] newArray(int i) {
            return new Month[i];
        }
    }

    public Month(Calendar calendar) {
        calendar.set(5, 1);
        Calendar calendarD = ni.d(calendar);
        this.b = calendarD;
        this.d = calendarD.get(2);
        this.e = calendarD.get(1);
        this.f = calendarD.getMaximum(7);
        this.g = calendarD.getActualMaximum(5);
        this.c = ni.o().format(calendarD.getTime());
        this.h = calendarD.getTimeInMillis();
    }

    public static Month g(int i, int i2) {
        Calendar calendarL = ni.l();
        calendarL.set(1, i);
        calendarL.set(2, i2);
        return new Month(calendarL);
    }

    public static Month h() {
        return new Month(ni.j());
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
        return this.d == month.d && this.e == month.e;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public int compareTo(Month month) {
        return this.b.compareTo(month.b);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.d), Integer.valueOf(this.e)});
    }

    public int i() {
        int firstDayOfWeek = this.b.get(7) - this.b.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f : firstDayOfWeek;
    }

    public long j(int i) {
        Calendar calendarD = ni.d(this.b);
        calendarD.set(5, i);
        return calendarD.getTimeInMillis();
    }

    public String k() {
        return this.c;
    }

    public long l() {
        return this.b.getTimeInMillis();
    }

    public Month m(int i) {
        Calendar calendarD = ni.d(this.b);
        calendarD.add(2, i);
        return new Month(calendarD);
    }

    public int n(Month month) {
        if (this.b instanceof GregorianCalendar) {
            return ((month.e - this.e) * 12) + (month.d - this.d);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.e);
        parcel.writeInt(this.d);
    }
}
