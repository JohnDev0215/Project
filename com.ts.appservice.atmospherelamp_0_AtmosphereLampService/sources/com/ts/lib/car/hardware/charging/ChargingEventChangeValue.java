package com.ts.lib.car.hardware.charging;

import com.ts.lib.car.CarBaseProperty;

/* JADX INFO: loaded from: classes3.dex */
public class ChargingEventChangeValue extends CarBaseProperty {
    private int mAcChargingLimitR;
    private int mAcChargingLimitS;
    private int mBatteryManagementDeviceStateR;
    private int mBatteryTypeR;
    private int mBmsWakeNoSenseCharge10R;
    private int mBmsWakeNoSenseCharge11R;
    private int mBmsWakeNoSenseCharge12R;
    private int mBmsWakeNoSenseCharge13R;
    private int mBmsWakeNoSenseCharge14R;
    private int mBmsWakeNoSenseCharge15R;
    private int mBmsWakeNoSenseCharge16R;
    private int mBmsWakeNoSenseCharge17R;
    private int mBmsWakeNoSenseCharge18R;
    private int mBmsWakeNoSenseCharge19R;
    private int mBmsWakeNoSenseCharge1R;
    private int mBmsWakeNoSenseCharge20R;
    private int mBmsWakeNoSenseCharge21R;
    private int mBmsWakeNoSenseCharge22R;
    private int mBmsWakeNoSenseCharge23R;
    private int mBmsWakeNoSenseCharge24R;
    private int mBmsWakeNoSenseCharge25R;
    private int mBmsWakeNoSenseCharge26R;
    private int mBmsWakeNoSenseCharge27R;
    private int mBmsWakeNoSenseCharge28R;
    private int mBmsWakeNoSenseCharge29R;
    private int mBmsWakeNoSenseCharge2R;
    private int mBmsWakeNoSenseCharge30R;
    private int mBmsWakeNoSenseCharge31R;
    private int mBmsWakeNoSenseCharge32R;
    private int mBmsWakeNoSenseCharge33R;
    private int mBmsWakeNoSenseCharge34R;
    private int mBmsWakeNoSenseCharge35R;
    private int mBmsWakeNoSenseCharge3R;
    private int mBmsWakeNoSenseCharge4R;
    private int mBmsWakeNoSenseCharge5R;
    private int mBmsWakeNoSenseCharge6R;
    private int mBmsWakeNoSenseCharge7R;
    private int mBmsWakeNoSenseCharge8R;
    private int mBmsWakeNoSenseCharge9R;
    private int mCarPlanAccOnStateR;
    private int mCarPlanBatteryHeatStateR;
    private int mCarPlanDayS;
    private int mCarPlanHourS;
    private int mCarPlanMinuteS;
    private int mCarPlanMonthS;
    private int mCarPlanStateR;
    private int mCarPlanStateS;
    private int mCarPlanYearS;
    private int mChargeBatteryLevelBeginR;
    private int mChargeBatteryLimitTempR;
    private int mChargeBatteryTempR;
    private float mChargeElectricR;
    private int mChargePlanStateS;
    private int mChargePreheatAllowR;
    private int mChargePreheatStateR;
    private int mChargePreheatStateS;
    private int mChargeStopCapacityStateNotLiR;
    private int mChargeStopCapacityStateR;
    private int mChargeStopCapacityStateS;
    private int mChargeTempCtlStateR;
    private int mChargeTempCtlStateS;
    private int mChargeToaggleGuardMixR;
    private int mChargeToggleGuardR;
    private int mChargeToggleGuardS;
    private float mChargeVoltageMaxR;
    private int mChargeVoltageR;
    private int mChargerFaultStateR;
    private int mChargerStateR;
    private int mChargerWorkStateFixR;
    private int mChargerWorkStateR;
    private int mChargingCapStateLeftR;
    private int mChargingCapStateRightR;
    private int mChargingGunStateR;
    private float mChargingPowerR;
    private int mChargingResetTimeHourR;
    private int mChargingResetTimeMinuteR;
    private int mChargingRestTimeHourR;
    private int mChargingRestTimeMinuteR;
    private int mChargingScheduleEnableStateR;
    private int mChargingScheduleStateR;
    private int mChargingScheduleTimeDayR;
    private int mChargingScheduleTimeHourR;
    private int mChargingScheduleTimeMinuteR;
    private int mChargingScheduleTimeMonthR;
    private int mChargingScheduleTimeYearR;
    private int mCharingPortLockRebackStateR;
    private float mDischargeElectricR;
    private int mDischargeGunTimerR;
    private int mDischargeLimitR;
    private int mDischargeLimitS;
    private int mDischargeOverReasonR;
    private int mDischargeRemainTimeR;
    private int mDischargeRequestStateR;
    private int mDischargeTimeHourR;
    private int mDischargeTimeHourS;
    private int mDischargeTimeMinuteR;
    private int mDischargeTimeMinuteS;
    private int mDischargeToggleR;
    private int mDischargeToggleS;
    private int mDischargeTotalTimeR;
    private int mDischargeVoltageR;
    private int mElectricPortCapStateLeftR;
    private int mElectricPortCapStateRightR;
    private int[] mElectricityPriceLeveSixS;
    private int mElectricityPriceLevel10S;
    private int mElectricityPriceLevel11S;
    private int mElectricityPriceLevel12S;
    private int mElectricityPriceLevel13S;
    private int mElectricityPriceLevel14S;
    private int mElectricityPriceLevel15S;
    private int mElectricityPriceLevel16S;
    private int mElectricityPriceLevel17S;
    private int mElectricityPriceLevel18S;
    private int mElectricityPriceLevel19S;
    private int mElectricityPriceLevel1S;
    private int mElectricityPriceLevel20S;
    private int mElectricityPriceLevel21S;
    private int mElectricityPriceLevel22S;
    private int mElectricityPriceLevel23S;
    private int mElectricityPriceLevel24S;
    private int mElectricityPriceLevel25S;
    private int mElectricityPriceLevel26S;
    private int mElectricityPriceLevel27S;
    private int mElectricityPriceLevel28S;
    private int mElectricityPriceLevel29S;
    private int mElectricityPriceLevel2S;
    private int mElectricityPriceLevel30S;
    private int mElectricityPriceLevel31S;
    private int mElectricityPriceLevel32S;
    private int mElectricityPriceLevel33S;
    private int mElectricityPriceLevel34S;
    private int mElectricityPriceLevel35S;
    private int mElectricityPriceLevel36S;
    private int mElectricityPriceLevel37S;
    private int mElectricityPriceLevel38S;
    private int mElectricityPriceLevel39S;
    private int mElectricityPriceLevel3S;
    private int mElectricityPriceLevel40S;
    private int mElectricityPriceLevel41S;
    private int mElectricityPriceLevel42S;
    private int mElectricityPriceLevel43S;
    private int mElectricityPriceLevel44S;
    private int mElectricityPriceLevel45S;
    private int mElectricityPriceLevel46S;
    private int mElectricityPriceLevel47S;
    private int mElectricityPriceLevel48S;
    private int mElectricityPriceLevel4S;
    private int mElectricityPriceLevel5S;
    private int mElectricityPriceLevel6S;
    private int mElectricityPriceLevel7S;
    private int mElectricityPriceLevel8S;
    private int mElectricityPriceLevel9S;
    private int[] mElectricityPriceLevelEightS;
    private int[] mElectricityPriceLevelNineS;
    private int[] mElectricityPriceLevelSevenS;
    private int mEvChargePlanEndTimeDayS;
    private int mEvChargePlanEndTimeHourS;
    private int mEvChargePlanEndTimeMinuteS;
    private int mEvChargePlanEndTimeMonthS;
    private int mEvChargePlanEndTimeYearS;
    private int mEvChargePlanStartTimeDayS;
    private int mEvChargePlanStartTimeHourS;
    private int mEvChargePlanStartTimeMinuteS;
    private int mEvChargePlanStartTimeMonthS;
    private int mEvChargePlanStartTimeYearS;
    private int mFullChgRemanTimHR;
    private int mFullChgRemanTimMR;
    private int mHevChargePlanEndTimeDayS;
    private int mHevChargePlanEndTimeHourS;
    private int mHevChargePlanEndTimeMinuteS;
    private int mHevChargePlanEndTimeMonthR;
    private int mHevChargePlanEndTimeMonthS;
    private int mHevChargePlanEndTimeTagR;
    private int mHevChargePlanEndTimeYearDayR;
    private int mHevChargePlanEndTimeYearHourR;
    private int mHevChargePlanEndTimeYearMinuteR;
    private int mHevChargePlanEndTimeYearS;
    private int mHevChargePlanStartTimeDayS;
    private int mHevChargePlanStartTimeHourS;
    private int mHevChargePlanStartTimeMinuteS;
    private int mHevChargePlanStartTimeMonthR;
    private int mHevChargePlanStartTimeMonthS;
    private int[] mHevChargePlanStartTimeR;
    private int mHevChargePlanStartTimeTagR;
    private int mHevChargePlanStartTimeYearDayR;
    private int mHevChargePlanStartTimeYearHourR;
    private int mHevChargePlanStartTimeYearMinuteR;
    private int mHevChargePlanStartTimeYearR;
    private int mHevChargePlanStartTimeYearS;
    private int mHevChargePlaneEndTimeYearR;
    private int mHevChargingType;
    private int mMovePowerStationToggleR;
    private int mMovePowerStationToggleS;
    private int[] mNewCarPlanMonthS;
    private int mNoSenseChargeStateR;
    private int mNoSenseChargeStateS;
    private int mObcWakeNoSenseCharge10R;
    private int mObcWakeNoSenseCharge11R;
    private int mObcWakeNoSenseCharge12R;
    private int mObcWakeNoSenseCharge13R;
    private int mObcWakeNoSenseCharge14R;
    private int mObcWakeNoSenseCharge15R;
    private int mObcWakeNoSenseCharge16R;
    private int mObcWakeNoSenseCharge17R;
    private int mObcWakeNoSenseCharge18R;
    private int mObcWakeNoSenseCharge19R;
    private int mObcWakeNoSenseCharge1R;
    private int mObcWakeNoSenseCharge20R;
    private int mObcWakeNoSenseCharge21R;
    private int mObcWakeNoSenseCharge22R;
    private int mObcWakeNoSenseCharge23R;
    private int mObcWakeNoSenseCharge24R;
    private int mObcWakeNoSenseCharge25R;
    private int mObcWakeNoSenseCharge26R;
    private int mObcWakeNoSenseCharge27R;
    private int mObcWakeNoSenseCharge28R;
    private int mObcWakeNoSenseCharge29R;
    private int mObcWakeNoSenseCharge2R;
    private int mObcWakeNoSenseCharge30R;
    private int mObcWakeNoSenseCharge31R;
    private int mObcWakeNoSenseCharge32R;
    private int mObcWakeNoSenseCharge33R;
    private int mObcWakeNoSenseCharge34R;
    private int mObcWakeNoSenseCharge35R;
    private int mObcWakeNoSenseCharge3R;
    private int mObcWakeNoSenseCharge4R;
    private int mObcWakeNoSenseCharge5R;
    private int mObcWakeNoSenseCharge6R;
    private int mObcWakeNoSenseCharge7R;
    private int mObcWakeNoSenseCharge8R;
    private int mObcWakeNoSenseCharge9R;
    private int mProducePowerWhenLowPowerR;
    private int mProducePowerWhenLowPowerS;
    private int mSituPowerGenerationStateR;
    private int mSituPowerGenerationStateS;
    private int mSituPowerGenerationSupportR;
    private int mSyncTimeDayS;
    private int mSyncTimeHourS;
    private int mSyncTimeMinuteS;
    private int mSyncTimeMonthS;
    private int mSyncTimeYearS;
    private float mVoltageMaxR;
    private int mVtovChargeBatteryLevelNumLimitR;
    private int mVtovChargeBatteryLevelNumR;
    private int mVtovDischargeConnectState;
    private int mVtovDischargeLimitValR;
    private int mVtovDischargeLowPowerHintR;
    private int mVtovDischargeLowestValR;
    private float mVtovDischargeQuantityR;
    private int mWirelessChargingFrStateR;
    private int mWirelessChargingFrSwitchStateR;
    private int mWirelessChargingFrSwitchStateS;
    private int mWirelessChargingPowerFlStateR;
    private int mWirelessChargingPowerFrStateR;
    private int mWirelessChargingStateR;
    private int mWirelessChargingSwitchStateR;
    private int mWirelessChargingSwitchStateS;
    private int mgetPortCapStateLeftR;
    private int mgetPortCapStateRightR;
    private int mv2gChargeStatusR;
    private int mv2gCmdS;
    private int mv2gOutputPowerR;
    private float mv2gPowerS;
    private int mv2gStatusR;
    private int mv2gStatusS;

