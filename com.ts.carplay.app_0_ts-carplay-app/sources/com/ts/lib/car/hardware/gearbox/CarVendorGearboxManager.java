package com.ts.lib.car.hardware.gearbox;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes.dex */
public interface CarVendorGearboxManager {
    public static final int ID_BRAKE_FLUID_LEVEL = 557857283;
    public static final int ID_BRAKE_PEDAL_STATE = 557857285;
    public static final int ID_BRAKE_PEDAL_STATUS_R = 557857293;
    public static final int ID_E_P_B_STATE_R = 557857288;
    public static final int ID_E_P_B_STATE_S = 557857289;
    public static final int ID_GEARBOX_MANUAL_MODE_LEVELR = 557857287;
    public static final int ID_GEAR_BOX_AUTO_MODE_TYPE = 557860357;
    public static final int ID_GEAR_BOX_AUTO_MODE_TYPE_TWO = 557857286;
    public static final int ID_GEAR_BOX_CODE = 557857291;
    public static final int ID_GEAR_BOX_TYPE = 557857292;
    public static final int ID_GEAR_R = 557857290;
    public static final int ID_PARK_BRAKE_SWITCH = 557857284;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.gearbox";
    public static final String TAG = "CAR.VENDOR.GEARBOX";

    public interface OnCarGearboxEventListener {
        void onGearboxErrorEvent(int i, int i2);

        void onGearboxEventChange(GearboxEventChangeValue gearboxEventChangeValue);
    }

    void addCarGearboxEventListener(OnCarGearboxEventListener onCarGearboxEventListener) throws CarNotConnectedException;

    int getBrakeFluidLevel() throws CarNotConnectedException;

    int getBrakePedalState() throws CarNotConnectedException;

    int getBrakePedalStatusR() throws CarNotConnectedException;

    int getEpbStateR() throws CarNotConnectedException;

    int getGearR() throws CarNotConnectedException;

    int getGearboxAutoModeType() throws CarNotConnectedException;

    int getGearboxAutoModeTypeTwo() throws CarNotConnectedException;

    int getGearboxCode() throws CarNotConnectedException;

    int getGearboxManualModeLevelR() throws CarNotConnectedException;

    int getGearboxType() throws CarNotConnectedException;

    int getParkBrakeSwitch() throws CarNotConnectedException;

    void removeCarGearboxEventListener(OnCarGearboxEventListener onCarGearboxEventListener) throws CarNotConnectedException;

    void setEpbStateS(int i) throws CarNotConnectedException;
}
