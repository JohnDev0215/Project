package com.google.android.material.datepicker;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Month f3905b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Month f3906c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Month f3907d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final DateValidator f3908e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f3909f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f3910g;

    public interface DateValidator extends Parcelable {
        boolean i(long j);
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
        public CalendarConstraints[] newArray(int i2) {
            return new CalendarConstraints[i2];
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final long f3911a = p.a(Month.c(1900, 0).f3928h);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final long f3912b = p.a(Month.c(2100, 11).f3928h);

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private long f3913c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f3914d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Long f3915e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private DateValidator f3916f;

        b(CalendarConstraints calendarConstraints) {
            this.f3913c = f3911a;
            this.f3914d = f3912b;
            this.f3916f = DateValidatorPointForward.a(Long.MIN_VALUE);
            this.f3913c = calendarConstraints.f3905b.f3928h;
            this.f3914d = calendarConstraints.f3906c.f3928h;
            this.f3915e = Long.valueOf(calendarConstraints.f3907d.f3928h);
            this.f3916f = calendarConstraints.f3908e;
        }

        public CalendarConstraints a() {
            if (this.f3915e == null) {
                long jQ1 = g.Q1();
                long j = this.f3913c;
                if (j > jQ1 || jQ1 > this.f3914d) {
                    jQ1 = j;
                }
                this.f3915e = Long.valueOf(jQ1);
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("DEEP_COPY_VALIDATOR_KEY", this.f3916f);
            return new CalendarConstraints(Month.d(this.f3913c), Month.d(this.f3914d), Month.d(this.f3915e.longValue()), (DateValidator) bundle.getParcelable("DEEP_COPY_VALIDATOR_KEY"), null);
        }

        public b b(long j) {
            this.f3915e = Long.valueOf(j);
            return this;
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
        return this.f3905b.equals(calendarConstraints.f3905b) && this.f3906c.equals(calendarConstraints.f3906c) && this.f3907d.equals(calendarConstraints.f3907d) && this.f3908e.equals(calendarConstraints.f3908e);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f3905b, this.f3906c, this.f3907d, this.f3908e});
    }

    Month j(Month month) {
        return month.compareTo(this.f3905b) < 0 ? this.f3905b : month.compareTo(this.f3906c) > 0 ? this.f3906c : month;
    }

    public DateValidator l() {
        return this.f3908e;
    }

    Month p() {
        return this.f3906c;
    }

    int q() {
        return this.f3910g;
    }

    Month r() {
        return this.f3907d;
    }

    Month s() {
        return this.f3905b;
    }

    int t() {
        return this.f3909f;
    }

    boolean u(long j) {
        if (this.f3905b.l(1) <= j) {
            Month month = this.f3906c;
            if (j <= month.l(month.f3927g)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f3905b, 0);
        parcel.writeParcelable(this.f3906c, 0);
        parcel.writeParcelable(this.f3907d, 0);
        parcel.writeParcelable(this.f3908e, 0);
    }

    private CalendarConstraints(Month month, Month month2, Month month3, DateValidator dateValidator) {
        this.f3905b = month;
        this.f3906c = month2;
        this.f3907d = month3;
        this.f3908e = dateValidator;
        if (month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (month3.compareTo(month2) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        this.f3910g = month.s(month2) + 1;
        this.f3909f = (month2.f3925e - month.f3925e) + 1;
    }
}
