package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import c.b.a.a.l.l;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* JADX INFO: loaded from: classes.dex */
public final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Calendar f2166b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f2167c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2168d;
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
        Calendar calendarD = l.d(calendar);
        this.f2166b = calendarD;
        this.f2168d = calendarD.get(2);
        this.e = calendarD.get(1);
        this.f = calendarD.getMaximum(7);
        this.g = calendarD.getActualMaximum(5);
        this.f2167c = l.o().format(calendarD.getTime());
        this.h = calendarD.getTimeInMillis();
    }

    public static Month g(int i, int i2) {
        Calendar calendarL = l.l();
        calendarL.set(1, i);
        calendarL.set(2, i2);
        return new Month(calendarL);
    }

    public static Month h() {
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
        return this.f2168d == month.f2168d && this.e == month.e;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public int compareTo(Month month) {
        return this.f2166b.compareTo(month.f2166b);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f2168d), Integer.valueOf(this.e)});
    }

    public int i() {
        int firstDayOfWeek = this.f2166b.get(7) - this.f2166b.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f : firstDayOfWeek;
    }

    public long j(int i) {
        Calendar calendarD = l.d(this.f2166b);
        calendarD.set(5, i);
        return calendarD.getTimeInMillis();
    }

    public String k() {
        return this.f2167c;
    }

    public long l() {
        return this.f2166b.getTimeInMillis();
    }

    public Month m(int i) {
        Calendar calendarD = l.d(this.f2166b);
        calendarD.add(2, i);
        return new Month(calendarD);
    }

    public int n(Month month) {
        if (this.f2166b instanceof GregorianCalendar) {
            return ((month.e - this.e) * 12) + (month.f2168d - this.f2168d);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.e);
        parcel.writeInt(this.f2168d);
    }
}
