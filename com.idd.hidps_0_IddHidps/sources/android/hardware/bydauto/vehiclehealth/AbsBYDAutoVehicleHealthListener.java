package android.hardware.bydauto.vehiclehealth;

import android.hardware.IBYDAutoListener;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoVehicleHealthListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDHealthListener";

    public void onEPBStateChanged(int i) {
        Log.v(TAG, "onEPBStateChanged");
    }

    public void onFrontWiperCheckStateChanged(int i) {
        Log.v(TAG, "onFrontWiperCheckStateChanged");
    }

    public void onOilLevelChanged(int i) {
        Log.v(TAG, "onOilLevelChanged");
    }

    public void onOilLifetimeChanged(int i) {
        Log.v(TAG, "onOilLifetimeChanged");
    }

    public void onOilMaintainanceHintChanged(int i, int i2) {
        Log.v(TAG, "onOilMaintainanceHintChanged");
    }

    public void onOilUpdateStateChanged(int i) {
        Log.v(TAG, "onOilUpdateStateChanged");
    }

    public void onRearWiperCheckStateChanged(int i) {
        Log.v(TAG, "onRearWiperCheckStateChanged");
    }

    public void onResetOilLifetimeChanged(int i, int i2) {
        Log.v(TAG, "onResetOilLifetimeChanged");
    }

    @Deprecated
    public void onResetTirePressureStateChanged(int i) {
        Log.v(TAG, "onResetTirePressureStateChanged");
    }

    public void onTransportModeChanged(int i) {
        Log.v(TAG, "onTransportModeChanged");
    }

    public void onTransportModeStateHintChanged(int i) {
        Log.v(TAG, "onTransportModeStateHintChanged");
    }

    public void onOilLifetimeChanged(int i, int i2) {
        Log.v(TAG, "onOilLifetimeChanged");
    }
}
