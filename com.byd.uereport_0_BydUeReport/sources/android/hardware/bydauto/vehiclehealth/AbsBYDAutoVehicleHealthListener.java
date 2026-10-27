package android.hardware.bydauto.vehiclehealth;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoVehicleHealthListener implements d {
    public static final String TAG = "AbsBYDHealthListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onBatteryHealthStatusChanged(int i8) {
        Log.v(TAG, "onBatteryHealthStatusChanged value = " + i8);
    }

    public abstract /* synthetic */ void onDataChanged(c cVar);

    public abstract /* synthetic */ void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue);

    public void onEPBHintChanged(int i8) {
        Log.v(TAG, "onEPBHintChanged state = " + i8);
    }

    public void onEPBStateChanged(int i8) {
        Log.v(TAG, "onEPBStateChanged");
    }

    public abstract /* synthetic */ void onError(int i8, String str);

    public void onFrontWiperCheckStateChanged(int i8) {
        Log.v(TAG, "onFrontWiperCheckStateChanged");
    }

    public void onOilLevelChanged(int i8) {
        Log.v(TAG, "onOilLevelChanged");
    }

    public void onOilLifetimeChanged(int i8) {
        Log.v(TAG, "onOilLifetimeChanged");
    }

    public void onOilLifetimeNewChanged(int i8) {
        Log.v(TAG, "onOilLifetimeNewChanged");
    }

    public void onOilMaintainanceDialogHintChanged(int i8) {
        Log.v(TAG, "onOilMaintainanceDialogHintChanged");
    }

    public void onOilMaintainanceHintChanged(int i8, int i9) {
        Log.v(TAG, "onOilMaintainanceHintChanged");
    }

    public void onOilUpdateStateChanged(int i8) {
        Log.v(TAG, "onOilUpdateStateChanged");
    }

    public void onRearWiperCheckStateChanged(int i8) {
        Log.v(TAG, "onRearWiperCheckStateChanged");
    }

    public void onResetOilLifetimeChanged(int i8, int i9) {
        Log.v(TAG, "onResetOilLifetimeChanged");
    }

    @Deprecated
    public void onResetTirePressureStateChanged(int i8) {
        Log.v(TAG, "onResetTirePressureStateChanged");
    }

    public void onTransportModeChanged(int i8) {
        Log.v(TAG, "onTransportModeChanged");
    }

    public void onTransportModeStateHintChanged(int i8) {
        Log.v(TAG, "onTransportModeStateHintChanged");
    }

    public void onOilLifetimeChanged(int i8, int i9) {
        Log.v(TAG, "onOilLifetimeChanged");
    }
}
