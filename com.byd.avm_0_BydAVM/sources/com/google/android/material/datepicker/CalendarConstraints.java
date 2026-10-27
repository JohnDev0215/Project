package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Month f2287b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Month f2288c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Month f2289d;
    private final DateValidator e;
    private final int f;
    private final int g;

    public interface DateValidator extends Parcelable {
        boolean c(long j);
    }

    static class a implements Parcelable.Creator<CalendarConstraints> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public CalendarConstraints createFromParcel(Parcel parcel) {
            return new CalendarConstraints((Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (DateValidator) parcel.readParcelable(DateValidator.class.getClassLoader()), null);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public CalendarConstraints[] newArray(int i) {
            return new CalendarConstraints[i];
        }
    }

    /* synthetic */ CalendarConstraints(Month month, Month month2, Month month3, DateValidator dateValidator, a aVar) {
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
        return this.f2287b.equals(calendarConstraints.f2287b) && this.f2288c.equals(calendarConstraints.f2288c) && this.f2289d.equals(calendarConstraints.f2289d) && this.e.equals(calendarConstraints.e);
    }

    Month f(Month month) {
        return month.compareTo(this.f2287b) < 0 ? this.f2287b : month.compareTo(this.f2288c) > 0 ? this.f2288c : month;
    }

    public DateValidator g() {
        return this.e;
    }

    Month h() {
        return this.f2288c;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f2287b, this.f2288c, this.f2289d, this.e});
    }

    int i() {
        return this.g;
    }

    Month j() {
        return this.f2289d;
    }

    Month k() {
        return this.f2287b;
    }

    int l() {
        return this.f;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f2287b, 0);
        parcel.writeParcelable(this.f2288c, 0);
        parcel.writeParcelable(this.f2289d, 0);
        parcel.writeParcelable(this.e, 0);
    }

    private CalendarConstraints(Month month, Month month2, Month month3, DateValidator dateValidator) {
        this.f2287b = month;
        this.f2288c = month2;
        this.f2289d = month3;
        this.e = dateValidator;
        if (month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (month3.compareTo(month2) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        this.g = month.n(month2) + 1;
        this.f = (month2.e - month.e) + 1;
    }
}
