package com.ts.lib.car.hardware.collectdata;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes2.dex */
public interface CarVendorCollectdataManager {
    public static final int ID_COLLISION = 557872141;
    public static final int ID_DC_CHARGING_FAULT = 557872139;
    public static final int ID_DM_VERSIONCODER = 557872142;
    public static final int ID_DRIVER_MOTOR_SPEED_FRONT = 557872128;
    public static final int ID_DRIVER_MOTOR_SPEED_REAR = 557872129;
    public static final int ID_DRIVER_MOTOR_TEMPERATURE_FRONT = 557872132;
    public static final int ID_DRIVER_MOTOR_TEMPERATURE_REAR = 557872133;
    public static final int ID_DRIVER_MOTOR_TORQUE_FRONT = 557872130;
    public static final int ID_DRIVER_MOTOR_TORQUE_REAR = 557872131;
    public static final int ID_ENGINE_SPEED = 559969290;
    public static final int ID_MOTOR_MCU_GENERATRIX_CURRENT_FRONT = 557872136;
    public static final int ID_MOTOR_MCU_GENERATRIX_CURRENT_REAR = 557872137;
    public static final int ID_MOTOR_MCU_GENERATRIX_VOLT_FRONT = 557872134;
    public static final int ID_MOTOR_MCU_GENERATRIX_VOLT_REAR = 557872135;
    public static final int ID_WATER_TEMPERATION_ALARM = 557872140;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.collectdata";
    public static final String TAG = "CAR.VENDOR.COLLECTDATA";

    public interface OnCarCollectdataEventListener {
        void onCollectdataErrorEvent(int i, int i2);

        void onCollectdataEventChange(CollectdataEventChangeValue collectdataEventChangeValue);
    }

    void addCarCollectdataEventListener(OnCarCollectdataEventListener onCarCollectdataEventListener) throws CarNotConnectedException;

    int getCollision() throws CarNotConnectedException;

    int getDCChargingFault() throws CarNotConnectedException;

    int getDmVersionCodeR() throws CarNotConnectedException;

    int getDriverMotorSpeedFront() throws CarNotConnectedException;

    int getDriverMotorSpeedRear() throws CarNotConnectedException;

    int getDriverMotorTemperatureFront() throws CarNotConnectedException;

    int getDriverMotorTemperatureRear() throws CarNotConnectedException;

    int getDriverMotorTorqueFront() throws CarNotConnectedException;

    int getDriverMotorTorqueRear() throws CarNotConnectedException;

    float getEngineSpeed() throws CarNotConnectedException;

    int getMotorMCUGeneratrixCurrentFront() throws CarNotConnectedException;

    int getMotorMCUGeneratrixCurrentRear() throws CarNotConnectedException;

    int getMotorMCUGeneratrixVoltFront() throws CarNotConnectedException;

    int getMotorMCUGeneratrixVoltRear() throws CarNotConnectedException;

    int getWaterTemperationAlarm() throws CarNotConnectedException;

    void removeCarCollectdataEventListener(OnCarCollectdataEventListener onCarCollectdataEventListener) throws CarNotConnectedException;

    int testBlankPlace() throws CarNotConnectedException;
}
