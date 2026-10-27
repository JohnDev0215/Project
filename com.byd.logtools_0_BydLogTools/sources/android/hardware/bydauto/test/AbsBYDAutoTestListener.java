package android.hardware.bydauto.test;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.test.bean.NetworkOperator;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoTestListener implements d {
    protected static final String TAG = "AbsBYDAutoTestListener";

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onApkInstallReqChanged(int i) {
        throw new RuntimeException("Stub!");
    }

    public void onAutomaticNetworkChange(int i) {
        Log.w(TAG, "onAutomaticNetworkChange state:" + i);
    }

    public void onChangedConfigArea(int i) {
        Log.w(TAG, "onChangedConfigArea state : " + i);
    }

    public void onChangedCpaa(int i) {
        Log.w(TAG, "onChangedCpaa state : " + i);
    }

    public void onCloudServiceConnectStateChanged(int i) {
        Log.w(TAG, "onTboxRestartChanged state : " + i);
    }

    public final void onDataChanged(c cVar) {
        throw new RuntimeException("Stub!");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    public void onError(int i, String str) {
        throw new RuntimeException("Stub!");
    }

    public void onGetOffScreenFlagResult(int i, int i2) {
        Log.w(TAG, "onGetOffScreenFlagResult type : " + i + ", resultCode : " + i2);
    }

    public void onManuallyStationedNetworkChange(String str, int i, int i2, String str2, int i3) {
        Log.w(TAG, "onManuallyStationedNetworkChange data:" + str + " curNetType:" + i + " state:" + i2 + " lastData:" + str2 + " lastNetType:" + i3);
    }

    public void onMcuStateChanged(int i) {
        throw new RuntimeException("Stub!");
    }

    public void onNetworkOperatorList(ArrayList<String> arrayList) {
        Log.w(TAG, "onNetworkOperatorList code : " + arrayList);
    }

    public void onNetworkOperatorModeChange(int i) {
        Log.w(TAG, "onNetworkOperatorModeChange state:" + i);
    }

    public void onObdReport(String str, String str2) {
        Log.w(TAG, "onObdReport id : " + str + " data : " + str2);
    }

    public void onOperatorInfoChange(ArrayList<NetworkOperator> arrayList) {
        Log.w(TAG, "onOperatorInfoChange list:" + arrayList);
    }

    public void onQNXLogStatusChanged(int i, boolean z) {
        Log.w(TAG, "onQNXLogStatusChanged: logtype : " + i + " status : " + z);
    }

    public void onReadConfigurationWordInfo(short s, ArrayList<Byte> arrayList) {
        Log.w(TAG, "onReadConfigurationWordInfo did : " + ((int) s) + " data : " + arrayList);
    }

    public void onSimCardStateChange(int i) {
        Log.w(TAG, "onSimCardStateChange status:" + i);
    }

    public void onStatusChangeCarType(boolean z) {
        Log.w(TAG, "onStatusChangeCarType state : " + z);
    }

    public void onTboxRestartChanged(int i) {
        Log.w(TAG, "onTboxRestartChanged state : " + i);
    }

    public void onWriteConfigurationWordInfo(short s, int i) {
        Log.w(TAG, "onWriteConfigurationWordInfo did : " + ((int) s) + " res : " + i);
    }

    public void onObdReport(int i, int i2) {
        Log.w(TAG, "onObdReport iviObdOperationType : " + i + " iviObdStatus : " + i2);
    }
}
