package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import b.g.i.h;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class CompositeDateValidator implements CalendarConstraints.DateValidator {
    public static final Parcelable.Creator<CompositeDateValidator> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List<CalendarConstraints.DateValidator> f2142b;

    public static class a implements Parcelable.Creator<CompositeDateValidator> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public CompositeDateValidator createFromParcel(Parcel parcel) {
            return new CompositeDateValidator((List) h.c(parcel.readArrayList(CalendarConstraints.DateValidator.class.getClassLoader())), null);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public CompositeDateValidator[] newArray(int i) {
            return new CompositeDateValidator[i];
        }
    }

    public /* synthetic */ CompositeDateValidator(List list, a aVar) {
        this(list);
    }

    @Override // com.google.android.material.datepicker.CalendarConstraints.DateValidator
    public boolean c(long j) {
        for (CalendarConstraints.DateValidator dateValidator : this.f2142b) {
            if (dateValidator != null && !dateValidator.c(j)) {
                return false;
            }
        }
        return true;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CompositeDateValidator) {
            return this.f2142b.equals(((CompositeDateValidator) obj).f2142b);
        }
        return false;
    }

    public int hashCode() {
        return this.f2142b.hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.f2142b);
    }

    public CompositeDateValidator(List<CalendarConstraints.DateValidator> list) {
        this.f2142b = list;
    }
}