    public int getElectricPortCapStateLeftR() {
        return this.mElectricPortCapStateLeftR;
    }

    public void setElectricPortCapStateLeftR(int electricPortCapStateLeftR) {
        this.mElectricPortCapStateLeftR = electricPortCapStateLeftR;
    }

    public int getElectricPortCapStateRightR() {
        return this.mElectricPortCapStateRightR;
    }

    public void setElectricPortCapStateRightR(int electricPortCapStateRightR) {
        this.mElectricPortCapStateRightR = electricPortCapStateRightR;
    }

    public int getChargeStopCapacityStateNotLiR() {
        return this.mChargeStopCapacityStateNotLiR;
    }

    public void setChargeStopCapacityStateNotLiR(int chargeStopCapacityStateNotLiR) {
        this.mChargeStopCapacityStateNotLiR = chargeStopCapacityStateNotLiR;
    }

    public int getv2gChargeStatusR() {
        return this.mv2gChargeStatusR;
    }

    public void setv2gChargeStatusR(int v2gChargeStatusR) {
        this.mv2gChargeStatusR = v2gChargeStatusR;
    }

    public int getv2gCmdS() {
        return this.mv2gCmdS;
    }

    public void setv2gCmdS(int v2gCmdS) {
        this.mv2gCmdS = v2gCmdS;
    }

    public int getv2gOutputPowerR() {
        return this.mv2gOutputPowerR;
    }

    public void setv2gOutputPowerR(int v2gOutputPowerR) {
        this.mv2gOutputPowerR = v2gOutputPowerR;
    }

    public float getv2gPowerS() {
        return this.mv2gPowerS;
    }

    public void setv2gPowerS(float v2gPowerS) {
        this.mv2gPowerS = v2gPowerS;
    }

    public int getv2gStatusR() {
        return this.mv2gStatusR;
    }

    public void setv2gStatusR(int v2gStatusR) {
        this.mv2gStatusR = v2gStatusR;
    }

    public int getv2gStatusS() {
        return this.mv2gStatusS;
    }

    public void setv2gStatusS(int v2gStatusS) {
        this.mv2gStatusS = v2gStatusS;
    }

    public int getAcChargingLimitR() {
        return this.mAcChargingLimitR;
    }

    public void setAcChargingLimitR(int acChargingLimitR) {
        this.mAcChargingLimitR = acChargingLimitR;
    }

    public int getAcChargingLimitS() {
        return this.mAcChargingLimitS;
    }

    public void setAcChargingLimitS(int acChargingLimitS) {
        this.mAcChargingLimitS = acChargingLimitS;
    }

    public int[] getHevChargePlanEndTimeR() {
        return this.mHevChargePlanStartTimeR;
    }

    public void setHevChargePlanEndTimeR(int[] value) {
        this.mHevChargePlanStartTimeR = value;
    }

    public int[] getNewCarPlanMonthS() {
        return this.mNewCarPlanMonthS;
    }

    public void setNewCarPlanMonthS(int[] carPlanMonthS) {
        this.mNewCarPlanMonthS = carPlanMonthS;
    }

    public int[] getElectricityPriceLeveSixS() {
        return this.mElectricityPriceLeveSixS;
    }

    public void setElectricityPriceLeveSixS(int[] electricityPriceLeveSixS) {
        this.mElectricityPriceLeveSixS = electricityPriceLeveSixS;
    }

    public int[] getElectricityPriceLevelEightS() {
        return this.mElectricityPriceLevelEightS;
    }

    public void setElectricityPriceLevelEightS(int[] electricityPriceLevelEightS) {
        this.mElectricityPriceLevelEightS = electricityPriceLevelEightS;
    }

    public int[] getElectricityPriceLevelNineS() {
        return this.mElectricityPriceLevelNineS;
    }

    public void setElectricityPriceLevelNineS(int[] electricityPriceLevelNineS) {
        this.mElectricityPriceLevelNineS = electricityPriceLevelNineS;
    }

    public int[] getElectricityPriceLevelSevenS() {
        return this.mElectricityPriceLevelSevenS;
    }

    public void setElectricityPriceLevelSevenS(int[] electricityPriceLevelSevenS) {
        this.mElectricityPriceLevelSevenS = electricityPriceLevelSevenS;
    }

    public int getHevChargePlanEndTimeTagR() {
        return this.mHevChargePlanEndTimeTagR;
    }

    public void setHevChargePlanEndTimeTagR(int hevChargePlanEndTimeTagR) {
        this.mHevChargePlanEndTimeTagR = hevChargePlanEndTimeTagR;
    }

    public int getHevChargePlanStartTimeTagR() {
        return this.mHevChargePlanStartTimeTagR;
    }

    public void setHevChargePlanStartTimeTagR(int hevChargePlanStartTimeTagR) {
        this.mHevChargePlanStartTimeTagR = hevChargePlanStartTimeTagR;
    }

    public int getHevChargePlanEndTimeMonthR() {
        return this.mHevChargePlanEndTimeMonthR;
    }

    public void setHevChargePlanEndTimeMonthR(int hevChargePlanEndTimeMonthR) {
        this.mHevChargePlanEndTimeMonthR = hevChargePlanEndTimeMonthR;
    }

    public int getHevChargePlanEndTimeYearDayR() {
        return this.mHevChargePlanEndTimeYearDayR;
    }

    public void setHevChargePlanEndTimeYearDayR(int hevChargePlanEndTimeYearDayR) {
        this.mHevChargePlanEndTimeYearDayR = hevChargePlanEndTimeYearDayR;
    }

    public int getHevChargePlanEndTimeYearHourR() {
        return this.mHevChargePlanEndTimeYearHourR;
    }

    public void setHevChargePlanEndTimeYearHourR(int hevChargePlanEndTimeYearHourR) {
        this.mHevChargePlanEndTimeYearHourR = hevChargePlanEndTimeYearHourR;
    }

    public int getHevChargePlanEndTimeYearMinuteR() {
        return this.mHevChargePlanEndTimeYearMinuteR;
    }

    public void setHevChargePlanEndTimeYearMinuteR(int hevChargePlanEndTimeYearMinuteR) {
        this.mHevChargePlanEndTimeYearMinuteR = hevChargePlanEndTimeYearMinuteR;
    }

    public int getHevChargePlanStartTimeMonthR() {
        return this.mHevChargePlanStartTimeMonthR;
    }

    public void setHevChargePlanStartTimeMonthR(int hevChargePlanStartTimeMonthR) {
        this.mHevChargePlanStartTimeMonthR = hevChargePlanStartTimeMonthR;
    }

    public int getHevChargePlanStartTimeYearDayR() {
        return this.mHevChargePlanStartTimeYearDayR;
    }

    public void setHevChargePlanStartTimeYearDayR(int hevChargePlanStartTimeYearDayR) {
        this.mHevChargePlanStartTimeYearDayR = hevChargePlanStartTimeYearDayR;
    }

    public int getHevChargePlanStartTimeYearHourR() {
        return this.mHevChargePlanStartTimeYearHourR;
    }

    public void setHevChargePlanStartTimeYearHourR(int hevChargePlanStartTimeYearHourR) {
        this.mHevChargePlanStartTimeYearHourR = hevChargePlanStartTimeYearHourR;
    }

    public int getHevChargePlanStartTimeYearMinuteR() {
        return this.mHevChargePlanStartTimeYearMinuteR;
    }

    public void setHevChargePlanStartTimeYearMinuteR(int hevChargePlanStartTimeYearMinuteR) {
        this.mHevChargePlanStartTimeYearMinuteR = hevChargePlanStartTimeYearMinuteR;
    }

    public int getHevChargePlanStartTimeYearR() {
        return this.mHevChargePlanStartTimeYearR;
    }

    public void setHevChargePlanStartTimeYearR(int hevChargePlanStartTimeYearR) {
        this.mHevChargePlanStartTimeYearR = hevChargePlanStartTimeYearR;
    }

    public int getHevChargePlaneEndTimeYearR() {
        return this.mHevChargePlaneEndTimeYearR;
    }

    public void setHevChargePlaneEndTimeYearR(int hevChargePlaneEndTimeYearR) {
        this.mHevChargePlaneEndTimeYearR = hevChargePlaneEndTimeYearR;
    }

    public int getChargePreheatAllowR() {
        return this.mChargePreheatAllowR;
    }

    public void setChargePreheatAllowR(int chargePreheatAllowR) {
        this.mChargePreheatAllowR = chargePreheatAllowR;
    }

    public int getWirelessChargingFrStateR() {
        return this.mWirelessChargingFrStateR;
    }

    public void setWirelessChargingFrStateR(int wirelessChargingFrStateR) {
        this.mWirelessChargingFrStateR = wirelessChargingFrStateR;
    }

    public int getWirelessChargingFrSwitchStateR() {
        return this.mWirelessChargingFrSwitchStateR;
    }

