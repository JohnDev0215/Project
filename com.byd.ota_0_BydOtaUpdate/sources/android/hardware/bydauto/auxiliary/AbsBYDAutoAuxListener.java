package android.hardware.bydauto.auxiliary;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoAuxListener implements d {
    public static final String TAG = "AbsBYDAutoAuxListener";

    public AbsBYDAutoAuxListener() {
        throw new RuntimeException("Stub!");
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onAuxConnectStatusChanged(int i2) {
        throw new RuntimeException("Stub!");
    }

    public void onAuxSignalStatusChanged(int i2) {
        throw new RuntimeException("Stub!");
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
}
