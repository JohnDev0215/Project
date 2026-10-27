package android.hardware.bydauto.tyre;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoTyreListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoTyreListener";

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onIndirectTyreSystemStateChanged(int i) {
    }

    public void onSystemUndervoltageStatusChanged(int i) {
    }

    public void onTyreBatteryStateChanged(int i) {
    }

    public void onTyreSystemStateChanged(int i) {
    }

    public void onTyreTemperatureStateChanged(int i) {
    }

    public void onTyreTurnStateChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onFeatureChanged(String str, int i) {
    }

    public void onIndirectTyrePressureStateChanged(int i, int i2) {
    }

    public void onTyreAirLeakStateChanged(int i, int i2) {
    }

    public void onTyreBatteryValueChanged(int i, float f) {
    }

    public void onTyreColorByTypeChanged(int i, int i2) {
    }

    public void onTyrePressureStateChanged(int i, int i2) {
    }

    public void onTyrePressureValueByTypeChanged(int i, float f) {
    }

    public void onTyrePressureValueChanged(int i, int i2) {
    }

    public void onTyrePressureWarnChanged(int i, int i2) {
    }

    public void onTyreSignalStateChanged(int i, int i2) {
    }

    public void onTyreTemperatureValueChanged(int i, int i2) {
    }
}