    public void setWirelessChargingFrSwitchStateR(int wirelessChargingFrSwitchStateR) {
        this.mWirelessChargingFrSwitchStateR = wirelessChargingFrSwitchStateR;
    }

    public int getWirelessChargingFrSwitchStateS() {
        return this.mWirelessChargingFrSwitchStateS;
    }

    public void setWirelessChargingFrSwitchStateS(int wirelessChargingFrSwitchStateS) {
        this.mWirelessChargingFrSwitchStateS = wirelessChargingFrSwitchStateS;
    }

    public int getWirelessChargingPowerFlStateR() {
        return this.mWirelessChargingPowerFlStateR;
    }

    public void setWirelessChargingPowerFlStateR(int wirelessChargingPowerFlStateR) {
        this.mWirelessChargingPowerFlStateR = wirelessChargingPowerFlStateR;
    }

    public int getWirelessChargingPowerFrStateR() {
        return this.mWirelessChargingPowerFrStateR;
    }

    public void setWirelessChargingPowerFrStateR(int wirelessChargingPowerFrStateR) {
        this.mWirelessChargingPowerFrStateR = wirelessChargingPowerFrStateR;
    }

    public int getVtovDischargeLowPowerHintR() {
        return this.mVtovDischargeLowPowerHintR;
    }

    public void setVtovDischargeLowPowerHintR(int vtovDischargeLowPowerHintR) {
        this.mVtovDischargeLowPowerHintR = vtovDischargeLowPowerHintR;
    }

    public int getBmsWakeNoSenseCharge10R() {
        return this.mBmsWakeNoSenseCharge10R;
    }

    public void setBmsWakeNoSenseCharge10R(int bmsWakeNoSenseCharge10R) {
        this.mBmsWakeNoSenseCharge10R = bmsWakeNoSenseCharge10R;
    }

    public int getBmsWakeNoSenseCharge11R() {
        return this.mBmsWakeNoSenseCharge11R;
    }

    public void setBmsWakeNoSenseCharge11R(int bmsWakeNoSenseCharge11R) {
        this.mBmsWakeNoSenseCharge11R = bmsWakeNoSenseCharge11R;
    }

    public int getBmsWakeNoSenseCharge12R() {
        return this.mBmsWakeNoSenseCharge12R;
    }

    public void setBmsWakeNoSenseCharge12R(int bmsWakeNoSenseCharge12R) {
        this.mBmsWakeNoSenseCharge12R = bmsWakeNoSenseCharge12R;
    }

    public int getBmsWakeNoSenseCharge13R() {
        return this.mBmsWakeNoSenseCharge13R;
    }

    public void setBmsWakeNoSenseCharge13R(int bmsWakeNoSenseCharge13R) {
        this.mBmsWakeNoSenseCharge13R = bmsWakeNoSenseCharge13R;
    }

    public int getBmsWakeNoSenseCharge14R() {
        return this.mBmsWakeNoSenseCharge14R;
    }

    public void setBmsWakeNoSenseCharge14R(int bmsWakeNoSenseCharge14R) {
        this.mBmsWakeNoSenseCharge14R = bmsWakeNoSenseCharge14R;
    }

    public int getBmsWakeNoSenseCharge15R() {
        return this.mBmsWakeNoSenseCharge15R;
    }

    public void setBmsWakeNoSenseCharge15R(int bmsWakeNoSenseCharge15R) {
        this.mBmsWakeNoSenseCharge15R = bmsWakeNoSenseCharge15R;
    }

    public int getBmsWakeNoSenseCharge16R() {
        return this.mBmsWakeNoSenseCharge16R;
    }

    public void setBmsWakeNoSenseCharge16R(int bmsWakeNoSenseCharge16R) {
        this.mBmsWakeNoSenseCharge16R = bmsWakeNoSenseCharge16R;
    }

    public int getBmsWakeNoSenseCharge17R() {
        return this.mBmsWakeNoSenseCharge17R;
    }

    public void setBmsWakeNoSenseCharge17R(int bmsWakeNoSenseCharge17R) {
        this.mBmsWakeNoSenseCharge17R = bmsWakeNoSenseCharge17R;
    }

    public int getBmsWakeNoSenseCharge18R() {
        return this.mBmsWakeNoSenseCharge18R;
    }

    public void setBmsWakeNoSenseCharge18R(int bmsWakeNoSenseCharge18R) {
        this.mBmsWakeNoSenseCharge18R = bmsWakeNoSenseCharge18R;
    }

    public int getBmsWakeNoSenseCharge19R() {
        return this.mBmsWakeNoSenseCharge19R;
    }

    public void setBmsWakeNoSenseCharge19R(int bmsWakeNoSenseCharge19R) {
        this.mBmsWakeNoSenseCharge19R = bmsWakeNoSenseCharge19R;
    }

    public int getBmsWakeNoSenseCharge1R() {
        return this.mBmsWakeNoSenseCharge1R;
    }

    public void setBmsWakeNoSenseCharge1R(int bmsWakeNoSenseCharge1R) {
        this.mBmsWakeNoSenseCharge1R = bmsWakeNoSenseCharge1R;
    }

    public int getBmsWakeNoSenseCharge20R() {
        return this.mBmsWakeNoSenseCharge20R;
    }

    public void setBmsWakeNoSenseCharge20R(int bmsWakeNoSenseCharge20R) {
        this.mBmsWakeNoSenseCharge20R = bmsWakeNoSenseCharge20R;
    }

    public int getBmsWakeNoSenseCharge21R() {
        return this.mBmsWakeNoSenseCharge21R;
    }

    public void setBmsWakeNoSenseCharge21R(int bmsWakeNoSenseCharge21R) {
        this.mBmsWakeNoSenseCharge21R = bmsWakeNoSenseCharge21R;
    }

    public int getBmsWakeNoSenseCharge22R() {
        return this.mBmsWakeNoSenseCharge22R;
    }

    public void setBmsWakeNoSenseCharge22R(int bmsWakeNoSenseCharge22R) {
        this.mBmsWakeNoSenseCharge22R = bmsWakeNoSenseCharge22R;
    }

    public int getBmsWakeNoSenseCharge23R() {
        return this.mBmsWakeNoSenseCharge23R;
    }

    public void setBmsWakeNoSenseCharge23R(int bmsWakeNoSenseCharge23R) {
        this.mBmsWakeNoSenseCharge23R = bmsWakeNoSenseCharge23R;
    }

    public int getBmsWakeNoSenseCharge24R() {
        return this.mBmsWakeNoSenseCharge24R;
    }

    public void setBmsWakeNoSenseCharge24R(int bmsWakeNoSenseCharge24R) {
        this.mBmsWakeNoSenseCharge24R = bmsWakeNoSenseCharge24R;
    }

    public int getBmsWakeNoSenseCharge25R() {
        return this.mBmsWakeNoSenseCharge25R;
    }

    public void setBmsWakeNoSenseCharge25R(int bmsWakeNoSenseCharge25R) {
        this.mBmsWakeNoSenseCharge25R = bmsWakeNoSenseCharge25R;
    }

    public int getBmsWakeNoSenseCharge26R() {
        return this.mBmsWakeNoSenseCharge26R;
    }

    public void setBmsWakeNoSenseCharge26R(int bmsWakeNoSenseCharge26R) {
        this.mBmsWakeNoSenseCharge26R = bmsWakeNoSenseCharge26R;
    }

    public int getBmsWakeNoSenseCharge27R() {
        return this.mBmsWakeNoSenseCharge27R;
    }

    public void setBmsWakeNoSenseCharge27R(int bmsWakeNoSenseCharge27R) {
        this.mBmsWakeNoSenseCharge27R = bmsWakeNoSenseCharge27R;
    }

    public int getBmsWakeNoSenseCharge28R() {
        return this.mBmsWakeNoSenseCharge28R;
    }

    public void setBmsWakeNoSenseCharge28R(int bmsWakeNoSenseCharge28R) {
        this.mBmsWakeNoSenseCharge28R = bmsWakeNoSenseCharge28R;
    }

    public int getBmsWakeNoSenseCharge29R() {
        return this.mBmsWakeNoSenseCharge29R;
    }

    public void setBmsWakeNoSenseCharge29R(int bmsWakeNoSenseCharge29R) {
        this.mBmsWakeNoSenseCharge29R = bmsWakeNoSenseCharge29R;
    }

    public int getBmsWakeNoSenseCharge2R() {
        return this.mBmsWakeNoSenseCharge2R;
    }

    public void setBmsWakeNoSenseCharge2R(int bmsWakeNoSenseCharge2R) {
        this.mBmsWakeNoSenseCharge2R = bmsWakeNoSenseCharge2R;
    }

    public int getBmsWakeNoSenseCharge30R() {
        return this.mBmsWakeNoSenseCharge30R;
    }

    public void setBmsWakeNoSenseCharge30R(int bmsWakeNoSenseCharge30R) {
        this.mBmsWakeNoSenseCharge30R = bmsWakeNoSenseCharge30R;
    }

    public int getBmsWakeNoSenseCharge31R() {
        return this.mBmsWakeNoSenseCharge31R;
    }

    public void setBmsWakeNoSenseCharge31R(int bmsWakeNoSenseCharge31R) {
        this.mBmsWakeNoSenseCharge31R = bmsWakeNoSenseCharge31R;
    }

    public int getBmsWakeNoSenseCharge32R() {
        return this.mBmsWakeNoSenseCharge32R;
    }

    public void setBmsWakeNoSenseCharge32R(int bmsWakeNoSenseCharge32R) {
        this.mBmsWakeNoSenseCharge32R = bmsWakeNoSenseCharge32R;
    }

    public int getBmsWakeNoSenseCharge33R() {
        return this.mBmsWakeNoSenseCharge33R;
    }

    public void setBmsWakeNoSenseCharge33R(int bmsWakeNoSenseCharge33R) {
        this.mBmsWakeNoSenseCharge33R = bmsWakeNoSenseCharge33R;
    }

    public int getBmsWakeNoSenseCharge34R() {
        return this.mBmsWakeNoSenseCharge34R;
    }

    public void setBmsWakeNoSenseCharge34R(int bmsWakeNoSenseCharge34R) {
        this.mBmsWakeNoSenseCharge34R = bmsWakeNoSenseCharge34R;
    }

    public int getBmsWakeNoSenseCharge35R() {
        return this.mBmsWakeNoSenseCharge35R;
    }

    public void setBmsWakeNoSenseCharge35R(int bmsWakeNoSenseCharge35R) {
        this.mBmsWakeNoSenseCharge35R = bmsWakeNoSenseCharge35R;
    }

    public int getBmsWakeNoSenseCharge3R() {
        return this.mBmsWakeNoSenseCharge3R;
    }

    public void setBmsWakeNoSenseCharge3R(int bmsWakeNoSenseCharge3R) {
        this.mBmsWakeNoSenseCharge3R = bmsWakeNoSenseCharge3R;
    }

