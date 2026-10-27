package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.List;
import n0.h;

/* JADX INFO: loaded from: classes2.dex */
public final class CompositeDateValidator implements CalendarConstraints.DateValidator {
    public static final Parcelable.Creator<CompositeDateValidator> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<CalendarConstraints.DateValidator> f3813d;

    public static class a implements Parcelable.Creator<CompositeDateValidator> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public CompositeDateValidator createFromParcel(Parcel parcel) {
            return new CompositeDateValidator((List) h.e(parcel.readArrayList(CalendarConstraints.DateValidator.class.getClassLoader())), null);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public CompositeDateValidator[] newArray(int i8) {
            return new CompositeDateValidator[i8];
        }
    }

    public /* synthetic */ CompositeDateValidator(List list, a aVar) {
        this(list);
    }

    @Override // com.google.android.material.datepicker.CalendarConstraints.DateValidator
    public boolean b(long j8) {
        for (CalendarConstraints.DateValidator dateValidator : this.f3813d) {
            if (dateValidator != null && !dateValidator.b(j8)) {
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
            return this.f3813d.equals(((CompositeDateValidator) obj).f3813d);
        }
        return false;
    }

    public int hashCode() {
        return this.f3813d.hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeList(this.f3813d);
    }

    public CompositeDateValidator(List<CalendarConstraints.DateValidator> list) {
        this.f3813d = list;
    }
}
