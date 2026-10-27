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
    private final Calendar f4176b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f4177c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f4178d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f4179e;
    final int f;
    final int g;
    final long h;

    static class a implements Parcelable.Creator<Month> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Month createFromParcel(Parcel parcel) {
            return Month.v(parcel.readInt(), parcel.readInt());
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
        this.f4176b = calendarD;
        this.f4178d = calendarD.get(2);
        this.f4179e = calendarD.get(1);
        this.f = calendarD.getMaximum(7);
        this.g = calendarD.getActualMaximum(5);
        this.f4177c = l.o().format(calendarD.getTime());
        this.h = calendarD.getTimeInMillis();
    }

    static Month v(int i, int i2) {
        Calendar calendarL = l.l();
        calendarL.set(1, i);
        calendarL.set(2, i2);
        return new Month(calendarL);
    }

    static Month w() {
        return new Month(l.j());
    }

    long A() {
        return this.f4176b.getTimeInMillis();
    }

    Month B(int i) {
        Calendar calendarD = l.d(this.f4176b);
        calendarD.add(2, i);
        return new Month(calendarD);
    }

    int C(Month month) {
        if (this.f4176b instanceof GregorianCalendar) {
            return ((month.f4179e - this.f4179e) * 12) + (month.f4178d - this.f4178d);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
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
        return this.f4178d == month.f4178d && this.f4179e == month.f4179e;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f4178d), Integer.valueOf(this.f4179e)});
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
    public int compareTo(Month month) {
        return this.f4176b.compareTo(month.f4176b);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f4179e);
        parcel.writeInt(this.f4178d);
    }

    int x() {
        int firstDayOfWeek = this.f4176b.get(7) - this.f4176b.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f : firstDayOfWeek;
    }

    long y(int i) {
        Calendar calendarD = l.d(this.f4176b);
        calendarD.set(5, i);
        return calendarD.getTimeInMillis();
    }

    String z() {
        return this.f4177c;
    }
}
