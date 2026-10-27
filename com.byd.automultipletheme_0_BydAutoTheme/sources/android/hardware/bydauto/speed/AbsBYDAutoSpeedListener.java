package android.hardware.bydauto.speed;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSpeedListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoSpeedListener";

    @Override // android.hardware.IBYDAutoListener
    public /* synthetic */ void isConnected(boolean z) {
        IBYDAutoListener.CC.$default$isConnected(this, z);
    }

    public AbsBYDAutoSpeedListener() {
        Log.v(TAG, TAG);
    }

    public void onSpeedChanged(double d) {
        Log.v(TAG, "onSpeedChanged");
    }

    public void onSpeedChanged(float f, int i) {
        Log.v(TAG, "onSpeedChanged");
    }

    public void onAccelerateDeepnessChanged(int i) {
        Log.v(TAG, "onAccelerateDeepnessChanged");
    }

    public void onAccelerateDeepnessChanged(int i, int i2) {
        Log.v(TAG, "onAccelerateDeepnessChanged");
    }

    public void onBrakeDeepnessChanged(int i) {
        Log.v(TAG, "onBrakeDeepnessChanged");
    }

    public void onBrakeDeepnessChanged(int i, int i2) {
        Log.v(TAG, "onBrakeDeepnessChanged");
    }

    public void onAccelerateValueChanged(float f) {
        Log.v(TAG, "onAccelerateValueChanged");
    }

    public void onSpeedFromGatewayChanged(float f) {
        Log.v(TAG, "onSpeedFromGatewayChanged");
    }

    public void onFuelAccelerateDeepnessChanged(float f) {
        Log.v(TAG, "onFuelAccelerateDeepnessChanged");
    }

    public void onSpeedValueChanged(double d) {
        Log.v(TAG, "onSpeedValueChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.v(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }
}
