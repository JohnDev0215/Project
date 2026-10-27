package android.hardware.bydauto.key;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoKeyListener implements d {
    public static final String TAG = "AbsBYDAutoKeyListener";

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public final void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onError(int i2, String str) {
        Log.d(TAG, "onError");
    }

    public void onIdlerWheel(int i2) {
        Log.d(TAG, "onIdlerWheel");
    }

    public void onKeyClick(int i2, int i3) {
        Log.d(TAG, "onKeyClick");
    }
}
