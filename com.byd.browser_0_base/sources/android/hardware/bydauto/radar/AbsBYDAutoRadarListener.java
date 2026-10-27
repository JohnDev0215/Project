package android.hardware.bydauto.radar;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadarListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoRadarListener";

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onReverseRadarKeyStateChanged(int i) {
    }

    public void onReverseRadarSwitchStateChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onRadarObstacleDistanceChanged(int i, int i2) {
    }

    public void onRadarProbeStateChanged(int i, int i2) {
    }
}
