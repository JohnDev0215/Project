package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class CompositeDateValidator implements CalendarConstraints.DateValidator {
    public static final Parcelable.Creator<CompositeDateValidator> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<CalendarConstraints.DateValidator> f3489b;

    static class a implements Parcelable.Creator<CompositeDateValidator> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public CompositeDateValidator createFromParcel(Parcel parcel) {
            return new CompositeDateValidator((List) c.g.j.h.c(parcel.readArrayList(CalendarConstraints.DateValidator.class.getClassLoader())), null);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public CompositeDateValidator[] newArray(int i) {
            return new CompositeDateValidator[i];
        }
    }

    /* synthetic */ CompositeDateValidator(List list, a aVar) {
        this(list);
    }

    @Override // com.google.android.material.datepicker.CalendarConstraints.DateValidator
    public boolean b(long j) {
        for (CalendarConstraints.DateValidator dateValidator : this.f3489b) {
            if (dateValidator != null && !dateValidator.b(j)) {
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
            return this.f3489b.equals(((CompositeDateValidator) obj).f3489b);
        }
        return false;
    }

    public int hashCode() {
        return this.f3489b.hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.f3489b);
    }

    private CompositeDateValidator(List<CalendarConstraints.DateValidator> list) {
        this.f3489b = list;
    }
}
