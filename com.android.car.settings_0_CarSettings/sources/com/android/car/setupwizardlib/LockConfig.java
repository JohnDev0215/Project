package com.android.car.setupwizardlib;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes5.dex */
public class LockConfig implements Parcelable {
    public static final Parcelable.Creator<LockConfig> CREATOR = new Parcelable.Creator<LockConfig>() { // from class: com.android.car.setupwizardlib.LockConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LockConfig createFromParcel(Parcel source) {
            return new LockConfig(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LockConfig[] newArray(int size) {
            return new LockConfig[size];
        }
    };
    public boolean enabled;
    public int minLockLength;

    public LockConfig(Parcel in) {
        readFromParcel(in);
    }

    public LockConfig(boolean enabled, int minLockLength) {
        this.enabled = enabled;
        this.minLockLength = minLockLength;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.enabled ? 1 : 0);
        parcel.writeInt(this.minLockLength);
    }

    private void readFromParcel(Parcel in) {
        this.enabled = in.readInt() != 0;
        this.minLockLength = in.readInt();
    }
}
