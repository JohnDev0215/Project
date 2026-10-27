package android.hardware.bydauto.ota;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.ac.tsimpl.a;
import b.c;
import b.d;
import h6.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoOtaListener implements d {
    protected static final String TAG = "AbsBYDAutoOtaListener";

    public AbsBYDAutoOtaListener() {
        n.t(TAG, TAG);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void on7dfExistState(boolean z6) {
        n.t(TAG, "AbsBYDAutoOtaListener.on7DFExist");
    }

    public void onBackupAreaDownloadState(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onBackupAreaDownloadState");
    }

    public void onBatteryPowerVoltageChanged(double d) {
        n.t(TAG, "AbsBYDAutoOtaListener.onBatteryPowerVoltageChanged");
    }

    public void onBatteryVoltageChanged(double d) {
        n.t(TAG, "AbsBYDAutoOtaListener.onBatteryVoltageChanged");
    }

    public void onBcmOtaState(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onBcmOtaState");
    }

    public void onCanInfoReceived(byte[] bArr) {
        n.t(TAG, "AbsBYDAutoOtaListener.onCanInfoReceived");
    }

    public final void onDataChanged(c cVar) {
        n.t(TAG, "AbsBYDAutoOtaListener.onDataChanged");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        n.t(TAG, "AbsBYDAutoOtaListener.onDataEventChanged");
    }

    public void onDcState(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onDcState");
    }

    public void onDischargeMainContactorStateChanged(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onDischargeMainContactorStateChanged");
    }

    public void onECUSoftcodeReceived(byte[] bArr) {
        n.t(TAG, "AbsBYDAutoOtaListener.onECUSoftcodeReceived");
    }

    public void onECUVersionAndSoftcodeReceived(byte[] bArr) {
        n.t(TAG, "AbsBYDAutoOtaListener.onECUVersionAndSoftcodeReceived");
    }

    public void onECUVersionReceived(byte[] bArr) {
        n.t(TAG, "AbsBYDAutoOtaListener.onECUVersionReceived");
    }

    public void onError(int i7, String str) {
        n.t(TAG, "AbsBYDAutoOtaListener.onError");
    }

    public void onFaultCodeInfoReceived(byte[] bArr) {
        n.t(TAG, "AbsBYDAutoOtaListener.onFaultCodeInfoReceived");
    }

    public void onIg1RelayState(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onIg1RelayState");
    }

    public void onIg3RelayState(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onIg3RelayState");
    }

    public void onIg4RelayState(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onIg4RelayState");
    }

    public void onLFDoorLockStateChanged(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onLFDoorLockStateChanged");
    }

    public void onLFDoorLockStatus(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onLFDoorLockStatus");
    }

    public void onLocalDiagStatusChanged(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onLocalDiagStatusChanged");
    }

    public void onMCUVersionAndSoftcodeReceived(byte[] bArr) {
        n.t(TAG, "AbsBYDAutoOtaListener.onMCUVersionAndSoftcodeReceived");
    }

    public void onNeedRendRegisterTable(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onNeedRendRegisterTable");
    }

    public void onOTAInfoACKChanged(byte[] bArr) {
        n.t(TAG, "AbsBYDAutoOtaListener.onOTAInfoACKChanged");
    }

    public void onOTAStateChanged(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onOTAStateChanged");
    }

    public void onOTATimecountStateChanged(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onOTATimecountStateChanged");
    }

    public void onPowerOnForbidStateChanged(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onPowerOnForbidStateChanged");
    }

    public void onPoweroffInformChanged(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onPoweroffInformChanged");
    }

    public void onProhibitPowerOnState(int i7) {
        a.r("AbsBYDAutoOtaListener.onProhibitPowerOnState state : ", i7, TAG);
    }

    @Deprecated
    public void onSmartChargeState(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onSmartChargeState");
    }

    public void onSmartPowerState(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onSmartPowerState");
    }

    public void onSocValueChanged(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onSocValueChanged");
    }

    public void onStartKeyState(int i7) {
        a.r("AbsBYDAutoOtaListener.onStartKeyState state ", i7, TAG);
    }

    public void onSyncMcuStateChanged(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onSyncMcuStateChanged");
    }

    public void onTurnOffState(int i7) {
        n.t(TAG, "AbsBYDAutoOtaListener.onTurnOffState");
    }

    public void onUpgradeProgress(int i7, int i10) {
        n.t(TAG, "AbsBYDAutoOtaListener.onUpgradeProgress");
    }

    public void onUpgradeResult(int i7, int i10, int i11) {
        n.t(TAG, "AbsBYDAutoOtaListener.onUpgradeResult");
    }

    public void onWholeFrameDataChanged(byte[] bArr) {
        n.t(TAG, "AbsBYDAutoOtaListener.onWholeFrameDataChanged");
    }
}
