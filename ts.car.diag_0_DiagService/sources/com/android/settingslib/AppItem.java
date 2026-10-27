package com.android.settingslib;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class AppItem implements Comparable<AppItem>, Parcelable {
    public static final Parcelable.Creator<AppItem> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1311c;
    public int d;
    public SparseBooleanArray e;
    public long f;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Parcelable.Creator<AppItem> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public AppItem createFromParcel(Parcel parcel) {
            return new AppItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public AppItem[] newArray(int i) {
            return new AppItem[i];
        }
    }

    public AppItem() {
        this.e = new SparseBooleanArray();
        this.f1311c = 0;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(AppItem appItem) {
        int iCompare = Integer.compare(this.d, appItem.d);
        return iCompare == 0 ? Long.compare(appItem.f, this.f) : iCompare;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f1311c);
        parcel.writeSparseBooleanArray(this.e);
        parcel.writeLong(this.f);
    }

    public AppItem(Parcel parcel) {
        this.e = new SparseBooleanArray();
        this.f1311c = parcel.readInt();
        this.e = parcel.readSparseBooleanArray();
        this.f = parcel.readLong();
    }
}
