package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.v8;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public class RangeDateSelector implements DateSelector<v8<Long, Long>> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = new a();
    public final String b = " ";
    public Long c = null;
    public Long d = null;
    public Long e = null;
    public Long f = null;

    public static class a implements Parcelable.Creator<RangeDateSelector> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector createFromParcel(Parcel parcel) {
            RangeDateSelector rangeDateSelector = new RangeDateSelector();
            rangeDateSelector.c = (Long) parcel.readValue(Long.class.getClassLoader());
            rangeDateSelector.d = (Long) parcel.readValue(Long.class.getClassLoader());
            return rangeDateSelector;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector[] newArray(int i) {
            return new RangeDateSelector[i];
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<v8<Long, Long>> b() {
        if (this.c == null || this.d == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new v8(this.c, this.d));
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void d(long j) {
        Long l = this.c;
        if (l == null) {
            this.c = Long.valueOf(j);
        } else if (this.d == null && i(l.longValue(), j)) {
            this.d = Long.valueOf(j);
        } else {
            this.d = null;
            this.c = Long.valueOf(j);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Long> e() {
        ArrayList arrayList = new ArrayList();
        Long l = this.c;
        if (l != null) {
            arrayList.add(l);
        }
        Long l2 = this.d;
        if (l2 != null) {
            arrayList.add(l2);
        }
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public v8<Long, Long> a() {
        return new v8<>(this.c, this.d);
    }

    public final boolean i(long j, long j2) {
        return j <= j2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.c);
        parcel.writeValue(this.d);
    }
}
