package android.hardware.bydauto.test;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.test.bean.NetworkOperator;
import android.util.Log;
import b.c;
import b.d;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoTestListener implements d {
    public static final String TAG = "AbsBYDAutoTestListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onApkInstallReqChanged(int i3) {
        throw new RuntimeException("Stub!");
    }

    public void onAutomaticNetworkChange(int i3) {
        Log.w(TAG, "onAutomaticNetworkChange state:" + i3);
    }

    public void onChangedConfigArea(int i3) {
        Log.w(TAG, "onChangedConfigArea state : " + i3);
    }

    public void onChangedCpaa(int i3) {
        Log.w(TAG, "onChangedCpaa state : " + i3);
    }

    public void onCloudServiceConnectStateChanged(int i3) {
        Log.w(TAG, "onTboxRestartChanged state : " + i3);
    }

    public final void onDataChanged(c cVar) {
        throw new RuntimeException("Stub!");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    public void onEnergyTestChange(int i3) {
        Log.w(TAG, "onEnergyTestChange status : " + i3);
    }

    public void onError(int i3, String str) {
        throw new RuntimeException("Stub!");
    }

    public void onGetOffScreenFlagResult(int i3, int i4) {
        Log.w(TAG, "onGetOffScreenFlagResult type : " + i3 + ", resultCode : " + i4);
    }

    public void onManuallyStationedNetworkChange(String str, int i3, int i4, String str2, int i5) {
        Log.w(TAG, "onManuallyStationedNetworkChange data:" + str + " curNetType:" + i3 + " state:" + i4 + " lastData:" + str2 + " lastNetType:" + i5);
    }

    public void onMcuStateChanged(int i3) {
        throw new RuntimeException("Stub!");
    }

    public void onNetworkOperatorList(ArrayList<String> arrayList) {
        Log.w(TAG, "onNetworkOperatorList code : " + arrayList);
    }

    public void onNetworkOperatorModeChange(int i3) {
        Log.w(TAG, "onNetworkOperatorModeChange state:" + i3);
    }

    public void onObdReport(String str, String str2) {
        Log.w(TAG, "onObdReport id : " + str + " data : " + str2);
    }

    public void onOperatorInfoChange(ArrayList<NetworkOperator> arrayList) {
        Log.w(TAG, "onOperatorInfoChange list:" + arrayList);
    }

    public void onQNXLogStatusChanged(int i3, boolean z3) {
        Log.w(TAG, "onQNXLogStatusChanged: logtype : " + i3 + " status : " + z3);
    }

    public void onReadConfigurationWordInfo(short s3, ArrayList<Byte> arrayList) {
        Log.w(TAG, "onReadConfigurationWordInfo did : " + ((int) s3) + " data : " + arrayList);
    }

    public void onSimCardStateChange(int i3) {
        Log.w(TAG, "onSimCardStateChange status:" + i3);
    }

    public void onStatusChangeCarType(boolean z3) {
        Log.w(TAG, "onStatusChangeCarType state : " + z3);
    }

    public void onTboxRestartChanged(int i3) {
        Log.w(TAG, "onTboxRestartChanged state : " + i3);
    }

    public void onWriteConfigurationWordInfo(short s3, int i3) {
        Log.w(TAG, "onWriteConfigurationWordInfo did : " + ((int) s3) + " res : " + i3);
    }

    public void onObdReport(int i3, int i4) {
        Log.w(TAG, "onObdReport iviObdOperationType : " + i3 + " iviObdStatus : " + i4);
    }
}
