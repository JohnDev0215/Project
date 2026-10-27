package com.amazon.avod.media.contentcache.internal;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.util.DataUnit;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class CacheConfig extends MediaConfigBase {
    public static final CacheConfig INSTANCE = new CacheConfig();
    private final TimeConfigurationValue mExpirationCheckFrequency;
    private final ConfigurationValue<Boolean> mIsEPrivacyConsentValidationEnabled;
    private final ConfigurationValue<Boolean> mIsLiveCachePeriodicExpiryCheckEnabled;
    private final ConfigurationValue<Boolean> mIsRecordExpiryValidationEnabled;
    private final ConfigurationValue<Boolean> mIsTimeToLiveEvictionEnabled;
    private final TimeConfigurationValue mLiveExpirationCheckFrequency;
    private final ConfigurationValue<Integer> mLiveMaxNumberOfTries;
    private final TimeConfigurationValue mLiveSessionTimeToLive;
    private final TimeConfigurationValue mLiveTimeToLive;
    private final ConfigurationValue<Integer> mMaxCachedLiveContentSessionCount;
    private final ConfigurationValue<Boolean> mShareOfflineKeyId;
    private final ConfigurationValue<Boolean> mShouldAllowEvictionDuringPlayback;
    private final ConfigurationValue<Boolean> mShouldBlockCachingOfUnownedContent;
    private final ConfigurationValue<Boolean> mShouldCacheLiveContentSessions;
    private final ConfigurationValue<Boolean> mShouldEnableAuxCacheKeyTTL;
    private final ConfigurationValue<Boolean> mShouldEnableAuxCacheKeyTTLOverride;
    private final TimeConfigurationValue mTimeIntervalToReportCacheMetrics;
    private final TimeConfigurationValue mTimeToLive;
    private final TimeConfigurationValue mTimeToLiveForAuxillaryEnabledContents;
    private final ConfigurationValue<Boolean> mUseCacheDirectoryFileSize;
    private final SmoothStreamingPlaybackConfig mSmoothStreamingPlaybackConfig = SmoothStreamingPlaybackConfig.INSTANCE;
    private final ConfigurationValue<MediaQuality> mQualityToCache = newEnumConfigValue("cache_qualityToCache", MediaQuality.MEDIUM, MediaQuality.class);
    private final ConfigurationValue<Integer> mMaxCacheRecords = newIntConfigValue("cache_maxNumRecords", 200);
    private final ConfigurationValue<Long> mMinFreeSpaceMB = newLongConfigValue("cache_minFreeSpaceMB", 1024);
    private final ConfigurationValue<Long> mMaxCacheSizeMB = newLongConfigValue("cache_maxCacheSizeMB_2", 500);
    private final ConfigurationValue<Integer> mMaxNumberOfTries = newIntConfigValue("cache_maxNumberOfTries", 5);
    private final ConfigurationValue<Boolean> mShouldBlockCachingOnForegroundApplication = newBooleanConfigValue("cache_shouldBlockCachingOnForegroundApplication_2", false);
    private final ConfigurationValue<Set<String>> mForegroundApplicationPrefixWhitelist = newSemicolonDelimitedStringSetConfigurationValue("cache_foregroundApplicationPrefixWhitelist", new String[]{"com.amazon"});

    private CacheConfig() {
        TimeSpan timeSpanFromHours = TimeSpan.fromHours(120L);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.mTimeToLive = newTimeConfigurationValue("cache_TimeToLiveSeconds", timeSpanFromHours, timeUnit);
        this.mTimeToLiveForAuxillaryEnabledContents = newTimeConfigurationValue("cache_TimeToLiveSecondsForAuxillariesEnabledContents", TimeSpan.fromHours(2L), timeUnit);
        this.mExpirationCheckFrequency = newTimeConfigurationValue("cache_TimeToLiveCheckSeconds", TimeSpan.fromHours(1L), timeUnit);
        TimeSpan timeSpanFromMinutes = TimeSpan.fromMinutes(60L);
        TimeUnit timeUnit2 = TimeUnit.MINUTES;
        this.mTimeIntervalToReportCacheMetrics = newTimeConfigurationValue("cache_TimeToReportNumberOfTitlesAndMemoryCachedMinutes", timeSpanFromMinutes, timeUnit2);
        this.mShouldBlockCachingOfUnownedContent = newBooleanConfigValue("cache_shouldBlockCachingOfUnownedContent", true);
        this.mIsRecordExpiryValidationEnabled = newBooleanConfigValue("cache_isRecordExpiryValidationEnabled", true);
        this.mIsLiveCachePeriodicExpiryCheckEnabled = newBooleanConfigValue("liveCache_isPeriodicExpiryCheckEnabled", true);
        this.mLiveExpirationCheckFrequency = newTimeConfigurationValue("liveCache_timeToLiveCheckMinutes", TimeSpan.fromMinutes(60L), timeUnit2);
        this.mLiveTimeToLive = newTimeConfigurationValue("liveCache_timeToLiveMinutes", TimeSpan.fromMinutes(60L), timeUnit2);
        this.mLiveSessionTimeToLive = newTimeConfigurationValue("liveCache_sessionTimeToLiveSeconds", TimeSpan.fromSeconds(60.0d), timeUnit);
        this.mIsTimeToLiveEvictionEnabled = newBooleanConfigValue("liveCache_isTimeToLiveEvictionEnabled", true);
        this.mShouldCacheLiveContentSessions = newBooleanConfigValue("liveCache_shouldCacheContentSessions", true);
        this.mMaxCachedLiveContentSessionCount = newIntConfigValue("liveCache_maxCachedContentSessionCount", 2);
        this.mLiveMaxNumberOfTries = newIntConfigValue("liveCache_maxTryCount", 2);
        this.mUseCacheDirectoryFileSize = newBooleanConfigValue("cache_useCacheDirectoryFileSize", true);
        ConfigType configType = ConfigType.SERVER;
        this.mShareOfflineKeyId = newBooleanConfigValue("cache_shareOfflineKeyId", true, configType);
        this.mShouldEnableAuxCacheKeyTTL = newBooleanConfigValue("cache_isAuxCacheTTLEnabled", true, configType);
        this.mShouldEnableAuxCacheKeyTTLOverride = newBooleanConfigValue("cache_isAuxCacheTTLEnabledOverride", false, configType);
        this.mShouldAllowEvictionDuringPlayback = newBooleanConfigValue("cache_shouldAllowEvictionDuringPlayback", false, configType);
        this.mIsEPrivacyConsentValidationEnabled = newBooleanConfigValue("cache_isEPrivacyConsentValidationEnabled", true, configType);
    }

    public boolean isRecordExpiryValidationEnabled() {
        return this.mIsRecordExpiryValidationEnabled.getValue().booleanValue();
    }

    public boolean isEPrivacyConsentValidationEnabled() {
        return this.mIsEPrivacyConsentValidationEnabled.getValue().booleanValue();
    }

    public boolean isLiveCachePeriodicExpiryCheckEnabled() {
        return this.mIsLiveCachePeriodicExpiryCheckEnabled.getValue().booleanValue();
    }

    public TimeSpan getLiveExpirationCheckFrequency() {
        return this.mLiveExpirationCheckFrequency.getValue();
    }

    @Nonnull
    public TimeSpan getTimeToLive() {
        return this.mTimeToLive.getValue();
    }

    @Nonnull
    public TimeSpan getTimeToLiveForAuxillaryEnabledContents() {
        return this.mTimeToLiveForAuxillaryEnabledContents.getValue();
    }

    public boolean shouldCacheLiveContentSessions() {
        return this.mShouldCacheLiveContentSessions.getValue().booleanValue();
    }

    public int getMaxCachedLiveContentSessionCount() {
        return this.mMaxCachedLiveContentSessionCount.getValue().intValue();
    }

    @Nonnull
    public TimeSpan getLiveTimeToLive() {
        return this.mLiveTimeToLive.getValue();
    }

    @Nonnull
    public TimeSpan getLiveSessionTimeToLive() {
        return this.mLiveSessionTimeToLive.getValue();
    }

    public boolean isTimeToLiveEvictionEnabled() {
        return this.mIsTimeToLiveEvictionEnabled.getValue().booleanValue();
    }

    public int getLiveMaxNumberOfTries() {
        return this.mLiveMaxNumberOfTries.getValue().intValue();
    }

    public TimeSpan getExpirationCheckFrequency() {
        return this.mExpirationCheckFrequency.getValue();
    }

    @Nonnull
    public TimeSpan getDurationToCache() {
        return this.mSmoothStreamingPlaybackConfig.getRequiredCacheSizeForWhisperCache();
    }

    public MediaQuality getQualityToCache() {
        return this.mQualityToCache.getValue();
    }

    public int getMaxNumberOfCacheRecords() {
        return this.mMaxCacheRecords.getValue().intValue();
    }

    public long getMinimalFreeSpaceInBytes() {
        return DataUnit.MEGABYTES.toBytes(this.mMinFreeSpaceMB.getValue().longValue());
    }

    public long getMaxCacheSizeInKb() {
        return (long) DataUnit.MEGABYTES.toKiloBytes(this.mMaxCacheSizeMB.getValue().longValue());
    }

    public int getMaxNumberOfTries() {
        return this.mMaxNumberOfTries.getValue().intValue();
    }

    public boolean shouldBlockCachingOnForegroundApplication() {
        return this.mShouldBlockCachingOnForegroundApplication.getValue().booleanValue();
    }

    public Set<String> getForegroundApplicationPrefixWhitelist() {
        return this.mForegroundApplicationPrefixWhitelist.getValue();
    }

    public TimeSpan getTimeIntervalToReportCacheMetrics() {
        return this.mTimeIntervalToReportCacheMetrics.getValue();
    }

    public boolean shouldBlockCachingOfUnownedContent() {
        return this.mShouldBlockCachingOfUnownedContent.getValue().booleanValue();
    }

    public boolean shouldUseCacheDirectoryFileSize() {
        return this.mUseCacheDirectoryFileSize.getValue().booleanValue();
    }

    public boolean getShareOfflineKeyId() {
        return this.mShareOfflineKeyId.getValue().booleanValue();
    }

    public boolean shouldUseAuxCacheTTL() {
        return this.mShouldEnableAuxCacheKeyTTL.getValue().booleanValue() || this.mShouldEnableAuxCacheKeyTTLOverride.getValue().booleanValue();
    }

    public boolean shouldAllowEvictionDuringPlayback() {
        return this.mShouldAllowEvictionDuringPlayback.getValue().booleanValue();
    }
}
