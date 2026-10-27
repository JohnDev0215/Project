package com.ts.lib.car.hardware.carmode;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes3.dex */
public interface CarVendorCarmodeManager {
    public static final int ID_AIR_CTRL_CHANGE_EVENT_R = 557871106;
    public static final int ID_AIR_CTRL_PRESS_EVENT_R = 557871107;
    public static final int ID_CAR_ENGINE_TYPER = 557871105;
    public static final int ID_ENGINE_ENABLES = 557871108;
    public static final int ID_SHOW_CAR_GEAR_CHANGER = 557871109;
    public static final int ID_SHOW_CAR_MODEL_TYPER = 557871104;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.carmode";
    public static final String TAG = "CAR.VENDOR.CARMODE";

    public interface OnCarCarmodeEventListener {
        void onCarmodeErrorEvent(int i, int i2);

        void onCarmodeEventChange(CarmodeEventChangeValue carmodeEventChangeValue);
    }

    void addCarCarmodeEventListener(OnCarCarmodeEventListener onCarCarmodeEventListener) throws CarNotConnectedException;

    int getAirCtrlChangeEventR() throws CarNotConnectedException;

    int getAirCtrlPressEventR() throws CarNotConnectedException;

    int getCarEngineTypeR() throws CarNotConnectedException;

    int getShowCarGearChangeR() throws CarNotConnectedException;

    int getShowCarModelTypeR() throws CarNotConnectedException;

    void removeCarCarmodeEventListener(OnCarCarmodeEventListener onCarCarmodeEventListener) throws CarNotConnectedException;

    void setEngineEnableS(int i) throws CarNotConnectedException;
}
