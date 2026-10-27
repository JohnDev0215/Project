package android.hardware.bydauto.key;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import defpackage.d;
import defpackage.e;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSceneKeyListener implements e {
    public static final String TAG = "AbsBYDAutoSceneKeyListener";

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onDataChanged(d dVar) {
        Log.d(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onError(int i, String str) {
        Log.d(TAG, "onError");
    }

    public void onIdlerWheel(int i) {
        Log.d(TAG, "onIdlerWheel");
    }

    public void onKeyClick(int i, int i2) {
        Log.d(TAG, "onKeyClick");
    }
}
