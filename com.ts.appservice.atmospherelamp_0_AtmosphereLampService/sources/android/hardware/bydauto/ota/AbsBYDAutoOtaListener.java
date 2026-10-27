package android.hardware.bydauto.ota;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoOtaListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoOtaListener";

    public AbsBYDAutoOtaListener() {
        Log.w(TAG, TAG);
    }

    public void onUpgradeProgress(int type, int progress) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onUpgradeProgress");
    }

    public void onUpgradeResult(int type, int result, int errorCode) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onUpgradeResult");
    }

    public void onOTAInfoACKChanged(byte[] otaInfo) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTAInfoACKChanged");
    }

    public void onPoweroffInformChanged(int inform) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onPoweroffInformChanged");
    }

    public void onLocalDiagStatusChanged(int status) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLocalDiagStatusChanged");
    }

    public void onECUVersionReceived(byte[] version) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onECUVersionReceived");
    }

    public void onECUSoftcodeReceived(byte[] softcode) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onECUSoftcodeReceived");
    }

    public void onECUVersionAndSoftcodeReceived(byte[] otaInfo) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onECUVersionAndSoftcodeReceived");
    }

    public void onMCUVersionAndSoftcodeReceived(byte[] otaInfo) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onMCUVersionAndSoftcodeReceived");
    }

    public void onOTAStateChanged(int state) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTAStateChanged");
    }

    public void onOTATimecountStateChanged(int state) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onOTATimecountStateChanged");
    }

    public void onDischargeMainContactorStateChanged(int state) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDischargeMainContactorStateChanged");
    }

    public void onBatteryVoltageChanged(double voltage) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBatteryVoltageChanged");
    }

    public void onPowerOnForbidStateChanged(int state) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onPowerOnForbidStateChanged");
    }

    public void onLFDoorLockStateChanged(int state) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLFDoorLockStateChanged");
    }

    public void onCanInfoReceived(byte[] messages) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onCanInfoReceived");
    }

    public void onBatteryPowerVoltageChanged(double voltage) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBatteryPowerVoltageChanged");
    }

    public void onFaultCodeInfoReceived(byte[] info) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onFaultCodeInfoReceived");
    }

    public void onSyncMcuStateChanged(int result) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSyncMcuStateChanged");
    }

    public void onNeedRendRegisterTable(int value) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onNeedRendRegisterTable");
    }

    public void onWholeFrameDataChanged(byte[] data) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onWholeFrameDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDataEventChanged");
    }

    public void onTurnOffState(int state) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onTurnOffState");
    }

    public void onSmartPowerState(int state) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSmartPowerState");
    }

    public void onDcState(int state) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onDcState");
    }

    @Deprecated
    public void onSmartChargeState(int state) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSmartChargeState");
    }

    public void onIg3RelayState(int state) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg3RelayState");
    }

    public void onIg1RelayState(int state) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg1RelayState");
    }

    public void onIg4RelayState(int state) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onIg4RelayState");
    }

    public void onSocValueChanged(int value) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onSocValueChanged");
    }

    public void onBackupAreaDownloadState(int state) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBackupAreaDownloadState");
    }

    public void on7dfExistState(boolean exist) {
        Log.w(TAG, "AbsBYDAutoOtaListener.on7DFExist");
    }

    public void onBcmOtaState(int state) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onBcmOtaState");
    }

    public void onLFDoorLockStatus(int state) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onLFDoorLockStatus");
    }

    public void onProhibitPowerOnState(int state) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onProhibitPowerOnState state : " + state);
    }

    public void onStartKeyState(int state) {
        Log.w(TAG, "AbsBYDAutoOtaListener.onStartKeyState state " + state);
    }
}
