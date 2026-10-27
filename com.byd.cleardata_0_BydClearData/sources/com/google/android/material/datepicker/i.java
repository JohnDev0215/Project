package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* JADX INFO: loaded from: classes.dex */
final class i implements Comparable<i>, Parcelable {
    public static final Parcelable.Creator<i> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Calendar f3068b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f3069c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f3070d;
    final int e;
    final int f;
    final int g;
    final long h;

    static class a implements Parcelable.Creator<i> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public i createFromParcel(Parcel parcel) {
            return i.v(parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public i[] newArray(int i) {
            return new i[i];
        }
    }

    private i(Calendar calendar) {
        calendar.set(5, 1);
        Calendar calendarD = p.d(calendar);
        this.f3068b = calendarD;
        this.f3070d = calendarD.get(2);
        this.e = calendarD.get(1);
        this.f = calendarD.getMaximum(7);
        this.g = calendarD.getActualMaximum(5);
        this.f3069c = p.o().format(calendarD.getTime());
        this.h = calendarD.getTimeInMillis();
    }

    static i v(int i, int i2) {
        Calendar calendarL = p.l();
        calendarL.set(1, i);
        calendarL.set(2, i2);
        return new i(calendarL);
    }

    static i w() {
        return new i(p.j());
    }

    long A() {
        return this.f3068b.getTimeInMillis();
    }

    i B(int i) {
        Calendar calendarD = p.d(this.f3068b);
        calendarD.add(2, i);
        return new i(calendarD);
    }

    int C(i iVar) {
        if (this.f3068b instanceof GregorianCalendar) {
            return ((iVar.e - this.e) * 12) + (iVar.f3070d - this.f3070d);
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
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return this.f3070d == iVar.f3070d && this.e == iVar.e;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f3070d), Integer.valueOf(this.e)});
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
    public int compareTo(i iVar) {
        return this.f3068b.compareTo(iVar.f3068b);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.e);
        parcel.writeInt(this.f3070d);
    }

    int x() {
        int firstDayOfWeek = this.f3068b.get(7) - this.f3068b.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f : firstDayOfWeek;
    }

    long y(int i) {
        Calendar calendarD = p.d(this.f3068b);
        calendarD.set(5, i);
        return calendarD.getTimeInMillis();
    }

    String z() {
        return this.f3069c;
    }
}
