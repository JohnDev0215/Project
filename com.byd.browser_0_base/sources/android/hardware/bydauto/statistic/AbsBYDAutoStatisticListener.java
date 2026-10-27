package android.hardware.bydauto.statistic;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoStatisticListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoStatisticListener";

    public void onAverageSpeedChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onDrivingRangeAllChanged(int i) {
    }

    public void onDrivingRangeUnitChanged(int i) {
    }

    public void onDrivingRangeValueChanged(int i) {
    }

    public void onDrivingTimeChanged(double d) {
    }

    public void onEVDrivingMileageConfigChanged(int i) {
    }

    public void onEVDrivingMileageModeChanged(int i) {
    }

    public void onEVMileageValueChanged(int i) {
    }

    public void onEVRemainingBatteryPowerChanged(float f) {
    }

    public void onElecDrivingRangeChanged(int i) {
    }

    public void onElecPercentageChanged(double d) {
    }

    public void onEvCurveFlagChanged(int i) {
    }

    public void onEvLast200MChanged(int i) {
    }

    public void onFuelADValueChanged(int i) {
    }

    public void onFuelCurveFlagChanged(int i) {
    }

    public void onFuelDrivingRangeChanged(int i) {
    }

    public void onFuelLast200MChanged(int i) {
    }

    public void onFuelPercentageChanged(int i) {
    }

    public void onHEVMileageValueChanged(int i) {
    }

    public void onInstantElecConChanged(float f) {
    }

    public void onInstantFuelConChanged(float f) {
    }

    public void onKeyBatteryLevelChanged(int i) {
    }

    public void onLast50KmEqualFuelConValueChanged(float f) {
    }

    public void onLastElecConPHMChanged(double d) {
    }

    public void onLastFuelConPHMChanged(double d) {
    }

    public void onRemainingBatteryPowerChanged(float f) {
    }

    public void onSOCBatteryPercentageChanged(int i) {
    }

    public void onSpeedSignalVDisValueChanged(int i) {
    }

    public void onTotalElecConChanged(double d) {
    }

    public void onTotalElecConPHMChanged(double d) {
    }

    public void onTotalElecConPhmUnitChanged(int i) {
    }

    public void onTotalFuelConChanged(double d) {
    }

    public void onTotalFuelConPHMChanged(double d) {
    }

    public void onTotalFuelConsumptionUnitChanged(int i) {
    }

    public void onTotalMileageValueChanged(float f) {
    }

    public void onWaterTemperatureChanged(int i) {
    }

    public void ontKeyBatteryLevelChanged(int i) {
    }

    public void onAverageElectricConsumptionChanged(int i, float f) {
    }

    public void onAverageFuelConsumptionChanged(int i, float f) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onElectricConsumptionChanged(int i, float f) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onFuelConsumptionChanged(int i, float f) {
    }

    public void onMessage5sOnlineStateChanged(int i, int i2) {
    }

    public void onMileageNumberChanged(int i, float f) {
    }

    public void onTravelTimeChanged(int i, float f) {
    }
}
