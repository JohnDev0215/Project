package android.hardware.bydauto.speed;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSpeedListener implements d {
    public static final String TAG = "AbsBYDAutoSpeedListener";

    public AbsBYDAutoSpeedListener() {
        Log.v(TAG, TAG);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onAccelerateDeepnessChanged(int i3) {
        Log.v(TAG, "onAccelerateDeepnessChanged");
    }

    public void onAccelerateDeepnessSignificantBitChanged(int i3) {
        Log.v(TAG, "onAccelerateDeepnessSignificantBitChanged");
    }

    public void onAccelerateValueChanged(float f3) {
        Log.v(TAG, "onAccelerateValueChanged");
    }

    public void onBrakeDeepnessChanged(int i3) {
        Log.v(TAG, "onBrakeDeepnessChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onError(int i3, String str) {
        Log.v(TAG, "onError");
    }

    public void onFuelAccelerateDeepnessChanged(float f3) {
        Log.v(TAG, "onFuelAccelerateDeepnessChanged");
    }

    public void onSpeedChanged(double d3) {
        Log.v(TAG, "onSpeedChanged");
    }

    public void onSpeedFromGatewayChanged(float f3) {
        Log.v(TAG, "onSpeedFromGatewayChanged");
    }

    public void onSpeedValueChanged(double d3) {
        Log.v(TAG, "onSpeedValueChanged");
    }

    public void onAccelerateDeepnessChanged(int i3, int i4) {
        Log.v(TAG, "onAccelerateDeepnessChanged");
    }

    public void onBrakeDeepnessChanged(int i3, int i4) {
        Log.v(TAG, "onBrakeDeepnessChanged");
    }

    public void onSpeedChanged(float f3, int i3) {
        Log.v(TAG, "onSpeedChanged");
    }
}
