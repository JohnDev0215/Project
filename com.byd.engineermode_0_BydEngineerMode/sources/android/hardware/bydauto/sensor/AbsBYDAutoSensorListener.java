package android.hardware.bydauto.sensor;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSensorListener implements d {
    protected static final String TAG = "AbsBYDAutoSensorListener";

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onAccSensorDataChanged(byte[] bArr) {
        Log.v(TAG, "onAccSensorDataChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    public void onHumiditySensorValueChanged(float f) {
        Log.v(TAG, "onHumiditySensorValueChanged");
    }

    public void onLightIntensityChanged(int i) {
        Log.v(TAG, "onLightIntensityChanged");
    }

    public void onSlopeValueChanged(int i) {
        Log.v(TAG, "onSlopeValueChanged");
    }

    public void onTemperatureSensorValueChanged(float f) {
        Log.v(TAG, "onTemperatureSensorValueChanged");
    }
}
