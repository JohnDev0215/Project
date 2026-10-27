package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collection;
import k0.d;

/* JADX INFO: loaded from: classes.dex */
public class RangeDateSelector implements DateSelector<d<Long, Long>> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f2272b = " ";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Long f2273c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Long f2274d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Long f2275e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Long f2276f = null;

    public static class a implements Parcelable.Creator<RangeDateSelector> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector createFromParcel(Parcel parcel) {
            RangeDateSelector rangeDateSelector = new RangeDateSelector();
            rangeDateSelector.f2273c = (Long) parcel.readValue(Long.class.getClassLoader());
            rangeDateSelector.f2274d = (Long) parcel.readValue(Long.class.getClassLoader());
            return rangeDateSelector;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector[] newArray(int i3) {
            return new RangeDateSelector[i3];
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<d<Long, Long>> b() {
        if (this.f2273c == null || this.f2274d == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new d(this.f2273c, this.f2274d));
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void d(long j3) {
        Long l3 = this.f2273c;
        if (l3 == null) {
            this.f2273c = Long.valueOf(j3);
        } else if (this.f2274d == null && i(l3.longValue(), j3)) {
            this.f2274d = Long.valueOf(j3);
        } else {
            this.f2274d = null;
            this.f2273c = Long.valueOf(j3);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Long> e() {
        ArrayList arrayList = new ArrayList();
        Long l3 = this.f2273c;
        if (l3 != null) {
            arrayList.add(l3);
        }
        Long l4 = this.f2274d;
        if (l4 != null) {
            arrayList.add(l4);
        }
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public d<Long, Long> a() {
        return new d<>(this.f2273c, this.f2274d);
    }

    public final boolean i(long j3, long j4) {
        return j3 <= j4;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeValue(this.f2273c);
        parcel.writeValue(this.f2274d);
    }
}
