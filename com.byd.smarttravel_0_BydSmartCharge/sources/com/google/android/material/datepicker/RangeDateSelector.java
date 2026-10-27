package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import b.g.i.d;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public class RangeDateSelector implements DateSelector<d<Long, Long>> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f2169b = " ";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Long f2170c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Long f2171d = null;
    public Long e = null;
    public Long f = null;

    public static class a implements Parcelable.Creator<RangeDateSelector> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector createFromParcel(Parcel parcel) {
            RangeDateSelector rangeDateSelector = new RangeDateSelector();
            rangeDateSelector.f2170c = (Long) parcel.readValue(Long.class.getClassLoader());
            rangeDateSelector.f2171d = (Long) parcel.readValue(Long.class.getClassLoader());
            return rangeDateSelector;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector[] newArray(int i) {
            return new RangeDateSelector[i];
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<d<Long, Long>> b() {
        if (this.f2170c == null || this.f2171d == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new d(this.f2170c, this.f2171d));
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void d(long j) {
        Long l = this.f2170c;
        if (l == null) {
            this.f2170c = Long.valueOf(j);
        } else if (this.f2171d == null && i(l.longValue(), j)) {
            this.f2171d = Long.valueOf(j);
        } else {
            this.f2171d = null;
            this.f2170c = Long.valueOf(j);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Long> e() {
        ArrayList arrayList = new ArrayList();
        Long l = this.f2170c;
        if (l != null) {
            arrayList.add(l);
        }
        Long l2 = this.f2171d;
        if (l2 != null) {
            arrayList.add(l2);
        }
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public d<Long, Long> a() {
        return new d<>(this.f2170c, this.f2171d);
    }

    public final boolean i(long j, long j2) {
        return j <= j2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.f2170c);
        parcel.writeValue(this.f2171d);
    }
}
