package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Month f3807d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Month f3808e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Month f3809f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final DateValidator f3810g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f3811h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f3812i;

    public interface DateValidator extends Parcelable {
        boolean b(long j8);
    }

    public static class a implements Parcelable.Creator<CalendarConstraints> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public CalendarConstraints createFromParcel(Parcel parcel) {
            return new CalendarConstraints((Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (DateValidator) parcel.readParcelable(DateValidator.class.getClassLoader()), null);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public CalendarConstraints[] newArray(int i8) {
            return new CalendarConstraints[i8];
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
        return this.f3807d.equals(calendarConstraints.f3807d) && this.f3808e.equals(calendarConstraints.f3808e) && this.f3809f.equals(calendarConstraints.f3809f) && this.f3810g.equals(calendarConstraints.f3810g);
    }

    public Month f(Month month) {
        return month.compareTo(this.f3807d) < 0 ? this.f3807d : month.compareTo(this.f3808e) > 0 ? this.f3808e : month;
    }

    public DateValidator g() {
        return this.f3810g;
    }

    public Month h() {
        return this.f3808e;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f3807d, this.f3808e, this.f3809f, this.f3810g});
    }

    public int i() {
        return this.f3812i;
    }

    public Month j() {
        return this.f3809f;
    }

    public Month k() {
        return this.f3807d;
    }

    public int l() {
        return this.f3811h;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeParcelable(this.f3807d, 0);
        parcel.writeParcelable(this.f3808e, 0);
        parcel.writeParcelable(this.f3809f, 0);
        parcel.writeParcelable(this.f3810g, 0);
    }

    public CalendarConstraints(Month month, Month month2, Month month3, DateValidator dateValidator) {
        this.f3807d = month;
        this.f3808e = month2;
        this.f3809f = month3;
        this.f3810g = dateValidator;
        if (month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (month3.compareTo(month2) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        this.f3812i = month.n(month2) + 1;
        this.f3811h = (month2.f3853g - month.f3853g) + 1;
    }
}