    public int getBmsWakeNoSenseCharge4R() {
        return this.mBmsWakeNoSenseCharge4R;
    }

    public void setBmsWakeNoSenseCharge4R(int bmsWakeNoSenseCharge4R) {
        this.mBmsWakeNoSenseCharge4R = bmsWakeNoSenseCharge4R;
    }

    public int getBmsWakeNoSenseCharge5R() {
        return this.mBmsWakeNoSenseCharge5R;
    }

    public void setBmsWakeNoSenseCharge5R(int bmsWakeNoSenseCharge5R) {
        this.mBmsWakeNoSenseCharge5R = bmsWakeNoSenseCharge5R;
    }

    public int getBmsWakeNoSenseCharge6R() {
        return this.mBmsWakeNoSenseCharge6R;
    }

    public void setBmsWakeNoSenseCharge6R(int bmsWakeNoSenseCharge6R) {
        this.mBmsWakeNoSenseCharge6R = bmsWakeNoSenseCharge6R;
    }

    public int getBmsWakeNoSenseCharge7R() {
        return this.mBmsWakeNoSenseCharge7R;
    }

    public void setBmsWakeNoSenseCharge7R(int bmsWakeNoSenseCharge7R) {
        this.mBmsWakeNoSenseCharge7R = bmsWakeNoSenseCharge7R;
    }

    public int getBmsWakeNoSenseCharge8R() {
        return this.mBmsWakeNoSenseCharge8R;
    }

    public void setBmsWakeNoSenseCharge8R(int bmsWakeNoSenseCharge8R) {
        this.mBmsWakeNoSenseCharge8R = bmsWakeNoSenseCharge8R;
    }

    public int getBmsWakeNoSenseCharge9R() {
        return this.mBmsWakeNoSenseCharge9R;
    }

    public void setBmsWakeNoSenseCharge9R(int bmsWakeNoSenseCharge9R) {
        this.mBmsWakeNoSenseCharge9R = bmsWakeNoSenseCharge9R;
    }

    public int getCarPlanAccOnStateR() {
        return this.mCarPlanAccOnStateR;
    }

    public void setCarPlanAccOnStateR(int carPlanAccOnStateR) {
        this.mCarPlanAccOnStateR = carPlanAccOnStateR;
    }

    public int getCarPlanBatteryHeatStateR() {
        return this.mCarPlanBatteryHeatStateR;
    }

    public void setCarPlanBatteryHeatStateR(int carPlanBatteryHeatStateR) {
        this.mCarPlanBatteryHeatStateR = carPlanBatteryHeatStateR;
    }

    public int getCarPlanDayS() {
        return this.mCarPlanDayS;
    }

    public void setCarPlanDayS(int carPlanDayS) {
        this.mCarPlanDayS = carPlanDayS;
    }

    public int getCarPlanHourS() {
        return this.mCarPlanHourS;
    }

    public void setCarPlanHourS(int carPlanHourS) {
        this.mCarPlanHourS = carPlanHourS;
    }

    public int getCarPlanMinuteS() {
        return this.mCarPlanMinuteS;
    }

    public void setCarPlanMinuteS(int carPlanMinuteS) {
        this.mCarPlanMinuteS = carPlanMinuteS;
    }

    public int getCarPlanMonthS() {
        return this.mCarPlanMonthS;
    }

    public void setCarPlanMonthS(int carPlanMonthS) {
        this.mCarPlanMonthS = carPlanMonthS;
    }

    public int getCarPlanStateR() {
        return this.mCarPlanStateR;
    }

    public void setCarPlanStateR(int carPlanStateR) {
        this.mCarPlanStateR = carPlanStateR;
    }

    public int getCarPlanStateS() {
        return this.mCarPlanStateS;
    }

    public void setCarPlanStateS(int carPlanStateS) {
        this.mCarPlanStateS = carPlanStateS;
    }

    public int getCarPlanYearS() {
        return this.mCarPlanYearS;
    }

    public void setCarPlanYearS(int carPlanYearS) {
        this.mCarPlanYearS = carPlanYearS;
    }

    public int getChargePlanStateS() {
        return this.mChargePlanStateS;
    }

    public void setChargePlanStateS(int chargePlanStateS) {
        this.mChargePlanStateS = chargePlanStateS;
    }

    public int getChargePreheatStateR() {
        return this.mChargePreheatStateR;
    }

    public void setChargePreheatStateR(int chargePreheatStateR) {
        this.mChargePreheatStateR = chargePreheatStateR;
    }

    public int getChargePreheatStateS() {
        return this.mChargePreheatStateS;
    }

    public void setChargePreheatStateS(int chargePreheatStateS) {
        this.mChargePreheatStateS = chargePreheatStateS;
    }

    public int getElectricityPriceLevel10S() {
        return this.mElectricityPriceLevel10S;
    }

    public void setElectricityPriceLevel10S(int electricityPriceLevel10S) {
        this.mElectricityPriceLevel10S = electricityPriceLevel10S;
    }

    public int getElectricityPriceLevel11S() {
        return this.mElectricityPriceLevel11S;
    }

    public void setElectricityPriceLevel11S(int electricityPriceLevel11S) {
        this.mElectricityPriceLevel11S = electricityPriceLevel11S;
    }

    public int getElectricityPriceLevel12S() {
        return this.mElectricityPriceLevel12S;
    }

    public void setElectricityPriceLevel12S(int electricityPriceLevel12S) {
        this.mElectricityPriceLevel12S = electricityPriceLevel12S;
    }

    public int getElectricityPriceLevel13S() {
        return this.mElectricityPriceLevel13S;
    }

    public void setElectricityPriceLevel13S(int electricityPriceLevel13S) {
        this.mElectricityPriceLevel13S = electricityPriceLevel13S;
    }

    public int getElectricityPriceLevel14S() {
        return this.mElectricityPriceLevel14S;
    }

    public void setElectricityPriceLevel14S(int electricityPriceLevel14S) {
        this.mElectricityPriceLevel14S = electricityPriceLevel14S;
    }

    public int getElectricityPriceLevel15S() {
        return this.mElectricityPriceLevel15S;
    }

    public void setElectricityPriceLevel15S(int electricityPriceLevel15S) {
        this.mElectricityPriceLevel15S = electricityPriceLevel15S;
    }

    public int getElectricityPriceLevel16S() {
        return this.mElectricityPriceLevel16S;
    }

    public void setElectricityPriceLevel16S(int electricityPriceLevel16S) {
        this.mElectricityPriceLevel16S = electricityPriceLevel16S;
    }

    public int getElectricityPriceLevel17S() {
        return this.mElectricityPriceLevel17S;
    }

    public void setElectricityPriceLevel17S(int electricityPriceLevel17S) {
        this.mElectricityPriceLevel17S = electricityPriceLevel17S;
    }

    public int getElectricityPriceLevel18S() {
        return this.mElectricityPriceLevel18S;
    }

    public void setElectricityPriceLevel18S(int electricityPriceLevel18S) {
        this.mElectricityPriceLevel18S = electricityPriceLevel18S;
    }

    public int getElectricityPriceLevel19S() {
        return this.mElectricityPriceLevel19S;
    }

    public void setElectricityPriceLevel19S(int electricityPriceLevel19S) {
        this.mElectricityPriceLevel19S = electricityPriceLevel19S;
    }

    public int getElectricityPriceLevel1S() {
        return this.mElectricityPriceLevel1S;
    }

    public void setElectricityPriceLevel1S(int electricityPriceLevel1S) {
        this.mElectricityPriceLevel1S = electricityPriceLevel1S;
    }

    public int getElectricityPriceLevel20S() {
        return this.mElectricityPriceLevel20S;
    }

    public void setElectricityPriceLevel20S(int electricityPriceLevel20S) {
        this.mElectricityPriceLevel20S = electricityPriceLevel20S;
    }

    public int getElectricityPriceLevel21S() {
        return this.mElectricityPriceLevel21S;
    }

    public void setElectricityPriceLevel21S(int electricityPriceLevel21S) {
        this.mElectricityPriceLevel21S = electricityPriceLevel21S;
    }

    public int getElectricityPriceLevel22S() {
        return this.mElectricityPriceLevel22S;
    }

    public void setElectricityPriceLevel22S(int electricityPriceLevel22S) {
        this.mElectricityPriceLevel22S = electricityPriceLevel22S;
    }

    public int getElectricityPriceLevel23S() {
        return this.mElectricityPriceLevel23S;
    }

    public void setElectricityPriceLevel23S(int electricityPriceLevel23S) {
        this.mElectricityPriceLevel23S = electricityPriceLevel23S;
    }

    public int getElectricityPriceLevel24S() {
        return this.mElectricityPriceLevel24S;
    }

    public void setElectricityPriceLevel24S(int electricityPriceLevel24S) {
        this.mElectricityPriceLevel24S = electricityPriceLevel24S;
    }

    public int getElectricityPriceLevel25S() {
        return this.mElectricityPriceLevel25S;
    }

    public void setElectricityPriceLevel25S(int electricityPriceLevel25S) {
        this.mElectricityPriceLevel25S = electricityPriceLevel25S;
    }

    public int getElectricityPriceLevel26S() {
        return this.mElectricityPriceLevel26S;
    }

    public void setElectricityPriceLevel26S(int electricityPriceLevel26S) {
        this.mElectricityPriceLevel26S = electricityPriceLevel26S;
    }

    public int getElectricityPriceLevel27S() {
        return this.mElectricityPriceLevel27S;
    }

    public void setElectricityPriceLevel27S(int electricityPriceLevel27S) {
        this.mElectricityPriceLevel27S = electricityPriceLevel27S;
    }

    public int getElectricityPriceLevel28S() {
        return this.mElectricityPriceLevel28S;
    }

    public void setElectricityPriceLevel28S(int electricityPriceLevel28S) {
        this.mElectricityPriceLevel28S = electricityPriceLevel28S;
    }

    public int getElectricityPriceLevel29S() {
        return this.mElectricityPriceLevel29S;
    }

    public void setElectricityPriceLevel29S(int electricityPriceLevel29S) {
        this.mElectricityPriceLevel29S = electricityPriceLevel29S;
    }

    public int getElectricityPriceLevel2S() {
        return this.mElectricityPriceLevel2S;
    }

    public void setElectricityPriceLevel2S(int electricityPriceLevel2S) {
        this.mElectricityPriceLevel2S = electricityPriceLevel2S;
    }

    public int getElectricityPriceLevel30S() {
        return this.mElectricityPriceLevel30S;
    }

    public void setElectricityPriceLevel30S(int electricityPriceLevel30S) {
        this.mElectricityPriceLevel30S = electricityPriceLevel30S;
    }

    public int getElectricityPriceLevel31S() {
        return this.mElectricityPriceLevel31S;
    }

