package com.neusoft.na.ocservice.greatwallsdk.bean;

/* JADX INFO: loaded from: classes2.dex */
public class TBTData {
    private int distance;
    private int turnId;
    private String roadName = "";
    private String distanceString = "";
    private String exit = "";

    public String getRoadName() {
        return this.roadName;
    }

    public void setRoadName(String str) {
        this.roadName = str;
    }

    public int getTurnId() {
        return this.turnId;
    }

    public void setTurnId(int i) {
        this.turnId = i;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public String getDistanceString() {
        return this.distanceString;
    }

    public void setDistanceString(String str) {
        this.distanceString = str;
    }

    public String getExit() {
        return this.exit;
    }

    public void setExit(String str) {
        this.exit = str;
    }
}
