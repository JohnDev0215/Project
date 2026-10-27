package android.hardware.bydauto.radar;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadarListener implements d {
    protected static final String TAG = "AbsBYDAutoRadarListener";

    public AbsBYDAutoRadarListener() {
        Log.v(TAG, TAG);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onError(int i2, String str) {
        Log.v(TAG, "onError");
    }

    public void onRadarObstacleDistanceChanged(int i2, int i3) {
        Log.v(TAG, "onRadarObstacleDistanceChanged");
    }

    public void onRadarProbeStateChanged(int i2, int i3) {
        Log.v(TAG, "onRadarProbeStateChanged");
    }

    public void onReverseRadarKeyStateChanged(int i2) {
        Log.v(TAG, "onReverseRadarKeyStateChanged");
    }

    public void onReverseRadarSwitchStateChanged(int i2) {
        Log.v(TAG, "onReverseRadarSwitchStateChanged");
    }
}
