package android.hardware.bydauto.tyre;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import defpackage.d;
import defpackage.e;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoTyreListener implements e {
    public static final String TAG = "AbsBYDAutoTyreListener";

    public AbsBYDAutoTyreListener() {
        Log.v(TAG, TAG);
    }

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public final void onDataChanged(d dVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    public void onFeatureChanged(String str, int i) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onIndirectTyrePressureStateChanged(int i, int i2) {
        Log.v(TAG, "onIndirectTyrePressureStateChanged");
    }

    public void onIndirectTyreSystemStateChanged(int i) {
        Log.v(TAG, "onIndirectTyreSystemStateChanged");
    }

    public void onSystemUndervoltageStatusChanged(int i) {
        Log.v(TAG, "onSystemUndervoltageStatusChanged state: " + i);
    }

    public void onTyreAirLeakStateChanged(int i, int i2) {
        Log.v(TAG, "onTyreAirLeakStateChanged");
    }

    public void onTyreBatteryStateChanged(int i) {
        Log.v(TAG, "onTyreBatteryStateChanged");
    }

    public void onTyreBatteryValueChanged(int i, float f) {
        Log.v(TAG, "onTyreBatteryValueChanged");
    }

    public void onTyreColorByTypeChanged(int i, int i2) {
        Log.v(TAG, "onTyreColorByTypeChanged");
    }

    public void onTyrePressureStateChanged(int i, int i2) {
        Log.v(TAG, "onTyrePressureStateChanged");
    }

    public void onTyrePressureValueByTypeChanged(int i, float f) {
        Log.v(TAG, "onTyrePressureValueByTypeChanged");
    }

    public void onTyrePressureValueChanged(int i, int i2) {
        Log.v(TAG, "onTyrePressureValueChanged");
    }

    public void onTyrePressureWarnChanged(int i, int i2) {
        Log.v(TAG, "onTyrePressureWarnChanged");
    }

    public void onTyreSignalStateChanged(int i, int i2) {
        Log.v(TAG, "onTyreSignalStateChanged");
    }

    public void onTyreSystemStateChanged(int i) {
        Log.v(TAG, "onTyreSystemStateChanged");
    }

    public void onTyreTemperatureStateChanged(int i) {
        Log.v(TAG, "onTyreTemperatureStateChanged");
    }

    public void onTyreTemperatureValueChanged(int i, int i2) {
        Log.v(TAG, "onTyreTemperatureValueChanged area: " + i + " value: " + i2 + "");
    }

    public void onTyreTurnStateChanged(int i) {
        Log.v(TAG, "onTyreTurnStateChanged");
    }
}
