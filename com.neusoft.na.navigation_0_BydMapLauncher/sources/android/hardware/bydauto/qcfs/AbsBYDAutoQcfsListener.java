package android.hardware.bydauto.qcfs;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoQcfsListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoQcfsListener";

    public AbsBYDAutoQcfsListener() {
        throw new RuntimeException("Stub!");
    }

    public void onEcuVersionInd(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public void onNewVerUpgradeCond(int i) {
        throw new RuntimeException("Stub!");
    }

    public void onRcvKeyCond(int i) {
        throw new RuntimeException("Stub!");
    }

    public void onUpgradeCond(int i) {
        throw new RuntimeException("Stub!");
    }

    public void onUpgradeCond(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public void onKeyIdInd(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public void onOtaStateInd(int i) {
        throw new RuntimeException("Stub!");
    }

    public void onFsQurQcStateInd() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }
}
