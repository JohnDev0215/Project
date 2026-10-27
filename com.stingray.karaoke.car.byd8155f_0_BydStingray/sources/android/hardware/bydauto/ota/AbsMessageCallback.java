package android.hardware.bydauto.ota;

import android.os.Bundle;
import h6.n;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsMessageCallback {
    protected static final String TAG = "AbsMessageCallback";

    public void onAndroidOtaState(int i7) {
        n.t(TAG, "AbsMessageCallback.onAndroidOtaState");
    }

    public void onAppointmentTime(int i7) {
        n.t(TAG, "AbsMessageCallback.onAppointmentTime");
    }

    public void onClearDiagnosticTroubleCode(List<DiagnosticInfo> list) {
        n.t(TAG, "AbsMessageCallback.onClearDiagnosticTroubleCode");
    }

    public void onCloudServiceListener(int i7, Bundle bundle) {
        n.t(TAG, "AbsMessageCallback.onCloudServiceListener");
    }

    public void onEcuInfo(List<EcuInfo> list) {
        n.t(TAG, "AbsMessageCallback.onEcuInfo");
    }

    public void onGetAQSlot(int i7) {
        n.t(TAG, "AbsMessageCallback.onGetAQSlot");
    }

    public void onGetDiagnosticTroubleCode(List<DiagnosticInfo> list) {
        n.t(TAG, "AbsMessageCallback.onGetDiagnosticTroubleCode");
    }

    public void onQnxOtaState(int i7) {
        n.t(TAG, "AbsMessageCallback.onQnxOtaState");
    }

    public void onSendCanNM(int i7) {
        n.t(TAG, "AbsMessageCallback.onSendCanNM");
    }

    public void onSetAQSlot(int i7) {
        n.t(TAG, "AbsMessageCallback.onSetAQSlot");
    }

    public void onUpdateFlag(int i7) {
        n.t(TAG, "AbsMessageCallback.onUpdateFlag");
    }
}
