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
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void on7dfExistState(boolean z3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.on7DFExist");
    }

    public void onBackupAreaDownloadState(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBackupAreaDownloadState");
    }

    public void onBatteryPowerVoltageChanged(double d3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBatteryPowerVoltageChanged");
    }

    public void onBatteryVoltageChanged(double d3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBatteryVoltageChanged");
    }

    public void onBcmOtaState(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBcmOtaState");
    }

    public void onCanInfoReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onCanInfoReceived");
    }

    public final void onDataChanged(c cVar) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDataChanged");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDataEventChanged");
    }

    public void onDcState(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDcState");
    }

    public void onDischargeMainContactorStateChanged(int i3) {
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

    public void onError(int i3, String str) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onError");
    }

    public void onFaultCodeInfoReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onFaultCodeInfoReceived");
    }

    public void onIg1RelayState(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg1RelayState");
    }

    public void onIg3RelayState(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg3RelayState");
    }

    public void onIg4RelayState(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg4RelayState");
    }

    public void onLFDoorLockStateChanged(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLFDoorLockStateChanged");
    }

    public void onLFDoorLockStatus(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLFDoorLockStatus");
    }

    public void onLocalDiagStatusChanged(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLocalDiagStatusChanged");
    }

    public void onMCUVersionAndSoftcodeReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onMCUVersionAndSoftcodeReceived");
    }

    public void onNeedRendRegisterTable(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onNeedRendRegisterTable");
    }

    public void onOTAInfoACKChanged(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTAInfoACKChanged");
    }

    public void onOTAStateChanged(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTAStateChanged");
    }

    public void onOTATimecountStateChanged(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTATimecountStateChanged");
    }

    public void onPowerOnForbidStateChanged(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onPowerOnForbidStateChanged");
    }

    public void onPoweroffInformChanged(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onPoweroffInformChanged");
    }

    public void onProhibitPowerOnState(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onProhibitPowerOnState state : " + i3);
    }

    @Deprecated
    public void onSmartChargeState(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSmartChargeState");
    }

    public void onSmartPowerState(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSmartPowerState");
    }

    public void onSocValueChanged(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSocValueChanged");
    }

    public void onStartKeyState(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onStartKeyState state " + i3);
    }

    public void onSyncMcuStateChanged(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSyncMcuStateChanged");
    }

    public void onTurnOffState(int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onTurnOffState");
    }

    public void onUpgradeProgress(int i3, int i4) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onUpgradeProgress");
    }

    public void onUpgradeResult(int i3, int i4, int i5) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onUpgradeResult");
    }

    public void onWholeFrameDataChanged(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onWholeFrameDataChanged");
    }
}
