package android.hardware.bydauto.statistic;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoStatisticListener implements d {
    protected static final String TAG = "AbsBYDAutoStatisticListener";

    public AbsBYDAutoStatisticListener() {
        Log.v(TAG, TAG);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    public void onAverageElectricConsumptionChanged(int i, float f) {
        Log.v(TAG, "onAverageElectricConsumptionChanged");
    }

    public void onAverageFuelConsumptionChanged(int i, float f) {
        Log.v(TAG, "onAverageFuelConsumptionChanged");
    }

    public void onAverageSpeedChanged(int i) {
        Log.v(TAG, "onAverageSpeedChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDrivingRangeValueChanged(int i) {
        Log.v(TAG, "onDrivingRangeValueChanged");
    }

    public void onDrivingTimeChanged(double d) {
        Log.v(TAG, "onDrivingTimeChanged");
    }

    public void onEVDrivingMileageConfigChanged(int i) {
        Log.v(TAG, "onEVDrivingMileageConfigChanged");
    }

    public void onEVDrivingMileageModeChanged(int i) {
        Log.v(TAG, "onEVDrivingMileageModeChanged");
    }

    public void onEVMileageValueChanged(int i) {
        Log.v(TAG, "onEVMileageValueChanged");
    }

    public void onEVRemainingBatteryPowerChanged(float f) {
        Log.v(TAG, "onEVRemainingBatteryPowerChanged");
    }

    public void onElecDrivingRangeChanged(int i) {
        Log.v(TAG, "onElecDrivingRangeChanged");
    }

    public void onElecPercentageChanged(double d) {
        Log.v(TAG, "onElecPercentageChanged");
    }

    public void onElectricConsumptionChanged(int i, float f) {
        Log.v(TAG, "onElectricConsumptionChanged");
    }

    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    public void onEvCurveFlagChanged(int i) {
        Log.v(TAG, "onEvCurveFlagChanged");
    }

    public void onEvLast200MChanged(int i) {
        Log.v(TAG, "onEvLast200MChanged");
    }

    public void onFuelADValueChanged(int i) {
        Log.v(TAG, "onFuelADValueChanged");
    }

    public void onFuelConsumptionChanged(int i, float f) {
        Log.v(TAG, "onFuelConsumptionChanged");
    }

    public void onFuelCurveFlagChanged(int i) {
        Log.v(TAG, "onFuelCurveFlagChanged");
    }

    public void onFuelDrivingRangeChanged(int i) {
        Log.v(TAG, "onFuelDrivingRangeChanged");
    }

    public void onFuelLast200MChanged(int i) {
        Log.v(TAG, "onFuelLast200MChanged");
    }

    public void onFuelPercentageChanged(int i) {
        Log.v(TAG, "onFuelPercentageChanged");
    }

    public void onHEVMileageValueChanged(int i) {
        Log.v(TAG, "onHEVMileageValueChanged");
    }

    public void onInstantElecConChanged(float f) {
        Log.v(TAG, "onInstantElecConChanged");
    }

    public void onInstantFuelConChanged(float f) {
        Log.v(TAG, "onInstantFuelConChanged");
    }

    public void onKeyBatteryLevelChanged(int i) {
        Log.v(TAG, "onKeyBatteryLevelChanged");
    }

    public void onLast50KmEqualFuelConValueChanged(float f) {
        Log.v(TAG, "onLast50KmEqualFuelConValueChanged");
    }

    public void onLastElecConPHMChanged(double d) {
        Log.v(TAG, "onLastElecConPHMChanged");
    }

    public void onLastFuelConPHMChanged(double d) {
        Log.v(TAG, "onLastFuelConPHMChanged");
    }

    public void onMessage5sOnlineStateChanged(int i, int i2) {
        Log.v(TAG, "onMessage5sOnlineStateChanged");
    }

    public void onMileageNumberChanged(int i, int i2) {
        Log.v(TAG, "onMileageNumberChanged");
    }

    public void onRemainingBatteryPowerChanged(float f) {
        Log.v(TAG, "onRemainingBatteryPowerChanged");
    }

    public void onSOCBatteryPercentageChanged(int i) {
        Log.v(TAG, "onSOCBatteryPercentageChanged");
    }

    public void onSpeedSignalVDisValueChanged(int i) {
        Log.v(TAG, "onSpeedSignalVDisValueChanged");
    }

    public void onTotalElecConChanged(double d) {
        Log.v(TAG, "onTotalElecConChanged");
    }

    public void onTotalElecConPHMChanged(double d) {
        Log.v(TAG, "onTotalElecConPHMChanged");
    }

    public void onTotalElecConPhmUnitChanged(int i) {
        Log.v(TAG, "onTotalElecConPhmUnitChanged");
    }

    public void onTotalFuelConChanged(double d) {
        Log.v(TAG, "onTotalFuelConChanged");
    }

    public void onTotalFuelConPHMChanged(double d) {
        Log.v(TAG, "onTotalFuelConPHMChanged");
    }

    public void onTotalFuelConsumptionUnitChanged(int i) {
        Log.v(TAG, "onTotalFuelConsumptionUnitChanged");
    }

    public void onTotalMileageValueChanged(float f) {
        Log.v(TAG, "onTotalMileageValueChanged");
    }

    public void onTravelTimeChanged(int i, float f) {
        Log.v(TAG, "onTravelTimeChanged");
    }

    public void onWaterTemperatureChanged(int i) {
        Log.v(TAG, "onWaterTemperatureChanged");
    }

    public void ontKeyBatteryLevelChanged(int i) {
        Log.v(TAG, "ontKeyBatteryLevelChanged");
    }
}
