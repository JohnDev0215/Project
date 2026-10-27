package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class LatLng implements Parcelable {
    public static final Parcelable.Creator<LatLng> CREATOR = new Parcelable.Creator<LatLng>() { // from class: com.neusoft.na.ocservice.LatLng.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LatLng createFromParcel(Parcel parcel) {
            return new LatLng(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LatLng[] newArray(int i) {
            return new LatLng[i];
        }
    };
    private String coordType;
    private Double latitude;
    private Double longitude;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double d) {
        this.latitude = d;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double d) {
        this.longitude = d;
    }

    public String getCoordType() {
        return this.coordType;
    }

    public void setCoordType(String str) {
        this.coordType = str;
    }

    protected LatLng(Parcel parcel) {
        Double dValueOf = Double.valueOf(0.0d);
        this.latitude = dValueOf;
        this.longitude = dValueOf;
        this.coordType = "coordtype";
        this.latitude = Double.valueOf(parcel.readDouble());
        this.longitude = Double.valueOf(parcel.readDouble());
        this.coordType = parcel.readString();
    }

    public LatLng(Double d, Double d2, String str) {
        this.latitude = Double.valueOf(0.0d);
        Double.valueOf(0.0d);
        this.coordType = "coordtype";
        this.latitude = d;
        this.longitude = d2;
        if (str != null) {
            this.coordType = str;
        }
    }

    public LatLng() {
        Double dValueOf = Double.valueOf(0.0d);
        this.latitude = dValueOf;
        this.longitude = dValueOf;
        this.coordType = "coordtype";
        this.latitude = dValueOf;
        this.longitude = dValueOf;
        this.coordType = "coordtype";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.latitude.doubleValue());
        parcel.writeDouble(this.longitude.doubleValue());
        parcel.writeString(this.coordType);
    }
}
