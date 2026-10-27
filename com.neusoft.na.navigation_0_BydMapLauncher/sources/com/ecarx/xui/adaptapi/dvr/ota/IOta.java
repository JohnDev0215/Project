package com.ecarx.xui.adaptapi.dvr.ota;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IOta {
    public static final int FAILED_FILE_ERROR = 5;
    public static final int FAILED_NO_FILE = 4;
    public static final int FAILED_NO_RESPONSE = 0;
    public static final int FAILED_NO_SDCARD = 3;
    public static final int FAILED_OTHER_REASON = 6;

    @Retention(RetentionPolicy.SOURCE)
    public @interface OtaFailedReason {
    }

    int getOtaBaseSoftwareVersionCode();

    String getOtaBaseSoftwareVersionName();

    boolean installPackage(IOtaSession iOtaSession, String str);

    IOtaSession requestOta(IOtaCallback iOtaCallback);

    boolean supportOtaFromIhu();
}
