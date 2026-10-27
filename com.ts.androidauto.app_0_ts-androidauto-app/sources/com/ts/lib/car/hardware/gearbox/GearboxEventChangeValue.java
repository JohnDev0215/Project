package com.ts.lib.car.hardware.gearbox;

import com.ts.lib.car.CarBaseProperty;

/* JADX INFO: loaded from: classes2.dex */
public class GearboxEventChangeValue extends CarBaseProperty {
    private int mBrakeFluidLevel;
    private int mBrakePedalState;
    private int mBrakePedalStatusR;
    private int mEpbStateR;
    private int mEpbStateS;
    private int mGearR;
    private int mGearboxAutoModeType;
    private int mGearboxAutoModeTypeTwo;
    private int mGearboxCode;
    private int mGearboxManualModeLevelR;
    private int mGearboxType;
    private int mParkBrakeSwitch;

    public int getBrakePedalStatusR() {
        return this.mBrakePedalStatusR;
    }

    public void setBrakePedalStatusR(int brakePedalStatusR) {
        this.mBrakePedalStatusR = brakePedalStatusR;
    }

    public int getGearR() {
        return this.mGearR;
    }

    public void setGearR(int gearR) {
        this.mGearR = gearR;
    }

    public int getEpbStateR() {
        return this.mEpbStateR;
    }

    public void setEpbStateR(int epbStateR) {
        this.mEpbStateR = epbStateR;
    }

    public int getEpbStateS() {
        return this.mEpbStateS;
    }

    public void setEpbStateS(int epbStateS) {
        this.mEpbStateS = epbStateS;
    }

    public int getGearboxManualModeLevelR() {
        return this.mGearboxManualModeLevelR;
    }

    public void setGearboxManualModeLevelR(int gearboxManualModeLevelR) {
        this.mGearboxManualModeLevelR = gearboxManualModeLevelR;
    }

    public int getGearboxAutoModeTypeTwo() {
        return this.mGearboxAutoModeTypeTwo;
    }

    public void setGearboxAutoModeTypeTwo(int gearboxAutoModeTypeTwo) {
        this.mGearboxAutoModeTypeTwo = gearboxAutoModeTypeTwo;
    }

    public int getBrakeFluidLevel() {
        return this.mBrakeFluidLevel;
    }

    public void setBrakeFluidLevel(int brakeFluidLevel) {
        this.mBrakeFluidLevel = brakeFluidLevel;
    }

    public int getBrakePedalState() {
        return this.mBrakePedalState;
    }

    public void setBrakePedalState(int brakePedalState) {
        this.mBrakePedalState = brakePedalState;
    }

    public int getGearboxAutoModeType() {
        return this.mGearboxAutoModeType;
    }

    public void setGearboxAutoModeType(int gearboxAutoModeType) {
        this.mGearboxAutoModeType = gearboxAutoModeType;
    }

    public int getParkBrakeSwitch() {
        return this.mParkBrakeSwitch;
    }

    public void setParkBrakeSwitch(int parkBrakeSwitch) {
        this.mParkBrakeSwitch = parkBrakeSwitch;
    }

    public int getGearboxCode() {
        return this.mGearboxCode;
    }

    public void setGearboxCode(int gearboxCode) {
        this.mGearboxCode = gearboxCode;
    }

    public int getGearboxType() {
        return this.mGearboxType;
    }

    public void setGearboxType(int gearboxType) {
        this.mGearboxType = gearboxType;
    }
}
