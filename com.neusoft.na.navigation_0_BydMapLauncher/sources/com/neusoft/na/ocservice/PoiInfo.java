package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class PoiInfo implements Parcelable {
    public static final Parcelable.Creator<PoiInfo> CREATOR = new Parcelable.Creator<PoiInfo>() { // from class: com.neusoft.na.ocservice.PoiInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PoiInfo createFromParcel(Parcel parcel) {
            return new PoiInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PoiInfo[] newArray(int i) {
            return new PoiInfo[i];
        }
    };
    private String address;
    private int dataType;
    private int distance;
    private LatLng latLng;
    private String name;
    private PoiType type;
    private String uid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected PoiInfo(Parcel parcel) {
        this.name = "";
        this.uid = "";
        this.distance = 0;
        this.address = "";
        this.dataType = -1;
        this.name = parcel.readString();
        this.uid = parcel.readString();
        this.distance = parcel.readInt();
        this.address = parcel.readString();
        this.type = (PoiType) parcel.readParcelable(PoiType.class.getClassLoader());
        this.latLng = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.dataType = parcel.readInt();
    }

    public PoiInfo(String str, String str2, int i, String str3, PoiType poiType, LatLng latLng, int i2) {
        this.name = "";
        this.uid = "";
        this.distance = 0;
        this.address = "";
        this.dataType = -1;
        if (str != null) {
            this.name = str;
        }
        if (str2 != null) {
            this.uid = str2;
        }
        this.distance = i;
        if (str3 != null) {
            this.address = str3;
        }
        this.type = poiType;
        this.latLng = latLng;
        this.dataType = i2;
    }

    public PoiInfo() {
        this.dataType = -1;
        this.name = "";
        this.uid = "";
        this.distance = 0;
        this.address = "";
        this.type = new PoiType();
        this.latLng = new LatLng();
        this.dataType = -1;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        if (str != null) {
            this.name = str;
        }
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        if (str != null) {
            this.uid = str;
        }
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
        if (str != null) {
            this.address = str;
        }
    }

    public PoiType getType() {
        return this.type;
    }

    public void setType(PoiType poiType) {
        this.type = poiType;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int i) {
        this.dataType = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.uid);
        parcel.writeInt(this.distance);
        parcel.writeString(this.address);
        parcel.writeParcelable(this.type, i);
        parcel.writeParcelable(this.latLng, i);
        parcel.writeInt(this.dataType);
    }
}