    public void setElectricityPriceLevel31S(int electricityPriceLevel31S) {
        this.mElectricityPriceLevel31S = electricityPriceLevel31S;
    }

    public int getElectricityPriceLevel32S() {
        return this.mElectricityPriceLevel32S;
    }

    public void setElectricityPriceLevel32S(int electricityPriceLevel32S) {
        this.mElectricityPriceLevel32S = electricityPriceLevel32S;
    }

    public int getElectricityPriceLevel33S() {
        return this.mElectricityPriceLevel33S;
    }

    public void setElectricityPriceLevel33S(int electricityPriceLevel33S) {
        this.mElectricityPriceLevel33S = electricityPriceLevel33S;
    }

    public int getElectricityPriceLevel34S() {
        return this.mElectricityPriceLevel34S;
    }

    public void setElectricityPriceLevel34S(int electricityPriceLevel34S) {
        this.mElectricityPriceLevel34S = electricityPriceLevel34S;
    }

    public int getElectricityPriceLevel35S() {
        return this.mElectricityPriceLevel35S;
    }

    public void setElectricityPriceLevel35S(int electricityPriceLevel35S) {
        this.mElectricityPriceLevel35S = electricityPriceLevel35S;
    }

    public int getElectricityPriceLevel36S() {
        return this.mElectricityPriceLevel36S;
    }

    public void setElectricityPriceLevel36S(int electricityPriceLevel36S) {
        this.mElectricityPriceLevel36S = electricityPriceLevel36S;
    }

    public int getElectricityPriceLevel37S() {
        return this.mElectricityPriceLevel37S;
    }

    public void setElectricityPriceLevel37S(int electricityPriceLevel37S) {
        this.mElectricityPriceLevel37S = electricityPriceLevel37S;
    }

    public int getElectricityPriceLevel38S() {
        return this.mElectricityPriceLevel38S;
    }

    public void setElectricityPriceLevel38S(int electricityPriceLevel38S) {
        this.mElectricityPriceLevel38S = electricityPriceLevel38S;
    }

    public int getElectricityPriceLevel39S() {
        return this.mElectricityPriceLevel39S;
    }

    public void setElectricityPriceLevel39S(int electricityPriceLevel39S) {
        this.mElectricityPriceLevel39S = electricityPriceLevel39S;
    }

    public int getElectricityPriceLevel3S() {
        return this.mElectricityPriceLevel3S;
    }

    public void setElectricityPriceLevel3S(int electricityPriceLevel3S) {
        this.mElectricityPriceLevel3S = electricityPriceLevel3S;
    }

    public int getElectricityPriceLevel40S() {
        return this.mElectricityPriceLevel40S;
    }

    public void setElectricityPriceLevel40S(int electricityPriceLevel40S) {
        this.mElectricityPriceLevel40S = electricityPriceLevel40S;
    }

    public int getElectricityPriceLevel41S() {
        return this.mElectricityPriceLevel41S;
    }

    public void setElectricityPriceLevel41S(int electricityPriceLevel41S) {
        this.mElectricityPriceLevel41S = electricityPriceLevel41S;
    }

    public int getElectricityPriceLevel42S() {
        return this.mElectricityPriceLevel42S;
    }

    public void setElectricityPriceLevel42S(int electricityPriceLevel42S) {
        this.mElectricityPriceLevel42S = electricityPriceLevel42S;
    }

    public int getElectricityPriceLevel43S() {
        return this.mElectricityPriceLevel43S;
    }

    public void setElectricityPriceLevel43S(int electricityPriceLevel43S) {
        this.mElectricityPriceLevel43S = electricityPriceLevel43S;
    }

    public int getElectricityPriceLevel44S() {
        return this.mElectricityPriceLevel44S;
    }

    public void setElectricityPriceLevel44S(int electricityPriceLevel44S) {
        this.mElectricityPriceLevel44S = electricityPriceLevel44S;
    }

    public int getElectricityPriceLevel45S() {
        return this.mElectricityPriceLevel45S;
    }

    public void setElectricityPriceLevel45S(int electricityPriceLevel45S) {
        this.mElectricityPriceLevel45S = electricityPriceLevel45S;
    }

    public int getElectricityPriceLevel46S() {
        return this.mElectricityPriceLevel46S;
    }

    public void setElectricityPriceLevel46S(int electricityPriceLevel46S) {
        this.mElectricityPriceLevel46S = electricityPriceLevel46S;
    }

    public int getElectricityPriceLevel47S() {
        return this.mElectricityPriceLevel47S;
    }

    public void setElectricityPriceLevel47S(int electricityPriceLevel47S) {
        this.mElectricityPriceLevel47S = electricityPriceLevel47S;
    }

    public int getElectricityPriceLevel48S() {
        return this.mElectricityPriceLevel48S;
    }

    public void setElectricityPriceLevel48S(int electricityPriceLevel48S) {
        this.mElectricityPriceLevel48S = electricityPriceLevel48S;
    }

    public int getElectricityPriceLevel4S() {
        return this.mElectricityPriceLevel4S;
    }

    public void setElectricityPriceLevel4S(int electricityPriceLevel4S) {
        this.mElectricityPriceLevel4S = electricityPriceLevel4S;
    }

    public int getElectricityPriceLevel5S() {
        return this.mElectricityPriceLevel5S;
    }

    public void setElectricityPriceLevel5S(int electricityPriceLevel5S) {
        this.mElectricityPriceLevel5S = electricityPriceLevel5S;
    }

    public int getElectricityPriceLevel6S() {
        return this.mElectricityPriceLevel6S;
    }

    public void setElectricityPriceLevel6S(int electricityPriceLevel6S) {
        this.mElectricityPriceLevel6S = electricityPriceLevel6S;
    }

    public int getElectricityPriceLevel7S() {
        return this.mElectricityPriceLevel7S;
    }

    public void setElectricityPriceLevel7S(int electricityPriceLevel7S) {
        this.mElectricityPriceLevel7S = electricityPriceLevel7S;
    }

    public int getElectricityPriceLevel8S() {
        return this.mElectricityPriceLevel8S;
    }

    public void setElectricityPriceLevel8S(int electricityPriceLevel8S) {
        this.mElectricityPriceLevel8S = electricityPriceLevel8S;
    }

    public int getElectricityPriceLevel9S() {
        return this.mElectricityPriceLevel9S;
    }

    public void setElectricityPriceLevel9S(int electricityPriceLevel9S) {
        this.mElectricityPriceLevel9S = electricityPriceLevel9S;
    }

    public int getEvChargePlanEndTimeDayS() {
        return this.mEvChargePlanEndTimeDayS;
    }

    public void setEvChargePlanEndTimeDayS(int evChargePlanEndTimeDayS) {
        this.mEvChargePlanEndTimeDayS = evChargePlanEndTimeDayS;
    }

    public int getEvChargePlanEndTimeHourS() {
        return this.mEvChargePlanEndTimeHourS;
    }

    public void setEvChargePlanEndTimeHourS(int evChargePlanEndTimeHourS) {
        this.mEvChargePlanEndTimeHourS = evChargePlanEndTimeHourS;
    }

    public int getEvChargePlanEndTimeMinuteS() {
        return this.mEvChargePlanEndTimeMinuteS;
    }

    public void setEvChargePlanEndTimeMinuteS(int evChargePlanEndTimeMinuteS) {
        this.mEvChargePlanEndTimeMinuteS = evChargePlanEndTimeMinuteS;
    }

    public int getEvChargePlanEndTimeMonthS() {
        return this.mEvChargePlanEndTimeMonthS;
    }

    public void setEvChargePlanEndTimeMonthS(int evChargePlanEndTimeMonthS) {
        this.mEvChargePlanEndTimeMonthS = evChargePlanEndTimeMonthS;
    }

    public int getEvChargePlanEndTimeYearS() {
        return this.mEvChargePlanEndTimeYearS;
    }

    public void setEvChargePlanEndTimeYearS(int evChargePlanEndTimeYearS) {
        this.mEvChargePlanEndTimeYearS = evChargePlanEndTimeYearS;
    }

    public int getEvChargePlanStartTimeDayS() {
        return this.mEvChargePlanStartTimeDayS;
    }

    public void setEvChargePlanStartTimeDayS(int evChargePlanStartTimeDayS) {
        this.mEvChargePlanStartTimeDayS = evChargePlanStartTimeDayS;
    }

    public int getEvChargePlanStartTimeHourS() {
        return this.mEvChargePlanStartTimeHourS;
    }

    public void setEvChargePlanStartTimeHourS(int evChargePlanStartTimeHourS) {
        this.mEvChargePlanStartTimeHourS = evChargePlanStartTimeHourS;
    }

    public int getEvChargePlanStartTimeMinuteS() {
        return this.mEvChargePlanStartTimeMinuteS;
    }

    public void setEvChargePlanStartTimeMinuteS(int evChargePlanStartTimeMinuteS) {
        this.mEvChargePlanStartTimeMinuteS = evChargePlanStartTimeMinuteS;
    }

    public int getEvChargePlanStartTimeMonthS() {
        return this.mEvChargePlanStartTimeMonthS;
    }

    public void setEvChargePlanStartTimeMonthS(int evChargePlanStartTimeMonthS) {
        this.mEvChargePlanStartTimeMonthS = evChargePlanStartTimeMonthS;
    }

    public int getEvChargePlanStartTimeYearS() {
        return this.mEvChargePlanStartTimeYearS;
    }

    public void setEvChargePlanStartTimeYearS(int evChargePlanStartTimeYearS) {
        this.mEvChargePlanStartTimeYearS = evChargePlanStartTimeYearS;
    }

    public int getHevChargePlanEndTimeDayS() {
        return this.mHevChargePlanEndTimeDayS;
    }

    public void setHevChargePlanEndTimeDayS(int hevChargePlanEndTimeDayS) {
        this.mHevChargePlanEndTimeDayS = hevChargePlanEndTimeDayS;
    }

    public int getHevChargePlanEndTimeHourS() {
        return this.mHevChargePlanEndTimeHourS;
    }

    public void setHevChargePlanEndTimeHourS(int hevChargePlanEndTimeHourS) {
        this.mHevChargePlanEndTimeHourS = hevChargePlanEndTimeHourS;
    }

    public int getHevChargePlanEndTimeMinuteS() {
        return this.mHevChargePlanEndTimeMinuteS;
    }

    public void setHevChargePlanEndTimeMinuteS(int hevChargePlanEndTimeMinuteS) {
        this.mHevChargePlanEndTimeMinuteS = hevChargePlanEndTimeMinuteS;
    }

    public int getHevChargePlanEndTimeMonthS() {
        return this.mHevChargePlanEndTimeMonthS;
    }

    public void setHevChargePlanEndTimeMonthS(int hevChargePlanEndTimeMonthS) {
        this.mHevChargePlanEndTimeMonthS = hevChargePlanEndTimeMonthS;
    }

