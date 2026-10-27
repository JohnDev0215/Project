package android.hardware.bydauto.statistic;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoStatisticListener implements d {
    public static final String TAG = "AbsBYDAutoStatisticListener";

    public AbsBYDAutoStatisticListener() {
        Log.v(TAG, TAG);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onAverageElectricConsumptionChanged(int i8, float f8) {
        Log.v(TAG, "onAverageElectricConsumptionChanged");
    }

    public void onAverageFuelConsumptionChanged(int i8, float f8) {
        Log.v(TAG, "onAverageFuelConsumptionChanged");
    }

    public void onAverageSpeedChanged(int i8) {
        Log.v(TAG, "onAverageSpeedChanged");
    }

    public void onCurrentTripElecChanged(float f8) {
        Log.v(TAG, "onCurrentTripElecChanged state is " + f8);
    }

    public void onCurrentTripElecUnitChanged(int i8) {
        Log.v(TAG, "onCurrentTripElecUnitChanged state is " + i8);
    }

    public void onCurrentTripFuelChanged(float f8) {
        Log.v(TAG, "onCurrentTripFuelChanged state is " + f8);
    }

    public void onCurrentTripFuelUnitChanged(int i8) {
        Log.v(TAG, "onCurrentTripFuelUnitChanged state is " + i8);
    }

    public void onCurrentTripMileageChanged(float f8) {
        Log.v(TAG, "onCurrentTripMileageChanged state is " + f8);
    }

    public void onCurrentTripMileageUnitChanged(int i8) {
        Log.v(TAG, "onCurrentTripMileageUnitChanged state is " + i8);
    }

    public void onCurrentTripTimeChanged(int i8) {
        Log.v(TAG, "onCurrentTripTimeChanged state is " + i8);
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDrivingRangeAllChanged(int i8) {
        Log.v(TAG, "onDrivingRangeAllChanged");
    }

    public void onDrivingRangeUnitChanged(int i8) {
        Log.v(TAG, "onDrivingRangeUnitChanged");
    }

    public void onDrivingRangeValueChanged(int i8) {
        Log.v(TAG, "onDrivingRangeValueChanged");
    }

    public void onDrivingTimeChanged(double d8) {
        Log.v(TAG, "onDrivingTimeChanged");
    }

    public void onEVDrivingMileageConfigChanged(int i8) {
        Log.v(TAG, "onEVDrivingMileageConfigChanged");
    }

    public void onEVDrivingMileageModeChanged(int i8) {
        Log.v(TAG, "onEVDrivingMileageModeChanged");
    }

    public void onEVMileageValueChanged(int i8) {
        Log.v(TAG, "onEVMileageValueChanged");
    }

    public void onEVRemainingBatteryPowerChanged(float f8) {
        Log.v(TAG, "onEVRemainingBatteryPowerChanged");
    }

    public void onElecConsUnitChanged(int i8) {
        Log.v(TAG, "onElecConsUnitChanged state is " + i8);
    }

    public void onElecDrivingRangeChanged(int i8) {
        Log.v(TAG, "onElecDrivingRangeChanged");
    }

    public void onElecPercentageChanged(double d8) {
        Log.v(TAG, "onElecPercentageChanged");
    }

    public void onElecUnitChanged(int i8) {
        Log.v(TAG, "onElecUnitChanged state is " + i8);
    }

    public void onElectricConsumptionChanged(int i8, float f8) {
        Log.v(TAG, "onElectricConsumptionChanged");
    }

    public void onError(int i8, String str) {
        Log.v(TAG, "onError");
    }

    public void onEvCurveFlagChanged(int i8) {
        Log.v(TAG, "onEvCurveFlagChanged");
    }

    public void onEvLast200MChanged(int i8) {
        Log.v(TAG, "onEvLast200MChanged");
    }

    public void onFuelADValueChanged(int i8) {
        Log.v(TAG, "onFuelADValueChanged");
    }

    public void onFuelConsumptionChanged(int i8, float f8) {
        Log.v(TAG, "onFuelConsumptionChanged");
    }

    public void onFuelCurveFlagChanged(int i8) {
        Log.v(TAG, "onFuelCurveFlagChanged");
    }

    public void onFuelDrivingRangeChanged(int i8) {
        Log.v(TAG, "onFuelDrivingRangeChanged");
    }

    public void onFuelLast200MChanged(int i8) {
        Log.v(TAG, "onFuelLast200MChanged");
    }

    public void onFuelPercentageChanged(int i8) {
        Log.v(TAG, "onFuelPercentageChanged");
    }

    public void onHEVMileageValueChanged(int i8) {
        Log.v(TAG, "onHEVMileageValueChanged");
    }

    public void onInstantElecConChanged(float f8) {
        Log.v(TAG, "onInstantElecConChanged");
    }

    public void onInstantFuelConChanged(float f8) {
        Log.v(TAG, "onInstantFuelConChanged");
    }

    public void onKeyBatteryLevelChanged(int i8) {
        Log.v(TAG, "onKeyBatteryLevelChanged");
    }

    public void onLast50KmEqualFuelConValueChanged(float f8) {
        Log.v(TAG, "onLast50KmEqualFuelConValueChanged");
    }

    public void onLastElecConPHMChanged(double d8) {
        Log.v(TAG, "onLastElecConPHMChanged");
    }

    public void onLastFuelConPHMChanged(double d8) {
        Log.v(TAG, "onLastFuelConPHMChanged");
    }

    public void onMessage5sOnlineStateChanged(int i8, int i9) {
        Log.v(TAG, "onMessage5sOnlineStateChanged");
    }

    public void onMileageNumberChanged(int i8, float f8) {
        Log.v(TAG, "onMileageNumberChanged");
    }

    public void onRemainingBatteryPowerChanged(float f8) {
        Log.v(TAG, "onRemainingBatteryPowerChanged");
    }

    public void onSOCBatteryPercentageChanged(int i8) {
        Log.v(TAG, "onSOCBatteryPercentageChanged");
    }

    public void onSpeedSignalVDisValueChanged(int i8) {
        Log.v(TAG, "onSpeedSignalVDisValueChanged");
    }

    public void onSpeedUnitChanged(int i8) {
        Log.v(TAG, "onSpeedUnitChanged state is " + i8);
    }

    public void onTotalElecConChanged(double d8) {
        Log.v(TAG, "onTotalElecConChanged");
    }

    public void onTotalElecConPHMChanged(double d8) {
        Log.v(TAG, "onTotalElecConPHMChanged");
    }

    public void onTotalElecConPhmUnitChanged(int i8) {
        Log.v(TAG, "onTotalElecConPhmUnitChanged");
    }

    public void onTotalFuelConChanged(double d8) {
        Log.v(TAG, "onTotalFuelConChanged");
    }

    public void onTotalFuelConPHMChanged(double d8) {
        Log.v(TAG, "onTotalFuelConPHMChanged");
    }

    public void onTotalFuelConsumptionUnitChanged(int i8) {
        Log.v(TAG, "onTotalFuelConsumptionUnitChanged");
    }

    public void onTotalMileageValueChanged(float f8) {
        Log.v(TAG, "onTotalMileageValueChanged");
    }

    public void onTravelTimeChanged(int i8, float f8) {
        Log.v(TAG, "onTravelTimeChanged");
    }

    public void onWaterTemperatureChanged(int i8) {
        Log.v(TAG, "onWaterTemperatureChanged");
    }

    public void ontKeyBatteryLevelChanged(int i8) {
        Log.v(TAG, "ontKeyBatteryLevelChanged");
    }
}
