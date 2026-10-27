package android.hardware.bydauto.collision;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoCollisionListener implements d {
    protected static final String TAG = "AbsBYDAutoCollisionListener";

    public AbsBYDAutoCollisionListener() {
        throw new RuntimeException("Stub!");
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onCollisionSignalStateChanged(byte[] bArr) {
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
