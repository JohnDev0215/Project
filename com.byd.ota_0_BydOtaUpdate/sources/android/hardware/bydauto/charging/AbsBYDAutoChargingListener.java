package android.hardware.bydauto.charging;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoChargingListener implements d {
    public static final String TAG = "AbsBYDAutoChargingListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onACChargingLimitChanged(int i2) {
        Log.v(TAG, "onACChargingLimitChanged");
    }

    public void onBMSWakeNoSenseChargeChanged(byte[] bArr) {
        Log.v(TAG, "onBMSWakeNoSenseChargeChanged");
    }

    public void onBatteryManagementDeviceStateChanged(int i2) {
        Log.v(TAG, "onBatteryManagementDeviceStateChanged");
    }

    public void onBatteryTypeChanged(int i2) {
        Log.v(TAG, "onBatteryTypeChanged");
    }

    public void onCapStateChanged(int i2) {
        Log.v(TAG, "onCapStateChanged");
    }

    public void onCarDischargeLowWarnChanged(int i2) {
        Log.v(TAG, "onCarDischargeLowWarnChanged");
    }

    public void onCarDischargeStateChanged(int i2) {
        Log.v(TAG, "onCarDischargeStateChanged");
    }

    public void onCarPlanAccOnStateChanged(int i2) {
        Log.v(TAG, "onCarPlanAccOnStateChanged");
    }

    public void onCarPlanBatteryHeatStateChanged(int i2) {
        Log.v(TAG, "onCarPlanBatteryHeatStateChanged");
    }

    public void onCarPlanStateChanged(int i2) {
        Log.v(TAG, "onCarPlanStateChanged");
    }

    public void onChargeLimitChanged(float f2) {
        Log.v(TAG, "onChargeLimitChanged");
    }

    public void onChargePreheatStateChanged(int i2) {
        Log.v(TAG, "onChargePreheatStateChanged");
    }

    public void onChargeStopCapacityStateChanged(int i2) {
        Log.v(TAG, "onChargeStopCapacityStateChanged");
    }

    public void onChargeStopCapacityStateNotLiChanged(int i2) {
        Log.v(TAG, "onChargeStopCapacityStateNotLiChanged state = " + i2);
    }

    public void onChargeStopSwitchStateChanged(int i2) {
        Log.v(TAG, "onChargeStopSwitchStateChanged");
    }

    public void onChargeTempCtlStateChanged(int i2) {
        Log.v(TAG, "onChargeTempCtlStateChanged");
    }

    public void onChargeToggleGuardChanged(int i2) {
        Log.v(TAG, "onChargeToaggleGuardChanged");
    }

    public void onChargerFaultStateChanged(int i2) {
        Log.v(TAG, "onChargerFaultStateChanged");
    }

    public void onChargerStateChanged(int i2) {
        Log.v(TAG, "onChargerStateChanged");
    }

    public void onChargerWorkStateChanged(int i2) {
        Log.v(TAG, "onChargerWorkStateChanged");
    }

    public void onChargingCapStateChanged(int i2, int i3) {
        Log.v(TAG, "onChargingCapStateChanged");
    }

    public void onChargingCapacityChanged(float f2) {
        Log.v(TAG, "onChargingCapacityChanged");
    }

    public void onChargingGunNotInsertedStateChanged(int i2) {
        Log.v(TAG, "onChargingGunNotInsertedStateChanged");
    }

    public void onChargingGunStateChanged(int i2) {
        Log.v(TAG, "onChargingGunStateChanged");
    }

    public void onChargingModeChanged(int i2) {
        Log.v(TAG, "onChargingModeChanged");
    }

    public void onChargingPortLockRebackStateChanged(int i2) {
        Log.v(TAG, "onChargingPortLockRebackStateChanged");
    }

    public void onChargingPowerChanged(float f2) {
        Log.v(TAG, "onChargingPowerChanged");
    }

    public void onChargingRestTimeChanged(int i2, int i3) {
        Log.v(TAG, "onChargingRestTimeChanged");
    }

    public void onChargingScheduleEnableStateChanged(int i2) {
        Log.v(TAG, "onChargingScheduleEnableStateChanged");
    }

    public void onChargingScheduleStateChanged(int i2) {
        Log.v(TAG, "onChargingScheduleStateChanged");
    }

    public void onChargingScheduleTimeCountDownChanged(int i2, int i3) {
        Log.v(TAG, "onChargingScheduleTimeCountDownChanged");
    }

    public void onChargingStateChanged(int i2) {
        Log.v(TAG, "onChargingStateChanged");
    }

    public void onChargingTimerInfoChanged(ChargingTimerInfo chargingTimerInfo) {
        Log.v(TAG, "onChargingTimerInfoChanged");
    }

    public void onChargingTypeChanged(int i2) {
        Log.v(TAG, "onChargingTypeChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDisChargeWarningStateChanged(int i2) {
        Log.v(TAG, "onDisChargeWarningStateChanged");
    }

    public void onDischargeConnecting() {
        Log.v(TAG, "onDischargConnecting");
    }

    public void onDischargeGunConnectCountDown(int i2) {
        Log.v(TAG, "onDischargeGunConnectCountDown");
    }

    public void onDischargeLimitChanged(float f2) {
        Log.v(TAG, "onDischargeLimitChanged");
    }

    public void onDischargeRequestStateChanged(int i2) {
        Log.v(TAG, "onDischargeRequestStateChanged");
    }

    public void onDischargeStateChanged(int i2, int i3) {
        Log.v(TAG, "onDischargeStateChanged");
    }

    public void onDischargeTimeChanged(long j) {
        Log.v(TAG, "onDischargeTimeChanged");
    }

    public void onDischargeToggleChanged(int i2) {
        Log.v(TAG, "onDischargeToggleChanged");
    }

    public void onError(int i2, String str) {
        Log.v(TAG, "onError");
    }

    public void onFeatureChanged(String str, int i2) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onHEVChargePlanEndTimeChargeChanged(int[] iArr) {
        Log.v(TAG, "onHEVChargePlanEndTimeChargeChanged");
    }

    public void onHEVChargePlanStartTimeChargeChanged(int[] iArr) {
        Log.v(TAG, "onHEVChargePlanStartTimeChargeChanged");
    }

    public void onIntelligentKeepTemModeChanged(int i2) {
        Log.v(TAG, "onIntelligentKeepTemModeChanged");
    }

    public void onMovePowerStationToggleChanged(int i2) {
        Log.v(TAG, "onMovePowerStationToggleChanged");
    }

    public void onNoSenseChargeStateChanged(int i2) {
        Log.v(TAG, "onNoSenseChargeStateChanged");
    }

    public void onOBCWakeNoSenseChargeChanged(byte[] bArr) {
        Log.v(TAG, "onOBCWakeNoSenseChargeChanged");
    }

    public void onPorducePowerWhenLowPowerChanged(int i2) {
        Log.v(TAG, "onPorducePowerWhenLowPowerChanged");
    }

    public void onPortCapStateChanged(int i2, int i3) {
        Log.v(TAG, "onPortCapStateChanged");
    }

    public void onSituPowerGenerationStateChanged(int i2) {
        Log.v(TAG, "onSituPowerGenerationStateChanged");
    }

    public void onSituPowerGenerationSupportChanged(int i2) {
        Log.v(TAG, "onSituPowerGenerationSupportChanged");
    }

    public void onSmartChargingStateChanged(int i2) {
        Log.v(TAG, "onSmartChargingStateChanged");
    }

    public void onSocSaveSwitchChanged(int i2) {
        Log.v(TAG, "onSocSaveSwitchChanged");
    }

    public void onV2GChargeStatusChanged(int i2) {
        Log.v(TAG, "onV2GChargeStatusChanged value = " + i2);
    }

    public void onV2GOutputPowerChanged(int i2) {
        Log.v(TAG, "onV2GOutputPowerChanged value = " + i2);
    }

    public void onV2GStatusChanged(int i2) {
        Log.v(TAG, "onV2GStatusChanged value = " + i2);
    }

    public void onVtovChargeBatteryLevelNumChanged(int i2) {
        Log.v(TAG, "onVtovChargeBatteryLevelNumChanged");
    }

    public void onVtovDischargeConnectStateChanged(int i2) {
        Log.v(TAG, "onVtovDischargeConnectStateChanged");
    }

    public void onVtovDischargeLimitValChanged(int i2) {
        Log.v(TAG, "onVtovDischargeLimitValChanged");
    }

    public void onVtovDischargeLowestValChanged(int i2) {
        Log.v(TAG, "onVtovDischargeLowestValChanged");
    }

    public void onVtovDischargeQuantityChanged(float f2) {
        Log.v(TAG, "onVtovDischargeQuantityChanged");
    }

    public void onWeatherAndTimeRequestChanged(int i2) {
        Log.v(TAG, "onWeatherAndTimeRequestChanged");
    }

    public void onWirelessChargingFRStateChanged(int i2) {
        Log.v(TAG, "onWirelessChargingFRStateChanged");
    }

    public void onWirelessChargingFRSwitchStateChanged(int i2) {
        Log.v(TAG, "onWirelessChargingFRSwitchStateChanged");
    }

    public void onWirelessChargingOnline5sStateChanged(int i2) {
        Log.v(TAG, "onWirelessChargingOnline5sStateChanged");
    }

    public void onWirelessChargingPowerFLStateChanged(int i2) {
        Log.v(TAG, "onWirelessChargingPowerFLStateChanged");
    }

    public void onWirelessChargingPowerFRStateChanged(int i2) {
        Log.v(TAG, "onWirelessChargingPowerFRStateChanged");
    }

    public void onWirelessChargingStateChanged(int i2) {
        Log.v(TAG, "onWirelessChargingStateChanged");
    }

    public void onWirelessChargingSwitchStateChanged(int i2) {
        Log.v(TAG, "onWirelessChargingSwitchStateChanged");
    }

    public void onWirlessChargingStateChanged(int i2) {
        Log.v(TAG, "onWirlessChargingStateChanged");
    }
}
