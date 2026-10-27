package android.hardware.bydauto.qcfs;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoQcfsListener implements d {
    public static final String TAG = "AbsBYDAutoQcfsListener";

    public AbsBYDAutoQcfsListener() {
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

    public final void onDataChanged(c cVar) {
        throw new RuntimeException("Stub!");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    public void onEcuVersionInd(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public void onError(int i3, String str) {
        throw new RuntimeException("Stub!");
    }

    public void onFsQurQcStateInd() {
        throw new RuntimeException("Stub!");
    }

    public void onKeyIdInd(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public void onNewVerUpgradeCond(int i3) {
        throw new RuntimeException("Stub!");
    }

    public void onOtaStateInd(int i3) {
        throw new RuntimeException("Stub!");
    }

    public void onRcvKeyCond(int i3) {
        throw new RuntimeException("Stub!");
    }

    public void onUpgradeCond(int i3) {
        throw new RuntimeException("Stub!");
    }

    public void onUpgradeCond(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }
}
