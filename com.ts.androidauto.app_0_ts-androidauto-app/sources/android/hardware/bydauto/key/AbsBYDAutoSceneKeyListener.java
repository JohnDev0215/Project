package android.hardware.bydauto.key;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSceneKeyListener implements IBYDAutoListener {
    public static final String TAG = "AbsBYDAutoSceneKeyListener";

    public void onIdlerWheel(int value) {
        Log.d(TAG, "onIdlerWheel");
    }

    public void onKeyClick(int keyValue, int click) {
        Log.d(TAG, "onKeyClick");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent var1) {
        Log.d(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int var1, String var2) {
        Log.d(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int var1, BYDAutoEventValue var2) {
        Log.d(TAG, "onDataEventChanged");
    }
}
