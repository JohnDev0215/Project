package b.b.j;

import android.hardware.bydauto.digital.IDigitalKeyLisenter;

/* JADX INFO: compiled from: AbsDigitalKeyListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements IDigitalKeyLisenter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f1933b = "AbsDigitalKeyListener";

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onAllowStartTimeAfterUnlockChange(int i) {
        b.b.a.f.f.b(this.f1933b, "onAllowStartTimeAfterUnlockChange %d", Integer.valueOf(i));
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onBleOperateCommand(int i) {
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onBleOperateResult(int i) {
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onKeyFindingResults(int i, int i2) {
        b.b.a.f.f.b(this.f1933b, "onKeyFindingResults result:%d config:%d", Integer.valueOf(i), Integer.valueOf(i2));
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onNfcOperationCommand(int i) {
        b.b.a.f.f.b(this.f1933b, "onNfcOperationCommand", new Object[0]);
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onOfflineOperateCmd(int i) {
        b.b.a.f.f.b(this.f1933b, "onOfflineOperateCmd %d", Integer.valueOf(i));
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onOfflineOperateResult(int i, int i2) {
        b.b.a.f.f.b(this.f1933b, "onOfflineOperateResult result:%d config:%d", Integer.valueOf(i), Integer.valueOf(i2));
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onOwnerKeyIdentifierResult(int i, int i2) {
        b.b.a.f.f.b(this.f1933b, "onOwnerKeyIdentifierResult result:%d config:%d", Integer.valueOf(i), Integer.valueOf(i2));
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onPadOfflineOperationResp(int i) {
        b.b.a.f.f.b(this.f1933b, "onPadOfflineOperationResp %d", Integer.valueOf(i));
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onPairingProcessCallBack(int i, int i2) {
        b.b.a.f.f.b(this.f1933b, "onPairingProcessCallBack result:%d config:%d", Integer.valueOf(i), Integer.valueOf(i2));
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onResponseFeedbackInform(int i) {
        b.b.a.f.f.b(this.f1933b, "onResponseFeedbackInform %d", Integer.valueOf(i));
    }
}
