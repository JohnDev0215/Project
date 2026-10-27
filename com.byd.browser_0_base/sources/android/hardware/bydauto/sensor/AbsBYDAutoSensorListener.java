package android.hardware.bydauto.sensor;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSensorListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoSensorListener";

    public void onAccSensorDataChanged(byte[] bArr) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onHumiditySensorValueChanged(float f) {
    }

    public void onLightIntensityChanged(int i) {
    }

    public void onSlopeValueChanged(int i) {
    }

    public void onTemperatureSensorValueChanged(float f) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }
}
