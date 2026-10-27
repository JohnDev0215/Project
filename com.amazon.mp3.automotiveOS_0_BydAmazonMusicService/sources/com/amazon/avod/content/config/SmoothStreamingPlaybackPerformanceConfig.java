package com.amazon.avod.content.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamingPlaybackPerformanceConfig extends MediaConfigBase {
    public static final SmoothStreamingPlaybackPerformanceConfig INSTANCE = new SmoothStreamingPlaybackPerformanceConfig();
    private final ConfigurationValue<Integer> mCriticalPerfDownshiftCount;
    private final ConfigurationValue<Integer> mLowEndPerfPenalty;
    private final ConfigurationValue<Integer> mMajorPerfDownshiftCount;
    private final ConfigurationValue<Integer> mPerfUpshiftCount;
    private final TimeConfigurationValue mSustainablePerfUpshift;
    private final TimeConfigurationValue mTrimBufferOnPerf;

    private SmoothStreamingPlaybackPerformanceConfig() {
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(4000L);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.mTrimBufferOnPerf = newTimeConfigurationValue("playback_trimBufferOnPerfMillis", timeSpanFromMilliseconds, timeUnit);
        this.mSustainablePerfUpshift = newTimeConfigurationValue("playback_sustainablePerfUpshiftMillis", TimeSpan.fromMilliseconds(20000L), timeUnit);
        this.mMajorPerfDownshiftCount = newIntConfigValue("playback_majorPerfDownshiftCount", 1);
        this.mCriticalPerfDownshiftCount = newIntConfigValue("playback_criticalPerfDownshiftCount", 2);
        this.mPerfUpshiftCount = newIntConfigValue("playback_perfUpshiftCount", 1);
        this.mLowEndPerfPenalty = newIntConfigValue("playback_perfLowEndPerfProfileStartPenalty", 6);
    }

    public TimeSpan getTrimBufferOnPerf() {
        return this.mTrimBufferOnPerf.getValue();
    }

    public TimeSpan getSustainablePerfUpshift() {
        return this.mSustainablePerfUpshift.getValue();
    }

    public int getMajorPerfDownshiftCount() {
        return this.mMajorPerfDownshiftCount.getValue().intValue();
    }

    public int getCriticalPerfDownshiftCount() {
        return this.mCriticalPerfDownshiftCount.getValue().intValue();
    }

    public int getPerfUpshiftCount() {
        return this.mPerfUpshiftCount.getValue().intValue();
    }

    public int getLowEndPerfPenalty() {
        return this.mLowEndPerfPenalty.getValue().intValue();
    }
}
