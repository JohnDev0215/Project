package android.hardware.bydauto.digital.tsimpl;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDigitalKeyTboxListener {
    void onDeleteAllNfcKeyResult(int i2, int i3);

    void onDeleteNfcKeyByKeyNoResult(String str, int i2);

    void onDeleteOwnIphoneKeyResult(String str, int i2);

    void onKeyListPageRefresh();

    void onKeyListResponse(int i2, List<DigitalKeyBean> list);
}
