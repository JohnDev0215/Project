package android.hardware.bydauto.digital;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public interface IDigitalKeyLisenter extends IBYDAutoListener {

    /* JADX INFO: renamed from: android.hardware.bydauto.digital.IDigitalKeyLisenter$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onDataChanged(IDigitalKeyLisenter _this, IBYDAutoEvent iBYDAutoEvent) {
        }

        public static void $default$onDataEventChanged(IDigitalKeyLisenter _this, int i, BYDAutoEventValue bYDAutoEventValue) {
        }

        public static void $default$onError(IDigitalKeyLisenter _this, int i, String str) {
        }
    }

    void onAllowStartTimeAfterUnlockChange(int i);

    @Override // android.hardware.IBYDAutoListener
    void onDataChanged(IBYDAutoEvent iBYDAutoEvent);

    @Override // android.hardware.IBYDAutoListener
    void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue);

    @Override // android.hardware.IBYDAutoListener
    void onError(int i, String str);

    void onKeyFindingResults(int i, int i2);

    void onNfcOperationCommand(int i);

    void onOfflineOperateCmd(int i);

    void onOfflineOperateResult(int i, int i2);

    void onOwnerKeyIdentifierResult(int i, int i2);

    void onPadOfflineOperationResp(int i);

    void onPairingProcessCallBack(int i, int i2);

    void onResponseFeedbackInform(int i);
}
