package android.hardware.bydauto.digital.tsimpl;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDigitalKeyTboxListener {
    void onDeleteAllNfcKeyResult(int i, int i2);

    void onDeleteNfcKeyByKeyNoResult(String str, int i);

    void onDeleteOwnIphoneKeyResult(String str, int i);

    void onKeyListPageRefresh();

    void onKeyListResponse(int i, List<DigitalKeyBean> list);
}
