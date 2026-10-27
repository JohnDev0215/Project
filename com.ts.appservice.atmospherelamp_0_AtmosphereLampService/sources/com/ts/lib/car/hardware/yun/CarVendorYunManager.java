package com.ts.lib.car.hardware.yun;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes3.dex */
public interface CarVendorYunManager {
    public static final int ID_ACHMCACOPENRSLTFDBCKINFOR = 557846055;
    public static final int ID_ACHMCACSHUTDOWNFAILREASON = 557846058;
    public static final int ID_ACHMC_AC_FAILURE_CAUSE = 557846056;
    public static final int ID_AV_CLOSE_RESULTS_FEEDBACK = 557846057;
    public static final int ID_CONTRLRESULTSFBINFO497S16 = 557846051;
    public static final int ID_COPILOTSEATVTILATSTAT4FA = 557846062;
    public static final int ID_COPILOT_SEAT_HEAT_STAT_4FA = 557846063;
    public static final int ID_ENG_START_RESULT_FDBK_INFO = 557846053;
    public static final int ID_ENG_STOP_RESULT_FDBK_INFO = 557846054;
    public static final int ID_FAILURE_REASONS_497S16 = 557846052;
    public static final int ID_FUNCTION_NUMBER_497 = 557846050;
    public static final int ID_LEFT_BACK_DOOR = 557846048;
    public static final int ID_LEFT_FRONT_DOOR = 557846046;
    public static final int ID_PILOT_SEAT_HEAT_STAT = 557846061;
    public static final int ID_PILOT_SEAT_VTILAT_STAT = 557846060;
    public static final int ID_REMAIN_BATTERY = 557846044;
    public static final int ID_REMOTE_AC_CONTROL_CYCLE_MODE = 557846031;
    public static final int ID_REMOTE_AC_CONTROL_DRIVER_TEMPERATURE = 557846025;
    public static final int ID_REMOTE_AC_CONTROL_MANNER = 557846030;
    public static final int ID_REMOTE_AC_CONTROL_START = 557846024;
    public static final int ID_REMOTE_AC_CONTROL_START_TIME = 557846026;
    public static final int ID_REMOTE_AC_CONTROL_SWITCH = 557846029;
    public static final int ID_REMOTE_AC_CONTROL_WIND_PATTERN = 557846028;
    public static final int ID_REMOTE_CONTROL33_S = 557846041;
    public static final int ID_REMOTE_CONTROL_LOCKOUT = 557846018;
    public static final int ID_REMOTE_CONTROL_SEARCH_FLASHING_LIGHT = 557846022;
    public static final int ID_REMOTE_CONTROL_SEARCH_FLASHING_LIGHTS_AND_WHISTLE = 557846021;
    public static final int ID_REMOTE_CONTROL_START_ENGINE = 557846016;
    public static final int ID_REMOTE_CONTROL_STOP_AC = 557846019;
    public static final int ID_REMOTE_CONTROL_STOP_ENGINE = 557846020;
    public static final int ID_REMOTE_CONTROL_UNLOCK_FOUR_DOORS = 557846017;
    public static final int ID_REMOTE_SEAT_CONTROL_COPILOT_HEAT = 557846036;
    public static final int ID_REMOTE_SEAT_CONTROL_COPILOT_VENTILATE = 557846035;
    public static final int ID_REMOTE_SEAT_CONTROL_DRIVER_HEAT = 557846034;
    public static final int ID_REMOTE_SEAT_CONTROL_DRIVER_VENTILATE = 557846033;
    public static final int ID_REMOTE_SEAT_CONTROL_FRONT_SEATS = 557846032;
    public static final int ID_REMOTE_UNLOCK_FOUR_DOORS = 557846023;
    public static final int ID_REMTOE_AC_CONTROL_AIR_GEAR = 557846027;
    public static final int ID_RIGHT_BACK_DOOR = 557846049;
    public static final int ID_RIGHT_FRONT_DOOR = 557846047;
    public static final int ID_RLEFT_SEAT_HEAT_STAT = 557846038;
    public static final int ID_RLEFT_SEAT_VTILAT_STAT = 557846037;
    public static final int ID_RL_SEAT_HEAT_STAT_486 = 557846065;
    public static final int ID_RL_SEAT_VTILAT_STAT_486 = 557846064;
    public static final int ID_RRIGHT_SEAT_HEAT_STAT = 557846040;
    public static final int ID_RRIGHT_SEAT_VTILAT_STAT = 557846039;
    public static final int ID_RR_SEAT_HEAT_STAT_486 = 557846067;
    public static final int ID_RR_SEAT_VTILAT_STAT_486 = 557846066;
    public static final int ID_STEERINGWHEELHEATINGSTATE = 557846059;
    public static final int ID_STEERING_WHEEL_HEAT = 557846042;
    public static final int ID_VEHICLE_SPEED = 560991773;
    public static final int ID_WATER_THERMOMETER = 560991771;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.yun";
    public static final String TAG = "CAR.VENDOR.YUN";

