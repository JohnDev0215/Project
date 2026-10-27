package android.hardware.bydauto.key;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSceneKeyListener implements d {
    public static final String TAG = "AbsBYDAutoSceneKeyListener";

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    public void onDataChanged(c cVar) {
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
