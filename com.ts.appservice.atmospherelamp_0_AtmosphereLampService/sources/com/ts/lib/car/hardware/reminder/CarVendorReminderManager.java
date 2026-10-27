package com.ts.lib.car.hardware.reminder;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes3.dex */
public interface CarVendorReminderManager {
    public static final int ID_ACC_TEXTINFO = 557870400;
    public static final int ID_DOOR_STATE_HOOD = 557862948;
    public static final int ID_DOOR_STATE_LEFT_FRONT = 557862943;
    public static final int ID_DOOR_STATE_LEFT_REAR = 557862945;
    public static final int ID_DOOR_STATE_LUGGAGE = 557862947;
    public static final int ID_DOOR_STATE_RIGHT_FRONT = 557862944;
    public static final int ID_DOOR_STATE_RIGHT_REAR = 557862946;
    public static final int ID_IND_CHANGED_ADAS0_X26C_FIRST = 557845272;
    public static final int ID_IND_CHANGED_ADAS0_X29CZ_FIRST = 557856774;
    public static final int ID_IND_CHANGED_ADAS0_X29C_SECOND = 557870438;
    public static final int ID_IND_CHANGED_ADAS0_X316 = 557856776;
    public static final int ID_IND_CHANGED_ADAS0_X32D = 557845271;
    public static final int ID_IND_CHANGED_BECAREFUL_CARSBACK_LEFT = 557845269;
    public static final int ID_IND_CHANGED_BECAREFUL_CARSBACK_RIGHT = 557845270;
    public static final int ID_IND_CHANGED_CLOUD_SERVICE_SIGNAL = 557845250;
    public static final int ID_IND_CHANGED_DOOR_NOT_CLOSED_LEFT_BACK = 557845256;
    public static final int ID_IND_CHANGED_DOOR_NOT_CLOSED_LEFT_FRONT = 557845254;
    public static final int ID_IND_CHANGED_DOOR_NOT_CLOSED_RIGHT_BACK = 557845257;
    public static final int ID_IND_CHANGED_DOOR_NOT_CLOSED_RIGHT_FRONT = 557845255;
    public static final int ID_IND_CHANGED_EXTERNAL_DISCHARGE_TERMINATION = 557845266;
    public static final int ID_IND_CHANGED_INSUFFICIENT_FUEL = 557845265;
    public static final int ID_IND_CHANGED_KEY_NOT_DETECTED = 557845263;
    public static final int ID_IND_CHANGED_LEAVE_THE_VEHICLE_AT_NONP_GEAR_INS = 557845259;
    public static final int ID_IND_CHANGED_LEAVE_THE_VEHICLE_AT_NONP_GEAR_VTOG = 557845260;
    public static final int ID_IND_CHANGED_LOWKEY_POWER = 557859850;
    public static final int ID_IND_CHANGED_LOW_BATTERY_VOLTAGE = 557875227;
    public static final int ID_IND_CHANGED_NIGHT_RUNNING_LIGHTS = 557845264;
    public static final int ID_IND_CHANGED_NOT_WEARING_SEAT_BELT = 557870596;
    public static final int ID_IND_CHANGED_PARKING_BRAKE_NOT_RELEASED_EPB = 557845253;
    public static final int ID_IND_CHANGED_PARKING_BRAKE_NOT_RELEASED_ESC = 559957505;
    public static final int ID_IND_CHANGED_PARKING_BRAKE_NOT_RELEASED_INS = 557857284;
    public static final int ID_IND_CHANGED_POWER_FAILURE = 557845249;
    public static final int ID_IND_CHANGED_POWER_SYSTEM_FAULT = 557845267;
    public static final int ID_IND_CHANGED_REMOTE_DRIVING_SUSPENSI = 557845261;
    public static final int ID_IND_CHANGED_RMD_RAPID_ACCELERATION_LIMIT = 557845273;
    public static final int ID_IND_CHANGED_RMD_REAR_AIR_CONDITIONING_OPERATE_INVALID = 557846612;
    public static final int ID_IND_CHANGED_RMD_REAR_AIR_CONDITIONING_UNLOCK = 557845275;
    public static final int ID_IND_CHANGED_START = 557845248;
    public static final int ID_LEFT_STRUT_MOROT_RUNNING_STATE = 557862953;
    public static final int ID_OTA_POWER_LEVEL = 557862927;
    public static final int ID_RMD_TAKE_PHONE = 557845276;
    public static final int ID_SOC_VALUER = 557860386;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.reminder";
    public static final String TAG = "CAR.VENDOR.REMINDER";

    public interface OnCarReminderEventListener {
        void onReminderErrorEvent(int i, int i2);

        void onReminderEventChange(ReminderEventChangeValue reminderEventChangeValue);
    }

    void addCarReminderEventListener(OnCarReminderEventListener onCarReminderEventListener) throws CarNotConnectedException;

    int getAccTextInfo() throws CarNotConnectedException;

    int getonIndChangedADAS0x29CFirst() throws CarNotConnectedException;

    int getonIndChangedADAS0x29CSecond() throws CarNotConnectedException;

    int getonIndChangedADAS0x316() throws CarNotConnectedException;

    int getonIndChangedADAS0x32D() throws CarNotConnectedException;

    int getonIndChangedAdas0x26CFirst() throws CarNotConnectedException;

    int getonIndChangedBeCarefulCarsBackLeft() throws CarNotConnectedException;

    int getonIndChangedBeCarefulCarsBackRight() throws CarNotConnectedException;

    int getonIndChangedCloudServiceSignal() throws CarNotConnectedException;

    int getonIndChangedDoorNotClosedLeftBack() throws CarNotConnectedException;

    int getonIndChangedDoorNotClosedLeftFront() throws CarNotConnectedException;

    int getonIndChangedDoorNotClosedRightBack() throws CarNotConnectedException;

    int getonIndChangedDoorNotClosedRightFront() throws CarNotConnectedException;

    int getonIndChangedExternalDischargeTermination() throws CarNotConnectedException;

    int getonIndChangedInsufficientFuel() throws CarNotConnectedException;

    int getonIndChangedKeyNotDetected() throws CarNotConnectedException;

    int getonIndChangedLeaveTheVehicleAtNonPGearINS() throws CarNotConnectedException;

    int getonIndChangedLeaveTheVehicleAtNonPGearVTOG() throws CarNotConnectedException;

    int getonIndChangedLowBatteryVoltage() throws CarNotConnectedException;

    int getonIndChangedLowkeyPower() throws CarNotConnectedException;

    int getonIndChangedNightRunningLights() throws CarNotConnectedException;

    int getonIndChangedNotWearingSeatBelt() throws CarNotConnectedException;

    float getonIndChangedParkingBrakeNotReleasedEpb() throws CarNotConnectedException;

    float getonIndChangedParkingBrakeNotReleasedEsc() throws CarNotConnectedException;

    int getonIndChangedParkingBrakeNotReleasedINS() throws CarNotConnectedException;

    int getonIndChangedPowerFailure() throws CarNotConnectedException;

    int getonIndChangedPowerSystemFault() throws CarNotConnectedException;

    int getonIndChangedRemoteDrivingSuspension() throws CarNotConnectedException;

    int getonIndChangedstart() throws CarNotConnectedException;

    void removeCarReminderEventListener(OnCarReminderEventListener onCarReminderEventListener) throws CarNotConnectedException;
}
