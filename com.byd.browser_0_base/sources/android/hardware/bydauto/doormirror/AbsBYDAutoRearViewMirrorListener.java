package android.hardware.bydauto.doormirror;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRearViewMirrorListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDRearViewMirrorListener";

    public void onAutoExternalRearMirrorAntiglareStateChanged(int i) {
    }

    public void onAutoExternalRearMirrorFoldActionStateChanged(int i) {
    }

    public void onAutoExternalRearMirrorFoldedState(int i) {
    }

    public void onAutoExternalRearMirrorStateChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }
}
