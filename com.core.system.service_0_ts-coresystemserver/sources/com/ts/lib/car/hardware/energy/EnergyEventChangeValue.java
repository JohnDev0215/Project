package com.ts.lib.car.hardware.energy;

import com.ts.lib.car.CarBaseProperty;

/* JADX INFO: loaded from: classes.dex */
public class EnergyEventChangeValue extends CarBaseProperty {
    private int mChangePHintR;
    private int mChargePortCoverHintS;
    private int mChargePortCoverTipR;
    private int mConfirmChargePortCoverS;
    private int mDriveDistanceShowModeR;
    private int mDriveDistanceShowModeS;
    private int mDriveDistanceShowStandardR;
    private int mDriveDistanceShowStandardS;
    private int mEPedalStateR;
    private int mEPedalStateS;
    private int mElectricPointFlag;
    private int mElectricShowModeR;
    private int mElectricShowModeS;
    private float[] mEnergyElectricCurveXR;
    private float[] mEnergyElectricCurveYR;
    private int mEnergyMode;
    private float[] mEnergyOilCurveXR;
    private float[] mEnergyOilCurveYR;
    private int mEnergyStateR;
    private int mHevElectricPointFlagR;
    private float mHevLast200MElectricConR;
    private float mLast200MElectricCon;
    private float mLast200MOilCon;
    private float mLast50KMElectricConAvg;
    private float mLast50KMOilConAvg;
    private int mMandatoryElectricStateR;
    private int mMandatoryElectricStateS;
    private int mMemoryElectricStateR;
    private int mMemoryElectricStateS;
    private int mMemoryMaxEvStateR;
    private int mMemoryMaxEvStateS;
    private int mMemorySportStateR;
    private int mMemorySportStateS;
    private int mOilPointFlag;
    private int mOperationMode;
    private int mOperationModeNormalR;
    private int mOperationModeS;
    private int mPowerGenerationState;
    private int mPowerGenerationValue;
    private int mPreSaleModelStateR;
    private int mPreSaleModelStateS;
    private int mResetCurveData;
    private int mResetCurveDataS;
    private int mRoadSurfaceMode;
    private int mRoadSurfaceModeNormalR;
    private int mRollBenchModeStatusR;
    private int mTripACPercent50KMR;
    private int mTripACPercentR;
    private int mTripDrivePercent50KMR;
    private int mTripDrivePercentR;
    private int mTripElecAppliancePercent50KMR;
    private int mTripElecAppliancePercentR;
    private float mTripElecConAvgR;
    private float mTripEnergyConAvgR;
    private float mTripLast50KMElectricConAvgR;
    private float mTripLast50KMFuelConAvgR;
    private float mTripLast50KMOilConAvgR;
    private float mTripOilConAvgR;
    private int mTripOtherPercent50KMR;
    private int mTripOtherPercentR;
    private int mTripPointFlagR;
    private int mYEnergyConLimitR;
    private float mYEnergyConR;
    private int miTacExitTagR;
    private int miTacModeR;
    private int miTacModeS;
    private int miTacpAllowTagR;
    private int miTacpStateR;
    private int miTacpStateS;

    public int getRollBenchModeStatusR() {
        return this.mRollBenchModeStatusR;
    }

    public void setRollBenchModeStatusR(int rollBenchModeStatusR) {
        this.mRollBenchModeStatusR = rollBenchModeStatusR;
    }

    public int getTripACPercent50KMR() {
        return this.mTripACPercent50KMR;
    }

    public void setTripACPercent50KMR(int tripACPercent50KMR) {
        this.mTripACPercent50KMR = tripACPercent50KMR;
    }

    public int getTripACPercentR() {
        return this.mTripACPercentR;
    }

    public void setTripACPercentR(int tripACPercentR) {
        this.mTripACPercentR = tripACPercentR;
    }

    public int getTripDrivePercent50KMR() {
        return this.mTripDrivePercent50KMR;
    }

    public void setTripDrivePercent50KMR(int tripDrivePercent50KMR) {
        this.mTripDrivePercent50KMR = tripDrivePercent50KMR;
    }

    public int getTripDrivePercentR() {
        return this.mTripDrivePercentR;
    }

    public void setTripDrivePercentR(int tripDrivePercentR) {
        this.mTripDrivePercentR = tripDrivePercentR;
    }

    public int getTripElecAppliancePercent50KMR() {
        return this.mTripElecAppliancePercent50KMR;
    }

    public void setTripElecAppliancePercent50KMR(int tripElecAppliancePercent50KMR) {
        this.mTripElecAppliancePercent50KMR = tripElecAppliancePercent50KMR;
    }

    public int getTripElecAppliancePercentR() {
        return this.mTripElecAppliancePercentR;
    }

    public void setTripElecAppliancePercentR(int tripElecAppliancePercentR) {
        this.mTripElecAppliancePercentR = tripElecAppliancePercentR;
    }

