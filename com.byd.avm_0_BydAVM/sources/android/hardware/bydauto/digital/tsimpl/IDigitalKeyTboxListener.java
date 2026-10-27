package android.hardware.bydauto.digital.tsimpl;

import com.ts.ota.otasdkmgr.DigitalKeyBeanSdk;
import com.ts.ota.otasdkmgr.IAbsBYDTBoxListener;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDigitalKeyTboxListener extends IAbsBYDTBoxListener {
    void onAppActivateResult(int i, int i2);

    void onDeleteAllNfcKeyResult(int i, int i2);

    void onDeleteNfcKeyByKeyNoResult(String str, int i);

    void onDeleteNfcKeyResult(int i, int i2);

    void onDeleteOwnIphoneKeyResult(String str, int i);

    void onKeyListPageRefresh();

    void onKeyListResponse(int i, List<DigitalKeyBeanSdk> list);

    void onMobileNetworkStateChanged(int i);

    void onNfcOperationCommand(int i);
}
