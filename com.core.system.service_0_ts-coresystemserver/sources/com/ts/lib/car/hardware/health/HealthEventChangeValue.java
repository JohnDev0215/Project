package com.ts.lib.car.hardware.health;

import com.ts.lib.car.CarBaseProperty;

/* JADX INFO: loaded from: classes.dex */
public class HealthEventChangeValue extends CarBaseProperty {
    private int mBatteryHealthStatusR;
    private int mEPBConfig;
    private int mEPBStateR;
    private int mEPBStateS;
    private int mEPBTtpe;
    private int mEpbHintR;
    private int mFrontWiperCheckStateR;
    private int mFrontWiperCheckStateS;
    private int mOilHint;
    private int mOilLevel;
    private int mOilLifetimeBattery;
    private int mOilLifetimeBrake;
    private int mOilLifetimeElec;
    private int mOilLifetimeEngine;
    private int mOilLifetimeNewBladeBatteryCoolantR;
    private int mOilLifetimeNewBrakeFluidLifeBrakeFluidLifeR;
    private int mOilLifetimeNewLongLifeCoolantLongLifeCoolantR;
    private int mOilLifetimeNewOilLifeR;
    private int mOilLifetimeNewTransmissionR;
    private int mOilLifetimeTransmission;
    private int mOilMaintainanceDialogHintBladeBatteryCoolantR;
    private int mOilMaintainanceDialogHintBrakeFluidLifeR;
    private int mOilMaintainanceDialogHintLongLifeCoolantR;
    private int mOilMaintainanceDialogHintOilLifeR;
    private int mOilMaintainanceDialogHintTransmissionR;
    private int mOilMaintainanceHintBladeBatteryCoolant;
    private int mOilMaintainanceHintBrakeFluidLife;
    private int mOilMaintainanceHintLongLifeCoolant;
    private int mOilMaintainanceHintOilLife;
    private int mOilMaintainanceHintTransmissionOilLife;
    private int mOilMaintainanceNotHintBladeBatteryCoolantS;
    private int mOilMaintainanceNotHintBrakeFluidLifeS;
    private int mOilMaintainanceNotHintLongLifeCoolantS;
    private int mOilMaintainanceNotHintOilLifeS;
    private int mOilMaintainanceNotHintTransmissionS;
    private int mOilUpdateState;
    private int mOilUpdateTagS;
    private int mRearWiperCheckStateR;
    private int mRearWiperCheckStateS;
    private int mResetOilLifetimeChangedBatteryR;
    private int mResetOilLifetimeChangedBreakR;
    private int mResetOilLifetimeChangedElecR;
    private int mResetOilLifetimeChangedEngineR;
    private int mResetOilLifetimeChangedTransMissionR;
    private int mTransportModeR;
    private int mTransportModeS;
    private int mTransportModeStateHintR;
    private int mTransportModeStateHintS;
    private int mTyreFaultLocation;
    private int mresetOilLifetimeBattery;
    private int mresetOilLifetimeBrake;
    private int mresetOilLifetimeELec;
    private int mresetOilLifetimeEngine;
    private int mresetOilLifetimeTransmission;
    private int mresetTirePressure;

    public int getBatteryHealthStatusR() {
        return this.mBatteryHealthStatusR;
    }

    public void setBatteryHealthStatusR(int batteryHealthStatusR) {
        this.mBatteryHealthStatusR = batteryHealthStatusR;
    }

    public int getEpbHintR() {
        return this.mEpbHintR;
    }

    public void setEpbHintR(int result) {
        this.mEpbHintR = result;
    }

    public int getOilLifetimeNewBladeBatteryCoolantR() {
        return this.mOilLifetimeNewBladeBatteryCoolantR;
    }

    public void setOilLifetimeNewBladeBatteryCoolantR(int value) {
        this.mOilLifetimeNewBladeBatteryCoolantR = value;
    }

    public int getOilLifetimeNewBrakeFluidLifeBrakeFluidLifeR() {
        return this.mOilLifetimeNewBrakeFluidLifeBrakeFluidLifeR;
    }

    public void setOilLifetimeNewBrakeFluidLifeBrakeFluidLifeR(int value) {
        this.mOilLifetimeNewBrakeFluidLifeBrakeFluidLifeR = value;
    }

    public int getOilLifetimeNewLongLifeCoolantLongLifeCoolantR() {
        return this.mOilLifetimeNewLongLifeCoolantLongLifeCoolantR;
    }

    public void setOilLifetimeNewLongLifeCoolantLongLifeCoolantR(int value) {
        this.mOilLifetimeNewLongLifeCoolantLongLifeCoolantR = value;
    }

