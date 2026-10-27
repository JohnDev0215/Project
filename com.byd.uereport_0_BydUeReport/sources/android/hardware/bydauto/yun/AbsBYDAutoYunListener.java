package android.hardware.bydauto.yun;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoYunListener implements d {
    public static final String TAG = "AbsBYDAutoYunListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    @Deprecated
    public void onAcStartResult(int i8, int i9, int i10) {
    }

    public void onAppActivateResult(int i8, int i9) {
        Log.d(TAG, "onAppActivateResult");
    }

    public void onChange(CanInfo canInfo) {
        Log.d(TAG, "can info is " + canInfo.toString());
    }

    @Deprecated
    public void onDataChanged(c cVar) {
    }

    @Deprecated
    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Deprecated
    public void onEngStartResult(int i8, int i9, int i10) {
    }

    @Deprecated
    public void onEngStopResult(int i8, int i9, int i10) {
    }

    @Deprecated
    public void onError(int i8, String str) {
    }

    @Deprecated
    public void onLockFourDoorsResult(int i8, int i9, int i10) {
    }

    @Deprecated
    public void onNeedRsendRegisterTable(int i8) {
    }

    @Deprecated
    public void onRemoteCarSearchResult(int i8, int i9, int i10) {
    }

    @Deprecated
    public void onSeatVtilatHeatingResult(int i8, int i9, int i10) {
    }

    @Deprecated
    public void onUnlockFourDoorsResult(int i8, int i9, int i10) {
    }

    @Deprecated
    public void onWholeFrameDataChanged(int i8, int i9, byte[] bArr) {
    }

    @Deprecated
    public void onWholeFrameDataChanged(byte[] bArr) {
    }
}
