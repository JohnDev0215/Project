package android.hardware.bydauto.ota;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoOtaListener implements d {
    public static final String TAG = "AbsBYDAutoOtaListener";

    public AbsBYDAutoOtaListener() {
        Log.w(TAG, TAG);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void on7dfExistState(boolean z7) {
        Log.w(TAG, "AbsBYDAutoOtaListener.on7DFExist");
    }

    public void onBackupAreaDownloadState(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBackupAreaDownloadState");
    }

    public void onBatteryPowerVoltageChanged(double d8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBatteryPowerVoltageChanged");
    }

    public void onBatteryVoltageChanged(double d8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBatteryVoltageChanged");
    }

    public void onBcmOtaState(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBcmOtaState");
    }

    public void onCanInfoReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onCanInfoReceived");
    }

    public final void onDataChanged(c cVar) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDataChanged");
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDataEventChanged");
    }

    public void onDcState(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDcState");
    }

    public void onDischargeMainContactorStateChanged(int i8) {
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

    public void onError(int i8, String str) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onError");
    }

    public void onFaultCodeInfoReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onFaultCodeInfoReceived");
    }

    public void onIg1RelayState(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg1RelayState");
    }

    public void onIg3RelayState(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg3RelayState");
    }

    public void onIg4RelayState(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg4RelayState");
    }

    public void onLFDoorLockStateChanged(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLFDoorLockStateChanged");
    }

    public void onLFDoorLockStatus(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLFDoorLockStatus");
    }

    public void onLocalDiagStatusChanged(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLocalDiagStatusChanged");
    }

    public void onMCUVersionAndSoftcodeReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onMCUVersionAndSoftcodeReceived");
    }

    public void onNeedRendRegisterTable(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onNeedRendRegisterTable");
    }

    public void onOTAInfoACKChanged(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTAInfoACKChanged");
    }

    public void onOTAStateChanged(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTAStateChanged");
    }

    public void onOTATimecountStateChanged(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTATimecountStateChanged");
    }

    public void onPowerOnForbidStateChanged(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onPowerOnForbidStateChanged");
    }

    public void onPoweroffInformChanged(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onPoweroffInformChanged");
    }

    public void onProhibitPowerOnState(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onProhibitPowerOnState state : " + i8);
    }

    @Deprecated
    public void onSmartChargeState(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSmartChargeState");
    }

    public void onSmartPowerState(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSmartPowerState");
    }

    public void onSocValueChanged(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSocValueChanged");
    }

    public void onStartKeyState(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onStartKeyState state " + i8);
    }

    public void onSyncMcuStateChanged(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSyncMcuStateChanged");
    }

    public void onTurnOffState(int i8) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onTurnOffState");
    }

    public void onUpgradeProgress(int i8, int i9) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onUpgradeProgress");
    }

    public void onUpgradeResult(int i8, int i9, int i10) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onUpgradeResult");
    }

    public void onWholeFrameDataChanged(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onWholeFrameDataChanged");
    }
}
