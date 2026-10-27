package android.hardware.bydauto.adas;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoADASListener implements d {
    protected static final String TAG = "AbsBYDAutoADASListener";

    public AbsBYDAutoADASListener() {
        Log.d(TAG, TAG);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onAEBStateChanged(int i2) {
        Log.d(TAG, "onAEBStateChanged");
    }

    public void onAVHStateChanged(int i2) {
        Log.d(TAG, "onAVHStateChanged");
    }

    public void onBSDStateChanged(int i2) {
        Log.d(TAG, "onBSDStateChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onESPStateChanged(int i2) {
        Log.d(TAG, "onESPStateChanged");
    }

    public void onError(int i2, String str) {
        Log.d(TAG, "onError");
    }

    public void onFeatureChanged(String str, int i2) {
        Log.d(TAG, "onFeatureChanged");
    }

    public void onHMAStateChanged(int i2) {
        Log.d(TAG, "onHMAStateChanged");
    }

    public void onIboosterStateChanged(int i2) {
        Log.d(TAG, "onIboosterStateChanged");
    }

    public void onLDSWTypeChanged(int i2) {
        Log.d(TAG, "onLDSWTypeChanged");
    }

    public void onLKSModeChanged(int i2) {
        Log.d(TAG, "onLKSModeChanged");
    }

    public void onLKSSensitivityChanged(int i2) {
        Log.d(TAG, "onLKSSensitivityChanged");
    }

    public void onPCWStateChanged(int i2) {
        Log.d(TAG, "onPCWStateChanged");
    }

    public void onSLAStateChanged(int i2) {
        Log.d(TAG, "onSLAStateChanged");
    }
}
