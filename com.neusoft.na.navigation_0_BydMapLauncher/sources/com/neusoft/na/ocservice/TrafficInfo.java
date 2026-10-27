package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class TrafficInfo implements Parcelable {
    public static final Parcelable.Creator<TrafficInfo> CREATOR = new Parcelable.Creator<TrafficInfo>() { // from class: com.neusoft.na.ocservice.TrafficInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TrafficInfo createFromParcel(Parcel parcel) {
            return new TrafficInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TrafficInfo[] newArray(int i) {
            return new TrafficInfo[i];
        }
    };
    private double percent;
    private int segIndex;
    private int tmcStatus;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getSegIndex() {
        return this.segIndex;
    }

    public void setSegIndex(int i) {
        this.segIndex = i;
    }

    public int getTmcStatus() {
        return this.tmcStatus;
    }

    public void setTmcStatus(int i) {
        this.tmcStatus = i;
    }

    public double getPercent() {
        return this.percent;
    }

    public void setPercent(double d) {
        this.percent = d;
    }

    protected TrafficInfo(Parcel parcel) {
        this.segIndex = -1;
        this.tmcStatus = -1;
        this.percent = -1.0d;
        this.segIndex = parcel.readInt();
        this.tmcStatus = parcel.readInt();
        this.percent = parcel.readDouble();
    }

    public TrafficInfo() {
        this.segIndex = -1;
        this.tmcStatus = -1;
        this.percent = -1.0d;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.segIndex);
        parcel.writeInt(this.tmcStatus);
        parcel.writeDouble(this.percent);
    }
}
