package android.hardware.bydauto.tyre;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoTyreListener implements d {
    public static final String TAG = "AbsBYDAutoTyreListener";

    public AbsBYDAutoTyreListener() {
        Log.v(TAG, TAG);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onError(int i8, String str) {
        Log.v(TAG, "onError");
    }

    public void onFeatureChanged(String str, int i8) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onIndirectTyrePressureStateChanged(int i8, int i9) {
        Log.v(TAG, "onIndirectTyrePressureStateChanged");
    }

    public void onIndirectTyreSystemStateChanged(int i8) {
        Log.v(TAG, "onIndirectTyreSystemStateChanged");
    }

    public void onSystemUndervoltageStatusChanged(int i8) {
        Log.v(TAG, "onSystemUndervoltageStatusChanged state: " + i8);
    }

    public void onTyreAirLeakStateChanged(int i8, int i9) {
        Log.v(TAG, "onTyreAirLeakStateChanged");
    }

    public void onTyreBatteryStateChanged(int i8) {
        Log.v(TAG, "onTyreBatteryStateChanged");
    }

    public void onTyreBatteryValueChanged(int i8, float f8) {
        Log.v(TAG, "onTyreBatteryValueChanged");
    }

    public void onTyreColorByTypeChanged(int i8, int i9) {
        Log.v(TAG, "onTyreColorByTypeChanged");
    }

    public void onTyrePressureStateChanged(int i8, int i9) {
        Log.v(TAG, "onTyrePressureStateChanged");
    }

    public void onTyrePressureValueByTypeChanged(int i8, float f8) {
        Log.v(TAG, "onTyrePressureValueByTypeChanged");
    }

    public void onTyrePressureValueChanged(int i8, int i9) {
        Log.v(TAG, "onTyrePressureValueChanged");
    }

    public void onTyrePressureWarnChanged(int i8, int i9) {
        Log.v(TAG, "onTyrePressureWarnChanged");
    }

    public void onTyreSignalStateChanged(int i8, int i9) {
        Log.v(TAG, "onTyreSignalStateChanged");
    }

    public void onTyreSystemStateChanged(int i8) {
        Log.v(TAG, "onTyreSystemStateChanged");
    }

    public void onTyreTemperatureStateChanged(int i8) {
        Log.v(TAG, "onTyreTemperatureStateChanged");
    }

    public void onTyreTemperatureValueChanged(int i8, int i9) {
        Log.v(TAG, "onTyreTemperatureValueChanged area: " + i8 + " value: " + i9 + "");
    }

    public void onTyreTurnStateChanged(int i8) {
        Log.v(TAG, "onTyreTurnStateChanged");
    }
}
