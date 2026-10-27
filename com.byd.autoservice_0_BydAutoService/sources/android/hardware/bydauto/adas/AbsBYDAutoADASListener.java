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
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onAEBStateChanged(int i3) {
        Log.d(TAG, "onAEBStateChanged");
    }

    public void onAVHStateChanged(int i3) {
        Log.d(TAG, "onAVHStateChanged");
    }

    public void onBSDStateChanged(int i3) {
        Log.d(TAG, "onBSDStateChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onESPStateChanged(int i3) {
        Log.d(TAG, "onESPStateChanged");
    }

    public void onError(int i3, String str) {
        Log.d(TAG, "onError");
    }

    public void onFeatureChanged(String str, int i3) {
        Log.d(TAG, "onFeatureChanged");
    }

    public void onHMAStateChanged(int i3) {
        Log.d(TAG, "onHMAStateChanged");
    }

    public void onIboosterStateChanged(int i3) {
        Log.d(TAG, "onIboosterStateChanged");
    }

    public void onLDSWTypeChanged(int i3) {
        Log.d(TAG, "onLDSWTypeChanged");
    }

    public void onLKSModeChanged(int i3) {
        Log.d(TAG, "onLKSModeChanged");
    }

    public void onLKSSensitivityChanged(int i3) {
        Log.d(TAG, "onLKSSensitivityChanged");
    }

    public void onPCWStateChanged(int i3) {
        Log.d(TAG, "onPCWStateChanged");
    }

    public void onSLAStateChanged(int i3) {
        Log.d(TAG, "onSLAStateChanged");
    }
}
