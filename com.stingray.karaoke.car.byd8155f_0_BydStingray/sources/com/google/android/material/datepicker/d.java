package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class d implements Comparable, Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new android.support.v4.media.a(3);
    public final Calendar l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f12844m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f12845n;
    public final int o;

    public d(Calendar calendar) {
        calendar.set(5, 1);
        Calendar calendarA = g.a(calendar);
        this.l = calendarA;
        this.f12844m = calendarA.get(2);
        this.f12845n = calendarA.get(1);
        this.o = calendarA.getMaximum(7);
        calendarA.getActualMaximum(5);
        calendarA.getTimeInMillis();
    }

    public final int a() {
        Calendar calendar = this.l;
        int firstDayOfWeek = calendar.get(7) - calendar.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.o : firstDayOfWeek;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.l.compareTo(((d) obj).l);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return this.f12844m == dVar.f12844m && this.f12845n == dVar.f12845n;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f12844m), Integer.valueOf(this.f12845n)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f12845n);
        parcel.writeInt(this.f12844m);
    }
}
