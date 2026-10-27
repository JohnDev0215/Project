package android.hardware.bydauto.speed;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSpeedListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoSpeedListener";

    public AbsBYDAutoSpeedListener() {
        Log.v(TAG, TAG);
    }

    public void onSpeedChanged(double value) {
        Log.v(TAG, "onSpeedChanged");
    }

    public void onSpeedChanged(float value, int flag) {
        Log.v(TAG, "onSpeedChanged");
    }

    public void onAccelerateDeepnessChanged(int value) {
        Log.v(TAG, "onAccelerateDeepnessChanged");
    }

    public void onAccelerateDeepnessChanged(int value, int flag) {
        Log.v(TAG, "onAccelerateDeepnessChanged");
    }

    public void onBrakeDeepnessChanged(int value) {
        Log.v(TAG, "onBrakeDeepnessChanged");
    }

    public void onBrakeDeepnessChanged(int value, int flag) {
        Log.v(TAG, "onBrakeDeepnessChanged");
    }

    public void onAccelerateValueChanged(float value) {
        Log.v(TAG, "onAccelerateValueChanged");
    }

    public void onSpeedFromGatewayChanged(float value) {
        Log.v(TAG, "onSpeedFromGatewayChanged");
    }

    public void onFuelAccelerateDeepnessChanged(float value) {
        Log.v(TAG, "onFuelAccelerateDeepnessChanged");
    }

    public void onSpeedValueChanged(double value) {
        Log.v(TAG, "onSpeedValueChanged");
    }

    public void onAccelerateDeepnessSignificantBitChanged(int value) {
        Log.v(TAG, "onAccelerateDeepnessSignificantBitChanged");
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
