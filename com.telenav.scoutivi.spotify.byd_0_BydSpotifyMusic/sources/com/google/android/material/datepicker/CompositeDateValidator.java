package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class CompositeDateValidator implements CalendarConstraints.DateValidator {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final d f4376e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final List<CalendarConstraints.DateValidator> f4377g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final d f4374h = new a();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final d f4375i = new b();
    public static final Parcelable.Creator<CompositeDateValidator> CREATOR = new c();

    static class a implements d {
        a() {
        }

        @Override // com.google.android.material.datepicker.CompositeDateValidator.d
        public int a() {
            return 1;
        }

        @Override // com.google.android.material.datepicker.CompositeDateValidator.d
        public boolean b(List<CalendarConstraints.DateValidator> list, long j7) {
            for (CalendarConstraints.DateValidator dateValidator : list) {
                if (dateValidator != null && dateValidator.g(j7)) {
                    return true;
                }
            }
            return false;
        }
    }

    static class b implements d {
        b() {
        }

        @Override // com.google.android.material.datepicker.CompositeDateValidator.d
        public int a() {
            return 2;
        }

        @Override // com.google.android.material.datepicker.CompositeDateValidator.d
        public boolean b(List<CalendarConstraints.DateValidator> list, long j7) {
            for (CalendarConstraints.DateValidator dateValidator : list) {
                if (dateValidator != null && !dateValidator.g(j7)) {
                    return false;
                }
            }
            return true;
        }
    }

    static class c implements Parcelable.Creator<CompositeDateValidator> {
        c() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public CompositeDateValidator createFromParcel(Parcel parcel) {
            ArrayList arrayList = parcel.readArrayList(CalendarConstraints.DateValidator.class.getClassLoader());
            int i7 = parcel.readInt();
            d dVar = (i7 != 2 && i7 == 1) ? CompositeDateValidator.f4374h : CompositeDateValidator.f4375i;
            return new CompositeDateValidator((List) h0.h.f(arrayList), dVar, null);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public CompositeDateValidator[] newArray(int i7) {
            return new CompositeDateValidator[i7];
        }
    }

    private interface d {
        int a();

        boolean b(List<CalendarConstraints.DateValidator> list, long j7);
    }

    /* synthetic */ CompositeDateValidator(List list, d dVar, a aVar) {
        this(list, dVar);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompositeDateValidator)) {
            return false;
        }
        CompositeDateValidator compositeDateValidator = (CompositeDateValidator) obj;
        return this.f4377g.equals(compositeDateValidator.f4377g) && this.f4376e.a() == compositeDateValidator.f4376e.a();
    }

    @Override // com.google.android.material.datepicker.CalendarConstraints.DateValidator
    public boolean g(long j7) {
        return this.f4376e.b(this.f4377g, j7);
    }

    public int hashCode() {
        return this.f4377g.hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeList(this.f4377g);
        parcel.writeInt(this.f4376e.a());
    }

    private CompositeDateValidator(List<CalendarConstraints.DateValidator> list, d dVar) {
        this.f4377g = list;
        this.f4376e = dVar;
    }
}
