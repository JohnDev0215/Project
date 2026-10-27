package com.ecarx.xui.adaptapi.vehicle.ems;

import com.ecarx.xui.adaptapi.FunctionStatus;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IDrivingMode {
    public static final int DM_COMFORT = 1;
    public static final int DM_ECO = 2;
    public static final int DM_HDC = 9;
    public static final int DM_HYBRID = 10;
    public static final int DM_MUD = 6;
    public static final int DM_OFF_ROAD = 4;
    public static final int DM_PHEV = 12;
    public static final int DM_POWER = 11;
    public static final int DM_PURE = 8;
    public static final int DM_ROCK = 7;
    public static final int DM_SNOW = 5;
    public static final int DM_SPORT = 3;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DrivingMode {
    }

    public interface DrivingModeChangeListener {
        void onDrivingModeChange(int i, boolean z);
    }

    void attachDrivingModeChangeListener(DrivingModeChangeListener drivingModeChangeListener);

    void detachDrivingModeChangeListener(DrivingModeChangeListener drivingModeChangeListener);

    int getDrivingMode();

    FunctionStatus getDrivingModeStatus(int i);

    int[] getSupportDrivingModes();

    boolean isDrivingModeTriggerByHardwareKey();

    boolean setDrivingMode(int i);
}
