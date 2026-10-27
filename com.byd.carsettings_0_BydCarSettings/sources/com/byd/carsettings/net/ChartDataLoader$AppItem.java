package com.byd.carsettings.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;

/* JADX INFO: loaded from: classes2.dex */
public class ChartDataLoader$AppItem implements Comparable<ChartDataLoader$AppItem>, Parcelable {
    public static final Parcelable.Creator<ChartDataLoader$AppItem> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2857a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2858b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public SparseBooleanArray f2859c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f2860d;

    public static class a implements Parcelable.Creator<ChartDataLoader$AppItem> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ChartDataLoader$AppItem createFromParcel(Parcel parcel) {
            return new ChartDataLoader$AppItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ChartDataLoader$AppItem[] newArray(int i) {
            return new ChartDataLoader$AppItem[i];
        }
    }

    public ChartDataLoader$AppItem() {
        this.f2859c = new SparseBooleanArray();
        this.f2857a = 0;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(ChartDataLoader$AppItem chartDataLoader$AppItem) {
        int iCompare = Integer.compare(this.f2858b, chartDataLoader$AppItem.f2858b);
        return iCompare == 0 ? Long.compare(chartDataLoader$AppItem.f2860d, this.f2860d) : iCompare;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2857a);
        parcel.writeSparseBooleanArray(this.f2859c);
        parcel.writeLong(this.f2860d);
    }

    public ChartDataLoader$AppItem(Parcel parcel) {
        this.f2859c = new SparseBooleanArray();
        this.f2857a = parcel.readInt();
        this.f2859c = parcel.readSparseBooleanArray();
        this.f2860d = parcel.readLong();
    }
}
