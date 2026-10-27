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
    private final Calendar f3780b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f3781c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f3782d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f3783e;
    final int f;
    final int g;
    final long h;

    static class a implements Parcelable.Creator<Month> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Month createFromParcel(Parcel parcel) {
            return Month.j(parcel.readInt(), parcel.readInt());
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
        this.f3780b = calendarD;
        this.f3782d = calendarD.get(2);
        this.f3783e = calendarD.get(1);
        this.f = calendarD.getMaximum(7);
        this.g = calendarD.getActualMaximum(5);
        this.f3781c = l.o().format(calendarD.getTime());
        this.h = calendarD.getTimeInMillis();
    }

    static Month j(int i, int i2) {
        Calendar calendarL = l.l();
        calendarL.set(1, i);
        calendarL.set(2, i2);
        return new Month(calendarL);
    }

    static Month k() {
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
        return this.f3782d == month.f3782d && this.f3783e == month.f3783e;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f3782d), Integer.valueOf(this.f3783e)});
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public int compareTo(Month month) {
        return this.f3780b.compareTo(month.f3780b);
    }

    int l() {
        int firstDayOfWeek = this.f3780b.get(7) - this.f3780b.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f : firstDayOfWeek;
    }

    long m(int i) {
        Calendar calendarD = l.d(this.f3780b);
        calendarD.set(5, i);
        return calendarD.getTimeInMillis();
    }

    String n() {
        return this.f3781c;
    }

    long o() {
        return this.f3780b.getTimeInMillis();
    }

    Month p(int i) {
        Calendar calendarD = l.d(this.f3780b);
        calendarD.add(2, i);
        return new Month(calendarD);
    }

    int q(Month month) {
        if (this.f3780b instanceof GregorianCalendar) {
            return ((month.f3783e - this.f3783e) * 12) + (month.f3782d - this.f3782d);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3783e);
        parcel.writeInt(this.f3782d);
    }
}
