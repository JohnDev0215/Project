package android.hardware.bydauto.doormirror;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRearViewMirrorListener implements d {
    public static final String TAG = "AbsBYDRearViewMirrorListener";

    public AbsBYDAutoRearViewMirrorListener() {
        Log.v(TAG, "AbsBYDAutoRearViewMirrorListener");
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onAutoExternalRearMirrorAntiglareStateChanged(int i3) {
        Log.v(TAG, "onAutoExternalRearMirrorAntiglareStateChanged");
    }

    public void onAutoExternalRearMirrorFoldActionStateChanged(int i3) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldActionStateChanged");
    }

    public void onAutoExternalRearMirrorFoldedState(int i3) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldedState");
    }

    public void onAutoExternalRearMirrorStateChanged(int i3) {
        Log.v(TAG, "onAutoExternalRearMirrorStateChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onError(int i3, String str) {
        Log.v(TAG, "onError");
    }
}
