package android.hardware.bydauto.doorlock;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDoorLockListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoDoorLockListener";

    public AbsBYDAutoDoorLockListener() {
        Log.v(TAG, TAG);
    }

    public void onDoorStatusChanged(int area, int state) {
        Log.v(TAG, "onDoorStatusChanged");
    }

    public void onDoorLockStatusChanged(int area, int state) {
        Log.v(TAG, "onDoorLockStatusChanged");
    }

    public void onLockHeatProtectedState(int state) {
        Log.v(TAG, "onLockHeatProtectedState");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        Log.v(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        Log.v(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onAutoExternalRearMirrorFoldActionStateChanged(int state) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldActionStateChanged");
    }

    public void onAutoExternalRearMirrorFoldedState(int state) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldedState");
    }

    public void onDoorLockCockpitHatchStateChanged(int state) {
        Log.v(TAG, "onDoorLockCockpitHatchStateChanged");
    }
}
