package android.hardware.bydauto.doorlock;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDoorLockListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoDoorLockListener";

    @Override // android.hardware.IBYDAutoListener
    public /* synthetic */ void isConnected(boolean z) {
        IBYDAutoListener.CC.$default$isConnected(this, z);
    }

    public AbsBYDAutoDoorLockListener() {
        Log.v(TAG, TAG);
    }

    public void onDoorStatusChanged(int i, int i2) {
        Log.v(TAG, "onDoorStatusChanged");
    }

    public void onDoorLockStatusChanged(int i, int i2) {
        Log.v(TAG, "onDoorLockStatusChanged");
    }

    public void onLockHeatProtectedState(int i) {
        Log.v(TAG, "onLockHeatProtectedState");
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

    public void onAutoExternalRearMirrorFoldActionStateChanged(int i) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldActionStateChanged");
    }

    public void onAutoExternalRearMirrorFoldedState(int i) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldedState");
    }
}
