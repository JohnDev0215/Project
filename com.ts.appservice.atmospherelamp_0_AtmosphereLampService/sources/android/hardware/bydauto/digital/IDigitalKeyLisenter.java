package android.hardware.bydauto.digital;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public interface IDigitalKeyLisenter extends IBYDAutoListener {
    void onAllowStartTimeAfterUnlockChange(int i);

    void onBleOperateCommand(int i);

    void onBleOperateResult(int i);

    void onKeyFindingResults(int i, int i2);

    void onNfcKeyUnlockResultChange(int i);

    void onNfcOperationCommand(int i);

    void onOfflineOperateCmd(int i);

    void onOfflineOperateResult(int i, int i2);

    void onOpcodeChecksumValueChange(int i);

    void onOpcodeCommandChange(int i);

    void onOpcodeDataLengthChange(int i);

    void onOpcodeDataValueChange(byte[] bArr);

    void onOpcodeStartFunctionChange(int i);

    void onOwnerKeyIdentifierResult(int i, int i2);

    void onPadExitPinCodeProcessChange(int i);

    void onPadOfflineOperationResp(int i);

    void onPairingProcessCallBack(int i, int i2);

    void onResponseFeedbackInform(int i);

    void onResponseResultOfOpcodeChange(int i);

    @Override // android.hardware.IBYDAutoListener
    default void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    default void onDataChanged(IBYDAutoEvent var1) {
    }

    @Override // android.hardware.IBYDAutoListener
    default void onError(int var1, String var2) {
    }
}
