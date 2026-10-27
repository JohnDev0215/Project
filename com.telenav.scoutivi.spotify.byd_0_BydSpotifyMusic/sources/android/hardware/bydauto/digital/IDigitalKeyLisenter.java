package android.hardware.bydauto.digital;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public interface IDigitalKeyLisenter extends d {
    @Override // a.d
    /* bridge */ /* synthetic */ default void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    void onAllowStartTimeAfterUnlockChange(int i7);

    default void onDataChanged(c cVar) {
    }

    default void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
    }

    default void onError(int i7, String str) {
    }

    void onKeyFindingResults(int i7, int i8);

    void onNfcOperationCommand(int i7);

    void onOfflineOperateCmd(int i7);

    void onOfflineOperateResult(int i7, int i8);

    void onOwnerKeyIdentifierResult(int i7, int i8);

    void onPadOfflineOperationResp(int i7);

    void onPairingProcessCallBack(int i7, int i8);

    void onResponseFeedbackInform(int i7);
}
