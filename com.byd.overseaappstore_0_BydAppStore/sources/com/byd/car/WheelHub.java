package com.byd.car;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class WheelHub implements Parcelable {
    public static final Parcelable.Creator<WheelHub> CREATOR = new Parcelable.Creator<WheelHub>() { // from class: com.byd.car.WheelHub.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WheelHub createFromParcel(Parcel parcel) {
            return new WheelHub(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WheelHub[] newArray(int i) {
            return new WheelHub[i];
        }
    };
    public int size;
    public int style;

    public WheelHub() {
        this.size = 0;
        this.style = 0;
    }

    public WheelHub(Parcel parcel) {
        this.size = parcel.readInt();
        this.style = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.size);
        parcel.writeInt(this.style);
    }
}
