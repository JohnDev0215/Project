package android.hardware.bydauto.wiper;

import android.hardware.bydauto.BYDAutoEventValue;
import b.b.c;
import b.b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoWiperListener implements d {
    protected static final String TAG = "AbsBYDAutoWiperListener";

    public AbsBYDAutoWiperListener() {
        throw new RuntimeException("Stub!");
    }

    @Override // b.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // b.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public final void onDataChanged(c cVar) {
        throw new RuntimeException("Stub!");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

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
