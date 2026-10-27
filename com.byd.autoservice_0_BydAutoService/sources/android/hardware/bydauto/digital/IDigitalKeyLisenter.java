package android.hardware.bydauto.digital;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public interface IDigitalKeyLisenter extends d {
    @Override // b.d
    /* bridge */ /* synthetic */ default void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    /* bridge */ /* synthetic */ default void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    void onAllowStartTimeAfterUnlockChange(int i3);

    void onBleOperateCommand(int i3);

    void onBleOperateResult(int i3);

    default void onDataChanged(c cVar) {
    }

    default void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
    }

    default void onError(int i3, String str) {
    }

    void onKeyFindingResults(int i3, int i4);

    void onNfcOperationCommand(int i3);

    void onOfflineOperateCmd(int i3);

    void onOfflineOperateResult(int i3, int i4);

    void onOwnerKeyIdentifierResult(int i3, int i4);

    void onPadOfflineOperationResp(int i3);

    void onPairingProcessCallBack(int i3, int i4);

    void onResponseFeedbackInform(int i3);
}
