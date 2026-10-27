package android.hardware.bydauto.yun;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoYunListener implements d {
    public static final String TAG = "AbsBYDAutoYunListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    @Deprecated
    public void onAcStartResult(int i3, int i4, int i5) {
    }

    public void onAppActivateResult(int i3, int i4) {
        Log.d(TAG, "onAppActivateResult");
    }

    public void onChange(CanInfo canInfo) {
        Log.d(TAG, "can info is " + canInfo.toString());
    }

    @Deprecated
    public void onDataChanged(c cVar) {
    }

    @Deprecated
    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Deprecated
    public void onEngStartResult(int i3, int i4, int i5) {
    }

    @Deprecated
    public void onEngStopResult(int i3, int i4, int i5) {
    }

    @Deprecated
    public void onError(int i3, String str) {
    }

    @Deprecated
    public void onLockFourDoorsResult(int i3, int i4, int i5) {
    }

    @Deprecated
    public void onNeedRsendRegisterTable(int i3) {
    }

    @Deprecated
    public void onRemoteCarSearchResult(int i3, int i4, int i5) {
    }

    @Deprecated
    public void onSeatVtilatHeatingResult(int i3, int i4, int i5) {
    }

    @Deprecated
    public void onUnlockFourDoorsResult(int i3, int i4, int i5) {
    }

    @Deprecated
    public void onWholeFrameDataChanged(int i3, int i4, byte[] bArr) {
    }

    @Deprecated
    public void onWholeFrameDataChanged(byte[] bArr) {
    }
}
