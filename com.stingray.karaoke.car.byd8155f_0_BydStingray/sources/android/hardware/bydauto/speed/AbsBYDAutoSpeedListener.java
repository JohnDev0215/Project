package android.hardware.bydauto.speed;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSpeedListener implements d {
    protected static final String TAG = "AbsBYDAutoSpeedListener";

    public AbsBYDAutoSpeedListener() {
        Log.v(TAG, TAG);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onAccelerateDeepnessChanged(int i7) {
        Log.v(TAG, "onAccelerateDeepnessChanged");
    }

    public void onAccelerateValueChanged(float f10) {
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

    public void onFuelAccelerateDeepnessChanged(float f10) {
        Log.v(TAG, "onFuelAccelerateDeepnessChanged");
    }

    public void onSpeedChanged(double d) {
        Log.v(TAG, "onSpeedChanged");
    }

    public void onSpeedFromGatewayChanged(float f10) {
        Log.v(TAG, "onSpeedFromGatewayChanged");
    }

    public void onSpeedValueChanged(double d) {
        Log.v(TAG, "onSpeedValueChanged");
    }

    public void onAccelerateDeepnessChanged(int i7, int i10) {
        Log.v(TAG, "onAccelerateDeepnessChanged");
    }

    public void onBrakeDeepnessChanged(int i7, int i10) {
        Log.v(TAG, "onBrakeDeepnessChanged");
    }

    public void onSpeedChanged(float f10, int i7) {
        Log.v(TAG, "onSpeedChanged");
    }
}
