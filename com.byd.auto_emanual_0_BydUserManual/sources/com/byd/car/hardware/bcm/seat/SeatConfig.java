package com.byd.car.hardware.bcm.seat;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseLongArray;

/* JADX INFO: loaded from: classes.dex */
public class SeatConfig implements Parcelable {
    public static final Parcelable.Creator<SeatConfig> CREATOR = new Parcelable.Creator<SeatConfig>() { // from class: com.byd.car.hardware.bcm.seat.SeatConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SeatConfig createFromParcel(Parcel in) {
            return new SeatConfig(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SeatConfig[] newArray(int size) {
            return new SeatConfig[size];
        }
    };
    public SparseLongArray mFeatureFlagList;

    public SeatConfig(SparseLongArray featureFlagList) {
        this.mFeatureFlagList = new SparseLongArray();
        for (int i = 0; i < featureFlagList.size(); i++) {
            this.mFeatureFlagList.put(featureFlagList.keyAt(i), featureFlagList.valueAt(i));
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mFeatureFlagList.size());
        for (int i = 0; i < this.mFeatureFlagList.size(); i++) {
            dest.writeInt(this.mFeatureFlagList.keyAt(i));
            dest.writeLong(this.mFeatureFlagList.valueAt(i));
        }
    }

    public SeatConfig(Parcel in) {
        int i = in.readInt();
        this.mFeatureFlagList = new SparseLongArray(i);
        for (int i2 = 0; i2 < i; i2++) {
            this.mFeatureFlagList.put(in.readInt(), in.readLong());
        }
    }
}
