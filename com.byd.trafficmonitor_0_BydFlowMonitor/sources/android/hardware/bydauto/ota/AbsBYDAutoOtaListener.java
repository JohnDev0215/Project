package android.hardware.bydauto.ota;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoOtaListener implements d {
    public static final String TAG = "AbsBYDAutoOtaListener";

    public AbsBYDAutoOtaListener() {
        Log.w(TAG, TAG);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void on7dfExistState(boolean z) {
        Log.w(TAG, "AbsBYDAutoOtaListener.on7DFExist");
    }

    public void onBackupAreaDownloadState(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBackupAreaDownloadState");
    }

    public void onBatteryPowerVoltageChanged(double d2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBatteryPowerVoltageChanged");
    }

    public void onBatteryVoltageChanged(double d2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBatteryVoltageChanged");
    }

    public void onBcmOtaState(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBcmOtaState");
    }

    public void onCanInfoReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onCanInfoReceived");
    }

    public final void onDataChanged(c cVar) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDataChanged");
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDataEventChanged");
    }

    public void onDcState(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDcState");
    }

    public void onDischargeMainContactorStateChanged(int i2) {
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

    public void onError(int i2, String str) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onError");
    }

    public void onFaultCodeInfoReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onFaultCodeInfoReceived");
    }

    public void onIg1RelayState(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg1RelayState");
    }

    public void onIg3RelayState(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg3RelayState");
    }

    public void onIg4RelayState(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg4RelayState");
    }

    public void onLFDoorLockStateChanged(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLFDoorLockStateChanged");
    }

    public void onLFDoorLockStatus(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLFDoorLockStatus");
    }

    public void onLocalDiagStatusChanged(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLocalDiagStatusChanged");
    }

    public void onMCUVersionAndSoftcodeReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onMCUVersionAndSoftcodeReceived");
    }

    public void onNeedRendRegisterTable(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onNeedRendRegisterTable");
    }

    public void onOTAInfoACKChanged(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTAInfoACKChanged");
    }

    public void onOTAStateChanged(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTAStateChanged");
    }

    public void onOTATimecountStateChanged(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTATimecountStateChanged");
    }

    public void onPowerOnForbidStateChanged(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onPowerOnForbidStateChanged");
    }

    public void onPoweroffInformChanged(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onPoweroffInformChanged");
    }

    public void onProhibitPowerOnState(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onProhibitPowerOnState state : " + i2);
    }

    @Deprecated
    public void onSmartChargeState(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSmartChargeState");
    }

    public void onSmartPowerState(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSmartPowerState");
    }

    public void onSocValueChanged(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSocValueChanged");
    }

    public void onStartKeyState(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onStartKeyState state " + i2);
    }

    public void onSyncMcuStateChanged(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSyncMcuStateChanged");
    }

    public void onTurnOffState(int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onTurnOffState");
    }

    public void onUpgradeProgress(int i2, int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onUpgradeProgress");
    }

    public void onUpgradeResult(int i2, int i3, int i4) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onUpgradeResult");
    }

    public void onWholeFrameDataChanged(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onWholeFrameDataChanged");
    }
}
