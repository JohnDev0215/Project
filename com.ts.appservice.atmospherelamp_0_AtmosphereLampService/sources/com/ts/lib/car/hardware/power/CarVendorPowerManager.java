package com.ts.lib.car.hardware.power;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes3.dex */
public interface CarVendorPowerManager {
    public static final int ID_AP_CONTROL_DISPLAY_STATE_REQ = 557969425;
    public static final int ID_AP_GET_OFF_SCREEN_FLAG_REQ = 557903891;
    public static final int ID_AP_GET_OFF_SCREEN_FLAG_RES = 557969428;
    public static final int ID_AP_POWER_BCALL_STATUS_REQ = 557903875;
    public static final int ID_AP_POWER_MODE_REQUEST = 557969412;
    public static final int ID_AP_POWER_SHUTDOWN_REQUEST = 557903877;
    public static final int ID_AP_POWER_STATE_REPORT = 289475073;
    public static final int ID_AP_POWER_STATE_REQ = 289475072;
    public static final int ID_AP_SET_OFF_SCREEN_FLAG = 557969426;
    public static final int ID_SYS_BOSCH_RESET = 557903885;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.power";
    public static final String TAG = "CAR.VENDOR.POWER";

    public interface OnCarPowerEventListener {
        void onPowerErrorEvent(int i, int i2);

        void onPowerEventChange(PowerEventChangeValue powerEventChangeValue);
    }

    void addCarPowerEventListener(OnCarPowerEventListener onCarPowerEventListener) throws CarNotConnectedException;

    int[] getIviPowerModeChanged() throws CarNotConnectedException;

    int[] getIviPowerStateChanged() throws CarNotConnectedException;

    void queryOffScreenFlag(int i) throws CarNotConnectedException;

    void removeCarPowerEventListener(OnCarPowerEventListener onCarPowerEventListener) throws CarNotConnectedException;

    void reset(int i) throws CarNotConnectedException;

    void setDisplayState(int i, int i2) throws CarNotConnectedException;

    void setDisplayState(int i, int i2, int i3) throws CarNotConnectedException;

    void setOffScreenFlag(int[] iArr) throws CarNotConnectedException;

    void setVehicleBcallStatusReq(int i) throws CarNotConnectedException;

    void setVehiclePowerShutdownRequest(int i) throws CarNotConnectedException;

    void setVehiclePowerStateReport(int[] iArr) throws CarNotConnectedException;
}
