package com.amazon.avod.media.downloadservice.internal;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.experiments.MobileWeblab;
import com.amazon.avod.experiments.PlaybackWeblabs;
import com.amazon.avod.experiments.WeblabTreatment;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.playbackclient.config.PlaybackConfig;
import com.amazon.cloud9.android.knobs.Knobs;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadServiceConfig extends MediaConfigBase {
    public static final DownloadServiceConfig INSTANCE = new DownloadServiceConfig();
    private final TimeConfigurationValue getDownloadServiceTimeoutSweepCadence;
    private final ConfigurationValue<Boolean> mAdjustMaxConcurrentDownloadCount;
    private final ConfigurationValue<Boolean> mAllowImmediateDownloads;
    private final ConfigurationValue<Boolean> mAreRequestTimeoutsStrictlyEnforced;
    private final ConfigurationValue<Float> mBackgroundThrottleFactor;
    private final ConfigurationValue<Integer> mBandwidthAverageCount;
    private final TimeConfigurationValue mBandwidthLogTimeIntervalSeconds;
    private final TimeConfigurationValue mDownloadServiceThreadPoolCoreThreadKeepAliveTime;
    private final ConfigurationValue<Boolean> mEnableDTIDInQueryParameters;
    private final ConfigurationValue<Boolean> mFailOnContentLengthMismatch;
    private final TimeConfigurationValue mHttpResponseCacheMaxAge;
    private final ConfigurationValue<Integer> mHttpResponseCacheSizeMB;
    private final ConfigurationValue<Boolean> mIsHttpResponseCacheEnabled;
    private final ConfigurationValue<Boolean> mIsOkHttpForDownloadServiceEnabled;
    private final ConfigurationValue<Boolean> mIsOkHttpForDownloadServiceWeblabEnabled;
    private final ConfigurationValue<Boolean> mIsSocketTaggingEnabled;
    private final ConfigurationValue<Float> mLivePrimaryBufferFillRatioBeforeSecondaryDownloads;
    private final ConfigurationValue<Boolean> mMTADownloadEnabled;
    private final TimeConfigurationValue mMaxBackgroundThrottleDelay;
    private final ConfigurationValue<Long> mOfflineLicenseValidatorTTLMillis;
    private final ConfigurationValue<Boolean> mPingServerAfterDownloadFailure;
    private final TimeConfigurationValue mPrimaryBufferBeforeSecondaryDownloads;
    private final ConfigurationValue<Boolean> mShouldRequestGzipCompression;
    private final ConfigurationValue<Boolean> mShouldUseThrottledMeteringInputStream;
    private final TimeConfigurationValue mThrottledMeteringInputStreamTimeThreshold;
    private final ConfigurationValue<Integer> mHttpConnectionTimeoutMilliseconds = newIntConfigValue("playback_cdnHttpConnectionTimeoutMilliseconds", 8000);
    private final ConfigurationValue<Integer> mHttpSocketTimeoutMilliseconds = newIntConfigValue("playback_cdnHttpSocketTimeoutMilliseconds", 8000);
    private final ConfigurationValue<String> mHttpUserAgent = newStringConfigValue("playback_cdnHttpUserAgent", "AivAndroidPlayer/1.0 (AndroidDevice)");
    private final ConfigurationValue<String> mPingUrl = newStringConfigValue("playback_pingUrl_2", "https://spectrum.s3.amazonaws.com/kindle-wifi/wifistub.html");
    private final ConfigurationValue<Integer> mMaxConcurrentDownloads = newIntConfigValue("playback_scheduledDownloadServiceMaxConcurrentDownloads", 2);
    private final ConfigurationValue<Integer> mDownloadServiceThreadPoolSize = newIntConfigValue("playback_scheduledDownloadServiceNumberOfThreads", 12);

    private DownloadServiceConfig() {
        TimeSpan timeSpanFromSeconds = TimeSpan.fromSeconds(30.0d);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.mDownloadServiceThreadPoolCoreThreadKeepAliveTime = newTimeConfigurationValue("playback_scheduledDownloadServiceThreadPoolCoreThreadKeepAliveTimeSeconds", timeSpanFromSeconds, timeUnit);
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(250L);
        TimeUnit timeUnit2 = TimeUnit.MILLISECONDS;
        this.getDownloadServiceTimeoutSweepCadence = newTimeConfigurationValue("playback_scheduledDownloadServiceTimeoutSweepCadenceMillis", timeSpanFromMilliseconds, timeUnit2);
        this.mAllowImmediateDownloads = newBooleanConfigValue("playback_downloadServiceAllowImmediateDownloads", false);
        this.mAdjustMaxConcurrentDownloadCount = newBooleanConfigValue("playback_adjustMaxConcurrentDownloadCount", false);
        this.mFailOnContentLengthMismatch = newBooleanConfigValue("playback_downloadServiceFailOnContentLengthMismatch", true);
        this.mBandwidthAverageCount = newIntConfigValue("playback_downloadServiceBandwidthAverageCount", 6);
        this.mPrimaryBufferBeforeSecondaryDownloads = newTimeConfigurationValue("playback_downloadServicePrimaryBufferBeforeSecondaryDownloadsSeconds", TimeSpan.fromSeconds(20.0d), timeUnit);
        this.mLivePrimaryBufferFillRatioBeforeSecondaryDownloads = newFloatConfigValue("playback_livePrimaryBufferFillRatioBeforeSecondaryDownloads", 0.8f);
        this.mBackgroundThrottleFactor = newFloatConfigValue("playback_downloadServiceBackgroundThrottleFactor", 1.0f);
        this.mMaxBackgroundThrottleDelay = newTimeConfigurationValue("playback_MaxDownloadServiceBackgroundThrottleDelay", TimeSpan.fromSeconds(4.0d), timeUnit);
        this.mPingServerAfterDownloadFailure = newBooleanConfigValue("playback_pingServerAfterDownloadFailure", false);
        this.mBandwidthLogTimeIntervalSeconds = newTimeConfigurationValue("playback_BandwidthLogTimeIntervalSeconds", TimeSpan.fromSeconds(60.0d), timeUnit);
        this.mIsSocketTaggingEnabled = newBooleanConfigValue("playback_enableSocketTagging", false);
        this.mShouldUseThrottledMeteringInputStream = newBooleanConfigValue("playback_shouldUseThrottledMeteringInputStream", true);
        this.mThrottledMeteringInputStreamTimeThreshold = newTimeConfigurationValue("playback_throttledMeteringInputStreamTimeThresholdMillis", TimeSpan.fromMilliseconds(500L), timeUnit2);
        ConfigType configType = ConfigType.SERVER;
        this.mMTADownloadEnabled = newBooleanConfigValue("mta_download_enabled", true, configType);
        this.mEnableDTIDInQueryParameters = newBooleanConfigValue("playback_enableDTIDInQueryForManifestAndFragment", true);
        TimeUnit timeUnit3 = TimeUnit.MINUTES;
        this.mOfflineLicenseValidatorTTLMillis = newLongConfigValue("downloads_offline_license_validator_ttl_ms", timeUnit3.toMillis(30L), configType);
        this.mShouldRequestGzipCompression = newBooleanConfigValue("playback_shouldRequestGzipCompression", true);
        this.mIsHttpResponseCacheEnabled = newBooleanConfigValue("playback_isHttpResponseCacheEnabled", true);
        this.mHttpResponseCacheMaxAge = newTimeConfigurationValue("playback_httpResponseCacheMaxAgeMinutes", TimeSpan.fromMinutes(60L), timeUnit3);
        this.mHttpResponseCacheSizeMB = newIntConfigValue("playback_httpResponseCacheSizeMB", 50);
        this.mAreRequestTimeoutsStrictlyEnforced = newBooleanConfigValue("playback_areRequestTimeoutsStrictlyEnforced", true);
        this.mIsOkHttpForDownloadServiceEnabled = newBooleanConfigValue("playback_isOkHttpForDownloadServiceEnabled", false);
        this.mIsOkHttpForDownloadServiceWeblabEnabled = newBooleanConfigValue("playback_isOkHttpForDownloadServiceWeblabEnabled", true);
    }

    public float getBackgroundThrottleFactor() {
        return this.mBackgroundThrottleFactor.getValue().floatValue();
    }

    public TimeSpan getMaxBackgroundThrottleDelay() {
        return this.mMaxBackgroundThrottleDelay.getValue();
    }

    public int getHttpConnectionTimeoutMilliseconds() {
        return this.mHttpConnectionTimeoutMilliseconds.getValue().intValue();
    }

    public int getHttpSocketTimeoutMilliseconds() {
        return this.mHttpSocketTimeoutMilliseconds.getValue().intValue();
    }

    public String getHttpUserAgent() {
        return this.mHttpUserAgent.getValue();
    }

    public int getDownloadServiceTheadPoolSize() {
        return this.mDownloadServiceThreadPoolSize.getValue().intValue() * getAdjustmentFactor();
    }

    public int getDownloadServiceMaxConcurrentDownloads() {
        return this.mMaxConcurrentDownloads.getValue().intValue() * getAdjustmentFactor();
    }

    private int getAdjustmentFactor() {
        return (this.mAdjustMaxConcurrentDownloadCount.getValue().booleanValue() && (PlaybackConfig.getInstance().isEmbeddedPlaybackEnabled() || PlaybackConfig.getInstance().isLiveCacheEnabled())) ? 2 : 1;
    }

    public TimeSpan getPrimaryBufferBeforeSecondaryDownloads() {
        return this.mPrimaryBufferBeforeSecondaryDownloads.getValue();
    }

    public float getLivePrimaryBufferFillRatioBeforeSecondaryDownloads() {
        return this.mLivePrimaryBufferFillRatioBeforeSecondaryDownloads.getValue().floatValue();
    }

    public TimeSpan getDownloadServiceThreadPoolCoreThreadKeepAliveTime() {
        return this.mDownloadServiceThreadPoolCoreThreadKeepAliveTime.getValue();
    }

    public TimeSpan getDownloadServiceTimeoutSweepCadence() {
        return this.getDownloadServiceTimeoutSweepCadence.getValue();
    }

    public int getBandwidthAverageCount() {
        return this.mBandwidthAverageCount.getValue().intValue();
    }

    public boolean shouldPingServerAfterDownloadFailure() {
        return this.mPingServerAfterDownloadFailure.getValue().booleanValue();
    }

    public boolean shouldAddDTIDForManifestAndFragment() {
        return this.mEnableDTIDInQueryParameters.getValue().booleanValue();
    }

    public boolean allowImmediateDownloads() {
        return this.mAllowImmediateDownloads.getValue().booleanValue();
    }

    public boolean failOnContentLengthMismatch() {
        return this.mFailOnContentLengthMismatch.getValue().booleanValue();
    }

    public String getPingUrl() {
        return this.mPingUrl.getValue();
    }

    public TimeSpan getBandwidthLogTimeIntervalSeconds() {
        return this.mBandwidthLogTimeIntervalSeconds.getValue();
    }

    public boolean isSocketTaggingEnabled() {
        return Knobs.get("aiv_socket_tag_enabled", false) || this.mIsSocketTaggingEnabled.getValue().booleanValue();
    }

    public boolean shouldUseThrottledMeteringInputStream() {
        return this.mShouldUseThrottledMeteringInputStream.getValue().booleanValue();
    }

    @Nonnull
    public TimeSpan getThrottledMeteringInputStreamTimeThreshold() {
        return this.mThrottledMeteringInputStreamTimeThreshold.getValue();
    }

    public boolean shouldRequestGzipCompression() {
        return this.mShouldRequestGzipCompression.getValue().booleanValue();
    }

    public boolean isHttpResponseCacheEnabled() {
        return this.mIsHttpResponseCacheEnabled.getValue().booleanValue();
    }

    @Nonnull
    public TimeSpan getHttpResponseCacheMaxAge() {
        return this.mHttpResponseCacheMaxAge.getValue();
    }

    public int getHttpResponseCacheSizeMB() {
        return this.mHttpResponseCacheSizeMB.getValue().intValue();
    }

    public boolean areRequestTimeoutsStrictlyEnforced() {
        return this.mAreRequestTimeoutsStrictlyEnforced.getValue().booleanValue();
    }

    public boolean isOkHttpForDownloadServiceEnabled() {
        if (this.mIsOkHttpForDownloadServiceWeblabEnabled.getValue().booleanValue()) {
            MobileWeblab mobileWeblab = PlaybackWeblabs.getPlaybackWeblabs().get("AIVPLAYERS_OK_HTTP_FOR_DOWNLOAD_SERVICE_320669");
            return (mobileWeblab == null || mobileWeblab.getCurrentTreatment() == WeblabTreatment.C) ? false : true;
        }
        return this.mIsOkHttpForDownloadServiceEnabled.getValue().booleanValue();
    }
}
