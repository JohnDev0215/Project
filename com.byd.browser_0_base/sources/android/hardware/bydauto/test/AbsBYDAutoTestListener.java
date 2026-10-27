package android.hardware.bydauto.test;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.test.bean.NetworkOperator;
import android.util.Log;
import defpackage.C0175b;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoTestListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoTestListener";

    public void onApkInstallReqChanged(int i) {
        throw new RuntimeException("Stub!");
    }

    public void onAutomaticNetworkChange(int i) {
        C0175b.s("onAutomaticNetworkChange state:", i, TAG);
    }

    public void onChangedConfigArea(int i) {
        C0175b.s("onChangedConfigArea state : ", i, TAG);
    }

    public void onChangedCpaa(int i) {
        C0175b.s("onChangedCpaa state : ", i, TAG);
    }

    public void onCloudServiceConnectStateChanged(int i) {
        C0175b.s("onTboxRestartChanged state : ", i, TAG);
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
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
        C0175b.s("onNetworkOperatorModeChange state:", i, TAG);
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
        C0175b.s("onSimCardStateChange status:", i, TAG);
    }

    public void onStatusChangeCarType(boolean z) {
        Log.w(TAG, "onStatusChangeCarType state : " + z);
    }

    public void onTboxRestartChanged(int i) {
        C0175b.s("onTboxRestartChanged state : ", i, TAG);
    }

    public void onWriteConfigurationWordInfo(short s, int i) {
        Log.w(TAG, "onWriteConfigurationWordInfo did : " + ((int) s) + " res : " + i);
    }

    public void onObdReport(int i, int i2) {
        Log.w(TAG, "onObdReport iviObdOperationType : " + i + " iviObdStatus : " + i2);
    }
}
