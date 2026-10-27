package android.hardware.bydauto.digital;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public interface IDigitalKeyLisenter extends d {
    @Override // b.d
    /* bridge */ /* synthetic */ default void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    /* bridge */ /* synthetic */ default void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    void onAllowStartTimeAfterUnlockChange(int i8);

    void onBleOperateCommand(int i8);

    void onBleOperateResult(int i8);

    default void onDataChanged(c cVar) {
    }

    default void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
    }

    default void onError(int i8, String str) {
    }

    void onKeyFindingResults(int i8, int i9);

    void onNfcOperationCommand(int i8);

    void onOfflineOperateCmd(int i8);

    void onOfflineOperateResult(int i8, int i9);

    void onOwnerKeyIdentifierResult(int i8, int i9);

    void onPadOfflineOperationResp(int i8);

    void onPairingProcessCallBack(int i8, int i9);

    void onResponseFeedbackInform(int i8);
}