    public int getHevChargePlanEndTimeYearS() {
        return this.mHevChargePlanEndTimeYearS;
    }

    public void setHevChargePlanEndTimeYearS(int hevChargePlanEndTimeYearS) {
        this.mHevChargePlanEndTimeYearS = hevChargePlanEndTimeYearS;
    }

    public int getHevChargePlanStartTimeDayS() {
        return this.mHevChargePlanStartTimeDayS;
    }

    public void setHevChargePlanStartTimeDayS(int hevChargePlanStartTimeDayS) {
        this.mHevChargePlanStartTimeDayS = hevChargePlanStartTimeDayS;
    }

    public int getHevChargePlanStartTimeHourS() {
        return this.mHevChargePlanStartTimeHourS;
    }

    public void setHevChargePlanStartTimeHourS(int hevChargePlanStartTimeHourS) {
        this.mHevChargePlanStartTimeHourS = hevChargePlanStartTimeHourS;
    }

    public int getHevChargePlanStartTimeMinuteS() {
        return this.mHevChargePlanStartTimeMinuteS;
    }

    public void setHevChargePlanStartTimeMinuteS(int hevChargePlanStartTimeMinuteS) {
        this.mHevChargePlanStartTimeMinuteS = hevChargePlanStartTimeMinuteS;
    }

    public int getHevChargePlanStartTimeMonthS() {
        return this.mHevChargePlanStartTimeMonthS;
    }

    public void setHevChargePlanStartTimeMonthS(int hevChargePlanStartTimeMonthS) {
        this.mHevChargePlanStartTimeMonthS = hevChargePlanStartTimeMonthS;
    }

    public int getHevChargePlanStartTimeYearS() {
        return this.mHevChargePlanStartTimeYearS;
    }

    public void setHevChargePlanStartTimeYearS(int hevChargePlanStartTimeYearS) {
        this.mHevChargePlanStartTimeYearS = hevChargePlanStartTimeYearS;
    }

    public int getNoSenseChargeStateR() {
        return this.mNoSenseChargeStateR;
    }

    public void setNoSenseChargeStateR(int noSenseChargeStateR) {
        this.mNoSenseChargeStateR = noSenseChargeStateR;
    }

    public int getNoSenseChargeStateS() {
        return this.mNoSenseChargeStateS;
    }

    public void setNoSenseChargeStateS(int noSenseChargeStateS) {
        this.mNoSenseChargeStateS = noSenseChargeStateS;
    }

    public int getObcWakeNoSenseCharge10R() {
        return this.mObcWakeNoSenseCharge10R;
    }

    public void setObcWakeNoSenseCharge10R(int obcWakeNoSenseCharge10R) {
        this.mObcWakeNoSenseCharge10R = obcWakeNoSenseCharge10R;
    }

    public int getObcWakeNoSenseCharge11R() {
        return this.mObcWakeNoSenseCharge11R;
    }

    public void setObcWakeNoSenseCharge11R(int obcWakeNoSenseCharge11R) {
        this.mObcWakeNoSenseCharge11R = obcWakeNoSenseCharge11R;
    }

    public int getObcWakeNoSenseCharge12R() {
        return this.mObcWakeNoSenseCharge12R;
    }

    public void setObcWakeNoSenseCharge12R(int obcWakeNoSenseCharge12R) {
        this.mObcWakeNoSenseCharge12R = obcWakeNoSenseCharge12R;
    }

    public int getObcWakeNoSenseCharge13R() {
        return this.mObcWakeNoSenseCharge13R;
    }

    public void setObcWakeNoSenseCharge13R(int obcWakeNoSenseCharge13R) {
        this.mObcWakeNoSenseCharge13R = obcWakeNoSenseCharge13R;
    }

    public int getObcWakeNoSenseCharge14R() {
        return this.mObcWakeNoSenseCharge14R;
    }

    public void setObcWakeNoSenseCharge14R(int obcWakeNoSenseCharge14R) {
        this.mObcWakeNoSenseCharge14R = obcWakeNoSenseCharge14R;
    }

    public int getObcWakeNoSenseCharge15R() {
        return this.mObcWakeNoSenseCharge15R;
    }

    public void setObcWakeNoSenseCharge15R(int obcWakeNoSenseCharge15R) {
        this.mObcWakeNoSenseCharge15R = obcWakeNoSenseCharge15R;
    }

    public int getObcWakeNoSenseCharge16R() {
        return this.mObcWakeNoSenseCharge16R;
    }

    public void setObcWakeNoSenseCharge16R(int obcWakeNoSenseCharge16R) {
        this.mObcWakeNoSenseCharge16R = obcWakeNoSenseCharge16R;
    }

    public int getObcWakeNoSenseCharge17R() {
        return this.mObcWakeNoSenseCharge17R;
    }

    public void setObcWakeNoSenseCharge17R(int obcWakeNoSenseCharge17R) {
        this.mObcWakeNoSenseCharge17R = obcWakeNoSenseCharge17R;
    }

    public int getObcWakeNoSenseCharge18R() {
        return this.mObcWakeNoSenseCharge18R;
    }

    public void setObcWakeNoSenseCharge18R(int obcWakeNoSenseCharge18R) {
        this.mObcWakeNoSenseCharge18R = obcWakeNoSenseCharge18R;
    }

    public int getObcWakeNoSenseCharge19R() {
        return this.mObcWakeNoSenseCharge19R;
    }

    public void setObcWakeNoSenseCharge19R(int obcWakeNoSenseCharge19R) {
        this.mObcWakeNoSenseCharge19R = obcWakeNoSenseCharge19R;
    }

    public int getObcWakeNoSenseCharge1R() {
        return this.mObcWakeNoSenseCharge1R;
    }

    public void setObcWakeNoSenseCharge1R(int obcWakeNoSenseCharge1R) {
        this.mObcWakeNoSenseCharge1R = obcWakeNoSenseCharge1R;
    }

    public int getObcWakeNoSenseCharge20R() {
        return this.mObcWakeNoSenseCharge20R;
    }

    public void setObcWakeNoSenseCharge20R(int obcWakeNoSenseCharge20R) {
        this.mObcWakeNoSenseCharge20R = obcWakeNoSenseCharge20R;
    }

    public int getObcWakeNoSenseCharge21R() {
        return this.mObcWakeNoSenseCharge21R;
    }

    public void setObcWakeNoSenseCharge21R(int obcWakeNoSenseCharge21R) {
        this.mObcWakeNoSenseCharge21R = obcWakeNoSenseCharge21R;
    }

    public int getObcWakeNoSenseCharge22R() {
        return this.mObcWakeNoSenseCharge22R;
    }

    public void setObcWakeNoSenseCharge22R(int obcWakeNoSenseCharge22R) {
        this.mObcWakeNoSenseCharge22R = obcWakeNoSenseCharge22R;
    }

    public int getObcWakeNoSenseCharge23R() {
        return this.mObcWakeNoSenseCharge23R;
    }

    public void setObcWakeNoSenseCharge23R(int obcWakeNoSenseCharge23R) {
        this.mObcWakeNoSenseCharge23R = obcWakeNoSenseCharge23R;
    }

    public int getObcWakeNoSenseCharge24R() {
        return this.mObcWakeNoSenseCharge24R;
    }

    public void setObcWakeNoSenseCharge24R(int obcWakeNoSenseCharge24R) {
        this.mObcWakeNoSenseCharge24R = obcWakeNoSenseCharge24R;
    }

    public int getObcWakeNoSenseCharge25R() {
        return this.mObcWakeNoSenseCharge25R;
    }

    public void setObcWakeNoSenseCharge25R(int obcWakeNoSenseCharge25R) {
        this.mObcWakeNoSenseCharge25R = obcWakeNoSenseCharge25R;
    }

    public int getObcWakeNoSenseCharge26R() {
        return this.mObcWakeNoSenseCharge26R;
    }

    public void setObcWakeNoSenseCharge26R(int obcWakeNoSenseCharge26R) {
        this.mObcWakeNoSenseCharge26R = obcWakeNoSenseCharge26R;
    }

    public int getObcWakeNoSenseCharge27R() {
        return this.mObcWakeNoSenseCharge27R;
    }

    public void setObcWakeNoSenseCharge27R(int obcWakeNoSenseCharge27R) {
        this.mObcWakeNoSenseCharge27R = obcWakeNoSenseCharge27R;
    }

    public int getObcWakeNoSenseCharge28R() {
        return this.mObcWakeNoSenseCharge28R;
    }

    public void setObcWakeNoSenseCharge28R(int obcWakeNoSenseCharge28R) {
        this.mObcWakeNoSenseCharge28R = obcWakeNoSenseCharge28R;
    }

    public int getObcWakeNoSenseCharge29R() {
        return this.mObcWakeNoSenseCharge29R;
    }

    public void setObcWakeNoSenseCharge29R(int obcWakeNoSenseCharge29R) {
        this.mObcWakeNoSenseCharge29R = obcWakeNoSenseCharge29R;
    }

    public int getObcWakeNoSenseCharge2R() {
        return this.mObcWakeNoSenseCharge2R;
    }

    public void setObcWakeNoSenseCharge2R(int obcWakeNoSenseCharge2R) {
        this.mObcWakeNoSenseCharge2R = obcWakeNoSenseCharge2R;
    }

    public int getObcWakeNoSenseCharge30R() {
        return this.mObcWakeNoSenseCharge30R;
    }

    public void setObcWakeNoSenseCharge30R(int obcWakeNoSenseCharge30R) {
        this.mObcWakeNoSenseCharge30R = obcWakeNoSenseCharge30R;
    }

    public int getObcWakeNoSenseCharge31R() {
        return this.mObcWakeNoSenseCharge31R;
    }

    public void setObcWakeNoSenseCharge31R(int obcWakeNoSenseCharge31R) {
        this.mObcWakeNoSenseCharge31R = obcWakeNoSenseCharge31R;
    }

    public int getObcWakeNoSenseCharge32R() {
        return this.mObcWakeNoSenseCharge32R;
    }

    public void setObcWakeNoSenseCharge32R(int obcWakeNoSenseCharge32R) {
        this.mObcWakeNoSenseCharge32R = obcWakeNoSenseCharge32R;
    }

    public int getObcWakeNoSenseCharge33R() {
        return this.mObcWakeNoSenseCharge33R;
    }

    public void setObcWakeNoSenseCharge33R(int obcWakeNoSenseCharge33R) {
        this.mObcWakeNoSenseCharge33R = obcWakeNoSenseCharge33R;
    }

    public int getObcWakeNoSenseCharge34R() {
        return this.mObcWakeNoSenseCharge34R;
    }

    public void setObcWakeNoSenseCharge34R(int obcWakeNoSenseCharge34R) {
        this.mObcWakeNoSenseCharge34R = obcWakeNoSenseCharge34R;
    }

    public int getObcWakeNoSenseCharge35R() {
        return this.mObcWakeNoSenseCharge35R;
    }

