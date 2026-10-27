package com.ecarx.xui.adaptapi.tbox.ota;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IOta {
    public static final int FAILED_REASON_DEVICE_BUSY = 2;
    public static final int FAILED_REASON_DEVICE_DISCONNECTED = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface OtaFailedReason {
    }

    int getOtaBaseSoftwareVersionCode();

    String getOtaBaseSoftwareVersionName();

    String getOtaPkgRootPath();

    boolean installPackage(IOtaSession iOtaSession, String str);

    IOtaSession requestOta(IOtaSessionCallback iOtaSessionCallback);
}
