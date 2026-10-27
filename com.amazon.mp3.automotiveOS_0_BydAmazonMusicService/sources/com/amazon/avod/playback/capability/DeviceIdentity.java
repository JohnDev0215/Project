package com.amazon.avod.playback.capability;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface DeviceIdentity {
    int getAppMajorVersion();

    @Nonnull
    String getAppPackageName();

    @Nonnull
    String getAppVersionName();

    @Nonnull
    String getDeviceId();

    @Nonnull
    String getDeviceTypeId();

    boolean isAmazonDevice();

    boolean isThirdParty();

    void waitOnInitialized();
}
