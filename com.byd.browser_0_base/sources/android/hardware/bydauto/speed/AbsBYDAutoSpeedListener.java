package android.hardware.bydauto.speed;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSpeedListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoSpeedListener";

    public void onAccelerateDeepnessChanged(int i) {
    }

    public void onBrakeDeepnessChanged(int i) {
    }

    public void onSpeedChanged(double d) {
    }

    public void onAccelerateDeepnessChanged(int i, int i2) {
    }

    public void onBrakeDeepnessChanged(int i, int i2) {
    }

    public void onSpeedChanged(float f, int i) {
    }

    public void onAccelerateDeepnessSignificantBitChanged(int i) {
    }

    public void onAccelerateValueChanged(float f) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onFuelAccelerateDeepnessChanged(float f) {
    }

    public void onSpeedFromGatewayChanged(float f) {
    }

    public void onSpeedValueChanged(double d) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }
}
