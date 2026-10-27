package android.hardware.bydauto.speed;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSpeedListener implements d {
    public static final String TAG = "AbsBYDAutoSpeedListener";

    public AbsBYDAutoSpeedListener() {
        Log.v(TAG, TAG);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onAccelerateDeepnessChanged(int i) {
        Log.v(TAG, "onAccelerateDeepnessChanged");
    }

    public void onAccelerateDeepnessSignificantBitChanged(int i) {
        Log.v(TAG, "onAccelerateDeepnessSignificantBitChanged");
    }

    public void onAccelerateValueChanged(float f2) {
        Log.v(TAG, "onAccelerateValueChanged");
    }

    public void onBrakeDeepnessChanged(int i) {
        Log.v(TAG, "onBrakeDeepnessChanged");
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

    public void onFuelAccelerateDeepnessChanged(float f2) {
        Log.v(TAG, "onFuelAccelerateDeepnessChanged");
    }

    public void onSpeedChanged(double d2) {
        Log.v(TAG, "onSpeedChanged");
    }

    public void onSpeedFromGatewayChanged(float f2) {
        Log.v(TAG, "onSpeedFromGatewayChanged");
    }

    public void onSpeedValueChanged(double d2) {
        Log.v(TAG, "onSpeedValueChanged");
    }

    public void onAccelerateDeepnessChanged(int i, int i2) {
        Log.v(TAG, "onAccelerateDeepnessChanged");
    }

    public void onBrakeDeepnessChanged(int i, int i2) {
        Log.v(TAG, "onBrakeDeepnessChanged");
    }

    public void onSpeedChanged(float f2, int i) {
        Log.v(TAG, "onSpeedChanged");
    }
}