    public void setObcWakeNoSenseCharge35R(int obcWakeNoSenseCharge35R) {
        this.mObcWakeNoSenseCharge35R = obcWakeNoSenseCharge35R;
    }

    public int getObcWakeNoSenseCharge3R() {
        return this.mObcWakeNoSenseCharge3R;
    }

    public void setObcWakeNoSenseCharge3R(int obcWakeNoSenseCharge3R) {
        this.mObcWakeNoSenseCharge3R = obcWakeNoSenseCharge3R;
    }

    public int getObcWakeNoSenseCharge4R() {
        return this.mObcWakeNoSenseCharge4R;
    }

    public void setObcWakeNoSenseCharge4R(int obcWakeNoSenseCharge4R) {
        this.mObcWakeNoSenseCharge4R = obcWakeNoSenseCharge4R;
    }

    public int getObcWakeNoSenseCharge5R() {
        return this.mObcWakeNoSenseCharge5R;
    }

    public void setObcWakeNoSenseCharge5R(int obcWakeNoSenseCharge5R) {
        this.mObcWakeNoSenseCharge5R = obcWakeNoSenseCharge5R;
    }

    public int getObcWakeNoSenseCharge6R() {
        return this.mObcWakeNoSenseCharge6R;
    }

    public void setObcWakeNoSenseCharge6R(int obcWakeNoSenseCharge6R) {
        this.mObcWakeNoSenseCharge6R = obcWakeNoSenseCharge6R;
    }

    public int getObcWakeNoSenseCharge7R() {
        return this.mObcWakeNoSenseCharge7R;
    }

    public void setObcWakeNoSenseCharge7R(int obcWakeNoSenseCharge7R) {
        this.mObcWakeNoSenseCharge7R = obcWakeNoSenseCharge7R;
    }

    public int getObcWakeNoSenseCharge8R() {
        return this.mObcWakeNoSenseCharge8R;
    }

    public void setObcWakeNoSenseCharge8R(int obcWakeNoSenseCharge8R) {
        this.mObcWakeNoSenseCharge8R = obcWakeNoSenseCharge8R;
    }

    public int getObcWakeNoSenseCharge9R() {
        return this.mObcWakeNoSenseCharge9R;
    }

    public void setObcWakeNoSenseCharge9R(int obcWakeNoSenseCharge9R) {
        this.mObcWakeNoSenseCharge9R = obcWakeNoSenseCharge9R;
    }

    public int getSituPowerGenerationStateR() {
        return this.mSituPowerGenerationStateR;
    }

    public void setSituPowerGenerationStateR(int situPowerGenerationStateR) {
        this.mSituPowerGenerationStateR = situPowerGenerationStateR;
    }

    public int getSituPowerGenerationStateS() {
        return this.mSituPowerGenerationStateS;
    }

    public void setSituPowerGenerationStateS(int situPowerGenerationStateS) {
        this.mSituPowerGenerationStateS = situPowerGenerationStateS;
    }

    public int getSituPowerGenerationSupportR() {
        return this.mSituPowerGenerationSupportR;
    }

    public void setSituPowerGenerationSupportR(int situPowerGenerationSupportR) {
        this.mSituPowerGenerationSupportR = situPowerGenerationSupportR;
    }

    public int getSyncTimeDayS() {
        return this.mSyncTimeDayS;
    }

    public void setSyncTimeDayS(int syncTimeDayS) {
        this.mSyncTimeDayS = syncTimeDayS;
    }

    public int getSyncTimeHourS() {
        return this.mSyncTimeHourS;
    }

    public void setSyncTimeHourS(int syncTimeHourS) {
        this.mSyncTimeHourS = syncTimeHourS;
    }

    public int getSyncTimeMinuteS() {
        return this.mSyncTimeMinuteS;
    }

    public void setSyncTimeMinuteS(int syncTimeMinuteS) {
        this.mSyncTimeMinuteS = syncTimeMinuteS;
    }

    public int getSyncTimeMonthS() {
        return this.mSyncTimeMonthS;
    }

    public void setSyncTimeMonthS(int syncTimeMonthS) {
        this.mSyncTimeMonthS = syncTimeMonthS;
    }

    public int getSyncTimeYearS() {
        return this.mSyncTimeYearS;
    }

    public void setSyncTimeYearS(int syncTimeYearS) {
        this.mSyncTimeYearS = syncTimeYearS;
    }

    public int getChargerWorkStateFixR() {
        return this.mChargerWorkStateFixR;
    }

    public void setChargerWorkStateFixR(int chargerWorkStateFixR) {
        this.mChargerWorkStateFixR = chargerWorkStateFixR;
    }

    public int getChargingRestTimeHourR() {
        return this.mChargingRestTimeHourR;
    }

    public void setChargingRestTimeHourR(int chargingRestTimeHourR) {
        this.mChargingRestTimeHourR = chargingRestTimeHourR;
    }

    public int getChargingRestTimeMinuteR() {
        return this.mChargingRestTimeMinuteR;
    }

    public void setChargingRestTimeMinuteR(int chargingRestTimeMinuteR) {
        this.mChargingRestTimeMinuteR = chargingRestTimeMinuteR;
    }

    public int getChargeStopCapacityStateR() {
        return this.mChargeStopCapacityStateR;
    }

    public void setChargeStopCapacityStateR(int chargeStopCapacityStateR) {
        this.mChargeStopCapacityStateR = chargeStopCapacityStateR;
    }

    public int getChargeStopCapacityStateS() {
        return this.mChargeStopCapacityStateS;
    }

    public void setChargeStopCapacityStateS(int chargeStopCapacityStateS) {
        this.mChargeStopCapacityStateS = chargeStopCapacityStateS;
    }

    public int getChargeTempCtlStateR() {
        return this.mChargeTempCtlStateR;
    }

    public void setChargeTempCtlStateR(int chargeTempCtlStateR) {
        this.mChargeTempCtlStateR = chargeTempCtlStateR;
    }

    public int getChargeTempCtlStateS() {
        return this.mChargeTempCtlStateS;
    }

    public void setChargeTempCtlStateS(int chargeTempCtlStateS) {
        this.mChargeTempCtlStateS = chargeTempCtlStateS;
    }

    public int getChargeToaggleGuardMixR() {
        return this.mChargeToaggleGuardMixR;
    }

    public void setChargeToaggleGuardMixR(int chargeToaggleGuardMixR) {
        this.mChargeToaggleGuardMixR = chargeToaggleGuardMixR;
    }

    public int getChargingCapStateLeftR() {
        return this.mChargingCapStateLeftR;
    }

    public void setChargingCapStateLeftR(int chargingCapStateLeftR) {
        this.mChargingCapStateLeftR = chargingCapStateLeftR;
    }

    public int getChargingCapStateRightR() {
        return this.mChargingCapStateRightR;
    }

    public void setChargingCapStateRightR(int chargingCapStateRightR) {
        this.mChargingCapStateRightR = chargingCapStateRightR;
    }

    public int getHevChargingType() {
        return this.mHevChargingType;
    }

    public void setHevChargingType(int hevChargingType) {
        this.mHevChargingType = hevChargingType;
    }

    public int getVtovDischargeConnectState() {
        return this.mVtovDischargeConnectState;
    }

    public void setVtovDischargeConnectState(int vtovDischargeConnectState) {
        this.mVtovDischargeConnectState = vtovDischargeConnectState;
    }

    public int getVtovDischargeLimitValR() {
        return this.mVtovDischargeLimitValR;
    }

    public void setVtovDischargeLimitValR(int vtovDischargeLimitValR) {
        this.mVtovDischargeLimitValR = vtovDischargeLimitValR;
    }

    public int getFullChgRemanTimHR() {
        return this.mFullChgRemanTimHR;
    }

    public void setFullChgRemanTimHR(int fullChgRemanTimHR) {
        this.mFullChgRemanTimHR = fullChgRemanTimHR;
    }

    public int getFullChgRemanTimMR() {
        return this.mFullChgRemanTimMR;
    }

    public void setFullChgRemanTimMR(int fullChgRemanTimMR) {
        this.mFullChgRemanTimMR = fullChgRemanTimMR;
    }

    public int getBatteryManagementDeviceStateR() {
        return this.mBatteryManagementDeviceStateR;
    }

    public void setBatteryManagementDeviceStateR(int batteryManagementDeviceStateR) {
        this.mBatteryManagementDeviceStateR = batteryManagementDeviceStateR;
    }

    public int getChargeToggleGuardR() {
        return this.mChargeToggleGuardR;
    }

    public void setChargeToggleGuardR(int chargeToggleGuardR) {
        this.mChargeToggleGuardR = chargeToggleGuardR;
    }

    public int getChargeToggleGuardS() {
        return this.mChargeToggleGuardS;
    }

    public void setChargeToggleGuardS(int chargeToggleGuardS) {
        this.mChargeToggleGuardS = chargeToggleGuardS;
    }

    public int getChargerFaultStateR() {
        return this.mChargerFaultStateR;
    }

    public void setChargerFaultStateR(int chargerFaultStateR) {
        this.mChargerFaultStateR = chargerFaultStateR;
    }

    public int getChargerStateR() {
        return this.mChargerStateR;
    }

    public void setChargerStateR(int chargerStateR) {
        this.mChargerStateR = chargerStateR;
    }

    public int getChargerWorkStateR() {
        return this.mChargerWorkStateR;
    }

    public void setChargerWorkStateR(int chargerWorkStateR) {
        this.mChargerWorkStateR = chargerWorkStateR;
    }

    public int getChargingGunStateR() {
        return this.mChargingGunStateR;
    }

    public void setChargingGunStateR(int chargingGunStateR) {
        this.mChargingGunStateR = chargingGunStateR;
    }

    public float getChargingPowerR() {
        return this.mChargingPowerR;
    }

    public void setChargingPowerR(float chargingPowerR) {
        this.mChargingPowerR = chargingPowerR;
    }

    public int getChargingResetTimeHourR() {
        return this.mChargingResetTimeHourR;
    }

    public void setChargingResetTimeHourR(int chargingResetTimeHourR) {
        this.mChargingResetTimeHourR = chargingResetTimeHourR;
    }

    public int getChargingResetTimeMinuteR() {
        return this.mChargingResetTimeMinuteR;
    }

    public void setChargingResetTimeMinuteR(int chargingResetTimeMinuteR) {
        this.mChargingResetTimeMinuteR = chargingResetTimeMinuteR;
    }

    public int getChargingScheduleEnableStateR() {
        return this.mChargingScheduleEnableStateR;
    }

    public void setChargingScheduleEnableStateR(int chargingScheduleEnableStateR) {
        this.mChargingScheduleEnableStateR = chargingScheduleEnableStateR;
    }

    public int getChargingScheduleStateR() {
        return this.mChargingScheduleStateR;
    }

