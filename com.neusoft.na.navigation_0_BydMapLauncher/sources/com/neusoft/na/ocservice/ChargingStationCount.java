package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class ChargingStationCount implements Parcelable {
    public static final Parcelable.Creator<ChargingStationCount> CREATOR = new Parcelable.Creator<ChargingStationCount>() { // from class: com.neusoft.na.ocservice.ChargingStationCount.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChargingStationCount createFromParcel(Parcel parcel) {
            return new ChargingStationCount(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChargingStationCount[] newArray(int i) {
            return new ChargingStationCount[i];
        }
    };
    private String availableCount;
    private String totalCount;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ChargingStationCount() {
    }

    public ChargingStationCount(String str, String str2) {
        this.totalCount = str;
        this.availableCount = str2;
    }

    public String getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(String str) {
        this.totalCount = str;
    }

    public String getAvailableCount() {
        return this.availableCount;
    }

    public void setAvailableCount(String str) {
        this.availableCount = str;
    }

    protected ChargingStationCount(Parcel parcel) {
        this.totalCount = parcel.readString();
        this.availableCount = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.totalCount);
        parcel.writeString(this.availableCount);
    }
}
