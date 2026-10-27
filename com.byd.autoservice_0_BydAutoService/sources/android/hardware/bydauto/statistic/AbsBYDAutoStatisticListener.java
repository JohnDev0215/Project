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
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onAverageElectricConsumptionChanged(int i3, float f3) {
        Log.v(TAG, "onAverageElectricConsumptionChanged");
    }

    public void onAverageFuelConsumptionChanged(int i3, float f3) {
        Log.v(TAG, "onAverageFuelConsumptionChanged");
    }

    public void onAverageSpeedChanged(int i3) {
        Log.v(TAG, "onAverageSpeedChanged");
    }

    public void onCurrentTripElecChanged(float f3) {
        Log.v(TAG, "onCurrentTripElecChanged state is " + f3);
    }

    public void onCurrentTripElecUnitChanged(int i3) {
        Log.v(TAG, "onCurrentTripElecUnitChanged state is " + i3);
    }

    public void onCurrentTripFuelChanged(float f3) {
        Log.v(TAG, "onCurrentTripFuelChanged state is " + f3);
    }

    public void onCurrentTripFuelUnitChanged(int i3) {
        Log.v(TAG, "onCurrentTripFuelUnitChanged state is " + i3);
    }

    public void onCurrentTripMileageChanged(float f3) {
        Log.v(TAG, "onCurrentTripMileageChanged state is " + f3);
    }

    public void onCurrentTripMileageUnitChanged(int i3) {
        Log.v(TAG, "onCurrentTripMileageUnitChanged state is " + i3);
    }

    public void onCurrentTripTimeChanged(int i3) {
        Log.v(TAG, "onCurrentTripTimeChanged state is " + i3);
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDrivingRangeAllChanged(int i3) {
        Log.v(TAG, "onDrivingRangeAllChanged");
    }

    public void onDrivingRangeUnitChanged(int i3) {
        Log.v(TAG, "onDrivingRangeUnitChanged");
    }

    public void onDrivingRangeValueChanged(int i3) {
        Log.v(TAG, "onDrivingRangeValueChanged");
    }

    public void onDrivingTimeChanged(double d3) {
        Log.v(TAG, "onDrivingTimeChanged");
    }

    public void onEVDrivingMileageConfigChanged(int i3) {
        Log.v(TAG, "onEVDrivingMileageConfigChanged");
    }

    public void onEVDrivingMileageModeChanged(int i3) {
        Log.v(TAG, "onEVDrivingMileageModeChanged");
    }

    public void onEVMileageValueChanged(int i3) {
        Log.v(TAG, "onEVMileageValueChanged");
    }

    public void onEVRemainingBatteryPowerChanged(float f3) {
        Log.v(TAG, "onEVRemainingBatteryPowerChanged");
    }

    public void onElecConsUnitChanged(int i3) {
        Log.v(TAG, "onElecConsUnitChanged state is " + i3);
    }

    public void onElecDrivingRangeChanged(int i3) {
        Log.v(TAG, "onElecDrivingRangeChanged");
    }

    public void onElecPercentageChanged(double d3) {
        Log.v(TAG, "onElecPercentageChanged");
    }

    public void onElecUnitChanged(int i3) {
        Log.v(TAG, "onElecUnitChanged state is " + i3);
    }

    public void onElectricConsumptionChanged(int i3, float f3) {
        Log.v(TAG, "onElectricConsumptionChanged");
    }

    public void onError(int i3, String str) {
        Log.v(TAG, "onError");
    }

    public void onEvCurveFlagChanged(int i3) {
        Log.v(TAG, "onEvCurveFlagChanged");
    }

    public void onEvLast200MChanged(int i3) {
        Log.v(TAG, "onEvLast200MChanged");
    }

    public void onFuelADValueChanged(int i3) {
        Log.v(TAG, "onFuelADValueChanged");
    }

    public void onFuelConsumptionChanged(int i3, float f3) {
        Log.v(TAG, "onFuelConsumptionChanged");
    }

    public void onFuelCurveFlagChanged(int i3) {
        Log.v(TAG, "onFuelCurveFlagChanged");
    }

    public void onFuelDrivingRangeChanged(int i3) {
        Log.v(TAG, "onFuelDrivingRangeChanged");
    }

    public void onFuelLast200MChanged(int i3) {
        Log.v(TAG, "onFuelLast200MChanged");
    }

    public void onFuelPercentageChanged(int i3) {
        Log.v(TAG, "onFuelPercentageChanged");
    }

    public void onHEVMileageValueChanged(int i3) {
        Log.v(TAG, "onHEVMileageValueChanged");
    }

    public void onInstantElecConChanged(float f3) {
        Log.v(TAG, "onInstantElecConChanged");
    }

    public void onInstantFuelConChanged(float f3) {
        Log.v(TAG, "onInstantFuelConChanged");
    }

    public void onKeyBatteryLevelChanged(int i3) {
        Log.v(TAG, "onKeyBatteryLevelChanged");
    }

    public void onLast50KmEqualFuelConValueChanged(float f3) {
        Log.v(TAG, "onLast50KmEqualFuelConValueChanged");
    }

    public void onLastElecConPHMChanged(double d3) {
        Log.v(TAG, "onLastElecConPHMChanged");
    }

    public void onLastFuelConPHMChanged(double d3) {
        Log.v(TAG, "onLastFuelConPHMChanged");
    }

    public void onMessage5sOnlineStateChanged(int i3, int i4) {
        Log.v(TAG, "onMessage5sOnlineStateChanged");
    }

    public void onMileageNumberChanged(int i3, float f3) {
        Log.v(TAG, "onMileageNumberChanged");
    }

    public void onRemainingBatteryPowerChanged(float f3) {
        Log.v(TAG, "onRemainingBatteryPowerChanged");
    }

    public void onSOCBatteryPercentageChanged(int i3) {
        Log.v(TAG, "onSOCBatteryPercentageChanged");
    }

    public void onSpeedSignalVDisValueChanged(int i3) {
        Log.v(TAG, "onSpeedSignalVDisValueChanged");
    }

    public void onSpeedUnitChanged(int i3) {
        Log.v(TAG, "onSpeedUnitChanged state is " + i3);
    }

    public void onTotalElecConChanged(double d3) {
        Log.v(TAG, "onTotalElecConChanged");
    }

    public void onTotalElecConPHMChanged(double d3) {
        Log.v(TAG, "onTotalElecConPHMChanged");
    }

    public void onTotalElecConPhmUnitChanged(int i3) {
        Log.v(TAG, "onTotalElecConPhmUnitChanged");
    }

    public void onTotalFuelConChanged(double d3) {
        Log.v(TAG, "onTotalFuelConChanged");
    }

    public void onTotalFuelConPHMChanged(double d3) {
        Log.v(TAG, "onTotalFuelConPHMChanged");
    }

    public void onTotalFuelConsumptionUnitChanged(int i3) {
        Log.v(TAG, "onTotalFuelConsumptionUnitChanged");
    }

    public void onTotalMileageValueChanged(float f3) {
        Log.v(TAG, "onTotalMileageValueChanged");
    }

    public void onTravelTimeChanged(int i3, float f3) {
        Log.v(TAG, "onTravelTimeChanged");
    }

    public void onWaterTemperatureChanged(int i3) {
        Log.v(TAG, "onWaterTemperatureChanged");
    }

    public void ontKeyBatteryLevelChanged(int i3) {
        Log.v(TAG, "ontKeyBatteryLevelChanged");
    }
}
