package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* JADX INFO: loaded from: classes.dex */
final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Calendar f4383e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final int f4384g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final int f4385h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final int f4386i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    final int f4387j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    final long f4388k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private String f4389l;

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
        public Month[] newArray(int i7) {
            return new Month[i7];
        }
    }

    private Month(Calendar calendar) {
        calendar.set(5, 1);
        Calendar calendarE = p.e(calendar);
        this.f4383e = calendarE;
        this.f4384g = calendarE.get(2);
        this.f4385h = calendarE.get(1);
        this.f4386i = calendarE.getMaximum(7);
        this.f4387j = calendarE.getActualMaximum(5);
        this.f4388k = calendarE.getTimeInMillis();
    }

    static Month c(int i7, int i8) {
        Calendar calendarL = p.l();
        calendarL.set(1, i7);
        calendarL.set(2, i8);
        return new Month(calendarL);
    }

    static Month h(long j7) {
        Calendar calendarL = p.l();
        calendarL.setTimeInMillis(j7);
        return new Month(calendarL);
    }

    static Month j() {
        return new Month(p.j());
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(Month month) {
        return this.f4383e.compareTo(month.f4383e);
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
        return this.f4384g == month.f4384g && this.f4385h == month.f4385h;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f4384g), Integer.valueOf(this.f4385h)});
    }

    int k() {
        int firstDayOfWeek = this.f4383e.get(7) - this.f4383e.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f4386i : firstDayOfWeek;
    }

    long m(int i7) {
        Calendar calendarE = p.e(this.f4383e);
        calendarE.set(5, i7);
        return calendarE.getTimeInMillis();
    }

    int p(long j7) {
        Calendar calendarE = p.e(this.f4383e);
        calendarE.setTimeInMillis(j7);
        return calendarE.get(5);
    }

    String q(Context context) {
        if (this.f4389l == null) {
            this.f4389l = d.i(context, this.f4383e.getTimeInMillis());
        }
        return this.f4389l;
    }

    long r() {
        return this.f4383e.getTimeInMillis();
    }

    Month s(int i7) {
        Calendar calendarE = p.e(this.f4383e);
        calendarE.add(2, i7);
        return new Month(calendarE);
    }

    int t(Month month) {
        if (this.f4383e instanceof GregorianCalendar) {
            return ((month.f4385h - this.f4385h) * 12) + (month.f4384g - this.f4384g);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f4385h);
        parcel.writeInt(this.f4384g);
    }
}
