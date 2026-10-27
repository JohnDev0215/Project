package com.amazon.avod.media.ads.internal.config;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.atv.cuepoint.CuepointPlaylist;
import com.amazon.atv.cuepoint.CuepointPlaylistMetadata;
import com.amazon.atv.cuepoint.ManifestFormat;
import com.amazon.atvplaybackresource.types.EntitlementType;
import com.amazon.avod.ads.api.AdSkipInfo;
import com.amazon.avod.core.Framework;
import com.amazon.avod.experiments.MobileWeblab;
import com.amazon.avod.experiments.PlaybackWeblabs;
import com.amazon.avod.experiments.WeblabTreatment;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.util.BetaConfigProvider;
import com.amazon.avod.util.DataUnit;
import com.amazon.avod.util.SerializationUtils;
import com.google.common.base.Preconditions;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;

/* JADX INFO: loaded from: classes2.dex */
public class AdsConfig extends MediaConfigBase {
    private static AdsConfig INSTANCE;
    private final ConfigurationValue<Float> mAdBreakBufferMillisFactor;
    private final ConfigurationValue<Integer> mAdBreakNumBuffersThreshold;
    private final ConfigurationValue<Integer> mAdCacheBitrateKbps;
    private final ConfigurationValue<Long> mAdCacheMaxSizeBytes;
    private final TimeConfigurationValue mAdClipBufferingTimeout;
    private final TimeConfigurationValue mAdClipPrepareTimeout;
    private final ConfigurationValue<Integer> mAdLiveBeaconThreads;
    private final ConfigurationValue<Boolean> mAdMasterAddToWatchlistEnabled;
    private final ConfigurationValue<Boolean> mAdMasterEnabled;
    private final ConfigurationValue<String> mAdMasterEncodedManifestURL;
    private final ConfigurationValue<String> mAdMasterLearnMoreURL;
    private final ConfigurationValue<Boolean> mAdMasterSkipEnabled;
    private final ConfigurationValue<Boolean> mAdMidRollAggressiveCaching;
    private final TimeConfigurationValue mAdPreloadDownloadTimeout;
    private final TimeConfigurationValue mAdSelectionTimeSpan;
    private final TimeConfigurationValue mAdTransitionInTime;
    private final TimeConfigurationValue mAdTransitionOutTime;
    private final ConfigurationValue<Float> mAdVolumeScaleFactor;
    private final ConfigurationValue<Set<String>> mAllowedCookieDomains;
    private final ConfigurationValue<Integer> mBeaconRetries;
    private final TimeConfigurationValue mBeaconTimeout;
    private final ConfigurationValue<Set<String>> mBlackListOfferTypes;
    private final ConfigurationValue<Set<String>> mBlacklistUrls;
    private final TimeConfigurationValue mCachedCuepointTimeToLiveInSeconds;
    private final ConfigurationValue<Set<String>> mDNWFDOfferTypeList;
    private final ConfigurationValue<String> mDeviceAdInsertionTypeOverride;
    private final ConfigurationValue<Set<String>> mDraperTypes;
    private final ConfigurationValue<Boolean> mDumpVastManifestToLogs;
    private final ConfigurationValue<Boolean> mEnableAdClipSkip;
    private final ConfigurationValue<Boolean> mEnablePersistentCacheForAvodPreRolls;
    private final ConfigurationValue<Boolean> mEnablePersistentCacheForDraperPreRolls;
    private final ConfigurationValue<Boolean> mEnableVastReporting;
    private final ConfigurationValue<Boolean> mEnabledAdIdBasedCaching;
    private final TimeConfigurationValue mGPAIDFetchTimeOut;
    private final ConfigurationValue<Boolean> mHonorAdScalableProperty;
    private final ConfigurationValue<Boolean> mIgnoreUnsupportedCreativeTypes;
    private final ConfigurationValue<Boolean> mIsAdsAloysiusStateTransitionEnabled;
    private final TimeConfigurationValue mLiveBeaconTimeout;
    private final ConfigurationValue<Boolean> mLiveTrackingEmsgRefreshEnabled;
    private final ConfigurationValue<Boolean> mLiveTrackingHardOff;
    private final ConfigurationValue<Boolean> mLiveTrackingUriRefreshEnabled;
    private final ConfigurationValue<Boolean> mPreInitAdPreloadingEnabled;
    private final ConfigurationValue<Boolean> mPrimePreRollCacheEnabled;
    private final ConfigurationValue<Integer> mRequestRetries;
    private final TimeConfigurationValue mRequestTimeout;
    private final ConfigurationValue<Set<String>> mSafeListedTitleIDsForTEVSReporting;
    private final ConfigurationValue<Long> mSeekOverAdBreakEndOffsetInMs;
    private final ConfigurationValue<Long> mSeekOverAdBreakStartOffsetInMs;
    private final ConfigurationValue<Boolean> mShouldDeinitOnPlaybackStop;
    private final ConfigurationValue<Boolean> mShouldEnableAdPlanTimeOutInDAGSSAI;
    private final ConfigurationValue<Boolean> mShouldEnableDraperForWatchFromBeginning;
    private final ConfigurationValue<Boolean> mShouldEnableEmptyAdsManifestParsingOptimization;
    private final ConfigurationValue<Boolean> mShouldEnableFragmentDownloaderForSSAIDownloads;
    private final ConfigurationValue<Boolean> mShouldEnableSSAI;
    private final ConfigurationValue<Boolean> mShouldEnableSSAIForDownloads;
    private final ConfigurationValue<Boolean> mShouldEnableSSAIForDownloadsInBeta;
    private final ConfigurationValue<Boolean> mShouldReportPlaybackStartEventDuringAds;
    private final ConfigurationValue<Boolean> mShouldReportTEVSEmptyAvailsResponse;
    private final ConfigurationValue<Boolean> mShouldReuseCachedCuePointPlaylist;
    private final ConfigurationValue<Boolean> mShouldReuseCachedCuePointPlaylistInDAGPath;
    private final ConfigurationValue<Boolean> mShouldShutdownExecutor;
    private final ConfigurationValue<Boolean> mSkipMissedPrimePreRollAds;
    private final ConfigurationValue<Long> mStrictModeTrustIntervalForBeaconReportingInMillis;
    private final ConfigurationValue<Boolean> mSuppressAdClipStartStopReporting;
    private final ConfigurationValue<Boolean> mSuppressAdsForDownloads;
    private final ConfigurationValue<Boolean> mSuppressCuepointsForChildRole;
    private final TimeConfigurationValue mTEVSRequestFrequency;
    private final ConfigurationValue<Long> mTimelineManagerInitializationTimeoutInMilliseconds;
    private final ConfigurationValue<Boolean> mUseBoltHttp;
    private final ConfigurationValue<Boolean> mUseDeviceUserAgent;
    private final ConfigurationValue<String> mUserAgent;
    private final ConfigurationValue<String> mVASTManifestTimelineType;
    private final ConfigurationValue<Set<String>> mWhitelistUrls;

