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
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onError(int i3, String str) {
        Log.v(TAG, "onError");
    }

    public void onFeatureChanged(String str, int i3) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onIndirectTyrePressureStateChanged(int i3, int i4) {
        Log.v(TAG, "onIndirectTyrePressureStateChanged");
    }

    public void onIndirectTyreSystemStateChanged(int i3) {
        Log.v(TAG, "onIndirectTyreSystemStateChanged");
    }

    public void onSystemUndervoltageStatusChanged(int i3) {
        Log.v(TAG, "onSystemUndervoltageStatusChanged state: " + i3);
    }

    public void onTyreAirLeakStateChanged(int i3, int i4) {
        Log.v(TAG, "onTyreAirLeakStateChanged");
    }

    public void onTyreBatteryStateChanged(int i3) {
        Log.v(TAG, "onTyreBatteryStateChanged");
    }

    public void onTyreBatteryValueChanged(int i3, float f3) {
        Log.v(TAG, "onTyreBatteryValueChanged");
    }

    public void onTyreColorByTypeChanged(int i3, int i4) {
        Log.v(TAG, "onTyreColorByTypeChanged");
    }

    public void onTyrePressureStateChanged(int i3, int i4) {
        Log.v(TAG, "onTyrePressureStateChanged");
    }

    public void onTyrePressureValueByTypeChanged(int i3, float f3) {
        Log.v(TAG, "onTyrePressureValueByTypeChanged");
    }

    public void onTyrePressureValueChanged(int i3, int i4) {
        Log.v(TAG, "onTyrePressureValueChanged");
    }

    public void onTyrePressureWarnChanged(int i3, int i4) {
        Log.v(TAG, "onTyrePressureWarnChanged");
    }

    public void onTyreSignalStateChanged(int i3, int i4) {
        Log.v(TAG, "onTyreSignalStateChanged");
    }

    public void onTyreSystemStateChanged(int i3) {
        Log.v(TAG, "onTyreSystemStateChanged");
    }

    public void onTyreTemperatureStateChanged(int i3) {
        Log.v(TAG, "onTyreTemperatureStateChanged");
    }

    public void onTyreTemperatureValueChanged(int i3, int i4) {
        Log.v(TAG, "onTyreTemperatureValueChanged area: " + i3 + " value: " + i4 + "");
    }

    public void onTyreTurnStateChanged(int i3) {
        Log.v(TAG, "onTyreTurnStateChanged");
    }
}
