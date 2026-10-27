package com.byd.naviserviceapi;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class RouteInfo2 implements Parcelable {
    public static final Parcelable.Creator<RouteInfo2> CREATOR = new Parcelable.Creator<RouteInfo2>() { // from class: com.byd.naviserviceapi.RouteInfo2.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteInfo2 createFromParcel(Parcel in) {
            return new RouteInfo2(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteInfo2[] newArray(int size) {
            return new RouteInfo2[size];
        }
    };
    private double distance;
    private String id;
    private String label;
    private int[] routeOption;
    private long time;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RouteInfo2() {
    }

    protected RouteInfo2(Parcel in) {
        this.id = in.readString();
        this.label = in.readString();
        if (this.routeOption == null) {
            Log.i("RouteInfo2", "routeOption is null");
            this.routeOption = in.createIntArray();
            Log.i("RouteInfo2", "create routeOption Array OK");
        }
        this.time = in.readLong();
        this.distance = in.readDouble();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.label);
        int[] iArr = this.routeOption;
        if (iArr != null) {
            dest.writeIntArray(iArr);
        }
        dest.writeLong(this.time);
        dest.writeDouble(this.distance);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int[] getRouteOption() {
        return this.routeOption;
    }

    public void setRouteOption(int[] routeOption) {
        this.routeOption = routeOption;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
