package android.hardware.bydauto.yun;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoYunListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoYunListener";

    @Deprecated
    public void onAcStartResult(int i, int i2, int i3) {
    }

    @Override // android.hardware.IBYDAutoListener
    @Deprecated
    public void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    @Override // android.hardware.IBYDAutoListener
    @Deprecated
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Deprecated
    public void onEngStartResult(int i, int i2, int i3) {
    }

    @Deprecated
    public void onEngStopResult(int i, int i2, int i3) {
    }

    @Override // android.hardware.IBYDAutoListener
    @Deprecated
    public void onError(int i, String str) {
    }

    @Deprecated
    public void onLockFourDoorsResult(int i, int i2, int i3) {
    }

    @Deprecated
    public void onNeedRsendRegisterTable(int i) {
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

    @Deprecated
    public void onWholeFrameDataChanged(int i, int i2, byte[] bArr) {
    }

    @Deprecated
    public void onWholeFrameDataChanged(byte[] bArr) {
    }

    public void onAppActivateResult(int i, int i2) {
        Log.d(TAG, "onAppActivateResult");
    }

    public void onChange(CanInfo canInfo) {
        Log.d(TAG, "can info is " + canInfo.toString());
    }
}
