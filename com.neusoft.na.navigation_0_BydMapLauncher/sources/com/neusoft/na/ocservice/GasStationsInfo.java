package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class GasStationsInfo implements Parcelable {
    public static final Parcelable.Creator<GasStationsInfo> CREATOR = new Parcelable.Creator<GasStationsInfo>() { // from class: com.neusoft.na.ocservice.GasStationsInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GasStationsInfo createFromParcel(Parcel parcel) {
            return new GasStationsInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GasStationsInfo[] newArray(int i) {
            return new GasStationsInfo[i];
        }
    };
    String address;
    int availablePumps;
    int dataType;
    int distance;
    String id;
    float latitude;
    float longitude;
    String name;
    OpeningHour[] operatingHours;
    String status;
    String typeCode;
    String typeName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public GasStationsInfo() {
        this.address = "";
        this.distance = -1;
        this.id = "";
        this.latitude = -1.0f;
        this.longitude = -1.0f;
        this.name = "";
        this.status = "";
        this.availablePumps = -1;
        this.typeCode = "";
        this.typeName = "";
        this.dataType = -1;
    }

    protected GasStationsInfo(Parcel parcel) {
        this.address = "";
        this.distance = -1;
        this.id = "";
        this.latitude = -1.0f;
        this.longitude = -1.0f;
        this.name = "";
        this.status = "";
        this.availablePumps = -1;
        this.typeCode = "";
        this.typeName = "";
        this.dataType = -1;
        this.address = parcel.readString();
        this.distance = parcel.readInt();
        this.id = parcel.readString();
        this.latitude = parcel.readFloat();
        this.longitude = parcel.readFloat();
        this.name = parcel.readString();
        this.operatingHours = (OpeningHour[]) parcel.createTypedArray(OpeningHour.CREATOR);
        this.status = parcel.readString();
        this.availablePumps = parcel.readInt();
        this.typeCode = parcel.readString();
        this.typeName = parcel.readString();
        this.dataType = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.address);
        parcel.writeInt(this.distance);
        parcel.writeString(this.id);
        parcel.writeFloat(this.latitude);
        parcel.writeFloat(this.longitude);
        parcel.writeString(this.name);
        parcel.writeTypedArray(this.operatingHours, i);
        parcel.writeString(this.status);
        parcel.writeInt(this.availablePumps);
        parcel.writeString(this.typeCode);
        parcel.writeString(this.typeName);
        parcel.writeInt(this.dataType);
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public void setLatitude(float f) {
        this.latitude = f;
    }

    public float getLongitude() {
        return this.longitude;
    }

    public void setLongitude(float f) {
        this.longitude = f;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
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

    public String getTypeCode() {
        return this.typeCode;
    }

    public void setTypeCode(String str) {
        this.typeCode = str;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String str) {
        this.typeName = str;
    }

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int i) {
        this.dataType = i;
    }
}
