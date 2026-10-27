package android.hardware.bydauto.sensor;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSensorListener implements IBYDAutoListener {
    public static final String TAG = "AbsBYDAutoSensorListener";

    public void onAccSensorDataChanged(byte[] bArr) {
        Log.v(TAG, "onAccSensorDataChanged");
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
