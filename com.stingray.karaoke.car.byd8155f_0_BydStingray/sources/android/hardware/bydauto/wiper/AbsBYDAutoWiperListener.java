package android.hardware.bydauto.wiper;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoWiperListener implements d {
    protected static final String TAG = "AbsBYDAutoWiperListener";

    public AbsBYDAutoWiperListener() {
        throw new RuntimeException("Stub!");
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public final void onDataChanged(c cVar) {
        throw new RuntimeException("Stub!");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    public void onError(int i7, String str) {
        throw new RuntimeException("Stub!");
    }

    public void onWindscreenWiperRelayStateChanged(int i7) {
        throw new RuntimeException("Stub!");
    }

    public void onWindscreenWiperResetStateChanged(int i7, int i10) {
        throw new RuntimeException("Stub!");
    }

    public void onWindscreenWiperSensitivityChanged(int i7) {
        throw new RuntimeException("Stub!");
    }
}
