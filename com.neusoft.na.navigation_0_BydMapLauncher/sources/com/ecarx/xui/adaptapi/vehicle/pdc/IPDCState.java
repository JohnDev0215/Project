package com.ecarx.xui.adaptapi.vehicle.pdc;

/* JADX INFO: loaded from: classes2.dex */
public interface IPDCState {
    public static final int RADAR_FRONT_LEFT = 1;
    public static final int RADAR_FRONT_MIDDLE_LEFT = 2;
    public static final int RADAR_FRONT_MIDDLE_RIGHT = 3;
    public static final int RADAR_FRONT_RIGHT = 4;
    public static final int RADAR_REAR_LEFT = 5;
    public static final int RADAR_REAR_MIDDLE_LEFT = 6;
    public static final int RADAR_REAR_MIDDLE_RIGHT = 8;
    public static final int RADAR_REAR_RIGHT = 7;

    int[] getChannelDistanceRange(int i);

    int getFrontChannelCount();

    int getFrontLeftDistance();

    int getFrontMiddleLeftDistance();

    int getFrontMiddleRightDistance();

    int getFrontRightDistance();

    int getPDCWorkMode();

    int getRearCHannelCount();

    int getRearLeftDistance();

    int getRearMiddleLeftDistance();

    int getRearMiddleRightDistance();

    int getRearRightDistance();
}
