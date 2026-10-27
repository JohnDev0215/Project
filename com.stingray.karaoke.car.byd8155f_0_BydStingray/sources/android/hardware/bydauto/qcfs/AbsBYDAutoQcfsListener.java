package android.hardware.bydauto.qcfs;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoQcfsListener implements d {
    protected static final String TAG = "AbsBYDAutoQcfsListener";

    public AbsBYDAutoQcfsListener() {
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

    public void onEcuVersionInd(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public void onError(int i7, String str) {
        throw new RuntimeException("Stub!");
    }

    public void onFsQurQcStateInd() {
        throw new RuntimeException("Stub!");
    }

    public void onKeyIdInd(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public void onNewVerUpgradeCond(int i7) {
        throw new RuntimeException("Stub!");
    }

    public void onOtaStateInd(int i7) {
        throw new RuntimeException("Stub!");
    }

    public void onRcvKeyCond(int i7) {
        throw new RuntimeException("Stub!");
    }

    public void onUpgradeCond(int i7) {
        throw new RuntimeException("Stub!");
    }

    public void onUpgradeCond(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }
}
