package android.hardware.bydauto.doorlock;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDoorLockListener implements d {
    protected static final String TAG = "AbsBYDAutoDoorLockListener";

    public AbsBYDAutoDoorLockListener() {
        Log.v(TAG, TAG);
    }

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public void onAutoExternalRearMirrorFoldActionStateChanged(int i7) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldActionStateChanged");
    }

    public void onAutoExternalRearMirrorFoldedState(int i7) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldedState");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDoorLockStatusChanged(int i7, int i8) {
        Log.v(TAG, "onDoorLockStatusChanged");
    }

    public void onDoorStatusChanged(int i7, int i8) {
        Log.v(TAG, "onDoorStatusChanged");
    }

    public void onError(int i7, String str) {
        Log.v(TAG, "onError");
    }

    public void onLockHeatProtectedState(int i7) {
        Log.v(TAG, "onLockHeatProtectedState");
    }
}
