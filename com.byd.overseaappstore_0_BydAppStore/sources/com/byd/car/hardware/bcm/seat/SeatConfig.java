package com.byd.car.hardware.bcm.seat;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseLongArray;

/* JADX INFO: loaded from: classes.dex */
public class SeatConfig implements Parcelable {
    public static final Parcelable.Creator<SeatConfig> CREATOR = new Parcelable.Creator<SeatConfig>() { // from class: com.byd.car.hardware.bcm.seat.SeatConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SeatConfig createFromParcel(Parcel parcel) {
            return new SeatConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SeatConfig[] newArray(int i) {
            return new SeatConfig[i];
        }
    };
    public SparseLongArray mFeatureFlagList;

    public SeatConfig(Parcel parcel) {
        int i = parcel.readInt();
        this.mFeatureFlagList = new SparseLongArray(i);
        for (int i2 = 0; i2 < i; i2++) {
            this.mFeatureFlagList.put(parcel.readInt(), parcel.readLong());
        }
    }

    public SeatConfig(SparseLongArray sparseLongArray) {
        this.mFeatureFlagList = new SparseLongArray();
        for (int i = 0; i < sparseLongArray.size(); i++) {
            this.mFeatureFlagList.put(sparseLongArray.keyAt(i), sparseLongArray.valueAt(i));
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mFeatureFlagList.size());
        for (int i2 = 0; i2 < this.mFeatureFlagList.size(); i2++) {
            parcel.writeInt(this.mFeatureFlagList.keyAt(i2));
            parcel.writeLong(this.mFeatureFlagList.valueAt(i2));
        }
    }
}