    public int getOilLifetimeNewOilLifeR() {
        return this.mOilLifetimeNewOilLifeR;
    }

    public void setOilLifetimeNewOilLifeR(int oilLifetimeNewOilLifeR) {
        this.mOilLifetimeNewOilLifeR = oilLifetimeNewOilLifeR;
    }

    public int getOilLifetimeNewTransmissionR() {
        return this.mOilLifetimeNewTransmissionR;
    }

    public void setOilLifetimeNewTransmissionR(int oilLifetimeNewTransmissionR) {
        this.mOilLifetimeNewTransmissionR = oilLifetimeNewTransmissionR;
    }

    public int getOilMaintainanceDialogHintBladeBatteryCoolantR() {
        return this.mOilMaintainanceDialogHintBladeBatteryCoolantR;
    }

    public void setOilMaintainanceDialogHintBladeBatteryCoolantR(int value) {
        this.mOilMaintainanceDialogHintBladeBatteryCoolantR = value;
    }

    public int getOilMaintainanceDialogHintBrakeFluidLifeR() {
        return this.mOilMaintainanceDialogHintBrakeFluidLifeR;
    }

    public void setOilMaintainanceDialogHintBrakeFluidLifeR(int value) {
        this.mOilMaintainanceDialogHintBrakeFluidLifeR = value;
    }

    public int getOilMaintainanceDialogHintLongLifeCoolantR() {
        return this.mOilMaintainanceDialogHintLongLifeCoolantR;
    }

    public void setOilMaintainanceDialogHintLongLifeCoolantR(int value) {
        this.mOilMaintainanceDialogHintLongLifeCoolantR = value;
    }

    public int getOilMaintainanceDialogHintOilLifeR() {
        return this.mOilMaintainanceDialogHintOilLifeR;
    }

    public void setOilMaintainanceDialogHintOilLifeR(int oilMaintainanceDialogHintOilLifeR) {
        this.mOilMaintainanceDialogHintOilLifeR = oilMaintainanceDialogHintOilLifeR;
    }

    public int getOilMaintainanceDialogHintTransmissionR() {
        return this.mOilMaintainanceDialogHintTransmissionR;
    }

    public void setOilMaintainanceDialogHintTransmissionR(int value) {
        this.mOilMaintainanceDialogHintTransmissionR = value;
    }

    public int getOilMaintainanceNotHintBladeBatteryCoolantS() {
        return this.mOilMaintainanceNotHintBladeBatteryCoolantS;
    }

    public void setOilMaintainanceNotHintBladeBatteryCoolantS(int value) {
        this.mOilMaintainanceNotHintBladeBatteryCoolantS = value;
    }

    public int getOilMaintainanceNotHintBrakeFluidLifeS() {
        return this.mOilMaintainanceNotHintBrakeFluidLifeS;
    }

    public void setOilMaintainanceNotHintBrakeFluidLifeS(int value) {
        this.mOilMaintainanceNotHintBrakeFluidLifeS = value;
    }

    public int getOilMaintainanceNotHintLongLifeCoolantS() {
        return this.mOilMaintainanceNotHintLongLifeCoolantS;
    }

    public void setOilMaintainanceNotHintLongLifeCoolantS(int value) {
        this.mOilMaintainanceNotHintLongLifeCoolantS = value;
    }

    public int getOilMaintainanceNotHintOilLifeS() {
        return this.mOilMaintainanceNotHintOilLifeS;
    }

    public void setOilMaintainanceNotHintOilLifeS(int oilMaintainanceNotHintOilLifeS) {
        this.mOilMaintainanceNotHintOilLifeS = oilMaintainanceNotHintOilLifeS;
    }

    public int getOilMaintainanceNotHintTransmissionS() {
        return this.mOilMaintainanceNotHintTransmissionS;
    }

    public void setOilMaintainanceNotHintTransmissionS(int oilMaintainanceNotHintTransmissionS) {
        this.mOilMaintainanceNotHintTransmissionS = oilMaintainanceNotHintTransmissionS;
    }

    public int getOilMaintainanceHintBladeBatteryCoolant() {
        return this.mOilMaintainanceHintBladeBatteryCoolant;
    }

    public void setOilMaintainanceHintBladeBatteryCoolant(int oilMaintainanceHintBladeBatteryCoolant) {
        this.mOilMaintainanceHintBladeBatteryCoolant = oilMaintainanceHintBladeBatteryCoolant;
    }

    public int getOilMaintainanceHintBrakeFluidLife() {
        return this.mOilMaintainanceHintBrakeFluidLife;
    }

