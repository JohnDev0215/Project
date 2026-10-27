package android.hardware.bydauto.adas;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoADASListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoADASListener";

    public AbsBYDAutoADASListener() {
        Log.d(TAG, TAG);
    }

    public void onFeatureChanged(String feature, int ifHas) {
        Log.d(TAG, "onFeatureChanged");
    }

    public void onHMAStateChanged(int state) {
        Log.d(TAG, "onHMAStateChanged");
    }

    public void onPCWStateChanged(int state) {
        Log.d(TAG, "onPCWStateChanged");
    }

    public void onAEBStateChanged(int state) {
        Log.d(TAG, "onAEBStateChanged");
    }

    public void onESPStateChanged(int state) {
        Log.d(TAG, "onESPStateChanged");
    }

    public void onLKSModeChanged(int mode) {
        Log.d(TAG, "onLKSModeChanged");
    }

    public void onLKSSensitivityChanged(int sensitivity) {
        Log.d(TAG, "onLKSSensitivityChanged");
    }

    public void onLDSWTypeChanged(int type) {
        Log.d(TAG, "onLDSWTypeChanged");
    }

    public void onBSDStateChanged(int state) {
        Log.d(TAG, "onBSDStateChanged");
    }

    public void onSLAStateChanged(int state) {
        Log.d(TAG, "onSLAStateChanged");
    }

    public void onAVHStateChanged(int state) {
        Log.d(TAG, "onAVHStateChanged");
    }

    public void onIboosterStateChanged(int state) {
        Log.d(TAG, "onIboosterStateChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        Log.d(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        Log.d(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        Log.d(TAG, "onDataEventChanged");
    }
}
