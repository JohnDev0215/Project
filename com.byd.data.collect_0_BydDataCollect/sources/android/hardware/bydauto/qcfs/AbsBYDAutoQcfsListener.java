package android.hardware.bydauto.qcfs;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoQcfsListener implements d {
    protected static final String TAG = "AbsBYDAutoQcfsListener";

    public AbsBYDAutoQcfsListener() {
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

    public void onEcuVersionInd(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public void onError(int i2, String str) {
        throw new RuntimeException("Stub!");
    }

    public void onFsQurQcStateInd() {
        throw new RuntimeException("Stub!");
    }

    public void onKeyIdInd(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public void onNewVerUpgradeCond(int i2) {
        throw new RuntimeException("Stub!");
    }

    public void onOtaStateInd(int i2) {
        throw new RuntimeException("Stub!");
    }

    public void onRcvKeyCond(int i2) {
        throw new RuntimeException("Stub!");
    }

    public void onUpgradeCond(int i2) {
        throw new RuntimeException("Stub!");
    }

    public void onUpgradeCond(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }
}
