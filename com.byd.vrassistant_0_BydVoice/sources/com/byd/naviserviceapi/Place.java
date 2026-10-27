package com.byd.naviserviceapi;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class Place implements Parcelable {
    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() { // from class: com.byd.naviserviceapi.Place.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
    public static final int TYPE_ADDRESS = 2;
    public static final int TYPE_POI = 1;
    private String address;
    private String[] categoryId;
    private String city;
    private String country;
    private double distance;
    private String house_number;
    private String id;
    private double latitude;
    private double longitude;
    private String name;
    private String state;
    private String[] street;
    private int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Place() {
    }

    public Place(Parcel in) {
        this.name = in.readString();
        this.type = in.readInt();
        this.id = in.readString();
        this.address = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.distance = in.readDouble();
        if (this.categoryId == null) {
            Log.d("Place", "cateoryId is null");
            this.categoryId = in.createStringArray();
            Log.d("Place", "cateoryId createStringArray OK");
        }
        this.country = in.readString();
        this.state = in.readString();
        this.city = in.readString();
        if (this.street == null) {
            this.street = in.createStringArray();
        }
        this.house_number = in.readString();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String[] getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String[] categoryId) {
        this.categoryId = categoryId;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String[] getStreet() {
        return this.street;
    }

    public void setStreet(String[] street) {
        this.street = street;
    }

    public String getHouse_number() {
        return this.house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.type);
        dest.writeString(this.id);
        dest.writeString(this.address);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.distance);
        String[] strArr = this.categoryId;
        if (strArr != null) {
            dest.writeStringArray(strArr);
        }
        dest.writeString(this.country);
        dest.writeString(this.state);
        dest.writeString(this.city);
        String[] strArr2 = this.street;
        if (strArr2 != null) {
            dest.writeStringArray(strArr2);
        }
        dest.writeString(this.house_number);
    }

    public String toString() {
        return "Place{name='" + this.name + "', type=" + this.type + ", id='" + this.id + "', address='" + this.address + "', latitude=" + this.latitude + ", longitude=" + this.longitude + ", distance=" + this.distance + ", categoryId=" + Arrays.toString(this.categoryId) + ", country='" + this.country + "', state='" + this.state + "', city='" + this.city + "', street=" + Arrays.toString(this.street) + ", house_number='" + this.house_number + "'}";
    }
}
