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
    private final Calendar f3922b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f3923c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f3924d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f3925e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final int f3926f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final int f3927g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final long f3928h;

    static class a implements Parcelable.Creator<Month> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Month createFromParcel(Parcel parcel) {
            return Month.c(parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Month[] newArray(int i2) {
            return new Month[i2];
        }
    }

    private Month(Calendar calendar) {
        calendar.set(5, 1);
        Calendar calendarF = p.f(calendar);
        this.f3922b = calendarF;
        this.f3924d = calendarF.get(2);
        this.f3925e = calendarF.get(1);
        this.f3926f = calendarF.getMaximum(7);
        this.f3927g = calendarF.getActualMaximum(5);
        this.f3923c = p.v().format(calendarF.getTime());
        this.f3928h = calendarF.getTimeInMillis();
    }

    static Month c(int i2, int i3) {
        Calendar calendarR = p.r();
        calendarR.set(1, i2);
        calendarR.set(2, i3);
        return new Month(calendarR);
    }

    static Month d(long j) {
        Calendar calendarR = p.r();
        calendarR.setTimeInMillis(j);
        return new Month(calendarR);
    }

    static Month f() {
        return new Month(p.p());
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(Month month) {
        return this.f3922b.compareTo(month.f3922b);
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
        return this.f3924d == month.f3924d && this.f3925e == month.f3925e;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f3924d), Integer.valueOf(this.f3925e)});
    }

    int j() {
        int firstDayOfWeek = this.f3922b.get(7) - this.f3922b.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f3926f : firstDayOfWeek;
    }

    long l(int i2) {
        Calendar calendarF = p.f(this.f3922b);
        calendarF.set(5, i2);
        return calendarF.getTimeInMillis();
    }

    String p() {
        return this.f3923c;
    }

    long q() {
        return this.f3922b.getTimeInMillis();
    }

    Month r(int i2) {
        Calendar calendarF = p.f(this.f3922b);
        calendarF.add(2, i2);
        return new Month(calendarF);
    }

    int s(Month month) {
        if (this.f3922b instanceof GregorianCalendar) {
            return ((month.f3925e - this.f3925e) * 12) + (month.f3924d - this.f3924d);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f3925e);
        parcel.writeInt(this.f3924d);
    }
}
