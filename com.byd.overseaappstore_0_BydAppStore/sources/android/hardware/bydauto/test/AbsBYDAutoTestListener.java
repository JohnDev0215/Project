package android.hardware.bydauto.test;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.test.bean.NetworkOperator;
import android.util.Log;
import java.util.ArrayList;
import spi.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoTestListener implements IBYDAutoListener {
    public static final String TAG = "AbsBYDAutoTestListener";

    public void onApkInstallReqChanged(int i) {
        throw new RuntimeException("Stub!");
    }

    public void onAutomaticNetworkChange(int i) {
        d.x("onAutomaticNetworkChange state:", i, TAG);
    }

    public void onChangedConfigArea(int i) {
        d.x("onChangedConfigArea state : ", i, TAG);
    }

    public void onChangedCpaa(int i) {
        d.x("onChangedCpaa state : ", i, TAG);
    }

    public void onCloudServiceConnectStateChanged(int i) {
        d.x("onTboxRestartChanged state : ", i, TAG);
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
        StringBuilder sbK = d.k("onManuallyStationedNetworkChange data:", str, " curNetType:", i, " state:");
        d.t(sbK, i2, " lastData:", str2, " lastNetType:");
        sbK.append(i3);
        Log.w(TAG, sbK.toString());
    }

    public void onMcuStateChanged(int i) {
        throw new RuntimeException("Stub!");
    }

    public void onNetworkOperatorList(ArrayList<String> arrayList) {
        Log.w(TAG, "onNetworkOperatorList code : " + arrayList);
    }

    public void onNetworkOperatorModeChange(int i) {
        d.x("onNetworkOperatorModeChange state:", i, TAG);
    }

    public void onObdReport(int i, int i2) {
        Log.w(TAG, "onObdReport iviObdOperationType : " + i + " iviObdStatus : " + i2);
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
        d.x("onSimCardStateChange status:", i, TAG);
    }

    public void onStatusChangeCarType(boolean z) {
        Log.w(TAG, "onStatusChangeCarType state : " + z);
    }

    public void onTboxRestartChanged(int i) {
        d.x("onTboxRestartChanged state : ", i, TAG);
    }

    public void onWriteConfigurationWordInfo(short s, int i) {
        Log.w(TAG, "onWriteConfigurationWordInfo did : " + ((int) s) + " res : " + i);
    }
}
