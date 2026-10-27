package android.hardware.bydauto.statistic;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoStatisticListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoStatisticListener";

    public AbsBYDAutoStatisticListener() {
        Log.v(TAG, TAG);
    }

    public void onTotalMileageValueChanged(float value) {
        Log.v(TAG, "onTotalMileageValueChanged");
    }

    public void onTotalFuelConChanged(double value) {
        Log.v(TAG, "onTotalFuelConChanged");
    }

    public void onTotalElecConChanged(double value) {
        Log.v(TAG, "onTotalElecConChanged");
    }

    public void onDrivingTimeChanged(double value) {
        Log.v(TAG, "onDrivingTimeChanged");
    }

    public void onLastFuelConPHMChanged(double value) {
        Log.v(TAG, "onLastFuelConPHMChanged");
    }

    public void onTotalFuelConPHMChanged(double value) {
        Log.v(TAG, "onTotalFuelConPHMChanged");
    }

    public void onLastElecConPHMChanged(double value) {
        Log.v(TAG, "onLastElecConPHMChanged");
    }

    public void onTotalElecConPHMChanged(double value) {
        Log.v(TAG, "onTotalElecConPHMChanged");
    }

    public void onElecDrivingRangeChanged(int value) {
        Log.v(TAG, "onElecDrivingRangeChanged");
    }

    public void onFuelDrivingRangeChanged(int value) {
        Log.v(TAG, "onFuelDrivingRangeChanged");
    }

    public void onFuelPercentageChanged(int value) {
        Log.v(TAG, "onFuelPercentageChanged");
    }

    public void onElecPercentageChanged(double value) {
        Log.v(TAG, "onElecPercentageChanged");
    }

    public void onFuelConsumptionChanged(int target, float value) {
        Log.v(TAG, "onFuelConsumptionChanged");
    }

    public void onElectricConsumptionChanged(int target, float value) {
        Log.v(TAG, "onElectricConsumptionChanged");
    }

    public void onAverageFuelConsumptionChanged(int target, float value) {
        Log.v(TAG, "onAverageFuelConsumptionChanged");
    }

    public void onAverageElectricConsumptionChanged(int target, float value) {
        Log.v(TAG, "onAverageElectricConsumptionChanged");
    }

    public void onTravelTimeChanged(int target, float value) {
        Log.v(TAG, "onTravelTimeChanged");
    }

    public void onAverageSpeedChanged(int value) {
        Log.v(TAG, "onAverageSpeedChanged");
    }

    public void onSpeedSignalVDisValueChanged(int value) {
        Log.v(TAG, "onSpeedSignalVDisValueChanged");
    }

    public void onFuelADValueChanged(int value) {
        Log.v(TAG, "onFuelADValueChanged");
    }

    public void onKeyBatteryLevelChanged(int value) {
        Log.v(TAG, "onKeyBatteryLevelChanged");
    }

    public void onSOCBatteryPercentageChanged(int value) {
        Log.v(TAG, "onSOCBatteryPercentageChanged");
    }

    public void onEVMileageValueChanged(int value) {
        Log.v(TAG, "onEVMileageValueChanged");
    }

    public void onHEVMileageValueChanged(int value) {
        Log.v(TAG, "onHEVMileageValueChanged");
    }

    public void onWaterTemperatureChanged(int value) {
        Log.v(TAG, "onWaterTemperatureChanged");
    }

    public void onEVDrivingMileageModeChanged(int mode) {
        Log.v(TAG, "onEVDrivingMileageModeChanged");
    }

    public void onEVDrivingMileageConfigChanged(int config) {
        Log.v(TAG, "onEVDrivingMileageConfigChanged");
    }

    public void onInstantElecConChanged(float value) {
        Log.v(TAG, "onInstantElecConChanged");
    }

    public void onInstantFuelConChanged(float value) {
        Log.v(TAG, "onInstantFuelConChanged");
    }

    public void onTotalElecConPhmUnitChanged(int value) {
        Log.v(TAG, "onTotalElecConPhmUnitChanged");
    }

    public void onMessage5sOnlineStateChanged(int id, int state) {
        Log.v(TAG, "onMessage5sOnlineStateChanged");
    }

    public void onDrivingRangeValueChanged(int value) {
        Log.v(TAG, "onDrivingRangeValueChanged");
    }

    public void onEvLast200MChanged(int value) {
        Log.v(TAG, "onEvLast200MChanged");
    }

    public void onFuelLast200MChanged(int value) {
        Log.v(TAG, "onFuelLast200MChanged");
    }

    public void onEvCurveFlagChanged(int state) {
        Log.v(TAG, "onEvCurveFlagChanged");
    }

    public void onFuelCurveFlagChanged(int state) {
        Log.v(TAG, "onFuelCurveFlagChanged");
    }

    public void onMileageNumberChanged(int status, float value) {
        Log.v(TAG, "onMileageNumberChanged");
    }

    public void onLast50KmEqualFuelConValueChanged(float value) {
        Log.v(TAG, "onLast50KmEqualFuelConValueChanged");
    }

    public void onTotalFuelConsumptionUnitChanged(int value) {
        Log.v(TAG, "onTotalFuelConsumptionUnitChanged");
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

    public void ontKeyBatteryLevelChanged(int value) {
        Log.v(TAG, "ontKeyBatteryLevelChanged");
    }

    public void onRemainingBatteryPowerChanged(float value) {
        Log.v(TAG, "onRemainingBatteryPowerChanged");
    }

    public void onEVRemainingBatteryPowerChanged(float value) {
        Log.v(TAG, "onEVRemainingBatteryPowerChanged");
    }

    public void onDrivingRangeAllChanged(int value) {
        Log.v(TAG, "onDrivingRangeAllChanged");
    }

    public void onDrivingRangeUnitChanged(int value) {
        Log.v(TAG, "onDrivingRangeUnitChanged");
    }

    public void onCurrentTripMileageChanged(float value) {
        Log.v(TAG, "onCurrentTripMileageChanged state is " + value);
    }

    public void onCurrentTripMileageUnitChanged(int value) {
        Log.v(TAG, "onCurrentTripMileageUnitChanged state is " + value);
    }

    public void onCurrentTripTimeChanged(int value) {
        Log.v(TAG, "onCurrentTripTimeChanged state is " + value);
    }

    public void onCurrentTripElecChanged(float value) {
        Log.v(TAG, "onCurrentTripElecChanged state is " + value);
    }

    public void onCurrentTripElecUnitChanged(int value) {
        Log.v(TAG, "onCurrentTripElecUnitChanged state is " + value);
    }

    public void onCurrentTripFuelChanged(float value) {
        Log.v(TAG, "onCurrentTripFuelChanged state is " + value);
    }

    public void onCurrentTripFuelUnitChanged(int value) {
        Log.v(TAG, "onCurrentTripFuelUnitChanged state is " + value);
    }

    public void onElecConsUnitChanged(int value) {
        Log.v(TAG, "onElecConsUnitChanged state is " + value);
    }

    public void onSpeedUnitChanged(int value) {
        Log.v(TAG, "onSpeedUnitChanged state is " + value);
    }

    public void onElecUnitChanged(int value) {
        Log.v(TAG, "onElecUnitChanged state is " + value);
    }
}
