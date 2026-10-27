package android.hardware.bydauto.key;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSceneKeyListener implements d {
    public static final String TAG = "AbsBYDAutoSceneKeyListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onError(int i3, String str) {
        Log.d(TAG, "onError");
    }

    public void onIdlerWheel(int i3) {
        Log.d(TAG, "onIdlerWheel");
    }

    public void onKeyClick(int i3, int i4) {
        Log.d(TAG, "onKeyClick");
    }
}
