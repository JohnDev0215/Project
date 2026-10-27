package android.hardware.bydauto.yun;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoYunListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoYunListener";

    public void onAppActivateResult(int result, int appTag) {
        Log.d(TAG, "onAppActivateResult");
    }

    @Deprecated
    public void onNeedRsendRegisterTable(int value) {
    }

    @Deprecated
    public void onWholeFrameDataChanged(byte[] data) {
    }

    @Deprecated
    public void onWholeFrameDataChanged(int id, int cid, byte[] data) {
    }

    @Override // android.hardware.IBYDAutoListener
    @Deprecated
    public void onError(int errCode, String errMessage) {
    }

    @Override // android.hardware.IBYDAutoListener
    @Deprecated
    public void onDataChanged(IBYDAutoEvent event) {
    }

    @Override // android.hardware.IBYDAutoListener
    @Deprecated
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
    }

    @Deprecated
    public void onUnlockFourDoorsResult(int p1, int p2, int p3) {
    }

    @Deprecated
    public void onLockFourDoorsResult(int p1, int p2, int p3) {
    }

    @Deprecated
    public void onRemoteCarSearchResult(int p1, int p2, int p3) {
    }

    @Deprecated
    public void onEngStartResult(int p1, int p2, int p3) {
    }

    @Deprecated
    public void onEngStopResult(int p1, int p2, int p3) {
    }

    @Deprecated
    public void onAcStartResult(int p1, int p2, int p3) {
    }

    @Deprecated
    public void onSeatVtilatHeatingResult(int p1, int p2, int p3) {
    }

    public void onChange(CanInfo canInfo) {
        Log.d(TAG, "can info is " + canInfo.toString());
    }
}