    public void setChargingScheduleStateR(int chargingScheduleStateR) {
        this.mChargingScheduleStateR = chargingScheduleStateR;
    }

    public int getChargingScheduleTimeDayR() {
        return this.mChargingScheduleTimeDayR;
    }

    public void setChargingScheduleTimeDayR(int chargingScheduleTimeDayR) {
        this.mChargingScheduleTimeDayR = chargingScheduleTimeDayR;
    }

    public int getChargingScheduleTimeHourR() {
        return this.mChargingScheduleTimeHourR;
    }

    public void setChargingScheduleTimeHourR(int chargingScheduleTimeHourR) {
        this.mChargingScheduleTimeHourR = chargingScheduleTimeHourR;
    }

    public int getChargingScheduleTimeMinuteR() {
        return this.mChargingScheduleTimeMinuteR;
    }

    public void setChargingScheduleTimeMinuteR(int chargingScheduleTimeMinuteR) {
        this.mChargingScheduleTimeMinuteR = chargingScheduleTimeMinuteR;
    }

    public int getChargingScheduleTimeMonthR() {
        return this.mChargingScheduleTimeMonthR;
    }

    public void setChargingScheduleTimeMonthR(int chargingScheduleTimeMonthR) {
        this.mChargingScheduleTimeMonthR = chargingScheduleTimeMonthR;
    }

    public int getChargingScheduleTimeYearR() {
        return this.mChargingScheduleTimeYearR;
    }

    public void setChargingScheduleTimeYearR(int chargingScheduleTimeYearR) {
        this.mChargingScheduleTimeYearR = chargingScheduleTimeYearR;
    }

    public int getCharingPortLockRebackStateR() {
        return this.mCharingPortLockRebackStateR;
    }

    public void setCharingPortLockRebackStateR(int charingPortLockRebackStateR) {
        this.mCharingPortLockRebackStateR = charingPortLockRebackStateR;
    }

    public int getDischargeLimitR() {
        return this.mDischargeLimitR;
    }

    public void setDischargeLimitR(int dischargeLimitR) {
        this.mDischargeLimitR = dischargeLimitR;
    }

    public int getDischargeLimitS() {
        return this.mDischargeLimitS;
    }

    public void setDischargeLimitS(int dischargeLimitS) {
        this.mDischargeLimitS = dischargeLimitS;
    }

    public int getDischargeRequestStateR() {
        return this.mDischargeRequestStateR;
    }

    public void setDischargeRequestStateR(int dischargeRequestStateR) {
        this.mDischargeRequestStateR = dischargeRequestStateR;
    }

    public int getDischargeTimeHourR() {
        return this.mDischargeTimeHourR;
    }

    public void setDischargeTimeHourR(int dischargeTimeHourR) {
        this.mDischargeTimeHourR = dischargeTimeHourR;
    }

    public int getDischargeTimeHourS() {
        return this.mDischargeTimeHourS;
    }

    public void setDischargeTimeHourS(int dischargeTimeHourS) {
        this.mDischargeTimeHourS = dischargeTimeHourS;
    }

    public int getDischargeTimeMinuteR() {
        return this.mDischargeTimeMinuteR;
    }

    public void setDischargeTimeMinuteR(int dischargeTimeMinuteR) {
        this.mDischargeTimeMinuteR = dischargeTimeMinuteR;
    }

    public int getDischargeTimeMinuteS() {
        return this.mDischargeTimeMinuteS;
    }

    public void setDischargeTimeMinuteS(int dischargeTimeMinuteS) {
        this.mDischargeTimeMinuteS = dischargeTimeMinuteS;
    }

    public int getDischargeToggleR() {
        return this.mDischargeToggleR;
    }

    public void setDischargeToggleR(int dischargeToggleR) {
        this.mDischargeToggleR = dischargeToggleR;
    }

    public int getDischargeToggleS() {
        return this.mDischargeToggleS;
    }

    public void setDischargeToggleS(int dischargeToggleS) {
        this.mDischargeToggleS = dischargeToggleS;
    }

    public int getMovePowerStationToggleR() {
        return this.mMovePowerStationToggleR;
    }

    public void setMovePowerStationToggleR(int movePowerStationToggleR) {
        this.mMovePowerStationToggleR = movePowerStationToggleR;
    }

    public int getMovePowerStationToggleS() {
        return this.mMovePowerStationToggleS;
    }

    public void setMovePowerStationToggleS(int movePowerStationToggleS) {
        this.mMovePowerStationToggleS = movePowerStationToggleS;
    }

    public int getProducePowerWhenLowPowerR() {
        return this.mProducePowerWhenLowPowerR;
    }

    public void setProducePowerWhenLowPowerR(int producePowerWhenLowPowerR) {
        this.mProducePowerWhenLowPowerR = producePowerWhenLowPowerR;
    }

    public long getProducePowerWhenLowPowerS() {
        return this.mProducePowerWhenLowPowerS;
    }

    public void setProducePowerWhenLowPowerS(int producePowerWhenLowPowerS) {
        this.mProducePowerWhenLowPowerS = producePowerWhenLowPowerS;
    }

    public int getWirelessChargingStateR() {
        return this.mWirelessChargingStateR;
    }

    public void setWirelessChargingStateR(int wirelessChargingStateR) {
        this.mWirelessChargingStateR = wirelessChargingStateR;
    }

    public int getWirelessChargingSwitchStateR() {
        return this.mWirelessChargingSwitchStateR;
    }

    public void setWirelessChargingSwitchStateR(int wirelessChargingSwitchStateR) {
        this.mWirelessChargingSwitchStateR = wirelessChargingSwitchStateR;
    }

    public int getWirelessChargingSwitchStateS() {
        return this.mWirelessChargingSwitchStateS;
    }

    public void setWirelessChargingSwitchStateS(int wirelessChargingSwitchStateS) {
        this.mWirelessChargingSwitchStateS = wirelessChargingSwitchStateS;
    }

    public int getBatteryTypeR() {
        return this.mBatteryTypeR;
    }

    public void setBatteryTypeR(int batteryTypeR) {
        this.mBatteryTypeR = batteryTypeR;
    }

    public int getChargeBatteryLevelBeginR() {
        return this.mChargeBatteryLevelBeginR;
    }

    public void setChargeBatteryLevelBeginR(int chargeBatteryLevelBeginR) {
        this.mChargeBatteryLevelBeginR = chargeBatteryLevelBeginR;
    }

    public int getChargeBatteryLimitTempR() {
        return this.mChargeBatteryLimitTempR;
    }

    public void setChargeBatteryLimitTempR(int chargeBatteryLimitTempR) {
        this.mChargeBatteryLimitTempR = chargeBatteryLimitTempR;
    }

    public int getChargeBatteryTempR() {
        return this.mChargeBatteryTempR;
    }

    public void setChargeBatteryTempR(int chargeBatteryTempR) {
        this.mChargeBatteryTempR = chargeBatteryTempR;
    }

    public float getChargeElectricR() {
        return this.mChargeElectricR;
    }

    public void setChargeElectricR(float chargeElectricR) {
        this.mChargeElectricR = chargeElectricR;
    }

    public float getChargeVoltageMaxR() {
        return this.mChargeVoltageMaxR;
    }

    public void setChargeVoltageMaxR(float chargeVoltageMaxR) {
        this.mChargeVoltageMaxR = chargeVoltageMaxR;
    }

    public int getChargeVoltageR() {
        return this.mChargeVoltageR;
    }

    public void setChargeVoltageR(int chargeVoltageR) {
        this.mChargeVoltageR = chargeVoltageR;
    }

    public float getDischargeElectricR() {
        return this.mDischargeElectricR;
    }

    public void setDischargeElectricR(float dischargeElectricR) {
        this.mDischargeElectricR = dischargeElectricR;
    }

    public int getDischargeGunTimerR() {
        return this.mDischargeGunTimerR;
    }

    public void setDischargeGunTimerR(int dischargeGunTimerR) {
        this.mDischargeGunTimerR = dischargeGunTimerR;
    }

    public int getDischargeOverReasonR() {
        return this.mDischargeOverReasonR;
    }

    public void setDischargeOverReasonR(int dischargeOverReasonR) {
        this.mDischargeOverReasonR = dischargeOverReasonR;
    }

    public int getDischargeRemainTimeR() {
        return this.mDischargeRemainTimeR;
    }

    public void setDischargeRemainTimeR(int dischargeRemainTimeR) {
        this.mDischargeRemainTimeR = dischargeRemainTimeR;
    }

    public int getDischargeTotalTimeR() {
        return this.mDischargeTotalTimeR;
    }

    public void setDischargeTotalTimeR(int dischargeTotalTimeR) {
        this.mDischargeTotalTimeR = dischargeTotalTimeR;
    }

    public int getDischargeVoltageR() {
        return this.mDischargeVoltageR;
    }

    public void setDischargeVoltageR(int dischargeVoltageR) {
        this.mDischargeVoltageR = dischargeVoltageR;
    }

    public float getVoltageMaxR() {
        return this.mVoltageMaxR;
    }

    public void setVoltageMaxR(float voltageMaxR) {
        this.mVoltageMaxR = voltageMaxR;
    }

    public int getVtovChargeBatteryLevelNumLimitR() {
        return this.mVtovChargeBatteryLevelNumLimitR;
    }

    public void setVtovChargeBatteryLevelNumLimitR(int vtovChargeBatteryLevelNumLimitR) {
        this.mVtovChargeBatteryLevelNumLimitR = vtovChargeBatteryLevelNumLimitR;
    }

    public int getVtovChargeBatteryLevelNumR() {
        return this.mVtovChargeBatteryLevelNumR;
    }

    public void setVtovChargeBatteryLevelNumR(int vtovChargeBatteryLevelNumR) {
        this.mVtovChargeBatteryLevelNumR = vtovChargeBatteryLevelNumR;
    }

    public int getVtovDischargeLowestValR() {
        return this.mVtovDischargeLowestValR;
    }

    public void setVtovDischargeLowestValR(int vtovDischargeLowestValR) {
        this.mVtovDischargeLowestValR = vtovDischargeLowestValR;
    }

    public float getVtovDischargeQuantityR() {
        return this.mVtovDischargeQuantityR;
    }

    public void setVtovDischargeQuantityR(float vtovDischargeQuantityR) {
        this.mVtovDischargeQuantityR = vtovDischargeQuantityR;
    }

    public int getgetPortCapStateLeftR() {
        return this.mgetPortCapStateLeftR;
    }

    public void setgetPortCapStateLeftR(int getPortCapStateLeftR) {
        this.mgetPortCapStateLeftR = getPortCapStateLeftR;
    }

    public int getgetPortCapStateRightR() {
        return this.mgetPortCapStateRightR;
    }

    public void setgetPortCapStateRightR(int getPortCapStateRightR) {
        this.mgetPortCapStateRightR = getPortCapStateRightR;
    }
}
