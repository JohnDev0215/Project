package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class DateValidatorPointForward implements CalendarConstraints.DateValidator {
    public static final Parcelable.Creator<DateValidatorPointForward> CREATOR = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final long f4379e;

    static class a implements Parcelable.Creator<DateValidatorPointForward> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public DateValidatorPointForward createFromParcel(Parcel parcel) {
            return new DateValidatorPointForward(parcel.readLong(), null);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public DateValidatorPointForward[] newArray(int i7) {
            return new DateValidatorPointForward[i7];
        }
    }

    /* synthetic */ DateValidatorPointForward(long j7, a aVar) {
        this(j7);
    }

    public static DateValidatorPointForward b(long j7) {
        return new DateValidatorPointForward(j7);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DateValidatorPointForward) && this.f4379e == ((DateValidatorPointForward) obj).f4379e;
    }

    @Override // com.google.android.material.datepicker.CalendarConstraints.DateValidator
    public boolean g(long j7) {
        return j7 >= this.f4379e;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.f4379e)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeLong(this.f4379e);
    }

    private DateValidatorPointForward(long j7) {
        this.f4379e = j7;
    }
}
