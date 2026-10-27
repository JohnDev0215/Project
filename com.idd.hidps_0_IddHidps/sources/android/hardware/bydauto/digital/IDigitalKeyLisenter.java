package android.hardware.bydauto.digital;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.digital.tsimpl.DigitalKeyBean;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDigitalKeyLisenter extends IBYDAutoListener {
    void onAllowStartTimeAfterUnlockChange(int i);

    @Override // android.hardware.IBYDAutoListener
    default void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    @Override // android.hardware.IBYDAutoListener
    default void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    void onDeleteAllNfcKeyResult(int i, int i2);

    void onDeleteNfcKeyByKeyNoResult(String str, int i);

    void onDeleteNfcKeyResult(int i, int i2);

    void onDeleteOwnIphoneKeyResult(String str, int i);

    @Override // android.hardware.IBYDAutoListener
    default void onError(int i, String str) {
    }

    void onKeyFindingResults(int i);

    void onKeyListPageRefresh();

    void onKeyListResponse(int i, List<DigitalKeyBean> list);

    void onNfcOperationCommand(int i);

    void onOfflineOperateCmd(int i);

    void onOfflineOperateResult(int i);

    void onOwnerKeyIdentifierResult(int i);

    void onPairingProcessCallBack(int i);
}
