package android.hardware.bydauto.key;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoKeyListener implements IBYDAutoListener {
    public static final String TAG = "AbsBYDAutoKeyListener";

    public void onIdlerWheel(int value) {
        Log.d(TAG, "onIdlerWheel");
    }

    public void onKeyClick(int keyValue, int click) {
        Log.d(TAG, "onKeyClick");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        Log.d(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        Log.d(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        Log.d(TAG, "onDataEventChanged");
    }
}
