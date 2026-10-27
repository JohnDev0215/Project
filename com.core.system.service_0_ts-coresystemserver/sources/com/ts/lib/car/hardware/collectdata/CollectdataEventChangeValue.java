package com.ts.lib.car.hardware.collectdata;

import com.ts.lib.car.CarBaseProperty;

/* JADX INFO: loaded from: classes.dex */
public class CollectdataEventChangeValue extends CarBaseProperty {
    private int mCollision;
    private int mDCChargingFault;
    private int mDmVersionCodeR;
    private int mDriverMotorSpeedFront;
    private int mDriverMotorSpeedRear;
    private int mDriverMotorTemperatureFront;
    private int mDriverMotorTemperatureRear;
    private int mDriverMotorTorqueFront;
    private int mDriverMotorTorqueRear;
    private float mEngineSpeed;
    private int mMotorMCUGeneratrixCurrentFront;
    private int mMotorMCUGeneratrixCurrentRear;
    private int mMotorMCUGeneratrixVoltFront;
    private int mMotorMCUGeneratrixVoltRear;
    private int mWaterTemperationAlarm;

    public int getDmVersionCodeR() {
        return this.mDmVersionCodeR;
    }

    public void setDmVersionCodeR(int dmVersionCodeR) {
        this.mDmVersionCodeR = dmVersionCodeR;
    }

    public int getCollision() {
        return this.mCollision;
    }

    public void setCollision(int collision) {
        this.mCollision = collision;
    }

    public int getDCChargingFault() {
        return this.mDCChargingFault;
    }

    public void setDCChargingFault(int dCChargingFault) {
        this.mDCChargingFault = dCChargingFault;
    }

    public int getDriverMotorSpeedFront() {
        return this.mDriverMotorSpeedFront;
    }

    public void setDriverMotorSpeedFront(int driverMotorSpeedFront) {
        this.mDriverMotorSpeedFront = driverMotorSpeedFront;
    }

    public int getDriverMotorSpeedRear() {
        return this.mDriverMotorSpeedRear;
    }

    public void setDriverMotorSpeedRear(int driverMotorSpeedRear) {
        this.mDriverMotorSpeedRear = driverMotorSpeedRear;
    }

    public int getDriverMotorTemperatureFront() {
        return this.mDriverMotorTemperatureFront;
    }

    public void setDriverMotorTemperatureFront(int driverMotorTemperatureFront) {
        this.mDriverMotorTemperatureFront = driverMotorTemperatureFront;
    }

    public int getDriverMotorTemperatureRear() {
        return this.mDriverMotorTemperatureRear;
    }

    public void setDriverMotorTemperatureRear(int driverMotorTemperatureRear) {
        this.mDriverMotorTemperatureRear = driverMotorTemperatureRear;
    }

    public int getDriverMotorTorqueFront() {
        return this.mDriverMotorTorqueFront;
    }

    public void setDriverMotorTorqueFront(int driverMotorTorqueFront) {
        this.mDriverMotorTorqueFront = driverMotorTorqueFront;
    }

    public int getDriverMotorTorqueRear() {
        return this.mDriverMotorTorqueRear;
    }

    public void setDriverMotorTorqueRear(int driverMotorTorqueRear) {
        this.mDriverMotorTorqueRear = driverMotorTorqueRear;
    }

    public float getEngineSpeed() {
        return this.mEngineSpeed;
    }

    public void setEngineSpeed(float engineSpeed) {
        this.mEngineSpeed = engineSpeed;
    }

    public int getMotorMCUGeneratrixCurrentFront() {
        return this.mMotorMCUGeneratrixCurrentFront;
    }

    public void setMotorMCUGeneratrixCurrentFront(int motorMCUGeneratrixCurrentFront) {
        this.mMotorMCUGeneratrixCurrentFront = motorMCUGeneratrixCurrentFront;
    }

    public int getMotorMCUGeneratrixCurrentRear() {
        return this.mMotorMCUGeneratrixCurrentRear;
    }

    public void setMotorMCUGeneratrixCurrentRear(int motorMCUGeneratrixCurrentRear) {
        this.mMotorMCUGeneratrixCurrentRear = motorMCUGeneratrixCurrentRear;
    }

    public int getMotorMCUGeneratrixVoltFront() {
        return this.mMotorMCUGeneratrixVoltFront;
    }

    public void setMotorMCUGeneratrixVoltFront(int motorMCUGeneratrixVoltFront) {
        this.mMotorMCUGeneratrixVoltFront = motorMCUGeneratrixVoltFront;
    }

    public int getMotorMCUGeneratrixVoltRear() {
        return this.mMotorMCUGeneratrixVoltRear;
    }

    public void setMotorMCUGeneratrixVoltRear(int motorMCUGeneratrixVoltRear) {
        this.mMotorMCUGeneratrixVoltRear = motorMCUGeneratrixVoltRear;
    }

    public int getWaterTemperationAlarm() {
        return this.mWaterTemperationAlarm;
    }

    public void setWaterTemperationAlarm(int waterTemperationAlarm) {
        this.mWaterTemperationAlarm = waterTemperationAlarm;
    }
}
