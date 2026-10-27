package com.amazon.avod.playback.player;

import amazon.android.config.ConfigurationValue;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackPerformanceConfig extends MediaConfigBase {
    public static final PlaybackPerformanceConfig INSTANCE = new PlaybackPerformanceConfig();
    private final ConfigurationValue<Float> mCodecDropCoefficient;
    private final ConfigurationValue<Float> mCodecTimeCoefficient;
    private final ConfigurationValue<Float> mCriticalPerfThreshold;
    private final ConfigurationValue<Float> mJitterNumCoefficient;
    private final ConfigurationValue<Float> mMajorPerfThreshold;
    private final ConfigurationValue<Float> mMinorPerfThreshold;
    private final TimeConfigurationValue mPerformanceAnalysisWindow;
    private final TimeConfigurationValue mPerformanceSamplingFrequency;
    private final ConfigurationValue<Float> mRenderDropCoefficient;
    private final ConfigurationValue<Float> mRenderTimeCoefficient;
    private final ConfigurationValue<Float> mSinglePerfSampleThreshold;
    private final ConfigurationValue<Float> mSourceDropCoefficient;
    private final ConfigurationValue<Float> mSourceTimeCoefficient;

    private PlaybackPerformanceConfig() {
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.mPerformanceAnalysisWindow = newTimeConfigurationValue("playback_performanceAnalysisWindowInMillis", timeSpanFromMilliseconds, timeUnit);
        this.mPerformanceSamplingFrequency = newTimeConfigurationValue("playback_performanceSamplingFrequencyInMillis", TimeSpan.fromMilliseconds(1000L), timeUnit);
        this.mSourceDropCoefficient = newFloatConfigValue("playback_sourceDropCoefficient", 3.0f);
        this.mCodecDropCoefficient = newFloatConfigValue("playback_codecDropCoefficient", 2.0f);
        this.mRenderDropCoefficient = newFloatConfigValue("playback_renderDropCoefficient", 2.0f);
        this.mSourceTimeCoefficient = newFloatConfigValue("playback_sourceTimeCoefficient", 1.5f);
        this.mCodecTimeCoefficient = newFloatConfigValue("playback_codecTimeCoefficient", 1.0f);
        this.mRenderTimeCoefficient = newFloatConfigValue("playback_renderTimeCoefficient", 1.0f);
        this.mJitterNumCoefficient = newFloatConfigValue("playback_jitterNumCoefficient", 2.0f);
        this.mSinglePerfSampleThreshold = newFloatConfigValue("playback_singlePerfSampleThreshold", 25.0f);
        this.mCriticalPerfThreshold = newFloatConfigValue("playback_criticalPerfThreshold", 75.0f);
        this.mMajorPerfThreshold = newFloatConfigValue("playback_majorPerfThreshold", 50.0f);
        this.mMinorPerfThreshold = newFloatConfigValue("playback_minorPerfThreshold", 25.0f);
    }

    public TimeSpan getPerformanceAnalysisWindow() {
        return this.mPerformanceAnalysisWindow.getValue();
    }

    public TimeSpan getPerformanceSamplingFrequency() {
        return this.mPerformanceSamplingFrequency.getValue();
    }

    public float getSourceDropCoefficient() {
        return this.mSourceDropCoefficient.getValue().floatValue();
    }

    public float getCodecDropCoefficient() {
        return this.mCodecDropCoefficient.getValue().floatValue();
    }

    public float getRenderDropCoefficient() {
        return this.mRenderDropCoefficient.getValue().floatValue();
    }

    public float getSourceTimeCoefficient() {
        return this.mSourceTimeCoefficient.getValue().floatValue();
    }

    public float getCodecTimeCoefficient() {
        return this.mCodecTimeCoefficient.getValue().floatValue();
    }

    public float getRenderTimeCoefficient() {
        return this.mRenderTimeCoefficient.getValue().floatValue();
    }

    public float getJitterNumCoefficient() {
        return this.mJitterNumCoefficient.getValue().floatValue();
    }

    public float getSinglePerfSampleThreshold() {
        return this.mSinglePerfSampleThreshold.getValue().floatValue();
    }

    public float getCriticalPerfThreshold() {
        return this.mCriticalPerfThreshold.getValue().floatValue();
    }

    public float getMajorPerfThreshold() {
        return this.mMajorPerfThreshold.getValue().floatValue();
    }

    public float getMinorPerfThreshold() {
        return this.mMinorPerfThreshold.getValue().floatValue();
    }
}
