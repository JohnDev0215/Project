package com.google.android.material.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;

/* JADX INFO: loaded from: classes2.dex */
public class ParcelableSparseIntArray extends SparseIntArray implements Parcelable {
    public static final Parcelable.Creator<ParcelableSparseIntArray> CREATOR = new a();

    public static class a implements Parcelable.Creator<ParcelableSparseIntArray> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ParcelableSparseIntArray createFromParcel(Parcel parcel) {
            int i8 = parcel.readInt();
            ParcelableSparseIntArray parcelableSparseIntArray = new ParcelableSparseIntArray(i8);
            int[] iArr = new int[i8];
            int[] iArr2 = new int[i8];
            parcel.readIntArray(iArr);
            parcel.readIntArray(iArr2);
            for (int i9 = 0; i9 < i8; i9++) {
                parcelableSparseIntArray.put(iArr[i9], iArr2[i9]);
            }
            return parcelableSparseIntArray;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ParcelableSparseIntArray[] newArray(int i8) {
            return new ParcelableSparseIntArray[i8];
        }
    }

    public ParcelableSparseIntArray() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        int[] iArr = new int[size()];
        int[] iArr2 = new int[size()];
        for (int i9 = 0; i9 < size(); i9++) {
            iArr[i9] = keyAt(i9);
            iArr2[i9] = valueAt(i9);
        }
        parcel.writeInt(size());
        parcel.writeIntArray(iArr);
        parcel.writeIntArray(iArr2);
    }

    public ParcelableSparseIntArray(int i8) {
        super(i8);
    }
}
