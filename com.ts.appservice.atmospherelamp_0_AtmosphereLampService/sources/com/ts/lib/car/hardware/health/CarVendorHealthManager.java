package com.ts.lib.car.hardware.health;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes3.dex */
public interface CarVendorHealthManager {
    public static final int ID_BATTERY_HEALTH_STATUS_R = 557850679;
    public static final int ID_EPB_CONFIG = 557850644;
    public static final int ID_EPB_HINT_R = 557850662;
    public static final int ID_EPB_STATE_R = 557850647;
    public static final int ID_EPB_STATE_S = 557850646;
    public static final int ID_EPB_TYPE = 557850645;
    public static final int ID_FRONT_WIPER_CHECK_STATER = 557850636;
    public static final int ID_FRONT_WIPER_CHECK_STATES = 557850637;
    public static final int ID_OILUPDATESTATE = 557850641;
    public static final int ID_OIL_HINT = 557850624;
    public static final int ID_OIL_LEVEL = 557850635;
    public static final int ID_OIL_LIFETIME_NEW_BLADE_BATTERY_COOLANT_R = 557850666;
    public static final int ID_OIL_LIFETIME_NEW_BRAKE_FLUID_LIFE_BRAKE_FLUID_LIFE_R = 557850665;
    public static final int ID_OIL_LIFETIME_NEW_LONG_LIFE_COOLANT_LONG_LIFE_COOLANT_R = 557850667;
    public static final int ID_OIL_LIFETIME_NEW_OIL_LIFE_R = 557850663;
    public static final int ID_OIL_LIFETIME_NEW_TRANSMISSION_R = 557850664;
    public static final int ID_OIL_LIFE_TIME_BATTERY = 557850628;
    public static final int ID_OIL_LIFE_TIME_BRAKE = 557850627;
    public static final int ID_OIL_LIFE_TIME_ELEC = 557850629;
    public static final int ID_OIL_LIFE_TIME_ENGINE = 557850625;
    public static final int ID_OIL_LIFE_TIME_TRANSMISSION = 557850626;
    public static final int ID_OIL_MAINTAINANCE_HINT_BLADE_BATTERY_COOLANT = 557850657;
    public static final int ID_OIL_MAINTAINANCE_HINT_BRAKE_FLUID_LIFE = 557850655;
    public static final int ID_OIL_MAINTAINANCE_HINT_LONG_LIFE_COOLANT = 557850656;
    public static final int ID_OIL_MAINTAINANCE_HINT_OIL_LIFE = 557850653;
    public static final int ID_OIL_MAINTAINANCE_HINT_TRANSMISSION_OILLIFE = 557850654;
    public static final int ID_OIL_MAINTENANCE_DIALOG_HINT_BLADE_BATTERY_COOLANT_R = 557850671;
    public static final int ID_OIL_MAINTENANCE_DIALOG_HINT_BRAKE_FLUID_LIFE_R = 557850673;
    public static final int ID_OIL_MAINTENANCE_DIALOG_HINT_LONG_LIFE_COOLANT_R = 557850672;
    public static final int ID_OIL_MAINTENANCE_DIALOG_HINT_OIL_LIFE_R = 557850668;
    public static final int ID_OIL_MAINTENANCE_DIALOG_HINT_TRANSMISSION_R = 557850669;
    public static final int ID_OIL_MAINTENANCE_NOT_HINT_BLADE_BATTERY_COOLANT_S = 557850677;
    public static final int ID_OIL_MAINTENANCE_NOT_HINT_BRAKE_FLUID_LIFE_S = 557850676;
    public static final int ID_OIL_MAINTENANCE_NOT_HINT_LONG_LIFE_COOLANT_S = 557850678;
    public static final int ID_OIL_MAINTENANCE_NOT_HINT_OIL_LIFE_S = 557850674;
    public static final int ID_OIL_MAINTENANCE_NOT_HINT_TRANSMISSION_S = 557850675;
    public static final int ID_OIL_UPDATE_TAG_S = 557850643;
    public static final int ID_REAR_WIPER_CHECK_STATER = 557850638;
    public static final int ID_REAR_WIPER_CHECK_STATES = 557850639;
    public static final int ID_RESET_OIL_LEFE_TIME_BATTERY = 557850633;
    public static final int ID_RESET_OIL_LEFE_TIME_BRAKE = 557850632;
    public static final int ID_RESET_OIL_LEFE_TIME_ELEC = 557850634;
    public static final int ID_RESET_OIL_LEFE_TIME_ENGINE = 557850630;
    public static final int ID_RESET_OIL_LEFE_TIME_TRANSMISSION = 557850631;
    public static final int ID_RESET_OIL_LIFETIME_CHANGED_BATTERYR = 557850651;
    public static final int ID_RESET_OIL_LIFETIME_CHANGED_BREAKR = 557850650;
    public static final int ID_RESET_OIL_LIFETIME_CHANGED_ELECR = 557850652;
    public static final int ID_RESET_OIL_LIFETIME_CHANGED_ENGINER = 557850648;
    public static final int ID_RESET_OIL_LIFETIME_CHANGED_TRANS_MISSIONR = 557850649;
    public static final int ID_RESET_TIRE_PRESSURE = 557850640;
    public static final int ID_TRANSPORT_MODE_R = 557850659;
    public static final int ID_TRANSPORT_MODE_S = 557850658;
    public static final int ID_TRANSPORT_MODE_STATE_HINT_R = 557850660;
    public static final int ID_TRANSPORT_MODE_STATE_HINT_S = 557850661;
    public static final int ID_TYREFAULTLOCATION = 557850642;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.health";
    public static final String TAG = "CAR.VENDOR.HEALTH";

