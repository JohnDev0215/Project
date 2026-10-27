package android.hardware.bydauto.wiper;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoWiperListener implements d {
    protected static final String TAG = "AbsBYDAutoWiperListener";

    public AbsBYDAutoWiperListener() {
        throw new RuntimeException("Stub!");
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public final void onDataChanged(c cVar) {
        throw new RuntimeException("Stub!");
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    public void onError(int i2, String str) {
        throw new RuntimeException("Stub!");
    }

    public void onWindscreenWiperRelayStateChanged(int i2) {
        throw new RuntimeException("Stub!");
    }

    public void onWindscreenWiperResetStateChanged(int i2, int i3) {
        throw new RuntimeException("Stub!");
    }

    public void onWindscreenWiperSensitivityChanged(int i2) {
        throw new RuntimeException("Stub!");
    }
}
