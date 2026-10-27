package com.amazon.avod.media.ads.internal.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.VideoQualityConfig;
import com.amazon.avod.media.framework.platform.DeviceConfiguration;

/* JADX INFO: loaded from: classes2.dex */
public class AdQualityConfig extends VideoQualityConfig {
    private static AdQualityConfig INSTANCE;
    private final ConfigurationValue<Float> mBitratePaddingPercentage;

    public static synchronized AdQualityConfig getInstance(DeviceConfiguration deviceConfiguration) {
        if (INSTANCE == null) {
            INSTANCE = new AdQualityConfig(deviceConfiguration);
        }
        return INSTANCE;
    }

    private AdQualityConfig(DeviceConfiguration deviceConfiguration) {
        super(deviceConfiguration, "ads");
        this.mBitratePaddingPercentage = newFloatConfigValue("ads_bitratePaddingPercentage", 0.2f);
    }

    public float getBitratePaddingPercentage() {
        return this.mBitratePaddingPercentage.getValue().floatValue();
    }
}
