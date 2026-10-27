package com.amazon.avod.media.playback;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.avod.media.AudioStreamType;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.DashConfig;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.PlaybackConfigKeys;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.playback.drm.DrmScheme;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.visualon.OSMPUtils.voMimeTypes;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class MediaDefaultConfiguration extends MediaConfigBase {
    private static final MediaDefaultConfiguration INSTANCE = new MediaDefaultConfiguration();
    private final TimeConfigurationValue mAVSyncThreshold;
    private final ConfigurationValue<Boolean> mAreTunnelModePipelineStatsEnabled;
    private final TimeConfigurationValue mAsyncBlockingQueuePollTimeout;
    private final ConfigurationValue<Integer> mAsyncBlockingQueueSize;
    private final ConfigurationValue<Integer> mAsyncTaskRetryIntervalMillis;
    private final ConfigurationValue<Integer> mAsyncWatchdogTickIntervalMillis;
    private final ConfigurationValue<Long> mAudioTrackBufferMaxSizeUs;
    private final ConfigurationValue<Long> mAudioTrackBufferMinSizeUs;
    private final ConfigurationValue<Integer> mAudioTrackBufferMultiplicationFactor;
    private final TimeConfigurationValue mAvSyncRenderThresholdV21;
    private final TimeConfigurationValue mBackoffTimeForRetriesOnAudioTrackCreationFailure;
    private final TimeConfigurationValue mBufferedTimeThrottle;
    private final ConfigurationValue<List<String>> mDefaultSupportedAudioCodecs;
    private final ConfigurationValue<List<String>> mHdrSupportFeatureNames;
    private final ConfigurationValue<Boolean> mIsAdjustedTimeFrameReleaseEnabled;
    private final ConfigurationValue<Boolean> mIsAsynchronousModeEnabled;
    private final ConfigurationValue<Boolean> mIsAudioStallingOptimizationEnabled;
    private final ConfigurationValue<Boolean> mIsAudioTimestampApiEnabled;
    private final ConfigurationValue<Boolean> mIsAudioTrackLatencyComputationEnabled;
    private final ConfigurationValue<Boolean> mIsAudioTrackLatencyComputationInPassthroughEnabled;
    private final ConfigurationValue<Boolean> mIsBackgroundAudioPlaybackSupported;
    private final ConfigurationValue<Boolean> mIsDisplaySecurityFlagEvaluationEnabled;
    private final ConfigurationValue<Boolean> mIsDolbyOverBluetoothAudioOutputSupported;
    private final ConfigurationValue<Boolean> mIsDolbyVisionHdrSupported;
    private final ConfigurationValue<Boolean> mIsDummySurfaceEnabled;
    private final ConfigurationValue<Boolean> mIsDynamicVideoSurfaceResizeEnabled;
    private final ConfigurationValue<Boolean> mIsFrameDropSimulationEnabled;
    private final ConfigurationValue<Boolean> mIsHDMIAudioPassthroughEnabled;
    private final ConfigurationValue<Boolean> mIsHEAACV2EvaluationEnabled;
    private final ConfigurationValue<Boolean> mIsHdr10Supported;
    private final ConfigurationValue<Boolean> mIsMediaCodecRecoveryEnabled;
    private final ConfigurationValue<Boolean> mIsMediaCodecZeroMemCopyMediaSourceEnabled;
    private final ConfigurationValue<Boolean> mIsMediaDrmHdcpLevelEvaluationEnabled;
    private final ConfigurationValue<Boolean> mIsMemStoreBasedRendererZeroMemCopyAllowed;
    private final ConfigurationValue<Boolean> mIsNonBlockingAudioRenderingEnabled;
    private final ConfigurationValue<Boolean> mIsOmxIlZeroMemCopyEnabled;
    private final ConfigurationValue<Boolean> mIsOpticalAudioPassthroughEnabled;
    private final ConfigurationValue<Boolean> mIsSamsungSmartFittingEnabled;
    private final ConfigurationValue<Boolean> mIsSuperResolutionUpscalerEnabled;
    private final ConfigurationValue<Boolean> mIsTunnelModeNewWriteAPIForAudioBufferEnabled;
    private final ConfigurationValue<Boolean> mIsTunnelModeNonBlockingAudioRenderingEnabled;
    private final ConfigurationValue<Boolean> mIsVideoSurfaceHotSwapSupported;
    private final ConfigurationValue<Boolean> mIsWidevineFieldProvisioningEnabled;
    private final ConfigurationValue<Integer> mMaxConcurrentSampleCount;
    private final ConfigurationValue<List<String>> mMediaCodecSupportedAudioCodecs;
    private final Map<String, ImmutableList<String>> mMimeTypeByFourCC;
    private final TimeConfigurationValue mMinAudioTrackLatencySampleInterval;
    private final ConfigurationValue<Integer> mMinCompressionRatioAV1;
    private final ConfigurationValue<Integer> mMinCompressionRatioAVC;
    private final ConfigurationValue<Integer> mMinCompressionRatioHEVC;
    private final ConfigurationValue<Long> mMinimumFirmwareVersionNumEnableHEVC;
    private final ConfigurationValue<Boolean> mNewGetBufferApiEnabled;
    private final ConfigurationValue<Integer> mNumSimulatedFrameDrops;
    private final ConfigurationValue<Integer> mNumTriesForAudioTrackCreationFailure;
    private final ConfigurationValue<Boolean> mOverrideHDRDeviceCapability;
    private final ConfigurationValue<Long> mPassThroughAudioTrackTrackBufferSizeUs;
    private final ConfigurationValue<Boolean> mPersistRendererSchemeFailuresAcrossAppRestarts;
    private final ConfigurationValue<Long> mSanityEvaluationTimeoutMillis;
    private final ConfigurationValue<Boolean> mShouldFlushBeforeAudioTrackStop;
    private final ConfigurationValue<Boolean> mShouldReportAVSyncStats;
    private final ConfigurationValue<Boolean> mShouldRetryOnAudioTrackCreationFailure;
    private final ConfigurationValue<Boolean> mShouldValidateVideoDecodingCadence;
    private final ConfigurationValue<Integer> mTickIntervalMillis;
    private final ConfigurationValue<Float> mTunnelModeAudioTrackSizeScaleFactor;
    private final ConfigurationValue<Long> mTunnelModeAudioTrackTrackBufferSizeUs;
    private final ConfigurationValue<Boolean> mUseBackgroundThreadForSurfaceCallbacks;
    private final ConfigurationValue<Long> mVideoDecodingCadenceLoggingThresholdInMillis;
    private final ConfigurationValue<Long> mVideoDecodingCadenceThresholdInMillis;
    private final ConfigurationValue<Boolean> mWaitForAudioStartupEnabled;
    private final ConfigurationValue<Boolean> mWidevineMultiKeyDecryptionEnabled;
    private final DashConfig mDashConfig = DashConfig.getInstance();
    private final Map<String, ImmutableList<UUID>> mCryptoSchemes = ImmutableMap.builder().put("application/vnd.ms-playready.media.pyv", ImmutableList.of(UUID.fromString("11ef8ba9-79d6-4ace-a3c8-27dcd51d2111"), UUID.fromString("9a04f079-9840-4286-ab92-e65be0885f95"))).put("application/video/wvm", ImmutableList.of(UUID.fromString("edef8ba9-79d6-4ace-a3c8-27dcd51d21ed"))).build();

    @Nonnull
    public static MediaDefaultConfiguration getInstance() {
        return INSTANCE;
    }

    private MediaDefaultConfiguration() {
        ImmutableList immutableListOf = ImmutableList.of(voMimeTypes.VOAUDIO_AAC);
        ImmutableList immutableListOf2 = ImmutableList.of("video/avc");
        ImmutableList immutableListOf3 = ImmutableList.of("video/hevc");
        this.mMimeTypeByFourCC = ImmutableMap.builder().put("aacl", immutableListOf).put("aach", immutableListOf).put("ach2", immutableListOf).put("ec-3", ImmutableList.of(voMimeTypes.VOAUDIO_E_AC3, voMimeTypes.VOAUDIO_AC3)).put("atmo", ImmutableList.of(voMimeTypes.VOAUDIO_E_AC3, voMimeTypes.VOAUDIO_AC3)).put("avc1", immutableListOf2).put("h264", immutableListOf2).put("hvc1", immutableListOf3).put("hev1", immutableListOf3).put("av01", ImmutableList.of("video/av01")).put("dvhe", ImmutableList.of("video/dolby-vision")).build();
        this.mMinimumFirmwareVersionNumEnableHEVC = newLongConfigValue("playback_minimumFirmwareVersionNumEnableHEVC", Long.MIN_VALUE);
        this.mIsWidevineFieldProvisioningEnabled = newBooleanConfigValue("playback_isWidevineFieldProvisioningEnabled", true);
        this.mIsDisplaySecurityFlagEvaluationEnabled = newBooleanConfigValue("playback_isDisplaySecurityFlagEvaluationEnabled", true);
        this.mIsMediaDrmHdcpLevelEvaluationEnabled = newBooleanConfigValue("playback_isMediaDrmHdcpLevelEvaluationEnabled", false);
        this.mIsNonBlockingAudioRenderingEnabled = newBooleanConfigValue("playback_isNonBlockingAudioRenderingEnabled", true);
        this.mIsAudioTrackLatencyComputationEnabled = newBooleanConfigValue("playback_isAudioTrackLatencyComputationEnabled", true);
        this.mIsAudioTrackLatencyComputationInPassthroughEnabled = newBooleanConfigValue("playback_isAudioTrackLatencyComputationInPassthroughEnabled", false);
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(500L);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.mMinAudioTrackLatencySampleInterval = newTimeConfigurationValue("playback_minAudioTrackLatencySampleIntervalMillis", timeSpanFromMilliseconds, timeUnit);
        this.mIsAudioTimestampApiEnabled = newBooleanConfigValue("playback_isAudioTimestampApiEnabled", true);
        this.mIsAdjustedTimeFrameReleaseEnabled = newBooleanConfigValue("playback_isAdjustedTimeFrameReleaseEnabled", true);
        this.mIsAudioStallingOptimizationEnabled = newBooleanConfigValue("playback_isAudioStallingOptimizationEnabled", false);
        this.mNewGetBufferApiEnabled = newBooleanConfigValue("playback_newGetBufferApiEnabled", false);
        this.mMaxConcurrentSampleCount = newIntConfigValue("playback_maxConcurrentSampleCount", 10);
        this.mTickIntervalMillis = newIntConfigValue("playback_tickIntervalMillis", 10);
        this.mShouldValidateVideoDecodingCadence = newBooleanConfigValue("playback_shouldValidateVideoDecodingCadence", true);
        this.mVideoDecodingCadenceThresholdInMillis = newLongConfigValue("playback_videoDecodingThresholdInMillis", 6000L);
        ConfigType configType = ConfigType.SERVER;
        this.mVideoDecodingCadenceLoggingThresholdInMillis = newLongConfigValue("playback_videoDecodingLoggingThresholdInMillis", 1000L, configType);
        this.mAsyncWatchdogTickIntervalMillis = newIntConfigValue("playback_asyncWatchdogTickIntervalMillis", 10, configType);
        this.mAudioTrackBufferMultiplicationFactor = newIntConfigValue("playback_audioTrackBufferMultiplicationFactor", 4);
        this.mPassThroughAudioTrackTrackBufferSizeUs = newLongConfigValue("playback_passThroughAudioTrackBufferSizeUs", 250000L);
        this.mTunnelModeAudioTrackTrackBufferSizeUs = newLongConfigValue("playback_passThroughTunnelModeAudioTrackBufferSizeUs", 250000L);
        this.mAudioTrackBufferMinSizeUs = newLongConfigValue("playback_audioTrackBufferMinSizeUs", 250000L);
        this.mAudioTrackBufferMaxSizeUs = newLongConfigValue("playback_audioTrackBufferMaxSizeUs", 750000L);
        this.mWaitForAudioStartupEnabled = newBooleanConfigValue("playback_waitForAudioStartupEnabled", true);
        this.mAvSyncRenderThresholdV21 = newTimeConfigurationValue("playback_avSyncRenderThresholdV21Ms", TimeSpan.fromMilliseconds(50L), timeUnit);
        this.mIsHDMIAudioPassthroughEnabled = newBooleanConfigValue("playback_isHDMIAudioPassthroughEnabled", true);
        this.mIsOpticalAudioPassthroughEnabled = newBooleanConfigValue("playback_isOpticalAudioPassthroughEnabled", true);
        AudioStreamType audioStreamType = AudioStreamType.AACL;
        this.mDefaultSupportedAudioCodecs = newSemicolonDelimitedStringListConfigurationValue("playback_defaultSupportedAudioCodecs", new String[]{audioStreamType.getFourCC(), AudioStreamType.DDP.getFourCC()});
        this.mMediaCodecSupportedAudioCodecs = newSemicolonDelimitedStringListConfigurationValue("playback_mediaCodecSupportedAudioCodecs", new String[]{AudioStreamType.AACH.getFourCC(), AudioStreamType.AACHV2.getFourCC(), audioStreamType.getFourCC()});
        this.mOverrideHDRDeviceCapability = newBooleanConfigValue(PlaybackConfigKeys.OVERRIDE_HDR_DEVICE_CAPABILITY, false);
        this.mHdrSupportFeatureNames = newSemicolonDelimitedStringListConfigurationValue("playback_hdrSupportFeatureNames", new String[]{"com.samsung.feature.hdr_capable", "com.sony.dtv.hardware.hdr", "com.lg.feature.hdr_capable"});
        this.mIsSamsungSmartFittingEnabled = newBooleanConfigValue("playback_isSamsungSmartFittingEnabled", true);
        this.mIsHdr10Supported = newBooleanConfigValue("playback_isHdr10Supported", true);
        this.mIsDolbyVisionHdrSupported = newBooleanConfigValue("playback_isDolbyVisionHdrSupported_2", false);
        this.mWidevineMultiKeyDecryptionEnabled = newBooleanConfigValue(PlaybackConfigKeys.MEDIADRM_WIDEVINE_MULTIKEY_DECRYPTION_ENABLED, true);
        this.mSanityEvaluationTimeoutMillis = newLongConfigValue("playback_sanityEvaluationTimeoutMillis", CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        this.mShouldReportAVSyncStats = newBooleanConfigValue("playback_shouldReportAVSyncStats", true);
        this.mAVSyncThreshold = newTimeConfigurationValue("playback_AVSyncThresholdUs", TimeSpan.fromMilliseconds(100L), TimeUnit.MICROSECONDS);
        this.mIsTunnelModeNewWriteAPIForAudioBufferEnabled = newBooleanConfigValue("playback_isTunnelModeNewWriteAPIForAudioBufferEnabled", false);
        this.mIsTunnelModeNonBlockingAudioRenderingEnabled = newBooleanConfigValue("playback_isTunnelModeNonBlockingAudioRenderingEnabled", false);
        this.mTunnelModeAudioTrackSizeScaleFactor = newFloatConfigValue("playback_tunnelModeAudioTrackSizeScaleFactor", 1.0f);
        this.mIsMediaCodecZeroMemCopyMediaSourceEnabled = newBooleanConfigValue("playback_isMediaCodecZeroMemCopyMediaSourceEnabled", true);
        this.mBufferedTimeThrottle = newTimeConfigurationValue("playback_bytebuffer_throttle_bufferedtime_seconds", TimeSpan.fromSeconds(30.0d), TimeUnit.SECONDS);
        this.mIsOmxIlZeroMemCopyEnabled = newBooleanConfigValue("playback_omxil_zeromem_copy_enabled", false);
        this.mIsMemStoreBasedRendererZeroMemCopyAllowed = newBooleanConfigValue("playback_isMemStoreBasedRendererZeroMemCopyAllowed", false, ConfigType.INTERNAL);
        this.mPersistRendererSchemeFailuresAcrossAppRestarts = newBooleanConfigValue("playback_persistRendererSchemeFailuresAcrossAppRestarts", true);
        this.mShouldFlushBeforeAudioTrackStop = newBooleanConfigValue("playback_shouldFlushBeforeAudioTrackStop", true);
        this.mShouldRetryOnAudioTrackCreationFailure = newBooleanConfigValue("playback_shouldRetryOnAudioTrackCreationFailure", true);
        this.mNumTriesForAudioTrackCreationFailure = newIntConfigValue("playback_numTriesForAudioTrackCreationFailure", 1);
        this.mBackoffTimeForRetriesOnAudioTrackCreationFailure = newTimeConfigurationValue("playback_backoffTimeForRetriesOnAudioTrackCreationFailuresInMillis", TimeSpan.fromMilliseconds(400L), timeUnit);
        this.mIsAsynchronousModeEnabled = newBooleanConfigValue("playback_isAsynchronousModeEnabled", false);
        this.mAsyncTaskRetryIntervalMillis = newIntConfigValue("playback_asyncTaskRetryIntervalMillis", 10);
        this.mAsyncBlockingQueueSize = newIntConfigValue("playback_asyncBlockingQueueSize", 20);
        this.mAsyncBlockingQueuePollTimeout = newTimeConfigurationValue("playback_asyncBlockingQueuePollTimeoutMillis", TimeSpan.fromMilliseconds(100L), timeUnit);
        this.mIsDynamicVideoSurfaceResizeEnabled = newBooleanConfigValue("playback_isDynamicVideoSurfaceResizeEnabled", true);
        this.mIsFrameDropSimulationEnabled = newBooleanConfigValue("playback_isFrameDropSimulationEnabled", false);
        this.mNumSimulatedFrameDrops = newIntConfigValue("playback_numSimulatedFrameDrops", 0);
        this.mAreTunnelModePipelineStatsEnabled = newBooleanConfigValue("playback_areTunnelModePipelineStatsEnabled", true, configType);
        this.mIsHEAACV2EvaluationEnabled = newBooleanConfigValue("playback_isHEAACV2EvaluationEnabled", true, configType);
        this.mIsVideoSurfaceHotSwapSupported = newBooleanConfigValue("playback_isVideoSurfaceHotSwapSupported_2", true, configType);
        this.mIsDummySurfaceEnabled = newBooleanConfigValue("playback_isDummySurfaceEnabled", false, configType);
        this.mIsBackgroundAudioPlaybackSupported = newBooleanConfigValue("playback_isBackgroundAudioPlaybackSupported", true, configType);
        this.mMinCompressionRatioAVC = newIntConfigValue("playback_minCompressionRatioAVC", 2, configType);
        this.mMinCompressionRatioHEVC = newIntConfigValue("playback_minCompressionRatioHEVC", 4, configType);
        this.mMinCompressionRatioAV1 = newIntConfigValue("playback_minCompressionRatioAV1", 4, configType);
        this.mIsDolbyOverBluetoothAudioOutputSupported = newBooleanConfigValue("playback_isDolbyOverBluetoothAudioOutputSupported", false, configType);
        this.mIsMediaCodecRecoveryEnabled = newBooleanConfigValue("playback_isMediaCodecRecoveryEnabled", true);
        this.mUseBackgroundThreadForSurfaceCallbacks = newBooleanConfigValue("playback_useBackgroundThreadForSurfaceCallbacks", false);
        this.mIsSuperResolutionUpscalerEnabled = newBooleanConfigValue("playback_isSuperResolutionUpscalerEnabled", false, configType);
    }

    @Nonnull
    public List<String> getMimeTypesByFourCC(@Nonnull String str) {
        return (List) MoreObjects.firstNonNull(this.mMimeTypeByFourCC.get(str.toLowerCase(Locale.US)), Collections.emptyList());
    }

    @Nonnull
    public List<UUID> getPreferredCryptoSchemes(@Nonnull String str) {
        return (List) MoreObjects.firstNonNull(this.mCryptoSchemes.get(str), Collections.emptyList());
    }

    @Nonnull
    public List<String> getHevcMimeTypes() {
        return getMimeTypesByFourCC("hvc1");
    }

    @Nonnull
    public List<String> getAv1MimeTypes() {
        return getMimeTypesByFourCC("av01");
    }

    @Nonnull
    public List<String> getDolbyDigitalPlusMimeTypes() {
        return getMimeTypesByFourCC("ec-3");
    }

    @Nonnull
    public List<UUID> getCryptoSchemes(@Nonnull DrmScheme drmScheme) {
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        if (drmScheme == DrmScheme.PLAYREADY) {
            return getPreferredCryptoSchemes("application/vnd.ms-playready.media.pyv");
        }
        if (drmScheme == DrmScheme.WIDEVINE) {
            return getPreferredCryptoSchemes("application/video/wvm");
        }
        return Collections.emptyList();
    }

    public boolean isWidevineEnabled() {
        return this.mDashConfig.isWidevineEnabled();
    }

    public boolean isWidevineFieldProvisioningEnabled() {
        return this.mIsWidevineFieldProvisioningEnabled.getValue().booleanValue();
    }

    public boolean isDisplaySecurityFlagEvaluationEnabled() {
        return this.mIsDisplaySecurityFlagEvaluationEnabled.getValue().booleanValue();
    }

    public boolean isMediaDrmHdcpLevelEvaluationEnabled() {
        return this.mIsMediaDrmHdcpLevelEvaluationEnabled.getValue().booleanValue();
    }

    public boolean isNonBlockingAudioRenderingEnabled() {
        return this.mIsNonBlockingAudioRenderingEnabled.getValue().booleanValue();
    }

    public boolean isAdjustedTimeFrameReleaseEnabled() {
        return this.mIsAdjustedTimeFrameReleaseEnabled.getValue().booleanValue();
    }

    public boolean isAudioStallingOptimizationEnabled() {
        return this.mIsAudioStallingOptimizationEnabled.getValue().booleanValue();
    }

    public boolean isNewGetBufferApiEnabled() {
        return this.mNewGetBufferApiEnabled.getValue().booleanValue();
    }

    public int getMaxConcurrentSampleCount() {
        return this.mMaxConcurrentSampleCount.getValue().intValue();
    }

    public int getTickIntervalMillis() {
        return this.mTickIntervalMillis.getValue().intValue();
    }

    public boolean shouldValidateVideoDecodingCadence() {
        return this.mShouldValidateVideoDecodingCadence.getValue().booleanValue();
    }

    public long getVideoDecodingCadenceThresholdInMillis() {
        return this.mVideoDecodingCadenceThresholdInMillis.getValue().longValue();
    }

    public long getVideoDecodingCadenceLoggingThresholdInMillis() {
        return this.mVideoDecodingCadenceLoggingThresholdInMillis.getValue().longValue();
    }

    public int getAsyncWatchdogTickIntervalMillis() {
        return this.mAsyncWatchdogTickIntervalMillis.getValue().intValue();
    }

    public boolean isAudioTrackLatencyComputationEnabled() {
        return this.mIsAudioTrackLatencyComputationEnabled.getValue().booleanValue();
    }

    public boolean isAudioTrackLatencyComputationInPassthroughEnabled() {
        return this.mIsAudioTrackLatencyComputationInPassthroughEnabled.getValue().booleanValue();
    }

    @Nonnull
    public TimeSpan getMinAudioTrackLatencySampleInterval() {
        return this.mMinAudioTrackLatencySampleInterval.getValue();
    }

    public boolean isAudioTimestampApiEnabled() {
        return this.mIsAudioTimestampApiEnabled.getValue().booleanValue();
    }

    public int getAudioTrackBufferMultiplicationFactor() {
        return this.mAudioTrackBufferMultiplicationFactor.getValue().intValue();
    }

    public long getMinimumFirmwareVersionNumEnableHEVC() {
        return this.mMinimumFirmwareVersionNumEnableHEVC.getValue().longValue();
    }

    public long getPassThroughAudioTrackBufferSizeUs() {
        return this.mPassThroughAudioTrackTrackBufferSizeUs.getValue().longValue();
    }

    public long getTunnelModeAudioTrackBufferSizeUs() {
        return this.mTunnelModeAudioTrackTrackBufferSizeUs.getValue().longValue();
    }

    public long getAudioTrackBufferMinSizeUs() {
        return this.mAudioTrackBufferMinSizeUs.getValue().longValue();
    }

    public long getAudioTrackBufferMaxSizeUs() {
        return this.mAudioTrackBufferMaxSizeUs.getValue().longValue();
    }

    public boolean waitForAudioStartupEnabled() {
        return this.mWaitForAudioStartupEnabled.getValue().booleanValue();
    }

    @Nonnull
    public TimeSpan getAvSyncRenderThresholdV21() {
        return this.mAvSyncRenderThresholdV21.getValue();
    }

    public boolean isHDMIAudioPassthroughEnabled() {
        return this.mIsHDMIAudioPassthroughEnabled.getValue().booleanValue();
    }

    public boolean isOpticalAudioPassthroughEnabled() {
        return this.mIsOpticalAudioPassthroughEnabled.getValue().booleanValue();
    }

    public List<String> getDefaultSupportedAudioCodecs() {
        return this.mDefaultSupportedAudioCodecs.getValue();
    }

    public List<String> getMediaCodecSupportedAudioCodecs() {
        return this.mMediaCodecSupportedAudioCodecs.getValue();
    }

    public boolean getOverrideHDRDeviceCapability() {
        return this.mOverrideHDRDeviceCapability.getValue().booleanValue();
    }

    public List<String> getHdrFeatureNameList() {
        return this.mHdrSupportFeatureNames.getValue();
    }

    public boolean isSamsungSmartFittingEnabled() {
        return this.mIsSamsungSmartFittingEnabled.getValue().booleanValue();
    }

    public boolean isHdr10Supported() {
        return this.mIsHdr10Supported.getValue().booleanValue();
    }

    public boolean isDolbyVisionHdrSupported() {
        return this.mIsDolbyVisionHdrSupported.getValue().booleanValue();
    }

    public boolean isWidevineMultiKeyDecryptionEnabled() {
        return this.mWidevineMultiKeyDecryptionEnabled.getValue().booleanValue();
    }

    public long getSanityEvaluationTimeoutMillis() {
        return this.mSanityEvaluationTimeoutMillis.getValue().longValue();
    }

    public boolean isTunnelModeNewWriteAPIForAudioBufferEnabled() {
        return this.mIsTunnelModeNewWriteAPIForAudioBufferEnabled.getValue().booleanValue();
    }

    public boolean isTunnelModeNonBlockingAudioRenderingEnabled() {
        return this.mIsTunnelModeNonBlockingAudioRenderingEnabled.getValue().booleanValue();
    }

    public float getTunnelModeAudioTrackSizeScaleFactor() {
        return this.mTunnelModeAudioTrackSizeScaleFactor.getValue().floatValue();
    }

    public boolean isMemStoreBasedRendererZeroMemCopyAllowed() {
        return this.mIsMemStoreBasedRendererZeroMemCopyAllowed.getValue().booleanValue();
    }

    public void setIsMemStoreBasedRendererZeroMemCopyAllowed(boolean z) {
        this.mIsMemStoreBasedRendererZeroMemCopyAllowed.updateValue(Boolean.valueOf(z));
    }

    public boolean isMediaCodecZeroMemCopyMediaSourceEnabled() {
        return this.mIsMediaCodecZeroMemCopyMediaSourceEnabled.getValue().booleanValue();
    }

    public TimeSpan getAVSyncThreshold() {
        return this.mAVSyncThreshold.getValue();
    }

    public boolean shouldReportAVSyncStats() {
        return this.mShouldReportAVSyncStats.getValue().booleanValue();
    }

    @Nonnull
    public TimeSpan getBufferedTimeThrottle() {
        return this.mBufferedTimeThrottle.getValue();
    }

    public boolean isOmxIlZeroMemCopyEnabled() {
        return this.mIsOmxIlZeroMemCopyEnabled.getValue().booleanValue();
    }

    public boolean shouldFlushBeforeAudioTrackStop() {
        return this.mShouldFlushBeforeAudioTrackStop.getValue().booleanValue();
    }

    public boolean shouldRetryOnAudioTrackCreationFailure() {
        return this.mShouldRetryOnAudioTrackCreationFailure.getValue().booleanValue();
    }

    public int getNumTriesForAudioTrackCreationFailure() {
        return this.mNumTriesForAudioTrackCreationFailure.getValue().intValue();
    }

    @Nonnull
    public TimeSpan getBackoffTimeForRetriesOnAudioTrackCreationFailure() {
        return this.mBackoffTimeForRetriesOnAudioTrackCreationFailure.getValue();
    }

    public boolean isAsynchronousModeEnabled() {
        return this.mIsAsynchronousModeEnabled.getValue().booleanValue();
    }

    public int getAsyncTaskRetryIntervalMillis() {
        return this.mAsyncTaskRetryIntervalMillis.getValue().intValue();
    }

    public int getAsyncBlockingQueueSize() {
        return this.mAsyncBlockingQueueSize.getValue().intValue();
    }

    @Nonnull
    public TimeSpan getAsyncBlockingQueuePollTimeout() {
        return this.mAsyncBlockingQueuePollTimeout.getValue();
    }

    public boolean isDynamicVideoSurfaceResizeEnabled() {
        return this.mIsDynamicVideoSurfaceResizeEnabled.getValue().booleanValue();
    }

    public boolean isFrameDropSimulationEnabled() {
        return this.mIsFrameDropSimulationEnabled.getValue().booleanValue();
    }

    public int getNumSimulatedFrameDrops() {
        return this.mNumSimulatedFrameDrops.getValue().intValue();
    }

    public boolean areTunnelModePipelineStatsEnabled() {
        return this.mAreTunnelModePipelineStatsEnabled.getValue().booleanValue();
    }

    public boolean isHEAACV2EvaluationEnabled() {
        return this.mIsHEAACV2EvaluationEnabled.getValue().booleanValue();
    }

    public boolean isVideoSurfaceHotSwapSupported() {
        return this.mIsVideoSurfaceHotSwapSupported.getValue().booleanValue();
    }

    public boolean isBackgroundAudioPlaybackSupported() {
        return this.mIsBackgroundAudioPlaybackSupported.getValue().booleanValue();
    }

    public boolean isDummySurfaceEnabled() {
        return this.mIsDummySurfaceEnabled.getValue().booleanValue();
    }

    public int getMinCompressionRatioAVC() {
        return this.mMinCompressionRatioAVC.getValue().intValue();
    }

    public int getMinCompressionRatioHEVC() {
        return this.mMinCompressionRatioHEVC.getValue().intValue();
    }

    public int getMinCompressionRatioAV1() {
        return this.mMinCompressionRatioAV1.getValue().intValue();
    }

    public boolean isDolbyOverBluetoothAudioOutputSupported() {
        return this.mIsDolbyOverBluetoothAudioOutputSupported.getValue().booleanValue();
    }

    public boolean isMediaCodecRecoveryEnabled() {
        return this.mIsMediaCodecRecoveryEnabled.getValue().booleanValue();
    }

    public boolean useBackgroundThreadForSurfaceCallbacks() {
        return this.mUseBackgroundThreadForSurfaceCallbacks.getValue().booleanValue();
    }
}
