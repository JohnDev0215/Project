package android.hardware.bydauto.radar;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadarListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoRadarListener";

    @Override // android.hardware.IBYDAutoListener
    public /* synthetic */ void isConnected(boolean z) {
        IBYDAutoListener.CC.$default$isConnected(this, z);
    }

    public AbsBYDAutoRadarListener() {
        Log.v(TAG, TAG);
    }

    public void onRadarProbeStateChanged(int i, int i2) {
        Log.v(TAG, "onRadarProbeStateChanged");
    }

    public void onRadarObstacleDistanceChanged(int i, int i2) {
        Log.v(TAG, "onRadarObstacleDistanceChanged");
    }

    public void onReverseRadarSwitchStateChanged(int i) {
        Log.v(TAG, "onReverseRadarSwitchStateChanged");
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

    public void onReverseRadarKeyStateChanged(int i) {
        Log.v(TAG, "onReverseRadarKeyStateChanged");
    }
}
