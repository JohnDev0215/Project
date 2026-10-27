package android.hardware.bydauto.vehiclehealth;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoVehicleHealthListener implements d {
    public static final String TAG = "AbsBYDHealthListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onBatteryHealthStatusChanged(int i) {
        Log.v(TAG, "onBatteryHealthStatusChanged value = " + i);
    }

    public abstract /* synthetic */ void onDataChanged(c cVar);

    public abstract /* synthetic */ void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue);

    public void onEPBHintChanged(int i) {
        Log.v(TAG, "onEPBHintChanged state = " + i);
    }

    public void onEPBStateChanged(int i) {
        Log.v(TAG, "onEPBStateChanged");
    }

    public abstract /* synthetic */ void onError(int i, String str);

    public void onFrontWiperCheckStateChanged(int i) {
        Log.v(TAG, "onFrontWiperCheckStateChanged");
    }

    public void onOilLevelChanged(int i) {
        Log.v(TAG, "onOilLevelChanged");
    }

    public void onOilLifetimeChanged(int i) {
        Log.v(TAG, "onOilLifetimeChanged");
    }

    public void onOilLifetimeNewChanged(int i) {
        Log.v(TAG, "onOilLifetimeNewChanged");
    }

    public void onOilMaintainanceDialogHintChanged(int i) {
        Log.v(TAG, "onOilMaintainanceDialogHintChanged");
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
