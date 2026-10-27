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
    private final Calendar f3544b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f3545c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f3546d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f3547e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final int f3548f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final int f3549g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final long f3550h;

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
        this.f3544b = calendarD;
        this.f3546d = calendarD.get(2);
        this.f3547e = calendarD.get(1);
        this.f3548f = calendarD.getMaximum(7);
        this.f3549g = calendarD.getActualMaximum(5);
        this.f3545c = l.o().format(calendarD.getTime());
        this.f3550h = calendarD.getTimeInMillis();
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

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public int compareTo(Month month) {
        return this.f3544b.compareTo(month.f3544b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Month)) {
            return false;
        }
        Month month = (Month) obj;
        return this.f3546d == month.f3546d && this.f3547e == month.f3547e;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f3546d), Integer.valueOf(this.f3547e)});
    }

    int i() {
        int firstDayOfWeek = this.f3544b.get(7) - this.f3544b.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f3548f : firstDayOfWeek;
    }

    long j(int i) {
        Calendar calendarD = l.d(this.f3544b);
        calendarD.set(5, i);
        return calendarD.getTimeInMillis();
    }

    String k() {
        return this.f3545c;
    }

    long l() {
        return this.f3544b.getTimeInMillis();
    }

    Month m(int i) {
        Calendar calendarD = l.d(this.f3544b);
        calendarD.add(2, i);
        return new Month(calendarD);
    }

    int n(Month month) {
        if (this.f3544b instanceof GregorianCalendar) {
            return ((month.f3547e - this.f3547e) * 12) + (month.f3546d - this.f3546d);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3547e);
        parcel.writeInt(this.f3546d);
    }
}
