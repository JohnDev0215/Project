package android.hardware.bydauto.ota;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoOtaListener implements d {
    protected static final String TAG = "AbsBYDAutoOtaListener";

    public AbsBYDAutoOtaListener() {
        Log.w(TAG, TAG);
    }

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public void on7dfExistState(boolean z6) {
        Log.w(TAG, "AbsBYDAutoOtaListener.on7DFExist");
    }

    public void onBackupAreaDownloadState(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBackupAreaDownloadState");
    }

    public void onBatteryPowerVoltageChanged(double d7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBatteryPowerVoltageChanged");
    }

    public void onBatteryVoltageChanged(double d7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBatteryVoltageChanged");
    }

    public void onBcmOtaState(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBcmOtaState");
    }

    public void onCanInfoReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onCanInfoReceived");
    }

    public final void onDataChanged(c cVar) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDataChanged");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDataEventChanged");
    }

    public void onDcState(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDcState");
    }

    public void onDischargeMainContactorStateChanged(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDischargeMainContactorStateChanged");
    }

    public void onECUSoftcodeReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onECUSoftcodeReceived");
    }

    public void onECUVersionAndSoftcodeReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onECUVersionAndSoftcodeReceived");
    }

    public void onECUVersionReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onECUVersionReceived");
    }

    public void onError(int i7, String str) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onError");
    }

    public void onFaultCodeInfoReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onFaultCodeInfoReceived");
    }

    public void onIg1RelayState(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg1RelayState");
    }

    public void onIg3RelayState(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg3RelayState");
    }

    public void onIg4RelayState(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg4RelayState");
    }

    public void onLFDoorLockStateChanged(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLFDoorLockStateChanged");
    }

    public void onLFDoorLockStatus(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLFDoorLockStatus");
    }

    public void onLocalDiagStatusChanged(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLocalDiagStatusChanged");
    }

    public void onMCUVersionAndSoftcodeReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onMCUVersionAndSoftcodeReceived");
    }

    public void onNeedRendRegisterTable(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onNeedRendRegisterTable");
    }

    public void onOTAInfoACKChanged(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTAInfoACKChanged");
    }

    public void onOTAStateChanged(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTAStateChanged");
    }

    public void onOTATimecountStateChanged(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTATimecountStateChanged");
    }

    public void onPowerOnForbidStateChanged(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onPowerOnForbidStateChanged");
    }

    public void onPoweroffInformChanged(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onPoweroffInformChanged");
    }

    public void onProhibitPowerOnState(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onProhibitPowerOnState state : " + i7);
    }

    @Deprecated
    public void onSmartChargeState(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSmartChargeState");
    }

    public void onSmartPowerState(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSmartPowerState");
    }

    public void onStartKeyState(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onStartKeyState state " + i7);
    }

    public void onSyncMcuStateChanged(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSyncMcuStateChanged");
    }

    public void onTurnOffState(int i7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onTurnOffState");
    }

    public void onUpgradeProgress(int i7, int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onUpgradeProgress");
    }

    public void onUpgradeResult(int i7, int i8, int i9) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onUpgradeResult");
    }

    public void onWholeFrameDataChanged(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onWholeFrameDataChanged");
    }
}
