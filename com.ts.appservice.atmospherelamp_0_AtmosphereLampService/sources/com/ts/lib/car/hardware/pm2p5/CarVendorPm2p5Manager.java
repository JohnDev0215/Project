package com.ts.lib.car.hardware.pm2p5;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes3.dex */
public interface CarVendorPm2p5Manager {
    public static final int ID_PM2P5_ANION_DETECT_STATE = 557854730;
    public static final int ID_PM2P5_ANION_STATE = 557854727;
    public static final int ID_PM2P5_CHECK_STATE = 557854724;
    public static final int ID_PM2P5_CHECK_STATE_IN = 557854722;
    public static final int ID_PM2P5_CHECK_STATE_OUT = 557854723;
    public static final int ID_PM2P5_DUAL_CHANNEL_DETECT_STATER = 557854720;
    public static final int ID_PM2P5_DUAL_CHANNEL_DETECT_STATES = 557854721;
    public static final int ID_PM2P5_LEVEL_IN = 557854728;
    public static final int ID_PM2P5_LEVEL_OUT = 557854729;
    public static final int ID_PM2P5_VALUE_IN = 557854725;
    public static final int ID_PM2P5_VALUE_OUT = 557854726;
    public static final int ID_PM2P5_WARNING_INFO = 557854731;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.pm2p5";
    public static final String TAG = "CAR.VENDOR.PM2P5";

    public interface OnCarPm2p5EventListener {
        void onPm2p5ErrorEvent(int i, int i2);

        void onPm2p5EventChange(Pm2p5EventChangeValue pm2p5EventChangeValue);
    }

    void addCarPm2p5EventListener(OnCarPm2p5EventListener onCarPm2p5EventListener) throws CarNotConnectedException;

    int getPm2p5AnionState() throws CarNotConnectedException;

    int getPm2p5CheckStateIn() throws CarNotConnectedException;

    int getPm2p5CheckStateOut() throws CarNotConnectedException;

    int getPm2p5DualChannelDetectStateR() throws CarNotConnectedException;

    int getPm2p5LevelIn() throws CarNotConnectedException;

    int getPm2p5LevelOut() throws CarNotConnectedException;

    int getPm2p5ValueIn() throws CarNotConnectedException;

    int getPm2p5ValueOut() throws CarNotConnectedException;

    int getPm2p5WarningInfo() throws CarNotConnectedException;

    void removeCarPm2p5EventListener(OnCarPm2p5EventListener onCarPm2p5EventListener) throws CarNotConnectedException;

    void setPm2p5AnionDetectState(int i) throws CarNotConnectedException;

    void setPm2p5CheckState(int i) throws CarNotConnectedException;

    void setPm2p5DualChannelDetectStateS(int i) throws CarNotConnectedException;
}
