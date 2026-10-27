package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public class RangeDateSelector implements DateSelector<b.g.j.d<Long, Long>> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f4180b = " ";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Long f4181c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Long f4182d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Long f4183e = null;
    private Long f = null;

    static class a implements Parcelable.Creator<RangeDateSelector> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector createFromParcel(Parcel parcel) {
            RangeDateSelector rangeDateSelector = new RangeDateSelector();
            rangeDateSelector.f4181c = (Long) parcel.readValue(Long.class.getClassLoader());
            rangeDateSelector.f4182d = (Long) parcel.readValue(Long.class.getClassLoader());
            return rangeDateSelector;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector[] newArray(int i) {
            return new RangeDateSelector[i];
        }
    }

    private boolean x(long j, long j2) {
        return j <= j2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<b.g.j.d<Long, Long>> i() {
        if (this.f4181c == null || this.f4182d == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new b.g.j.d(this.f4181c, this.f4182d));
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void p(long j) {
        Long l = this.f4181c;
        if (l == null) {
            this.f4181c = Long.valueOf(j);
        } else if (this.f4182d == null && x(l.longValue(), j)) {
            this.f4182d = Long.valueOf(j);
        } else {
            this.f4182d = null;
            this.f4181c = Long.valueOf(j);
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Long> s() {
        ArrayList arrayList = new ArrayList();
        Long l = this.f4181c;
        if (l != null) {
            arrayList.add(l);
        }
        Long l2 = this.f4182d;
        if (l2 != null) {
            arrayList.add(l2);
        }
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
    public b.g.j.d<Long, Long> d() {
        return new b.g.j.d<>(this.f4181c, this.f4182d);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.f4181c);
        parcel.writeValue(this.f4182d);
    }
}
