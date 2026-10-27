package android.hardware.bydauto.test;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoTestListener implements d {
    protected static final String TAG = "AbsBYDAutoTestListener";

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public void onApkInstallReqChanged(int i7) {
        throw new RuntimeException("Stub!");
    }

    public void onChangedConfigArea(int i7) {
        Log.w(TAG, "onChangedConfigArea state : " + i7);
    }

    public void onChangedCpaa(int i7) {
        Log.w(TAG, "onChangedCpaa state : " + i7);
    }

    public void onCloudServiceConnectStateChanged(int i7) {
        Log.w(TAG, "onTboxRestartChanged state : " + i7);
    }

    public final void onDataChanged(c cVar) {
        throw new RuntimeException("Stub!");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    public void onError(int i7, String str) {
        throw new RuntimeException("Stub!");
    }

    public void onMcuStateChanged(int i7) {
        throw new RuntimeException("Stub!");
    }

    public void onNetworkOperatorList(ArrayList<String> arrayList) {
        Log.w(TAG, "onNetworkOperatorList code : " + arrayList);
    }

    public void onObdReport(String str, String str2) {
        Log.w(TAG, "onObdReport id : " + str + " data : " + str2);
    }

    public void onQNXLogStatusChanged(int i7, boolean z6) {
        Log.w(TAG, "onQNXLogStatusChanged: logtype : " + i7 + " status : " + z6);
    }

    public void onReadConfigurationWordInfo(short s6, ArrayList<Byte> arrayList) {
        Log.w(TAG, "onReadConfigurationWordInfo did : " + ((int) s6) + " data : " + arrayList);
    }

    public void onTboxRestartChanged(int i7) {
        Log.w(TAG, "onTboxRestartChanged state : " + i7);
    }

    public void onWriteConfigurationWordInfo(short s6, int i7) {
        Log.w(TAG, "onWriteConfigurationWordInfo did : " + ((int) s6) + " res : " + i7);
    }
}
