package com.ts.lib.car.hardware.engine;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes.dex */
public interface CarVendorEngineManager {
    public static final int ID_ENGINE_CODER = 557851657;
    public static final int ID_ENGINE_CONDITIONR = 557851654;
    public static final int ID_ENGINE_COOLANT_LEVELR = 557851652;
    public static final int ID_ENGINE_DISPLACEMENTR = 559948808;
    public static final int ID_ENGINE_POWERR = 557851650;
    public static final int ID_ENGINE_SPEEDR = 557851651;
    public static final int ID_OIL_LEVELR = 557851655;
    public static final int ID_SLIDE_RAIL_MOTOR_ELEC_PERCENT_R = 557851658;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.engine";
    public static final String TAG = "CAR.VENDOR.ENGINE";

    public interface OnCarEngineEventListener {
        void onEngineErrorEvent(int i, int i2);

        void onEngineEventChange(EngineEventChangeValue engineEventChangeValue);
    }

    void addCarEngineEventListener(OnCarEngineEventListener onCarEngineEventListener) throws CarNotConnectedException;

    int getEngineCodeR() throws CarNotConnectedException;

    int getEngineConditionR() throws CarNotConnectedException;

    int getEngineCoolantLevelR() throws CarNotConnectedException;

    float getEngineDisplacementR() throws CarNotConnectedException;

    int getEnginePowerR() throws CarNotConnectedException;

    int getEngineSpeedR() throws CarNotConnectedException;

    int getOilLevelR() throws CarNotConnectedException;

    int getSlideRailMotorElecPercentR() throws CarNotConnectedException;

    void removeCarEngineEventListener(OnCarEngineEventListener onCarEngineEventListener) throws CarNotConnectedException;

    void setEngineConditionS(int i) throws CarNotConnectedException;
}
