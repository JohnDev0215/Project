package com.amazon.avod.playback;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.media.playback.support.RendererPerformanceData;
import com.amazon.avod.media.playback.util.SlidingWindowEventTracker;
import com.amazon.avod.playback.event.playback.StatusEvent;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.qos.internal.metrics.QoSMetric;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AutoEvalPlayerPerformanceEvaluator {
    private final AutoEvalPlayerPerformanceEvaluatorConfig mConfig;
    private final float mFrameDropDetectionPercentage;
    private final SlidingWindowEventTracker mFrameDropTracker;
    private final boolean mIsAutoEvalPlayerPerformanceEvaluatorEnabled;
    private TimeSpan mLastEventTimeStamp = null;
    private int mLastTotalFrameDropCount = 0;
    private final PlaybackEventTransport mPlaybackEventBus;
    private PlaybackSessionContext mPlaybackSessionContext;
    private PlaybackSessionResources mPlaybackSessionResources;

    public AutoEvalPlayerPerformanceEvaluator(@Nonnull PlaybackEventTransport playbackEventTransport) {
        this.mPlaybackEventBus = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "playbackEventBus");
        AutoEvalPlayerPerformanceEvaluatorConfig autoEvalPlayerPerformanceEvaluatorConfig = AutoEvalPlayerPerformanceEvaluatorConfig.INSTANCE;
        this.mConfig = autoEvalPlayerPerformanceEvaluatorConfig;
        this.mFrameDropTracker = new SlidingWindowEventTracker(autoEvalPlayerPerformanceEvaluatorConfig.mFrameDropWindowLength.getValue(), ((Integer) autoEvalPlayerPerformanceEvaluatorConfig.mFrameDropWindowThreshold.getValue()).intValue());
        this.mFrameDropDetectionPercentage = ((Float) autoEvalPlayerPerformanceEvaluatorConfig.mFrameDropDetectionPercentage.getValue()).floatValue();
        this.mIsAutoEvalPlayerPerformanceEvaluatorEnabled = ((Boolean) autoEvalPlayerPerformanceEvaluatorConfig.mIsAutoEvalPlayerPerformanceEvaluatorEnabled.getValue()).booleanValue();
    }

    public void initialize(@Nonnull PlaybackSessionContext playbackSessionContext, @Nonnull PlaybackSessionResources playbackSessionResources) {
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
        this.mPlaybackSessionResources = (PlaybackSessionResources) Preconditions.checkNotNull(playbackSessionResources, "playbackSessionResources");
    }

    private boolean shouldEvaluate() {
        if (this.mIsAutoEvalPlayerPerformanceEvaluatorEnabled && this.mPlaybackSessionResources.getRendererSchemeController().isAutoSchemeResolutionEnabled() && this.mPlaybackSessionResources.getRendererSchemeController().isHardwareAcceleratedSchemeAvailable(false)) {
            return this.mPlaybackSessionContext.getRendererSchemeType() == RendererSchemeType.MEDIACODEC_MEDIADRM || this.mPlaybackSessionContext.getRendererSchemeType() == RendererSchemeType.MEDIACODEC_SOFTWAREPLAYREADY;
        }
        return false;
    }

    public void processPerformanceData(@Nonnull StatusEvent statusEvent) {
        PlaybackSessionContext playbackSessionContext;
        Preconditions.checkNotNull(statusEvent, "statusEvent");
        if (shouldEvaluate()) {
            TimeSpan eventTimeStamp = statusEvent.getEventTimeStamp();
            RendererPerformanceData rendererPerformanceData = statusEvent.getRendererPerformanceData();
            if (this.mLastEventTimeStamp != null && (playbackSessionContext = this.mPlaybackSessionContext) != null && playbackSessionContext.getContentSession() != null) {
                long totalMilliseconds = eventTimeStamp.getTotalMilliseconds() - this.mLastEventTimeStamp.getTotalMilliseconds();
                StreamIndex videoStream = this.mPlaybackSessionContext.getContentSession().getContext().getManifest().getVideoStream();
                if (videoStream != null) {
                    evaluateForFrameDropAnomaly(videoStream.getMaxFrameRate(), totalMilliseconds, rendererPerformanceData);
                    checkFrameDropThreshold(statusEvent);
                }
            }
            this.mLastEventTimeStamp = eventTimeStamp;
            this.mLastTotalFrameDropCount = rendererPerformanceData.getTotalRenderDropNum();
        }
    }

    public void onPlaybackStop() {
        this.mLastEventTimeStamp = null;
        this.mLastTotalFrameDropCount = 0;
    }

    private void evaluateForFrameDropAnomaly(float f, long j, @Nonnull RendererPerformanceData rendererPerformanceData) {
        if (j <= 0 || f <= 0.0f) {
            return;
        }
        float f2 = (j / 1000.0f) * f;
        if (Math.signum(f2) == 0.0f) {
            return;
        }
        int totalRenderDropNum = rendererPerformanceData.getTotalRenderDropNum() - this.mLastTotalFrameDropCount;
        float f3 = (totalRenderDropNum * 100) / f2;
        if (f3 >= this.mFrameDropDetectionPercentage) {
            DLog.warnf(String.format(Locale.US, "AutoEvalPlayerPerformanceEvaluator detected frame drop anomaly MaxContentFrameRate: %s PercentFramesDropped: %s FrameDropDetectionPercentage: %s Dropped/Processed: %s/%s LastDecodedBitrate: %s", Float.valueOf(f), Float.valueOf(f3), Float.valueOf(this.mFrameDropDetectionPercentage), Integer.valueOf(totalRenderDropNum), Float.valueOf(f2), Integer.valueOf(rendererPerformanceData.getLastDecodedSampleBitrate())));
            this.mFrameDropTracker.recordEvent(TimeSpan.now());
        }
    }

    private void checkFrameDropThreshold(@Nonnull StatusEvent statusEvent) {
        if (this.mFrameDropTracker.isEventCountGreaterThanOrEqualToThreshold()) {
            String str = String.format(Locale.US, "AutoEvalPlayerPerformanceEvaluator frame drop threshold breached windowLength: %s windowThreshold: %s FrameDropDetectionPercentage: %s", this.mConfig.mFrameDropWindowLength.getValue(), this.mConfig.mFrameDropWindowThreshold.getValue(), Float.valueOf(this.mFrameDropDetectionPercentage));
            DLog.warnf(str);
            this.mPlaybackEventBus.postEvent(PlaybackRestartEvent.newAutoEvalPoorPlayerPerformanceRestartEvent(TimeUnit.NANOSECONDS.toMillis(statusEvent.getLastRenderedTimestampInNanoseconds()), str));
            this.mPlaybackSessionResources.getEventReporter().getPlaybackReporter().reportMetric(QoSMetric.Information.toString(), "PlayerAutoEvalPoorPlayerPerformance", TimeSpan.ZERO, str, null);
            this.mFrameDropTracker.purgeAllRecords();
        }
    }

    private static class AutoEvalPlayerPerformanceEvaluatorConfig extends MediaConfigBase {
        private static final AutoEvalPlayerPerformanceEvaluatorConfig INSTANCE = new AutoEvalPlayerPerformanceEvaluatorConfig();
        private final ConfigurationValue<Float> mFrameDropDetectionPercentage;
        private final TimeConfigurationValue mFrameDropWindowLength;
        private final ConfigurationValue<Integer> mFrameDropWindowThreshold;
        private final ConfigurationValue<Boolean> mIsAutoEvalPlayerPerformanceEvaluatorEnabled;

        private AutoEvalPlayerPerformanceEvaluatorConfig() {
            ConfigType configType = ConfigType.SERVER;
            this.mIsAutoEvalPlayerPerformanceEvaluatorEnabled = newBooleanConfigValue("playback_isAutoEvalPlayerPerformanceEvaluatorEnabled", true, configType);
            this.mFrameDropWindowLength = newTimeConfigurationValue("playback_frameDropWindowLength", TimeSpan.fromSeconds(30.0d), TimeUnit.SECONDS, configType);
            this.mFrameDropWindowThreshold = newIntConfigValue("playback_frameDropWindowThreshold", 40, configType);
            this.mFrameDropDetectionPercentage = newFloatConfigValue("playback_frameDropDetectionPercentage", 10.0f, configType);
        }
    }
}
