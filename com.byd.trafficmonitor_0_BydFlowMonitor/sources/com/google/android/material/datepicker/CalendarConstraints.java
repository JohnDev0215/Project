package com.google.android.material.datepicker;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import c.b.a.a.x.f;
import c.b.a.a.x.o;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Month f4654b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Month f4655c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Month f4656d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final DateValidator f4657e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f4658f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f4659g;

    public interface DateValidator extends Parcelable {
        boolean e(long j);
    }

    public static class a implements Parcelable.Creator<CalendarConstraints> {
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
        public static final long f4660a = o.a(Month.k(1900, 0).f4701h);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final long f4661b = o.a(Month.k(2100, 11).f4701h);

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f4662c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f4663d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Long f4664e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public DateValidator f4665f;

        public b(CalendarConstraints calendarConstraints) {
            this.f4662c = f4660a;
            this.f4663d = f4661b;
            this.f4665f = DateValidatorPointForward.j(Long.MIN_VALUE);
            this.f4662c = calendarConstraints.f4654b.f4701h;
            this.f4663d = calendarConstraints.f4655c.f4701h;
            this.f4664e = Long.valueOf(calendarConstraints.f4656d.f4701h);
            this.f4665f = calendarConstraints.f4657e;
        }

        public CalendarConstraints a() {
            if (this.f4664e == null) {
                long jH = f.H();
                long j = this.f4662c;
                if (j > jH || jH > this.f4663d) {
                    jH = j;
                }
                this.f4664e = Long.valueOf(jH);
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("DEEP_COPY_VALIDATOR_KEY", this.f4665f);
            return new CalendarConstraints(Month.l(this.f4662c), Month.l(this.f4663d), Month.l(this.f4664e.longValue()), (DateValidator) bundle.getParcelable("DEEP_COPY_VALIDATOR_KEY"), null);
        }

        public b b(long j) {
            this.f4664e = Long.valueOf(j);
            return this;
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
        return this.f4654b.equals(calendarConstraints.f4654b) && this.f4655c.equals(calendarConstraints.f4655c) && this.f4656d.equals(calendarConstraints.f4656d) && this.f4657e.equals(calendarConstraints.f4657e);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4654b, this.f4655c, this.f4656d, this.f4657e});
    }

    public Month n(Month month) {
        return month.compareTo(this.f4654b) < 0 ? this.f4654b : month.compareTo(this.f4655c) > 0 ? this.f4655c : month;
    }

    public DateValidator o() {
        return this.f4657e;
    }

    public Month p() {
        return this.f4655c;
    }

    public int q() {
        return this.f4659g;
    }

    public Month r() {
        return this.f4656d;
    }

    public Month s() {
        return this.f4654b;
    }

    public int t() {
        return this.f4658f;
    }

    public boolean u(long j) {
        if (this.f4654b.o(1) <= j) {
            Month month = this.f4655c;
            if (j <= month.o(month.f4700g)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f4654b, 0);
        parcel.writeParcelable(this.f4655c, 0);
        parcel.writeParcelable(this.f4656d, 0);
        parcel.writeParcelable(this.f4657e, 0);
    }

    public CalendarConstraints(Month month, Month month2, Month month3, DateValidator dateValidator) {
        this.f4654b = month;
        this.f4655c = month2;
        this.f4656d = month3;
        this.f4657e = dateValidator;
        if (month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (month3.compareTo(month2) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        this.f4659g = month.s(month2) + 1;
        this.f4658f = (month2.f4698e - month.f4698e) + 1;
    }
}
