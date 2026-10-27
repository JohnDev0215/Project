package android.hardware.bydauto.tyre;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import spi.c;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoTyreListener implements IBYDAutoListener {
    public static final String TAG = "AbsBYDAutoTyreListener";

    public AbsBYDAutoTyreListener() {
        Log.v(TAG, TAG);
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.v(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    @Override // android.hardware.IBYDAutoListener
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
        c.u("onSystemUndervoltageStatusChanged state: ", i, TAG);
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
