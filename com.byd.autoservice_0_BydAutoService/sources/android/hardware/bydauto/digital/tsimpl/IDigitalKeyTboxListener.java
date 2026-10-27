package android.hardware.bydauto.digital.tsimpl;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDigitalKeyTboxListener {
    void onDeleteAllNfcKeyResult(int i3, int i4);

    void onDeleteNfcKeyByKeyNoResult(String str, int i3);

    void onDeleteOwnIphoneKeyResult(String str, int i3);

    void onKeyListPageRefresh();

    void onKeyListResponse(int i3, List<DigitalKeyBean> list);
}
