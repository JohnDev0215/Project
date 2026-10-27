package android.hardware.bydauto.yun;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoYunListener implements d {
    protected static final String TAG = "AbsBYDAutoYunListener";

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    @Deprecated
    public void onAcStartResult(int i7, int i8, int i9) {
    }

    public void onAppActivateResult(int i7, int i8) {
        Log.d(TAG, "onAppActivateResult");
    }

    public void onChange(CanInfo canInfo) {
        Log.d(TAG, "can info is " + canInfo.toString());
    }

    @Deprecated
    public void onDataChanged(c cVar) {
    }

    @Deprecated
    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Deprecated
    public void onEngStartResult(int i7, int i8, int i9) {
    }

    @Deprecated
    public void onEngStopResult(int i7, int i8, int i9) {
    }

    @Deprecated
    public void onError(int i7, String str) {
    }

    @Deprecated
    public void onLockFourDoorsResult(int i7, int i8, int i9) {
    }

    @Deprecated
    public void onNeedRsendRegisterTable(int i7) {
    }

    @Deprecated
    public void onRemoteCarSearchResult(int i7, int i8, int i9) {
    }

    @Deprecated
    public void onSeatVtilatHeatingResult(int i7, int i8, int i9) {
    }

    @Deprecated
    public void onUnlockFourDoorsResult(int i7, int i8, int i9) {
    }

    @Deprecated
    public void onWholeFrameDataChanged(int i7, int i8, byte[] bArr) {
    }

    @Deprecated
    public void onWholeFrameDataChanged(byte[] bArr) {
    }
}