    public static synchronized AdsConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AdsConfig();
        }
        return INSTANCE;
    }

    private AdsConfig() {
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(45000L);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.mAdPreloadDownloadTimeout = newTimeConfigurationValue("ads_preloadDownloadTimeoutMilliseconds", timeSpanFromMilliseconds, timeUnit);
        this.mWhitelistUrls = newStringSetConfigValue("ads_clickThroughWhitelist", "", ";");
        this.mBlacklistUrls = newStringSetConfigValue("ads_clickThroughBlacklist", "", ";");
        this.mAllowedCookieDomains = newStringSetConfigValue("ads_cookieDomainWhitelist", "amazon.com;vindicosuite.com", ";");
        this.mAdCacheMaxSizeBytes = newLongConfigValue("ads_cacheSizeBytes", DataUnit.MEGABYTES.toBytes(100.0f));
        this.mAdCacheBitrateKbps = newIntConfigValue("ad_cacheBitrateKbps", DisconnectedBufferOptions.DISCONNECTED_BUFFER_SIZE_DEFAULT);
        this.mAdBreakBufferMillisFactor = newFloatConfigValue("ads_adBreakBufferMillisFactor", 1.5f);
        this.mAdBreakNumBuffersThreshold = newIntConfigValue("ads_adBreakNumBuffersThreshold", 3);
        TimeSpan timeSpanFromSeconds = TimeSpan.fromSeconds(10.0d);
        TimeUnit timeUnit2 = TimeUnit.SECONDS;
        this.mAdClipPrepareTimeout = newTimeConfigurationValue("ads_clipPrepareTimeoutSeconds", timeSpanFromSeconds, timeUnit2);
        this.mAdVolumeScaleFactor = newFloatConfigValue("ads_adVolumeScaleFactor", 1.0f);
        this.mTEVSRequestFrequency = newTimeConfigurationValue("ads_mTEVSRequestFrequencyInSeconds", TimeSpan.fromSeconds(6.0d), timeUnit2);
        this.mGPAIDFetchTimeOut = newTimeConfigurationValue("ads_GPAIDFetchTimeOutInSeconds", TimeSpan.fromMilliseconds(1000L), timeUnit);
        this.mLiveTrackingUriRefreshEnabled = newBooleanConfigValue("ads_refreshTrackingUriForLive", true);
        this.mLiveTrackingEmsgRefreshEnabled = newBooleanConfigValue("ads_enableEmsgBasedTrackingUriForLive", false);
        this.mLiveTrackingHardOff = newBooleanConfigValue("ads_refreshTrackingUriForLiveHardOff", false);
        this.mEnableVastReporting = newBooleanConfigValue("ads_enableVastReporting", true);
        this.mAdLiveBeaconThreads = newIntConfigValue("ads_liveBeaconThreads", 2);
        this.mLiveBeaconTimeout = newTimeConfigurationValue("ads_liveBeaconTimeoutMillis", TimeSpan.fromMilliseconds(4000L), timeUnit);
        this.mHonorAdScalableProperty = newBooleanConfigValue("ads_honorAdScalableProperty", false);
        this.mIgnoreUnsupportedCreativeTypes = newBooleanConfigValue("ads_ignoreUnsupportedCreativeTypes", true);
        this.mPreInitAdPreloadingEnabled = newBooleanConfigValue("ads_enablePreInitAdPreloading", true);
        this.mPrimePreRollCacheEnabled = newBooleanConfigValue("ads_enablePrimePreRollCache", true);
        this.mSkipMissedPrimePreRollAds = newBooleanConfigValue("ads_skipMissedPrimePreRollAds", false);
        this.mDumpVastManifestToLogs = newBooleanConfigValue("ads_dumpVastManifestToLogs", false);
        this.mEnabledAdIdBasedCaching = newBooleanConfigValue("ads_enableAdIdBasedCaching", true);
        this.mEnablePersistentCacheForDraperPreRolls = newBooleanConfigValue("ads_enablePersistentCacheForDraperPreRolls", true);
        this.mEnablePersistentCacheForAvodPreRolls = newBooleanConfigValue("ads_enablePersistentCacheForAvodPreRolls", false);
        this.mBeaconRetries = newIntConfigValue("ads_beaconRetries", 3);
        this.mBeaconTimeout = newTimeConfigurationValue("ads_beaconTimeout", TimeSpan.fromMilliseconds(10000L), timeUnit);
        this.mRequestRetries = newIntConfigValue("ads_requestRetries", 3);
        this.mRequestTimeout = newTimeConfigurationValue("ads_requestTimeout", TimeSpan.fromMilliseconds(3000L), timeUnit);
        this.mUserAgent = newStringConfigValue("ads_userAgent", "AivAndroidPlayer/1.0 (AndroidDevice)");
        this.mAdTransitionInTime = newTimeConfigurationValue("ads_transitionInTimeMillis", TimeSpan.fromMilliseconds(500L), timeUnit);
        this.mAdTransitionOutTime = newTimeConfigurationValue("ads_transitionOutTimeMillis", TimeSpan.fromMilliseconds(500L), timeUnit);
        this.mDNWFDOfferTypeList = newSemicolonDelimitedStringSetConfigurationValue("ads_DoNotWaitForDraperOfferTypeList", new String[0]);
        ConfigType configType = ConfigType.SERVER;
        this.mShouldEnableEmptyAdsManifestParsingOptimization = newBooleanConfigValue("ads_ShouldEnableEmptyAdsManifestParsingOptimization", true, configType);
        this.mShouldReuseCachedCuePointPlaylist = newBooleanConfigValue("ads_shouldReuseCachedCuepointPlaylist", false, configType);
        this.mShouldReuseCachedCuePointPlaylistInDAGPath = newBooleanConfigValue("ads_shouldReuseCachedCuePointPlaylistInDAGPath", false, configType);
        this.mCachedCuepointTimeToLiveInSeconds = newTimeConfigurationValue("ads_cachedCuepointTimeToLiveInSeconds", TimeSpan.fromSeconds(86400.0d), timeUnit2, configType);
        this.mBlackListOfferTypes = newSemicolonDelimitedStringSetConfigurationValue("ads_offerTypeBlackList", new String[]{EntitlementType.A4K_SUBSCRIPTION.name(), EntitlementType.RENTAL.name(), EntitlementType.PURCHASE.name()});
        this.mAdSelectionTimeSpan = newTimeConfigurationValue("ads_selectorTimeSpanMilliseconds", TimeSpan.fromMilliseconds(250L), timeUnit);
        this.mDraperTypes = newSemicolonDelimitedStringSetConfigurationValue("ads_DraperTypes", new String[]{"atvdraperschedulingservice"});
        this.mAdClipBufferingTimeout = newTimeConfigurationValue("ads_clipBufferingTimeoutSeconds", TimeSpan.fromSeconds(5.0d), timeUnit2);
        this.mAdMasterEnabled = newBooleanConfigValue("ads_adMaster_enabled", false);
        this.mShouldShutdownExecutor = newBooleanConfigValue("adstatemachine_shouldShutdownAdsExecutor", false);
        this.mShouldDeinitOnPlaybackStop = newBooleanConfigValue("ads_shouldDeinitOnPlaybackStop", true);
        this.mDeviceAdInsertionTypeOverride = newStringConfigValue("ads_deviceAdInsertionTypeOverride", "SSAI", configType);
        this.mAdMasterEncodedManifestURL = newStringConfigValue("ads_adMaster_encodedManifestURL", "https://tiny.amazon.com/8zmq6v9p/drapermanifest");
        this.mAdMasterLearnMoreURL = newStringConfigValue("ads_adMaster_learnMoreURL", "https://www.amazon.com");
        this.mAdMasterSkipEnabled = newBooleanConfigValue("ads_adMaster_skipEnabled", true);
        this.mAdMasterAddToWatchlistEnabled = newBooleanConfigValue("ads_adMaster_addToWatchlistEnabled", true);
        this.mAdMidRollAggressiveCaching = newBooleanConfigValue("ads_enableMidRollAggressiveCaching", true);
        this.mUseBoltHttp = newBooleanConfigValue("ads_useBoltHttp", true);
        this.mUseDeviceUserAgent = newBooleanConfigValue("ads_useDeviceUserAgent", true);
        this.mSuppressCuepointsForChildRole = newBooleanConfigValue("ads_suppressCuepointsForChildRole", true);
        this.mSuppressAdClipStartStopReporting = newBooleanConfigValue("ads_suppressAdClipStartStopReporting", true);
        this.mEnableAdClipSkip = newBooleanConfigValue("ads_skip", false);
        this.mShouldEnableSSAI = newBooleanConfigValue("ads_enableSSAI_2", true);
        this.mShouldEnableSSAIForDownloads = newBooleanConfigValue("ads_enableSSAIForDownloads", false);
        this.mShouldEnableSSAIForDownloadsInBeta = newBooleanConfigValue("ads_enableSSAIForDownloadsInBeta", false);
        this.mShouldEnableFragmentDownloaderForSSAIDownloads = newBooleanConfigValue("ads_shouldEnableFragmentDownloaderForSSAIDownloads", true);
        this.mSeekOverAdBreakStartOffsetInMs = newLongConfigValue("ads_seekOverAdBreakStartOffsetInMs", 50L);
        this.mSeekOverAdBreakEndOffsetInMs = newLongConfigValue("ads_seekOverAdBreakEndOffsetInMs", 50L);
        this.mShouldReportPlaybackStartEventDuringAds = newBooleanConfigValue("ads_shouldReportPlaybackStartEventDuringAds", true);
        this.mIsAdsAloysiusStateTransitionEnabled = newBooleanConfigValue("ads_isAdsAloysiusStateTransitionEnabled", true);
        this.mShouldReportTEVSEmptyAvailsResponse = newBooleanConfigValue("ads_shouldReportTEVSResponseStatus", false);
        this.mSafeListedTitleIDsForTEVSReporting = newStringSetConfigValue("ads_safeListTitleIDsForTEVSReporting", "", ";");
        this.mVASTManifestTimelineType = newStringConfigValue("ads_vastManifestTimelineType", "absolute");
        this.mTimelineManagerInitializationTimeoutInMilliseconds = newLongConfigValue("ads_timelineManagerInitializationTimeoutInMilliseconds", CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        this.mShouldEnableDraperForWatchFromBeginning = newBooleanConfigValue("ads_shouldEnableDraperForWatchFromBeginning", true);
        this.mStrictModeTrustIntervalForBeaconReportingInMillis = newLongConfigValue("ads_strictModeTrustIntervalForBeaconReportingInMillis", 500L);
        this.mShouldEnableAdPlanTimeOutInDAGSSAI = newBooleanConfigValue("ads_shouldEnableAdPlanTimeOutInDAGSSAI", false);
        this.mSuppressAdsForDownloads = newBooleanConfigValue("ads_suppressAdsForDownloads", true);
    }

    public boolean getHonorAdScalableProperty() {
        return this.mHonorAdScalableProperty.getValue().booleanValue();
    }

    public boolean getIgnoreUnsupportedCreativeTypes() {
        return this.mIgnoreUnsupportedCreativeTypes.getValue().booleanValue();
    }

    public boolean getPreInitAdPreloadingEnabled() {
        return this.mPreInitAdPreloadingEnabled.getValue().booleanValue();
    }

    public boolean getPrimePreRollCacheEnabled() {
        return this.mPrimePreRollCacheEnabled.getValue().booleanValue();
    }

    public boolean getSkipMissedPrimePreRollAds() {
        return this.mSkipMissedPrimePreRollAds.getValue().booleanValue();
    }

    public TimeSpan getPreloadDownloadTimeout() {
        return this.mAdPreloadDownloadTimeout.getValue();
    }

    public Set<String> getWhitelistUrls() {
        return this.mWhitelistUrls.getValue();
    }

    public Set<String> getBlacklistUrls() {
        return this.mBlacklistUrls.getValue();
    }

    public Set<String> getAllowedCookieDomains() {
        return this.mAllowedCookieDomains.getValue();
    }

    public long getCacheMaxSizeBytes() {
        return this.mAdCacheMaxSizeBytes.getValue().longValue();
    }

    public int getCacheBitrateKbps() {
        return this.mAdCacheBitrateKbps.getValue().intValue();
    }

    public float getAdBreakBufferMillisFactor() {
        return this.mAdBreakBufferMillisFactor.getValue().floatValue();
    }

    public int getAdBreakNumBuffersThreshold() {
        return this.mAdBreakNumBuffersThreshold.getValue().intValue();
    }

    public TimeSpan getAdClipPrepareTimeout() {
        return this.mAdClipPrepareTimeout.getValue();
    }

    public float getAdVolumeScaleFactor() {
        return this.mAdVolumeScaleFactor.getValue().floatValue();
    }

    public int getBeaconRetries() {
        return this.mBeaconRetries.getValue().intValue();
    }

    public TimeSpan getBeaconTimeout() {
        return this.mBeaconTimeout.getValue();
    }

    @Nonnull
    public TimeSpan getLiveBeaconTimeout() {
        return this.mLiveBeaconTimeout.getValue();
    }

    public int getBeaconThreadCount() {
        return this.mAdLiveBeaconThreads.getValue().intValue();
    }

    public int getRequestRetries() {
        return this.mRequestRetries.getValue().intValue();
    }

    public TimeSpan getRequestTimeout() {
        return this.mRequestTimeout.getValue();
    }

    public TimeSpan getTEVSRequestFrequency() {
        return this.mTEVSRequestFrequency.getValue();
    }

    public TimeSpan getGPAIDFetchTimeOut() {
        return this.mGPAIDFetchTimeOut.getValue();
    }

    public boolean shouldRefreshTrackingUriForLive() {
        return this.mLiveTrackingUriRefreshEnabled.getValue().booleanValue();
    }

    public boolean shouldRefreshTrackingUriOnEmsg() {
        return this.mLiveTrackingEmsgRefreshEnabled.getValue().booleanValue();
    }

    public boolean isTrackingEnabled() {
        return !this.mLiveTrackingHardOff.getValue().booleanValue();
    }

    public boolean isVastReportingEnabled() {
        return this.mEnableVastReporting.getValue().booleanValue();
    }

    public String getUserAgent() {
        return this.mUserAgent.getValue();
    }

    public TimeSpan getAdTransitionInTime() {
        return this.mAdTransitionInTime.getValue();
    }

    public TimeSpan getAdTransitionOutTime() {
        return this.mAdTransitionOutTime.getValue();
    }

    public boolean shouldSkipAdIfNotReady(@Nonnull String str) {
        return this.mDNWFDOfferTypeList.getValue().contains(str);
    }

    public boolean shouldEnableEmptyAdsManifestParsingOptimization() {
        return this.mShouldEnableEmptyAdsManifestParsingOptimization.getValue().booleanValue();
    }

    public Set<String> getBlackListAdTypes() {
        return this.mBlackListOfferTypes.getValue();
    }

    public boolean shouldDumpVastManifestToLogs() {
        return this.mDumpVastManifestToLogs.getValue().booleanValue();
    }

    public boolean isAdIdBasedCachingEnabled() {
        return this.mEnabledAdIdBasedCaching.getValue().booleanValue();
    }

    public boolean isPersistentCacheForDraperPreRollsEnabled() {
        return this.mEnablePersistentCacheForDraperPreRolls.getValue().booleanValue();
    }

    public boolean isPersistentCacheForAvodPreRollsEnabled() {
        return this.mEnablePersistentCacheForAvodPreRolls.getValue().booleanValue();
    }

    @Nonnull
    public TimeSpan getAdSelectionTimeSpan() {
        return this.mAdSelectionTimeSpan.getValue();
    }

    public boolean isAdBreakDraper(@Nonnull AdBreak adBreak) {
        Preconditions.checkNotNull(adBreak, "adBreak");
        String adSystem = adBreak.getAdSystem();
        return adSystem != null && this.mDraperTypes.getValue().contains(adSystem.toLowerCase(Locale.US));
    }

    public TimeSpan getAdClipBufferingTimeout() {
        return this.mAdClipBufferingTimeout.getValue();
    }

    public boolean getAdMasterEnabled() {
        if (Framework.isDebugConfigurationEnabled()) {
            return this.mAdMasterEnabled.getValue().booleanValue();
        }
        return false;
    }

    @Nonnull
    public CuepointPlaylist getAdMasterInjectedCuepoint() {
        CuepointPlaylist.Builder builder = new CuepointPlaylist.Builder();
        CuepointPlaylistMetadata.Builder builder2 = new CuepointPlaylistMetadata.Builder();
        builder2.showCountdownTimer = true;
        builder.additionalMetadata = builder2.build();
        try {
            builder.encodedManifest = SerializationUtils.toString(FirebasePerfUrlConnection.openStream(new URL(this.mAdMasterEncodedManifestURL.getValue())));
        } catch (IOException unused) {
            builder.encodedManifest = "";
        }
        builder.format = ManifestFormat.VMAP_VAST;
        return builder.build();
    }

    @Nonnull
    public String getAdMasterInjectedLearnMoreURL() {
        return this.mAdMasterLearnMoreURL.getValue();
    }

    @Nullable
    public AdSkipInfo getAdSkipInfo() {
        if (this.mAdMasterSkipEnabled.getValue().booleanValue()) {
            return new AdSkipInfo(true, 2L, 0L);
        }
        return null;
    }

    public boolean isMidRollAggressiveCachingEnabled() {
        return this.mAdMidRollAggressiveCaching.getValue().booleanValue();
    }

    public boolean isBoltHttpClientEnabledForAds() {
        return this.mUseBoltHttp.getValue().booleanValue();
    }

    public boolean shouldUseDeviceUserAgent() {
        return this.mUseDeviceUserAgent.getValue().booleanValue();
    }

    public boolean shouldSuppressCuepointsForChildRole() {
        return this.mSuppressCuepointsForChildRole.getValue().booleanValue();
    }

    public boolean shouldSuppressAdClipStartStopReporting() {
        return this.mSuppressAdClipStartStopReporting.getValue().booleanValue();
    }

    public boolean shouldSkipAds() {
        return this.mEnableAdClipSkip.getValue().booleanValue();
    }

    public boolean isSSAIEnabled() {
        return this.mShouldEnableSSAI.getValue().booleanValue();
    }

    public boolean isSSAIEnabledForDownloads() {
        MobileWeblab mobileWeblab = PlaybackWeblabs.getPlaybackWeblabs().get("AIVPLAYERS_SSAI_SINGLE_PERIOD_DOWNLOADS_380281");
        return (this.mShouldEnableSSAIForDownloads.getValue().booleanValue() && (mobileWeblab != null && mobileWeblab.getCurrentTreatment().equals(WeblabTreatment.T1))) || isSSAIDownloadsEnabledInBeta();
    }

    public boolean shouldEnableFragmentDownloaderForSSAIDownloads() {
        return this.mShouldEnableFragmentDownloaderForSSAIDownloads.getValue().booleanValue() && isSSAIEnabledForDownloads();
    }

    private boolean isSSAIDownloadsEnabledInBeta() {
        return this.mShouldEnableSSAIForDownloadsInBeta.getValue().booleanValue() && BetaConfigProvider.getInstance().provideBetaConfig().isBeta();
    }

    public long getSeekOverAdBreakStartOffsetInMs() {
        if (MediaSystemSharedDependencies.getInstance().isSDKPlayer()) {
            return 0L;
        }
        return this.mSeekOverAdBreakStartOffsetInMs.getValue().longValue();
    }

    public long getSeekOverAdBreakEndOffsetInMs() {
        if (MediaSystemSharedDependencies.getInstance().isSDKPlayer()) {
            return 0L;
        }
        return this.mSeekOverAdBreakEndOffsetInMs.getValue().longValue();
    }

    public boolean shouldReportPlaybackStartEventDuringAds() {
        return this.mShouldReportPlaybackStartEventDuringAds.getValue().booleanValue();
    }

    public boolean isAdsAloysiusStateTransitionEnabled() {
        return this.mIsAdsAloysiusStateTransitionEnabled.getValue().booleanValue();
    }

    public boolean shouldShutdownExecutor() {
        return this.mShouldShutdownExecutor.getValue().booleanValue();
    }

    public boolean shouldDeinitOnPlaybackStop() {
        return this.mShouldDeinitOnPlaybackStop.getValue().booleanValue();
    }

    public String getDeviceAdInsertionTypeOverride() {
        return this.mDeviceAdInsertionTypeOverride.getValue();
    }

    public boolean shouldReportTEVSEmptyAvailsResponse() {
        return this.mShouldReportTEVSEmptyAvailsResponse.getValue().booleanValue();
    }

    @Nonnull
    public Set<String> getSafeListedTitleIdsForTEVSReporting() {
        return this.mSafeListedTitleIDsForTEVSReporting.getValue();
    }

    @Nonnull
    public String getVASTManifestTimelineType() {
        return this.mVASTManifestTimelineType.getValue();
    }

    @Nonnull
    public Long getTimelineManagerInitializationTimeoutInMilliseconds() {
        return this.mTimelineManagerInitializationTimeoutInMilliseconds.getValue();
    }

    public boolean shouldEnableDraperForWatchFromBeginning() {
        return this.mShouldEnableDraperForWatchFromBeginning.getValue().booleanValue();
    }

    public long getStrictModeTrustIntervalForBeaconReportingInMillis() {
        return this.mStrictModeTrustIntervalForBeaconReportingInMillis.getValue().longValue();
    }

    public boolean shouldEnableAdPlanTimeOutInDAGSSAI() {
        return this.mShouldEnableAdPlanTimeOutInDAGSSAI.getValue().booleanValue();
    }

    public boolean shouldSuppressAdsForDownloads() {
        return this.mSuppressAdsForDownloads.getValue().booleanValue();
    }
}
