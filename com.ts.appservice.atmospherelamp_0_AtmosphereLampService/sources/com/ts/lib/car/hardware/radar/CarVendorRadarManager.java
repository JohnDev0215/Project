package com.ts.lib.car.hardware.radar;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes3.dex */
public interface CarVendorRadarManager {
    public static final int ID_NEW_REVERSE_RADAR_SWITCH_STATE_R = 557861140;
    public static final int ID_RADAR_OBSTACLE_DISTANCE_FL = 557861129;
    public static final int ID_RADAR_OBSTACLE_DISTANCE_FML = 557861130;
    public static final int ID_RADAR_OBSTACLE_DISTANCE_FMR = 557861131;
    public static final int ID_RADAR_OBSTACLE_DISTANCE_FR = 557861132;
    public static final int ID_RADAR_OBSTACLE_DISTANCE_RL = 557861133;
    public static final int ID_RADAR_OBSTACLE_DISTANCE_RML = 557861134;
    public static final int ID_RADAR_OBSTACLE_DISTANCE_RMR = 557861135;
    public static final int ID_RADAR_OBSTACLE_DISTANCE_RR = 557861136;
    public static final int ID_RADAR_PROBE_STATE_FL = 557861120;
    public static final int ID_RADAR_PROBE_STATE_FML = 557861121;
    public static final int ID_RADAR_PROBE_STATE_FMR = 557861122;
    public static final int ID_RADAR_PROBE_STATE_FR = 557861123;
    public static final int ID_RADAR_PROBE_STATE_MR = 557861124;
    public static final int ID_RADAR_PROBE_STATE_RL = 557861125;
    public static final int ID_RADAR_PROBE_STATE_RML = 557861126;
    public static final int ID_RADAR_PROBE_STATE_RMR = 557861127;
    public static final int ID_RADAR_PROBE_STATE_RR = 557861128;
    public static final int ID_REVERSE_RADAR_SWITCH_STATE_R = 557861137;
    public static final int ID_REVERSE_RADAR_SWITCH_STATE_S = 557926677;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.radar";
    public static final String TAG = "CAR.VENDOR.RADAR";

    public interface OnCarRadarEventListener {
        void onRadarErrorEvent(int i, int i2);

        void onRadarEventChange(RadarEventChangeValue radarEventChangeValue);
    }

    void addCarRadarEventListener(OnCarRadarEventListener onCarRadarEventListener) throws CarNotConnectedException;

    int getNewReverseRadarSwitchStateR() throws CarNotConnectedException;

    int getRadarObstacleDistanceFL() throws CarNotConnectedException;

    int getRadarObstacleDistanceFML() throws CarNotConnectedException;

    int getRadarObstacleDistanceFMR() throws CarNotConnectedException;

    int getRadarObstacleDistanceFR() throws CarNotConnectedException;

    int getRadarObstacleDistanceRL() throws CarNotConnectedException;

    int getRadarObstacleDistanceRML() throws CarNotConnectedException;

    int getRadarObstacleDistanceRMR() throws CarNotConnectedException;

    int getRadarObstacleDistanceRR() throws CarNotConnectedException;

    int getRadarProbeStateFL() throws CarNotConnectedException;

    int getRadarProbeStateFML() throws CarNotConnectedException;

    int getRadarProbeStateFMR() throws CarNotConnectedException;

    int getRadarProbeStateFR() throws CarNotConnectedException;

    int getRadarProbeStateMR() throws CarNotConnectedException;

    int getRadarProbeStateRL() throws CarNotConnectedException;

    int getRadarProbeStateRML() throws CarNotConnectedException;

    int getRadarProbeStateRMR() throws CarNotConnectedException;

    int getRadarProbeStateRR() throws CarNotConnectedException;

    int getReverseRadarSwitchStateR() throws CarNotConnectedException;

    void removeCarRadarEventListener(OnCarRadarEventListener onCarRadarEventListener) throws CarNotConnectedException;

    void setReverseRadarSwitchStateS(int[] iArr) throws CarNotConnectedException;
}
