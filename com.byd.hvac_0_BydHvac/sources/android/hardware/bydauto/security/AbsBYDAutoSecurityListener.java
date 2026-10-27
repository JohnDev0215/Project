package android.hardware.bydauto.security;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSecurityListener implements d {
    public static final String TAG = "AbsBYDAutoSecurityListener";

    public AbsBYDAutoSecurityListener() {
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

    public final void onDataChanged(c cVar) {
        throw new RuntimeException("Stub!");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    public void onError(int i, String str) {
        throw new RuntimeException("Stub!");
    }

    public void onSecurityStateChanged(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }
}
