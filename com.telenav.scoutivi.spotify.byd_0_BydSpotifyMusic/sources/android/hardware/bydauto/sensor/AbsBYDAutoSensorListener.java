package android.hardware.bydauto.sensor;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSensorListener implements d {
    protected static final String TAG = "AbsBYDAutoSensorListener";

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public void onAccSensorDataChanged(byte[] bArr) {
        Log.v(TAG, "onAccSensorDataChanged");
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

    public void onHumiditySensorValueChanged(float f7) {
        Log.v(TAG, "onHumiditySensorValueChanged");
    }

    public void onLightIntensityChanged(int i7) {
        Log.v(TAG, "onLightIntensityChanged");
    }

    public void onSlopeValueChanged(int i7) {
        Log.v(TAG, "onSlopeValueChanged");
    }

    public void onTemperatureSensorValueChanged(float f7) {
        Log.v(TAG, "onTemperatureSensorValueChanged");
    }
}
