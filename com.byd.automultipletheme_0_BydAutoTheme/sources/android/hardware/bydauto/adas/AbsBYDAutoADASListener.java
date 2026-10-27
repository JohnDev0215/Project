package android.hardware.bydauto.adas;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoADASListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoADASListener";

    @Override // android.hardware.IBYDAutoListener
    public /* synthetic */ void isConnected(boolean z) {
        IBYDAutoListener.CC.$default$isConnected(this, z);
    }

    public AbsBYDAutoADASListener() {
        Log.d(TAG, TAG);
    }

    public void onFeatureChanged(String str, int i) {
        Log.d(TAG, "onFeatureChanged");
    }

    public void onHMAStateChanged(int i) {
        Log.d(TAG, "onHMAStateChanged");
    }

    public void onPCWStateChanged(int i) {
        Log.d(TAG, "onPCWStateChanged");
    }

    public void onAEBStateChanged(int i) {
        Log.d(TAG, "onAEBStateChanged");
    }

    public void onESPStateChanged(int i) {
        Log.d(TAG, "onESPStateChanged");
    }

    public void onLKSModeChanged(int i) {
        Log.d(TAG, "onLKSModeChanged");
    }

    public void onLKSSensitivityChanged(int i) {
        Log.d(TAG, "onLKSSensitivityChanged");
    }

    public void onLDSWTypeChanged(int i) {
        Log.d(TAG, "onLDSWTypeChanged");
    }

    public void onBSDStateChanged(int i) {
        Log.d(TAG, "onBSDStateChanged");
    }

    public void onSLAStateChanged(int i) {
        Log.d(TAG, "onSLAStateChanged");
    }

    public void onAVHStateChanged(int i) {
        Log.d(TAG, "onAVHStateChanged");
    }

    public void onIboosterStateChanged(int i) {
        Log.d(TAG, "onIboosterStateChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.d(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.d(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }
}
