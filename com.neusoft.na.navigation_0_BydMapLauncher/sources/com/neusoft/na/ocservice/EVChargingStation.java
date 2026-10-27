package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class EVChargingStation implements Parcelable {
    public static final Parcelable.Creator<EVChargingStation> CREATOR = new Parcelable.Creator<EVChargingStation>() { // from class: com.neusoft.na.ocservice.EVChargingStation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EVChargingStation createFromParcel(Parcel parcel) {
            return new EVChargingStation(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EVChargingStation[] newArray(int i) {
            return new EVChargingStation[i];
        }
    };
    private int POIType;
    private String address;
    private ChargingStationCount chargingStationCount;
    private int distance;
    private LatLng latLng;
    private String name;
    private String uid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public EVChargingStation() {
    }

    public EVChargingStation(String str, int i, String str2, String str3, int i2, LatLng latLng, ChargingStationCount chargingStationCount) {
        this.name = str;
        this.distance = i;
        this.address = str2;
        this.latLng = latLng;
        this.chargingStationCount = chargingStationCount;
        this.uid = str3;
        this.POIType = i2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public int getPOIType() {
        return this.POIType;
    }

    public void setPOIType(int i) {
        this.POIType = i;
    }

    public ChargingStationCount getChargingStationCount() {
        return this.chargingStationCount;
    }

    public void setChargingStationCount(ChargingStationCount chargingStationCount) {
        this.chargingStationCount = chargingStationCount;
    }

    protected EVChargingStation(Parcel parcel) {
        this.name = parcel.readString();
        this.distance = parcel.readInt();
        this.address = parcel.readString();
        this.latLng = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.uid = parcel.readString();
        this.POIType = parcel.readInt();
        this.chargingStationCount = (ChargingStationCount) parcel.readParcelable(ChargingStationCount.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeInt(this.distance);
        parcel.writeString(this.address);
        parcel.writeParcelable(this.latLng, i);
        parcel.writeString(this.uid);
        parcel.writeInt(this.POIType);
        parcel.writeParcelable(this.chargingStationCount, i);
    }
}
