package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* JADX INFO: loaded from: classes.dex */
final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Calendar f2314b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f2315c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f2316d;
    final int e;
    final int f;
    final int g;
    final long h;

    static class a implements Parcelable.Creator<Month> {
        a() {
        }

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

    private Month(Calendar calendar) {
        calendar.set(5, 1);
        Calendar calendarD = l.d(calendar);
        this.f2314b = calendarD;
        this.f2316d = calendarD.get(2);
        this.e = calendarD.get(1);
        this.f = calendarD.getMaximum(7);
        this.g = calendarD.getActualMaximum(5);
        this.f2315c = l.o().format(calendarD.getTime());
        this.h = calendarD.getTimeInMillis();
    }

    static Month g(int i, int i2) {
        Calendar calendarL = l.l();
        calendarL.set(1, i);
        calendarL.set(2, i2);
        return new Month(calendarL);
    }

    static Month h() {
        return new Month(l.j());
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
        return this.f2316d == month.f2316d && this.e == month.e;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public int compareTo(Month month) {
        return this.f2314b.compareTo(month.f2314b);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f2316d), Integer.valueOf(this.e)});
    }

    int i() {
        int firstDayOfWeek = this.f2314b.get(7) - this.f2314b.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f : firstDayOfWeek;
    }

    long j(int i) {
        Calendar calendarD = l.d(this.f2314b);
        calendarD.set(5, i);
        return calendarD.getTimeInMillis();
    }

    String k() {
        return this.f2315c;
    }

    long l() {
        return this.f2314b.getTimeInMillis();
    }

    Month m(int i) {
        Calendar calendarD = l.d(this.f2314b);
        calendarD.add(2, i);
        return new Month(calendarD);
    }

    int n(Month month) {
        if (this.f2314b instanceof GregorianCalendar) {
            return ((month.e - this.e) * 12) + (month.f2316d - this.f2316d);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.e);
        parcel.writeInt(this.f2316d);
    }
}
