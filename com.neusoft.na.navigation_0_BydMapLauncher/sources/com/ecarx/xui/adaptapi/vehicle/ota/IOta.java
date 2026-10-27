package com.ecarx.xui.adaptapi.vehicle.ota;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IOta {
    public static final int FAILED_CRITICAL_REASON_CONFIGURATION_MISMATCH = 4;
    public static final int FAILED_REASON_CONFIGURATION_MISMATCH = 3;
    public static final int FAILED_REASON_DEVICE_BUSY = 2;
    public static final int FAILED_REASON_DEVICE_DISCONNECTED = 1;
    public static final int FAILED_REASON_OTHER = 6;
    public static final int FAILED_REASON_REQUEST_NOT_ALLOWED = 5;
    public static final int REMINDER_OTA_DELAYED_BY_ENERGY_LOW = 3;
    public static final int REMINDER_OTA_DELAYED_BY_MOVING = 1;
    public static final int REMINDER_OTA_DELAYED_BY_SYSTEM = 4;
    public static final int REMINDER_OTA_DELAYED_BY_TIMEOUT = 5;
    public static final int REMINDER_OTA_DELAYED_BY_USAGEMODE_NOT_ALLOWED = 6;
    public static final int REMINDER_OTA_PENDING_BY_ASSIGN_BOOT_ACCEPTED = 7;
    public static final int REMINDER_OTA_PENDING_BY_ASSIGN_BOOT_DENIED = 7;
    public static final int REMINDER_OTA_PEPS_OFF = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface OtaFailedReason {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OtaReminder {
    }

    int getOtaBaseSoftwareVersionCode();

    String getOtaBaseSoftwareVersionName();

    String getOtaChangelog();

    String getOtaPkgRootPath();

    int getPackageSize();

    boolean installPackage(IOtaSession iOtaSession, String str);

    boolean installPackageByTime(IOtaSession iOtaSession, String str, int i);

    boolean isNewVersionFound();

    boolean isOtaPreOdered();

    void otaSuspendedByUser();

    void releaseOtaCallback(IOtaSessionCallback iOtaSessionCallback);

    IOtaSession requestOta(IOtaSessionCallback iOtaSessionCallback);
}
