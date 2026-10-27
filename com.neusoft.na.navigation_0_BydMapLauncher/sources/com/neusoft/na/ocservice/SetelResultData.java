package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class SetelResultData implements Parcelable {
    public static final Parcelable.Creator<SetelResultData> CREATOR = new Parcelable.Creator<SetelResultData>() { // from class: com.neusoft.na.ocservice.SetelResultData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SetelResultData createFromParcel(Parcel parcel) {
            return new SetelResultData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SetelResultData[] newArray(int i) {
            return new SetelResultData[i];
        }
    };
    boolean active;
    String address;
    boolean atStation;
    int availablePumps;
    double distance;
    double geofenceLatitude;
    double geofenceLongitude;
    int geofenceRadius;
    String id;
    boolean isNavigationMap;
    double latitude;
    double longitude;
    String name;
    int navigationMapIndex;
    OpeningHour[] operatingHours;
    String status;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SetelResultData() {
        this.active = false;
        this.address = "";
        this.atStation = false;
        this.distance = -1.0d;
        this.geofenceLatitude = -1.0d;
        this.geofenceLongitude = -1.0d;
        this.geofenceRadius = -1;
        this.id = "";
        this.isNavigationMap = false;
        this.latitude = -1.0d;
        this.longitude = -1.0d;
        this.name = "";
        this.navigationMapIndex = -1;
        this.status = "";
        this.availablePumps = -1;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean z) {
        this.active = z;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public boolean isAtStation() {
        return this.atStation;
    }

    public void setAtStation(boolean z) {
        this.atStation = z;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public double getGeofenceLatitude() {
        return this.geofenceLatitude;
    }

    public void setGeofenceLatitude(double d) {
        this.geofenceLatitude = d;
    }

    public double getGeofenceLongitude() {
        return this.geofenceLongitude;
    }

    public void setGeofenceLongitude(double d) {
        this.geofenceLongitude = d;
    }

    public int getGeofenceRadius() {
        return this.geofenceRadius;
    }

    public void setGeofenceRadius(int i) {
        this.geofenceRadius = i;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public boolean isNavigationMap() {
        return this.isNavigationMap;
    }

    public void setNavigationMap(boolean z) {
        this.isNavigationMap = z;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getNavigationMapIndex() {
        return this.navigationMapIndex;
    }

    public void setNavigationMapIndex(int i) {
        this.navigationMapIndex = i;
    }

    public OpeningHour[] getOperatingHours() {
        return this.operatingHours;
    }

    public void setOperatingHours(OpeningHour[] openingHourArr) {
        this.operatingHours = openingHourArr;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public int getAvailablePumps() {
        return this.availablePumps;
    }

    public void setAvailablePumps(int i) {
        this.availablePumps = i;
    }

    protected SetelResultData(Parcel parcel) {
        this.active = false;
        this.address = "";
        this.atStation = false;
        this.distance = -1.0d;
        this.geofenceLatitude = -1.0d;
        this.geofenceLongitude = -1.0d;
        this.geofenceRadius = -1;
        this.id = "";
        this.isNavigationMap = false;
        this.latitude = -1.0d;
        this.longitude = -1.0d;
        this.name = "";
        this.navigationMapIndex = -1;
        this.status = "";
        this.availablePumps = -1;
        this.active = parcel.readByte() != 0;
        this.address = parcel.readString();
        this.atStation = parcel.readByte() != 0;
        this.distance = parcel.readDouble();
        this.geofenceLatitude = parcel.readDouble();
        this.geofenceLongitude = parcel.readDouble();
        this.geofenceRadius = parcel.readInt();
        this.id = parcel.readString();
        this.isNavigationMap = parcel.readByte() != 0;
        this.latitude = parcel.readDouble();
        this.longitude = parcel.readDouble();
        this.name = parcel.readString();
        this.navigationMapIndex = parcel.readInt();
        this.operatingHours = (OpeningHour[]) parcel.createTypedArray(OpeningHour.CREATOR);
        this.status = parcel.readString();
        this.availablePumps = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.active ? (byte) 1 : (byte) 0);
        parcel.writeString(this.address);
        parcel.writeByte(this.atStation ? (byte) 1 : (byte) 0);
        parcel.writeDouble(this.distance);
        parcel.writeDouble(this.geofenceLatitude);
        parcel.writeDouble(this.geofenceLongitude);
        parcel.writeInt(this.geofenceRadius);
        parcel.writeString(this.id);
        parcel.writeByte(this.isNavigationMap ? (byte) 1 : (byte) 0);
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
        parcel.writeString(this.name);
        parcel.writeInt(this.navigationMapIndex);
        parcel.writeTypedArray(this.operatingHours, i);
        parcel.writeString(this.status);
        parcel.writeInt(this.availablePumps);
    }
}
