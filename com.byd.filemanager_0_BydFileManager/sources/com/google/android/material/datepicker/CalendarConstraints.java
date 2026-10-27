package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Month f4150b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Month f4151c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Month f4152d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final DateValidator f4153e;
    private final int f;
    private final int g;

    public interface DateValidator extends Parcelable {
        boolean j(long j);
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

    int A() {
        return this.f;
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
        return this.f4150b.equals(calendarConstraints.f4150b) && this.f4151c.equals(calendarConstraints.f4151c) && this.f4152d.equals(calendarConstraints.f4152d) && this.f4153e.equals(calendarConstraints.f4153e);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4150b, this.f4151c, this.f4152d, this.f4153e});
    }

    Month u(Month month) {
        return month.compareTo(this.f4150b) < 0 ? this.f4150b : month.compareTo(this.f4151c) > 0 ? this.f4151c : month;
    }

    public DateValidator v() {
        return this.f4153e;
    }

    Month w() {
        return this.f4151c;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f4150b, 0);
        parcel.writeParcelable(this.f4151c, 0);
        parcel.writeParcelable(this.f4152d, 0);
        parcel.writeParcelable(this.f4153e, 0);
    }

    int x() {
        return this.g;
    }

    Month y() {
        return this.f4152d;
    }

    Month z() {
        return this.f4150b;
    }

    private CalendarConstraints(Month month, Month month2, Month month3, DateValidator dateValidator) {
        this.f4150b = month;
        this.f4151c = month2;
        this.f4152d = month3;
        this.f4153e = dateValidator;
        if (month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (month3.compareTo(month2) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        this.g = month.C(month2) + 1;
        this.f = (month2.f4179e - month.f4179e) + 1;
    }
}
