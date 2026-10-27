package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Month f3510b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Month f3511c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Month f3512d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final DateValidator f3513e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f3514f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f3515g;

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

    Month e(Month month) {
        return month.compareTo(this.f3510b) < 0 ? this.f3510b : month.compareTo(this.f3511c) > 0 ? this.f3511c : month;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CalendarConstraints)) {
            return false;
        }
        CalendarConstraints calendarConstraints = (CalendarConstraints) obj;
        return this.f3510b.equals(calendarConstraints.f3510b) && this.f3511c.equals(calendarConstraints.f3511c) && this.f3512d.equals(calendarConstraints.f3512d) && this.f3513e.equals(calendarConstraints.f3513e);
    }

    public DateValidator g() {
        return this.f3513e;
    }

    Month h() {
        return this.f3511c;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f3510b, this.f3511c, this.f3512d, this.f3513e});
    }

    int i() {
        return this.f3515g;
    }

    Month j() {
        return this.f3512d;
    }

    Month k() {
        return this.f3510b;
    }

    int l() {
        return this.f3514f;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3510b, 0);
        parcel.writeParcelable(this.f3511c, 0);
        parcel.writeParcelable(this.f3512d, 0);
        parcel.writeParcelable(this.f3513e, 0);
    }

    private CalendarConstraints(Month month, Month month2, Month month3, DateValidator dateValidator) {
        this.f3510b = month;
        this.f3511c = month2;
        this.f3512d = month3;
        this.f3513e = dateValidator;
        if (month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (month3.compareTo(month2) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        this.f3515g = month.n(month2) + 1;
        this.f3514f = (month2.f3547e - month.f3547e) + 1;
    }
}
