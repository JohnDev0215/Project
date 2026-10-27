package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public class RangeDateSelector implements DateSelector<c.g.j.d<Long, Long>> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3514b = " ";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Long f3515c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Long f3516d = null;
    private Long e = null;
    private Long f = null;

    static class a implements Parcelable.Creator<RangeDateSelector> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector createFromParcel(Parcel parcel) {
            RangeDateSelector rangeDateSelector = new RangeDateSelector();
            rangeDateSelector.f3515c = (Long) parcel.readValue(Long.class.getClassLoader());
            rangeDateSelector.f3516d = (Long) parcel.readValue(Long.class.getClassLoader());
            return rangeDateSelector;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector[] newArray(int i) {
            return new RangeDateSelector[i];
        }
    }

    private boolean i(long j, long j2) {
        return j <= j2;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<c.g.j.d<Long, Long>> a() {
        if (this.f3515c == null || this.f3516d == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new c.g.j.d(this.f3515c, this.f3516d));
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Long> c() {
        ArrayList arrayList = new ArrayList();
        Long l = this.f3515c;
        if (l != null) {
            arrayList.add(l);
        }
        Long l2 = this.f3516d;
        if (l2 != null) {
            arrayList.add(l2);
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void e(long j) {
        Long l = this.f3515c;
        if (l == null) {
            this.f3515c = Long.valueOf(j);
        } else if (this.f3516d == null && i(l.longValue(), j)) {
            this.f3516d = Long.valueOf(j);
        } else {
            this.f3516d = null;
            this.f3515c = Long.valueOf(j);
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public c.g.j.d<Long, Long> d() {
        return new c.g.j.d<>(this.f3515c, this.f3516d);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.f3515c);
        parcel.writeValue(this.f3516d);
    }
}
