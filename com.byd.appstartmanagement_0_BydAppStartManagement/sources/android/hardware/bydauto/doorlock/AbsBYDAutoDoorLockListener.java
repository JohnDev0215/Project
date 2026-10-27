package android.hardware.bydauto.doorlock;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDoorLockListener implements d {
    public static final String TAG = "AbsBYDAutoDoorLockListener";

    public AbsBYDAutoDoorLockListener() {
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

    public void onAutoExternalRearMirrorFoldActionStateChanged(int i) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldActionStateChanged");
    }

    public void onAutoExternalRearMirrorFoldedState(int i) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldedState");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDoorLockCockpitHatchStateChanged(int i) {
        Log.v(TAG, "onDoorLockCockpitHatchStateChanged");
    }

    public void onDoorLockStatusChanged(int i, int i2) {
        Log.v(TAG, "onDoorLockStatusChanged");
    }

    public void onDoorStatusChanged(int i, int i2) {
        Log.v(TAG, "onDoorStatusChanged");
    }

    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    public void onLockHeatProtectedState(int i) {
        Log.v(TAG, "onLockHeatProtectedState");
    }
}
