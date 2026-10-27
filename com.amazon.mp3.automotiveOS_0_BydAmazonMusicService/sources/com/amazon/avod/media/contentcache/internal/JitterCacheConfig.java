package com.amazon.avod.media.contentcache.internal;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class JitterCacheConfig extends MediaConfigBase {
    public static final JitterCacheConfig INSTANCE = new JitterCacheConfig();
    private int mBackgroundCacheRequestCount;
    private final TimeConfigurationValue mBackgroundCacheRequestMonitorWindowLength;
    private TimeSpan mLastBackgroundCacheRequestPerformed;
    private final TimeConfigurationValue mMaxDelayDurationForBackgroundCacheRequests;
    private final ConfigurationValue<Integer> mMaxNumberOfBackgroundCacheRequestsPerWindow;
    private TimeSpan mNextScheduledExecution;
    private final ConfigurationValue<Boolean> mShouldAddJitterToBackgroundCacheRequests;

    private JitterCacheConfig() {
        TimeSpan timeSpan = TimeSpan.ZERO;
        this.mLastBackgroundCacheRequestPerformed = timeSpan;
        this.mNextScheduledExecution = timeSpan;
        this.mBackgroundCacheRequestCount = 0;
        ConfigType configType = ConfigType.SERVER;
        this.mShouldAddJitterToBackgroundCacheRequests = newBooleanConfigValue("cache_shouldAddJitterToBackgroundCacheRequests", false, configType);
        TimeSpan timeSpanFromHours = TimeSpan.fromHours(4L);
        TimeUnit timeUnit = TimeUnit.MINUTES;
        this.mMaxDelayDurationForBackgroundCacheRequests = newTimeConfigurationValue("cache_maxDelayDurationForBackgroundCacheRequests", timeSpanFromHours, timeUnit, configType);
        this.mBackgroundCacheRequestMonitorWindowLength = newTimeConfigurationValue("cache_backgroundCacheRequestMonitorWindowLength", TimeSpan.fromMinutes(2L), timeUnit, configType);
        this.mMaxNumberOfBackgroundCacheRequestsPerWindow = newIntConfigValue("cache_maxNumberOfBackgroundCacheRequestsPerWindow", 10, configType);
    }

    public TimeSpan getLastBackgroundCacheRequestPerformed() {
        return this.mLastBackgroundCacheRequestPerformed;
    }

    public TimeSpan getNextScheduledExecution() {
        return this.mNextScheduledExecution;
    }

    public int getBackgroundCacheRequestCount() {
        return this.mBackgroundCacheRequestCount;
    }

    public void setLastBackgroundCacheRequestPerformed(TimeSpan timeSpan) {
        this.mLastBackgroundCacheRequestPerformed = timeSpan;
    }

    public void setNextScheduledExecution(TimeSpan timeSpan) {
        this.mNextScheduledExecution = timeSpan;
    }

    public void setBackgroundCacheRequestCount(int i) {
        this.mBackgroundCacheRequestCount = i;
    }

    public boolean shouldAddJitterToBackgroundCacheRequests() {
        return this.mShouldAddJitterToBackgroundCacheRequests.getValue().booleanValue();
    }

    public TimeSpan getMaxDelayDurationForBackgroundCacheRequestsInMinutes() {
        return this.mMaxDelayDurationForBackgroundCacheRequests.getValue();
    }

    public TimeSpan getSlidingWindowLength() {
        return this.mBackgroundCacheRequestMonitorWindowLength.getValue();
    }

    public int getSlidingWindowThreshold() {
        return this.mMaxNumberOfBackgroundCacheRequestsPerWindow.getValue().intValue();
    }
}