    public interface OnCarHealthEventListener {
        void onHealthErrorEvent(int i, int i2);

        void onHealthEventChange(HealthEventChangeValue healthEventChangeValue);
    }

    void addCarHealthEventListener(OnCarHealthEventListener onCarHealthEventListener) throws CarNotConnectedException;

    int getBatteryHealthStatusR() throws CarNotConnectedException;

    int getEPBConfig() throws CarNotConnectedException;

    int getEPBStateR() throws CarNotConnectedException;

    int getEPBTtpe() throws CarNotConnectedException;

    int getEpbHintR() throws CarNotConnectedException;

    int getFrontWiperCheckStateR() throws CarNotConnectedException;

    int getOilHint() throws CarNotConnectedException;

    int getOilLevel() throws CarNotConnectedException;

    int getOilLifetimeBattery() throws CarNotConnectedException;

    int getOilLifetimeBrake() throws CarNotConnectedException;

    int getOilLifetimeElec() throws CarNotConnectedException;

    int getOilLifetimeEngine() throws CarNotConnectedException;

    int getOilLifetimeNewBladeBatteryCoolantR() throws CarNotConnectedException;

    int getOilLifetimeNewBrakeFluidLifeBrakeFluidLifeR() throws CarNotConnectedException;

    int getOilLifetimeNewLongLifeCoolantLongLifeCoolantR() throws CarNotConnectedException;

    int getOilLifetimeNewOilLifeR() throws CarNotConnectedException;

    int getOilLifetimeNewTransmissionR() throws CarNotConnectedException;

    int getOilLifetimeTransmission() throws CarNotConnectedException;

    int getOilMaintainanceDialogHintBladeBatteryCoolantR() throws CarNotConnectedException;

    int getOilMaintainanceDialogHintBrakeFluidLifeR() throws CarNotConnectedException;

    int getOilMaintainanceDialogHintLongLifeCoolantR() throws CarNotConnectedException;

    int getOilMaintainanceDialogHintOilLifeR() throws CarNotConnectedException;

    int getOilMaintainanceDialogHintTransmissionR() throws CarNotConnectedException;

    int getOilMaintainanceHintBladeBatteryCoolant() throws CarNotConnectedException;

    int getOilMaintainanceHintBrakeFluidLife() throws CarNotConnectedException;

    int getOilMaintainanceHintLongLifeCoolant() throws CarNotConnectedException;

    int getOilMaintainanceHintOilLife() throws CarNotConnectedException;

    int getOilMaintainanceHintTransmissionOilLife() throws CarNotConnectedException;

    int getOilUpdateState() throws CarNotConnectedException;

    int getRearWiperCheckStateR() throws CarNotConnectedException;

    int getResetOilLifetimeChangedBatteryR() throws CarNotConnectedException;

    int getResetOilLifetimeChangedBreakR() throws CarNotConnectedException;

    int getResetOilLifetimeChangedElecR() throws CarNotConnectedException;

    int getResetOilLifetimeChangedEngineR() throws CarNotConnectedException;

    int getResetOilLifetimeChangedTransMissionR() throws CarNotConnectedException;

    int getTransportModeR() throws CarNotConnectedException;

    int getTransportModeStateHintR() throws CarNotConnectedException;

    int getTyreFaultLocation() throws CarNotConnectedException;

    void removeCarHealthEventListener(OnCarHealthEventListener onCarHealthEventListener) throws CarNotConnectedException;

    void resetOilLifetimeBattery(int i) throws CarNotConnectedException;

    void resetOilLifetimeBrake(int i) throws CarNotConnectedException;

    void resetOilLifetimeELec(int i) throws CarNotConnectedException;

    void resetOilLifetimeEngine(int i) throws CarNotConnectedException;

    void resetOilLifetimeTransmission(int i) throws CarNotConnectedException;

    void resetTirePressure(int i) throws CarNotConnectedException;

    void setEPBStateS(int i) throws CarNotConnectedException;

    void setFrontWiperCheckStateS(int i) throws CarNotConnectedException;

    void setOilMaintainanceNotHintBladeBatteryCoolantS(int i) throws CarNotConnectedException;

    void setOilMaintainanceNotHintBrakeFluidLifeS(int i) throws CarNotConnectedException;

    void setOilMaintainanceNotHintLongLifeCoolantS(int i) throws CarNotConnectedException;

    void setOilMaintainanceNotHintOilLifeS(int i) throws CarNotConnectedException;

    void setOilMaintainanceNotHintTransmissionS(int i) throws CarNotConnectedException;

    void setOilUpdateTagS(int i) throws CarNotConnectedException;

    void setRearWiperCheckStateS(int i) throws CarNotConnectedException;

    void setTransportModeS(int i) throws CarNotConnectedException;

    void setTransportModeStateHintS(int i) throws CarNotConnectedException;
}
