package android.hardware.bydauto.auxiliary;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoAuxListener implements d {
    protected static final String TAG = "AbsBYDAutoAuxListener";

    public AbsBYDAutoAuxListener() {
        throw new RuntimeException("Stub!");
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onAuxConnectStatusChanged(int i7) {
        throw new RuntimeException("Stub!");
    }

    public void onAuxSignalStatusChanged(int i7) {
        throw new RuntimeException("Stub!");
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
}
