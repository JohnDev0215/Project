package com.android.launcher3.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

/* JADX INFO: loaded from: classes4.dex */
public class ParcelableSparseArray extends SparseArray<Parcelable> implements Parcelable {
    public static final Parcelable.Creator<ParcelableSparseArray> CREATOR = new Parcelable.Creator<ParcelableSparseArray>() { // from class: com.android.launcher3.util.ParcelableSparseArray.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableSparseArray createFromParcel(Parcel source) {
            ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
            ClassLoader classLoader = parcelableSparseArray.getClass().getClassLoader();
            int i = source.readInt();
            for (int i2 = 0; i2 < i; i2++) {
                parcelableSparseArray.put(source.readInt(), source.readParcelable(classLoader));
            }
            return parcelableSparseArray;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableSparseArray[] newArray(int size) {
            return new ParcelableSparseArray[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        int size = size();
        dest.writeInt(size);
        for (int i = 0; i < size; i++) {
            dest.writeInt(keyAt(i));
            dest.writeParcelable(valueAt(i), 0);
        }
    }
}