    public interface OnCarYunEventListener {
        void onYunErrorEvent(int i, int i2);

        void onYunEventChange(YunEventChangeValue yunEventChangeValue);
    }

    void addCarYunEventListener(OnCarYunEventListener onCarYunEventListener) throws CarNotConnectedException;

    int getACHMCACFailureCause() throws CarNotConnectedException;

    int getACHMCACOpenRsltFdbckInfor() throws CarNotConnectedException;

    int getACHMCACShutdownFailReason() throws CarNotConnectedException;

    int getAVCloseResultsFeedback() throws CarNotConnectedException;

    int getContrlResultsFBInfo497s16() throws CarNotConnectedException;

    int getCopilotSeatHeatStat4FA() throws CarNotConnectedException;

    int getCopilotSeatVtilatStat4FA() throws CarNotConnectedException;

    int getEngStartResultFdbkInfo() throws CarNotConnectedException;

    int getEngStopResultFdbkInfo() throws CarNotConnectedException;

    int getFailureReasons497s16() throws CarNotConnectedException;

    int getFunctionNumber497() throws CarNotConnectedException;

    int getLeftBackDoor() throws CarNotConnectedException;

    int getLeftFrontDoor() throws CarNotConnectedException;

    int getPilotSeatHeatStat() throws CarNotConnectedException;

    int getPilotSeatVtilatStat() throws CarNotConnectedException;

    int getRLSeatHeatStat486() throws CarNotConnectedException;

    int getRLSeatVtilatStat486() throws CarNotConnectedException;

    int getRRSeatHeatStat486() throws CarNotConnectedException;

    int getRRSeatVtilatStat486() throws CarNotConnectedException;

    int getRemainBattery() throws CarNotConnectedException;

    int getRightBackDoor() throws CarNotConnectedException;

    int getRightFrontDoor() throws CarNotConnectedException;

    int getSteeringWheelHeatingState() throws CarNotConnectedException;

    byte[] getVehicleSpeed() throws CarNotConnectedException;

    byte[] getWaterThermometer() throws CarNotConnectedException;

    void removeCarYunEventListener(OnCarYunEventListener onCarYunEventListener) throws CarNotConnectedException;

    void setRLeftSeatHeatStat(int i) throws CarNotConnectedException;

    void setRLeftSeatVtilatStat(int i) throws CarNotConnectedException;

    void setRRightSeatHeatStat(int i) throws CarNotConnectedException;

    void setRRightSeatVtilatStat(int i) throws CarNotConnectedException;

    void setRemoteAcControlAirGear(int i) throws CarNotConnectedException;

    void setRemoteAcControlCycleMode(int i) throws CarNotConnectedException;

    void setRemoteAcControlDriverTemperature(int i) throws CarNotConnectedException;

    void setRemoteAcControlManner(int i) throws CarNotConnectedException;

    void setRemoteAcControlStart(int i) throws CarNotConnectedException;

    void setRemoteAcControlStartTime(int i) throws CarNotConnectedException;

    void setRemoteAcControlSwitch(int i) throws CarNotConnectedException;

    void setRemoteAcControlWindPattern(int i) throws CarNotConnectedException;

    void setRemoteControl33S(int i) throws CarNotConnectedException;

    void setRemoteControlLockout(int i) throws CarNotConnectedException;

    void setRemoteControlSearchFlashingLight(int i) throws CarNotConnectedException;

    void setRemoteControlSearchFlashingLightsAndWhistle(int i) throws CarNotConnectedException;

    void setRemoteControlStartEngine(int i) throws CarNotConnectedException;

    void setRemoteControlStopAc(int i) throws CarNotConnectedException;

    void setRemoteControlStopEngine(int i) throws CarNotConnectedException;

    void setRemoteControlUnlockFourDoors(int i) throws CarNotConnectedException;

    void setRemoteSeatControlCopilotHeat(int i) throws CarNotConnectedException;

    void setRemoteSeatControlCopilotVentilate(int i) throws CarNotConnectedException;

    void setRemoteSeatControlDriverHeat(int i) throws CarNotConnectedException;

    void setRemoteSeatControlDriverVentilate(int i) throws CarNotConnectedException;

    void setRemoteSeatControlFrontSeats(int i) throws CarNotConnectedException;

    void setRemoteUnlockFourDoors(int i) throws CarNotConnectedException;

    void setSteeringWheelHeat(int i) throws CarNotConnectedException;
}
