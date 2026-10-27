package com.ecarx.xui.adaptapi.dvr.ota;

/* JADX INFO: loaded from: classes2.dex */
interface IOtaCallback {
    void onFailed(int i);

    void onRebootingAfterOta();

    void onShouldBeginInstall();

    void onSucceeded();
}
