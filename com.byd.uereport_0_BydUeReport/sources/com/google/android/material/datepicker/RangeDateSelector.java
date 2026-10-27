package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collection;
import n0.d;

/* JADX INFO: loaded from: classes2.dex */
public class RangeDateSelector implements DateSelector<d<Long, Long>> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f3857d = " ";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Long f3858e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Long f3859f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Long f3860g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Long f3861h = null;

    public static class a implements Parcelable.Creator<RangeDateSelector> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector createFromParcel(Parcel parcel) {
            RangeDateSelector rangeDateSelector = new RangeDateSelector();
            rangeDateSelector.f3858e = (Long) parcel.readValue(Long.class.getClassLoader());
            rangeDateSelector.f3859f = (Long) parcel.readValue(Long.class.getClassLoader());
            return rangeDateSelector;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector[] newArray(int i8) {
            return new RangeDateSelector[i8];
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<d<Long, Long>> a() {
        if (this.f3858e == null || this.f3859f == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new d(this.f3858e, this.f3859f));
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Long> c() {
        ArrayList arrayList = new ArrayList();
        Long l8 = this.f3858e;
        if (l8 != null) {
            arrayList.add(l8);
        }
        Long l9 = this.f3859f;
        if (l9 != null) {
            arrayList.add(l9);
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void e(long j8) {
        Long l8 = this.f3858e;
        if (l8 == null) {
            this.f3858e = Long.valueOf(j8);
        } else if (this.f3859f == null && i(l8.longValue(), j8)) {
            this.f3859f = Long.valueOf(j8);
        } else {
            this.f3859f = null;
            this.f3858e = Long.valueOf(j8);
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public d<Long, Long> d() {
        return new d<>(this.f3858e, this.f3859f);
    }

    public final boolean i(long j8, long j9) {
        return j8 <= j9;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeValue(this.f3858e);
        parcel.writeValue(this.f3859f);
    }
}
