package com.byd.naviserviceapi;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class RouteInfo implements Parcelable {
    public static final Parcelable.Creator<RouteInfo> CREATOR = new Parcelable.Creator<RouteInfo>() { // from class: com.byd.naviserviceapi.RouteInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteInfo createFromParcel(Parcel in) {
            return new RouteInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteInfo[] newArray(int size) {
            return new RouteInfo[size];
        }
    };
    private double distance;
    private String id;
    private String label;
    private int routeOption;
    private long time;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RouteInfo() {
    }

    protected RouteInfo(Parcel in) {
        this.id = in.readString();
        this.label = in.readString();
        this.routeOption = in.readInt();
        this.time = in.readLong();
        this.distance = in.readDouble();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.label);
        dest.writeInt(this.routeOption);
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

    public int getRouteOption() {
        return this.routeOption;
    }

    public void setRouteOption(int routeOption) {
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