    public float getTripElecConAvgR() {
        return this.mTripElecConAvgR;
    }

    public void setTripElecConAvgR(float tripElecConAvgR) {
        this.mTripElecConAvgR = tripElecConAvgR;
    }

    public float getTripEnergyConAvgR() {
        return this.mTripEnergyConAvgR;
    }

    public void setTripEnergyConAvgR(float tripEnergyConAvgR) {
        this.mTripEnergyConAvgR = tripEnergyConAvgR;
    }

    public float getTripLast50KMElectricConAvgR() {
        return this.mTripLast50KMElectricConAvgR;
    }

    public void setTripLast50KMElectricConAvgR(float tripLast50KMElectricConAvgR) {
        this.mTripLast50KMElectricConAvgR = tripLast50KMElectricConAvgR;
    }

    public float getTripLast50KMFuelConAvgR() {
        return this.mTripLast50KMFuelConAvgR;
    }

    public void setTripLast50KMFuelConAvgR(float tripLast50KMFuelConAvgR) {
        this.mTripLast50KMFuelConAvgR = tripLast50KMFuelConAvgR;
    }

    public float getTripLast50KMOilConAvgR() {
        return this.mTripLast50KMOilConAvgR;
    }

    public void setTripLast50KMOilConAvgR(float tripLast50KMOilConAvgR) {
        this.mTripLast50KMOilConAvgR = tripLast50KMOilConAvgR;
    }

    public float getTripOilConAvgR() {
        return this.mTripOilConAvgR;
    }

    public void setTripOilConAvgR(float tripOilConAvgR) {
        this.mTripOilConAvgR = tripOilConAvgR;
    }

    public int getTripOtherPercent50KMR() {
        return this.mTripOtherPercent50KMR;
    }

    public void setTripOtherPercent50KMR(int tripOtherPercent50KMR) {
        this.mTripOtherPercent50KMR = tripOtherPercent50KMR;
    }

    public int getTripOtherPercentR() {
        return this.mTripOtherPercentR;
    }

    public void setTripOtherPercentR(int tripOtherPercentR) {
        this.mTripOtherPercentR = tripOtherPercentR;
    }

    public int getTripPointFlagR() {
        return this.mTripPointFlagR;
    }

    public void setTripPointFlagR(int tripPointFlagR) {
        this.mTripPointFlagR = tripPointFlagR;
    }

    public int getYEnergyConLimitR() {
        return this.mYEnergyConLimitR;
    }

    public void setYEnergyConLimitR(int yEnergyConLimitR) {
        this.mYEnergyConLimitR = yEnergyConLimitR;
    }

    public float getYEnergyConR() {
        return this.mYEnergyConR;
    }

    public void setYEnergyConR(float yEnergyConR) {
        this.mYEnergyConR = yEnergyConR;
    }

    public int getChargePortCoverHintS() {
        return this.mChargePortCoverHintS;
    }

    public void setChargePortCoverHintS(int chargePortCoverHintS) {
        this.mChargePortCoverHintS = chargePortCoverHintS;
    }

    public int getConfirmChargePortCoverS() {
        return this.mConfirmChargePortCoverS;
    }

    public void setConfirmChargePortCoverS(int confirmChargePortCoverS) {
        this.mConfirmChargePortCoverS = confirmChargePortCoverS;
    }

    public int getChargePortCoverTipR() {
        return this.mChargePortCoverTipR;
    }

    public void setChargePortCoverTipR(int chargePortCoverTipR) {
        this.mChargePortCoverTipR = chargePortCoverTipR;
    }

    public int getChangePHintR() {
        return this.mChangePHintR;
    }

    public void setChangePHintR(int changePHintR) {
        this.mChangePHintR = changePHintR;
    }

    public int getiTacExitTagR() {
        return this.miTacExitTagR;
    }

    public void setiTacExitTagR(int iTacExitTagR) {
        this.miTacExitTagR = iTacExitTagR;
    }

    public int getiTacModeR() {
        return this.miTacModeR;
    }

    public void setiTacModeR(int iTacModeR) {
        this.miTacModeR = iTacModeR;
    }

    public int getiTacModeS() {
        return this.miTacModeS;
    }

    public void setiTacModeS(int iTacModeS) {
        this.miTacModeS = iTacModeS;
    }

    public int getiTacpAllowTagR() {
        return this.miTacpAllowTagR;
    }

    public void setiTacpAllowTagR(int iTacpAllowTagR) {
        this.miTacpAllowTagR = iTacpAllowTagR;
    }

    public int getiTacpStateR() {
        return this.miTacpStateR;
    }

    public void setiTacpStateR(int iTacpStateR) {
        this.miTacpStateR = iTacpStateR;
    }

    public int getiTacpStateS() {
        return this.miTacpStateS;
    }

