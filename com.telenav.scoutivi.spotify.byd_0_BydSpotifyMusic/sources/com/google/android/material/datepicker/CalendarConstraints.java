package com.google.android.material.datepicker;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Month f4362e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Month f4363g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final DateValidator f4364h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Month f4365i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final int f4366j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final int f4367k;

    public interface DateValidator extends Parcelable {
        boolean g(long j7);
    }

    static class a implements Parcelable.Creator<CalendarConstraints> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public CalendarConstraints createFromParcel(Parcel parcel) {
            return new CalendarConstraints((Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (DateValidator) parcel.readParcelable(DateValidator.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), null);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public CalendarConstraints[] newArray(int i7) {
            return new CalendarConstraints[i7];
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        static final long f4368e = p.a(Month.c(1900, 0).f4388k);

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        static final long f4369f = p.a(Month.c(2100, 11).f4388k);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private long f4370a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private long f4371b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Long f4372c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private DateValidator f4373d;

        b(CalendarConstraints calendarConstraints) {
            this.f4370a = f4368e;
            this.f4371b = f4369f;
            this.f4373d = DateValidatorPointForward.b(Long.MIN_VALUE);
            this.f4370a = calendarConstraints.f4362e.f4388k;
            this.f4371b = calendarConstraints.f4363g.f4388k;
            this.f4372c = Long.valueOf(calendarConstraints.f4365i.f4388k);
            this.f4373d = calendarConstraints.f4364h;
        }

        public CalendarConstraints a() {
            Bundle bundle = new Bundle();
            bundle.putParcelable("DEEP_COPY_VALIDATOR_KEY", this.f4373d);
            Month monthH = Month.h(this.f4370a);
            Month monthH2 = Month.h(this.f4371b);
            DateValidator dateValidator = (DateValidator) bundle.getParcelable("DEEP_COPY_VALIDATOR_KEY");
            Long l7 = this.f4372c;
            return new CalendarConstraints(monthH, monthH2, dateValidator, l7 == null ? null : Month.h(l7.longValue()), null);
        }

        public b b(long j7) {
            this.f4372c = Long.valueOf(j7);
            return this;
        }
    }

    /* synthetic */ CalendarConstraints(Month month, Month month2, DateValidator dateValidator, Month month3, a aVar) {
        this(month, month2, dateValidator, month3);
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
        return this.f4362e.equals(calendarConstraints.f4362e) && this.f4363g.equals(calendarConstraints.f4363g) && h0.c.a(this.f4365i, calendarConstraints.f4365i) && this.f4364h.equals(calendarConstraints.f4364h);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4362e, this.f4363g, this.f4365i, this.f4364h});
    }

    Month k(Month month) {
        return month.compareTo(this.f4362e) < 0 ? this.f4362e : month.compareTo(this.f4363g) > 0 ? this.f4363g : month;
    }

    public DateValidator m() {
        return this.f4364h;
    }

    Month p() {
        return this.f4363g;
    }

    int q() {
        return this.f4367k;
    }

    Month r() {
        return this.f4365i;
    }

    Month s() {
        return this.f4362e;
    }

    int t() {
        return this.f4366j;
    }

    boolean u(long j7) {
        if (this.f4362e.m(1) <= j7) {
            Month month = this.f4363g;
            if (j7 <= month.m(month.f4387j)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeParcelable(this.f4362e, 0);
        parcel.writeParcelable(this.f4363g, 0);
        parcel.writeParcelable(this.f4365i, 0);
        parcel.writeParcelable(this.f4364h, 0);
    }

    private CalendarConstraints(Month month, Month month2, DateValidator dateValidator, Month month3) {
        this.f4362e = month;
        this.f4363g = month2;
        this.f4365i = month3;
        this.f4364h = dateValidator;
        if (month3 != null && month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (month3 != null && month3.compareTo(month2) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        this.f4367k = month.t(month2) + 1;
        this.f4366j = (month2.f4385h - month.f4385h) + 1;
    }
}
