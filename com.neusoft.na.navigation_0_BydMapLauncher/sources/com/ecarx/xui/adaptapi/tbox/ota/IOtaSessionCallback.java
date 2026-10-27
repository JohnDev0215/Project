package com.ecarx.xui.adaptapi.tbox.ota;

/* JADX INFO: loaded from: classes2.dex */
interface IOtaSessionCallback {
    void onBootCompleted();

    void onFailed(int i);

    void onRebootingAfterOta();

    void onSessionCanceled();

    void onShouldBeginInstall();

    void onSucceeded();
}