    public void setiTacpStateS(int iTacpStateS) {
        this.miTacpStateS = iTacpStateS;
    }

    public int getOperationModeS() {
        return this.mOperationModeS;
    }

    public void setOperationModeS(int operationModeS) {
        this.mOperationModeS = operationModeS;
    }

    public int getRoadSurfaceModeNormalR() {
        return this.mRoadSurfaceModeNormalR;
    }

    public void setRoadSurfaceModeNormalR(int roadSurfaceModeNormalR) {
        this.mRoadSurfaceModeNormalR = roadSurfaceModeNormalR;
    }

    public int getOperationModeNormalR() {
        return this.mOperationModeNormalR;
    }

    public void setOperationModeNormalR(int operationModeNormalR) {
        this.mOperationModeNormalR = operationModeNormalR;
    }

    public int getHevElectricPointFlagR() {
        return this.mHevElectricPointFlagR;
    }

    public void setHevElectricPointFlagR(int hevElectricPointFlagR) {
        this.mHevElectricPointFlagR = hevElectricPointFlagR;
    }

    public float getHevLast200MElectricConR() {
        return this.mHevLast200MElectricConR;
    }

    public void setHevLast200MElectricConR(float hevLast200MElectricConR) {
        this.mHevLast200MElectricConR = hevLast200MElectricConR;
    }

    public int getResetCurveDataS() {
        return this.mResetCurveDataS;
    }

    public void setResetCurveDataS(int resetCurveDataS) {
        this.mResetCurveDataS = resetCurveDataS;
    }

    public int getElectricPointFlag() {
        return this.mElectricPointFlag;
    }

    public void setElectricPointFlag(int electricPointFlag) {
        this.mElectricPointFlag = electricPointFlag;
    }

    public int getEnergyStateR() {
        return this.mEnergyStateR;
    }

    public void setEnergyStateR(int energyStateR) {
        this.mEnergyStateR = energyStateR;
    }

    public float getLast200MElectricCon() {
        return this.mLast200MElectricCon;
    }

    public void setLast200MElectricCon(float last200MElectricCon) {
        this.mLast200MElectricCon = last200MElectricCon;
    }

    public float getLast200MOilCon() {
        return this.mLast200MOilCon;
    }

    public void setLast200MOilCon(float last200MOilCon) {
        this.mLast200MOilCon = last200MOilCon;
    }

    public float getLast50KMElectricConAvg() {
        return this.mLast50KMElectricConAvg;
    }

    public void setLast50KMElectricConAvg(float last50KMElectricConAvg) {
        this.mLast50KMElectricConAvg = last50KMElectricConAvg;
    }

    public float getLast50KMOilConAvg() {
        return this.mLast50KMOilConAvg;
    }

    public void setLast50KMOilConAvg(float last50KMOilConAvg) {
        this.mLast50KMOilConAvg = last50KMOilConAvg;
    }

    public int getOilPointFlag() {
        return this.mOilPointFlag;
    }

    public void setOilPointFlag(int oilPointFlag) {
        this.mOilPointFlag = oilPointFlag;
    }

    public int getDriveDistanceShowModeR() {
        return this.mDriveDistanceShowModeR;
    }

    public void setDriveDistanceShowModeR(int driveDistanceShowModeR) {
        this.mDriveDistanceShowModeR = driveDistanceShowModeR;
    }

    public int getDriveDistanceShowModeS() {
        return this.mDriveDistanceShowModeS;
    }

    public void setDriveDistanceShowModeS(int driveDistanceShowModeS) {
        this.mDriveDistanceShowModeS = driveDistanceShowModeS;
    }

    public int getDriveDistanceShowStandardR() {
        return this.mDriveDistanceShowStandardR;
    }

    public void setDriveDistanceShowStandardR(int driveDistanceShowStandardR) {
        this.mDriveDistanceShowStandardR = driveDistanceShowStandardR;
    }

    public int getDriveDistanceShowStandardS() {
        return this.mDriveDistanceShowStandardS;
    }

    public void setDriveDistanceShowStandardS(int driveDistanceShowStandardS) {
        this.mDriveDistanceShowStandardS = driveDistanceShowStandardS;
    }

    public int getEPedalStateR() {
        return this.mEPedalStateR;
    }

    public void setEPedalStateR(int ePedalStateR) {
        this.mEPedalStateR = ePedalStateR;
    }

    public int getEPedalStateS() {
        return this.mEPedalStateS;
    }

    public void setEPedalStateS(int ePedalStateS) {
        this.mEPedalStateS = ePedalStateS;
    }

    public int getElectricShowModeR() {
        return this.mElectricShowModeR;
    }

    public void setElectricShowModeR(int electricShowModeR) {
        this.mElectricShowModeR = electricShowModeR;
    }

