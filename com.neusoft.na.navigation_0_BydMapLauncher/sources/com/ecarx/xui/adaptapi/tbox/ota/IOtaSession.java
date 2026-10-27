package com.ecarx.xui.adaptapi.tbox.ota;

/* JADX INFO: loaded from: classes2.dex */
interface IOtaSession {
    boolean cancel();

    boolean couldBeginInstallRightNow();

    int getOtaProgress();

    boolean ifSystemWillRebootAfterOta();

    boolean isInstallationStarted();
}
