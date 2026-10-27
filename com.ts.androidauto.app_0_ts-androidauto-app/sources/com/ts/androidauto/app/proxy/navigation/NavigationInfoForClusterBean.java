package com.ts.androidauto.app.proxy.navigation;

/* JADX INFO: loaded from: classes2.dex */
public class NavigationInfoForClusterBean {
    private String mDistToDest;
    private String mDistToDestTime;
    private String mDistToDestUnit;
    private int mMapType;
    private String mNextDistUnit;
    private String mNextNextDistUnit;
    private String mNextNextRoadDist;
    private int mNextNextTbtIcon;
    private String mNextRoadDist;
    private String mNextRoadName;
    private int mNextTbtIcon;
    private String mToDestDays;
    private int mToDestHours;
    private int mToDestMinutes;
    private int mToDestSeconds;
    private String mToDestTime;

    public NavigationInfoForClusterBean(int mapType) {
        this.mMapType = mapType;
    }

    public int getMapType() {
        return this.mMapType;
    }

    public void setMapType(int mapType) {
        this.mMapType = mapType;
    }

    public int getNextTbtIcon() {
        return this.mNextTbtIcon;
    }

    public void setNextTbtIcon(int nextTbtIcon) {
        this.mNextTbtIcon = nextTbtIcon;
    }

    public String getNextRoadName() {
        return this.mNextRoadName;
    }

    public void setNextRoadName(String nextRoadName) {
        this.mNextRoadName = nextRoadName;
    }

    public String getNextRoadDist() {
        return this.mNextRoadDist;
    }

    public void setNextRoadDist(String nextRoadDist) {
        this.mNextRoadDist = nextRoadDist;
    }

    public String getNextDistUnit() {
        return this.mNextDistUnit;
    }

    public void setNextDistUnit(String nextDistUnit) {
        this.mNextDistUnit = nextDistUnit;
    }

    public int getNextNextTbtIcon() {
        return this.mNextNextTbtIcon;
    }

    public void setNextNextTbtIcon(int nextNextTbtIcon) {
        this.mNextNextTbtIcon = nextNextTbtIcon;
    }

    public String getNextNextRoadDist() {
        return this.mNextNextRoadDist;
    }

    public void setNextNextRoadDist(String nextNextRoadDist) {
        this.mNextNextRoadDist = nextNextRoadDist;
    }

    public String getNextNextDistUnit() {
        return this.mNextNextDistUnit;
    }

    public void setNextNextDistUnit(String nextNextDistUnit) {
        this.mNextNextDistUnit = nextNextDistUnit;
    }

    public String getDistToDest() {
        return this.mDistToDest;
    }

    public void setDistToDest(String distToDest) {
        this.mDistToDest = distToDest;
    }

    public String getDistToDestUnit() {
        return this.mDistToDestUnit;
    }

    public void setDistToDestUnit(String distToDestUnit) {
        this.mDistToDestUnit = distToDestUnit;
    }

    public String getToDestTime() {
        return this.mToDestTime;
    }

    public void setToDestTime(String toDestTime) {
        this.mToDestTime = toDestTime;
    }

    public String getToDestDays() {
        return this.mToDestDays;
    }

    public void setToDestDays(String toDestDays) {
        this.mToDestDays = toDestDays;
    }

    public int getToDestHours() {
        return this.mToDestHours;
    }

    public void setToDestHours(int toDestHours) {
        this.mToDestHours = toDestHours;
    }

    public int getToDestMinutes() {
        return this.mToDestMinutes;
    }

    public void setToDestMinutes(int toDestMinutes) {
        this.mToDestMinutes = toDestMinutes;
    }

    public int getToDestSeconds() {
        return this.mToDestSeconds;
    }

    public void setToDestSeconds(int toDestSeconds) {
        this.mToDestSeconds = toDestSeconds;
    }

    public String getDistToDestTime() {
        return this.mDistToDestTime;
    }

    public void setDistToDestTime(String distToDestTime) {
        this.mDistToDestTime = distToDestTime;
    }

    public String toString() {
        return "NavigationInfoForClusterBean{mMapType=" + this.mMapType + ", mNextTbtIcon=" + this.mNextTbtIcon + ", mNextRoadName='" + this.mNextRoadName + "', mNextRoadDist=" + this.mNextRoadDist + ", mNextDistUnit=" + this.mNextDistUnit + ", mNextNextTbtIcon=" + this.mNextNextTbtIcon + ", mNextNextRoadDist=" + this.mNextNextRoadDist + ", mNextNextDistUnit=" + this.mNextNextDistUnit + ", mDistToDest=" + this.mDistToDest + ", mDistToDestUnit=" + this.mDistToDestUnit + ", mToDestTime=" + this.mToDestTime + ", mToDestDays=" + this.mToDestDays + ", mToDestHours=" + this.mToDestHours + ", mToDestMinutes=" + this.mToDestMinutes + ", mToDestSeconds=" + this.mToDestSeconds + ", mDistToDestTime=" + this.mDistToDestTime + '}';
    }
}
