package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import d2.l;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* JADX INFO: loaded from: classes.dex */
public final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Calendar f2265b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f2266c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2267d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2268e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2269f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f2270g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f2271h;

    public static class a implements Parcelable.Creator<Month> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Month createFromParcel(Parcel parcel) {
            return Month.g(parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Month[] newArray(int i3) {
            return new Month[i3];
        }
    }

    public Month(Calendar calendar) {
        calendar.set(5, 1);
        Calendar calendarD = l.d(calendar);
        this.f2265b = calendarD;
        this.f2267d = calendarD.get(2);
        this.f2268e = calendarD.get(1);
        this.f2269f = calendarD.getMaximum(7);
        this.f2270g = calendarD.getActualMaximum(5);
        this.f2266c = l.o().format(calendarD.getTime());
        this.f2271h = calendarD.getTimeInMillis();
    }

    public static Month g(int i3, int i4) {
        Calendar calendarL = l.l();
        calendarL.set(1, i3);
        calendarL.set(2, i4);
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
        return this.f2267d == month.f2267d && this.f2268e == month.f2268e;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public int compareTo(Month month) {
        return this.f2265b.compareTo(month.f2265b);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f2267d), Integer.valueOf(this.f2268e)});
    }

    public int i() {
        int firstDayOfWeek = this.f2265b.get(7) - this.f2265b.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f2269f : firstDayOfWeek;
    }

    public long j(int i3) {
        Calendar calendarD = l.d(this.f2265b);
        calendarD.set(5, i3);
        return calendarD.getTimeInMillis();
    }

    public String k() {
        return this.f2266c;
    }

    public long l() {
        return this.f2265b.getTimeInMillis();
    }

    public Month m(int i3) {
        Calendar calendarD = l.d(this.f2265b);
        calendarD.add(2, i3);
        return new Month(calendarD);
    }

    public int n(Month month) {
        if (this.f2265b instanceof GregorianCalendar) {
            return ((month.f2268e - this.f2268e) * 12) + (month.f2267d - this.f2267d);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeInt(this.f2268e);
        parcel.writeInt(this.f2267d);
    }
}
