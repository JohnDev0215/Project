package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class DRPosition implements Parcelable {
    public static final Parcelable.Creator<DRPosition> CREATOR = new Parcelable.Creator<DRPosition>() { // from class: com.neusoft.na.ocservice.DRPosition.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DRPosition createFromParcel(Parcel parcel) {
            return new DRPosition(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DRPosition[] newArray(int i) {
            return new DRPosition[i];
        }
    };
    private double heading;
    private double latitude;
    private double longitude;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DRPosition(double d, double d2, double d3) {
        this.heading = d;
        this.latitude = d2;
        this.longitude = d3;
    }

    protected DRPosition(Parcel parcel) {
        this.heading = parcel.readDouble();
        this.latitude = parcel.readDouble();
        this.longitude = parcel.readDouble();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.heading);
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
    }

    public double getHeading() {
        return this.heading;
    }

    public void setHeading(double d) {
        this.heading = d;
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
}
