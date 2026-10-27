package com.byd.car;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class WheelHub implements Parcelable {
    public static final Parcelable.Creator<WheelHub> CREATOR = new Parcelable.Creator<WheelHub>() { // from class: com.byd.car.WheelHub.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WheelHub createFromParcel(Parcel in) {
            return new WheelHub(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WheelHub[] newArray(int size) {
            return new WheelHub[size];
        }
    };
    public int size;
    public int style;

    public WheelHub() {
        this.size = 0;
        this.style = 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.size);
        dest.writeInt(this.style);
    }

    public WheelHub(Parcel in) {
        this.size = in.readInt();
        this.style = in.readInt();
    }
}