    public int getElectricShowModeS() {
        return this.mElectricShowModeS;
    }

    public void setElectricShowModeS(int electricShowModeS) {
        this.mElectricShowModeS = electricShowModeS;
    }

    public float[] getEnergyElectricCurveXR() {
        return this.mEnergyElectricCurveXR;
    }

    public void setEnergyElectricCurveXR(float[] energyElectricCurveXR) {
        this.mEnergyElectricCurveXR = energyElectricCurveXR;
    }

    public float[] getEnergyElectricCurveYR() {
        return this.mEnergyElectricCurveYR;
    }

    public void setEnergyElectricCurveYR(float[] energyElectricCurveYR) {
        this.mEnergyElectricCurveYR = energyElectricCurveYR;
    }

    public int getEnergyMode() {
        return this.mEnergyMode;
    }

    public void setEnergyMode(int energyMode) {
        this.mEnergyMode = energyMode;
    }

    public float[] getEnergyOilCurveXR() {
        return this.mEnergyOilCurveXR;
    }

    public void setEnergyOilCurveXR(float[] energyOilCurveXR) {
        this.mEnergyOilCurveXR = energyOilCurveXR;
    }

    public float[] getEnergyOilCurveYR() {
        return this.mEnergyOilCurveYR;
    }

    public void setEnergyOilCurveYR(float[] energyOilCurveYR) {
        this.mEnergyOilCurveYR = energyOilCurveYR;
    }

    public int getMandatoryElectricStateR() {
        return this.mMandatoryElectricStateR;
    }

    public void setMandatoryElectricStateR(int mandatoryElectricStateR) {
        this.mMandatoryElectricStateR = mandatoryElectricStateR;
    }

    public int getMandatoryElectricStateS() {
        return this.mMandatoryElectricStateS;
    }

    public void setMandatoryElectricStateS(int mandatoryElectricStateS) {
        this.mMandatoryElectricStateS = mandatoryElectricStateS;
    }

    public int getMemoryElectricStateR() {
        return this.mMemoryElectricStateR;
    }

    public void setMemoryElectricStateR(int memoryElectricStateR) {
        this.mMemoryElectricStateR = memoryElectricStateR;
    }

    public int getMemoryElectricStateS() {
        return this.mMemoryElectricStateS;
    }

    public void setMemoryElectricStateS(int memoryElectricStateS) {
        this.mMemoryElectricStateS = memoryElectricStateS;
    }

    public int getMemoryMaxEvStateR() {
        return this.mMemoryMaxEvStateR;
    }

    public void setMemoryMaxEvStateR(int memoryMaxEvStateR) {
        this.mMemoryMaxEvStateR = memoryMaxEvStateR;
    }

    public int getMemoryMaxEvStateS() {
        return this.mMemoryMaxEvStateS;
    }

    public void setMemoryMaxEvStateS(int memoryMaxEvStateS) {
        this.mMemoryMaxEvStateS = memoryMaxEvStateS;
    }

    public int getMemorySportStateR() {
        return this.mMemorySportStateR;
    }

    public void setMemorySportStateR(int memorySportStateR) {
        this.mMemorySportStateR = memorySportStateR;
    }

    public int getMemorySportStateS() {
        return this.mMemorySportStateS;
    }

    public void setMemorySportStateS(int memorySportStateS) {
        this.mMemorySportStateS = memorySportStateS;
    }

    public int getOperationMode() {
        return this.mOperationMode;
    }

    public void setOperationMode(int operationMode) {
        this.mOperationMode = operationMode;
    }

    public int getPowerGenerationState() {
        return this.mPowerGenerationState;
    }

    public void setPowerGenerationState(int powerGenerationState) {
        this.mPowerGenerationState = powerGenerationState;
    }

    public int getPowerGenerationValue() {
        return this.mPowerGenerationValue;
    }

    public void setPowerGenerationValue(int powerGenerationValue) {
        this.mPowerGenerationValue = powerGenerationValue;
    }

    public int getPreSaleModelStateR() {
        return this.mPreSaleModelStateR;
    }

    public void setPreSaleModelStateR(int preSaleModelStateR) {
        this.mPreSaleModelStateR = preSaleModelStateR;
    }

    public int getPreSaleModelStateS() {
        return this.mPreSaleModelStateS;
    }

    public void setPreSaleModelStateS(int preSaleModelStateS) {
        this.mPreSaleModelStateS = preSaleModelStateS;
    }

    public int getResetCurveData() {
        return this.mResetCurveData;
    }

    public void setResetCurveData(int resetCurveData) {
        this.mResetCurveData = resetCurveData;
    }

    public int getRoadSurfaceMode() {
        return this.mRoadSurfaceMode;
    }

    public void setRoadSurfaceMode(int roadSurfaceMode) {
        this.mRoadSurfaceMode = roadSurfaceMode;
    }
}
