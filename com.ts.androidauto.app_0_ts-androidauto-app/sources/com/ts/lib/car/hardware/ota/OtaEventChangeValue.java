package com.ts.lib.car.hardware.ota;

import com.ts.lib.car.CarBaseProperty;

/* JADX INFO: loaded from: classes2.dex */
public class OtaEventChangeValue extends CarBaseProperty {
    private int mBackupAreaDownload;
    private int mBackupAreaDownloadStateR;
    private int mBatteryManagementDeviceState;
    private int mBatteryVoltage;
    private int mBatteryVoltageSecond;
    private int mBcmOtaState;
    private int mBurglarState;
    private int mChargeAndDischargeSystemState;
    private int mChargeState;
    private int mDcState;
    private int mDischargeMainContactorState;
    private int mDischargeState;
    private int mDischargeStateVtol;
    private int mDoorLockStatusAreaLeftFront;
    private int mEclPower;
    private int mEngineHoodState;
    private float mEnvironmentTemp;
    private int mEnvironmentTempSecond;
    private int mEpbState;
    private int mGear;
    private int mHighVoltageCmd;
    private int mIg1RelayStateR;
    private int mIg3RelayState;
    private int mIg4RelayStateR;
    private int mIgnitionState;
    private int mMultimediaSelfUpdateS;
    private int mOtaEnergyTypeR;
    private int mOtaModel;
    private int mOtaPower;
    private int mOtaPowerLevel;
    private int mProhibitPowerOnStateR;
    private int mQuitChargeAndDischarge;
    private int mQuitSmartCharge;
    private int mSmartChargeState;
    private int mSmartChargeStateSecond;
    private int mSmartPower;
    private int mSmartPowerException;
    private int mSmartPowerState;
    private int mSmartPowerUsable;
    private int mSocValue;
    private int mSocValueR;
    private int mStartKeyStateR;
    private int mStrResetCycleReqR;
    private int mTurnOffState;
    private float mVehicleSpeed;
    private int mVehicleType;

    public int getStrResetCycleReqR() {
        return this.mStrResetCycleReqR;
    }

    public void setStrResetCycleReqR(int value) {
        this.mStrResetCycleReqR = value;
    }

    public int getIg1RelayStateR() {
        return this.mIg1RelayStateR;
    }

    public void setIg1RelayStateR(int ig1RelayStateR) {
        this.mIg1RelayStateR = ig1RelayStateR;
    }

    public int getIg4RelayStateR() {
        return this.mIg4RelayStateR;
    }

    public void setIg4RelayStateR(int ig4RelayStateR) {
        this.mIg4RelayStateR = ig4RelayStateR;
    }

    public int getDoorLockStatusAreaLeftFront() {
        return this.mDoorLockStatusAreaLeftFront;
    }

    public void setDoorLockStatusAreaLeftFront(int doorLockStatusAreaLeftFront) {
        this.mDoorLockStatusAreaLeftFront = doorLockStatusAreaLeftFront;
    }

    public int getMultimediaSelfUpdateS() {
        return this.mMultimediaSelfUpdateS;
    }

    public void setMultimediaSelfUpdateS(int multimediaSelfUpdateS) {
        this.mMultimediaSelfUpdateS = multimediaSelfUpdateS;
    }

    public int getProhibitPowerOnStateR() {
        return this.mProhibitPowerOnStateR;
    }

    public void setProhibitPowerOnStateR(int prohibitPowerOnStateR) {
        this.mProhibitPowerOnStateR = prohibitPowerOnStateR;
    }

    public int getStartKeyStateR() {
        return this.mStartKeyStateR;
    }

    public void setStartKeyStateR(int startKeyStateR) {
        this.mStartKeyStateR = startKeyStateR;
    }

    public int getOtaEnergyTypeR() {
        return this.mOtaEnergyTypeR;
    }

    public void setOtaEnergyTypeR(int otaEnergyTypeR) {
        this.mOtaEnergyTypeR = otaEnergyTypeR;
    }

    public int getOtaPowerLevel() {
        return this.mOtaPowerLevel;
    }

    public void setOtaPowerLevel(int otaPowerLevel) {
        this.mOtaPowerLevel = otaPowerLevel;
    }

    public int getSocValueR() {
        return this.mSocValueR;
    }

    public void setSocValueR(int socValueR) {
        this.mSocValueR = socValueR;
    }

    public int getBackupAreaDownloadStateR() {
        return this.mBackupAreaDownloadStateR;
    }

    public void setBackupAreaDownloadStateR(int backupAreaDownloadStateR) {
        this.mBackupAreaDownloadStateR = backupAreaDownloadStateR;
    }

    public int getBatteryManagementDeviceState() {
        return this.mBatteryManagementDeviceState;
    }

    public void setBatteryManagementDeviceState(int batteryManagementDeviceState) {
        this.mBatteryManagementDeviceState = batteryManagementDeviceState;
    }

