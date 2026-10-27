package com.amazon.avod.media.playback.support;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.util.SlidingWindowConfig;
import com.amazon.avod.media.playback.util.SlidingWindowEventTracker;
import com.amazon.avod.playbackclient.config.PlaybackConfig;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class AutoEvalCanaryPolicy {
    private final AutoEvalCanaryConfig mConfig;
    private final SlidingWindowEventTracker mExceptionTracker;
    private final Set<String> mHandledErrorSet;
    private final boolean mIsAmazonDevice;
    private final boolean mIsAutoEvalCanaryEnabled;
    private final boolean mIsAutoEvalCanaryErrorWildcardEnabled;
    private final PlaybackConfig mPlaybackConfig;
    private final TimeSpan mSlidingWindowLength;
    private final int mSlidingWindowThreshold;
    private final SlidingWindowEventTracker mSuccessTracker;
    private final Object mWindowMutex;

    private static class SingletonHolder {
        private static final AutoEvalCanaryPolicy INSTANCE = new AutoEvalCanaryPolicy(AutoEvalCanaryConfig.getInstance());

        private SingletonHolder() {
        }
    }

    @Nonnull
    public static AutoEvalCanaryPolicy getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private AutoEvalCanaryPolicy(@Nonnull AutoEvalCanaryConfig autoEvalCanaryConfig) {
        this(autoEvalCanaryConfig, new SlidingWindowEventTracker(autoEvalCanaryConfig), new SlidingWindowEventTracker(autoEvalCanaryConfig), PlaybackConfig.getInstance());
    }

    AutoEvalCanaryPolicy(@Nonnull AutoEvalCanaryConfig autoEvalCanaryConfig, @Nonnull SlidingWindowEventTracker slidingWindowEventTracker, @Nonnull SlidingWindowEventTracker slidingWindowEventTracker2, @Nonnull PlaybackConfig playbackConfig) {
        this.mWindowMutex = new Object();
        AutoEvalCanaryConfig autoEvalCanaryConfig2 = (AutoEvalCanaryConfig) Preconditions.checkNotNull(autoEvalCanaryConfig, "autoEvalCanaryConfig");
        this.mConfig = autoEvalCanaryConfig2;
        this.mSuccessTracker = (SlidingWindowEventTracker) Preconditions.checkNotNull(slidingWindowEventTracker2, "successTracker");
        this.mExceptionTracker = (SlidingWindowEventTracker) Preconditions.checkNotNull(slidingWindowEventTracker, "exceptionTracker");
        PlaybackConfig playbackConfig2 = (PlaybackConfig) Preconditions.checkNotNull(playbackConfig, "playbackConfig");
        this.mPlaybackConfig = playbackConfig2;
        this.mIsAutoEvalCanaryEnabled = autoEvalCanaryConfig2.isAutoEvalCanaryEnabled();
        this.mIsAmazonDevice = playbackConfig2.isAmazonDevice();
        this.mIsAutoEvalCanaryErrorWildcardEnabled = autoEvalCanaryConfig2.isAutoEvalCanaryErrorWildcardEnabled();
        this.mSlidingWindowLength = autoEvalCanaryConfig2.getSlidingWindowLength();
        this.mSlidingWindowThreshold = autoEvalCanaryConfig2.getSlidingWindowThreshold();
        this.mHandledErrorSet = autoEvalCanaryConfig2.getAutoEvalCanaryHandledErrorSet();
    }

    public void recordEvent(@Nullable MediaException mediaException) {
        synchronized (this.mWindowMutex) {
            boolean z = mediaException == null;
            SlidingWindowEventTracker slidingWindowEventTracker = z ? this.mSuccessTracker : this.mExceptionTracker;
            TimeSpan timeSpanNow = TimeSpan.now();
            slidingWindowEventTracker.recordEvent(timeSpanNow);
            Object[] objArr = new Object[5];
            objArr[0] = z ? "SUCCESS" : "FAILURE";
            objArr[1] = timeSpanNow;
            objArr[2] = Integer.valueOf(this.mExceptionTracker.getEventCount());
            objArr[3] = this.mSlidingWindowLength;
            objArr[4] = Integer.valueOf(this.mSlidingWindowThreshold);
            DLog.warnf("AutoEvalCanaryPolicy recorded %s eventeventTimestamp: %s currentExceptionCount: %s exceptionWindowLength: %s exceptionWindowThreshold: %s", objArr);
        }
    }

    public boolean shouldExecuteAction(@Nullable MediaException mediaException) {
        boolean zIsEventCountGreaterThanOrEqualToThreshold;
        synchronized (this.mWindowMutex) {
            zIsEventCountGreaterThanOrEqualToThreshold = (mediaException == null ? this.mSuccessTracker : this.mExceptionTracker).isEventCountGreaterThanOrEqualToThreshold();
        }
        return zIsEventCountGreaterThanOrEqualToThreshold;
    }

    public boolean shouldHandle(@Nullable MediaException mediaException) {
        if (!this.mIsAutoEvalCanaryEnabled || this.mIsAmazonDevice) {
            return false;
        }
        if (mediaException == null || this.mIsAutoEvalCanaryErrorWildcardEnabled) {
            return true;
        }
        return this.mHandledErrorSet.contains(((Enum) mediaException.getErrorCode()).name());
    }

    @Nonnull
    public String getPolicyNote() {
        return String.format(Locale.US, "windowLength: %s windowThreshold: %s errorSet: %s errorWildCardEnabled: %s", this.mSlidingWindowLength, Integer.valueOf(this.mSlidingWindowThreshold), this.mHandledErrorSet, Boolean.valueOf(this.mIsAutoEvalCanaryErrorWildcardEnabled));
    }

    static class AutoEvalCanaryConfig extends MediaConfigBase implements SlidingWindowConfig {
        private static final AutoEvalCanaryConfig INSTANCE = new AutoEvalCanaryConfig();
        private final ConfigurationValue<Boolean> mAutoEvalCanaryEnabled = newBooleanConfigValue("playback_autoEvalCanaryEnabled", true);
        private final ConfigurationValue<Boolean> mAutoEvalCanaryErrorWildcardEnabled = newBooleanConfigValue("playback_autoEvalCanaryErrorWildcardEnabled", true);
        private final ConfigurationValue<Set<String>> mAutoEvalCanaryHandledErrorSet = newSemicolonDelimitedStringSetConfigurationValue("playback_autoEvalCanaryHandledErrorSet", new String[0]);
        private final TimeConfigurationValue mAutoEvalCanaryWindowLength = newTimeConfigurationValue("playback_autoEvalCanaryWindowLength", TimeSpan.fromMinutes(60), TimeUnit.MINUTES);
        private final ConfigurationValue<Integer> mAutoEvalCanaryThreshold = newIntConfigValue("playback_autoEvalCanaryThreshold", 1);

        @Nonnull
        public static AutoEvalCanaryConfig getInstance() {
            return INSTANCE;
        }

        private AutoEvalCanaryConfig() {
        }

        public boolean isAutoEvalCanaryEnabled() {
            return this.mAutoEvalCanaryEnabled.getValue().booleanValue();
        }

        public boolean isAutoEvalCanaryErrorWildcardEnabled() {
            return this.mAutoEvalCanaryErrorWildcardEnabled.getValue().booleanValue();
        }

        @Nonnull
        public Set<String> getAutoEvalCanaryHandledErrorSet() {
            return this.mAutoEvalCanaryHandledErrorSet.getValue();
        }

        @Override // com.amazon.avod.media.playback.util.SlidingWindowConfig
        @Nonnull
        public TimeSpan getSlidingWindowLength() {
            return this.mAutoEvalCanaryWindowLength.getValue();
        }

        @Override // com.amazon.avod.media.playback.util.SlidingWindowConfig
        public int getSlidingWindowThreshold() {
            return this.mAutoEvalCanaryThreshold.getValue().intValue();
        }
    }
}
