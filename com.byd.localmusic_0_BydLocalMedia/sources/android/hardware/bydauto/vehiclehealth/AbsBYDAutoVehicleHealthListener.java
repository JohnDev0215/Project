package android.hardware.bydauto.vehiclehealth;

import android.hardware.IBYDAutoListener;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsBYDAutoVehicleHealthListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDHealthListener";

    public void onOilLifetimeChanged(int i) {
        Log.v(TAG, "onOilLifetimeChanged");
    }

    public void onOilLevelChanged(int i) {
        Log.v(TAG, "onOilLevelChanged");
    }

    public void onFrontWiperCheckStateChanged(int i) {
        Log.v(TAG, "onFrontWiperCheckStateChanged");
    }

    public void onRearWiperCheckStateChanged(int i) {
        Log.v(TAG, "onRearWiperCheckStateChanged");
    }

    @Deprecated
    public void onResetTirePressureStateChanged(int i) {
        Log.v(TAG, "onResetTirePressureStateChanged");
    }

    public void onOilLifetimeChanged(int i, int i2) {
        Log.v(TAG, "onOilLifetimeChanged");
    }

    public void onResetOilLifetimeChanged(int i, int i2) {
        Log.v(TAG, "onResetOilLifetimeChanged");
    }

    public void onEPBStateChanged(int i) {
        Log.v(TAG, "onEPBStateChanged");
    }

    public void onOilUpdateStateChanged(int i) {
        Log.v(TAG, "onOilUpdateStateChanged");
    }

    public void onOilMaintainanceHintChanged(int i, int i2) {
        Log.v(TAG, "onOilMaintainanceHintChanged");
    }

    public void onTransportModeChanged(int i) {
        Log.v(TAG, "onTransportModeChanged");
    }

    public void onTransportModeStateHintChanged(int i) {
        Log.v(TAG, "onTransportModeStateHintChanged");
    }

    public void onEPBHintChanged(int i) {
        Log.v(TAG, "onEPBHintChanged state = " + i);
    }

    public void onOilLifetimeNewChanged(int i) {
        Log.v(TAG, "onOilLifetimeNewChanged");
    }

    public void onOilMaintainanceDialogHintChanged(int i) {
        Log.v(TAG, "onOilMaintainanceDialogHintChanged");
    }

    public void onBatteryHealthStatusChanged(int i) {
        Log.v(TAG, "onBatteryHealthStatusChanged value = " + i);
    }
}
