package android.hardware.bydauto.digital;

import android.hardware.bydauto.BYDAutoEventValue;
import defpackage.d;
import defpackage.e;

/* JADX INFO: loaded from: classes.dex */
public interface IDigitalKeyLisenter extends e {
    @Override // defpackage.e
    /* bridge */ /* synthetic */ default void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // defpackage.e
    /* bridge */ /* synthetic */ default void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    void onAllowStartTimeAfterUnlockChange(int i);

    void onBleOperateCommand(int i);

    void onBleOperateResult(int i);

    default void onDataChanged(d dVar) {
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
