package com.amazon.music.metrics.mts;

/* JADX INFO: loaded from: classes4.dex */
public interface MTSEventDataProvider {
    public static final String NO_SUB_DEVICE = null;

    String getAppVersion();

    String getDeviceTimeZone();

    String getLocale();

    String getMusicTerritory();

    String getPlatformName();

    String getSubDeviceId();

    String getSubDeviceStreamMedium();
}
