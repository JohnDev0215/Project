package android.hardware.bydauto.wiper;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoWiperListener implements IBYDAutoListener {
    public static final String TAG = "AbsBYDAutoWiperListener";

    public AbsBYDAutoWiperListener() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        throw new RuntimeException("Stub!");
    }

    public void onWindscreenWiperRelayStateChanged(int i) {
        throw new RuntimeException("Stub!");
    }

    public void onWindscreenWiperResetStateChanged(int i, int i2) {
        throw new RuntimeException("Stub!");
    }

    public void onWindscreenWiperSensitivityChanged(int i) {
        throw new RuntimeException("Stub!");
    }
}
