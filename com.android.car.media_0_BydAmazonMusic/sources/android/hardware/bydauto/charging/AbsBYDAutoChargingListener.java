package android.hardware.bydauto.charging;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoChargingListener implements IBYDAutoListener {
    public static final String TAG = "AbsBYDAutoChargingListener";

    public void onACChargingLimitChanged(int i) {
        Log.v(TAG, "onACChargingLimitChanged");
    }

    public void onBMSWakeNoSenseChargeChanged(byte[] bArr) {
        Log.v(TAG, "onBMSWakeNoSenseChargeChanged");
    }

    public void onBatteryManagementDeviceStateChanged(int i) {
        Log.v(TAG, "onBatteryManagementDeviceStateChanged");
    }

    public void onBatteryTypeChanged(int i) {
        Log.v(TAG, "onBatteryTypeChanged");
    }

    public void onCapStateChanged(int i) {
        Log.v(TAG, "onCapStateChanged");
    }

    public void onCarDischargeLowWarnChanged(int i) {
        Log.v(TAG, "onCarDischargeLowWarnChanged");
    }

    public void onCarDischargeStateChanged(int i) {
        Log.v(TAG, "onCarDischargeStateChanged");
    }

    public void onCarPlanAccOnStateChanged(int i) {
        Log.v(TAG, "onCarPlanAccOnStateChanged");
    }

    public void onCarPlanBatteryHeatStateChanged(int i) {
        Log.v(TAG, "onCarPlanBatteryHeatStateChanged");
    }

    public void onCarPlanStateChanged(int i) {
        Log.v(TAG, "onCarPlanStateChanged");
    }

    public void onChargeLimitChanged(float f) {
        Log.v(TAG, "onChargeLimitChanged");
    }

    public void onChargePreheatStateChanged(int i) {
        Log.v(TAG, "onChargePreheatStateChanged");
    }

    public void onChargeStopCapacityStateChanged(int i) {
        Log.v(TAG, "onChargeStopCapacityStateChanged");
    }

    public void onChargeStopSwitchStateChanged(int i) {
        Log.v(TAG, "onChargeStopSwitchStateChanged");
    }

    public void onChargeTempCtlStateChanged(int i) {
        Log.v(TAG, "onChargeTempCtlStateChanged");
    }

    public void onChargeToggleGuardChanged(int i) {
        Log.v(TAG, "onChargeToaggleGuardChanged");
    }

    public void onChargerFaultStateChanged(int i) {
        Log.v(TAG, "onChargerFaultStateChanged");
    }

    public void onChargerStateChanged(int i) {
        Log.v(TAG, "onChargerStateChanged");
    }

    public void onChargerWorkStateChanged(int i) {
        Log.v(TAG, "onChargerWorkStateChanged");
    }

    public void onChargingCapStateChanged(int i, int i2) {
        Log.v(TAG, "onChargingCapStateChanged");
    }

    public void onChargingCapacityChanged(float f) {
        Log.v(TAG, "onChargingCapacityChanged");
    }

    public void onChargingGunNotInsertedStateChanged(int i) {
        Log.v(TAG, "onChargingGunNotInsertedStateChanged");
    }

    public void onChargingGunStateChanged(int i) {
        Log.v(TAG, "onChargingGunStateChanged");
    }

    public void onChargingModeChanged(int i) {
        Log.v(TAG, "onChargingModeChanged");
    }

    public void onChargingPortLockRebackStateChanged(int i) {
        Log.v(TAG, "onChargingPortLockRebackStateChanged");
    }

    public void onChargingPowerChanged(float f) {
        Log.v(TAG, "onChargingPowerChanged");
    }

    public void onChargingRestTimeChanged(int i, int i2) {
        Log.v(TAG, "onChargingRestTimeChanged");
    }

    public void onChargingScheduleEnableStateChanged(int i) {
        Log.v(TAG, "onChargingScheduleEnableStateChanged");
    }

    public void onChargingScheduleStateChanged(int i) {
        Log.v(TAG, "onChargingScheduleStateChanged");
    }

    public void onChargingScheduleTimeCountDownChanged(int i, int i2) {
        Log.v(TAG, "onChargingScheduleTimeCountDownChanged");
    }

    public void onChargingStateChanged(int i) {
        Log.v(TAG, "onChargingStateChanged");
    }

    public void onChargingTimerInfoChanged(ChargingTimerInfo chargingTimerInfo) {
        Log.v(TAG, "onChargingTimerInfoChanged");
    }

    public void onChargingTypeChanged(int i) {
        Log.v(TAG, "onChargingTypeChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.v(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDisChargeWarningStateChanged(int i) {
        Log.v(TAG, "onDisChargeWarningStateChanged");
    }

    public void onDischargeConnecting() {
        Log.v(TAG, "onDischargConnecting");
    }

    public void onDischargeGunConnectCountDown(int i) {
        Log.v(TAG, "onDischargeGunConnectCountDown");
    }

    public void onDischargeLimitChanged(float f) {
        Log.v(TAG, "onDischargeLimitChanged");
    }

    public void onDischargeRequestStateChanged(int i) {
        Log.v(TAG, "onDischargeRequestStateChanged");
    }

    public void onDischargeStateChanged(int i, int i2) {
        Log.v(TAG, "onDischargeStateChanged");
    }

    public void onDischargeTimeChanged(long j) {
        Log.v(TAG, "onDischargeTimeChanged");
    }

    public void onDischargeToggleChanged(int i) {
        Log.v(TAG, "onDischargeToggleChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    public void onFeatureChanged(String str, int i) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onHEVChargePlanEndTimeChargeChanged(int[] iArr) {
        Log.v(TAG, "onHEVChargePlanEndTimeChargeChanged");
    }

    public void onHEVChargePlanStartTimeChargeChanged(int[] iArr) {
        Log.v(TAG, "onHEVChargePlanStartTimeChargeChanged");
    }

    public void onIntelligentKeepTemModeChanged(int i) {
        Log.v(TAG, "onIntelligentKeepTemModeChanged");
    }

    public void onMovePowerStationToggleChanged(int i) {
        Log.v(TAG, "onMovePowerStationToggleChanged");
    }

    public void onNoSenseChargeStateChanged(int i) {
        Log.v(TAG, "onNoSenseChargeStateChanged");
    }

    public void onOBCWakeNoSenseChargeChanged(byte[] bArr) {
        Log.v(TAG, "onOBCWakeNoSenseChargeChanged");
    }

    public void onPorducePowerWhenLowPowerChanged(int i) {
        Log.v(TAG, "onPorducePowerWhenLowPowerChanged");
    }

    public void onPortCapStateChanged(int i, int i2) {
        Log.v(TAG, "onPortCapStateChanged");
    }

    public void onSituPowerGenerationStateChanged(int i) {
        Log.v(TAG, "onSituPowerGenerationStateChanged");
    }

    public void onSituPowerGenerationSupportChanged(int i) {
        Log.v(TAG, "onSituPowerGenerationSupportChanged");
    }

    public void onSmartChargingStateChanged(int i) {
        Log.v(TAG, "onSmartChargingStateChanged");
    }

    public void onSocSaveSwitchChanged(int i) {
        Log.v(TAG, "onSocSaveSwitchChanged");
    }

    public void onVtovChargeBatteryLevelNumChanged(int i) {
        Log.v(TAG, "onVtovChargeBatteryLevelNumChanged");
    }

    public void onVtovDischargeConnectStateChanged(int i) {
        Log.v(TAG, "onVtovDischargeConnectStateChanged");
    }

    public void onVtovDischargeLimitValChanged(int i) {
        Log.v(TAG, "onVtovDischargeLimitValChanged");
    }

    public void onVtovDischargeLowestValChanged(int i) {
        Log.v(TAG, "onVtovDischargeLowestValChanged");
    }

    public void onVtovDischargeQuantityChanged(float f) {
        Log.v(TAG, "onVtovDischargeQuantityChanged");
    }

    public void onWeatherAndTimeRequestChanged(int i) {
        Log.v(TAG, "onWeatherAndTimeRequestChanged");
    }

    public void onWirelessChargingFRStateChanged(int i) {
        Log.v(TAG, "onWirelessChargingFRStateChanged");
    }

    public void onWirelessChargingFRSwitchStateChanged(int i) {
        Log.v(TAG, "onWirelessChargingFRSwitchStateChanged");
    }

    public void onWirelessChargingOnline5sStateChanged(int i) {
        Log.v(TAG, "onWirelessChargingOnline5sStateChanged");
    }

    public void onWirelessChargingPowerFLStateChanged(int i) {
        Log.v(TAG, "onWirelessChargingPowerFLStateChanged");
    }

    public void onWirelessChargingPowerFRStateChanged(int i) {
        Log.v(TAG, "onWirelessChargingPowerFRStateChanged");
    }

    public void onWirelessChargingStateChanged(int i) {
        Log.v(TAG, "onWirelessChargingStateChanged");
    }

    public void onWirelessChargingSwitchStateChanged(int i) {
        Log.v(TAG, "onWirelessChargingSwitchStateChanged");
    }

    public void onWirlessChargingStateChanged(int i) {
        Log.v(TAG, "onWirlessChargingStateChanged");
    }
}
