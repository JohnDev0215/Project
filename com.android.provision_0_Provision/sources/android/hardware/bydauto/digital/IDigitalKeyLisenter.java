package android.hardware.bydauto.digital;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public interface IDigitalKeyLisenter extends IBYDAutoListener {
    void onAllowStartTimeAfterUnlockChange(int i);

    @Override // android.hardware.IBYDAutoListener
    default void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    @Override // android.hardware.IBYDAutoListener
    default void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    default void onError(int i, String str) {
    }

    void onKeyFindingResults(int i, int i2);

    void onNfcOperationCommand(int i);

    void onOfflineOperateCmd(int i);

    void onOfflineOperateResult(int i, int i2);

    void onOwnerKeyIdentifierResult(int i, int i2);

    void onPadOfflineOperationResp(int i);

    void onPairingProcessCallBack(int i, int i2);

    void onResponseFeedbackInform(int i);
}
