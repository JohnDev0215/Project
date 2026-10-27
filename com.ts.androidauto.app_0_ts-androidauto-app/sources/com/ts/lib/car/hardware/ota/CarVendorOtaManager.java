package com.ts.lib.car.hardware.ota;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes2.dex */
public interface CarVendorOtaManager {
    public static final int ID_AP_GET_STR_RESET_CYCLE_REQ = 557903886;
    public static final int ID_BACKUP_AREA_DOWNLOAD = 557843610;
    public static final int ID_BACKUP_AREA_DOWNLOAD_STATER = 557860385;
    public static final int ID_BATTERY_MANAGE_DEVICE_STATE = 557860378;
    public static final int ID_BATTERY_VOLTAGE = 557860364;
    public static final int ID_BATTERY_VOLTAGE_SECOND = 557860382;
    public static final int ID_BCM_OTA_STATE = 557860370;
    public static final int ID_BURGLAR_STATE = 557860355;
    public static final int ID_CHARGE_AND_DISCHARGE_SYSTEM_STATE = 557860380;
    public static final int ID_CHARGE_STATE = 557860365;
    public static final int ID_DC_STATE = 557860362;
    public static final int ID_DISCHARGE_MAIN_CONTROLLER_STATE = 557860379;
    public static final int ID_DISCHARGE_STATE = 557860366;
    public static final int ID_DISCHARGE_STATE_VTOL = 557860383;
    public static final int ID_DOOR_LOCK_AREA_LEFT_FRONT = 557860391;
    public static final int ID_ECL_POWER = 557860372;
    public static final int ID_ENGINE_HOOD_STATE = 557860368;
    public static final int ID_ENVIRONMENT_TEMP = 557860356;
    public static final int ID_ENVIRONMENT_TEMP_SECOND = 557860381;
    public static final int ID_EPB_STATE = 557860358;
    public static final int ID_GEAR = 557857290;
    public static final int ID_HIGH_VOLTAGE_CMD = 557860377;
    public static final int ID_IG1_RELAY_STATE_R = 557860393;
    public static final int ID_IG3_RELAY_STATE = 557860369;
    public static final int ID_IG4_RELAY_STATE_R = 557860392;
    public static final int ID_IGNITION_STATE = 557843465;
    public static final int ID_MULTIMEDIA_SELF_UPDATE_S = 557860390;
    public static final int ID_OTA_ENERGY_TYPER = 557862951;
    public static final int ID_OTA_MODEL = 557860375;
    public static final int ID_OTA_POWER = 557860376;
    public static final int ID_OTA_POWER_LEVEL = 557862927;
    public static final int ID_PROHIBIT_POWER_ON_STATE_R = 557860389;
    public static final int ID_QUIT_CHARGE_AND_DISCHARGE = 557860374;
    public static final int ID_QUIT_SMART_CHARGE = 557860373;
    public static final int ID_SMART_CHARGE_STATE = 557860367;
    public static final int ID_SMART_CHARGE_STATE_SECOND = 557860384;
    public static final int ID_SMART_POWER = 557860371;
    public static final int ID_SMART_POWER_EXCEPTION = 557860361;
    public static final int ID_SMART_POWER_STATE = 557860360;
    public static final int ID_SMART_POWER_USABLE = 557860359;
    public static final int ID_SOC_VALUE = 557860363;
    public static final int ID_SOC_VALUER = 557860386;
    public static final int ID_START_KEY_STATE_R = 557860388;
    public static final int ID_TURN_OFF_STATE = 557860354;
    public static final int ID_USB_UPDATE_STATE_S = 557860394;
    public static final int ID_VEHICLE_SPEED = 559957505;
    public static final int ID_VEHICLE_TYPE = 557860352;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.ota";
    public static final String TAG = "CAR.VENDOR.OTA";

    public interface OnCarOtaEventListener {
        void onOtaErrorEvent(int i, int i2);

        void onOtaEventChange(OtaEventChangeValue otaEventChangeValue);
    }

    void addCarOtaEventListener(OnCarOtaEventListener onCarOtaEventListener) throws CarNotConnectedException;

    int getBackupAreaDownloadStateR() throws CarNotConnectedException;

    int getBatteryManagementDeviceState() throws CarNotConnectedException;

    int getBatteryVoltage() throws CarNotConnectedException;

    int getBatteryVoltageSecond() throws CarNotConnectedException;

    int getBcmOtaState() throws CarNotConnectedException;

    int getBurglarState() throws CarNotConnectedException;

    int getChargeAndDischargeSystemState() throws CarNotConnectedException;

    int getChargeState() throws CarNotConnectedException;

    int getDcState() throws CarNotConnectedException;

    int getDischargeMainContactorState() throws CarNotConnectedException;

    int getDischargeState() throws CarNotConnectedException;

    int getDischargeStateVtol() throws CarNotConnectedException;

    int getDoorLockStatusAreaLeftFront() throws CarNotConnectedException;

    int getEngineHoodState() throws CarNotConnectedException;

    int getEnvironmentTemp() throws CarNotConnectedException;

    int getEnvironmentTempSecond() throws CarNotConnectedException;

    int getEpbState() throws CarNotConnectedException;

    int getGear() throws CarNotConnectedException;

    int getIg1RelayStateR() throws CarNotConnectedException;

    int getIg3RelayState() throws CarNotConnectedException;

    int getIg4RelayStateR() throws CarNotConnectedException;

    int getIgnitionState() throws CarNotConnectedException;

    int getOtaEnergyTypeR() throws CarNotConnectedException;

    int getOtaPowerLevel() throws CarNotConnectedException;

    int getProhibitPowerOnStateR() throws CarNotConnectedException;

    int getSmartChargeState() throws CarNotConnectedException;

    int getSmartChargeStateSecond() throws CarNotConnectedException;

    int getSmartPowerException() throws CarNotConnectedException;

    int getSmartPowerState() throws CarNotConnectedException;

    int getSmartPowerUsable() throws CarNotConnectedException;

    int getSocValue() throws CarNotConnectedException;

    int getSocValueR() throws CarNotConnectedException;

    int getStartKeyStateR() throws CarNotConnectedException;

    int getStrResetCycleReqR() throws CarNotConnectedException;

    int getTurnOffState() throws CarNotConnectedException;

    float getVehicleSpeed() throws CarNotConnectedException;

    int getVehicleType() throws CarNotConnectedException;

    void removeCarOtaEventListener(OnCarOtaEventListener onCarOtaEventListener) throws CarNotConnectedException;

    void setBackupAreaDownload(int i) throws CarNotConnectedException;

    void setEclPower(int i) throws CarNotConnectedException;

    void setHighVoltageCmd(int i) throws CarNotConnectedException;

    void setMultimediaSelfUpdateS(int i) throws CarNotConnectedException;

    void setOtaModel(int i) throws CarNotConnectedException;

    void setOtaPower(int i) throws CarNotConnectedException;

    void setQuitChargeAndDischarge(int i) throws CarNotConnectedException;

    void setQuitSmartCharge(int i) throws CarNotConnectedException;

    void setSmartPower(int i) throws CarNotConnectedException;

    void setUsbUpdateStateS(int i) throws CarNotConnectedException;
}
