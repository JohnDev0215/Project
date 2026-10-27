package com.ts.lib.car.hardware.tyre;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes2.dex */
public interface CarVendorTyreManager {
    public static final int ID_INDIRECT_TYRE_CONFIRM = 557861384;
    public static final int ID_INDIRECT_TYRE_PRESSURE_STATE_LB_R = 557869846;
    public static final int ID_INDIRECT_TYRE_PRESSURE_STATE_LF_R = 557869844;
    public static final int ID_INDIRECT_TYRE_PRESSURE_STATE_RB_R = 557869847;
    public static final int ID_INDIRECT_TYRE_PRESSURE_STATE_RF_R = 557869845;
    public static final int ID_INDIRECT_TYRE_SYSTEM_STATE = 557861383;
    public static final int ID_SYSTEM_UNDERVOLTAGE_STATUS_R = 557875239;
    public static final int ID_TYRE_AIR_LEAK_STATE = 557861376;
    public static final int ID_TYRE_AREA_R = 557861394;
    public static final int ID_TYRE_BATTERY_STATE = 557861377;
    public static final int ID_TYRE_BATTERY_VALUE = 559958537;
    public static final int ID_TYRE_BODY_TYPE_R = 557861387;
    public static final int ID_TYRE_COLOR_BY_TYPE_LB_R = 557875236;
    public static final int ID_TYRE_COLOR_BY_TYPE_LF_R = 557875234;
    public static final int ID_TYRE_COLOR_BY_TYPE_RB_R = 557875237;
    public static final int ID_TYRE_COLOR_BY_TYPE_RF_R = 557875235;
    public static final int ID_TYRE_PRESSURE_STATE = 557861378;
    public static final int ID_TYRE_PRESSURE_SYS_FAIL_HINT_R = 557861392;
    public static final int ID_TYRE_PRESSURE_VALUE = 557861379;
    public static final int ID_TYRE_PRESSURE_VALUE_BY_TYPE_LB_R = 559972383;
    public static final int ID_TYRE_PRESSURE_VALUE_BY_TYPE_LF_R = 559972381;
    public static final int ID_TYRE_PRESSURE_VALUE_BY_TYPE_RB_R = 559972384;
    public static final int ID_TYRE_PRESSURE_VALUE_BY_TYPE_RF_R = 559972382;
    public static final int ID_TYRE_PRESSURE_WARN_HINT_R = 557861393;
    public static final int ID_TYRE_PRESSURE_WARN_LEFT_FRONT_R = 557861388;
    public static final int ID_TYRE_PRESSURE_WARN_LEFT_REAR_R = 557861390;
    public static final int ID_TYRE_PRESSURE_WARN_RIGHT_FRONT_R = 557861389;
    public static final int ID_TYRE_PRESSURE_WARN_RIGHT_REAR_R = 557861391;
    public static final int ID_TYRE_SIGNAL_STATE = 557861380;
    public static final int ID_TYRE_SYSTEM_STATE = 557861381;
    public static final int ID_TYRE_TEMPERATURE_STATE = 557861382;
    public static final int ID_TYRE_TEMPERATURE_VALUE_R = 557875238;
    public static final int ID_TYRE_TURN_STATE = 557861386;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.tyre";
    public static final String TAG = "CAR.VENDOR.TYRE";

    public interface OnCarTyreEventListener {
        void onTyreErrorEvent(int i, int i2);

        void onTyreEventChange(TyreEventChangeValue tyreEventChangeValue);
    }

    void addCarTyreEventListener(OnCarTyreEventListener onCarTyreEventListener) throws CarNotConnectedException;

    int getIndirectTyrePressureStateLbR() throws CarNotConnectedException;

    int getIndirectTyrePressureStateLfR() throws CarNotConnectedException;

    int getIndirectTyrePressureStateRbR() throws CarNotConnectedException;

    int getIndirectTyrePressureStateRfR() throws CarNotConnectedException;

    int getIndirectTyreSystemState() throws CarNotConnectedException;

    int getSystemUndervoltageStatusR() throws CarNotConnectedException;

    int getTyreAirLeakState() throws CarNotConnectedException;

    int getTyreAreaR() throws CarNotConnectedException;

    int getTyreBatteryState() throws CarNotConnectedException;

    float getTyreBatteryValue() throws CarNotConnectedException;

    int getTyreBodyTypeR() throws CarNotConnectedException;

    int getTyreColorByTypeLbR() throws CarNotConnectedException;

    int getTyreColorByTypeLfR() throws CarNotConnectedException;

    int getTyreColorByTypeRbR() throws CarNotConnectedException;

    int getTyreColorByTypeRfR() throws CarNotConnectedException;

    int getTyrePressureState() throws CarNotConnectedException;

    int getTyrePressureSysFailHintR() throws CarNotConnectedException;

    int getTyrePressureValue() throws CarNotConnectedException;

    float getTyrePressureValueByTypeLbR() throws CarNotConnectedException;

    float getTyrePressureValueByTypeLfR() throws CarNotConnectedException;

    float getTyrePressureValueByTypeRbR() throws CarNotConnectedException;

    float getTyrePressureValueByTypeRfR() throws CarNotConnectedException;

    int getTyrePressureWarnHintR() throws CarNotConnectedException;

    int getTyrePressureWarnLeftFrontR() throws CarNotConnectedException;

    int getTyrePressureWarnLeftRearR() throws CarNotConnectedException;

    int getTyrePressureWarnRightFrontR() throws CarNotConnectedException;

    int getTyrePressureWarnRightRearR() throws CarNotConnectedException;

    int getTyreSignalState() throws CarNotConnectedException;

    int getTyreSystemState() throws CarNotConnectedException;

    int getTyreTemperatureState() throws CarNotConnectedException;

    int getTyreTemperatureValueR() throws CarNotConnectedException;

    int getTyreTurnState() throws CarNotConnectedException;

    void removeCarTyreEventListener(OnCarTyreEventListener onCarTyreEventListener) throws CarNotConnectedException;

    void setIndirectTyreConfirm(int i) throws CarNotConnectedException;
}
