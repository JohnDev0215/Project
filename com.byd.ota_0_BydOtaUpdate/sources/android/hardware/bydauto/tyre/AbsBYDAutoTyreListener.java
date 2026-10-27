package android.hardware.bydauto.tyre;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoTyreListener implements d {
    public static final String TAG = "AbsBYDAutoTyreListener";

    public AbsBYDAutoTyreListener() {
        Log.v(TAG, TAG);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onError(int i2, String str) {
        Log.v(TAG, "onError");
    }

    public void onFeatureChanged(String str, int i2) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onIndirectTyrePressureStateChanged(int i2, int i3) {
        Log.v(TAG, "onIndirectTyrePressureStateChanged");
    }

    public void onIndirectTyreSystemStateChanged(int i2) {
        Log.v(TAG, "onIndirectTyreSystemStateChanged");
    }

    public void onSystemUndervoltageStatusChanged(int i2) {
        Log.v(TAG, "onSystemUndervoltageStatusChanged state: " + i2);
    }

    public void onTyreAirLeakStateChanged(int i2, int i3) {
        Log.v(TAG, "onTyreAirLeakStateChanged");
    }

    public void onTyreBatteryStateChanged(int i2) {
        Log.v(TAG, "onTyreBatteryStateChanged");
    }

    public void onTyreBatteryValueChanged(int i2, float f2) {
        Log.v(TAG, "onTyreBatteryValueChanged");
    }

    public void onTyreColorByTypeChanged(int i2, int i3) {
        Log.v(TAG, "onTyreColorByTypeChanged");
    }

    public void onTyrePressureStateChanged(int i2, int i3) {
        Log.v(TAG, "onTyrePressureStateChanged");
    }

    public void onTyrePressureValueByTypeChanged(int i2, float f2) {
        Log.v(TAG, "onTyrePressureValueByTypeChanged");
    }

    public void onTyrePressureValueChanged(int i2, int i3) {
        Log.v(TAG, "onTyrePressureValueChanged");
    }

    public void onTyrePressureWarnChanged(int i2, int i3) {
        Log.v(TAG, "onTyrePressureWarnChanged");
    }

    public void onTyreSignalStateChanged(int i2, int i3) {
        Log.v(TAG, "onTyreSignalStateChanged");
    }

    public void onTyreSystemStateChanged(int i2) {
        Log.v(TAG, "onTyreSystemStateChanged");
    }

    public void onTyreTemperatureStateChanged(int i2) {
        Log.v(TAG, "onTyreTemperatureStateChanged");
    }

    public void onTyreTemperatureValueChanged(int i2, int i3) {
        Log.v(TAG, "onTyreTemperatureValueChanged area: " + i2 + " value: " + i3 + "");
    }

    public void onTyreTurnStateChanged(int i2) {
        Log.v(TAG, "onTyreTurnStateChanged");
    }
}
