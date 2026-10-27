package com.amazon.avod.playback.session;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.util.SlidingWindowConfig;
import com.amazon.avod.media.playback.util.SlidingWindowEventTracker;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playbackclient.config.PlaybackConfig;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class PlaybackSessionFailureManagerPolicy {
    private final PlaybackSessionFailureManagerConfig mConfig;
    private final SlidingWindowEventTracker mExceptionTracker;
    private final Object mExceptionWindowMutex;
    private final PlaybackConfig mPlaybackConfig;

    private static class SingletonHolder {
        private static final PlaybackSessionFailureManagerPolicy INSTANCE = new PlaybackSessionFailureManagerPolicy(PlaybackSessionFailureManagerConfig.getInstance());

        private SingletonHolder() {
        }
    }

    @Nonnull
    public static PlaybackSessionFailureManagerPolicy getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private PlaybackSessionFailureManagerPolicy(@Nonnull PlaybackSessionFailureManagerConfig playbackSessionFailureManagerConfig) {
        this(playbackSessionFailureManagerConfig, new SlidingWindowEventTracker(playbackSessionFailureManagerConfig), PlaybackConfig.getInstance());
    }

    PlaybackSessionFailureManagerPolicy(@Nonnull PlaybackSessionFailureManagerConfig playbackSessionFailureManagerConfig, @Nonnull SlidingWindowEventTracker slidingWindowEventTracker, @Nonnull PlaybackConfig playbackConfig) {
        this.mExceptionWindowMutex = new Object();
        this.mConfig = (PlaybackSessionFailureManagerConfig) Preconditions.checkNotNull(playbackSessionFailureManagerConfig, "playbackSessionFailureManagerConfig");
        this.mExceptionTracker = (SlidingWindowEventTracker) Preconditions.checkNotNull(slidingWindowEventTracker, "slidingWindowEventTracker");
        this.mPlaybackConfig = (PlaybackConfig) Preconditions.checkNotNull(playbackConfig, "playbackConfig");
    }

    public void reportException() {
        synchronized (this.mExceptionWindowMutex) {
            TimeSpan timeSpanNow = TimeSpan.now();
            this.mExceptionTracker.recordEvent(timeSpanNow);
            DLog.warnf("PlaybackSessionFailureManagerPolicy recorded exception eventTimestamp: %s currentExceptionCount: %s exceptionWindowLength: %s exceptionWindowThreshold: %s", timeSpanNow, Integer.valueOf(this.mExceptionTracker.getEventCount()), this.mConfig.getSlidingWindowLength(), Integer.valueOf(this.mConfig.getSlidingWindowThreshold()));
        }
    }

    public boolean shouldExecuteRecoveryAction() {
        boolean zIsEventCountGreaterThanOrEqualToThreshold;
        synchronized (this.mExceptionWindowMutex) {
            zIsEventCountGreaterThanOrEqualToThreshold = this.mExceptionTracker.isEventCountGreaterThanOrEqualToThreshold();
        }
        return zIsEventCountGreaterThanOrEqualToThreshold;
    }

    public boolean shouldHandle(@Nonnull MediaException mediaException) {
        Preconditions.checkNotNull(mediaException, "mediaException");
        if (!this.mConfig.getPlaybackSessionFailureManagerEnabled() || this.mPlaybackConfig.isAmazonDevice()) {
            return false;
        }
        return this.mConfig.getHandledUnrecoverableErrorSet().contains(((Enum) mediaException.getErrorCode()).name());
    }

    @Nonnull
    public String getPolicyNote() {
        return String.format(Locale.US, "windowLength: %s windowThreshold: %s errorSet: %s", this.mConfig.getSlidingWindowLength(), Integer.valueOf(this.mConfig.getSlidingWindowThreshold()), this.mConfig.getHandledUnrecoverableErrorSet());
    }

    static class PlaybackSessionFailureManagerConfig extends MediaConfigBase implements SlidingWindowConfig {
        private static final PlaybackSessionFailureManagerConfig INSTANCE = new PlaybackSessionFailureManagerConfig();
        private final ConfigurationValue<Set<String>> mHandledUnrecoverableErrorSet;
        private final ConfigurationValue<Boolean> mPlaybackSessionFailureManagerEnabled;
        private final ConfigurationValue<Integer> mPlaybackSessionFailureManagerThreshold;
        private final TimeConfigurationValue mPlaybackSessionFailureManagerWindowLength;

        @Nonnull
        public static PlaybackSessionFailureManagerConfig getInstance() {
            return INSTANCE;
        }

        private PlaybackSessionFailureManagerConfig() {
            String[] strArr = {PlaybackException.PlaybackError.RENDERER_DECRYPTION_FAILURE.name(), PlaybackException.PlaybackError.RENDERER_DECRYPTION_FAILURE_MISSING_KEY.name(), PlaybackException.PlaybackError.RENDERER_DECRYPTION_FAILURE_INSUFFICIENT_OUTPUT_PROTECTION.name(), PlaybackException.PlaybackError.RENDERER_DECRYPTION_FAILURE_UNSUPPORTED_OPERATION.name(), PlaybackException.PlaybackError.RENDERER_INITIALIZE_FAILED_CODEC_CONFIG.name(), PlaybackException.PlaybackError.RENDERER_INITIALIZE_FAILED.name(), PlaybackException.PlaybackError.INTERNAL_FATAL_ERROR.name(), PlaybackException.PlaybackError.SAMPLE_ADAPTION_FAILURE.name(), LicenseError.GENERATE_CHALLENGE_FAILURE.name(), LicenseError.PROCESS_RESPONSE_FAILURE.name(), LicenseError.AIV_LICENSE_SERVICE_CALL_FAILURE.name()};
            this.mPlaybackSessionFailureManagerEnabled = newBooleanConfigValue("playbackSessionFailureManagerEnabled", true);
            this.mHandledUnrecoverableErrorSet = newSemicolonDelimitedStringSetConfigurationValue("handledUnrecoverableErrorSet", strArr);
            this.mPlaybackSessionFailureManagerWindowLength = newTimeConfigurationValue("playbackSessionFailureManagerWindowLength", TimeSpan.fromMinutes(15L), TimeUnit.MINUTES);
            this.mPlaybackSessionFailureManagerThreshold = newIntConfigValue("playbackSessionFailureManagerThreshold", 3);
        }

        public boolean getPlaybackSessionFailureManagerEnabled() {
            return this.mPlaybackSessionFailureManagerEnabled.getValue().booleanValue();
        }

        @Nonnull
        public Set<String> getHandledUnrecoverableErrorSet() {
            return this.mHandledUnrecoverableErrorSet.getValue();
        }

        @Override // com.amazon.avod.media.playback.util.SlidingWindowConfig
        @Nonnull
        public TimeSpan getSlidingWindowLength() {
            return this.mPlaybackSessionFailureManagerWindowLength.getValue();
        }

        @Override // com.amazon.avod.media.playback.util.SlidingWindowConfig
        public int getSlidingWindowThreshold() {
            return this.mPlaybackSessionFailureManagerThreshold.getValue().intValue();
        }
    }
}
