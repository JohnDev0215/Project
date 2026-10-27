package android.hardware.bydauto.doormirror;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRearViewMirrorListener implements d {
    protected static final String TAG = "AbsBYDRearViewMirrorListener";

    public AbsBYDAutoRearViewMirrorListener() {
        Log.v(TAG, "AbsBYDAutoRearViewMirrorListener");
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onAutoExternalRearMirrorAntiglareStateChanged(int i2) {
        Log.v(TAG, "onAutoExternalRearMirrorAntiglareStateChanged");
    }

    public void onAutoExternalRearMirrorFoldActionStateChanged(int i2) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldActionStateChanged");
    }

    public void onAutoExternalRearMirrorFoldedState(int i2) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldedState");
    }

    public void onAutoExternalRearMirrorStateChanged(int i2) {
        Log.v(TAG, "onAutoExternalRearMirrorStateChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onError(int i2, String str) {
        Log.v(TAG, "onError");
    }
}
