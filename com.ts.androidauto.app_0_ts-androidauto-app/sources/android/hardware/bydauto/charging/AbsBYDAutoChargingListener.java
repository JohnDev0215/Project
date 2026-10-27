package android.hardware.bydauto.charging;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoChargingListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoChargingListener";

    public void onChargingStateChanged(int state) {
        Log.v(TAG, "onChargingStateChanged");
    }

    public void onChargingModeChanged(int mode) {
        Log.v(TAG, "onChargingModeChanged");
    }

    public void onChargingTimerInfoChanged(ChargingTimerInfo timerInfo) {
        Log.v(TAG, "onChargingTimerInfoChanged");
    }

    public void onChargerFaultStateChanged(int state) {
        Log.v(TAG, "onChargerFaultStateChanged");
    }

    public void onChargerWorkStateChanged(int state) {
        Log.v(TAG, "onChargerWorkStateChanged");
    }

    public void onChargingCapacityChanged(float value) {
        Log.v(TAG, "onChargingCapacityChanged");
    }

    public void onChargingTypeChanged(int type) {
        Log.v(TAG, "onChargingTypeChanged");
    }

    public void onChargingRestTimeChanged(int hour, int min) {
        Log.v(TAG, "onChargingRestTimeChanged");
    }

    public void onChargingCapStateChanged(int type, int state) {
        Log.v(TAG, "onChargingCapStateChanged");
    }

    public void onWirelessChargingSwitchStateChanged(int state) {
        Log.v(TAG, "onWirelessChargingSwitchStateChanged");
    }

    public void onWirelessChargingOnline5sStateChanged(int state) {
        Log.v(TAG, "onWirelessChargingOnline5sStateChanged");
    }

    public void onChargingPortLockRebackStateChanged(int state) {
        Log.v(TAG, "onChargingPortLockRebackStateChanged");
    }

    public void onDischargeRequestStateChanged(int state) {
        Log.v(TAG, "onDischargeRequestStateChanged");
    }

    public void onChargerStateChanged(int state) {
        Log.v(TAG, "onChargerStateChanged");
    }

    public void onChargingGunStateChanged(int state) {
        Log.v(TAG, "onChargingGunStateChanged");
    }

    public void onChargingPowerChanged(float value) {
        Log.v(TAG, "onChargingPowerChanged");
    }

    public void onBatteryManagementDeviceStateChanged(int state) {
        Log.v(TAG, "onBatteryManagementDeviceStateChanged");
    }

    public void onChargingScheduleEnableStateChanged(int state) {
        Log.v(TAG, "onChargingScheduleEnableStateChanged");
    }

    public void onChargingScheduleStateChanged(int state) {
        Log.v(TAG, "onChargingScheduleStateChanged");
    }

    public void onChargingGunNotInsertedStateChanged(int state) {
        Log.v(TAG, "onChargingGunNotInsertedStateChanged");
    }

    public void onSmartChargingStateChanged(int state) {
        Log.v(TAG, "onSmartChargingStateChanged");
    }

    public void onDischargeStateChanged(int type, int state) {
        Log.v(TAG, "onDischargeStateChanged");
    }

    public void onDisChargeWarningStateChanged(int state) {
        Log.v(TAG, "onDisChargeWarningStateChanged");
    }

    public void onFeatureChanged(String feature, int ifHas) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onWirlessChargingStateChanged(int state) {
        Log.v(TAG, "onWirlessChargingStateChanged");
    }

    public void onChargeTempCtlStateChanged(int state) {
        Log.v(TAG, "onChargeTempCtlStateChanged");
    }

    public void onBatteryTypeChanged(int type) {
        Log.v(TAG, "onBatteryTypeChanged");
    }

    public void onChargeStopSwitchStateChanged(int state) {
        Log.v(TAG, "onChargeStopSwitchStateChanged");
    }

    public void onChargeStopCapacityStateChanged(int state) {
        Log.v(TAG, "onChargeStopCapacityStateChanged");
    }

    public void onWeatherAndTimeRequestChanged(int state) {
        Log.v(TAG, "onWeatherAndTimeRequestChanged");
    }

    public void onCarDischargeStateChanged(int state) {
        Log.v(TAG, "onCarDischargeStateChanged");
    }

    public void onCarDischargeLowWarnChanged(int state) {
        Log.v(TAG, "onCarDischargeLowWarnChanged");
    }

    public void onVtovDischargeConnectStateChanged(int value) {
        Log.v(TAG, "onVtovDischargeConnectStateChanged");
    }

    public void onVtovDischargeLimitValChanged(int value) {
        Log.v(TAG, "onVtovDischargeLimitValChanged");
    }

    public void onVtovDischargeLowestValChanged(int value) {
        Log.v(TAG, "onVtovDischargeLowestValChanged");
    }

    public void onVtovDischargeQuantityChanged(float value) {
        Log.v(TAG, "onVtovDischargeQuantityChanged");
    }

    public void onCapStateChanged(int value) {
        Log.v(TAG, "onCapStateChanged");
    }

    public void onSocSaveSwitchChanged(int value) {
        Log.v(TAG, "onSocSaveSwitchChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        Log.v(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        Log.v(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onChargingScheduleTimeCountDownChanged(int hour, int min) {
        Log.v(TAG, "onChargingScheduleTimeCountDownChanged");
    }

    public void onIntelligentKeepTemModeChanged(int mode) {
        Log.v(TAG, "onIntelligentKeepTemModeChanged");
    }

    public void onChargeLimitChanged(float percent) {
        Log.v(TAG, "onChargeLimitChanged");
    }

    public void onDischargeLimitChanged(float percent) {
        Log.v(TAG, "onDischargeLimitChanged");
    }

    public void onChargeToggleGuardChanged(int mode) {
        Log.v(TAG, "onChargeToaggleGuardChanged");
    }

    public void onMovePowerStationToggleChanged(int state) {
        Log.v(TAG, "onMovePowerStationToggleChanged");
    }

    public void onDischargeGunConnectCountDown(int time) {
        Log.v(TAG, "onDischargeGunConnectCountDown");
    }

    public void onDischargeConnecting() {
        Log.v(TAG, "onDischargConnecting");
    }

    public void onDischargeToggleChanged(int state) {
        Log.v(TAG, "onDischargeToggleChanged");
    }

    public void onDischargeTimeChanged(long time) {
        Log.v(TAG, "onDischargeTimeChanged");
    }

    public void onPorducePowerWhenLowPowerChanged(int state) {
        Log.v(TAG, "onPorducePowerWhenLowPowerChanged");
    }

    public void onWirelessChargingStateChanged(int state) {
        Log.v(TAG, "onWirelessChargingStateChanged");
    }

    public void onVtovChargeBatteryLevelNumChanged(int value) {
        Log.v(TAG, "onVtovChargeBatteryLevelNumChanged");
    }

    public void onSituPowerGenerationStateChanged(int state) {
        Log.v(TAG, "onSituPowerGenerationStateChanged");
    }

    public void onSituPowerGenerationSupportChanged(int state) {
        Log.v(TAG, "onSituPowerGenerationSupportChanged");
    }

    public void onChargePreheatStateChanged(int state) {
        Log.v(TAG, "onChargePreheatStateChanged");
    }

    public void onCarPlanStateChanged(int state) {
        Log.v(TAG, "onCarPlanStateChanged");
    }

    public void onCarPlanAccOnStateChanged(int state) {
        Log.v(TAG, "onCarPlanAccOnStateChanged");
    }

    public void onCarPlanBatteryHeatStateChanged(int state) {
        Log.v(TAG, "onCarPlanBatteryHeatStateChanged");
    }

    public void onNoSenseChargeStateChanged(int state) {
        Log.v(TAG, "onNoSenseChargeStateChanged");
    }

    public void onBMSWakeNoSenseChargeChanged(byte[] value) {
        Log.v(TAG, "onBMSWakeNoSenseChargeChanged");
    }

    public void onOBCWakeNoSenseChargeChanged(byte[] value) {
        Log.v(TAG, "onOBCWakeNoSenseChargeChanged");
    }

    public void onWirelessChargingFRSwitchStateChanged(int state) {
        Log.v(TAG, "onWirelessChargingFRSwitchStateChanged");
    }

    public void onWirelessChargingFRStateChanged(int state) {
        Log.v(TAG, "onWirelessChargingFRStateChanged");
    }

    public void onWirelessChargingPowerFRStateChanged(int state) {
        Log.v(TAG, "onWirelessChargingPowerFRStateChanged");
    }

    public void onWirelessChargingPowerFLStateChanged(int state) {
        Log.v(TAG, "onWirelessChargingPowerFLStateChanged");
    }

    public void onPortCapStateChanged(int type, int state) {
        Log.v(TAG, "onPortCapStateChanged");
    }

    public void onHEVChargePlanStartTimeChargeChanged(int[] value) {
        Log.v(TAG, "onHEVChargePlanStartTimeChargeChanged");
    }

    public void onHEVChargePlanEndTimeChargeChanged(int[] value) {
        Log.v(TAG, "onHEVChargePlanEndTimeChargeChanged");
    }

    public void onACChargingLimitChanged(int value) {
        Log.v(TAG, "onACChargingLimitChanged");
    }

    public void onChargeStopCapacityStateNotLiChanged(int state) {
        Log.v(TAG, "onChargeStopCapacityStateNotLiChanged state = " + state);
    }

    public void onV2GStatusChanged(int value) {
        Log.v(TAG, "onV2GStatusChanged value = " + value);
    }

    public void onV2GChargeStatusChanged(int value) {
        Log.v(TAG, "onV2GChargeStatusChanged value = " + value);
    }

    public void onV2GOutputPowerChanged(int value) {
        Log.v(TAG, "onV2GOutputPowerChanged value = " + value);
    }

    public void onElectricPortCapStateChanged(int type, int state) {
        Log.v(TAG, "onElectricPortCapStateChanged type " + type + "state is " + state);
    }
}
