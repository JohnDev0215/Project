package android.hardware.bydauto.digital;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public interface IDigitalKeyLisenter extends d {
    @Override // a.a.d
    /* bridge */ /* synthetic */ default void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.a.d
    /* bridge */ /* synthetic */ default void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    void onAllowStartTimeAfterUnlockChange(int i2);

    void onBleOperateCommand(int i2);

    void onBleOperateResult(int i2);

    default void onDataChanged(c cVar) {
    }

    default void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
    }

    default void onError(int i2, String str) {
    }

    void onKeyFindingResults(int i2, int i3);

    void onNfcOperationCommand(int i2);

    void onOfflineOperateCmd(int i2);

    void onOfflineOperateResult(int i2, int i3);

    void onOwnerKeyIdentifierResult(int i2, int i3);

    void onPadOfflineOperationResp(int i2);

    void onPairingProcessCallBack(int i2, int i3);

    void onResponseFeedbackInform(int i2);
}
