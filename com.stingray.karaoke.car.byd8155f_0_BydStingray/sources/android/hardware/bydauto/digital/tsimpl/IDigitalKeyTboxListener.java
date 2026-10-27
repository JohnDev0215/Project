package android.hardware.bydauto.digital.tsimpl;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDigitalKeyTboxListener {
    void onDeleteAllNfcKeyResult(int i7, int i10);

    void onDeleteNfcKeyByKeyNoResult(String str, int i7);

    void onDeleteOwnIphoneKeyResult(String str, int i7);

    void onKeyListPageRefresh();

    void onKeyListResponse(int i7, List<DigitalKeyBean> list);
}
