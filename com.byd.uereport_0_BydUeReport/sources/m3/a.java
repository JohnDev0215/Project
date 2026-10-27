package m3;

import android.hardware.bydauto.digital.IDigitalKeyLisenter;

/* JADX INFO: compiled from: BydIDigitalKeyListener.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements IDigitalKeyLisenter {
    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onAllowStartTimeAfterUnlockChange(int i8) {
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onBleOperateCommand(int i8) {
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onBleOperateResult(int i8) {
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onKeyFindingResults(int i8, int i9) {
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onOfflineOperateCmd(int i8) {
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onOfflineOperateResult(int i8, int i9) {
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onOwnerKeyIdentifierResult(int i8, int i9) {
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onPadOfflineOperationResp(int i8) {
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onResponseFeedbackInform(int i8) {
    }
}
