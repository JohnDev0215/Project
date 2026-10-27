package com.amazon.avod.content.config;

import amazon.android.config.ConfigRegistry;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import amazon.android.config.internal.JacksonJsonConfigurationValue;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicAlgorithm;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.smoothstream.FragmentValidationMode;
import com.amazon.avod.content.smoothstream.StartAudioBitrateSelectionMode;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.streamstate.retentionpolicy.LiveRetentionPolicy;
import com.amazon.avod.content.urlvending.FailoverMap;
import com.amazon.avod.experiments.MobileWeblab;
import com.amazon.avod.experiments.PlaybackWeblabs;
import com.amazon.avod.experiments.WeblabTreatment;
import com.amazon.avod.media.AudioStreamType;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.downloadservice.PriorityTier;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.PlaybackConfigKeys;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.media.framework.config.WhitelistBlacklistAvailabilityProfileConfig;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyConfigurationProfile;
import com.amazon.avod.media.framework.config.profiles.parsers.ProfileConfigParser;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.android.gms.cast.CastStatusCodes;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamingPlaybackConfig extends MediaConfigBase {
    public static final SmoothStreamingPlaybackConfig INSTANCE = new SmoothStreamingPlaybackConfig();
    private final ConfigurationValue<Boolean> mAddFragmentUrlToSampleHolder;
    private final ConfigurationValue<Boolean> mAllowListingLanguagesAcrossAudioFormats;
    private final ConfigurationValue<String> mAudioAdaptationSetSelectionMode;
    private final ConfigurationValue<Boolean> mAudioAdaptationSetSwitchingEnabled;
    private final ConfigurationValue<Integer> mAudioFragmentOverheadBytes;
    private final ConfigurationValue<Integer> mBandwidthAverageCount;
    private final TimeConfigurationValue mCacheDurationForWhisperCache;
    private final TimeConfigurationValue mCacheRequiredForResume;
    private final TimeConfigurationValue mCacheRequiredForStreamingPlayback;
    private final TimeConfigurationValue mCacheRequiredForStreamingPlaybackForLive;
    private final TimeConfigurationValue mCacheRequiredForStreamingPlaybackForLiveWithDynamicAds;
    private final ConfigurationValue<Integer> mConcurrentDownloadTaskNumberOfRequests;
    private final ConfigurationValue<Boolean> mConsumeExactAudioFragment;
    private final ConfigurationValue<Boolean> mContentDebugInfoEnabled;
    private final ConfigurationValue<Boolean> mCorrectAudioStreamBoundaries;
    private final TimeConfigurationValue mDashAudioVideoDownloadSynchronizationThreshold;
    private final TimeConfigurationValue mDashFixedBitrateDownloadTimeout;
    private final ConfigurationValue<Integer> mDashParserFlags;
    private final ConfigurationValue<String> mDefaultAudioLanguageMultiPeriod;
    private final ConfigurationValue<String> mDefaultAudioStream;
    private final ConfigurationValue<Boolean> mDeleteBaselineAfterDownloadingUpgradeInLiveStreaming;
    private final ConfigurationValue<Boolean> mDetailedContentDebugInfoEnabled;
    private final ConfigurationValue<Integer> mDiskSpaceOverheadMegs;
    private final TimeConfigurationValue mDownloadFixedFragmentTimeout;
    private final TimeConfigurationValue mDownloadRetryDelayBackoff;
    private final ConfigurationValue<FragmentValidationMode> mDownloadValidationMode;
    private final ConfigurationValue<Boolean> mEnableFragmentBasedAudioStreamSelectionForProgressivePlayback;
    private final ConfigurationValue<Boolean> mEncodedDashManifest;
    private final JacksonJsonConfigurationValue<FailoverMap> mFailoverMap;
    private final ConfigurationValue<Integer> mFixedStaticAudioBitrate;
    private final ConfigurationValue<Boolean> mForceSnipOverlappedFragments;
    private final TimeConfigurationValue mFragmentGapOrOverlapReportingThresholdMillis;
    private final TimeConfigurationValue mFragmentGapOrOverlapThresholdMillis;
    private final ConfigurationValue<Boolean> mFragmentRepairEnabledOnWAN;
    private final ConfigurationValue<Integer> mHeapAllocatorFlags;
    private final ConfigurationValue<Integer> mHeapAllocatorFlagsT1;
    private final ConfigurationValue<Integer> mHeapAllocatorFlagsT2;
    private final ConfigurationValue<Integer> mHeapAllocatorFlagsT3;
    private final ConfigurationValue<Boolean> mHeapAllocatorWeblabEnabled;
    private final ConfigurationValue<Integer> mHeapFragmentationHeadroomInPercents;
    private final ConfigurationValue<Integer> mHeapSpillRatioInPercents;
    private final ConfigurationValue<Integer> mHeapSpillRatioInPercentsT1;
    private final ConfigurationValue<Integer> mHeapSpillRatioInPercentsT2;
    private final ConfigurationValue<Integer> mHeapSpillRatioInPercentsT3;
    private final ConfigurationValue<HeuristicAlgorithm> mHeuristicAlgorithmLive;
    private final ConfigurationValue<HeuristicAlgorithm> mHeuristicAlgorithmVOD;
    private final ConfigurationValue<Boolean> mHeuristicsDownloadControlsEnabled;
    private final ConfigurationValue<Integer> mInMemoryDriveSizeInMb;
    private final ConfigurationValue<Integer> mInitialBandwidthEstimateKbps;
    private final ConfigurationValue<Boolean> mIsAdAudioFragmentRoundingFixEnabled;
    private final ConfigurationValue<Boolean> mIsBidirectionalAudioVideoSynchronizationEnabled;
    private final ConfigurationValue<Boolean> mIsFailoverManagerBasedCDNSwitchingEnabled;
    private final ConfigurationValue<Boolean> mIsFailoverManagerBasedCDNSwitchingEnabledTNF;
    private final ConfigurationValue<Boolean> mIsFilebackedContentStoreMapNeeded;
    private final ConfigurationValue<Boolean> mIsHttpsFallbackAllowed;
    private final ConfigurationValue<Boolean> mIsInMemoryDriveEnabled;
    private final ConfigurationValue<Boolean> mIsLiveRetentionPolicyConfigEnabled;
    private final ConfigurationValue<Boolean> mIsLiveRetentionPolicyDebugLogEnabled;
    private final ConfigurationValue<Boolean> mIsManifestRetryWithExponentialBackoffAllowed;
    private final ConfigurationValue<Boolean> mIsManifestUrlOverrideEnabled;
    private final ConfigurationValue<Boolean> mIsMemoryBackedContentStoreEnabled;
    private final ConfigurationValue<Boolean> mIsOffsetForFragmentGapOrOverlapEnabled;
    private final ConfigurationValue<Boolean> mIsPlayerRestartForManifestDownloadFailureAllowed;
    private final TimeConfigurationValue mLastResumablePositionFromEndOfContent;
    private final TimeConfigurationValue mLiveCacheBufferLookAhead;
    private final TimeConfigurationValue mLiveCacheBufferLookBehind;
    private final ConfigurationValue<PriorityTier> mLiveCacheRequestPriorityTier;
    private final TimeConfigurationValue mLiveCacheTickerInterval;
    private final TimeConfigurationValue mLiveManifestDownloadTimeout;
    private final ConfigurationValue<Integer> mLiveManifestDownloadTries;
    private final ConfigurationValue<LiveRetentionPolicy> mLiveRetentionPolicy;
    private final TimeConfigurationValue mLogFragmentInfoAfterTime;
    private final ConfigurationValue<Boolean> mManifestDownloadAllowCompression;
    private final ConfigurationValue<Float> mManifestDownloadRetryBackoffGrowthFactor;
    private final TimeConfigurationValue mManifestDownloadRetryMaxBackoffMillis;
    private final TimeConfigurationValue mManifestDownloadRetryMinBackoffMillis;
    private final TimeConfigurationValue mManifestDownloadTimeout;
    private final ConfigurationValue<Integer> mManifestDownloadTries;
    private final ConfigurationValue<Integer> mManifestRefreshThreads;
    private final ConfigurationValue<Boolean> mManifestResponseCacheEnabled;
    private final ConfigurationValue<String> mManifestUrlOverrideValue;
    private final ConfigurationValue<Boolean> mManifestValidationEnabled;
    private final ConfigurationValue<Integer> mMaxAuxiliaryConcurrentRequests;
    private final ConfigurationValue<Integer> mMaxAuxiliaryRequestsCancellation;
    private final ConfigurationValue<Integer> mMaxVideoBitrateWhenBufferEmpty;
    private final TimeConfigurationValue mMaximumRetryDelay;
    private final ConfigurationValue<Integer> mMaximumSampleSizeBytes;
    private final ConfigurationValue<Integer> mMemstoreReservedMemoryInMegaBytes;
    private final ConfigurationValue<Boolean> mMidstreamSwitchingEnabled;
    private final ConfigurationValue<Integer> mMinBandwidthToMaintainAuxiliaryDownloadKbps;
    private final ConfigurationValue<Integer> mMinBandwidthToStartAuxiliaryDownloadKbps;
    private final ConfigurationValue<Integer> mMinBufferFullnessToMaintainAuxiliaryDownloadSeconds;
    private final ConfigurationValue<Integer> mMinBufferFullnessToStartAuxiliaryDownloadSeconds;
    private final ConfigurationValue<Integer> mMinUpdatePeriodToEnableManifestRefreshThresholdMillis;
    private final ConfigurationValue<Boolean> mMultiPeriodManifestSupportForLegacyLiveStreamsEnabled;
    private final ConfigurationValue<Integer> mNativeMemoryAllocationMaxInMegabytes;
    private final ConfigurationValue<Integer> mNativeMemoryAllocationMinInMegabytes;
    private final ConfigurationValue<Boolean> mNativeMemoryAllocationReopenVramLibrary;
    private final ConfigurationValue<Integer> mNativeMemoryAllocationRetryFallbackInMegabytes;
    private final ConfigurationValue<Integer> mNumberOfRetriesBeforeEndingProgressiveDownload;
    private final TimeConfigurationValue mPlayheadCorrectionThreshold;
    private final TimeConfigurationValue mPsshFragmentDownloadTimeout;
    private final ConfigurationValue<Integer> mPsshFragmentDownloadTries;
    private final ConfigurationValue<Boolean> mPsshFragmentResponseCacheEnabled;
    private final ConfigurationValue<Boolean> mReportInvalidResolutionSwitch;
    private final ConfigurationValue<Integer> mReportingCadenceForFragmentDiagnostics;
    private final ConfigurationValue<Integer> mRequiredCacheSizeForProgressivePlayback;
    private final ConfigurationValue<Integer> mSaveStatusEveryXFragment;
    private final ConfigurationValue<Boolean> mShouldAllowCdnFragmentRedirects;
    private final ConfigurationValue<Boolean> mShouldCleanOutOfWindowFragmentsBeforeDownload;
    private final ConfigurationValue<Boolean> mShouldFatalOnNativeMemAllocFailures;
    private final ConfigurationValue<Boolean> mShouldFetchVodPsshFromInitFragment;
    private final ConfigurationValue<Boolean> mShouldGetDurationToCacheFromHeuristics;
    private final ConfigurationValue<Boolean> mShouldListenForPrimaryDownloadEvents;
    private final ConfigurationValue<Boolean> mShouldNotifyHeuristicsOnDownloaderStateChange;
    private final ConfigurationValue<Boolean> mShouldPersistCdnManifestRedirects;
    private final ConfigurationValue<Boolean> mShouldRequestSegmentListManifest;
    private final ConfigurationValue<Boolean> mShouldSelectLowerAudioBitrates;
    private final ConfigurationValue<Boolean> mShouldSupportSignedCompositionTimeOffset;
    private final ConfigurationValue<Boolean> mShouldUseHierarchicalBaseURLResolver;
    private final ConfigurationValue<Boolean> mShouldUseLegacyPerformanceMonitorLive;
    private final ConfigurationValue<Boolean> mShouldUseLegacyPerformanceMonitorVOD;
    private final ConfigurationValue<Boolean> mShouldUseManifestContentStore;
    private final ConfigurationValue<Boolean> mShouldUseNativeMemoryForManifest;
    private final ConfigurationValue<Boolean> mShouldUseURIBasedBaseURLResolution;
    private final ConfigurationValue<Boolean> mShouldValidateMinimalStreamCountPerPeriod;
    private final ConfigurationValue<Integer> mSingleFileReadyToWatchDeltaPercentage;
    private final ConfigurationValue<Integer> mSingleFragmentContentStoreConsumptionHeadPadding;
    private final ConfigurationValue<Boolean> mSkipAudioQualityChangeUpdates;
    private final ConfigurationValue<StartAudioBitrateSelectionMode> mStartAudioBitrateSelectionMode;
    private final TimeConfigurationValue mStopDashDownloaderTimeout;
    private final ConfigurationValue<Integer> mStreamingAudioMaxTriesBeforeRemovingTimeout;
    private final ConfigurationValue<Integer> mStreamingFutureBufferSizeSeconds;
    private final TimeConfigurationValue mStreamingMaxFragmentTimeout;
    private final TimeConfigurationValue mStreamingMinFragmentTimeout;
    private final ConfigurationValue<Integer> mStreamingPastBufferSizeSeconds;
    private final TimeConfigurationValue mStreamingRetryDelayBackoff;
    private final ConfigurationValue<Float> mStreamingTimeoutScalingFactor;
    private final ConfigurationValue<Integer> mSubtitleFragmentOverheadBytes;
    private final TimeConfigurationValue mSubtitleLeadTime;
    private final ConfigurationValue<Boolean> mUseDashFragmentParserForSmoothStreams;
    private final ConfigurationValue<Boolean> mUseDynamicFrontBufferForLiveStreaming;
    private final ConfigurationValue<Boolean> mUseLastBandwidthForAccessPointOnResume;
    private final ConfigurationValue<Boolean> mUseMultiPeriodForSegmentTemplateBasedManifest;
    private final ConfigurationValue<Boolean> mUseOptimizedCleaningOutOfWindowFragments;
    private final ConfigurationValue<Boolean> mValidateProtectionHeaderBeforeUsing;
    private final ConfigurationValue<Integer> mVideoFragmentOverheadBytes;

    private SmoothStreamingPlaybackConfig() {
        TimeSpan timeSpanFromSeconds = TimeSpan.fromSeconds(8.0d);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.mCacheRequiredForResume = newTimeConfigurationValue("rtw_minimumCacheRequiredForResumeInSec", timeSpanFromSeconds, timeUnit);
        this.mCacheRequiredForStreamingPlayback = newTimeConfigurationValue("rtw_minimumCacheRequiredForPlaybackInSec", TimeSpan.fromSeconds(6.0d), timeUnit);
        this.mCacheRequiredForStreamingPlaybackForLiveWithDynamicAds = newTimeConfigurationValue("rtw_minimumCacheRequiredForPlaybackInSecAdInserted", TimeSpan.fromSeconds(6.0d), timeUnit);
        this.mCacheRequiredForStreamingPlaybackForLive = newTimeConfigurationValue("rtw_minimumCacheRequiredForPlaybackInSecLive", TimeSpan.fromSeconds(6.0d), timeUnit);
        this.mMaxVideoBitrateWhenBufferEmpty = newIntConfigValue("heuristicsOverride_maxVideoBitrateWhenBufferEmpty", 700000);
        this.mMaximumSampleSizeBytes = newIntConfigValue("streaming_MaximumSampleSizeBytes", (int) DataUnit.MEGABYTES.toBytes(8.0f));
        this.mDefaultAudioStream = newStringConfigValue("streaming_DefaultAudioStream", AudioStreamType.AACL.getFourCC());
        this.mAllowListingLanguagesAcrossAudioFormats = newBooleanConfigValue("streaming_allowListingLanguagesAcrossAudioFormats", true);
        this.mUseLastBandwidthForAccessPointOnResume = newBooleanConfigValue("streaming_UseLastBandwidthForAccessPoint", true);
        this.mLastResumablePositionFromEndOfContent = newTimeConfigurationValue("player_lastResumablePositionFromEndOfContent", TimeSpan.fromSeconds(15.0d), timeUnit);
        this.mInMemoryDriveSizeInMb = newIntConfigValue("player_InMemoryDriveSizeInMb", 400);
        this.mStreamingFutureBufferSizeSeconds = newIntConfigValue("playback_streamingLookAheadBufferSizeInSeconds_3", 180);
        this.mStreamingPastBufferSizeSeconds = newIntConfigValue("playback_streamingLookBehindBufferSizeInSeconds", 60);
        this.mVideoFragmentOverheadBytes = newIntConfigValue("playback_videoFragmentOverhead", CastStatusCodes.ERROR_SERVICE_CREATION_FAILED);
        this.mAudioFragmentOverheadBytes = newIntConfigValue("playback_audioFragmentOverhead", 1650);
        this.mSubtitleFragmentOverheadBytes = newIntConfigValue("playback_subtitleFragmentOverhead", 2000);
        this.mBandwidthAverageCount = newIntConfigValue("playback_bandwidthAverageCount", 6);
        this.mSaveStatusEveryXFragment = newIntConfigValue("playback_saveStatsInterval", 10);
        this.mRequiredCacheSizeForProgressivePlayback = newIntConfigValue("playback_requiredCacheSizeForPlaybackInSec", 10);
        this.mDiskSpaceOverheadMegs = newIntConfigValue("playback_diskSpaceOverheadMegs", 50);
        this.mConcurrentDownloadTaskNumberOfRequests = newIntConfigValue("download_concurrentDownloadTaskNumberOfRequests", 2);
        this.mNumberOfRetriesBeforeEndingProgressiveDownload = newIntConfigValue("download_numberOfRetriesBeforeFatalError", 30);
        this.mInitialBandwidthEstimateKbps = newIntConfigValue("playback_initialBandwidthEstimateKbps", 500);
        this.mDownloadValidationMode = newEnumConfigValue("playback_downloadValidationMode", FragmentValidationMode.ITERATE_SAMPLES, FragmentValidationMode.class);
        TimeSpan timeSpan = TimeSpan.MAX_VALUE;
        TimeUnit timeUnit2 = TimeUnit.MILLISECONDS;
        this.mDownloadFixedFragmentTimeout = newTimeConfigurationValue("playback_downloadSlowFragmentDownloadTimeoutMilliseconds", timeSpan, timeUnit2);
        this.mStreamingMinFragmentTimeout = newTimeConfigurationValue("playback_streamingSlowFragmentDownloadTimeoutMilliseconds", TimeSpan.fromSeconds(4.0d), timeUnit2);
        this.mStreamingMaxFragmentTimeout = newTimeConfigurationValue("playback_streamingMaxFragmentTimeoutMilliseconds", TimeSpan.fromSeconds(30.0d), timeUnit2);
        this.mStreamingTimeoutScalingFactor = newFloatConfigValue("playback_streamingTimeoutScalingFactor", 0.15f);
        this.mStreamingAudioMaxTriesBeforeRemovingTimeout = newIntConfigValue("playback_fragmentDownloadTriesBeforeRemovingTimeout", 5);
        this.mLogFragmentInfoAfterTime = newTimeConfigurationValue("playback_logFragmentAtDownloadTimeSeconds", TimeSpan.fromSeconds(60.0d), timeUnit);
        this.mStreamingRetryDelayBackoff = newTimeConfigurationValue("playback_retryDelayBackoffStreamingMilliseconds", TimeSpan.fromMilliseconds(100L), timeUnit2);
        this.mDownloadRetryDelayBackoff = newTimeConfigurationValue("playback_retryDelayBackoffDownloadMilliseconds", TimeSpan.fromMilliseconds(1000L), timeUnit2);
        this.mMaximumRetryDelay = newTimeConfigurationValue("playback_maximumRetryDelaySeconds", TimeSpan.fromSeconds(30.0d), timeUnit);
        this.mPlayheadCorrectionThreshold = newTimeConfigurationValue("playback_playheadCorrectionThresholdMillis", TimeSpan.fromMilliseconds(100L), timeUnit2);
        this.mIsFilebackedContentStoreMapNeeded = newBooleanConfigValue("playback_isFilebackedContentStoremMapNeeded", true);
        this.mIsInMemoryDriveEnabled = newBooleanConfigValue("player_IsInMemoryDriveEnabled_3", false);
        this.mUseDashFragmentParserForSmoothStreams = newBooleanConfigValue("playback_useDashFragmentParserForSmoothStreams", false);
        this.mIsMemoryBackedContentStoreEnabled = newBooleanConfigValue("playback_isMemoryBackedContentStoreEnabled", true);
        this.mNativeMemoryAllocationMaxInMegabytes = new WhitelistBlacklistAvailabilityProfileConfig(PlaybackConfigKeys.PLAYBACK_NATIVE_MEMORY_MAX_ALLOCATION, ThirdPartyConfigurationProfile.getInstance(), "HD_HEVC:400;HD:400;HDR:400;UHD_HDR:400", 0, ProfileConfigParser.IntProfileResultParser.getResultParser(), false).asConfigValue();
        this.mNativeMemoryAllocationMinInMegabytes = new WhitelistBlacklistAvailabilityProfileConfig(PlaybackConfigKeys.PLAYBACK_NATIVE_MEMORY_MIN_ALLOCATION, ThirdPartyConfigurationProfile.getInstance(), "HD_HEVC:360;HD:360;HDR:360;UHD_HDR:360", 0, ProfileConfigParser.IntProfileResultParser.getResultParser(), false).asConfigValue();
        this.mNativeMemoryAllocationRetryFallbackInMegabytes = newIntConfigValue("playback_nativeMemoryAllocationRetryFallbackInMegabytes_3", 10);
        this.mNativeMemoryAllocationReopenVramLibrary = newBooleanConfigValue("playback_nativeMemoryAllocationReopenVramLibrary", false);
        this.mHeapAllocatorFlags = newIntConfigValue("playback_heapAllocatorFlags_3", 1);
        this.mHeapSpillRatioInPercents = newIntConfigValue("playback_heapSpillRatioInPercents_3", 20);
        this.mHeapFragmentationHeadroomInPercents = newIntConfigValue("playback_heapFragmentationHeadroomInPercents", 6);
        this.mShouldFatalOnNativeMemAllocFailures = newBooleanConfigValue("playback_shouldFatalOnNativeMemAllocFailures", false);
        this.mManifestDownloadTries = newIntConfigValue("playback_manifestDownloadTries", 6);
        this.mLiveManifestDownloadTries = newIntConfigValue("playback_liveManifestDownloadTries", 30);
        this.mManifestDownloadTimeout = newTimeConfigurationValue("playback_manifestDownloadTimeoutMillis", TimeSpan.fromMilliseconds(10000L), timeUnit2);
        this.mLiveManifestDownloadTimeout = newTimeConfigurationValue("playback_LivemanifestDownloadTimeoutMillis", TimeSpan.fromMilliseconds(10000L), timeUnit2);
        this.mManifestDownloadAllowCompression = newBooleanConfigValue("playback_manifestDownloadAllowCompression", true);
        this.mManifestResponseCacheEnabled = newBooleanConfigValue("playback_manifestResponseCacheEnabled", true);
        this.mIsManifestRetryWithExponentialBackoffAllowed = newBooleanConfigValue("playback_isManifestRetryWithExponentialBackoffAllowed", true);
        this.mManifestDownloadRetryMinBackoffMillis = newTimeConfigurationValue("playback_manifestDownloadRetryMinBackoffMillis", TimeSpan.fromMilliseconds(100L), timeUnit2);
        this.mManifestDownloadRetryMaxBackoffMillis = newTimeConfigurationValue("playback_manifestDownloadRetryMaxBackoffMillis", TimeSpan.fromMilliseconds(3000L), timeUnit2);
        this.mManifestDownloadRetryBackoffGrowthFactor = newFloatConfigValue("playback_manifestDownloadRetryBackoffGrowthFactor", 2.0f);
        this.mIsPlayerRestartForManifestDownloadFailureAllowed = newBooleanConfigValue("playback_isPlayerRestartForManifestDownloadFailureAllowed", true);
        this.mPsshFragmentDownloadTries = newIntConfigValue("playback_psshFragmentDownloadTries", 6);
        this.mPsshFragmentDownloadTimeout = newTimeConfigurationValue("playback_psshFragmentDownloadTimeoutMillis", timeSpan, timeUnit2);
        this.mPsshFragmentResponseCacheEnabled = newBooleanConfigValue("playback_psshFragmentResponseCacheEnabled", true);
        this.mDashParserFlags = newIntConfigValue("playback_dashParserFlags", 0);
        this.mShouldSupportSignedCompositionTimeOffset = newBooleanConfigValue("playback_shouldSupportSignedCompositionTimeOffset", false);
        this.mFragmentRepairEnabledOnWAN = newBooleanConfigValue("playback_fragmentRepairEnabledOnWAN", true);
        this.mIsHttpsFallbackAllowed = newBooleanConfigValue("playback_isHttpsFallbackAllowed", true);
        this.mSubtitleLeadTime = newTimeConfigurationValue("live_subtitleLeadTime", TimeSpan.fromMilliseconds(500L), timeUnit2);
        this.mEncodedDashManifest = newBooleanConfigValue("playback_requestEncodedDashManifest", false);
        this.mReportInvalidResolutionSwitch = newBooleanConfigValue("qos_ReportInvalidResolutionSwitch", true);
        this.mDashAudioVideoDownloadSynchronizationThreshold = newTimeConfigurationValue("playback_dashAudioVideoDownloadSynchronizationThreshold", TimeSpan.fromSeconds(10.0d), timeUnit);
        this.mIsBidirectionalAudioVideoSynchronizationEnabled = newBooleanConfigValue("playback_isBidirectionalAudioVideoSynchronizationEnabled", false);
        this.mDashFixedBitrateDownloadTimeout = newTimeConfigurationValue("playback_dashFixedBitrateDownloadTimeout", TimeSpan.fromSeconds(120.0d), timeUnit);
        this.mStopDashDownloaderTimeout = newTimeConfigurationValue("playback_stopDashDownloaderTimeout", TimeSpan.fromMilliseconds(300L), timeUnit2);
        this.mShouldSelectLowerAudioBitrates = newBooleanConfigValue("playback_useLowerAudioBitrates", false);
        this.mStartAudioBitrateSelectionMode = newEnumConfigValue(PlaybackConfigKeys.AUDIO_START_BITRATE_SELECTION_MODE, StartAudioBitrateSelectionMode.AUDIO_FORMAT_BASED, StartAudioBitrateSelectionMode.class);
        this.mFixedStaticAudioBitrate = newIntConfigValue(PlaybackConfigKeys.FIXED_STATIC_AUDIO_START_BITRATE, 0);
        this.mMemstoreReservedMemoryInMegaBytes = newIntConfigValue("playback_memstoreReservedMemoryInMegaBytes", 10);
        this.mAudioAdaptationSetSwitchingEnabled = newBooleanConfigValue("playback_audioAdaptationSetSwitchingEnabled", true);
        this.mHeuristicsDownloadControlsEnabled = newBooleanConfigValue("playback_heuristicsDownloadControlsEnabled", true);
        this.mMaxAuxiliaryConcurrentRequests = newIntConfigValue("auxiliaryDownload_maxAuxiliaryConcurrentRequests", 1);
        this.mMaxAuxiliaryRequestsCancellation = newIntConfigValue("auxiliaryDownload_maxAuxiliaryRequestsCancellation", 3);
        this.mMinBandwidthToStartAuxiliaryDownloadKbps = newIntConfigValue("auxiliaryDownload_minBandwidthToStartAuxiliaryDownloadKbps", 800);
        this.mMinBandwidthToMaintainAuxiliaryDownloadKbps = newIntConfigValue("auxiliaryDownload_minBandwidthToMaintainAuxiliaryDownloadKbps", 0);
        this.mMinBufferFullnessToStartAuxiliaryDownloadSeconds = newIntConfigValue("auxiliaryDownload_minBufferFullnessToStartAuxiliaryDownloadSeconds", 40);
        this.mMinBufferFullnessToMaintainAuxiliaryDownloadSeconds = newIntConfigValue("auxiliaryDownload_minBufferFullnessToMaintainAuxiliaryDownloadSeconds", 20);
        this.mAudioAdaptationSetSelectionMode = newStringConfigValue("audioSelection_audioAdaptationSetSelectionMode", "LEGACY");
        this.mUseDynamicFrontBufferForLiveStreaming = newBooleanConfigValue("playback_useDynamicFrontBufferForLiveStreaming", true);
        this.mIsLiveRetentionPolicyConfigEnabled = newBooleanConfigValue("playback_isLiveRetentionPolicyConfigEnabled_1", false);
        this.mLiveRetentionPolicy = newEnumConfigValue("playback_liveRetentionPolicy", LiveRetentionPolicy.ASCENDING_LIVE_EDGE_SLIDING_WINDOW, LiveRetentionPolicy.class);
        this.mIsLiveRetentionPolicyDebugLogEnabled = newBooleanConfigValue("playback_isLiveRetentionPolicyDebugLogEnabled", true);
        this.mDeleteBaselineAfterDownloadingUpgradeInLiveStreaming = newBooleanConfigValue("live_deleteBaselineAfterDownloadingUpgradeInLiveStreaming", true);
        this.mSingleFragmentContentStoreConsumptionHeadPadding = newIntConfigValue("live_singleFragmentContentStoreConsumptionHeadPadding", 1);
        this.mContentDebugInfoEnabled = newBooleanConfigValue("playback_contentDebugInfoEnabled", true);
        this.mDetailedContentDebugInfoEnabled = newBooleanConfigValue("playback_detailedContentDebugInfoEnabled", false);
        this.mEnableFragmentBasedAudioStreamSelectionForProgressivePlayback = newBooleanConfigValue("playback_enableFragmentBasedAudioStreamSelectionForProgressivePlayback", true);
        this.mManifestValidationEnabled = newBooleanConfigValue("playback_manifestValidationEnabled", true);
        this.mConsumeExactAudioFragment = newBooleanConfigValue("playback_consumeExactAudioFragment", true);
        this.mMultiPeriodManifestSupportForLegacyLiveStreamsEnabled = newBooleanConfigValue("playback_periodicManifestRefreshForLegacyLiveStreamsEnabled", false);
        this.mMinUpdatePeriodToEnableManifestRefreshThresholdMillis = newIntConfigValue("playback_minUpdatePeriodToEnableManifestRefreshThresholdMillis", 20000);
        this.mIsFailoverManagerBasedCDNSwitchingEnabled = newBooleanConfigValue("playback_isFailoverManagerBasedCDNSwitchingEnabled_2", false);
        this.mIsOffsetForFragmentGapOrOverlapEnabled = newBooleanConfigValue("playback_isOffsetForFragmentGapOrOverlapEnabled", true);
        this.mFragmentGapOrOverlapThresholdMillis = newTimeConfigurationValue("playback_fragmentGapOrOverlapThresholdMillis", TimeSpan.fromMilliseconds(1L), timeUnit2);
        this.mFragmentGapOrOverlapReportingThresholdMillis = newTimeConfigurationValue("playback_fragmentGapOrOverlapReportingThresholdMillis", TimeSpan.fromMilliseconds(500L), timeUnit2);
        this.mIsAdAudioFragmentRoundingFixEnabled = newBooleanConfigValue("playback_isAdAudioFragmentRoundingFixEnabled", false);
        this.mForceSnipOverlappedFragments = newBooleanConfigValue("playback_forceSnipOverlappedFragments", true);
        this.mAddFragmentUrlToSampleHolder = newBooleanConfigValue("playback_addFragmentUrlToSampleHolder", false);
        this.mIsFailoverManagerBasedCDNSwitchingEnabledTNF = newBooleanConfigValue("playback_isFailoverManagerBasedCDNSwitchingEnabled_TNF", true);
        FailoverMap defaultInstance = FailoverMap.getDefaultInstance();
        ConfigRegistry configRegistry = ConfigRegistry.getInstance();
        ConfigType configType = ConfigType.SERVER;
        this.mFailoverMap = new JacksonJsonConfigurationValue<>(PlaybackConfigKeys.FAILOVER_MAPPING, defaultInstance, FailoverMap.class, configRegistry.getConfigEditor(configType), new FailoverMap.Parser());
        this.mSingleFileReadyToWatchDeltaPercentage = newIntConfigValue("playback_singleFileReadyToWatchDeltaPercentage", 0);
        this.mSkipAudioQualityChangeUpdates = newBooleanConfigValue("playback_skipAudioQualityChangeUpdates", false);
        this.mManifestRefreshThreads = newIntConfigValue("playback.contentsession.manifestRefreshThreads", 2);
        this.mValidateProtectionHeaderBeforeUsing = newBooleanConfigValue("playback_validateProtectionHeaderBeforeUsing", true);
        this.mShouldFetchVodPsshFromInitFragment = newBooleanConfigValue("streaming_shouldFetchVodPsshFromInitFragment", false);
        this.mShouldUseLegacyPerformanceMonitorVOD = newBooleanConfigValue("playback_useLegacyPerformanceMonitorVOD", true);
        this.mShouldUseLegacyPerformanceMonitorLive = newBooleanConfigValue("playback_useLegacyPerformanceMonitorLive", false);
        this.mShouldUseManifestContentStore = newBooleanConfigValue("playback_useManifestContentStore", true);
        this.mUseMultiPeriodForSegmentTemplateBasedManifest = newBooleanConfigValue("playback_useMultiPeriodForSegmentTemplateBasedManifest", true);
        this.mDefaultAudioLanguageMultiPeriod = newStringConfigValue("default_audioLanguageForMultiperiod", "eng");
        this.mShouldValidateMinimalStreamCountPerPeriod = newBooleanConfigValue("playback_shouldValidateMinimalStreamCountPerPeriod", true);
        this.mCorrectAudioStreamBoundaries = newBooleanConfigValue("playback_correctAudioStreamBoundaries", true);
        this.mShouldUseNativeMemoryForManifest = newBooleanConfigValue("playback_shouldUseNativeMemoryForManifest", true);
        this.mShouldRequestSegmentListManifest = newBooleanConfigValue("playback_shouldRequestSegmentListManifest", false);
        this.mHeuristicAlgorithmVOD = newEnumConfigValue("playback_heuristicAlgorithmVOD", HeuristicAlgorithm.MAGNITUDE, HeuristicAlgorithm.class);
        this.mHeuristicAlgorithmLive = newEnumConfigValue("playback_heuristicAlgorithmLive", HeuristicAlgorithm.SHORT_BUFFER, HeuristicAlgorithm.class);
        this.mMidstreamSwitchingEnabled = newBooleanConfigValue("playback_midstreamSwitchingEnabled", false);
        this.mLiveCacheBufferLookAhead = newTimeConfigurationValue("liveCache_bufferLookAheadSeconds", TimeSpan.fromSeconds(10.0d), timeUnit);
        this.mLiveCacheBufferLookBehind = newTimeConfigurationValue("liveCache_bufferLookBehindSeconds", TimeSpan.fromSeconds(10.0d), timeUnit);
        this.mLiveCacheTickerInterval = newTimeConfigurationValue("liveCache_tickerIntervalMillis", TimeSpan.fromMilliseconds(100L), timeUnit2);
        this.mLiveCacheRequestPriorityTier = newEnumConfigValue("liveCache_requestPriorityTier", PriorityTier.SECONDARY, PriorityTier.class);
        this.mUseOptimizedCleaningOutOfWindowFragments = newBooleanConfigValue("playback_useOptimizedCleaningOutOfWindowFragments", true);
        this.mShouldCleanOutOfWindowFragmentsBeforeDownload = newBooleanConfigValue("playback_shouldCleanOutOfWindowFragmentsBeforeDownload", true);
        this.mShouldUseURIBasedBaseURLResolution = newBooleanConfigValue("playback_shouldUseURIBasedBaseURLResolution", true);
        this.mShouldUseHierarchicalBaseURLResolver = newBooleanConfigValue("playback_shouldUseHierarchicalBaseURLResolver", false);
        this.mShouldGetDurationToCacheFromHeuristics = newBooleanConfigValue("playback_shouldGetDurationToCacheFromHeuristics", true, configType);
        this.mCacheDurationForWhisperCache = newTimeConfigurationValue("cache_durationToCacheSeconds", TimeSpan.fromSeconds(8.0d), timeUnit);
        this.mReportingCadenceForFragmentDiagnostics = newIntConfigValue("playback_reportingCadenceForFragmentDiagnostics", 30);
        this.mShouldListenForPrimaryDownloadEvents = newBooleanConfigValue("playback_downloadAvailability_shouldListenForPrimaryDownloadEvents", true);
        this.mShouldNotifyHeuristicsOnDownloaderStateChange = newBooleanConfigValue("playback_shouldNotifyHeuristicsOnDownloaderStateChange", true);
        this.mHeapAllocatorWeblabEnabled = newBooleanConfigValue("playback_heapAllocatorWeblabEnabled", false);
        this.mHeapAllocatorFlagsT1 = newIntConfigValue("playback_HeapAllocatorFlags_T1", 1);
        this.mHeapAllocatorFlagsT2 = newIntConfigValue("playback_HeapAllocatorFlags_T2", 1);
        this.mHeapAllocatorFlagsT3 = newIntConfigValue("playback_HeapAllocatorFlags_T3", 1);
        this.mHeapSpillRatioInPercentsT1 = newIntConfigValue("playback_heapSpillRatioInPercents_T1", 20);
        this.mHeapSpillRatioInPercentsT2 = newIntConfigValue("playback_heapSpillRatioInPercents_T2", 20);
        this.mHeapSpillRatioInPercentsT3 = newIntConfigValue("playback_heapSpillRatioInPercents_T3", 20);
        this.mShouldPersistCdnManifestRedirects = newBooleanConfigValue("playback_shouldPersistCdnManifestRedirects", false);
        this.mShouldAllowCdnFragmentRedirects = newBooleanConfigValue("playback_shouldAllowCdnFragmentRedirects", true);
        this.mIsManifestUrlOverrideEnabled = newBooleanConfigValue("playback_isManifestUrlOverrideEnabled", false);
        this.mManifestUrlOverrideValue = newStringConfigValue("playback_manifestUrlOverrideValue", "https://midas.us-west-2.amazonaws.com/v1/dash/415052244193/deltaOrigin3/tnf_dash_stream_02.mpd");
    }

    @Nonnull
    public TimeSpan getRequiredCacheSizeForResume(@Nullable Heuristics heuristics) {
        if (heuristics == null || heuristics.getHeuristicsPlaybackConfig() == null) {
            return this.mCacheRequiredForResume.getValue();
        }
        return TimeSpan.fromSeconds(heuristics.getHeuristicsPlaybackConfig().getBufferFullnessForPlaybackResumeSeconds());
    }

    @Nonnull
    public TimeSpan getRequiredCacheSizeForStreamingPlayback(@Nullable Heuristics heuristics) {
        if (heuristics == null || heuristics.getHeuristicsPlaybackConfig() == null) {
            return getRequiredCacheSizeForStreamingPlayback();
        }
        return TimeSpan.fromSeconds(heuristics.getHeuristicsPlaybackConfig().getBufferFullnessForPlaybackStartSeconds());
    }

    @Nonnull
    public TimeSpan getRequiredCacheSizeForStreamingPlayback(@Nonnull ContentSessionType contentSessionType, @Nullable Heuristics heuristics, boolean z, boolean z2) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$avod$content$ContentSessionType[contentSessionType.ordinal()]) {
            case 1:
            case 2:
                return getRequiredCacheSizeForProgressivePlayback();
            case 3:
            case 4:
            case 5:
                return getRequiredCacheSizeForStreamingPlayback(heuristics, z, z2);
            case 6:
                return getRequiredCacheSizeForStreamingPlayback(heuristics);
            default:
                throw new IllegalArgumentException("Unrecognized sessionType!");
        }
    }

    @Nonnull
    public TimeSpan getRequiredCacheSizeForWhisperCache(@Nullable Heuristics heuristics) {
        if (heuristics == null || heuristics.getHeuristicsPlaybackConfig() == null) {
            return getRequiredCacheSizeForWhisperCache();
        }
        return TimeSpan.fromSeconds(heuristics.getHeuristicsPlaybackConfig().getBufferLengthForWhisperCacheSeconds());
    }

    @Nonnull
    public TimeSpan getRequiredCacheSizeForStreamingPlayback(@Nullable Heuristics heuristics, boolean z, boolean z2) {
        if (heuristics == null || heuristics.getHeuristicsPlaybackConfig() == null) {
            return getRequiredCacheSizeForStreamingPlayback(z, z2);
        }
        return TimeSpan.fromSeconds(heuristics.getHeuristicsPlaybackConfig().getBufferFullnessForPlaybackStartSeconds(z, z2));
    }

    TimeSpan getRequiredCacheSizeForStreamingPlayback() {
        return this.mCacheRequiredForStreamingPlayback.getValue();
    }

    @Nonnull
    public TimeSpan getRequiredCacheSizeForWhisperCache() {
        return this.mCacheDurationForWhisperCache.getValue();
    }

    @Nonnull
    public TimeSpan getRequiredCacheSizeForStreamingPlaybackForLiveWithDynamicAd() {
        return this.mCacheRequiredForStreamingPlaybackForLiveWithDynamicAds.getValue();
    }

    @Nonnull
    public TimeSpan getRequiredCacheSizeForStreamingPlaybackForLive() {
        return this.mCacheRequiredForStreamingPlaybackForLive.getValue();
    }

    public boolean getIsFilebackedContentStoreMapNeeded() {
        return this.mIsFilebackedContentStoreMapNeeded.getValue().booleanValue();
    }

    public boolean getUseDashFragmentParserForSmoothStream() {
        return this.mUseDashFragmentParserForSmoothStreams.getValue().booleanValue();
    }

    @Nonnull
    public TimeSpan getRequiredCacheSizeForStreamingPlayback(boolean z, boolean z2) {
        if (z) {
            return this.mCacheRequiredForStreamingPlaybackForLiveWithDynamicAds.getValue();
        }
        if (z2) {
            return this.mCacheRequiredForStreamingPlaybackForLive.getValue();
        }
        return this.mCacheRequiredForStreamingPlayback.getValue();
    }

    public int getMaxVideoBitrateWhenBufferEmpty() {
        return this.mMaxVideoBitrateWhenBufferEmpty.getValue().intValue();
    }

    public String getAudioStream() {
        return this.mDefaultAudioStream.getValue();
    }

    public boolean allowListingLanguagesAcrossAudioFormats() {
        return this.mAllowListingLanguagesAcrossAudioFormats.getValue().booleanValue();
    }

    public int getMaximumSampleSizeInBytes() {
        return this.mMaximumSampleSizeBytes.getValue().intValue();
    }

    public boolean isInMemoryDriveEnabled() {
        return this.mIsInMemoryDriveEnabled.getValue().booleanValue();
    }

    public int getInMemoryDriveSizeInMb() {
        return this.mInMemoryDriveSizeInMb.getValue().intValue();
    }

    public boolean useLastBandwidthForAccessPointOnResume() {
        return this.mUseLastBandwidthForAccessPointOnResume.getValue().booleanValue();
    }

    public TimeSpan getLastResumablePositionFromEndOfContent() {
        return this.mLastResumablePositionFromEndOfContent.getValue();
    }

    public boolean shouldUseURIBasedBaseURLResolution() {
        return this.mShouldUseURIBasedBaseURLResolution.getValue().booleanValue();
    }

    public boolean shouldUseHierarchicalBaseURLResolver() {
        return this.mShouldUseHierarchicalBaseURLResolver.getValue().booleanValue();
    }

    public int getStreamingFutureBufferSizeSeconds(@Nullable Heuristics heuristics) {
        if (heuristics == null || heuristics.getHeuristicsPlaybackConfig() == null) {
            return this.mStreamingFutureBufferSizeSeconds.getValue().intValue();
        }
        return heuristics.getHeuristicsPlaybackConfig().getForwardBufferLengthSeconds();
    }

    public int getStreamingPastBufferSizeSeconds(@Nullable Heuristics heuristics) {
        if (heuristics == null || heuristics.getHeuristicsPlaybackConfig() == null) {
            return this.mStreamingPastBufferSizeSeconds.getValue().intValue();
        }
        return heuristics.getHeuristicsPlaybackConfig().getBackwardBufferLengthSeconds();
    }

    public int getNumberOfManifestDownloadTries(boolean z) {
        return (z ? this.mLiveManifestDownloadTries : this.mManifestDownloadTries).getValue().intValue();
    }

    public int getFragmentOverheadBytes(StreamType streamType) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[streamType.ordinal()];
        if (i == 1) {
            return this.mSubtitleFragmentOverheadBytes.getValue().intValue();
        }
        if (i == 2) {
            return this.mAudioFragmentOverheadBytes.getValue().intValue();
        }
        if (i != 3) {
            return 0;
        }
        return this.mVideoFragmentOverheadBytes.getValue().intValue();
    }

    public int getNumberOfRetriesBeforeEndingProgressiveDownload() {
        return this.mNumberOfRetriesBeforeEndingProgressiveDownload.getValue().intValue();
    }

    public int getBandwidthAverageCount() {
        return this.mBandwidthAverageCount.getValue().intValue();
    }

    public int getSaveStatusInterval() {
        return this.mSaveStatusEveryXFragment.getValue().intValue();
    }

    public TimeSpan getRequiredCacheSizeForProgressivePlayback() {
        return TimeSpan.fromSeconds(this.mRequiredCacheSizeForProgressivePlayback.getValue().intValue());
    }

    public TimeSpan getLogFragmentInfoAfterTime() {
        return this.mLogFragmentInfoAfterTime.getValue();
    }

    public TimeSpan getStreamingMinFragmentTimeout() {
        return this.mStreamingMinFragmentTimeout.getValue();
    }

    public TimeSpan getStreamingMaxFragmentTimeout() {
        return this.mStreamingMaxFragmentTimeout.getValue();
    }

    public float getStreamingTimeoutScalingFactor() {
        return this.mStreamingTimeoutScalingFactor.getValue().floatValue();
    }

    public int getStreamingAudioMaxTriesBeforeRemovingTimeout() {
        return this.mStreamingAudioMaxTriesBeforeRemovingTimeout.getValue().intValue();
    }

    public TimeSpan getDownloadFixedFragmentTimeout() {
        return this.mDownloadFixedFragmentTimeout.getValue();
    }

    public int getInitalBandwidthEstimateKbps() {
        return this.mInitialBandwidthEstimateKbps.getValue().intValue();
    }

    public TimeSpan getRetryDelayBackoff(ContentSessionType contentSessionType) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$avod$content$ContentSessionType[contentSessionType.ordinal()]) {
            case 1:
            case 2:
            case 6:
                return this.mDownloadRetryDelayBackoff.getValue();
            case 3:
            case 4:
            case 5:
                return getStreamingRetryDelayBackoff();
            default:
                throw new IllegalStateException("Missing option for " + contentSessionType + " in delay backoff switch statement.");
        }
    }

    public TimeSpan getStreamingRetryDelayBackoff() {
        return this.mStreamingRetryDelayBackoff.getValue();
    }

    public TimeSpan getMaximumRetryDelay() {
        return this.mMaximumRetryDelay.getValue();
    }

    public FragmentValidationMode getDownloadValidationMode() {
        return this.mDownloadValidationMode.getValue();
    }

    public int getConcurrentDownloadTaskNumberOfRequests() {
        return this.mConcurrentDownloadTaskNumberOfRequests.getValue().intValue();
    }

    public int getDiskSpaceOverheadMegs() {
        return this.mDiskSpaceOverheadMegs.getValue().intValue();
    }

    public boolean isMemoryBackedContentStoreEnabled() {
        return this.mIsMemoryBackedContentStoreEnabled.getValue().booleanValue();
    }

    public int getNativeMemoryAllocationMaxInMegabytes() {
        return this.mNativeMemoryAllocationMaxInMegabytes.getValue().intValue();
    }

    public int getNativeMemoryAllocationMinInMegabytes() {
        return this.mNativeMemoryAllocationMinInMegabytes.getValue().intValue();
    }

    public int getNativeMemoryAllocationRetryFallbackInMegabytes() {
        return this.mNativeMemoryAllocationRetryFallbackInMegabytes.getValue().intValue();
    }

    public int getHeapAllocatorFlags() {
        MobileWeblab mobileWeblab;
        int iIntValue = this.mHeapAllocatorFlags.getValue().intValue();
        if (this.mHeapAllocatorWeblabEnabled.getValue().booleanValue() && (mobileWeblab = PlaybackWeblabs.getPlaybackWeblabs().get("AIVPLAYERS_327614")) != null) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$avod$experiments$WeblabTreatment[mobileWeblab.getCurrentTreatment().ordinal()];
            if (i == 1) {
                DLog.logf("using default heap flags %s as weblab is Control treatment", Integer.valueOf(iIntValue));
            } else {
                if (i == 2) {
                    int iIntValue2 = this.mHeapAllocatorFlagsT1.getValue().intValue();
                    DLog.logf("Using heap flags %s for weblab T1 treatment", Integer.valueOf(iIntValue2));
                    return iIntValue2;
                }
                if (i == 3) {
                    int iIntValue3 = this.mHeapAllocatorFlagsT2.getValue().intValue();
                    DLog.logf("Using heap flags %s for weblab T2 treatment", Integer.valueOf(iIntValue3));
                    return iIntValue3;
                }
                if (i == 4) {
                    int iIntValue4 = this.mHeapAllocatorFlagsT3.getValue().intValue();
                    DLog.logf("Using heap flags %s for weblab T3 treatment", Integer.valueOf(iIntValue4));
                    return iIntValue4;
                }
                DLog.warnf("Invalid treatment: %s", mobileWeblab.getCurrentTreatment());
            }
        }
        return iIntValue;
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.config.SmoothStreamingPlaybackConfig$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$ContentSessionType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$experiments$WeblabTreatment;

        static {
            int[] iArr = new int[WeblabTreatment.values().length];
            $SwitchMap$com$amazon$avod$experiments$WeblabTreatment = iArr;
            try {
                iArr[WeblabTreatment.C.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$experiments$WeblabTreatment[WeblabTreatment.T1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$experiments$WeblabTreatment[WeblabTreatment.T2.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$experiments$WeblabTreatment[WeblabTreatment.T3.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[StreamType.values().length];
            $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType = iArr2;
            try {
                iArr2[StreamType.SUBTITLES.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[StreamType.AUDIO.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[StreamType.VIDEO.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr3 = new int[ContentSessionType.values().length];
            $SwitchMap$com$amazon$avod$content$ContentSessionType = iArr3;
            try {
                iArr3[ContentSessionType.DOWNLOAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.PROGRESSIVE_PLAYBACK.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.LIVE_CACHE.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING_CONTINUOUS.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.CONTENT_CACHE.ordinal()] = 6;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    public int getHeapSpillRatioInPercents() {
        MobileWeblab mobileWeblab;
        int iIntValue = this.mHeapSpillRatioInPercents.getValue().intValue();
        if (this.mHeapAllocatorWeblabEnabled.getValue().booleanValue() && (mobileWeblab = PlaybackWeblabs.getPlaybackWeblabs().get("AIVPLAYERS_327614")) != null) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$avod$experiments$WeblabTreatment[mobileWeblab.getCurrentTreatment().ordinal()];
            if (i == 1) {
                DLog.logf("using default heap spill ratio %s as weblab is Control treatment", Integer.valueOf(iIntValue));
            } else {
                if (i == 2) {
                    int iIntValue2 = this.mHeapSpillRatioInPercentsT1.getValue().intValue();
                    DLog.logf("Using heap spill ratio %s for weblab T1 treatment", Integer.valueOf(iIntValue2));
                    return iIntValue2;
                }
                if (i == 3) {
                    int iIntValue3 = this.mHeapSpillRatioInPercentsT2.getValue().intValue();
                    DLog.logf("Using heap spill ratio %s for weblab T2 treatment", Integer.valueOf(iIntValue3));
                    return iIntValue3;
                }
                if (i == 4) {
                    int iIntValue4 = this.mHeapSpillRatioInPercentsT3.getValue().intValue();
                    DLog.logf("Using heap spill ratio %s for weblab T3 treatment", Integer.valueOf(iIntValue4));
                    return iIntValue4;
                }
                DLog.warnf("Invalid treatment: %s", mobileWeblab.getCurrentTreatment());
            }
        }
        return iIntValue;
    }

    public boolean getNativeMemoryAllocationReopenVramLibrary() {
        return this.mNativeMemoryAllocationReopenVramLibrary.getValue().booleanValue();
    }

    public boolean fragmentRepairEnabledOnWAN() {
        return this.mFragmentRepairEnabledOnWAN.getValue().booleanValue();
    }

    public TimeSpan getPlayheadCorrectionThreshold() {
        return this.mPlayheadCorrectionThreshold.getValue();
    }

    public TimeSpan getManifestDownloadTimeout() {
        return this.mManifestDownloadTimeout.getValue();
    }

    public TimeSpan getLiveManifestDownloadTimeout() {
        return this.mLiveManifestDownloadTimeout.getValue();
    }

    public boolean isManifestDownloadCompressionEnabled() {
        return this.mManifestDownloadAllowCompression.getValue().booleanValue();
    }

    public boolean isManifestResponseCacheEnabled() {
        return this.mManifestResponseCacheEnabled.getValue().booleanValue();
    }

    public boolean isManifestRetryWithExponentialBackoffAllowed() {
        return this.mIsManifestRetryWithExponentialBackoffAllowed.getValue().booleanValue();
    }

    @Nonnull
    public TimeSpan getManifestDownloadRetryMinBackoffMillis() {
        return this.mManifestDownloadRetryMinBackoffMillis.getValue();
    }

    @Nonnull
    public TimeSpan getManifestDownloadRetryMaxBackoffMillis() {
        return this.mManifestDownloadRetryMaxBackoffMillis.getValue();
    }

    @Nonnull
    public float getManifestDownloadRetryBackoffGrowthFactor() {
        return this.mManifestDownloadRetryBackoffGrowthFactor.getValue().floatValue();
    }

    public boolean isPlayerRestartForManifestDownloadFailureAllowed() {
        return this.mIsPlayerRestartForManifestDownloadFailureAllowed.getValue().booleanValue();
    }

    public int getNumberOfPsshFragmentDownloadTries() {
        return this.mPsshFragmentDownloadTries.getValue().intValue();
    }

    public TimeSpan getPsshFragmentDownloadTimeout() {
        return this.mPsshFragmentDownloadTimeout.getValue();
    }

    public boolean isPsshFragmentResponseCacheEnabled() {
        return this.mPsshFragmentResponseCacheEnabled.getValue().booleanValue();
    }

    public int getDashParserFlags() {
        return this.mDashParserFlags.getValue().intValue();
    }

    public boolean shouldSupportSignedCompositionTimeOffset() {
        return this.mShouldSupportSignedCompositionTimeOffset.getValue().booleanValue();
    }

    public boolean isHttpsFallbackAllowed() {
        return this.mIsHttpsFallbackAllowed.getValue().booleanValue();
    }

    public TimeSpan getSubtitleLeadTime() {
        return this.mSubtitleLeadTime.getValue();
    }

    public boolean requestEncodedDashManifest() {
        return this.mEncodedDashManifest.getValue().booleanValue();
    }

    public boolean shouldReportInvalidResolutionSwitch() {
        return this.mReportInvalidResolutionSwitch.getValue().booleanValue();
    }

    public TimeSpan getDashAudioVideoDownloadSynchronizationThreshold() {
        return this.mDashAudioVideoDownloadSynchronizationThreshold.getValue();
    }

    public boolean isBidirectionalAudioVideoSynchronizationEnabled() {
        return this.mIsBidirectionalAudioVideoSynchronizationEnabled.getValue().booleanValue();
    }

    public TimeSpan getDashFixedBitrateDownloadTimeout() {
        return this.mDashFixedBitrateDownloadTimeout.getValue();
    }

    public TimeSpan getStopDashDownloaderTimeout() {
        return this.mStopDashDownloaderTimeout.getValue();
    }

    public boolean shouldSelectLowerAudioBitrates() {
        return this.mShouldSelectLowerAudioBitrates.getValue().booleanValue();
    }

    public StartAudioBitrateSelectionMode getStartAudioBitrateSelectionMode() {
        return this.mStartAudioBitrateSelectionMode.getValue();
    }

    public int getFixedStaticAudioBitrate() {
        return this.mFixedStaticAudioBitrate.getValue().intValue();
    }

    public int getMemstoreReservedMemoryInMegaBytes() {
        return this.mMemstoreReservedMemoryInMegaBytes.getValue().intValue();
    }

    public boolean isAudioAdaptationSetSwitchingEnabled() {
        return this.mAudioAdaptationSetSwitchingEnabled.getValue().booleanValue();
    }

    public boolean getHeuristicsDownloadControlsEnabled() {
        return this.mHeuristicsDownloadControlsEnabled.getValue().booleanValue();
    }

    public int getMaxAuxiliaryConcurrentRequests() {
        return this.mMaxAuxiliaryConcurrentRequests.getValue().intValue();
    }

    public int getMaxAuxiliaryRequestsCancellation() {
        return this.mMaxAuxiliaryRequestsCancellation.getValue().intValue();
    }

    public int getMinBandwidthToStartAuxiliaryDownloadKbps() {
        return this.mMinBandwidthToStartAuxiliaryDownloadKbps.getValue().intValue();
    }

    public int getMinBufferFullnessToStartAuxiliaryDownloadSeconds() {
        return this.mMinBufferFullnessToStartAuxiliaryDownloadSeconds.getValue().intValue();
    }

    public int getMinBandwidthToMaintainAuxiliaryDownloadKbps() {
        return this.mMinBandwidthToMaintainAuxiliaryDownloadKbps.getValue().intValue();
    }

    public int getMinBufferFullnessToMaintainAuxiliaryDownloadSeconds() {
        return this.mMinBufferFullnessToMaintainAuxiliaryDownloadSeconds.getValue().intValue();
    }

    public String getAudioAdaptationSetSelectionMode() {
        return this.mAudioAdaptationSetSelectionMode.getValue();
    }

    public boolean useDynamicFrontBufferForLiveStreaming() {
        return this.mUseDynamicFrontBufferForLiveStreaming.getValue().booleanValue();
    }

    public boolean isLiveRetentionPolicyConfigEnabled() {
        return this.mIsLiveRetentionPolicyConfigEnabled.getValue().booleanValue();
    }

    @Nonnull
    public String getLiveRetentionPolicy() {
        return this.mLiveRetentionPolicy.getValue().name();
    }

    public boolean isLiveRetentionPolicyDebugLogEnabled() {
        return this.mIsLiveRetentionPolicyDebugLogEnabled.getValue().booleanValue();
    }

    public boolean deleteBaselineAfterDownloadingUpgradeInLiveStreaming() {
        return this.mDeleteBaselineAfterDownloadingUpgradeInLiveStreaming.getValue().booleanValue();
    }

    public int getSingleFragmentContentStoreConsumptionHeadPadding() {
        return this.mSingleFragmentContentStoreConsumptionHeadPadding.getValue().intValue();
    }

    public boolean isContentDebugInfoEnabled() {
        return this.mContentDebugInfoEnabled.getValue().booleanValue();
    }

    public boolean isDetailedContentDebugInfoEnabled() {
        return this.mDetailedContentDebugInfoEnabled.getValue().booleanValue();
    }

    public boolean enableFragmentBasedAudioStreamSelectionForProgressivePlayback() {
        return this.mEnableFragmentBasedAudioStreamSelectionForProgressivePlayback.getValue().booleanValue();
    }

    public boolean isManifestValidationEnabled() {
        return this.mManifestValidationEnabled.getValue().booleanValue();
    }

    public boolean getMultiPeriodManifestSupportForLegacyLiveStreamsEnabled() {
        return this.mMultiPeriodManifestSupportForLegacyLiveStreamsEnabled.getValue().booleanValue();
    }

    public int getMinUpdatePeriodToEnableManifestRefreshThresholdMillis() {
        return this.mMinUpdatePeriodToEnableManifestRefreshThresholdMillis.getValue().intValue();
    }

    public boolean shouldConsumeExactAudioFragment(boolean z) {
        return !z && this.mConsumeExactAudioFragment.getValue().booleanValue();
    }

    public int getHeapFragmentationHeadroomInPercents() {
        return this.mHeapFragmentationHeadroomInPercents.getValue().intValue();
    }

    public boolean shouldFatalOnNativeMemAllocFailures() {
        return this.mShouldFatalOnNativeMemAllocFailures.getValue().booleanValue();
    }

    public boolean isFailoverManagerBasedCDNSwitchingEnabled(@Nullable String str) {
        if (str == null) {
            return this.mIsFailoverManagerBasedCDNSwitchingEnabled.getValue().booleanValue();
        }
        return this.mIsFailoverManagerBasedCDNSwitchingEnabledTNF.getValue().booleanValue();
    }

    public boolean isOffsetForFragmentGapOrOverlapEnabled() {
        return this.mIsOffsetForFragmentGapOrOverlapEnabled.getValue().booleanValue();
    }

    @Nonnull
    public TimeSpan getFragmentGapOrOverlapThreshold() {
        return this.mFragmentGapOrOverlapThresholdMillis.getValue();
    }

    @Nonnull
    public TimeSpan getFragmentGapOrOverlapReportingThreshold() {
        return this.mFragmentGapOrOverlapReportingThresholdMillis.getValue();
    }

    public boolean isAdAudioFragmentRoundingFixEnabled() {
        return this.mIsAdAudioFragmentRoundingFixEnabled.getValue().booleanValue();
    }

    public boolean forceSnipOverlappedFragments() {
        return this.mForceSnipOverlappedFragments.getValue().booleanValue();
    }

    public boolean addFragmentUrlToSampleHolder() {
        return this.mAddFragmentUrlToSampleHolder.getValue().booleanValue();
    }

    @Nonnull
    public FailoverMap getFailoverMap() {
        return this.mFailoverMap.getValue();
    }

    public int getSingleFileReadyToWatchDeltaPercentage() {
        return this.mSingleFileReadyToWatchDeltaPercentage.getValue().intValue();
    }

    public Boolean shouldSkipAudioQualityChangeUpdates() {
        return this.mSkipAudioQualityChangeUpdates.getValue();
    }

    public int getManifestRefreshThreads() {
        return this.mManifestRefreshThreads.getValue().intValue();
    }

    public boolean shouldValidateProtectionHeaderBeforeUsing() {
        return this.mValidateProtectionHeaderBeforeUsing.getValue().booleanValue();
    }

    public boolean shouldFetchVodPsshFromInitFragment() {
        return this.mShouldFetchVodPsshFromInitFragment.getValue().booleanValue();
    }

    public boolean shouldUseLegacyPerformanceMonitor(boolean z) {
        return (z && this.mShouldUseLegacyPerformanceMonitorLive.getValue().booleanValue()) || (!z && this.mShouldUseLegacyPerformanceMonitorVOD.getValue().booleanValue());
    }

    public boolean shouldUseManifestContentStore() {
        return this.mShouldUseManifestContentStore.getValue().booleanValue();
    }

    public boolean useMultiPeriodForSegmentTemplateBasedManifest() {
        return this.mUseMultiPeriodForSegmentTemplateBasedManifest.getValue().booleanValue();
    }

    public String getDefaultAudioLanguageForMultiPeriod() {
        return this.mDefaultAudioLanguageMultiPeriod.getValue();
    }

    public boolean shouldValidateMinimalStreamCountPerPeriod() {
        return this.mShouldValidateMinimalStreamCountPerPeriod.getValue().booleanValue();
    }

    public boolean shouldCorrectAudioStreamBoundaries() {
        return this.mCorrectAudioStreamBoundaries.getValue().booleanValue();
    }

    public boolean shouldUseNativeMemoryForManifest() {
        return this.mShouldUseNativeMemoryForManifest.getValue().booleanValue();
    }

    public boolean shouldRequestSegmentListManifest() {
        MobileWeblab mobileWeblab = PlaybackWeblabs.getPlaybackWeblabs().get("AIVPLAYERS_SSAI_MULTI_PERIOD_MANIFEST_352930");
        return this.mShouldRequestSegmentListManifest.getValue().booleanValue() && (mobileWeblab != null && mobileWeblab.getCurrentTreatment().equals(WeblabTreatment.T1));
    }

    @Nonnull
    public String getHeuristicAlgorithmVOD() {
        return this.mHeuristicAlgorithmVOD.getValue().name();
    }

    @Nonnull
    public String getHeuristicAlgorithmLive() {
        return this.mHeuristicAlgorithmLive.getValue().name();
    }

    public boolean isMidstreamSwitchingEnabled() {
        return this.mMidstreamSwitchingEnabled.getValue().booleanValue();
    }

    @Nonnull
    public TimeSpan getLiveCacheBufferLookAhead() {
        return this.mLiveCacheBufferLookAhead.getValue();
    }

    @Nonnull
    public TimeSpan getLiveCacheBufferLookBehind() {
        return this.mLiveCacheBufferLookBehind.getValue();
    }

    @Nonnull
    public TimeSpan getLiveCacheTickerInterval() {
        return this.mLiveCacheTickerInterval.getValue();
    }

    @Nonnull
    public PriorityTier getLiveCacheRequestPriorityTier() {
        return this.mLiveCacheRequestPriorityTier.getValue();
    }

    public boolean useOptimizedCleaningOutOfWindowFragments() {
        return this.mUseOptimizedCleaningOutOfWindowFragments.getValue().booleanValue();
    }

    public boolean shouldCleanOutOfWindowFragmentsBeforeDownload() {
        return this.mShouldCleanOutOfWindowFragmentsBeforeDownload.getValue().booleanValue();
    }

    public boolean shouldGetDurationToCacheFromHeuristics() {
        return this.mShouldGetDurationToCacheFromHeuristics.getValue().booleanValue();
    }

    public int getReportingCadenceForFragmentDiagnostics() {
        return this.mReportingCadenceForFragmentDiagnostics.getValue().intValue();
    }

    public boolean shouldListenForPrimaryDownloadEvents() {
        return this.mShouldListenForPrimaryDownloadEvents.getValue().booleanValue();
    }

    public boolean shouldNotifyHeuristicsOnDownloaderStateChange() {
        return this.mShouldNotifyHeuristicsOnDownloaderStateChange.getValue().booleanValue();
    }

    public boolean shouldPersistCdnManifestRedirects() {
        return this.mShouldPersistCdnManifestRedirects.getValue().booleanValue();
    }

    public boolean shouldAllowCdnFragmentRedirects() {
        return this.mShouldAllowCdnFragmentRedirects.getValue().booleanValue();
    }

    public boolean isManifestUrlOverrideEnabled() {
        return this.mIsManifestUrlOverrideEnabled.getValue().booleanValue();
    }

    public String getManifestUrlOverrideValue() {
        return this.mManifestUrlOverrideValue.getValue();
    }
}
