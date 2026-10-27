package com.amazon.music.metrics.mts.event.definition.deviceInfo;

import com.amazon.music.metrics.mts.MTSEvent;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class DeviceInfoEvent extends MTSEvent {
    public DeviceInfoEvent(DeviceInfoProvider deviceInfoProvider) {
        super("deviceInfo");
        addDeviceInfo(deviceInfoProvider);
    }

    private void addDeviceInfo(DeviceInfoProvider deviceInfoProvider) {
        addAttribute("isCharging", deviceInfoProvider.isCharging());
        Locale locale = Locale.US;
        addAttribute("batteryPercentage", String.format(locale, "%3d", Integer.valueOf(Math.min(100, deviceInfoProvider.getBatteryPercentage()))));
        addAttribute("isWifiConnected", deviceInfoProvider.isWifiConnected());
        addAttribute("isBackgroundAppRefreshEnabled", deviceInfoProvider.isBackgroundAppRefreshEnabled());
        addAttribute("totalDiskGb", String.format(locale, "%3.2f", Float.valueOf(Math.min(999.99f, deviceInfoProvider.getTotalDiskGb()))));
        addAttribute("remainingDiskGb", String.format(locale, "%3.2f", Float.valueOf(Math.min(999.99f, deviceInfoProvider.getRemainingDiskGb()))));
        addAttribute("totalStreamingCacheGb", String.format(locale, "%3.2f", Float.valueOf(Math.min(999.99f, deviceInfoProvider.getTotalStreamingCacheGb()))));
        addAttribute("remainingStreamingCacheGb", String.format(locale, "%3.2f", Float.valueOf(Math.min(999.99f, deviceInfoProvider.getRemainingStreamingCacheGb()))));
    }
}
