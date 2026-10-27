package android.hardware.bydauto.radar;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadarListener implements d {
    protected static final String TAG = "AbsBYDAutoRadarListener";

    public AbsBYDAutoRadarListener() {
        Log.v(TAG, TAG);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
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

    public void onRadarObstacleDistanceChanged(int i7, int i10) {
        Log.v(TAG, "onRadarObstacleDistanceChanged");
    }

    public void onRadarProbeStateChanged(int i7, int i10) {
        Log.v(TAG, "onRadarProbeStateChanged");
    }

    public void onReverseRadarKeyStateChanged(int i7) {
        Log.v(TAG, "onReverseRadarKeyStateChanged");
    }

    public void onReverseRadarSwitchStateChanged(int i7) {
        Log.v(TAG, "onReverseRadarSwitchStateChanged");
    }
}