    public int getBatteryVoltageSecond() {
        return this.mBatteryVoltageSecond;
    }

    public void setBatteryVoltageSecond(int batteryVoltageSecond) {
        this.mBatteryVoltageSecond = batteryVoltageSecond;
    }

    public int getChargeAndDischargeSystemState() {
        return this.mChargeAndDischargeSystemState;
    }

    public void setChargeAndDischargeSystemState(int chargeAndDischargeSystemState) {
        this.mChargeAndDischargeSystemState = chargeAndDischargeSystemState;
    }

    public int getDischargeStateVtol() {
        return this.mDischargeStateVtol;
    }

    public void setDischargeStateVtol(int dischargeStateVtol) {
        this.mDischargeStateVtol = dischargeStateVtol;
    }

    public int getEnvironmentTempSecond() {
        return this.mEnvironmentTempSecond;
    }

    public void setEnvironmentTempSecond(int environmentTempSecond) {
        this.mEnvironmentTempSecond = environmentTempSecond;
    }

    public int getSmartChargeStateSecond() {
        return this.mSmartChargeStateSecond;
    }

    public void setSmartChargeStateSecond(int smartChargeStateSecond) {
        this.mSmartChargeStateSecond = smartChargeStateSecond;
    }

    public int getDischargeMainContactorState() {
        return this.mDischargeMainContactorState;
    }

    public void setDischargeMainContactorState(int dischargeMainContactorState) {
        this.mDischargeMainContactorState = dischargeMainContactorState;
    }

    public int getBatteryVoltage() {
        return this.mBatteryVoltage;
    }

    public void setBatteryVoltage(int batteryVoltage) {
        this.mBatteryVoltage = batteryVoltage;
    }

    public int getBcmOtaState() {
        return this.mBcmOtaState;
    }

    public void setBcmOtaState(int bcmOtaState) {
        this.mBcmOtaState = bcmOtaState;
    }

    public int getBurglarState() {
        return this.mBurglarState;
    }

    public void setBurglarState(int burglarState) {
        this.mBurglarState = burglarState;
    }

    public int getChargeState() {
        return this.mChargeState;
    }

    public void setChargeState(int chargeState) {
        this.mChargeState = chargeState;
    }

    public int getDcState() {
        return this.mDcState;
    }

    public void setDcState(int dcState) {
        this.mDcState = dcState;
    }

    public int getDischargeState() {
        return this.mDischargeState;
    }

    public void setDischargeState(int dischargeState) {
        this.mDischargeState = dischargeState;
    }

    public int getEclPower() {
        return this.mEclPower;
    }

    public void setEclPower(int eclPower) {
        this.mEclPower = eclPower;
    }

    public int getEngineHoodState() {
        return this.mEngineHoodState;
    }

    public void setEngineHoodState(int engineHoodState) {
        this.mEngineHoodState = engineHoodState;
    }

    public float getEnvironmentTemp() {
        return this.mEnvironmentTemp;
    }

    public void setEnvironmentTemp(float environmentTemp) {
        this.mEnvironmentTemp = environmentTemp;
    }

    public int getEpbState() {
        return this.mEpbState;
    }

    public void setEpbState(int epbState) {
        this.mEpbState = epbState;
    }

    public int getGear() {
        return this.mGear;
    }

    public void setGear(int gear) {
        this.mGear = gear;
    }

    public int getHighVoltageCmd() {
        return this.mHighVoltageCmd;
    }

    public void setHighVoltageCmd(int highVoltageCmd) {
        this.mHighVoltageCmd = highVoltageCmd;
    }

    public int getIg3RelayState() {
        return this.mIg3RelayState;
    }

    public void setIg3RelayState(int ig3RelayState) {
        this.mIg3RelayState = ig3RelayState;
    }

    public int getIgnitionState() {
        return this.mIgnitionState;
    }

    public void setIgnitionState(int ignitionState) {
        this.mIgnitionState = ignitionState;
    }

    public int getOtaModel() {
        return this.mOtaModel;
    }

    public void setOtaModel(int otaModel) {
        this.mOtaModel = otaModel;
    }

    public int getOtaPower() {
        return this.mOtaPower;
    }

    public void setOtaPower(int otaPower) {
        this.mOtaPower = otaPower;
    }

    public int getQuitChargeAndDischarge() {
        return this.mQuitChargeAndDischarge;
    }

    public void setQuitChargeAndDischarge(int quitChargeAndDischarge) {
        this.mQuitChargeAndDischarge = quitChargeAndDischarge;
    }

    public int getQuitSmartCharge() {
        return this.mQuitSmartCharge;
    }

    public void setQuitSmartCharge(int quitSmartCharge) {
        this.mQuitSmartCharge = quitSmartCharge;
    }

    public int getSmartChargeState() {
        return this.mSmartChargeState;
    }

    public void setSmartChargeState(int smartChargeState) {
        this.mSmartChargeState = smartChargeState;
    }

    public int getSmartPower() {
        return this.mSmartPower;
    }

