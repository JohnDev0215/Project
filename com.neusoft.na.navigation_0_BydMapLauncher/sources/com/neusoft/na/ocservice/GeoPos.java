package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class GeoPos implements Parcelable {
    public static final Parcelable.Creator<GeoPos> CREATOR = new Parcelable.Creator<GeoPos>() { // from class: com.neusoft.na.ocservice.GeoPos.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GeoPos createFromParcel(Parcel parcel) {
            return new GeoPos(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GeoPos[] newArray(int i) {
            return new GeoPos[i];
        }
    };
    private double Latitude;
    private double Longitude;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public double getLatitude() {
        return this.Latitude;
    }

    public void setLatitude(double d) {
        this.Latitude = d;
    }

    public double getLongitude() {
        return this.Longitude;
    }

    public void setLongitude(double d) {
        this.Longitude = d;
    }

    public GeoPos() {
        this.Latitude = 0.0d;
        this.Longitude = 0.0d;
    }

    protected GeoPos(Parcel parcel) {
        this.Latitude = 0.0d;
        this.Longitude = 0.0d;
        this.Latitude = parcel.readDouble();
        this.Longitude = parcel.readDouble();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.Latitude);
        parcel.writeDouble(this.Longitude);
    }
}
