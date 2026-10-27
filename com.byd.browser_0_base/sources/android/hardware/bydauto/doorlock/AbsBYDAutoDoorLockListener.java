package android.hardware.bydauto.doorlock;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDoorLockListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoDoorLockListener";

    public void onAutoExternalRearMirrorFoldActionStateChanged(int i) {
    }

    public void onAutoExternalRearMirrorFoldedState(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onDoorLockCockpitHatchStateChanged(int i) {
    }

    public void onLockHeatProtectedState(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDoorLockStatusChanged(int i, int i2) {
    }

    public void onDoorStatusChanged(int i, int i2) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }
}
