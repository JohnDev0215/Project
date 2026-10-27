package android.hardware.bydauto.digital;

import android.hardware.bydauto.BYDAutoEventValue;
import b.b.c;
import b.b.d;

/* JADX INFO: loaded from: classes.dex */
public interface IDigitalKeyLisenter extends d {
    @Override // b.b.d
    /* bridge */ /* synthetic */ default void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // b.b.d
    /* bridge */ /* synthetic */ default void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    void onAllowStartTimeAfterUnlockChange(int i);

    void onBleOperateCommand(int i);

    void onBleOperateResult(int i);

    default void onDataChanged(c cVar) {
    }

    default void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

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
