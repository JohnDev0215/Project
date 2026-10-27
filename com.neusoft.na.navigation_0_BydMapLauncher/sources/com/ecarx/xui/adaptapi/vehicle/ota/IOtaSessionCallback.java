package com.ecarx.xui.adaptapi.vehicle.ota;

/* JADX INFO: loaded from: classes2.dex */
public interface IOtaSessionCallback {
    void onBootCompleted();

    void onFailed(int i);

    void onInstallStarted(int i);

    void onOtaReminder(int i);

    void onRebootingAfterOta();

    void onSessionCanceled();

    void onShouldBeginInstall();

    void onSucceeded();
}
