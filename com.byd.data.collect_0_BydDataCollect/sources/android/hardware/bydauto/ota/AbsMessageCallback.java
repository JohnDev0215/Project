package android.hardware.bydauto.ota;

import android.os.Bundle;
import android.util.Log;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsMessageCallback {
    protected static final String TAG = "AbsMessageCallback";

    public void onAndroidOtaState(int i2) {
        Log.w(TAG, "AbsMessageCallback.onAndroidOtaState");
    }

    public void onAppointmentTime(int i2) {
        Log.w(TAG, "AbsMessageCallback.onAppointmentTime");
    }

    public void onClearDiagnosticTroubleCode(List<DiagnosticInfo> list) {
        Log.w(TAG, "AbsMessageCallback.onClearDiagnosticTroubleCode");
    }

    public void onCloudServiceListener(int i2, Bundle bundle) {
        Log.w(TAG, "AbsMessageCallback.onCloudServiceListener");
    }

    public void onEcuInfo(List<EcuInfo> list) {
        Log.w(TAG, "AbsMessageCallback.onEcuInfo");
    }

    public void onGetAQSlot(int i2) {
        Log.w(TAG, "AbsMessageCallback.onGetAQSlot");
    }

    public void onGetDiagnosticTroubleCode(List<DiagnosticInfo> list) {
        Log.w(TAG, "AbsMessageCallback.onGetDiagnosticTroubleCode");
    }

    public void onQnxOtaState(int i2) {
        Log.w(TAG, "AbsMessageCallback.onQnxOtaState");
    }

    public void onSendCanNM(int i2) {
        Log.w(TAG, "AbsMessageCallback.onSendCanNM");
    }

    public void onSetAQSlot(int i2) {
        Log.w(TAG, "AbsMessageCallback.onSetAQSlot");
    }

    public void onUpdateFlag(int i2) {
        Log.w(TAG, "AbsMessageCallback.onUpdateFlag");
    }
}
