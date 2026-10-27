package android.hardware.bydauto.test;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.test.bean.NetworkOperator;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoTestListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoTestListener";

    public void onMcuStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onApkInstallReqChanged(int req) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        throw new RuntimeException("Stub!");
    }

    public void onQNXLogStatusChanged(int logtype, boolean status) {
        Log.w(TAG, "onQNXLogStatusChanged: logtype : " + logtype + " status : " + status);
    }

    public void onReadConfigurationWordInfo(short did, ArrayList<Byte> data) {
        Log.w(TAG, "onReadConfigurationWordInfo did : " + ((int) did) + " data : " + data);
    }

    public void onWriteConfigurationWordInfo(short did, int res) {
        Log.w(TAG, "onWriteConfigurationWordInfo did : " + ((int) did) + " res : " + res);
    }

    public void onNetworkOperatorList(ArrayList<String> code) {
        Log.w(TAG, "onNetworkOperatorList code : " + code);
    }

    public void onTboxRestartChanged(int state) {
        Log.w(TAG, "onTboxRestartChanged state : " + state);
    }

    public void onCloudServiceConnectStateChanged(int state) {
        Log.w(TAG, "onTboxRestartChanged state : " + state);
    }

    public void onObdReport(String id, String data) {
        Log.w(TAG, "onObdReport id : " + id + " data : " + data);
    }

    public void onObdReport(int iviObdOperationType, int iviObdStatus) {
        Log.w(TAG, "onObdReport iviObdOperationType : " + iviObdOperationType + " iviObdStatus : " + iviObdStatus);
    }

    public void onChangedConfigArea(int state) {
        Log.w(TAG, "onChangedConfigArea state : " + state);
    }

    public void onChangedCpaa(int state) {
        Log.w(TAG, "onChangedCpaa state : " + state);
    }

    public void onStatusChangeCarType(boolean status) {
        Log.w(TAG, "onStatusChangeCarType state : " + status);
    }

    public void onGetOffScreenFlagResult(int type, int resultCode) {
        Log.w(TAG, "onGetOffScreenFlagResult type : " + type + ", resultCode : " + resultCode);
    }

    public void onNetworkOperatorModeChange(int state) {
        Log.w(TAG, "onNetworkOperatorModeChange state:" + state);
    }

    public void onOperatorInfoChange(ArrayList<NetworkOperator> list) {
        Log.w(TAG, "onOperatorInfoChange list:" + list);
    }

    public void onManuallyStationedNetworkChange(String data, int curNetType, int state, String lastData, int lastNetType) {
        Log.w(TAG, "onManuallyStationedNetworkChange data:" + data + " curNetType:" + curNetType + " state:" + state + " lastData:" + lastData + " lastNetType:" + lastNetType);
    }

    public void onAutomaticNetworkChange(int state) {
        Log.w(TAG, "onAutomaticNetworkChange state:" + state);
    }

    public void onSimCardStateChange(int status) {
        Log.w(TAG, "onSimCardStateChange status:" + status);
    }

    public void onEnergyTestChange(int status) {
        Log.w(TAG, "onEnergyTestChange status : " + status);
    }
}
