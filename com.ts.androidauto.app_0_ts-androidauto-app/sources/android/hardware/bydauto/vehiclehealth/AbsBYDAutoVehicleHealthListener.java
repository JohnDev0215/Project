package android.hardware.bydauto.vehiclehealth;

import android.hardware.IBYDAutoListener;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoVehicleHealthListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDHealthListener";

    public void onOilLifetimeChanged(int value) {
        Log.v(TAG, "onOilLifetimeChanged");
    }

    public void onOilLevelChanged(int value) {
        Log.v(TAG, "onOilLevelChanged");
    }

    public void onFrontWiperCheckStateChanged(int state) {
        Log.v(TAG, "onFrontWiperCheckStateChanged");
    }

    public void onRearWiperCheckStateChanged(int state) {
        Log.v(TAG, "onRearWiperCheckStateChanged");
    }

    @Deprecated
    public void onResetTirePressureStateChanged(int state) {
        Log.v(TAG, "onResetTirePressureStateChanged");
    }

    public void onOilLifetimeChanged(int type, int value) {
        Log.v(TAG, "onOilLifetimeChanged");
    }

    public void onResetOilLifetimeChanged(int type, int value) {
        Log.v(TAG, "onResetOilLifetimeChanged");
    }

    public void onEPBStateChanged(int state) {
        Log.v(TAG, "onEPBStateChanged");
    }

    public void onOilUpdateStateChanged(int value) {
        Log.v(TAG, "onOilUpdateStateChanged");
    }

    public void onOilMaintainanceHintChanged(int type, int value) {
        Log.v(TAG, "onOilMaintainanceHintChanged");
    }

    public void onTransportModeChanged(int state) {
        Log.v(TAG, "onTransportModeChanged");
    }

    public void onTransportModeStateHintChanged(int state) {
        Log.v(TAG, "onTransportModeStateHintChanged");
    }

    public void onEPBHintChanged(int state) {
        Log.v(TAG, "onEPBHintChanged state = " + state);
    }

    public void onOilLifetimeNewChanged(int state) {
        Log.v(TAG, "onOilLifetimeNewChanged");
    }

    public void onOilMaintainanceDialogHintChanged(int state) {
        Log.v(TAG, "onOilMaintainanceDialogHintChanged");
    }

    public void onBatteryHealthStatusChanged(int value) {
        Log.v(TAG, "onBatteryHealthStatusChanged value = " + value);
    }
}
