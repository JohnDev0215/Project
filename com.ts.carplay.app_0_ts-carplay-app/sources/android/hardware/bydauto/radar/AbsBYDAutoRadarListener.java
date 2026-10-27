package android.hardware.bydauto.radar;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadarListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoRadarListener";

    public AbsBYDAutoRadarListener() {
        Log.v(TAG, TAG);
    }

    public void onRadarProbeStateChanged(int area, int state) {
        Log.v(TAG, "onRadarProbeStateChanged");
    }

    public void onRadarObstacleDistanceChanged(int area, int value) {
        Log.v(TAG, "onRadarObstacleDistanceChanged");
    }

    public void onReverseRadarSwitchStateChanged(int state) {
        Log.v(TAG, "onReverseRadarSwitchStateChanged");
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

    public void onReverseRadarKeyStateChanged(int state) {
        Log.v(TAG, "onReverseRadarKeyStateChanged");
    }
}
