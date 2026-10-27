package android.hardware.bydauto.rescue;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRescueListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoRescueListener";

    public AbsBYDAutoRescueListener() {
        throw new RuntimeException("Stub!");
    }

    public void onInfoFromMCUChanged(int info) {
        throw new RuntimeException("Stub!");
    }

    public void onAirbagStateChanged(int state) {
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
