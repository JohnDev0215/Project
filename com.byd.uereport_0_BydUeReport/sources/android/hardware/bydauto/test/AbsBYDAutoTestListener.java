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
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onApkInstallReqChanged(int i8) {
        throw new RuntimeException("Stub!");
    }

    public void onAutomaticNetworkChange(int i8) {
        Log.w(TAG, "onAutomaticNetworkChange state:" + i8);
    }

    public void onChangedConfigArea(int i8) {
        Log.w(TAG, "onChangedConfigArea state : " + i8);
    }

    public void onChangedCpaa(int i8) {
        Log.w(TAG, "onChangedCpaa state : " + i8);
    }

    public void onCloudServiceConnectStateChanged(int i8) {
        Log.w(TAG, "onTboxRestartChanged state : " + i8);
    }

    public final void onDataChanged(c cVar) {
        throw new RuntimeException("Stub!");
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    public void onEnergyTestChange(int i8) {
        Log.w(TAG, "onEnergyTestChange status : " + i8);
    }

    public void onError(int i8, String str) {
        throw new RuntimeException("Stub!");
    }

    public void onGetOffScreenFlagResult(int i8, int i9) {
        Log.w(TAG, "onGetOffScreenFlagResult type : " + i8 + ", resultCode : " + i9);
    }

    public void onManuallyStationedNetworkChange(String str, int i8, int i9, String str2, int i10) {
        Log.w(TAG, "onManuallyStationedNetworkChange data:" + str + " curNetType:" + i8 + " state:" + i9 + " lastData:" + str2 + " lastNetType:" + i10);
    }

    public void onMcuStateChanged(int i8) {
        throw new RuntimeException("Stub!");
    }

    public void onNetworkOperatorList(ArrayList<String> arrayList) {
        Log.w(TAG, "onNetworkOperatorList code : " + arrayList);
    }

    public void onNetworkOperatorModeChange(int i8) {
        Log.w(TAG, "onNetworkOperatorModeChange state:" + i8);
    }

    public void onObdReport(String str, String str2) {
        Log.w(TAG, "onObdReport id : " + str + " data : " + str2);
    }

    public void onOperatorInfoChange(ArrayList<NetworkOperator> arrayList) {
        Log.w(TAG, "onOperatorInfoChange list:" + arrayList);
    }

    public void onQNXLogStatusChanged(int i8, boolean z7) {
        Log.w(TAG, "onQNXLogStatusChanged: logtype : " + i8 + " status : " + z7);
    }

    public void onReadConfigurationWordInfo(short s7, ArrayList<Byte> arrayList) {
        Log.w(TAG, "onReadConfigurationWordInfo did : " + ((int) s7) + " data : " + arrayList);
    }

    public void onSimCardStateChange(int i8) {
        Log.w(TAG, "onSimCardStateChange status:" + i8);
    }

    public void onStatusChangeCarType(boolean z7) {
        Log.w(TAG, "onStatusChangeCarType state : " + z7);
    }

    public void onTboxRestartChanged(int i8) {
        Log.w(TAG, "onTboxRestartChanged state : " + i8);
    }

    public void onWriteConfigurationWordInfo(short s7, int i8) {
        Log.w(TAG, "onWriteConfigurationWordInfo did : " + ((int) s7) + " res : " + i8);
    }

    public void onObdReport(int i8, int i9) {
        Log.w(TAG, "onObdReport iviObdOperationType : " + i8 + " iviObdStatus : " + i9);
    }
}
