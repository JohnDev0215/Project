package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import b.h.l.g;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class CompositeDateValidator implements CalendarConstraints.DateValidator {
    public static final Parcelable.Creator<CompositeDateValidator> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List<CalendarConstraints.DateValidator> f4666b;

    public static class a implements Parcelable.Creator<CompositeDateValidator> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public CompositeDateValidator createFromParcel(Parcel parcel) {
            return new CompositeDateValidator((List) g.c(parcel.readArrayList(CalendarConstraints.DateValidator.class.getClassLoader())), null);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public CompositeDateValidator[] newArray(int i2) {
            return new CompositeDateValidator[i2];
        }
    }

    public /* synthetic */ CompositeDateValidator(List list, a aVar) {
        this(list);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.CalendarConstraints.DateValidator
    public boolean e(long j) {
        for (CalendarConstraints.DateValidator dateValidator : this.f4666b) {
            if (dateValidator != null && !dateValidator.e(j)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CompositeDateValidator) {
            return this.f4666b.equals(((CompositeDateValidator) obj).f4666b);
        }
        return false;
    }

    public int hashCode() {
        return this.f4666b.hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeList(this.f4666b);
    }

    public CompositeDateValidator(List<CalendarConstraints.DateValidator> list) {
        this.f4666b = list;
    }
}
