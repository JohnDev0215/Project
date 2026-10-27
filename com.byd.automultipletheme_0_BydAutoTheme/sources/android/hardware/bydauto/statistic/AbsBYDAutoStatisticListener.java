package android.hardware.bydauto.statistic;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoStatisticListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoStatisticListener";

    @Override // android.hardware.IBYDAutoListener
    public /* synthetic */ void isConnected(boolean z) {
        IBYDAutoListener.CC.$default$isConnected(this, z);
    }

    public AbsBYDAutoStatisticListener() {
        Log.v(TAG, TAG);
    }

    public void onTotalMileageValueChanged(float f) {
        Log.v(TAG, "onTotalMileageValueChanged");
    }

    public void onTotalFuelConChanged(double d) {
        Log.v(TAG, "onTotalFuelConChanged");
    }

    public void onTotalElecConChanged(double d) {
        Log.v(TAG, "onTotalElecConChanged");
    }

    public void onDrivingTimeChanged(double d) {
        Log.v(TAG, "onDrivingTimeChanged");
    }

    public void onLastFuelConPHMChanged(double d) {
        Log.v(TAG, "onLastFuelConPHMChanged");
    }

    public void onTotalFuelConPHMChanged(double d) {
        Log.v(TAG, "onTotalFuelConPHMChanged");
    }

    public void onLastElecConPHMChanged(double d) {
        Log.v(TAG, "onLastElecConPHMChanged");
    }

    public void onTotalElecConPHMChanged(double d) {
        Log.v(TAG, "onTotalElecConPHMChanged");
    }

    public void onElecDrivingRangeChanged(int i) {
        Log.v(TAG, "onElecDrivingRangeChanged");
    }

    public void onFuelDrivingRangeChanged(int i) {
        Log.v(TAG, "onFuelDrivingRangeChanged");
    }

    public void onFuelPercentageChanged(int i) {
        Log.v(TAG, "onFuelPercentageChanged");
    }

    public void onElecPercentageChanged(double d) {
        Log.v(TAG, "onElecPercentageChanged");
    }

    public void onFuelConsumptionChanged(int i, float f) {
        Log.v(TAG, "onFuelConsumptionChanged");
    }

    public void onElectricConsumptionChanged(int i, float f) {
        Log.v(TAG, "onElectricConsumptionChanged");
    }

    public void onAverageFuelConsumptionChanged(int i, float f) {
        Log.v(TAG, "onAverageFuelConsumptionChanged");
    }

    public void onAverageElectricConsumptionChanged(int i, float f) {
        Log.v(TAG, "onAverageElectricConsumptionChanged");
    }

    public void onTravelTimeChanged(int i, float f) {
        Log.v(TAG, "onTravelTimeChanged");
    }

    public void onAverageSpeedChanged(int i) {
        Log.v(TAG, "onAverageSpeedChanged");
    }

    public void onSpeedSignalVDisValueChanged(int i) {
        Log.v(TAG, "onSpeedSignalVDisValueChanged");
    }

    public void onFuelADValueChanged(int i) {
        Log.v(TAG, "onFuelADValueChanged");
    }

    public void onKeyBatteryLevelChanged(int i) {
        Log.v(TAG, "onKeyBatteryLevelChanged");
    }

    public void onSOCBatteryPercentageChanged(int i) {
        Log.v(TAG, "onSOCBatteryPercentageChanged");
    }

    public void onEVMileageValueChanged(int i) {
        Log.v(TAG, "onEVMileageValueChanged");
    }

    public void onHEVMileageValueChanged(int i) {
        Log.v(TAG, "onHEVMileageValueChanged");
    }

    public void onWaterTemperatureChanged(int i) {
        Log.v(TAG, "onWaterTemperatureChanged");
    }

    public void onEVDrivingMileageModeChanged(int i) {
        Log.v(TAG, "onEVDrivingMileageModeChanged");
    }

    public void onEVDrivingMileageConfigChanged(int i) {
        Log.v(TAG, "onEVDrivingMileageConfigChanged");
    }

    public void onInstantElecConChanged(float f) {
        Log.v(TAG, "onInstantElecConChanged");
    }

    public void onInstantFuelConChanged(float f) {
        Log.v(TAG, "onInstantFuelConChanged");
    }

    public void onTotalElecConPhmUnitChanged(int i) {
        Log.v(TAG, "onTotalElecConPhmUnitChanged");
    }

    public void onMessage5sOnlineStateChanged(int i, int i2) {
        Log.v(TAG, "onMessage5sOnlineStateChanged");
    }

    public void onDrivingRangeValueChanged(int i) {
        Log.v(TAG, "onDrivingRangeValueChanged");
    }

    public void onEvLast200MChanged(int i) {
        Log.v(TAG, "onEvLast200MChanged");
    }

    public void onFuelLast200MChanged(int i) {
        Log.v(TAG, "onFuelLast200MChanged");
    }

    public void onEvCurveFlagChanged(int i) {
        Log.v(TAG, "onEvCurveFlagChanged");
    }

    public void onFuelCurveFlagChanged(int i) {
        Log.v(TAG, "onFuelCurveFlagChanged");
    }

    public void onMileageNumberChanged(int i, int i2) {
        Log.v(TAG, "onMileageNumberChanged");
    }

    public void onLast50KmEqualFuelConValueChanged(float f) {
        Log.v(TAG, "onLast50KmEqualFuelConValueChanged");
    }

    public void onTotalFuelConsumptionUnitChanged(int i) {
        Log.v(TAG, "onTotalFuelConsumptionUnitChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.v(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void ontKeyBatteryLevelChanged(int i) {
        Log.v(TAG, "ontKeyBatteryLevelChanged");
    }

    public void onRemainingBatteryPowerChanged(float f) {
        Log.v(TAG, "onRemainingBatteryPowerChanged");
    }

    public void onEVRemainingBatteryPowerChanged(float f) {
        Log.v(TAG, "onEVRemainingBatteryPowerChanged");
    }
}
