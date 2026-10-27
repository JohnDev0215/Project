package com.amazon.avod.qos;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.qos.metadata.QOSEventName;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class QoSConfig extends MediaConfigBase {
    public static final QoSConfig INSTANCE = new QoSConfig();
    private final ConfigurationValue<Integer> mBatchUploadProbability = newIntConfigValue("qos_fragmentBatchUploadProbability", 100);
    private final ConfigurationValue<Integer> mFragmentBatchSize = newIntConfigValue("qos_fragmentBatchSize", 50);
    private final TimeConfigurationValue mFragmentDownloadTimeReportingThreshold = newTimeConfigurationValue("qos_FragmentDownloadTimeThreshold", TimeSpan.fromMilliseconds(100000), TimeUnit.MILLISECONDS);
    private final TimeConfigurationValue mIntervalBetweenPlaybackSessionUploads = newTimeConfigurationValue("qos_intervalBetweenPlaybackSessionUploadsMinutes", TimeSpan.fromMinutes(20), TimeUnit.MINUTES);
    private final ConfigurationValue<String> mReportingConfigVersion = newStringConfigValue("qos_ReportingConfigVersion", "(default)");
    private final ConfigurationValue<Boolean> mReportPerFragmentMetricsOnDownload = newBooleanConfigValue("qos_ReportPerFragmentMetricsOnDownload", false);
    private final ConfigurationValue<Set<String>> mImmediateEvents = newSemicolonDelimitedStringSetConfigurationValue("qos_immediateUploadEvents", new String[]{QOSEventName.StartPlayback.name(), QOSEventName.PlaybackSession.name()});
    private final ConfigurationValue<Set<String>> mBlacklistedGenericEvents = newSemicolonDelimitedStringSetConfigurationValue("qos_BlacklistedGenericEvents", new String[0]);
    private final ConfigurationValue<Boolean> mMultiSessionReportingEnabled = newBooleanConfigValue("qos_enableMultiSessionReporting", false);
    private final ConfigurationValue<Boolean> mShouldReportMetricsForCaching = newBooleanConfigValue("qos_shouldReportMetricsForCaching", true);
    private final ConfigurationValue<Boolean> mShouldThrottlePerformanceEvents = newBooleanConfigValue("qos_shouldThrottlePerformanceEvents", true);
    private final ConfigurationValue<Boolean> mShouldDropQoSEventsForSdkPlayer = newBooleanConfigValue("qos_shouldDropQoSEventsForSdkPlayer", true);
    private final ConfigurationValue<Boolean> mShouldDropLegacyEventsOnInsufficientDiskSpace = newBooleanConfigValue("qos_shouldDropLegacyEventsOnInsufficientDiskSpace", true, ConfigType.SERVER);
    private final TimeConfigurationValue mIntervalToEnsureStableBitrateInSeconds = newTimeConfigurationValue("qos_IntervalToEnsureStableBitrateInSecond", TimeSpan.fromSeconds(30.0d), TimeUnit.SECONDS);

    private QoSConfig() {
    }

    public int getFragmentBatchUploadProbability() {
        return this.mBatchUploadProbability.getValue().intValue();
    }

    public int getFragmentBatchSize() {
        return this.mFragmentBatchSize.getValue().intValue();
    }

    public TimeSpan getFragmentDownloadTimeThreshold() {
        return this.mFragmentDownloadTimeReportingThreshold.getValue();
    }

    public TimeSpan getIntervalBetweenPlaybackSessionUploads() {
        return this.mIntervalBetweenPlaybackSessionUploads.getValue();
    }

    public boolean reportPerFragmentMetricsOnDownload() {
        return this.mReportPerFragmentMetricsOnDownload.getValue().booleanValue();
    }

    public Set<String> getBlacklistedGenericEvents() {
        return this.mBlacklistedGenericEvents.getValue();
    }

    public Set<String> getImmediateUploadEvents() {
        return this.mImmediateEvents.getValue();
    }

    public String getReportingConfigVersion() {
        return this.mReportingConfigVersion.getValue();
    }

    public boolean getMultiSessionReportingEnabled() {
        return this.mMultiSessionReportingEnabled.getValue().booleanValue();
    }

    public boolean shouldReportMetricsForCaching() {
        return this.mShouldReportMetricsForCaching.getValue().booleanValue();
    }

    public boolean shouldThrottlePerformanceEvents() {
        return this.mShouldThrottlePerformanceEvents.getValue().booleanValue();
    }

    public boolean shouldDropQoSEventsForSdkPlayer() {
        return this.mShouldDropQoSEventsForSdkPlayer.getValue().booleanValue();
    }

    public TimeSpan getTimeIntervalToEnsureStableBitrate() {
        return this.mIntervalToEnsureStableBitrateInSeconds.getValue();
    }

    public boolean shouldDropLegacyEventsOnInsufficientDiskSpace() {
        return this.mShouldDropLegacyEventsOnInsufficientDiskSpace.getValue().booleanValue();
    }
}
