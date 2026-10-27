package com.ecarx.xui.adaptapi.navigation.speed;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface ISpeedLimitingInfo {
    public static final int ROAD_COUNTY = 3;
    public static final int ROAD_EXPRESS = 6;
    public static final int ROAD_HIGH_SPEED = 0;
    public static final int ROAD_NATION = 1;
    public static final int ROAD_NOMAL = 9;
    public static final int ROAD_NO_NAV = 10;
    public static final int ROAD_PRIMARY = 7;
    public static final int ROAD_PROV = 2;
    public static final int ROAD_SECONDARY = 8;
    public static final int ROAD_TOWN = 4;
    public static final int ROAD_VILLAGE = 5;

    public interface ElecLimitingInfo {
        int getDist();

        double getLat();

        double getLong();

        int getSpeed();
    }

    public interface JctWayInfo {
        int getDist();

        double getLat();

        double getLong();
    }

    public interface RoadInfo {
        int getRoadClass();

        String getRoadName();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RoadType {
    }

    ElecLimitingInfo getElecLimitingInfo();

    JctWayInfo getJctWayInfo();

    RoadInfo getRoadInfo();
}
