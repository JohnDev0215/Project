package android.hardware.bydauto.adas;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoADASListener implements d {
    public static final String TAG = "AbsBYDAutoADASListener";

    public AbsBYDAutoADASListener() {
        Log.d(TAG, TAG);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onAEBStateChanged(int i8) {
        Log.d(TAG, "onAEBStateChanged");
    }

    public void onAVHStateChanged(int i8) {
        Log.d(TAG, "onAVHStateChanged");
    }

    public void onBSDStateChanged(int i8) {
        Log.d(TAG, "onBSDStateChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onESPStateChanged(int i8) {
        Log.d(TAG, "onESPStateChanged");
    }

    public void onError(int i8, String str) {
        Log.d(TAG, "onError");
    }

    public void onFeatureChanged(String str, int i8) {
        Log.d(TAG, "onFeatureChanged");
    }

    public void onHMAStateChanged(int i8) {
        Log.d(TAG, "onHMAStateChanged");
    }

    public void onIboosterStateChanged(int i8) {
        Log.d(TAG, "onIboosterStateChanged");
    }

    public void onLDSWTypeChanged(int i8) {
        Log.d(TAG, "onLDSWTypeChanged");
    }

    public void onLKSModeChanged(int i8) {
        Log.d(TAG, "onLKSModeChanged");
    }

    public void onLKSSensitivityChanged(int i8) {
        Log.d(TAG, "onLKSSensitivityChanged");
    }

    public void onPCWStateChanged(int i8) {
        Log.d(TAG, "onPCWStateChanged");
    }

    public void onSLAStateChanged(int i8) {
        Log.d(TAG, "onSLAStateChanged");
    }
}
