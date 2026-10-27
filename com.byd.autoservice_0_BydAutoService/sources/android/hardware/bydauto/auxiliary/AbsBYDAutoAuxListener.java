package android.hardware.bydauto.auxiliary;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoAuxListener implements d {
    public static final String TAG = "AbsBYDAutoAuxListener";

    public AbsBYDAutoAuxListener() {
        throw new RuntimeException("Stub!");
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onAuxConnectStatusChanged(int i3) {
        throw new RuntimeException("Stub!");
    }

    public void onAuxSignalStatusChanged(int i3) {
        throw new RuntimeException("Stub!");
    }

    public final void onDataChanged(c cVar) {
        throw new RuntimeException("Stub!");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    public void onError(int i3, String str) {
        throw new RuntimeException("Stub!");
    }
}
