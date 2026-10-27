package android.hardware.bydauto.sensor;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSensorListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoSensorListener";

    public void onTemperatureSensorValueChanged(float value) {
        Log.v(TAG, "onTemperatureSensorValueChanged");
    }

    public void onHumiditySensorValueChanged(float value) {
        Log.v(TAG, "onHumiditySensorValueChanged");
    }

    public void onLightIntensityChanged(int value) {
        Log.v(TAG, "onLightIntensityChanged");
    }

    public void onSlopeValueChanged(int value) {
        Log.v(TAG, "onSlopeValueChanged");
    }

    public void onAccSensorDataChanged(byte[] data) {
        Log.v(TAG, "onAccSensorDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        Log.v(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        Log.v(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        Log.v(TAG, "onDataEventChanged");
    }
}
