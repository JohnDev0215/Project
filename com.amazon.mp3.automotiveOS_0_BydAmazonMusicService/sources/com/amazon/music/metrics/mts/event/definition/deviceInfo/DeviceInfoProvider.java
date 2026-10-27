package com.amazon.music.metrics.mts.event.definition.deviceInfo;

/* JADX INFO: loaded from: classes4.dex */
public interface DeviceInfoProvider {
    int getBatteryPercentage();

    float getRemainingDiskGb();

    float getRemainingStreamingCacheGb();

    float getTotalDiskGb();

    float getTotalStreamingCacheGb();

    boolean isBackgroundAppRefreshEnabled();

    boolean isCharging();

    boolean isWifiConnected();
}
