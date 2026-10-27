package android.hardware.bydauto.ota;

import android.os.Bundle;
import android.util.Log;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsMessageCallback {
    protected static final String TAG = "AbsMessageCallback";

    public void onEcuInfo(List<EcuInfo> ecuInfoList) {
        Log.w(TAG, "AbsMessageCallback.onEcuInfo");
    }

    public void onGetAQSlot(int slot) {
        Log.w(TAG, "AbsMessageCallback.onGetAQSlot");
    }

    public void onSetAQSlot(int resultCode) {
        Log.w(TAG, "AbsMessageCallback.onSetAQSlot");
    }

    public void onAppointmentTime(int resultCode) {
        Log.w(TAG, "AbsMessageCallback.onAppointmentTime");
    }

    public void onUpdateFlag(int resultCode) {
        Log.w(TAG, "AbsMessageCallback.onUpdateFlag");
    }

    public void onQnxOtaState(int resultCode) {
        Log.w(TAG, "AbsMessageCallback.onQnxOtaState");
    }

    public void onAndroidOtaState(int resultCode) {
        Log.w(TAG, "AbsMessageCallback.onAndroidOtaState");
    }

    public void onCloudServiceListener(int type, Bundle bundle) {
        Log.w(TAG, "AbsMessageCallback.onCloudServiceListener");
    }

    public void onGetDiagnosticTroubleCode(List<DiagnosticInfo> infos) {
        Log.w(TAG, "AbsMessageCallback.onGetDiagnosticTroubleCode");
    }

    public void onClearDiagnosticTroubleCode(List<DiagnosticInfo> infos) {
        Log.w(TAG, "AbsMessageCallback.onClearDiagnosticTroubleCode");
    }

    public void onSendCanNM(int resultCode) {
        Log.w(TAG, "AbsMessageCallback.onSendCanNM");
    }
}
