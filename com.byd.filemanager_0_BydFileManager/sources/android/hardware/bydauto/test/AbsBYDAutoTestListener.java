package android.hardware.bydauto.test;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoTestListener implements d {
    protected static final String TAG = "AbsBYDAutoTestListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    public void onApkInstallReqChanged(int i) {
        throw new RuntimeException("Stub!");
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

    public void onMcuStateChanged(int i) {
        throw new RuntimeException("Stub!");
    }

    public void onNetworkOperatorList(ArrayList<String> arrayList) {
        Log.w(TAG, "onNetworkOperatorList code : " + arrayList);
    }

    public void onObdReport(String str, String str2) {
        Log.w(TAG, "onObdReport id : " + str + " data : " + str2);
    }

    public void onQNXLogStatusChanged(int i, boolean z) {
        Log.w(TAG, "onQNXLogStatusChanged: logtype : " + i + " status : " + z);
    }

    public void onReadConfigurationWordInfo(short s, ArrayList<Byte> arrayList) {
        Log.w(TAG, "onReadConfigurationWordInfo did : " + ((int) s) + " data : " + arrayList);
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
