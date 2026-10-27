package android.hardware.bydauto.adas;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoADASListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoADASListener";

    public void onAEBStateChanged(int i) {
    }

    public void onAVHStateChanged(int i) {
    }

    public void onBSDStateChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onESPStateChanged(int i) {
    }

    public void onHMAStateChanged(int i) {
    }

    public void onIboosterStateChanged(int i) {
    }

    public void onLDSWTypeChanged(int i) {
    }

    public void onLKSModeChanged(int i) {
    }

    public void onLKSSensitivityChanged(int i) {
    }

    public void onPCWStateChanged(int i) {
    }

    public void onSLAStateChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onFeatureChanged(String str, int i) {
    }
}
