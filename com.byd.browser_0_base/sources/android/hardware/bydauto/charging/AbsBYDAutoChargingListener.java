package android.hardware.bydauto.charging;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoChargingListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoChargingListener";

    public void onDischargeConnecting() {
    }

    public void onACChargingLimitChanged(int i) {
    }

    public void onBMSWakeNoSenseChargeChanged(byte[] bArr) {
    }

    public void onBatteryManagementDeviceStateChanged(int i) {
    }

    public void onBatteryTypeChanged(int i) {
    }

    public void onCapStateChanged(int i) {
    }

    public void onCarDischargeLowWarnChanged(int i) {
    }

    public void onCarDischargeStateChanged(int i) {
    }

    public void onCarPlanAccOnStateChanged(int i) {
    }

    public void onCarPlanBatteryHeatStateChanged(int i) {
    }

    public void onCarPlanStateChanged(int i) {
    }

    public void onChargeLimitChanged(float f) {
    }

    public void onChargePreheatStateChanged(int i) {
    }

    public void onChargeStopCapacityStateChanged(int i) {
    }

    public void onChargeStopSwitchStateChanged(int i) {
    }

    public void onChargeTempCtlStateChanged(int i) {
    }

    public void onChargeToggleGuardChanged(int i) {
    }

    public void onChargerFaultStateChanged(int i) {
    }

    public void onChargerStateChanged(int i) {
    }

    public void onChargerWorkStateChanged(int i) {
    }

    public void onChargingCapacityChanged(float f) {
    }

    public void onChargingGunNotInsertedStateChanged(int i) {
    }

    public void onChargingGunStateChanged(int i) {
    }

    public void onChargingModeChanged(int i) {
    }

    public void onChargingPortLockRebackStateChanged(int i) {
    }

    public void onChargingPowerChanged(float f) {
    }

    public void onChargingScheduleEnableStateChanged(int i) {
    }

    public void onChargingScheduleStateChanged(int i) {
    }

    public void onChargingStateChanged(int i) {
    }

    public void onChargingTimerInfoChanged(ChargingTimerInfo chargingTimerInfo) {
    }

    public void onChargingTypeChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onDisChargeWarningStateChanged(int i) {
    }

    public void onDischargeGunConnectCountDown(int i) {
    }

    public void onDischargeLimitChanged(float f) {
    }

    public void onDischargeRequestStateChanged(int i) {
    }

    public void onDischargeTimeChanged(long j) {
    }

    public void onDischargeToggleChanged(int i) {
    }

    public void onHEVChargePlanEndTimeChargeChanged(int[] iArr) {
    }

    public void onHEVChargePlanStartTimeChargeChanged(int[] iArr) {
    }

    public void onIntelligentKeepTemModeChanged(int i) {
    }

    public void onMovePowerStationToggleChanged(int i) {
    }

    public void onNoSenseChargeStateChanged(int i) {
    }

    public void onOBCWakeNoSenseChargeChanged(byte[] bArr) {
    }

    public void onPorducePowerWhenLowPowerChanged(int i) {
    }

    public void onSituPowerGenerationStateChanged(int i) {
    }

    public void onSituPowerGenerationSupportChanged(int i) {
    }

    public void onSmartChargingStateChanged(int i) {
    }

    public void onSocSaveSwitchChanged(int i) {
    }

    public void onVtovChargeBatteryLevelNumChanged(int i) {
    }

    public void onVtovDischargeConnectStateChanged(int i) {
    }

    public void onVtovDischargeLimitValChanged(int i) {
    }

    public void onVtovDischargeLowestValChanged(int i) {
    }

    public void onVtovDischargeQuantityChanged(float f) {
    }

    public void onWeatherAndTimeRequestChanged(int i) {
    }

    public void onWirelessChargingFRStateChanged(int i) {
    }

    public void onWirelessChargingFRSwitchStateChanged(int i) {
    }

    public void onWirelessChargingOnline5sStateChanged(int i) {
    }

    public void onWirelessChargingPowerFLStateChanged(int i) {
    }

    public void onWirelessChargingPowerFRStateChanged(int i) {
    }

    public void onWirelessChargingStateChanged(int i) {
    }

    public void onWirelessChargingSwitchStateChanged(int i) {
    }

    public void onWirlessChargingStateChanged(int i) {
    }

    public void onChargingCapStateChanged(int i, int i2) {
    }

    public void onChargingRestTimeChanged(int i, int i2) {
    }

    public void onChargingScheduleTimeCountDownChanged(int i, int i2) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDischargeStateChanged(int i, int i2) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onFeatureChanged(String str, int i) {
    }

    public void onPortCapStateChanged(int i, int i2) {
    }
}
