package android.hardware.bydauto.test;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.ac.tsimpl.a;
import b.c;
import b.d;
import h6.n;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoTestListener implements d {
    protected static final String TAG = "AbsBYDAutoTestListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onApkInstallReqChanged(int i7) {
        throw new RuntimeException("Stub!");
    }

    public void onChangedConfigArea(int i7) {
        a.r("onChangedConfigArea state : ", i7, TAG);
    }

    public void onChangedCpaa(int i7) {
        a.r("onChangedCpaa state : ", i7, TAG);
    }

    public void onCloudServiceConnectStateChanged(int i7) {
        a.r("onTboxRestartChanged state : ", i7, TAG);
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
        n.t(TAG, "onNetworkOperatorList code : " + arrayList);
    }

    public void onObdReport(String str, String str2) {
        n.t(TAG, "onObdReport id : " + str + " data : " + str2);
    }

    public void onQNXLogStatusChanged(int i7, boolean z6) {
        n.t(TAG, "onQNXLogStatusChanged: logtype : " + i7 + " status : " + z6);
    }

    public void onReadConfigurationWordInfo(short s6, ArrayList<Byte> arrayList) {
        n.t(TAG, "onReadConfigurationWordInfo did : " + ((int) s6) + " data : " + arrayList);
    }

    public void onTboxRestartChanged(int i7) {
        a.r("onTboxRestartChanged state : ", i7, TAG);
    }

    public void onWriteConfigurationWordInfo(short s6, int i7) {
        n.t(TAG, "onWriteConfigurationWordInfo did : " + ((int) s6) + " res : " + i7);
    }

    public void onObdReport(int i7, int i10) {
        n.t(TAG, "onObdReport iviObdOperationType : " + i7 + " iviObdStatus : " + i10);
    }
}
