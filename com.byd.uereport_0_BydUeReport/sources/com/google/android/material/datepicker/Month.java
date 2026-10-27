package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import u5.l;

/* JADX INFO: loaded from: classes2.dex */
public final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Calendar f3850d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f3851e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f3852f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f3853g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f3854h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f3855i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f3856j;

    public static class a implements Parcelable.Creator<Month> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Month createFromParcel(Parcel parcel) {
            return Month.g(parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Month[] newArray(int i8) {
            return new Month[i8];
        }
    }

    public Month(Calendar calendar) {
        calendar.set(5, 1);
        Calendar calendarD = l.d(calendar);
        this.f3850d = calendarD;
        this.f3852f = calendarD.get(2);
        this.f3853g = calendarD.get(1);
        this.f3854h = calendarD.getMaximum(7);
        this.f3855i = calendarD.getActualMaximum(5);
        this.f3851e = l.o().format(calendarD.getTime());
        this.f3856j = calendarD.getTimeInMillis();
    }

    public static Month g(int i8, int i9) {
        Calendar calendarL = l.l();
        calendarL.set(1, i8);
        calendarL.set(2, i9);
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
        return this.f3852f == month.f3852f && this.f3853g == month.f3853g;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public int compareTo(Month month) {
        return this.f3850d.compareTo(month.f3850d);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f3852f), Integer.valueOf(this.f3853g)});
    }

    public int i() {
        int firstDayOfWeek = this.f3850d.get(7) - this.f3850d.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f3854h : firstDayOfWeek;
    }

    public long j(int i8) {
        Calendar calendarD = l.d(this.f3850d);
        calendarD.set(5, i8);
        return calendarD.getTimeInMillis();
    }

    public String k() {
        return this.f3851e;
    }

    public long l() {
        return this.f3850d.getTimeInMillis();
    }

    public Month m(int i8) {
        Calendar calendarD = l.d(this.f3850d);
        calendarD.add(2, i8);
        return new Month(calendarD);
    }

    public int n(Month month) {
        if (this.f3850d instanceof GregorianCalendar) {
            return ((month.f3853g - this.f3853g) * 12) + (month.f3852f - this.f3852f);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeInt(this.f3853g);
        parcel.writeInt(this.f3852f);
    }
}
