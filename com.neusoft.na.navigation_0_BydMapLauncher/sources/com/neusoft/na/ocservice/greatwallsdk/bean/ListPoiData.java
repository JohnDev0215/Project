package com.neusoft.na.ocservice.greatwallsdk.bean;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ListPoiData {
    private List<PoiData> wayPoints = new ArrayList();

    public List<PoiData> getWayPoints() {
        return this.wayPoints;
    }

    public void setWayPoints(List<PoiData> list) {
        this.wayPoints = list;
    }

    public void addRecord(PoiData poiData) {
        this.wayPoints.add(poiData);
    }
}
