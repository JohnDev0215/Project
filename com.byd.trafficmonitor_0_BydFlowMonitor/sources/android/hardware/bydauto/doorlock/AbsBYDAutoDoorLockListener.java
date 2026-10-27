package android.hardware.bydauto.doorlock;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDoorLockListener implements d {
    public static final String TAG = "AbsBYDAutoDoorLockListener";

    public AbsBYDAutoDoorLockListener() {
        Log.v(TAG, TAG);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onAutoExternalRearMirrorFoldActionStateChanged(int i2) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldActionStateChanged");
    }

    public void onAutoExternalRearMirrorFoldedState(int i2) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldedState");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDoorLockCockpitHatchStateChanged(int i2) {
        Log.v(TAG, "onDoorLockCockpitHatchStateChanged");
    }

    public void onDoorLockStatusChanged(int i2, int i3) {
        Log.v(TAG, "onDoorLockStatusChanged");
    }

    public void onDoorStatusChanged(int i2, int i3) {
        Log.v(TAG, "onDoorStatusChanged");
    }

    public void onError(int i2, String str) {
        Log.v(TAG, "onError");
    }

    public void onLockHeatProtectedState(int i2) {
        Log.v(TAG, "onLockHeatProtectedState");
    }
}
