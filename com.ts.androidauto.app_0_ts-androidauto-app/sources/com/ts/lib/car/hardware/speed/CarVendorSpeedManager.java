package com.ts.lib.car.hardware.speed;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes2.dex */
public interface CarVendorSpeedManager {
    public static final int ID_ACCELERATE_DEEPNESSR = 557845760;
    public static final int ID_ACCELERATE_DEEPNESS_SIGNIFICANT_BIT_R = 557845764;
    public static final int ID_BRAKE_DEEPNESSR = 557845761;
    public static final int ID_CURRENT_SPEEDR = 557867014;
    public static final int ID_IPB_VEHICLE_SPEED_STATUS_R = 559942915;
    public static final int ID_SPEED_ALIVE_COUNTER_R = 559942916;
    public static final int ID_SPEED_VALUE_R = 559957505;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.speed";
    public static final String TAG = "CAR.VENDOR.SPEED";

    public interface OnCarSpeedEventListener {
        void onSpeedErrorEvent(int i, int i2);

        void onSpeedEventChange(SpeedEventChangeValue speedEventChangeValue);
    }

    void addCarSpeedEventListener(OnCarSpeedEventListener onCarSpeedEventListener) throws CarNotConnectedException;

    int getAccelerateDeepnessR() throws CarNotConnectedException;

    int getAccelerateDeepnessSignificantBitR() throws CarNotConnectedException;

    int getBrakeDeepnessR() throws CarNotConnectedException;

    int getCurrentSpeedR() throws CarNotConnectedException;

    float getIpbVehicleSpeedStatusR() throws CarNotConnectedException;

    float getSpeedAliveCounterR() throws CarNotConnectedException;

    float getSpeedValueR() throws CarNotConnectedException;

    void removeCarSpeedEventListener(OnCarSpeedEventListener onCarSpeedEventListener) throws CarNotConnectedException;
}
