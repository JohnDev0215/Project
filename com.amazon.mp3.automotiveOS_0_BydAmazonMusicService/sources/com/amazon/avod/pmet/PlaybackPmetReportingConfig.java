package com.amazon.avod.pmet;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackPmetReportingConfig extends MediaConfigBase {
    private static final ImmutableSet<String> PMET_CDN_PIVOTS_WHITELIST = ImmutableSet.of("Cloudfront", "Akamai", "Limelight", "Level3");
    private static final ImmutableSet<String> PMET_ORIGIN_PIVOTS_SAFELIST = ImmutableSet.of("IAD_US_IAD_1", "IAD_US_IAD_2", "PDX_US_IAD_1", "PDX_US_IAD_2", "IAD_ROW_IAD_1", "IAD_ROW_IAD_2", "DUB_ROW_IAD_1", "DUB_ROW_IAD_2", "MIDAS_IAD_IAD_1", "MIDAS_IAD_IAD_2", "MIDAS_PDX_PDX_1", "MIDAS_PDX_PDX_2", "MIDAS_DUB_PDX_1", "MIDAS_DUB_PDX_2", "MIDAS_FRA_IAD_2", "MIDAS_FRA_IAD_1", "MIDAS_NRT_IAD_1", "MIDAS_NRT_IAD_2", "OTTB_IAD_1", "OTTB_IAD_2");
    private final ConfigurationValue<Boolean> mIsAdErrorPmetReportingEnabled;
    private final ConfigurationValue<Boolean> mIsBufferingAnalysisPmetReportingEnabled;
    private final ConfigurationValue<Boolean> mIsBufferingEventPmetReportingEnabled;
    private final ConfigurationValue<Boolean> mIsCacheEvictionPmetReportingEnabled;
    private final ConfigurationValue<Boolean> mIsCacheOperationPmetReportingEnabled;
    private final ConfigurationValue<Boolean> mIsCacheStatusPmetReportingEnabled;
    private final ConfigurationValue<Boolean> mIsFragmentDownloadPmetReportingEnabled;
    private final ConfigurationValue<Boolean> mIsFragmentQualityPmetReportingEnabled;
    private final ConfigurationValue<Boolean> mIsFragmentURLResolutionErrorsPmetReportingEnabled;
    private final ConfigurationValue<Boolean> mIsManifestDownloadPmetReportingEnabled;
    private final ConfigurationValue<Boolean> mIsMemoryUtilizationPmetReportingEnabled;
    private final ConfigurationValue<Boolean> mIsPeriodicMemoryInfoUpdateEnabled;
    private final ConfigurationValue<Boolean> mIsPlaybackSessionsPmetReportingEnabled;
    private final ConfigurationValue<Boolean> mIsPlaybackSessionsPmetReportingHighPriority;
    private final ConfigurationValue<Boolean> mIsPmetReportingCdnOriginPivotsEnabled;
    private final ConfigurationValue<Boolean> mIsPmetReportingCoreMetricsTitleIdPivotEnabled;
    private final ConfigurationValue<Boolean> mIsPmetReportingSecondaryMetricsTitleIdPivotEnabled;
    private final ConfigurationValue<Boolean> mIsPmetReportingVodOriginPivotsEnabled;
    private final ConfigurationValue<Boolean> mIsSubtitleErrorPmetReportingEnabled;
    private final ConfigurationValue<Boolean> mIsSubtitleMetricsPmetReportingEnabled;
    private final TimeConfigurationValue mPeriodicMemoryInfoUpdateIntervalInSecond;
    private final ConfigurationValue<Integer> mPlaybackSessionsReportingEventDefaultInterval;
    private final ConfigurationValue<Set<String>> mPmetTitleIdPivotsWhitelist;

    private static class SingletonHolder {
        private static final PlaybackPmetReportingConfig INSTANCE = new PlaybackPmetReportingConfig();

        private SingletonHolder() {
        }
    }

    public static PlaybackPmetReportingConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private PlaybackPmetReportingConfig() {
        this.mIsBufferingEventPmetReportingEnabled = newBooleanConfigValue("playback_isBufferingEventPmetReportingEnabled", true);
        this.mIsBufferingAnalysisPmetReportingEnabled = newBooleanConfigValue("playback_isBufferingAnalysisPmetReportingEnabled", true);
        this.mIsPlaybackSessionsPmetReportingEnabled = newBooleanConfigValue("playback_isPlaybackSessionsPmetReportingEnabled", true);
        this.mPlaybackSessionsReportingEventDefaultInterval = newIntConfigValue("playbackSessionsReportingEventDefaultIntervalMins", 5);
        this.mIsPlaybackSessionsPmetReportingHighPriority = newBooleanConfigValue("playback_isPlaybackSessionsPmetReportingHighPriority", false);
        this.mIsPmetReportingCdnOriginPivotsEnabled = newBooleanConfigValue("playback_isPmetReportingCdnOriginPivotsEnabled", true);
        this.mIsPmetReportingVodOriginPivotsEnabled = newBooleanConfigValue("playback_isPmetReportingVodOriginPivotsEnabled", true);
        this.mIsFragmentDownloadPmetReportingEnabled = newBooleanConfigValue("playback_isFragmentDownloadPmetReportingEnabled", true);
        this.mIsSubtitleErrorPmetReportingEnabled = newBooleanConfigValue("playback_isSubtitleErrorPmetReportingEnabled", true);
        this.mIsSubtitleMetricsPmetReportingEnabled = newBooleanConfigValue("playback_isSubtitleMetricsPmetReportingEnabled", true);
        this.mIsAdErrorPmetReportingEnabled = newBooleanConfigValue("playback_isAdErrorPmetReportingEnabled", true);
        this.mIsManifestDownloadPmetReportingEnabled = newBooleanConfigValue("playback_isManifestDownloadPmetReportingEnabled", true);
        this.mIsFragmentQualityPmetReportingEnabled = newBooleanConfigValue("playback_isFragmentQualityPmetReportingEnabled", true);
        this.mIsPmetReportingCoreMetricsTitleIdPivotEnabled = newBooleanConfigValue("playback_isPmetReportingCoreMetricsTitleIdPivotEnabled", true);
        this.mIsPmetReportingSecondaryMetricsTitleIdPivotEnabled = newBooleanConfigValue("playback_isPmetReportingSecondaryMetricsTitleIdPivotEnabled", false);
        this.mPmetTitleIdPivotsWhitelist = newStringSetConfigValue("playback_pmetTitleIdPivotsWhitelist", "", ",");
        this.mIsFragmentURLResolutionErrorsPmetReportingEnabled = newBooleanConfigValue("playback_fragmentURLResolutionErrorsPmetReportingEnabled", true);
        this.mIsPeriodicMemoryInfoUpdateEnabled = newBooleanConfigValue("playback_isPeriodicMemoryInfoUpdateEnabled", true);
        this.mPeriodicMemoryInfoUpdateIntervalInSecond = newTimeConfigurationValue("playback_periodicMemoryInfoUpdateIntervalInSecond", TimeSpan.fromSeconds(60.0d), TimeUnit.SECONDS);
        this.mIsMemoryUtilizationPmetReportingEnabled = newBooleanConfigValue("playback_isMemoryUtilizationPmetReportingEnabled", true);
        this.mIsCacheOperationPmetReportingEnabled = newBooleanConfigValue("playback_isCacheOperationPmetReportingEnabled", true);
        this.mIsCacheStatusPmetReportingEnabled = newBooleanConfigValue("playback_isCacheStatusPmetReportingEnabled", true);
        this.mIsCacheEvictionPmetReportingEnabled = newBooleanConfigValue("playback_isCacheEvicitonPmetReportingEnabled", true);
    }

    public boolean isBufferingEventPmetReportingEnabled() {
        return this.mIsBufferingEventPmetReportingEnabled.getValue().booleanValue();
    }

    public boolean isBufferingAnalysisPmetReportingEnabled() {
        return this.mIsBufferingAnalysisPmetReportingEnabled.getValue().booleanValue();
    }

    public int getPlaybackSessionsReportingEventDefaultIntervalMins() {
        return this.mPlaybackSessionsReportingEventDefaultInterval.getValue().intValue();
    }

    public boolean isPlaybackSessionsPmetReportingHighPriority() {
        return this.mIsPlaybackSessionsPmetReportingHighPriority.getValue().booleanValue();
    }

    public boolean isPmetReportingCdnOriginPivotsEnabled() {
        return this.mIsPmetReportingCdnOriginPivotsEnabled.getValue().booleanValue();
    }

    public boolean isPmetReportingOriginPivotsEnabled() {
        return this.mIsPmetReportingCdnOriginPivotsEnabled.getValue().booleanValue();
    }

    public boolean isPmetReportingVodOriginPivotsEnabled() {
        return this.mIsPmetReportingVodOriginPivotsEnabled.getValue().booleanValue();
    }

    public boolean isFragmentDownloadPmetReportingEnabled() {
        return this.mIsFragmentDownloadPmetReportingEnabled.getValue().booleanValue();
    }

    public boolean isSubtitleErrorPmetReportingEnabled() {
        return this.mIsSubtitleErrorPmetReportingEnabled.getValue().booleanValue();
    }

    public boolean isAdErrorPmetReportingEnabled() {
        return this.mIsAdErrorPmetReportingEnabled.getValue().booleanValue();
    }

    public boolean isSubtitleMetricsPmetReportingEnabled() {
        return this.mIsSubtitleMetricsPmetReportingEnabled.getValue().booleanValue();
    }

    public boolean isManifestDownloadPmetReportingEnabled() {
        return this.mIsManifestDownloadPmetReportingEnabled.getValue().booleanValue();
    }

    public boolean isFragmentQualityPmetReportingEnabled() {
        return this.mIsFragmentQualityPmetReportingEnabled.getValue().booleanValue();
    }

    public boolean isPmetReportingCoreMetricsTitleIdPivotEnabled() {
        return this.mIsPmetReportingCoreMetricsTitleIdPivotEnabled.getValue().booleanValue();
    }

    public boolean isPmetReportingSecondaryMetricsTitleIdPivotEnabled() {
        return this.mIsPmetReportingSecondaryMetricsTitleIdPivotEnabled.getValue().booleanValue();
    }

    @Nonnull
    public Set<String> getPmetTitleIdPivotsWhitelist() {
        return this.mPmetTitleIdPivotsWhitelist.getValue();
    }

    public boolean mIsFragmentURLResolutionErrorsPmetReportingEnabled() {
        return this.mIsFragmentURLResolutionErrorsPmetReportingEnabled.getValue().booleanValue();
    }

    public boolean isCacheOperationPmetReportingEnabled() {
        return this.mIsCacheOperationPmetReportingEnabled.getValue().booleanValue();
    }

    public boolean isCacheStatusPmetReportingEnabled() {
        return this.mIsCacheStatusPmetReportingEnabled.getValue().booleanValue();
    }

    public boolean isCacheEvictionPmetReportingEnabled() {
        return this.mIsCacheEvictionPmetReportingEnabled.getValue().booleanValue();
    }
}
