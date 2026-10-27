package android.hardware.bydauto.radar;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadarListener implements d {
    protected static final String TAG = "AbsBYDAutoRadarListener";

    public AbsBYDAutoRadarListener() {
        Log.v(TAG, TAG);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
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
