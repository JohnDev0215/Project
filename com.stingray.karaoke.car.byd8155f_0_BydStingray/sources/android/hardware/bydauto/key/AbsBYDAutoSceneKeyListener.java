package android.hardware.bydauto.key;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSceneKeyListener implements d {
    public static final String TAG = "AbsBYDAutoSceneKeyListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onError(int i7, String str) {
        Log.d(TAG, "onError");
    }

    public void onIdlerWheel(int i7) {
        Log.d(TAG, "onIdlerWheel");
    }

    public void onKeyClick(int i7, int i10) {
        Log.d(TAG, "onKeyClick");
    }
}
