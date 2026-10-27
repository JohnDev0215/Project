package android.hardware.bydauto.radar;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import defpackage.d;
import defpackage.e;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadarListener implements e {
    public static final String TAG = "AbsBYDAutoRadarListener";

    public AbsBYDAutoRadarListener() {
        Log.v(TAG, TAG);
    }

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public final void onDataChanged(d dVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    public void onRadarObstacleDistanceChanged(int i, int i2) {
        Log.v(TAG, "onRadarObstacleDistanceChanged");
    }

    public void onRadarProbeStateChanged(int i, int i2) {
        Log.v(TAG, "onRadarProbeStateChanged");
    }

    public void onReverseRadarKeyStateChanged(int i) {
        Log.v(TAG, "onReverseRadarKeyStateChanged");
    }

    public void onReverseRadarSwitchStateChanged(int i) {
        Log.v(TAG, "onReverseRadarSwitchStateChanged");
    }
}
