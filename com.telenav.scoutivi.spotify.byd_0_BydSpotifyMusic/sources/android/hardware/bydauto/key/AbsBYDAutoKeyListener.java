package android.hardware.bydauto.key;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoKeyListener implements d {
    public static final String TAG = "AbsBYDAutoKeyListener";

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public final void onDataChanged(c cVar) {
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

    public void onKeyClick(int i7, int i8) {
        Log.d(TAG, "onKeyClick");
    }
}