    public void setOilMaintainanceHintBrakeFluidLife(int oilMaintainanceHintBrakeFluidLife) {
        this.mOilMaintainanceHintBrakeFluidLife = oilMaintainanceHintBrakeFluidLife;
    }

    public int getOilMaintainanceHintLongLifeCoolant() {
        return this.mOilMaintainanceHintLongLifeCoolant;
    }

    public void setOilMaintainanceHintLongLifeCoolant(int oilMaintainanceHintLongLifeCoolant) {
        this.mOilMaintainanceHintLongLifeCoolant = oilMaintainanceHintLongLifeCoolant;
    }

    public int getOilMaintainanceHintOilLife() {
        return this.mOilMaintainanceHintOilLife;
    }

    public void setOilMaintainanceHintOilLife(int oilMaintainanceHintOilLife) {
        this.mOilMaintainanceHintOilLife = oilMaintainanceHintOilLife;
    }

    public int getOilMaintainanceHintTransmissionOilLife() {
        return this.mOilMaintainanceHintTransmissionOilLife;
    }

    public void setOilMaintainanceHintTransmissionOilLife(int oilMaintainanceHintTransmissionOilLife) {
        this.mOilMaintainanceHintTransmissionOilLife = oilMaintainanceHintTransmissionOilLife;
    }

    public int getResetOilLifetimeChangedBatteryR() {
        return this.mResetOilLifetimeChangedBatteryR;
    }

    public void setResetOilLifetimeChangedBatteryR(int resetOilLifetimeChangedBatteryR) {
        this.mResetOilLifetimeChangedBatteryR = resetOilLifetimeChangedBatteryR;
    }

    public int getResetOilLifetimeChangedBreakR() {
        return this.mResetOilLifetimeChangedBreakR;
    }

    public void setResetOilLifetimeChangedBreakR(int resetOilLifetimeChangedBreakR) {
        this.mResetOilLifetimeChangedBreakR = resetOilLifetimeChangedBreakR;
    }

    public int getResetOilLifetimeChangedElecR() {
        return this.mResetOilLifetimeChangedElecR;
    }

    public void setResetOilLifetimeChangedElecR(int resetOilLifetimeChangedElecR) {
        this.mResetOilLifetimeChangedElecR = resetOilLifetimeChangedElecR;
    }

    public int getResetOilLifetimeChangedEngineR() {
        return this.mResetOilLifetimeChangedEngineR;
    }

    public void setResetOilLifetimeChangedEngineR(int resetOilLifetimeChangedEngineR) {
        this.mResetOilLifetimeChangedEngineR = resetOilLifetimeChangedEngineR;
    }

    public int getResetOilLifetimeChangedTransMissionR() {
        return this.mResetOilLifetimeChangedTransMissionR;
    }

    public void setResetOilLifetimeChangedTransMissionR(int resetOilLifetimeChangedTransMissionR) {
        this.mResetOilLifetimeChangedTransMissionR = resetOilLifetimeChangedTransMissionR;
    }

    public int getFrontWiperCheckStateR() {
        return this.mFrontWiperCheckStateR;
    }

    public void setFrontWiperCheckStateR(int frontWiperCheckStateR) {
        this.mFrontWiperCheckStateR = frontWiperCheckStateR;
    }

    public int getFrontWiperCheckStateS() {
        return this.mFrontWiperCheckStateS;
    }

    public void setFrontWiperCheckStateS(int frontWiperCheckStateS) {
        this.mFrontWiperCheckStateS = frontWiperCheckStateS;
    }

    public int getOilHint() {
        return this.mOilHint;
    }

    public void setOilHint(int oilHint) {
        this.mOilHint = oilHint;
    }

    public int getOilLevel() {
        return this.mOilLevel;
    }

    public void setOilLevel(int oilLevel) {
        this.mOilLevel = oilLevel;
    }

    public int getOilLifetimeBattery() {
        return this.mOilLifetimeBattery;
    }

    public void setOilLifetimeBattery(int oilLifetimeBattery) {
        this.mOilLifetimeBattery = oilLifetimeBattery;
    }

    public int getOilLifetimeBrake() {
        return this.mOilLifetimeBrake;
    }

    public void setOilLifetimeBrake(int oilLifetimeBrake) {
        this.mOilLifetimeBrake = oilLifetimeBrake;
    }

    public int getOilLifetimeElec() {
        return this.mOilLifetimeElec;
    }

    public void setOilLifetimeElec(int oilLifetimeElec) {
        this.mOilLifetimeElec = oilLifetimeElec;
    }

    public int getOilLifetimeEngine() {
        return this.mOilLifetimeEngine;
    }

