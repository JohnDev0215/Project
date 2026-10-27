package android.hardware.bydauto.key;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSceneKeyListener implements d {
    public static final String TAG = "AbsBYDAutoSceneKeyListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onError(int i8, String str) {
        Log.d(TAG, "onError");
    }

    public void onIdlerWheel(int i8) {
        Log.d(TAG, "onIdlerWheel");
    }

    public void onKeyClick(int i8, int i9) {
        Log.d(TAG, "onKeyClick");
    }
}
