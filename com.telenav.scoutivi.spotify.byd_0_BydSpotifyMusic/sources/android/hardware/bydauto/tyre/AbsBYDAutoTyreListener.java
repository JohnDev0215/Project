package android.hardware.bydauto.tyre;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoTyreListener implements d {
    protected static final String TAG = "AbsBYDAutoTyreListener";

    public AbsBYDAutoTyreListener() {
        Log.v(TAG, TAG);
    }

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onError(int i7, String str) {
        Log.v(TAG, "onError");
    }

    public void onFeatureChanged(String str, int i7) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onIndirectTyreSystemStateChanged(int i7) {
        Log.v(TAG, "onIndirectTyreSystemStateChanged");
    }

    public void onTyreAirLeakStateChanged(int i7, int i8) {
        Log.v(TAG, "onTyreAirLeakStateChanged");
    }

    public void onTyreBatteryStateChanged(int i7) {
        Log.v(TAG, "onTyreBatteryStateChanged");
    }

    public void onTyreBatteryValueChanged(int i7, float f7) {
        Log.v(TAG, "onTyreBatteryValueChanged");
    }

    public void onTyrePressureStateChanged(int i7, int i8) {
        Log.v(TAG, "onTyrePressureStateChanged");
    }

    public void onTyrePressureValueChanged(int i7, int i8) {
        Log.v(TAG, "onTyrePressureValueChanged");
    }

    public void onTyrePressureWarnChanged(int i7, int i8) {
        Log.v(TAG, "onTyrePressureWarnChanged");
    }

    public void onTyreSignalStateChanged(int i7, int i8) {
        Log.v(TAG, "onTyreSignalStateChanged");
    }

    public void onTyreSystemStateChanged(int i7) {
        Log.v(TAG, "onTyreSystemStateChanged");
    }

    public void onTyreTemperatureStateChanged(int i7) {
        Log.v(TAG, "onTyreTemperatureStateChanged");
    }

    public void onTyreTurnStateChanged(int i7) {
        Log.v(TAG, "onTyreTurnStateChanged");
    }
}
