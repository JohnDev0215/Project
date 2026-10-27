package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public class RangeDateSelector implements DateSelector<b.g.j.d<Long, Long>> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3551b = " ";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Long f3552c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Long f3553d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Long f3554e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Long f3555f = null;

    static class a implements Parcelable.Creator<RangeDateSelector> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector createFromParcel(Parcel parcel) {
            RangeDateSelector rangeDateSelector = new RangeDateSelector();
            rangeDateSelector.f3552c = (Long) parcel.readValue(Long.class.getClassLoader());
            rangeDateSelector.f3553d = (Long) parcel.readValue(Long.class.getClassLoader());
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
    public Collection<b.g.j.d<Long, Long>> b() {
        if (this.f3552c == null || this.f3553d == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new b.g.j.d(this.f3552c, this.f3553d));
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void d(long j) {
        Long l = this.f3552c;
        if (l == null) {
            this.f3552c = Long.valueOf(j);
        } else if (this.f3553d == null && i(l.longValue(), j)) {
            this.f3553d = Long.valueOf(j);
        } else {
            this.f3553d = null;
            this.f3552c = Long.valueOf(j);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Long> f() {
        ArrayList arrayList = new ArrayList();
        Long l = this.f3552c;
        if (l != null) {
            arrayList.add(l);
        }
        Long l2 = this.f3553d;
        if (l2 != null) {
            arrayList.add(l2);
        }
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public b.g.j.d<Long, Long> a() {
        return new b.g.j.d<>(this.f3552c, this.f3553d);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.f3552c);
        parcel.writeValue(this.f3553d);
    }
}
