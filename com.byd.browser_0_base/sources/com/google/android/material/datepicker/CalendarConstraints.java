package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.qk3;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new a();
    public final Month e;
    public final Month f;
    public final DateValidator g;
    public final Month h;
    public final int i;
    public final int j;
    public final int k;

    public interface DateValidator extends Parcelable {
        boolean P(long j);
    }

    public class a implements Parcelable.Creator<CalendarConstraints> {
        @Override // android.os.Parcelable.Creator
        public final CalendarConstraints createFromParcel(Parcel parcel) {
            return new CalendarConstraints((Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (DateValidator) parcel.readParcelable(DateValidator.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        public final CalendarConstraints[] newArray(int i) {
            return new CalendarConstraints[i];
        }
    }

    public static final class b {
        public static final long f = qk3.a(Month.a(1900, 0).j);
        public static final long g = qk3.a(Month.a(2100, 11).j);
        public final long a;
        public final long b;
        public Long c;
        public final int d;
        public final DateValidator e;

        public b(CalendarConstraints calendarConstraints) {
            this.a = f;
            this.b = g;
            this.e = new DateValidatorPointForward(Long.MIN_VALUE);
            this.a = calendarConstraints.e.j;
            this.b = calendarConstraints.f.j;
            this.c = Long.valueOf(calendarConstraints.h.j);
            this.d = calendarConstraints.i;
            this.e = calendarConstraints.g;
        }
    }

    public CalendarConstraints(Month month, Month month2, DateValidator dateValidator, Month month3, int i) {
        this.e = month;
        this.f = month2;
        this.h = month3;
        this.i = i;
        this.g = dateValidator;
        if (month3 != null && month.e.compareTo(month3.e) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (month3 != null && month3.e.compareTo(month2.e) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        if (i < 0 || i > qk3.d(null).getMaximum(7)) {
            throw new IllegalArgumentException("firstDayOfWeek is not valid");
        }
        if (!(month.e instanceof GregorianCalendar)) {
            throw new IllegalArgumentException("Only Gregorian calendars are supported.");
        }
        int i2 = month2.g;
        int i3 = month.g;
        this.k = (month2.f - month.f) + ((i2 - i3) * 12) + 1;
        this.j = (i2 - i3) + 1;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CalendarConstraints)) {
            return false;
        }
        CalendarConstraints calendarConstraints = (CalendarConstraints) obj;
        return this.e.equals(calendarConstraints.e) && this.f.equals(calendarConstraints.f) && Objects.equals(this.h, calendarConstraints.h) && this.i == calendarConstraints.i && this.g.equals(calendarConstraints.g);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.e, this.f, this.h, Integer.valueOf(this.i), this.g});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.e, 0);
        parcel.writeParcelable(this.f, 0);
        parcel.writeParcelable(this.h, 0);
        parcel.writeParcelable(this.g, 0);
        parcel.writeInt(this.i);
    }
}
