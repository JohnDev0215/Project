package com.amazon.avod.content.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.throughput.BandwidthStats;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import kotlin.time.DurationKt;

/* JADX INFO: loaded from: classes2.dex */
public class LiveStreamingPlaybackConfig extends MediaConfigBase {
    private static final Integer DEFAULT_LIVE_STREAMING_LOOK_AHEAD_BUFFER_IN_SECONDS = 60;
    private static final Integer DEFAULT_LIVE_STREAMING_MIN_TIME_WINDOW_DURATION_IN_SECONDS = 30;
    private final ConfigurationValue<Integer> mBaselineBitrateCap;
    private final ConfigurationValue<Integer> mDualRequestsHeuristicsMinFutureBufferSizeSeconds;
    private final TimeConfigurationValue mDynamicFrontMarginStep;
    private final ConfigurationValue<Boolean> mEnableDualRequest;
    private final ConfigurationValue<Boolean> mIsHeuristicsCallbackChangeWebLabEnabled;
    private final ConfigurationValue<Boolean> mIsUtcTranslationEnabled;
    private final ConfigurationValue<Integer> mLiveFrontGuardForRefresh;
    private final ConfigurationValue<Integer> mLiveStreamingLookAheadBufferSize;
    private final ConfigurationValue<Integer> mLiveStreamingMinTimeWindowDuration;
    private final TimeConfigurationValue mMaxDynamicFrontMargin;
    private final ConfigurationValue<Integer> mMaxNumberOfCdnErrorAllowedForManifestRefresh;
    private final ConfigurationValue<Boolean> mShouldRefreshManifestInCdnError;

    private static final class SingletonHolder {
        private static final LiveStreamingPlaybackConfig INSTANCE = new LiveStreamingPlaybackConfig();

        private SingletonHolder() {
        }
    }

    private LiveStreamingPlaybackConfig() {
        this.mBaselineBitrateCap = newIntConfigValue("playback_liveStreamingBaselineBitrateCap", DurationKt.NANOS_IN_MILLIS);
        this.mEnableDualRequest = newBooleanConfigValue("playback_liveStreamingEnableDualRequest", true);
        this.mDualRequestsHeuristicsMinFutureBufferSizeSeconds = newIntConfigValue("playback_liveStreamingDualRequestsHeuristicsMinFutureBufferSizeSeconds", 11);
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(2000L);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.mMaxDynamicFrontMargin = newTimeConfigurationValue("playback_liveMaxDynamicFrontMargin", timeSpanFromMilliseconds, timeUnit);
        this.mDynamicFrontMarginStep = newTimeConfigurationValue("playback_liveDynamicFrontMarginStep", TimeSpan.fromMilliseconds(25L), timeUnit);
        this.mLiveFrontGuardForRefresh = newIntConfigValue("playback_liveFrontMarginForManifestRefreshMillis", 20000);
        this.mIsUtcTranslationEnabled = newBooleanConfigValue("playback_liveUtcTranslationEnabled", true);
        this.mIsHeuristicsCallbackChangeWebLabEnabled = newBooleanConfigValue("playback_isHeuristicsCallbackChangeWebLabEnabled", true);
        this.mLiveStreamingLookAheadBufferSize = newIntConfigValue("playback_liveStreamingLookAheadBufferSizeInSeconds", DEFAULT_LIVE_STREAMING_LOOK_AHEAD_BUFFER_IN_SECONDS.intValue());
        this.mLiveStreamingMinTimeWindowDuration = newIntConfigValue("playback_liveStreamingMinTimeWindowDurationInSeconds", DEFAULT_LIVE_STREAMING_MIN_TIME_WINDOW_DURATION_IN_SECONDS.intValue());
        this.mShouldRefreshManifestInCdnError = newBooleanConfigValue("playback_shouldRefreshManifestInCdnError", true);
        this.mMaxNumberOfCdnErrorAllowedForManifestRefresh = newIntConfigValue("playback_maxNumberOfCdnErrorAllowedForManifestRefresh", 3);
    }

    public static LiveStreamingPlaybackConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public int getLiveStreamingFutureBufferSizeSeconds(@Nullable Heuristics heuristics) {
        if (heuristics == null || heuristics.getHeuristicsPlaybackConfig() == null) {
            return this.mLiveStreamingLookAheadBufferSize.getValue().intValue();
        }
        if (!BandwidthStats.isSupported()) {
            return Math.max(this.mDualRequestsHeuristicsMinFutureBufferSizeSeconds.getValue().intValue(), heuristics.getHeuristicsPlaybackConfig().getDelayBehindLiveHeadSeconds());
        }
        return heuristics.getHeuristicsPlaybackConfig().getDelayBehindLiveHeadSeconds();
    }

    public int getLiveStreamingMinTimeWindowDurationSeconds() {
        return this.mLiveStreamingMinTimeWindowDuration.getValue().intValue();
    }

    public int getLiveFrontMarginForRefreshDurationMillis() {
        return this.mLiveFrontGuardForRefresh.getValue().intValue();
    }

    public int getLiveStreamingBaselineBitrateCap() {
        return this.mBaselineBitrateCap.getValue().intValue();
    }

    public Boolean getEnableLiveStreamingDualRequest() {
        return this.mEnableDualRequest.getValue();
    }

    public int getDualRequestsHeuristicsMinFutureBufferSizeSeconds() {
        return this.mDualRequestsHeuristicsMinFutureBufferSizeSeconds.getValue().intValue();
    }

    public TimeSpan getMaxDynamicFrontMargin() {
        return this.mMaxDynamicFrontMargin.getValue();
    }

    public TimeSpan getDynamicFrontMarginStep() {
        return this.mDynamicFrontMarginStep.getValue();
    }

    public boolean isUtcTranslationEnabled() {
        return this.mIsUtcTranslationEnabled.getValue().booleanValue();
    }

    public boolean isHeuristicsCallbackChangeWebLabEnabled() {
        return this.mIsHeuristicsCallbackChangeWebLabEnabled.getValue().booleanValue();
    }

    public boolean shouldRefreshManifestInCdnError() {
        return this.mShouldRefreshManifestInCdnError.getValue().booleanValue();
    }

    public int getMaxNumberOfCdnErrorAllowedForManifestRefresh() {
        return this.mMaxNumberOfCdnErrorAllowedForManifestRefresh.getValue().intValue();
    }
}
