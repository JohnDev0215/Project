package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Month f3751b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Month f3752c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Month f3753d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final DateValidator f3754e;
    private final int f;
    private final int g;

    public interface DateValidator extends Parcelable {
        boolean e(long j);
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

    Month a(Month month) {
        return month.compareTo(this.f3751b) < 0 ? this.f3751b : month.compareTo(this.f3752c) > 0 ? this.f3752c : month;
    }

    public DateValidator c() {
        return this.f3754e;
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
        return this.f3751b.equals(calendarConstraints.f3751b) && this.f3752c.equals(calendarConstraints.f3752c) && this.f3753d.equals(calendarConstraints.f3753d) && this.f3754e.equals(calendarConstraints.f3754e);
    }

    Month f() {
        return this.f3752c;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f3751b, this.f3752c, this.f3753d, this.f3754e});
    }

    int i() {
        return this.g;
    }

    Month j() {
        return this.f3753d;
    }

    Month k() {
        return this.f3751b;
    }

    int l() {
        return this.f;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3751b, 0);
        parcel.writeParcelable(this.f3752c, 0);
        parcel.writeParcelable(this.f3753d, 0);
        parcel.writeParcelable(this.f3754e, 0);
    }

    private CalendarConstraints(Month month, Month month2, Month month3, DateValidator dateValidator) {
        this.f3751b = month;
        this.f3752c = month2;
        this.f3753d = month3;
        this.f3754e = dateValidator;
        if (month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (month3.compareTo(month2) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        this.g = month.q(month2) + 1;
        this.f = (month2.f3783e - month.f3783e) + 1;
    }
}
