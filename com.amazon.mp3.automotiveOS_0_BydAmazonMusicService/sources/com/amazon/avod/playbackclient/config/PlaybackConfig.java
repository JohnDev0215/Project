package com.amazon.avod.playbackclient.config;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.connectivity.NetworkType;
import com.amazon.avod.core.constants.UrlType;
import com.amazon.avod.listeners.SetListenerProxy;
import com.amazon.avod.settings.StreamingConnectionSetting;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackConfig extends ConfigBase {
    private static final StreamingConnectionSetting STREAMING_CONNECTION_SETTING_FALLBACK = StreamingConnectionSetting.BEST;
    private final ConfigurationValue<Long> mBufferingThresholdMillis;
    private final ConfigurationValue<Boolean> mDelayPreparingPlaybackFeaturesUntilOnStarted;
    private final ConfigurationValue<DeviceFamily> mDeviceFamily;
    private final ConfigurationValue<Long> mExitPlaybackAfterPauseMillis;
    private final ConfigurationValue<Boolean> mFinishPlaybackActivityTimeoutEnabled;
    private final ConfigurationValue<Long> mFinishPlaybackActivityTimeoutMins;
    private final ConfigurationValue<Boolean> mForceDeskModeInEchoShowModeOnFireTablet;
    private final ConfigurationValue<Boolean> mHasSeenFirstTimeStreamingNotification;
    private final ConfigurationValue<Boolean> mIsContinuousPlayPreinitEnabled;
    private final ConfigurationValue<Boolean> mIsDoubleTapToSeekEnabled;
    private final ConfigurationValue<Boolean> mIsEmbeddedPlaybackEnabled;
    private final ConfigurationValue<Boolean> mIsGestureSeekingWeblabEnabled;
    private final ConfigurationValue<Boolean> mIsImmersiveModeEnabled;
    private final ConfigurationValue<Boolean> mIsLiveCacheEnabled;

    @Deprecated
    private final ConfigurationValue<Boolean> mIsMismatchedUhdFrameRateSupported;
    private final ConfigurationValue<Boolean> mIsMultiWindowSupported;
    private final ConfigurationValue<Boolean> mIsMultiWindowSupportedAndroid28;
    private final ConfigurationValue<Boolean> mIsPIPEnabled;
    private final ConfigurationValue<Boolean> mIsPlaybackActivityBackgroundAudioPlaybackEnabled;
    private final ConfigurationValue<Boolean> mIsPlaybackActivitySingleInstanceEnabled;
    private final ConfigurationValue<Boolean> mIsPlaybackActivitySingleInstanceWeblabEnabled;
    private final ConfigurationValue<Boolean> mIsPlaybackActivityVideoSurfaceHotSwapEnabled;
    private final ConfigurationValue<Boolean> mIsPlaybackBackgroundBlack;
    private final ConfigurationValue<Boolean> mIsPlayingTrueDuringLoad;
    private final ConfigurationValue<Boolean> mIsSecurePlaybackSurfaceEnabled;
    private final ConfigurationValue<Boolean> mIsThirdPartyClientMediaCommandFeatureEnabled;
    private final ConfigurationValue<Boolean> mIsWANStreamingDisabledByUser;
    private final ConfigurationValue<Boolean> mIsWANStreamingDisabledByUserDefaultValue;
    private final ConfigurationValue<Long> mLiveAutoSeekUnavailableMillis;
    private final ConfigurationValue<Boolean> mLiveStreamSupported;
    private final ConfigurationValue<Integer> mMaxNumberOfUnexpectedBufferingAllowed;
    private final ConfigurationValue<Long> mMinRequiredTimeWithoutBufferingMillis;
    private final ConfigurationValue<Integer> mMinimumReadyToWatchForScreenSwitchSeconds;
    private final NetworkConnectionManager mNetworkConnectionManager;
    private final ConfigurationValue<Boolean> mNotifyAboutWANStreaming;
    private final ConfigurationValue<Long> mOfflineLicenseWarningHours;
    private final ConfigurationValue<Long> mPlaybackControlsFadeTimeoutMillis;
    private final ConfigurationValue<Long> mPlaybackDelayedThresholdMillis;
    private final ConfigurationValue<Integer> mPlaybackMonitorDefaultDelay;
    private final ConfigurationValue<Long> mPlaybackPersistenceUpdateIntervalMillis;
    private final ConfigurationValue<Integer> mPlaybackVideoQualityAggregatorShiftsToReport;
    private final PreferenceChangeListenerProxy mPreferenceChangeListenerProxy;
    private final ConfigurationValue<Boolean> mRestoreUIModeInEchoShowModeOnFireTablet;
    private final ConfigurationValue<Boolean> mShouldCheckMultiWindowOnPrepared;
    private final ConfigurationValue<Boolean> mShouldCheckPhoneStatusOnPlayback;
    private final ConfigurationValue<Boolean> mShouldDisplaySeasonOffers;
    private final ConfigurationValue<Boolean> mShouldEnforceSystemKeyEvents;
    private final ConfigurationValue<Boolean> mShouldHoldWakeLockDuringInitializationSpinner;
    private final ConfigurationValue<Boolean> mShouldPausePlaybackInOnPause;
    private final ConfigurationValue<Boolean> mShouldReportMultiWindowEventsToQos;
    private final ConfigurationValue<Boolean> mShouldSendSessionTokenOnHeartbeat;
    private final ConfigurationValue<Boolean> mShouldShowAudioFormatChangeDialog;
    private final ConfigurationValue<Boolean> mShouldShowOfflineDelayedStartDialog;
    private final ConfigurationValue<Boolean> mShouldShowStreamingNotification;

    @Deprecated
    private final ConfigurationValue<Boolean> mShouldSkipHdcpChecking;
    private final ConfigurationValue<Boolean> mShouldTeardownPlaybackOnScreenOffIntent;
    private final ConfigurationValue<Boolean> mShouldTeardownPlaybackOnSleep;
    private final ConfigurationValue<Boolean> mShouldTrustMultiWindowAPI;
    private final ConfigurationValue<Boolean> mShouldTrustMultiWindowAPI28;
    private final ConfigurationValue<Boolean> mShouldUseFeatureAsFallbackVMT;
    private final ConfigurationValue<Long> mSkipBackDistanceMillis;
    private final ConfigurationValue<Long> mSkipForwardDistanceMillis;
    private final ConfigurationValue<Long> mSpinnerDelayMillis;
    private final ConfigurationValue<Integer> mStreamSwitchCalculationTime;
    private final ConfigurationValue<Integer> mStreamingQuality;
    private final ConfigurationValue<Integer> mStreamingQualityDefaultForNonWanDevice;
    private final ConfigurationValue<Integer> mStreamingQualityDefaultForWanDevice;
    private final ConfigurationValue<Long> mSubtitleVisibleDuration;

    @Deprecated
    private final ConfigurationValue<Boolean> mSupportDefaultUhdDisplayMode;
    private final ConfigurationValue<Long> mTimeDataPollMillis;
    private final ConfigurationValue<Boolean> mTriggerPIPOnMultiasking;
    private final ConfigurationValue<Integer> mUpdateStreamPeriod;
    private final ConfigurationValue<Set<String>> mUpdateStreamUrlTypeSupportSet;
    private final ConfigurationValue<Boolean> mUseHighestQualityWhenOnWifi;
    private final ConfigurationValue<Boolean> mUseHighestQualityWhenOnWifiDefaultValue;
    private final ConfigurationValue<Set<String>> mValidLcidPrefixList;
    private final ConfigurationValue<Long> mVolumeSliderVisibleDuration;
    private final ConfigurationValue<Long> mWanPlaybackDelayBetweenBufferAheadThresholdCheckMillis;
    private final ConfigurationValue<Long> mWanPlaybackMaxBufferAheadTimeAllowedMillis;
    private final ConfigurationValue<Long> mWanPlaybackMinBufferAheadTimeAllowedMillis;
    private final ConfigurationValue<Boolean> playback_reactiveCacheTornDownTitle;
    private final ConfigurationValue<Boolean> playback_reactiveCacheTornDownTitleKFT;

    private enum DeviceFamily {
        FIRE_TV,
        FIRE_TABLET,
        ECHO,
        THIRD_PARTY
    }

    private static class SingletonHolder {
        private static final PlaybackConfig INSTANCE = new PlaybackConfig();

        private SingletonHolder() {
        }
    }

    public static final PlaybackConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    PlaybackConfig() {
        super("aiv.PlaybackConfig");
        this.mNetworkConnectionManager = NetworkConnectionManager.getInstance();
        this.mPreferenceChangeListenerProxy = new PreferenceChangeListenerProxy();
        ConfigType configType = ConfigType.SERVER;
        this.mMinimumReadyToWatchForScreenSwitchSeconds = newIntConfigValue("StreamSwitchRTWTime", 60, configType);
        this.mStreamSwitchCalculationTime = newIntConfigValue("StreamSwitchCalculationTime", 5, configType);
        this.mUpdateStreamPeriod = newIntConfigValue("updateStreamPeriod", 6, configType);
        this.mPlaybackMonitorDefaultDelay = newIntConfigValue("PLAYBACK_MONITOR_DEFAULT_DELAY", 1000, configType);
        this.mPlaybackPersistenceUpdateIntervalMillis = newLongConfigValue("playbackPersistenceUpdateInterval", 15000L, configType);
        TimeUnit timeUnit = TimeUnit.MINUTES;
        this.mWanPlaybackMaxBufferAheadTimeAllowedMillis = newLongConfigValue("wanPlaybackMaxBufferAheadTimeAllowedMillis", timeUnit.toMillis(5L), configType);
        this.mWanPlaybackMinBufferAheadTimeAllowedMillis = newLongConfigValue("wanPlaybackMinBufferAheadTimeAllowedMillis", timeUnit.toMillis(2L), configType);
        TimeUnit timeUnit2 = TimeUnit.SECONDS;
        this.mWanPlaybackDelayBetweenBufferAheadThresholdCheckMillis = newLongConfigValue("wanPlaybackDelayBetweenBufferAheadThresholdCheckMillis", timeUnit2.toMillis(3L), configType);
        this.mLiveAutoSeekUnavailableMillis = newLongConfigValue("Live_AutoSeekMillis", timeUnit2.toMillis(15L), configType);
        this.mTimeDataPollMillis = newLongConfigValue("PlaybackTimeDataPollMillis", 1000L, configType);
        this.mVolumeSliderVisibleDuration = newLongConfigValue("VolumeSliderVisibileDuration", timeUnit2.toMillis(5L), configType);
        this.mSubtitleVisibleDuration = newLongConfigValue("ClosedCaptionsVisibileDuration", timeUnit2.toMillis(5L), configType);
        this.mMaxNumberOfUnexpectedBufferingAllowed = newIntConfigValue("MaxNumberOfUnexpectedBufferingAllowed", 4, configType);
        this.mMinRequiredTimeWithoutBufferingMillis = newLongConfigValue("MinRequiredTimeWithoutBufferingMillis", timeUnit.toMillis(5L), configType);
        this.mBufferingThresholdMillis = newLongConfigValue("BufferingThresholdMillis", timeUnit2.toMillis(30L), configType);
        this.mPlaybackDelayedThresholdMillis = newLongConfigValue("PlaybackDelayedThresholdMillis", timeUnit2.toMillis(45L), configType);
        this.mSpinnerDelayMillis = newLongConfigValue("PlaybackSpinnerDelayMillis", 1200L, configType);
        this.mSkipBackDistanceMillis = newLongConfigValue("PlaybackSkipBackDistanceMillis", timeUnit2.toMillis(10L), configType);
        this.mSkipForwardDistanceMillis = newLongConfigValue("PlaybackSkipForwardDistanceMillis", timeUnit2.toMillis(10L), configType);
        this.mShouldTeardownPlaybackOnSleep = newBooleanConfigValue("ShouldTeardownPlaybackOnSleep", true, configType);
        this.mShouldTeardownPlaybackOnScreenOffIntent = newBooleanConfigValue("ShouldTeardownPlaybackOnScreenOffIntent", false, configType);
        this.mShouldDisplaySeasonOffers = newBooleanConfigValue("ShouldDisplaySeasonOffers", false, configType);
        this.mShouldEnforceSystemKeyEvents = newBooleanConfigValue("keyevents_shouldEnforceSystemKeyEvents", false, configType);
        this.mLiveStreamSupported = newBooleanConfigValue("PlaybackLiveStreamSupported", true, configType);
        this.mPlaybackControlsFadeTimeoutMillis = newLongConfigValue("PlaybackControlsFadeTimeout", timeUnit2.toMillis(5L), configType);
        this.mExitPlaybackAfterPauseMillis = newLongConfigValue("exitPlaybackAfterPauseMillis", TimeUnit.HOURS.toMillis(36L), configType);
        this.mHasSeenFirstTimeStreamingNotification = newBooleanConfigValue("hasSeenFirstTimeStreamingNotification", false, ConfigType.INTERNAL);
        this.mShouldHoldWakeLockDuringInitializationSpinner = newBooleanConfigValue("playback_ShouldHoldWakeLockDuringInitializationSpinner", false, configType);
        this.mStreamingQualityDefaultForWanDevice = newIntConfigValue("StreamingQualityWanDeviceDefault", StreamingConnectionSetting.GOOD.getValue(), configType);
        this.mStreamingQualityDefaultForNonWanDevice = newIntConfigValue("StreamingQualityNonWanDeviceDefault", StreamingConnectionSetting.BEST.getValue(), configType);
        ConfigType configType2 = ConfigType.ACCOUNT;
        this.mStreamingQuality = newIntConfigValue("streaming_quality", Integer.MIN_VALUE, configType2);
        this.mUseHighestQualityWhenOnWifi = newBooleanConfigValue("use_highest_quality_on_wifi", true, configType2);
        this.mUseHighestQualityWhenOnWifiDefaultValue = newBooleanConfigValue("use_highest_quality_on_wifi_default_value", true, configType);
        this.mIsWANStreamingDisabledByUser = newBooleanConfigValue("only_stream_on_wifi", false, configType2);
        this.mIsWANStreamingDisabledByUserDefaultValue = newBooleanConfigValue("only_stream_on_wifi_default_value", false, configType);
        this.mNotifyAboutWANStreaming = newBooleanConfigValue("notify_wan_streaming", true, configType2);
        this.mShouldCheckPhoneStatusOnPlayback = newBooleanConfigValue("playback_shouldCheckPhoneStatusOnPlayback", true, configType);
        this.mPlaybackVideoQualityAggregatorShiftsToReport = newIntConfigValue("playback_playbackVideoQualityAggregatorShiftsToReport", 20, configType);
        this.playback_reactiveCacheTornDownTitle = newBooleanConfigValue("playback_reactiveCacheTornDownTitle", false, configType);
        this.playback_reactiveCacheTornDownTitleKFT = newBooleanConfigValue("playback_reactiveCacheTornDownTitleKFT", true, configType);
        this.mShouldSkipHdcpChecking = newBooleanConfigValue("shouldSkipHdcpChecking", false, configType);
        this.mIsMismatchedUhdFrameRateSupported = newBooleanConfigValue("isMismatchedUhdFrameRateSupported", false, configType);
        this.mShouldShowStreamingNotification = newBooleanConfigValue("shouldShowStreamingNotification", true, configType);
        this.mShouldShowAudioFormatChangeDialog = newBooleanConfigValue("shouldShowAudioFormatChangeDialog", false, configType);
        this.mShouldShowOfflineDelayedStartDialog = newBooleanConfigValue("shouldShowOfflineDelayedStartDialog", false, configType);
        this.mSupportDefaultUhdDisplayMode = newBooleanConfigValue("supportDefaultUhdDisplayMode", false, configType);
        this.mOfflineLicenseWarningHours = newLongConfigValue("offlineLicenseWarningHours", 168L, configType);
        this.mDelayPreparingPlaybackFeaturesUntilOnStarted = newBooleanConfigValue("playback_delayPreparingPlaybackFeaturesUntilOnStarted_2", true, configType);
        this.mIsPIPEnabled = newBooleanConfigValue("playback_isPIPEnabled", true, configType);
        this.mIsMultiWindowSupported = newBooleanConfigValue("playback_isMultiWindowSupported", true, configType);
        this.mIsMultiWindowSupportedAndroid28 = newBooleanConfigValue("playback_isMultiWindowSupportedAndroid28", true, configType);
        this.mShouldTrustMultiWindowAPI = newBooleanConfigValue("playback_shouldTrustMultiWindowAPI", true, configType);
        this.mShouldTrustMultiWindowAPI28 = newBooleanConfigValue("playback_shouldTrustMultiWindowAPI28", false, configType);
        this.mShouldCheckMultiWindowOnPrepared = newBooleanConfigValue("playback_shouldCheckMultiWindowOnPrepared", true, configType);
        this.mTriggerPIPOnMultiasking = newBooleanConfigValue("playback_triggerPIPOnMultitasking", true, configType);
        this.mShouldReportMultiWindowEventsToQos = newBooleanConfigValue("playback_shouldReportMultiWindowEventsToQos", true, configType);
        this.mIsPlaybackBackgroundBlack = newBooleanConfigValue("playback_isBackgroundBlack", true, configType);
        this.mDeviceFamily = newEnumConfigValue("playback_deviceFamily", DeviceFamily.THIRD_PARTY, DeviceFamily.class, configType);
        this.mIsThirdPartyClientMediaCommandFeatureEnabled = newBooleanConfigValue("playback_isThirdPartyClientMediaCommandFeatureEnabled", true, configType);
        this.mShouldUseFeatureAsFallbackVMT = newBooleanConfigValue("shouldUseFeatureAsFallbackVMT", false, configType);
        this.mValidLcidPrefixList = newStringSetConfigValue("validLcidPrefixList", "amzn1.dv.lcid", ",", configType);
        this.mIsPlayingTrueDuringLoad = newBooleanConfigValue("playback_isPlayingTrueDuringLoad", true, configType);
        this.mIsEmbeddedPlaybackEnabled = newBooleanConfigValue("playback_isEmbeddedPlaybackEnabled", false, configType);
        this.mUpdateStreamUrlTypeSupportSet = newStringSetConfigValue("playback_upddateStreamUrlTypeSupportSet", String.format(Locale.US, "%s;%s", UrlType.CONTENT, UrlType.LIVE), ";", configType);
        this.mShouldSendSessionTokenOnHeartbeat = newBooleanConfigValue("playback_shouldSendSessionTokenOnHeartbeat", true, configType);
        this.mIsLiveCacheEnabled = newBooleanConfigValue("playback_isLiveCacheEnabled_2", true, configType);
        this.mIsImmersiveModeEnabled = newBooleanConfigValue("playback_isImmersiveModeEnabled", false, configType);
        this.mIsPlaybackActivityVideoSurfaceHotSwapEnabled = newBooleanConfigValue("playbackActivity_isVideoSurfaceHotSwapEnabled", false, configType);
        this.mIsPlaybackActivityBackgroundAudioPlaybackEnabled = newBooleanConfigValue("playbackActivity_isBackgroundAudioPlaybackEnabled", false, configType);
        this.mShouldPausePlaybackInOnPause = newBooleanConfigValue("playbackActivity_shouldPausePlaybackInOnPause", false, configType);
        this.mIsSecurePlaybackSurfaceEnabled = newBooleanConfigValue("playback_isSecurePlaybackSurfaceEnabled", true, configType);
        this.mIsPlaybackActivitySingleInstanceWeblabEnabled = newBooleanConfigValue("playbackActivity_isSingleInstanceWeblabEnabled", false, configType);
        this.mIsPlaybackActivitySingleInstanceEnabled = newBooleanConfigValue("playbackActivity_isSingleInstanceEnabled", false, configType);
        this.mForceDeskModeInEchoShowModeOnFireTablet = newBooleanConfigValue("playbackActivity_forceDeskModeInEchoShowModeOnFireTablet", true, configType);
        this.mRestoreUIModeInEchoShowModeOnFireTablet = newBooleanConfigValue("playbackActivity_restoreUIModeInEchoShowModeOnFireTablet", true, configType);
        this.mIsGestureSeekingWeblabEnabled = newBooleanConfigValue("playbackActivity_isGestureSeekingWeblabEnabled", false, configType);
        this.mIsDoubleTapToSeekEnabled = newBooleanConfigValue("playbackActivity_isDoubleTapToSeekEnabled", false, configType);
        this.mIsContinuousPlayPreinitEnabled = newBooleanConfigValue("playback_isContinuousPlayPreinitEnabled", false, configType);
        this.mFinishPlaybackActivityTimeoutEnabled = newBooleanConfigValue("playback_finishPlaybackActivityTimeoutEnabled", false, configType);
        this.mFinishPlaybackActivityTimeoutMins = newLongConfigValue("playback_finishPlaybackActivityTimeoutMins", 15L, configType);
    }

    public int getMinimumReadyToWatchForScreenSwitchSeconds() {
        return this.mMinimumReadyToWatchForScreenSwitchSeconds.getValue().intValue();
    }

    public int getStreamSwitchCalculationTimeSeconds() {
        return this.mStreamSwitchCalculationTime.getValue().intValue();
    }

    public int getPlaybackMonitorDefaultDelayMillis() {
        return this.mPlaybackMonitorDefaultDelay.getValue().intValue();
    }

    public int getMaxNumberOfUnexpectedBufferingAllowed() {
        return this.mMaxNumberOfUnexpectedBufferingAllowed.getValue().intValue();
    }

    public boolean isWANMobileVideoEnabled() {
        return !this.mIsWANStreamingDisabledByUser.getValue().booleanValue();
    }

    public int getPlaybackVideoQualityAggregatorShiftsToReport() {
        return this.mPlaybackVideoQualityAggregatorShiftsToReport.getValue().intValue();
    }

    @Nonnull
    public StreamingConnectionSetting getStreamingQuality() {
        int iIntValue;
        if (this.mNetworkConnectionManager.canDeviceSupportWAN()) {
            iIntValue = this.mStreamingQualityDefaultForWanDevice.getValue().intValue();
        } else {
            iIntValue = this.mStreamingQualityDefaultForNonWanDevice.getValue().intValue();
        }
        return getQualityOrDefault(this.mStreamingQuality.getValue().intValue(), getQualityOrDefault(iIntValue, STREAMING_CONNECTION_SETTING_FALLBACK));
    }

    public Optional<StreamingConnectionSetting> getStreamingQualityForNetwork(@Nonnull NetworkType networkType) {
        Preconditions.checkNotNull(networkType, "networkType");
        if (networkType == NetworkType.NO_CONNECTION) {
            return Optional.absent();
        }
        if (networkType == NetworkType.WAN) {
            if (isWANMobileVideoEnabled()) {
                return Optional.of(getStreamingQuality());
            }
            return Optional.absent();
        }
        if (this.mNetworkConnectionManager.canDeviceSupportWAN() && shouldUseHighestQualityOnWifi()) {
            return Optional.of(StreamingConnectionSetting.BEST);
        }
        return Optional.of(getStreamingQuality());
    }

    @Nonnull
    private StreamingConnectionSetting getQualityOrDefault(int i, @Nonnull StreamingConnectionSetting streamingConnectionSetting) {
        Preconditions.checkNotNull(streamingConnectionSetting, "defaultQuality");
        for (StreamingConnectionSetting streamingConnectionSetting2 : StreamingConnectionSetting.values()) {
            if (streamingConnectionSetting2.getValue() == i) {
                return streamingConnectionSetting2;
            }
        }
        return streamingConnectionSetting;
    }

    public boolean shouldUseHighestQualityOnWifi() {
        return this.mUseHighestQualityWhenOnWifi.getValue(this.mUseHighestQualityWhenOnWifiDefaultValue.getValue()).booleanValue();
    }

    private static class PreferenceChangeListenerProxy extends SetListenerProxy<Object> {
        private PreferenceChangeListenerProxy() {
        }
    }

    public boolean isFireTv() {
        return this.mDeviceFamily.getValue().equals(DeviceFamily.FIRE_TV);
    }

    public boolean isFireTablet() {
        return this.mDeviceFamily.getValue().equals(DeviceFamily.FIRE_TABLET);
    }

    public boolean isEcho() {
        return this.mDeviceFamily.getValue().equals(DeviceFamily.ECHO);
    }

    public boolean isAmazonDevice() {
        return isFireTv() || isFireTablet() || isEcho();
    }

    public boolean isPlaybackBackgroundBlack() {
        return this.mIsPlaybackBackgroundBlack.getValue().booleanValue();
    }

    public boolean isEmbeddedPlaybackEnabled() {
        return this.mIsEmbeddedPlaybackEnabled.getValue().booleanValue();
    }

    public boolean isLiveCacheEnabled() {
        return this.mIsLiveCacheEnabled.getValue().booleanValue();
    }

    public boolean isImmersiveModeEnabled() {
        return this.mIsImmersiveModeEnabled.getValue().booleanValue();
    }

    public boolean isSecurePlaybackSurfaceEnabled() {
        return this.mIsSecurePlaybackSurfaceEnabled.getValue().booleanValue();
    }

    public boolean isContinuousPlayPreinitEnabled() {
        return this.mIsContinuousPlayPreinitEnabled.getValue().booleanValue();
    }
}
