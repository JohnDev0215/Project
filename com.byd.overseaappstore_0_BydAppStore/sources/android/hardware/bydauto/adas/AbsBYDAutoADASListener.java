package android.hardware.bydauto.adas;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoADASListener implements IBYDAutoListener {
    public static final String TAG = "AbsBYDAutoADASListener";

    public AbsBYDAutoADASListener() {
        Log.d(TAG, TAG);
    }

    public void onAEBStateChanged(int i) {
        Log.d(TAG, "onAEBStateChanged");
    }

    public void onAVHStateChanged(int i) {
        Log.d(TAG, "onAVHStateChanged");
    }

    public void onBSDStateChanged(int i) {
        Log.d(TAG, "onBSDStateChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.d(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onESPStateChanged(int i) {
        Log.d(TAG, "onESPStateChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.d(TAG, "onError");
    }

    public void onFeatureChanged(String str, int i) {
        Log.d(TAG, "onFeatureChanged");
    }

    public void onHMAStateChanged(int i) {
        Log.d(TAG, "onHMAStateChanged");
    }

    public void onIboosterStateChanged(int i) {
        Log.d(TAG, "onIboosterStateChanged");
    }

    public void onLDSWTypeChanged(int i) {
        Log.d(TAG, "onLDSWTypeChanged");
    }

    public void onLKSModeChanged(int i) {
        Log.d(TAG, "onLKSModeChanged");
    }

    public void onLKSSensitivityChanged(int i) {
        Log.d(TAG, "onLKSSensitivityChanged");
    }

    public void onPCWStateChanged(int i) {
        Log.d(TAG, "onPCWStateChanged");
    }

    public void onSLAStateChanged(int i) {
        Log.d(TAG, "onSLAStateChanged");
    }
}
