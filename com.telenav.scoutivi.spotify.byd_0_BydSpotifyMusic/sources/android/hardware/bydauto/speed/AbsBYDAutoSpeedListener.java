package android.hardware.bydauto.speed;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSpeedListener implements d {
    protected static final String TAG = "AbsBYDAutoSpeedListener";

    public AbsBYDAutoSpeedListener() {
        Log.v(TAG, TAG);
    }

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public void onAccelerateDeepnessChanged(int i7) {
        Log.v(TAG, "onAccelerateDeepnessChanged");
    }

    public void onAccelerateValueChanged(float f7) {
        Log.v(TAG, "onAccelerateValueChanged");
    }

    public void onBrakeDeepnessChanged(int i7) {
        Log.v(TAG, "onBrakeDeepnessChanged");
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

    public void onFuelAccelerateDeepnessChanged(float f7) {
        Log.v(TAG, "onFuelAccelerateDeepnessChanged");
    }

    public void onSpeedChanged(double d7) {
        Log.v(TAG, "onSpeedChanged");
    }

    public void onSpeedFromGatewayChanged(float f7) {
        Log.v(TAG, "onSpeedFromGatewayChanged");
    }

    public void onSpeedValueChanged(double d7) {
        Log.v(TAG, "onSpeedValueChanged");
    }

    public void onAccelerateDeepnessChanged(int i7, int i8) {
        Log.v(TAG, "onAccelerateDeepnessChanged");
    }

    public void onBrakeDeepnessChanged(int i7, int i8) {
        Log.v(TAG, "onBrakeDeepnessChanged");
    }

    public void onSpeedChanged(float f7, int i7) {
        Log.v(TAG, "onSpeedChanged");
    }
}
