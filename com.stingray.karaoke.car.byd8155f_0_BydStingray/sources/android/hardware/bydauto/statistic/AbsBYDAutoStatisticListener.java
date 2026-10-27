package android.hardware.bydauto.statistic;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoStatisticListener implements d {
    protected static final String TAG = "AbsBYDAutoStatisticListener";

    public AbsBYDAutoStatisticListener() {
        Log.v(TAG, TAG);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onAverageElectricConsumptionChanged(int i7, float f10) {
        Log.v(TAG, "onAverageElectricConsumptionChanged");
    }

    public void onAverageFuelConsumptionChanged(int i7, float f10) {
        Log.v(TAG, "onAverageFuelConsumptionChanged");
    }

    public void onAverageSpeedChanged(int i7) {
        Log.v(TAG, "onAverageSpeedChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDrivingRangeValueChanged(int i7) {
        Log.v(TAG, "onDrivingRangeValueChanged");
    }

    public void onDrivingTimeChanged(double d) {
        Log.v(TAG, "onDrivingTimeChanged");
    }

    public void onEVDrivingMileageConfigChanged(int i7) {
        Log.v(TAG, "onEVDrivingMileageConfigChanged");
    }

    public void onEVDrivingMileageModeChanged(int i7) {
        Log.v(TAG, "onEVDrivingMileageModeChanged");
    }

    public void onEVMileageValueChanged(int i7) {
        Log.v(TAG, "onEVMileageValueChanged");
    }

    public void onEVRemainingBatteryPowerChanged(float f10) {
        Log.v(TAG, "onEVRemainingBatteryPowerChanged");
    }

    public void onElecDrivingRangeChanged(int i7) {
        Log.v(TAG, "onElecDrivingRangeChanged");
    }

    public void onElecPercentageChanged(double d) {
        Log.v(TAG, "onElecPercentageChanged");
    }

    public void onElectricConsumptionChanged(int i7, float f10) {
        Log.v(TAG, "onElectricConsumptionChanged");
    }

    public void onError(int i7, String str) {
        Log.v(TAG, "onError");
    }

    public void onEvCurveFlagChanged(int i7) {
        Log.v(TAG, "onEvCurveFlagChanged");
    }

    public void onEvLast200MChanged(int i7) {
        Log.v(TAG, "onEvLast200MChanged");
    }

    public void onFuelADValueChanged(int i7) {
        Log.v(TAG, "onFuelADValueChanged");
    }

    public void onFuelConsumptionChanged(int i7, float f10) {
        Log.v(TAG, "onFuelConsumptionChanged");
    }

    public void onFuelCurveFlagChanged(int i7) {
        Log.v(TAG, "onFuelCurveFlagChanged");
    }

    public void onFuelDrivingRangeChanged(int i7) {
        Log.v(TAG, "onFuelDrivingRangeChanged");
    }

    public void onFuelLast200MChanged(int i7) {
        Log.v(TAG, "onFuelLast200MChanged");
    }

    public void onFuelPercentageChanged(int i7) {
        Log.v(TAG, "onFuelPercentageChanged");
    }

    public void onHEVMileageValueChanged(int i7) {
        Log.v(TAG, "onHEVMileageValueChanged");
    }

    public void onInstantElecConChanged(float f10) {
        Log.v(TAG, "onInstantElecConChanged");
    }

    public void onInstantFuelConChanged(float f10) {
        Log.v(TAG, "onInstantFuelConChanged");
    }

    public void onKeyBatteryLevelChanged(int i7) {
        Log.v(TAG, "onKeyBatteryLevelChanged");
    }

    public void onLast50KmEqualFuelConValueChanged(float f10) {
        Log.v(TAG, "onLast50KmEqualFuelConValueChanged");
    }

    public void onLastElecConPHMChanged(double d) {
        Log.v(TAG, "onLastElecConPHMChanged");
    }

    public void onLastFuelConPHMChanged(double d) {
        Log.v(TAG, "onLastFuelConPHMChanged");
    }

    public void onMessage5sOnlineStateChanged(int i7, int i10) {
        Log.v(TAG, "onMessage5sOnlineStateChanged");
    }

    public void onMileageNumberChanged(int i7, int i10) {
        Log.v(TAG, "onMileageNumberChanged");
    }

    public void onRemainingBatteryPowerChanged(float f10) {
        Log.v(TAG, "onRemainingBatteryPowerChanged");
    }

    public void onSOCBatteryPercentageChanged(int i7) {
        Log.v(TAG, "onSOCBatteryPercentageChanged");
    }

    public void onSpeedSignalVDisValueChanged(int i7) {
        Log.v(TAG, "onSpeedSignalVDisValueChanged");
    }

    public void onTotalElecConChanged(double d) {
        Log.v(TAG, "onTotalElecConChanged");
    }

    public void onTotalElecConPHMChanged(double d) {
        Log.v(TAG, "onTotalElecConPHMChanged");
    }

    public void onTotalElecConPhmUnitChanged(int i7) {
        Log.v(TAG, "onTotalElecConPhmUnitChanged");
    }

    public void onTotalFuelConChanged(double d) {
        Log.v(TAG, "onTotalFuelConChanged");
    }

    public void onTotalFuelConPHMChanged(double d) {
        Log.v(TAG, "onTotalFuelConPHMChanged");
    }

    public void onTotalFuelConsumptionUnitChanged(int i7) {
        Log.v(TAG, "onTotalFuelConsumptionUnitChanged");
    }

    public void onTotalMileageValueChanged(float f10) {
        Log.v(TAG, "onTotalMileageValueChanged");
    }

    public void onTravelTimeChanged(int i7, float f10) {
        Log.v(TAG, "onTravelTimeChanged");
    }

    public void onWaterTemperatureChanged(int i7) {
        Log.v(TAG, "onWaterTemperatureChanged");
    }

    public void ontKeyBatteryLevelChanged(int i7) {
        Log.v(TAG, "ontKeyBatteryLevelChanged");
    }
}
