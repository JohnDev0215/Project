package com.amazon.avod.media.download.plugin.action;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class RetriablePluginDownloaderConfig extends MediaConfigBase {
    private final ConfigurationValue<Float> mBackOffGrowthFactor;
    private final TimeConfigurationValue mMaxBackOffMillis;
    private final TimeConfigurationValue mMinBackOffMillis;
    private final ConfigurationValue<Integer> mNumRetries = newIntConfigValue("pluginDownloader_numRetries", 5);
    private final ConfigurationValue<Boolean> mEnableExponentialBackoff = newBooleanConfigValue("pluginDownloader_enableExponentialBackoff", true);

    private static final class SingletonHolder {
        private static final RetriablePluginDownloaderConfig INSTANCE = new RetriablePluginDownloaderConfig();

        private SingletonHolder() {
        }
    }

    public static RetriablePluginDownloaderConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public RetriablePluginDownloaderConfig() {
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(500L);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.mMinBackOffMillis = newTimeConfigurationValue("pluginDownloader_minBackOffMillis", timeSpanFromMilliseconds, timeUnit);
        this.mMaxBackOffMillis = newTimeConfigurationValue("pluginDownloader_maxBackOffMillis", TimeSpan.fromMilliseconds(10000L), timeUnit);
        this.mBackOffGrowthFactor = newFloatConfigValue("pluginDownloader_backOffGrowthFactor", 2.0f);
    }

    public int getNumRetries() {
        return this.mNumRetries.getValue().intValue();
    }

    public boolean isExponentialBackoffEnabled() {
        return this.mEnableExponentialBackoff.getValue().booleanValue();
    }

    public TimeSpan getMinBackOffMillis() {
        return this.mMinBackOffMillis.getValue();
    }

    public TimeSpan getMaxBackOffMillis() {
        return this.mMaxBackOffMillis.getValue();
    }

    public float getBackOffGrowthFactor() {
        return this.mBackOffGrowthFactor.getValue().floatValue();
    }
}
