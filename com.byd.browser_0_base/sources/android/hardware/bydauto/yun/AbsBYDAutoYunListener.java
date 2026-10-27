package android.hardware.bydauto.yun;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoYunListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoYunListener";

    public void onChange(CanInfo canInfo) {
        canInfo.toString();
    }

    @Deprecated
    public void onWholeFrameDataChanged(int i, int i2, byte[] bArr) {
    }

    @Deprecated
    public void onWholeFrameDataChanged(byte[] bArr) {
    }

    @Override // android.hardware.IBYDAutoListener
    @Deprecated
    public void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    @Deprecated
    public void onNeedRsendRegisterTable(int i) {
    }

    public void onAppActivateResult(int i, int i2) {
    }

    @Override // android.hardware.IBYDAutoListener
    @Deprecated
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    @Deprecated
    public void onError(int i, String str) {
    }

    @Deprecated
    public void onAcStartResult(int i, int i2, int i3) {
    }

    @Deprecated
    public void onEngStartResult(int i, int i2, int i3) {
    }

    @Deprecated
    public void onEngStopResult(int i, int i2, int i3) {
    }

    @Deprecated
    public void onLockFourDoorsResult(int i, int i2, int i3) {
    }

    @Deprecated
    public void onRemoteCarSearchResult(int i, int i2, int i3) {
    }

    @Deprecated
    public void onSeatVtilatHeatingResult(int i, int i2, int i3) {
    }

    @Deprecated
    public void onUnlockFourDoorsResult(int i, int i2, int i3) {
    }
}
