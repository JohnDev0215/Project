package com.ts.carplay.linkadapter.iap.data;

/* JADX INFO: loaded from: classes.dex */
public class RouteGuidanceManeuverUpdate {
    private static final int ANGLE_INVALID = 65535;
    private String mAfterManeuverRoadName;
    private String mDistanceBetweenManeuverDisplayStr;
    private String mManeuverDescription;
    private int mDisplayComponentId = -1;
    private int mIndex = -1;
    private int mManeuverType = -1;
    private long mDistanceBetweenManeuver = -1;
    private int mDistanceBetweenManeuverDisplayUnits = -1;
    private int mDrivingSide = -1;
    private int mJunctionType = -1;
    private int mJunctionElementAngle = 65535;
    private int mJunctionElementExitAngle = 65535;

    public int getDisplayComponentId() {
        return this.mDisplayComponentId;
    }

    public void setDisplayComponentId(int id) {
        this.mDisplayComponentId = id;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void setIndex(int index) {
        this.mIndex = index;
    }

    public String getManeuverDescription() {
        return this.mManeuverDescription;
    }

    public void setManeuverDescription(String description) {
        this.mManeuverDescription = description;
    }

    public int getManeuverType() {
        return this.mManeuverType;
    }

    public void setManeuverType(int type) {
        this.mManeuverType = type;
    }

    public String getAfterManeuverRoadName() {
        return this.mAfterManeuverRoadName;
    }

    public void setAfterManeuverRoadName(String roadName) {
        this.mAfterManeuverRoadName = roadName;
    }

    public long getDistanceBetweenManeuver() {
        return this.mDistanceBetweenManeuver;
    }

    public void setDistanceBetweenManeuver(long distance) {
        this.mDistanceBetweenManeuver = distance;
    }

    public String getDistanceBetweenManeuverDisplayStr() {
        return this.mDistanceBetweenManeuverDisplayStr;
    }

    public void setDistanceBetweenManeuverDisplayStr(String displayStr) {
        this.mDistanceBetweenManeuverDisplayStr = displayStr;
    }

    public int getDistanceBetweenManeuverDisplayUnits() {
        return this.mDistanceBetweenManeuverDisplayUnits;
    }

    public void setDistanceBetweenManeuverDisplayUnits(int displayUnits) {
        this.mDistanceBetweenManeuverDisplayUnits = displayUnits;
    }

    public int getDrivingSide() {
        return this.mDrivingSide;
    }

    public void setDrivingSide(int side) {
        this.mDrivingSide = side;
    }

    public int getJunctionType() {
        return this.mJunctionType;
    }

    public void setJunctionType(int type) {
        this.mJunctionType = type;
    }

    public int getJunctionElementAngle() {
        return this.mJunctionElementAngle;
    }

    public void setJunctionElementAngle(int angle) {
        this.mJunctionElementAngle = angle;
    }

    public int getJunctionElementExitAngle() {
        return this.mJunctionElementExitAngle;
    }

    public void setJunctionElementExitAngle(int angle) {
        this.mJunctionElementExitAngle = angle;
    }
}
