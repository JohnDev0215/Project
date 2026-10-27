package android.hardware.bydauto.doormirror;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRearViewMirrorListener implements d {
    protected static final String TAG = "AbsBYDRearViewMirrorListener";

    public AbsBYDAutoRearViewMirrorListener() {
        Log.v(TAG, "AbsBYDAutoRearViewMirrorListener");
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onAutoExternalRearMirrorAntiglareStateChanged(int i7) {
        Log.v(TAG, "onAutoExternalRearMirrorAntiglareStateChanged");
    }

    public void onAutoExternalRearMirrorFoldActionStateChanged(int i7) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldActionStateChanged");
    }

    public void onAutoExternalRearMirrorFoldedState(int i7) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldedState");
    }

    public void onAutoExternalRearMirrorStateChanged(int i7) {
        Log.v(TAG, "onAutoExternalRearMirrorStateChanged");
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
}
