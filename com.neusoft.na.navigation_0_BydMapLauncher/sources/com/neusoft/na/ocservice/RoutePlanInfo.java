package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class RoutePlanInfo implements Parcelable {
    public static final Parcelable.Creator<RoutePlanInfo> CREATOR = new Parcelable.Creator<RoutePlanInfo>() { // from class: com.neusoft.na.ocservice.RoutePlanInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RoutePlanInfo createFromParcel(Parcel parcel) {
            return new RoutePlanInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RoutePlanInfo[] newArray(int i) {
            return new RoutePlanInfo[i];
        }
    };
    private int currentRouteIndex;
    private PoiInfo poiInfo;
    private List<RouteInfo> routeInfos;
    private PoiInfo startPoi;
    private List<PoiInfo> viaPois;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RoutePlanInfo() {
    }

    protected RoutePlanInfo(Parcel parcel) {
        this.currentRouteIndex = parcel.readInt();
        this.poiInfo = (PoiInfo) parcel.readParcelable(PoiInfo.class.getClassLoader());
        this.routeInfos = parcel.createTypedArrayList(RouteInfo.CREATOR);
        this.startPoi = (PoiInfo) parcel.readParcelable(PoiInfo.class.getClassLoader());
        this.viaPois = parcel.createTypedArrayList(PoiInfo.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.currentRouteIndex);
        parcel.writeParcelable(this.poiInfo, i);
        parcel.writeTypedList(this.routeInfos);
        parcel.writeParcelable(this.startPoi, i);
        parcel.writeTypedList(this.viaPois);
    }

    public int getCurrentRouteIndex() {
        return this.currentRouteIndex;
    }

    public void setCurrentRouteIndex(int i) {
        this.currentRouteIndex = i;
    }

    public PoiInfo getPoiInfo() {
        return this.poiInfo;
    }

    public void setPoiInfo(PoiInfo poiInfo) {
        this.poiInfo = poiInfo;
    }

    public List<RouteInfo> getRouteInfos() {
        return this.routeInfos;
    }

    public void setRouteInfos(List<RouteInfo> list) {
        this.routeInfos = list;
    }

    public PoiInfo getStartPoi() {
        return this.startPoi;
    }

    public void setStartPoi(PoiInfo poiInfo) {
        this.startPoi = poiInfo;
    }

    public List<PoiInfo> getViaPois() {
        return this.viaPois;
    }

    public void setViaPois(List<PoiInfo> list) {
        this.viaPois = list;
    }
}