    public void setSmartPower(int smartPower) {
        this.mSmartPower = smartPower;
    }

    public int getSmartPowerException() {
        return this.mSmartPowerException;
    }

    public void setSmartPowerException(int smartPowerException) {
        this.mSmartPowerException = smartPowerException;
    }

    public int getSmartPowerState() {
        return this.mSmartPowerState;
    }

    public void setSmartPowerState(int smartPowerState) {
        this.mSmartPowerState = smartPowerState;
    }

    public int getSmartPowerUsable() {
        return this.mSmartPowerUsable;
    }

    public void setSmartPowerUsable(int smartPowerUsable) {
        this.mSmartPowerUsable = smartPowerUsable;
    }

    public int getSocValue() {
        return this.mSocValue;
    }

    public void setSocValue(int socValue) {
        this.mSocValue = socValue;
    }

    public int getTurnOffState() {
        return this.mTurnOffState;
    }

    public void setTurnOffState(int turnOffState) {
        this.mTurnOffState = turnOffState;
    }

    public float getVehicleSpeed() {
        return this.mVehicleSpeed;
    }

    public void setVehicleSpeed(float vehicleSpeed) {
        this.mVehicleSpeed = vehicleSpeed;
    }

    public int getVehicleType() {
        return this.mVehicleType;
    }

    public void setVehicleType(int vehicleType) {
        this.mVehicleType = vehicleType;
    }

    public int getBackupAreaDownload() {
        return this.mBackupAreaDownload;
    }

    public void setBackupAreaDownload(int backupAreaDownload) {
        this.mBackupAreaDownload = backupAreaDownload;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OtaEventChangeValue{");
        builder.append("PropertyId=0x" + propertyIdTenToHex());
        builder.append(" areaId=0x" + areaIdTenToHex());
        builder.append(" status=" + getStatus());
        builder.append(" timestamp=" + getTimestamp());
        builder.append(" value=" + getValue());
        builder.append(", mBatteryVoltage=" + this.mBatteryVoltage);
        builder.append(", mBcmOtaState=" + this.mBcmOtaState);
        builder.append(", mBurglarState=" + this.mBurglarState);
        builder.append(", mChargeState=" + this.mChargeState);
        builder.append(", mDcState=" + this.mDcState);
        builder.append(", mDischargeState=" + this.mDischargeState);
        builder.append(", mEclPower=" + this.mEclPower);
        builder.append(", mEngineHoodState=" + this.mEngineHoodState);
        builder.append(", mEnvironmentTemp=" + this.mEnvironmentTemp);
        builder.append(", mEpbState=" + this.mEpbState);
        builder.append(", mGear=" + this.mGear);
        builder.append(", mHighVoltageCmd=" + this.mHighVoltageCmd);
        builder.append(", mIg3RelayState=" + this.mIg3RelayState);
        builder.append(", mIgnitionState=" + this.mIgnitionState);
        builder.append(", mOtaModel=" + this.mOtaModel);
        builder.append(", mOtaPower=" + this.mOtaPower);
        builder.append(", mQuitChargeAndDischarge=" + this.mQuitChargeAndDischarge);
        builder.append(", mQuitSmartCharge=" + this.mQuitSmartCharge);
        builder.append(", mSmartChargeState=" + this.mSmartChargeState);
        builder.append(", mSmartPower=" + this.mSmartPower);
        builder.append(", mSmartPowerException=" + this.mSmartPowerException);
        builder.append(", mSmartPowerState=" + this.mSmartPowerState);
        builder.append(", mSmartPowerUsable=" + this.mSmartPowerUsable);
        builder.append(", mSocValue=" + this.mSocValue);
        builder.append(", mTurnOffState=" + this.mTurnOffState);
        builder.append(", mVehicleSpeed=" + this.mVehicleSpeed);
        builder.append(", mVehicleType=" + this.mVehicleType);
        builder.append(", mBackupAreaDownload=" + this.mBackupAreaDownload);
        builder.append(", mDischargeMainContactorState=" + this.mDischargeMainContactorState);
        builder.append(", mBatteryManagementDeviceState=" + this.mBatteryManagementDeviceState);
        builder.append(", mBatteryVoltageSecond=" + this.mBatteryVoltageSecond);
        builder.append(", mChargeAndDischargeSystemState=" + this.mChargeAndDischargeSystemState);
        builder.append(", mDischargeStateVtol=" + this.mDischargeStateVtol);
        builder.append(", mEnvironmentTempSecond=" + this.mEnvironmentTempSecond);
        builder.append(", mSmartChargeStateSecond=" + this.mSmartChargeStateSecond);
        builder.append(", mOtaEnergyTypeR=" + this.mOtaEnergyTypeR);
        builder.append(", mOtaPowerLevel=" + this.mOtaPowerLevel);
        builder.append(", mSocValueR=" + this.mSocValueR);
        builder.append("}");
        return builder.toString();
    }
}
