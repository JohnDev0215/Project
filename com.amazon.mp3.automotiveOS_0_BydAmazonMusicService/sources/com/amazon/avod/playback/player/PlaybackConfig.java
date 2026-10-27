package com.amazon.avod.playback.player;

import amazon.android.config.ConfigurationValue;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.playback.PlaybackException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackConfig extends MediaConfigBase {
    public static final PlaybackConfig INSTANCE = new PlaybackConfig();
    private final ConfigurationValue<Integer> mAuditPingStartCode;
    private final ConfigurationValue<Integer> mAuditPingStopCode;
    private final ConfigurationValue<Integer> mAuditPingUooStatusOptInCode;
    private final ConfigurationValue<Integer> mAuditPingUooStatusOptOutCode;
    private final ConfigurationValue<Boolean> mCanDisableCache;
    private final ConfigurationValue<Boolean> mCanDisableDownloads;
    private final ConfigurationValue<Integer> mConsecutiveLowFpsIntervalsRequired;
    private final ConfigurationValue<Float> mExponentialTimeWindowStartFactor;
    private final ConfigurationValue<Boolean> mIsAVSyncStatsReportingEnabled;
    private final ConfigurationValue<Boolean> mIsAuditPingEnabled;
    private final ConfigurationValue<Boolean> mIsDrmSystemBusyRecoveryEnabled;
    private final ConfigurationValue<Boolean> mIsExponentialTimeWindowStartThresholdEnabled;
    private final ConfigurationValue<Boolean> mIsOutputDisplayChangeSupported;
    private final ConfigurationValue<Boolean> mIsRendererBufferStatsEnabled;
    private final ConfigurationValue<Set<String>> mLicenseFailureErrorStrings;
    private final ConfigurationValue<Integer> mNativeAudioBufferSizeMb;
    private final ConfigurationValue<Integer> mNativeAvcVideoBufferSizeMb;
    private final ConfigurationValue<Integer> mNativeBufferRequiredForContinueInMs;
    private final ConfigurationValue<Integer> mNativeBufferRequiredForStartPlaybackInMs;
    private final ConfigurationValue<Integer> mNativeHevcVideoBufferSizeMb;
    private final ConfigurationValue<Boolean> mPauseDownloadWhileStreaming;
    private final TimeConfigurationValue mPlaybackStateMachineTickInterval;
    private final TimeConfigurationValue mPlaybackStatusEventIntervalMillis;
    private final TimeConfigurationValue mPlayerLifecycleStateMachineTerminateTimeout;
    private final ConfigurationValue<Integer> mRealtimeDriftThresholdMillis;
    private final TimeConfigurationValue mRendererInactivityThreshold;
    private final TimeConfigurationValue mRendererInitializeSleep;
    private final TimeConfigurationValue mRendererInitializeTimeout;
    private final ConfigurationValue<Boolean> mRendererReuseEnabled;
    private final TimeConfigurationValue mRendererTerminationTimeout;
    private final TimeConfigurationValue mRendererUpdateStatisticsInterval;
    private final TimeConfigurationValue mSeekTriggerProximityThreshold;
    private final ConfigurationValue<Boolean> mShouldForceMediaCodecMediaDrmForTnf;
    private final ConfigurationValue<Boolean> mShouldForceOmxForTnf;
    private final ConfigurationValue<Boolean> mShouldReportStreamMetaData;
    private final ConfigurationValue<Boolean> mShouldRestartOnCorruptFragment;
    private final ConfigurationValue<Boolean> mShouldUseWidevineForTnf;
    private final ConfigurationValue<String> mSupportedAdAgencyForAuditPing;
    private final TimeConfigurationValue mSurfaceCreationTimeout;
    private final TimeConfigurationValue mTimeToRenderBeforePostingResume;
    private final TimeConfigurationValue mTimeWindowStartMaxThreshold;
    private final TimeConfigurationValue mTimeWindowStartThreshold;
    private final ConfigurationValue<Integer> mVideoRegionChangeInterpolationTimeMillis;
    private final ConfigurationValue<Integer> mRenderedTimeCheckIntervalMillis = newIntConfigValue("playback_RenderedTimeCheckIntervalMillis", 1000);
    private final ConfigurationValue<Boolean> mIsRendererActivityWatchdogEnabled = newBooleanConfigValue("playback_rendererActivityWatchdogEnabled", true);

    PlaybackConfig() {
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(4000L);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.mRendererInactivityThreshold = newTimeConfigurationValue("playback_rendererInactivityThresholdInMillis", timeSpanFromMilliseconds, timeUnit);
        this.mRealtimeDriftThresholdMillis = newIntConfigValue("playback_RealtimeRendererDriftThresholdMillis", ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        this.mVideoRegionChangeInterpolationTimeMillis = newIntConfigValue("playback_VideoRegionChangeInterpolationTimeMillis", 300);
        this.mNativeBufferRequiredForStartPlaybackInMs = newIntConfigValue("NativeBufferRequiredForStartPlaybackInMs", 2000);
        this.mNativeBufferRequiredForContinueInMs = newIntConfigValue("NativeBufferRequiredForContinueInMs", 50);
        this.mConsecutiveLowFpsIntervalsRequired = newIntConfigValue("playback_consecutiveLowFpsIntervalsRequired", 5);
        this.mSeekTriggerProximityThreshold = newTimeConfigurationValue("playback_seekTriggerProximityThresholdInMillis", TimeSpan.fromMilliseconds(100L), timeUnit);
        this.mRendererInitializeSleep = newTimeConfigurationValue("rendererInitializeSleepMS", TimeSpan.fromMilliseconds(50L), timeUnit);
        this.mRendererInitializeTimeout = newTimeConfigurationValue("rendererInitializeTimeoutMS", TimeSpan.fromSeconds(10.0d), timeUnit);
        this.mRendererUpdateStatisticsInterval = newTimeConfigurationValue("rendererUpdateStatisticsIntervalMS", TimeSpan.fromSeconds(60.0d), timeUnit);
        this.mTimeToRenderBeforePostingResume = newTimeConfigurationValue("playback_timeRenderedBeforePostingResumeMS", TimeSpan.fromMilliseconds(100L), timeUnit);
        this.mRendererTerminationTimeout = newTimeConfigurationValue("rendererTerminationTimeoutMS", TimeSpan.fromMilliseconds(3000L), timeUnit);
        this.mPauseDownloadWhileStreaming = newBooleanConfigValue("playback_pauseDownloadWhileStreaming", true);
        this.mTimeWindowStartThreshold = newTimeConfigurationValue("live_timeWindowStartThresholdMillis", TimeSpan.fromMilliseconds(10000L), timeUnit);
        this.mRendererReuseEnabled = newBooleanConfigValue("playback_rendererReuseEnabled_2", false);
        this.mLicenseFailureErrorStrings = newSemicolonDelimitedStringSetConfigurationValue("playback_licenseFailuresErrorStrings", new String[]{LicenseError.AIV_LICENSE_SERVICE_CALL_FAILURE.getExternalCode().getName(), PlaybackException.PlaybackError.RENDERER_DECRYPTION_FAILURE_MISSING_KEY.getExternalCode().getName(), PlaybackException.PlaybackError.RENDERER_DECRYPTION_FAILURE_EXPIRED_KEY.getExternalCode().getName()});
        this.mShouldUseWidevineForTnf = newBooleanConfigValue("playback_shouldUseWidevineForTnf", false);
        this.mShouldForceOmxForTnf = newBooleanConfigValue("playback_shouldForceOmxForTnf", false);
        this.mShouldRestartOnCorruptFragment = newBooleanConfigValue("playback_shouldRestartOnCorruptFragment", true);
        this.mShouldForceMediaCodecMediaDrmForTnf = newBooleanConfigValue("playback_shouldForceMediaCodecMediaDrmForTnf", false);
        this.mIsAuditPingEnabled = newBooleanConfigValue("playback_auditPingEnabled", true);
        this.mAuditPingStartCode = newIntConfigValue("playback_auditPingNielsenStartCode", 1);
        this.mAuditPingStopCode = newIntConfigValue("playback_auditPingNielsenStopCode", 3);
        this.mAuditPingUooStatusOptInCode = newIntConfigValue("playback_auditPingUooStatusOptInCode", 0);
        this.mAuditPingUooStatusOptOutCode = newIntConfigValue("playback_auditPingUooStatusOptOutCode", 1);
        this.mSupportedAdAgencyForAuditPing = newStringConfigValue("playback_supportedAdAgencyForAuditPing", "Nielsen");
        this.mIsDrmSystemBusyRecoveryEnabled = newBooleanConfigValue("playback_isDrmSystemBusyRecoveryEnabled", true);
        this.mIsRendererBufferStatsEnabled = newBooleanConfigValue("playback_isRendererBufferStatsEnabled", true);
        this.mIsAVSyncStatsReportingEnabled = newBooleanConfigValue("playback_isAVSyncStatsReportingEnabled", true);
        this.mCanDisableCache = newBooleanConfigValue("playback_canDisableCache", true);
        this.mCanDisableDownloads = newBooleanConfigValue("playback_canDisableDownloads", true);
        this.mPlayerLifecycleStateMachineTerminateTimeout = newTimeConfigurationValue("playback_playerLifecycleStateMachineTimeoutMillis", TimeSpan.fromMilliseconds(20000L), timeUnit);
        this.mSurfaceCreationTimeout = newTimeConfigurationValue("playback_surfaceCreationTimeoutMillis", TimeSpan.fromMilliseconds(15000L), timeUnit);
        this.mExponentialTimeWindowStartFactor = newFloatConfigValue("playback_exponentialTimeWindowStartFactor", 2.0f);
        this.mIsExponentialTimeWindowStartThresholdEnabled = newBooleanConfigValue("playback_exponentialTimeWindowStartThresholdEnabled", true);
        this.mTimeWindowStartMaxThreshold = newTimeConfigurationValue("live_timeWindowStartMaxThresholdMillis", TimeSpan.fromMilliseconds(60000L), timeUnit);
        this.mShouldReportStreamMetaData = newBooleanConfigValue("playback_shouldReportStreamMetaData", true);
        this.mIsOutputDisplayChangeSupported = newBooleanConfigValue("playback_isOutputDisplayChangeSupported", true);
        this.mPlaybackStateMachineTickInterval = newTimeConfigurationValue("playback_playbackStateMachineTickIntervalMillis", TimeSpan.fromMilliseconds(15L), timeUnit);
        this.mNativeAudioBufferSizeMb = newIntConfigValue("playback_nativeAudioBufferSizeMb", 2);
        this.mNativeAvcVideoBufferSizeMb = newIntConfigValue("playback_nativeAvcVideoBufferSizeMb", 8);
        this.mNativeHevcVideoBufferSizeMb = newIntConfigValue("playback_nativeHevcVideoBufferSizeMb", 20);
        this.mPlaybackStatusEventIntervalMillis = newTimeConfigurationValue("playback_playbackStatusEventIntervalMillis", TimeSpan.fromMilliseconds(250L), timeUnit);
    }

    @Nonnull
    public TimeSpan getTimeWindowStartMaxThreshold() {
        return this.mTimeWindowStartMaxThreshold.getValue();
    }

    public boolean isExponentialTimeWindowStartThresholdEnabled() {
        return this.mIsExponentialTimeWindowStartThresholdEnabled.getValue().booleanValue();
    }

    public float getExponentialTimeWindowStartFactor() {
        return this.mExponentialTimeWindowStartFactor.getValue().floatValue();
    }

    public TimeSpan getRenderedTimeCheckIntervalMillis() {
        return TimeSpan.fromMilliseconds(this.mRenderedTimeCheckIntervalMillis.getValue().intValue());
    }

    public boolean isRendererActivityWatchdogEnabled() {
        return this.mIsRendererActivityWatchdogEnabled.getValue().booleanValue();
    }

    public boolean isAuditPingEnabled() {
        return this.mIsAuditPingEnabled.getValue().booleanValue();
    }

    public int getAuditPingStartCode() {
        return this.mAuditPingStartCode.getValue().intValue();
    }

    public int getAuditPingStopCode() {
        return this.mAuditPingStopCode.getValue().intValue();
    }

    @Nonnull
    public TimeSpan getRendererInactivityThreshold() {
        return this.mRendererInactivityThreshold.getValue();
    }

    public TimeSpan getRealtimeDriftThresholdMillis() {
        return TimeSpan.fromMilliseconds(this.mRealtimeDriftThresholdMillis.getValue().intValue());
    }

    public TimeSpan getRequiredNativeBufferForStartPlayback() {
        return TimeSpan.fromMilliseconds(this.mNativeBufferRequiredForStartPlaybackInMs.getValue().intValue());
    }

    public TimeSpan getRequiredNativeBufferForContinuePlayback() {
        return TimeSpan.fromMilliseconds(this.mNativeBufferRequiredForContinueInMs.getValue().intValue());
    }

    public TimeSpan getVideoRegionInterpolationTime() {
        return TimeSpan.fromMilliseconds(this.mVideoRegionChangeInterpolationTimeMillis.getValue().intValue());
    }

    public int getConsecutiveLowFpsIntervalsRequired() {
        return this.mConsecutiveLowFpsIntervalsRequired.getValue().intValue();
    }

    public TimeSpan getSeekTriggerProximityThreshold() {
        return this.mSeekTriggerProximityThreshold.getValue();
    }

    public TimeSpan getRendererUpdateStatisticsInterval() {
        return this.mRendererUpdateStatisticsInterval.getValue();
    }

    public TimeSpan getTimeToRenderBeforePostingResume() {
        return this.mTimeToRenderBeforePostingResume.getValue();
    }

    public TimeSpan getRendererTerminationTimeout() {
        return this.mRendererTerminationTimeout.getValue();
    }

    public boolean shouldPauseDownloadWhileStreaming() {
        return this.mPauseDownloadWhileStreaming.getValue().booleanValue();
    }

    public TimeSpan getTimeWindowStartThreshold() {
        return this.mTimeWindowStartThreshold.getValue();
    }

    @Nonnull
    public Set<String> getLicenseFailureErrorStrings() {
        return this.mLicenseFailureErrorStrings.getValue();
    }

    @Nonnull
    public String getSupportedAdAgencyForAuditPing() {
        return this.mSupportedAdAgencyForAuditPing.getValue();
    }

    public int getAuditPingUooStatusOptOutCode() {
        return this.mAuditPingUooStatusOptOutCode.getValue().intValue();
    }

    public int getAuditPingUooStatusOptInCode() {
        return this.mAuditPingUooStatusOptInCode.getValue().intValue();
    }

    public boolean isRendererReuseEnabled() {
        return this.mRendererReuseEnabled.getValue().booleanValue();
    }

    public boolean shouldUseWidevineForTnf() {
        return this.mShouldUseWidevineForTnf.getValue().booleanValue();
    }

    public boolean shouldForceOmxForTnf() {
        return this.mShouldForceOmxForTnf.getValue().booleanValue();
    }

    public boolean shouldForceMediaCodecMediaDrmForTnf() {
        return this.mShouldForceMediaCodecMediaDrmForTnf.getValue().booleanValue();
    }

    public boolean shouldRestartOnCorruptFragment() {
        return this.mShouldRestartOnCorruptFragment.getValue().booleanValue();
    }

    public boolean isDrmSystemBusyRecoveryEnabled() {
        return this.mIsDrmSystemBusyRecoveryEnabled.getValue().booleanValue();
    }

    public boolean isRendererBufferStatsEnabled() {
        return this.mIsRendererBufferStatsEnabled.getValue().booleanValue();
    }

    public boolean isAVSyncStatsReportingEnabled() {
        return this.mIsAVSyncStatsReportingEnabled.getValue().booleanValue();
    }

    public boolean canDisableCache() {
        return this.mCanDisableCache.getValue().booleanValue();
    }

    public boolean canDisableDownloads() {
        return this.mCanDisableDownloads.getValue().booleanValue();
    }

    @Nonnull
    public TimeSpan getPlayerLifecycleStateMachineTerminateTimeout() {
        return this.mPlayerLifecycleStateMachineTerminateTimeout.getValue();
    }

    @Nonnull
    public TimeSpan getPlayerSurfaceTerminateTimeout() {
        return this.mSurfaceCreationTimeout.getValue();
    }

    public boolean getShouldReportStreamMetaData() {
        return this.mShouldReportStreamMetaData.getValue().booleanValue();
    }

    public boolean isOutputDisplayChangeSupported() {
        return this.mIsOutputDisplayChangeSupported.getValue().booleanValue();
    }

    @Nonnull
    public TimeSpan getPlaybackStateMachineTickInterval() {
        return this.mPlaybackStateMachineTickInterval.getValue();
    }

    public int getNativeAudioBufferSizeMb() {
        return this.mNativeAudioBufferSizeMb.getValue().intValue();
    }

    public int getNativeAvcVideoBufferSizeMb() {
        return this.mNativeAvcVideoBufferSizeMb.getValue().intValue();
    }

    public int getNativeHevcVideoBufferSizeMb() {
        return this.mNativeHevcVideoBufferSizeMb.getValue().intValue();
    }

    @Nonnull
    public TimeSpan getPlaybackStatusEventIntervalMillis() {
        return this.mPlaybackStatusEventIntervalMillis.getValue();
    }
}
