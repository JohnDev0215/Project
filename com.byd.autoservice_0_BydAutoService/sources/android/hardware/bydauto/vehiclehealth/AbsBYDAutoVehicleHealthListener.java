package android.hardware.bydauto.vehiclehealth;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoVehicleHealthListener implements d {
    public static final String TAG = "AbsBYDHealthListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onBatteryHealthStatusChanged(int i3) {
        Log.v(TAG, "onBatteryHealthStatusChanged value = " + i3);
    }

    public abstract /* synthetic */ void onDataChanged(c cVar);

    public abstract /* synthetic */ void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue);

    public void onEPBHintChanged(int i3) {
        Log.v(TAG, "onEPBHintChanged state = " + i3);
    }

    public void onEPBStateChanged(int i3) {
        Log.v(TAG, "onEPBStateChanged");
    }

    public abstract /* synthetic */ void onError(int i3, String str);

    public void onFrontWiperCheckStateChanged(int i3) {
        Log.v(TAG, "onFrontWiperCheckStateChanged");
    }

    public void onOilLevelChanged(int i3) {
        Log.v(TAG, "onOilLevelChanged");
    }

    public void onOilLifetimeChanged(int i3) {
        Log.v(TAG, "onOilLifetimeChanged");
    }

    public void onOilLifetimeNewChanged(int i3) {
        Log.v(TAG, "onOilLifetimeNewChanged");
    }

    public void onOilMaintainanceDialogHintChanged(int i3) {
        Log.v(TAG, "onOilMaintainanceDialogHintChanged");
    }

    public void onOilMaintainanceHintChanged(int i3, int i4) {
        Log.v(TAG, "onOilMaintainanceHintChanged");
    }

    public void onOilUpdateStateChanged(int i3) {
        Log.v(TAG, "onOilUpdateStateChanged");
    }

    public void onRearWiperCheckStateChanged(int i3) {
        Log.v(TAG, "onRearWiperCheckStateChanged");
    }

    public void onResetOilLifetimeChanged(int i3, int i4) {
        Log.v(TAG, "onResetOilLifetimeChanged");
    }

    @Deprecated
    public void onResetTirePressureStateChanged(int i3) {
        Log.v(TAG, "onResetTirePressureStateChanged");
    }

    public void onTransportModeChanged(int i3) {
        Log.v(TAG, "onTransportModeChanged");
    }

    public void onTransportModeStateHintChanged(int i3) {
        Log.v(TAG, "onTransportModeStateHintChanged");
    }

    public void onOilLifetimeChanged(int i3, int i4) {
        Log.v(TAG, "onOilLifetimeChanged");
    }
}
