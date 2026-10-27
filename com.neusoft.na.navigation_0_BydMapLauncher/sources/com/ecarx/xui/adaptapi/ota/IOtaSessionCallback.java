package com.ecarx.xui.adaptapi.ota;

/* JADX INFO: loaded from: classes2.dex */
public interface IOtaSessionCallback {
    void onFailed(int i);

    void onRebootingAfterOta();

    void onSessionCanceled();

    void onShouldBeginInstall();

    void onSucceeded();
}
