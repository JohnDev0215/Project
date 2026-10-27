package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class SetelSearchData implements Parcelable {
    public static final Parcelable.Creator<SetelSearchData> CREATOR = new Parcelable.Creator<SetelSearchData>() { // from class: com.neusoft.na.ocservice.SetelSearchData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SetelSearchData createFromParcel(Parcel parcel) {
            return new SetelSearchData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SetelSearchData[] newArray(int i) {
            return new SetelSearchData[i];
        }
    };
    boolean IsNavigationMap;
    int NavigationMap_index;
    String address;
    int dataType;
    double distance;
    double lat;
    double lon;
    String name;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public double getLon() {
        return this.lon;
    }

    public void setLon(double d) {
        this.lon = d;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double d) {
        this.lat = d;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public boolean isNavigationMap() {
        return this.IsNavigationMap;
    }

    public void setNavigationMap(boolean z) {
        this.IsNavigationMap = z;
    }

    public int getNavigationMap_index() {
        return this.NavigationMap_index;
    }

    public void setNavigationMap_index(int i) {
        this.NavigationMap_index = i;
    }

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int i) {
        this.dataType = i;
    }

    public SetelSearchData() {
        this.name = "";
        this.address = "";
        this.lon = -1.0d;
        this.lat = -1.0d;
        this.distance = -1.0d;
        this.IsNavigationMap = false;
        this.NavigationMap_index = -1;
        this.dataType = -1;
    }

    protected SetelSearchData(Parcel parcel) {
        this.name = "";
        this.address = "";
        this.lon = -1.0d;
        this.lat = -1.0d;
        this.distance = -1.0d;
        this.IsNavigationMap = false;
        this.NavigationMap_index = -1;
        this.dataType = -1;
        this.name = parcel.readString();
        this.address = parcel.readString();
        this.lon = parcel.readDouble();
        this.lat = parcel.readDouble();
        this.distance = parcel.readDouble();
        this.IsNavigationMap = parcel.readByte() != 0;
        this.NavigationMap_index = parcel.readInt();
        this.dataType = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.address);
        parcel.writeDouble(this.lon);
        parcel.writeDouble(this.lat);
        parcel.writeDouble(this.distance);
        parcel.writeByte(this.IsNavigationMap ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.NavigationMap_index);
        parcel.writeInt(this.dataType);
    }
}
