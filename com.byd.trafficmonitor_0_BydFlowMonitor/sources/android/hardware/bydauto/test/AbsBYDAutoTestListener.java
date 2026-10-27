package android.hardware.bydauto.test;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.test.bean.NetworkOperator;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoTestListener implements d {
    public static final String TAG = "AbsBYDAutoTestListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onApkInstallReqChanged(int i2) {
        throw new RuntimeException("Stub!");
    }

    public void onAutomaticNetworkChange(int i2) {
        Log.w(TAG, "onAutomaticNetworkChange state:" + i2);
    }

    public void onChangedConfigArea(int i2) {
        Log.w(TAG, "onChangedConfigArea state : " + i2);
    }

    public void onChangedCpaa(int i2) {
        Log.w(TAG, "onChangedCpaa state : " + i2);
    }

    public void onCloudServiceConnectStateChanged(int i2) {
        Log.w(TAG, "onTboxRestartChanged state : " + i2);
    }

    public final void onDataChanged(c cVar) {
        throw new RuntimeException("Stub!");
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    public void onError(int i2, String str) {
        throw new RuntimeException("Stub!");
    }

    public void onGetOffScreenFlagResult(int i2, int i3) {
        Log.w(TAG, "onGetOffScreenFlagResult type : " + i2 + ", resultCode : " + i3);
    }

    public void onManuallyStationedNetworkChange(String str, int i2, int i3, String str2, int i4) {
        Log.w(TAG, "onManuallyStationedNetworkChange data:" + str + " curNetType:" + i2 + " state:" + i3 + " lastData:" + str2 + " lastNetType:" + i4);
    }

    public void onMcuStateChanged(int i2) {
        throw new RuntimeException("Stub!");
    }

    public void onNetworkOperatorList(ArrayList<String> arrayList) {
        Log.w(TAG, "onNetworkOperatorList code : " + arrayList);
    }

    public void onNetworkOperatorModeChange(int i2) {
        Log.w(TAG, "onNetworkOperatorModeChange state:" + i2);
    }

    public void onObdReport(String str, String str2) {
        Log.w(TAG, "onObdReport id : " + str + " data : " + str2);
    }

    public void onOperatorInfoChange(ArrayList<NetworkOperator> arrayList) {
        Log.w(TAG, "onOperatorInfoChange list:" + arrayList);
    }

    public void onQNXLogStatusChanged(int i2, boolean z) {
        Log.w(TAG, "onQNXLogStatusChanged: logtype : " + i2 + " status : " + z);
    }

    public void onReadConfigurationWordInfo(short s, ArrayList<Byte> arrayList) {
        Log.w(TAG, "onReadConfigurationWordInfo did : " + ((int) s) + " data : " + arrayList);
    }

    public void onSimCardStateChange(int i2) {
        Log.w(TAG, "onSimCardStateChange status:" + i2);
    }

    public void onStatusChangeCarType(boolean z) {
        Log.w(TAG, "onStatusChangeCarType state : " + z);
    }

    public void onTboxRestartChanged(int i2) {
        Log.w(TAG, "onTboxRestartChanged state : " + i2);
    }

    public void onWriteConfigurationWordInfo(short s, int i2) {
        Log.w(TAG, "onWriteConfigurationWordInfo did : " + ((int) s) + " res : " + i2);
    }

    public void onObdReport(int i2, int i3) {
        Log.w(TAG, "onObdReport iviObdOperationType : " + i2 + " iviObdStatus : " + i3);
    }
}
