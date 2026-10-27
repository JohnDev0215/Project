package android.hardware.bydauto.digital;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.digital.tsimpl.DigitalKeyBean;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDigitalKeyLisenter extends d {
    @Override // a.a.d
    /* bridge */ /* synthetic */ default void isConnected(boolean z) {
        super.isConnected(z);
    }

    void onAllowStartTimeAfterUnlockChange(int i);

    default void onDataChanged(c cVar) {
    }

    default void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    void onDeleteAllNfcKeyResult(int i, int i2);

    void onDeleteNfcKeyByKeyNoResult(String str, int i);

    void onDeleteNfcKeyResult(int i, int i2);

    void onDeleteOwnIphoneKeyResult(String str, int i);

    default void onError(int i, String str) {
    }

    void onKeyFindingResults(int i, int i2);

    void onKeyListPageRefresh();

    void onKeyListResponse(int i, List<DigitalKeyBean> list);

    void onNfcOperationCommand(int i);

    void onOfflineOperateCmd(int i);

    void onOfflineOperateResult(int i, int i2);

    void onOwnerKeyIdentifierResult(int i, int i2);

    void onPairingProcessCallBack(int i, int i2);

    void onResponseFeedbackInform(int i);
}
