package com.ecarx.xui.adaptapi.vehicle.ota;

/* JADX INFO: loaded from: classes2.dex */
public interface IOtaSession {
    boolean cancel();

    boolean couldBeginInstallRightNow();

    int getOtaConsumingTime();

    int getOtaProgress();

    boolean ifSystemWillRebootAfterOta();

    boolean isInstallationStarted();
}
