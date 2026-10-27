package android.hardware.bydauto.yun;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoYunListener implements d {
    protected static final String TAG = "AbsBYDAutoYunListener";

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    @Deprecated
    public void onAcStartResult(int i, int i2, int i3) {
    }

    public void onAppActivateResult(int i, int i2) {
        Log.d(TAG, "onAppActivateResult");
    }

    public void onChange(CanInfo canInfo) {
        Log.d(TAG, "can info is " + canInfo.toString());
    }

    @Deprecated
    public void onDataChanged(c cVar) {
    }

    @Deprecated
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Deprecated
    public void onEngStartResult(int i, int i2, int i3) {
    }

    @Deprecated
    public void onEngStopResult(int i, int i2, int i3) {
    }

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
}
