package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Month f2224b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Month f2225c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Month f2226d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final DateValidator f2227e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2228f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f2229g;

    public interface DateValidator extends Parcelable {
        boolean c(long j3);
    }

    public static class a implements Parcelable.Creator<CalendarConstraints> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public CalendarConstraints createFromParcel(Parcel parcel) {
            return new CalendarConstraints((Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (DateValidator) parcel.readParcelable(DateValidator.class.getClassLoader()), null);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public CalendarConstraints[] newArray(int i3) {
            return new CalendarConstraints[i3];
        }
    }

    public /* synthetic */ CalendarConstraints(Month month, Month month2, Month month3, DateValidator dateValidator, a aVar) {
        this(month, month2, month3, dateValidator);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CalendarConstraints)) {
            return false;
        }
        CalendarConstraints calendarConstraints = (CalendarConstraints) obj;
        return this.f2224b.equals(calendarConstraints.f2224b) && this.f2225c.equals(calendarConstraints.f2225c) && this.f2226d.equals(calendarConstraints.f2226d) && this.f2227e.equals(calendarConstraints.f2227e);
    }

    public Month f(Month month) {
        return month.compareTo(this.f2224b) < 0 ? this.f2224b : month.compareTo(this.f2225c) > 0 ? this.f2225c : month;
    }

    public DateValidator g() {
        return this.f2227e;
    }

    public Month h() {
        return this.f2225c;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f2224b, this.f2225c, this.f2226d, this.f2227e});
    }

    public int i() {
        return this.f2229g;
    }

    public Month j() {
        return this.f2226d;
    }

    public Month k() {
        return this.f2224b;
    }

    public int l() {
        return this.f2228f;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeParcelable(this.f2224b, 0);
        parcel.writeParcelable(this.f2225c, 0);
        parcel.writeParcelable(this.f2226d, 0);
        parcel.writeParcelable(this.f2227e, 0);
    }

    public CalendarConstraints(Month month, Month month2, Month month3, DateValidator dateValidator) {
        this.f2224b = month;
        this.f2225c = month2;
        this.f2226d = month3;
        this.f2227e = dateValidator;
        if (month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (month3.compareTo(month2) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        this.f2229g = month.n(month2) + 1;
        this.f2228f = (month2.f2268e - month.f2268e) + 1;
    }
}
