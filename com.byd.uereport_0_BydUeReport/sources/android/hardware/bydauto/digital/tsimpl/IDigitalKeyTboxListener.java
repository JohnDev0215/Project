package android.hardware.bydauto.digital.tsimpl;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDigitalKeyTboxListener {
    void onDeleteAllNfcKeyResult(int i8, int i9);

    void onDeleteNfcKeyByKeyNoResult(String str, int i8);

    void onDeleteOwnIphoneKeyResult(String str, int i8);

    void onKeyListPageRefresh();

    void onKeyListResponse(int i8, List<DigitalKeyBean> list);
}
