package com.ecarx.xui.adaptapi.ota;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IOtaSession {
    public static final int OTA_PRIORITY_HIGH = 2;
    public static final int OTA_PRIORITY_LOW = 0;
    public static final int OTA_PRIORITY_NORMAL = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface OtaPriority {
    }

    boolean cancel();

    boolean couldBeginInstallRightNow();

    int getOtaPriority();

    int getOtaProgress();

    boolean ifSystemWillRebootAfterOta();

    boolean isInstallationStarted();

    boolean isRecoveryOta();
}
