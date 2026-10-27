package android.hardware.bydauto.auxiliary;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoAuxListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoAuxListener";

    public AbsBYDAutoAuxListener() {
        throw new RuntimeException("Stub!");
    }

    public void onAuxConnectStatusChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onAuxSignalStatusChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        throw new RuntimeException("Stub!");
    }
}
