package android.hardware.bydauto.vehiclehealth;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoVehicleHealthListener implements d {
    protected static final String TAG = "AbsBYDHealthListener";

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public abstract /* synthetic */ void onDataChanged(c cVar);

    public abstract /* synthetic */ void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue);

    public void onEPBStateChanged(int i7) {
        Log.v(TAG, "onEPBStateChanged");
    }

    public abstract /* synthetic */ void onError(int i7, String str);

    public void onFrontWiperCheckStateChanged(int i7) {
        Log.v(TAG, "onFrontWiperCheckStateChanged");
    }

    public void onOilLevelChanged(int i7) {
        Log.v(TAG, "onOilLevelChanged");
    }

    public void onOilLifetimeChanged(int i7) {
        Log.v(TAG, "onOilLifetimeChanged");
    }

    public void onOilMaintainanceHintChanged(int i7, int i8) {
        Log.v(TAG, "onOilMaintainanceHintChanged");
    }

    public void onOilUpdateStateChanged(int i7) {
        Log.v(TAG, "onOilUpdateStateChanged");
    }

    public void onRearWiperCheckStateChanged(int i7) {
        Log.v(TAG, "onRearWiperCheckStateChanged");
    }

    public void onResetOilLifetimeChanged(int i7, int i8) {
        Log.v(TAG, "onResetOilLifetimeChanged");
    }

    @Deprecated
    public void onResetTirePressureStateChanged(int i7) {
        Log.v(TAG, "onResetTirePressureStateChanged");
    }

    public void onTransportModeChanged(int i7) {
        Log.v(TAG, "onTransportModeChanged");
    }

    public void onTransportModeStateHintChanged(int i7) {
        Log.v(TAG, "onTransportModeStateHintChanged");
    }

    public void onOilLifetimeChanged(int i7, int i8) {
        Log.v(TAG, "onOilLifetimeChanged");
    }
}
