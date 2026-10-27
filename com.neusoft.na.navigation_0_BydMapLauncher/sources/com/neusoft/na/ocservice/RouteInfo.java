package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class RouteInfo implements Parcelable {
    public static final Parcelable.Creator<RouteInfo> CREATOR = new Parcelable.Creator<RouteInfo>() { // from class: com.neusoft.na.ocservice.RouteInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteInfo createFromParcel(Parcel parcel) {
            return new RouteInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteInfo[] newArray(int i) {
            return new RouteInfo[i];
        }
    };
    private double distance;
    private String distanceAuto;
    private String routeTag;
    private int strategy;
    private double time;
    private String timeAuto;
    private int tolls;
    private List<TrafficInfo> trafficInfos;
    private int trafficLights;
    private List<PoiInfo> viaPois;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected RouteInfo(Parcel parcel) {
        this.routeTag = "";
        this.strategy = -1;
        this.time = -1.0d;
        this.distance = -1.0d;
        this.timeAuto = "";
        this.distanceAuto = "";
        this.trafficLights = -1;
        this.tolls = -1;
        this.trafficInfos = new ArrayList();
        this.viaPois = new ArrayList();
        this.routeTag = parcel.readString();
        this.strategy = parcel.readInt();
        this.time = parcel.readDouble();
        this.distance = parcel.readDouble();
        this.timeAuto = parcel.readString();
        this.distanceAuto = parcel.readString();
        this.trafficLights = parcel.readInt();
        this.tolls = parcel.readInt();
        this.trafficInfos = parcel.createTypedArrayList(TrafficInfo.CREATOR);
        this.viaPois = parcel.createTypedArrayList(PoiInfo.CREATOR);
    }

    public RouteInfo() {
        this.routeTag = "";
        this.strategy = -1;
        this.time = -1.0d;
        this.distance = -1.0d;
        this.timeAuto = "";
        this.distanceAuto = "";
        this.trafficLights = -1;
        this.tolls = -1;
        this.trafficInfos = new ArrayList();
        this.viaPois = new ArrayList();
    }

    public String getRouteTag() {
        return this.routeTag;
    }

    public void setRouteTag(String str) {
        this.routeTag = str;
    }

    public int getStrategy() {
        return this.strategy;
    }

    public void setStrategy(int i) {
        this.strategy = i;
    }

    public double getTime() {
        return this.time;
    }

    public void setTime(double d) {
        this.time = d;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public String getTimeAuto() {
        return this.timeAuto;
    }

    public void setTimeAuto(String str) {
        this.timeAuto = str;
    }

    public String getDistanceAuto() {
        return this.distanceAuto;
    }

    public void setDistanceAuto(String str) {
        this.distanceAuto = str;
    }

    public int getTrafficLights() {
        return this.trafficLights;
    }

    public void setTrafficLights(int i) {
        this.trafficLights = i;
    }

    public int getTolls() {
        return this.tolls;
    }

    public void setTolls(int i) {
        this.tolls = i;
    }

    public List<TrafficInfo> getTrafficInfos() {
        return this.trafficInfos;
    }

    public void setTrafficInfos(List<TrafficInfo> list) {
        this.trafficInfos = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.routeTag);
        parcel.writeInt(this.strategy);
        parcel.writeDouble(this.time);
        parcel.writeDouble(this.distance);
        parcel.writeString(this.timeAuto);
        parcel.writeString(this.distanceAuto);
        parcel.writeInt(this.trafficLights);
        parcel.writeInt(this.tolls);
        parcel.writeTypedList(this.trafficInfos);
        parcel.writeTypedList(this.viaPois);
    }

    public List<PoiInfo> getViaPois() {
        return this.viaPois;
    }

    public void setViaPois(List<PoiInfo> list) {
        this.viaPois = list;
    }
}
