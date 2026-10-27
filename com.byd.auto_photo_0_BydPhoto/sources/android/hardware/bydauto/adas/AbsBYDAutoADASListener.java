package android.hardware.bydauto.adas;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoADASListener implements d {
    protected static final String TAG = "AbsBYDAutoADASListener";

    public AbsBYDAutoADASListener() {
        Log.d(TAG, TAG);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
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

    public final void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onESPStateChanged(int i) {
        Log.d(TAG, "onESPStateChanged");
    }

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
