package android.hardware.bydauto.ota;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoOtaListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoOtaListener";

    @Override // android.hardware.IBYDAutoListener
    public /* synthetic */ void isConnected(boolean z) {
        IBYDAutoListener.CC.$default$isConnected(this, z);
    }

    public AbsBYDAutoOtaListener() {
        Log.w(TAG, TAG);
    }

    public void onUpgradeProgress(int i, int i2) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onUpgradeProgress");
    }

    public void onUpgradeResult(int i, int i2, int i3) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onUpgradeResult");
    }

    public void onOTAInfoACKChanged(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTAInfoACKChanged");
    }

    public void onPoweroffInformChanged(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onPoweroffInformChanged");
    }

    public void onLocalDiagStatusChanged(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLocalDiagStatusChanged");
    }

    public void onECUVersionReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onECUVersionReceived");
    }

    public void onECUSoftcodeReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onECUSoftcodeReceived");
    }

    public void onECUVersionAndSoftcodeReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onECUVersionAndSoftcodeReceived");
    }

    public void onMCUVersionAndSoftcodeReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onMCUVersionAndSoftcodeReceived");
    }

    public void onOTAStateChanged(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTAStateChanged");
    }

    public void onOTATimecountStateChanged(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTATimecountStateChanged");
    }

    public void onDischargeMainContactorStateChanged(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDischargeMainContactorStateChanged");
    }

    public void onBatteryVoltageChanged(double d) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBatteryVoltageChanged");
    }

    public void onPowerOnForbidStateChanged(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onPowerOnForbidStateChanged");
    }

    public void onLFDoorLockStateChanged(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLFDoorLockStateChanged");
    }

    public void onCanInfoReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onCanInfoReceived");
    }

    public void onBatteryPowerVoltageChanged(double d) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBatteryPowerVoltageChanged");
    }

    public void onFaultCodeInfoReceived(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onFaultCodeInfoReceived");
    }

    public void onSyncMcuStateChanged(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSyncMcuStateChanged");
    }

    public void onNeedRendRegisterTable(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onNeedRendRegisterTable");
    }

    public void onWholeFrameDataChanged(byte[] bArr) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onWholeFrameDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDataEventChanged");
    }

    public void onTurnOffState(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onTurnOffState");
    }

    public void onSmartPowerState(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSmartPowerState");
    }

    public void onDcState(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDcState");
    }

    @Deprecated
    public void onSmartChargeState(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSmartChargeState");
    }

    public void onIg3RelayState(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg3RelayState");
    }

    public void onIg1RelayState(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg1RelayState");
    }

    public void onIg4RelayState(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg4RelayState");
    }

    public void onSocValueChanged(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSocValueChanged");
    }

    public void onBackupAreaDownloadState(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBackupAreaDownloadState");
    }

    public void on7dfExistState(boolean z) {
        Log.w(TAG, "AbsBYDAutoOtaListener.on7DFExist");
    }

    public void onBcmOtaState(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBcmOtaState");
    }

    public void onLFDoorLockStatus(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLFDoorLockStatus");
    }

    public void onProhibitPowerOnState(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onProhibitPowerOnState state : " + i);
    }

    public void onStartKeyState(int i) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onStartKeyState state " + i);
    }
}
