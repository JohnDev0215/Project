package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Month f3486b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Month f3487c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Month f3488d;
    private final DateValidator e;
    private final int f;
    private final int g;

    public interface DateValidator extends Parcelable {
        boolean b(long j);
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
        return this.f3486b.equals(calendarConstraints.f3486b) && this.f3487c.equals(calendarConstraints.f3487c) && this.f3488d.equals(calendarConstraints.f3488d) && this.e.equals(calendarConstraints.e);
    }

    Month f(Month month) {
        return month.compareTo(this.f3486b) < 0 ? this.f3486b : month.compareTo(this.f3487c) > 0 ? this.f3487c : month;
    }

    public DateValidator g() {
        return this.e;
    }

    Month h() {
        return this.f3487c;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f3486b, this.f3487c, this.f3488d, this.e});
    }

    int i() {
        return this.g;
    }

    Month j() {
        return this.f3488d;
    }

    Month k() {
        return this.f3486b;
    }

    int l() {
        return this.f;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3486b, 0);
        parcel.writeParcelable(this.f3487c, 0);
        parcel.writeParcelable(this.f3488d, 0);
        parcel.writeParcelable(this.e, 0);
    }

    private CalendarConstraints(Month month, Month month2, Month month3, DateValidator dateValidator) {
        this.f3486b = month;
        this.f3487c = month2;
        this.f3488d = month3;
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