    public void setOilLifetimeEngine(int oilLifetimeEngine) {
        this.mOilLifetimeEngine = oilLifetimeEngine;
    }

    public int getOilLifetimeTransmission() {
        return this.mOilLifetimeTransmission;
    }

    public void setOilLifetimeTransmission(int oilLifetimeTransmission) {
        this.mOilLifetimeTransmission = oilLifetimeTransmission;
    }

    public int getOilUpdateState() {
        return this.mOilUpdateState;
    }

    public void setOilUpdateState(int oilUpdateState) {
        this.mOilUpdateState = oilUpdateState;
    }

    public int getRearWiperCheckStateR() {
        return this.mRearWiperCheckStateR;
    }

    public void setRearWiperCheckStateR(int rearWiperCheckStateR) {
        this.mRearWiperCheckStateR = rearWiperCheckStateR;
    }

    public int getRearWiperCheckStateS() {
        return this.mRearWiperCheckStateS;
    }

    public void setRearWiperCheckStateS(int rearWiperCheckStateS) {
        this.mRearWiperCheckStateS = rearWiperCheckStateS;
    }

    public int getTyreFaultLocation() {
        return this.mTyreFaultLocation;
    }

    public void setTyreFaultLocation(int tyreFaultLocation) {
        this.mTyreFaultLocation = tyreFaultLocation;
    }

    public int getresetOilLifetimeBattery() {
        return this.mresetOilLifetimeBattery;
    }

    public void setresetOilLifetimeBattery(int resetOilLifetimeBattery) {
        this.mresetOilLifetimeBattery = resetOilLifetimeBattery;
    }

    public int getresetOilLifetimeBrake() {
        return this.mresetOilLifetimeBrake;
    }

    public void setresetOilLifetimeBrake(int resetOilLifetimeBrake) {
        this.mresetOilLifetimeBrake = resetOilLifetimeBrake;
    }

    public int getresetOilLifetimeELec() {
        return this.mresetOilLifetimeELec;
    }

    public void setresetOilLifetimeELec(int resetOilLifetimeELec) {
        this.mresetOilLifetimeELec = resetOilLifetimeELec;
    }

    public int getresetOilLifetimeEngine() {
        return this.mresetOilLifetimeEngine;
    }

    public void setresetOilLifetimeEngine(int resetOilLifetimeEngine) {
        this.mresetOilLifetimeEngine = resetOilLifetimeEngine;
    }

    public int getresetOilLifetimeTransmission() {
        return this.mresetOilLifetimeTransmission;
    }

    public void setresetOilLifetimeTransmission(int resetOilLifetimeTransmission) {
        this.mresetOilLifetimeTransmission = resetOilLifetimeTransmission;
    }

    public int getresetTirePressure() {
        return this.mresetTirePressure;
    }

    public void setresetTirePressure(int resetTirePressure) {
        this.mresetTirePressure = resetTirePressure;
    }

    public int getEPBConfig() {
        return this.mEPBConfig;
    }

    public void setEPBConfig(int ePBConfig) {
        this.mEPBConfig = ePBConfig;
    }

    public int getEPBStateR() {
        return this.mEPBStateR;
    }

    public void setEPBStateR(int ePBStateR) {
        this.mEPBStateR = ePBStateR;
    }

    public int getEPBStateS() {
        return this.mEPBStateS;
    }

    public void setEPBStateS(int ePBStateS) {
        this.mEPBStateS = ePBStateS;
    }

    public int getEPBTtpe() {
        return this.mEPBTtpe;
    }

    public void setEPBTtpe(int ePBTtpe) {
        this.mEPBTtpe = ePBTtpe;
    }

    public int getOilUpdateTagS() {
        return this.mOilUpdateTagS;
    }

    public void setOilUpdateTagS(int oilUpdateTagS) {
        this.mOilUpdateTagS = oilUpdateTagS;
    }

    public int getTransportModeR() {
        return this.mTransportModeR;
    }

    public void setTransportModeR(int transportModeR) {
        this.mTransportModeR = transportModeR;
    }

    public int getTransportModeS() {
        return this.mTransportModeS;
    }

    public void setTransportModeS(int transportModeS) {
        this.mTransportModeS = transportModeS;
    }

    public int getTransportModeStateHintR() {
        return this.mTransportModeStateHintR;
    }

    public void setTransportModeStateHintR(int transportModeStateHintR) {
        this.mTransportModeStateHintR = transportModeStateHintR;
    }

    public int getTransportModeStateHintS() {
        return this.mTransportModeStateHintS;
    }

    public void setTransportModeStateHintS(int transportModeStateHintS) {
        this.mTransportModeStateHintS = transportModeStateHintS;
    }
}
