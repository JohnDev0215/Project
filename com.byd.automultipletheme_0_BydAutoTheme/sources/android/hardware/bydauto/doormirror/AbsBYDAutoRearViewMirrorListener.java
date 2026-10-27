package android.hardware.bydauto.doormirror;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRearViewMirrorListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDRearViewMirrorListener";

    @Override // android.hardware.IBYDAutoListener
    public /* synthetic */ void isConnected(boolean z) {
        IBYDAutoListener.CC.$default$isConnected(this, z);
    }

    public AbsBYDAutoRearViewMirrorListener() {
        Log.v(TAG, "AbsBYDAutoRearViewMirrorListener");
    }

    public void onAutoExternalRearMirrorStateChanged(int i) {
        Log.v(TAG, "onAutoExternalRearMirrorStateChanged");
    }

    public void onAutoExternalRearMirrorAntiglareStateChanged(int i) {
        Log.v(TAG, "onAutoExternalRearMirrorAntiglareStateChanged");
    }

    public void onAutoExternalRearMirrorFoldActionStateChanged(int i) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldActionStateChanged");
    }

    public void onAutoExternalRearMirrorFoldedState(int i) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldedState");
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
}
