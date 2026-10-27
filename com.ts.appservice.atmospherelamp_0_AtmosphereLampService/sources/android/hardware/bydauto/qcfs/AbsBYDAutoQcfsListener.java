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

    public void onEcuVersionInd(byte[] value) {
        throw new RuntimeException("Stub!");
    }

    public void onNewVerUpgradeCond(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onRcvKeyCond(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onUpgradeCond(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onUpgradeCond(byte[] value) {
        throw new RuntimeException("Stub!");
    }

    public void onKeyIdInd(byte[] value) {
        throw new RuntimeException("Stub!");
    }

    public void onOtaStateInd(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onFsQurQcStateInd() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        throw new RuntimeException("Stub!");
    }
}
