package com.amazon.avod.qos.reporter;

import android.os.Build;
import com.amazon.avod.battery.BatteryInfo;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.experiments.PlaybackWeblabs;
import com.amazon.avod.media.DataRate;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.error.MediaInternalErrorCode;
import com.amazon.avod.media.playback.support.RendererSchemeResolverConfig;
import com.amazon.avod.playback.BufferingAnalysis;
import com.amazon.avod.playback.PlaybackPerformanceReport;
import com.amazon.avod.playback.capability.DeviceCapability;
import com.amazon.avod.playback.capability.DeviceResources;
import com.amazon.avod.playbackclient.config.PlaybackConfig;
import com.amazon.avod.pmet.ContentStoreType;
import com.amazon.avod.pmet.ContentTypePivot;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.qahooks.PlaybackQAEvent;
import com.amazon.avod.qahooks.PlaybackQAMetric;
import com.amazon.avod.qahooks.QALog;
import com.amazon.avod.qos.EventThrottle;
import com.amazon.avod.qos.QoSConfig;
import com.amazon.avod.qos.internal.metrics.QoSMetric;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.qos.model.internal.AudioAdaptationSetSwitchContext;
import com.amazon.avod.qos.model.internal.ReporterContext;
import com.amazon.avod.qos.model.internal.StreamSwitchContext;
import com.amazon.avod.qos.reporter.internal.EventReporterBase;
import com.amazon.avod.qos.reporter.internal.PlatformMetricsEventSender;
import com.amazon.avod.qos.reporter.internal.QoSMetricEventSender;
import com.amazon.avod.settings.StreamingConnectionSetting;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.sequence.AbstractIntSequence;
import com.amazon.avod.util.sequence.ArithmeticIntSequence;
import com.amazon.avod.util.sequence.CompositeIntSequence;
import com.amazon.avod.util.sequence.ConstantIntSequence;
import com.amazon.avod.util.sequence.ExponentialSequence;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Provider;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackEventReporter extends EventReporterBase {
    static AtomicBoolean sIsFirstSession = new AtomicBoolean(true);
    private final EventThrottle mAmountStreamedThrottle;
    private final Provider<BatteryInfo> mBatteryInfoProvider;
    private DataRate mBitrate;
    private final MediaSystemSharedContext mContext;
    private final DeviceResources mDeviceResources;
    private final EventThrottle mFpsEventThrottle;
    private final EventThrottle mLowPerformanceEventThrottle;
    private final NetworkConnectionManager mNetworkConnectionManager;
    private final PlatformMetricsEventSender mPlatformMetricsSender;
    private final PlaybackConfig mPlaybackConfig;
    private final PlaybackPmetMetricReporter mPlaybackPmetMetricReporter;
    private final EventThrottle mPlaybackSessionThrottle;
    private final RendererSchemeResolverConfig mRendererSchemeResolverConfig;

    PlaybackEventReporter(@Nonnull QoSConfig qoSConfig, @Nonnull QoSMetricEventSender qoSMetricEventSender, @Nonnull PlatformMetricsEventSender platformMetricsEventSender, @Nonnull Provider<BatteryInfo> provider, @Nonnull DeviceResources deviceResources, @Nonnull MediaSystemSharedContext mediaSystemSharedContext) {
        this(qoSConfig, qoSMetricEventSender, platformMetricsEventSender, provider, deviceResources, mediaSystemSharedContext, PlaybackConfig.getInstance(), NetworkConnectionManager.getInstance(), PlaybackPmetMetricReporter.getInstance(), RendererSchemeResolverConfig.getInstance());
    }

    PlaybackEventReporter(@Nonnull QoSConfig qoSConfig, @Nonnull QoSMetricEventSender qoSMetricEventSender, @Nonnull PlatformMetricsEventSender platformMetricsEventSender, @Nonnull Provider<BatteryInfo> provider, @Nonnull DeviceResources deviceResources, @Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull PlaybackConfig playbackConfig, @Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter, @Nonnull RendererSchemeResolverConfig rendererSchemeResolverConfig) {
        AbstractIntSequence constantIntSequence;
        super(qoSMetricEventSender);
        this.mFpsEventThrottle = new EventThrottle(new ExponentialSequence(15, 2.0d, 300));
        CompositeIntSequence compositeIntSequence = new CompositeIntSequence(Lists.newArrayList(new ExponentialSequence(1, 2.0d, 15), new ArithmeticIntSequence(15, 15, Integer.MAX_VALUE)));
        TimeUnit timeUnit = TimeUnit.MINUTES;
        this.mAmountStreamedThrottle = new EventThrottle(compositeIntSequence, timeUnit);
        this.mBitrate = null;
        Preconditions.checkNotNull(qoSConfig, "config");
        Preconditions.checkNotNull(qoSMetricEventSender, "sender");
        this.mBatteryInfoProvider = (Provider) Preconditions.checkNotNull(provider, "batteryInfoProvider");
        this.mPlatformMetricsSender = (PlatformMetricsEventSender) Preconditions.checkNotNull(platformMetricsEventSender, "platformMetricsSender");
        this.mDeviceResources = (DeviceResources) Preconditions.checkNotNull(deviceResources, "deviceResources");
        this.mContext = (MediaSystemSharedContext) Preconditions.checkNotNull(mediaSystemSharedContext, "context");
        this.mPlaybackSessionThrottle = new EventThrottle(new ConstantIntSequence(qoSConfig.getIntervalBetweenPlaybackSessionUploads().getTotalMinutes()), timeUnit);
        if (qoSConfig.shouldThrottlePerformanceEvents()) {
            constantIntSequence = new ExponentialSequence(15, 2.0d, 300);
        } else {
            constantIntSequence = new ConstantIntSequence(0);
        }
        this.mLowPerformanceEventThrottle = new EventThrottle(constantIntSequence);
        this.mPlaybackConfig = (PlaybackConfig) Preconditions.checkNotNull(playbackConfig, "playbackConfig");
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkConnectionManager");
        this.mPlaybackPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "playbackPmetMetricReporter");
        this.mRendererSchemeResolverConfig = (RendererSchemeResolverConfig) Preconditions.checkNotNull(rendererSchemeResolverConfig, "rendererSchemeResolverConfig");
    }

    public void handleAudioTrackSwitchStart(String str, String str2, String str3, String str4) {
        StreamSwitchContext streamSwitchContext = new StreamSwitchContext(str, str2, str3, str4);
        getReporterContext().startAudioTrackSwitching(streamSwitchContext);
        getReporterContext().setAudioLanguage(str2);
        reportMetric(QOSEventName.MultiTrackAudio.name(), null, TimeSpan.now(), streamSwitchContext.toString(), str2);
    }

    public void handleAudioAdaptationSetSwitchStart(String str, int i, String str2, String str3) {
        getReporterContext().startAudioAdaptationSetSwitching(new AudioAdaptationSetSwitchContext(str, i, str2, str3));
    }

    public void onMediaPlayerStart(TimeSpan timeSpan, TimeSpan timeSpan2) {
        getReporterContext().setPlaybackStarted(true);
        getReporterContext().timedEventStart(ReporterContext.TimedEventType.START, timeSpan, timeSpan2);
    }

    public void onPlaybackAuthorityDetermined(String str) {
        getReporterContext().setPlaybackAuthorityInfo(str);
    }

    public void onPlay(TimeSpan timeSpan, TimeSpan timeSpan2) {
        getReporterContext().timedEventEnd(ReporterContext.TimedEventType.PAUSE, timeSpan, timeSpan2);
    }

    public void onPause(TimeSpan timeSpan, TimeSpan timeSpan2) {
        getReporterContext().timedEventStart(ReporterContext.TimedEventType.PAUSE, timeSpan, timeSpan2);
    }

    public void onHdmiStatusChange(boolean z, TimeSpan timeSpan) {
        if (z) {
            getReporterContext().timedEventStart(ReporterContext.TimedEventType.HDMI, null, timeSpan);
        } else {
            getReporterContext().timedEventEnd(ReporterContext.TimedEventType.HDMI, null, timeSpan);
        }
    }

    public void onInitialPlaybackIndex(long j) {
        getReporterContext().setInitialPlaybackIndexSeconds(j);
    }

    public void onPlaybackIndex(long j) {
        getReporterContext().setFinalPlaybackIndexSeconds(j);
    }

    @SuppressFBWarnings({"ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD"})
    public void onPlaybackStart(TimeSpan timeSpan, TimeSpan timeSpan2, @Nullable String str, @Nullable DeviceCapability deviceCapability, @Nonnull String str2, @Nullable String str3, @Nonnull AudioAdaptationSetSwitchContext audioAdaptationSetSwitchContext) {
        onPlaybackStart(timeSpan, timeSpan2, str, deviceCapability, str2, str3, audioAdaptationSetSwitchContext, null, null, null, false);
    }

    @SuppressFBWarnings({"ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD"})
    public void onPlaybackStart(TimeSpan timeSpan, TimeSpan timeSpan2, @Nullable String str, @Nullable DeviceCapability deviceCapability, @Nonnull String str2, @Nullable String str3, @Nonnull AudioAdaptationSetSwitchContext audioAdaptationSetSwitchContext, @Nullable String str4, @Nullable String str5, @Nullable ContentStoreType contentStoreType, boolean z) {
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        TimeSpan timeSpanTimedEventEnd = getReporterContext().timedEventEnd(ReporterContext.TimedEventType.START, timeSpan, timeSpan2);
        if (timeSpanTimedEventEnd != null) {
            JSONObject jSONObject = new JSONObject();
            if (str != null) {
                try {
                    jSONObject.put("startTimeNote", str);
                } catch (JSONException unused) {
                    DLog.errorf("Could not create Json");
                }
            }
            if (deviceCapability != null) {
                jSONObject.put("coreFreq", deviceCapability.getCoreFrequency());
                jSONObject.put("hwAccl", deviceCapability.getHardwareAcceleration());
                jSONObject.put("neon", deviceCapability.getNeonSupport());
                jSONObject.put("cores", deviceCapability.getNumberOfCores());
            }
            Optional<StreamingConnectionSetting> streamingQualityForNetwork = this.mPlaybackConfig.getStreamingQualityForNetwork(this.mNetworkConnectionManager.getNetworkInfo().getNetworkType());
            if (streamingQualityForNetwork.isPresent()) {
                jSONObject.put("streamingQualityPref", streamingQualityForNetwork.get().toString());
            }
            this.mDeviceResources.appendMemoryInformationToJsonObject(jSONObject);
            try {
                jSONObject.put("rendererInfo", new JSONArray(str2));
            } catch (JSONException unused2) {
                jSONObject.put("rendererInfo", str2);
            }
            BatteryInfo batteryInfo = this.mBatteryInfoProvider.get();
            if (batteryInfo != null) {
                JSONObject jSONObject2 = new JSONObject();
                addBatteryInfoToJson(jSONObject2, batteryInfo);
                jSONObject.put("battery", jSONObject2);
                getReporterContext().setBatteryInfo(batteryInfo);
            }
            if (str4 != null) {
                jSONObject.put("videoFourCC", str4);
            }
            if (str5 != null) {
                jSONObject.put("audioFourCC", str5);
            }
            metricsBuilder.eventDuration(Long.valueOf(timeSpanTimedEventEnd.getTotalMilliseconds())).note(jSONObject.toString()).eventSubtype(getReporterContext().getPlaybackType());
            if (str3 != null) {
                metricsBuilder.playbackSettingsId(str3);
                getReporterContext().setHeuristicsSettingsId(str3);
            }
            reportMetric(QoSMetric.PlaybackStart, metricsBuilder);
            if (sIsFirstSession.compareAndSet(true, false)) {
                reportMetric(QoSMetric.Information, getMetricsBuilder().eventSubtype("FirstPlaybackAfterStartup"));
            }
            QoSMetric qoSMetric = QoSMetric.Information;
            reportMetric(qoSMetric, getMetricsBuilder().eventSubtype("Weblab").note(getWeblabInformation()));
            reportMetric(qoSMetric, getMetricsBuilder().eventSubtype("DisplayInfo").note(this.mContext.getDisplayInfo()));
            reportMetric(qoSMetric, getMetricsBuilder().eventSubtype("MediaQuality").note(this.mPlaybackConfig.getStreamingQuality().getMediaQuality().name()));
            String cause = audioAdaptationSetSwitchContext.getCause();
            int bitrate = audioAdaptationSetSwitchContext.getBitrate();
            String adaptationSetName = audioAdaptationSetSwitchContext.getAdaptationSetName();
            String streamSelectionParams = audioAdaptationSetSwitchContext.getStreamSelectionParams();
            QALog.newQALog(PlaybackQAEvent.PLAYBACK_AUDIO_BITRATE_CHANGED).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.CAUSE, cause).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.NEW_QUALITY, bitrate).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.NEW_ADAPTATION_SET, adaptationSetName).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.NOTE, streamSelectionParams).send();
            reportMetric(QoSMetric.AudioBitrateChange, getMetricsBuilder().eventSubtype(cause).fromBitrate(0).toBitrate(Integer.valueOf(bitrate)).fromAdaptationSet("").toAdaptationSet(adaptationSetName).note(streamSelectionParams));
        }
        getReporterContext().timedEventStart(ReporterContext.TimedEventType.PLAY, timeSpan, timeSpan2);
        this.mAmountStreamedThrottle.onEvent();
        this.mPlaybackSessionThrottle.onEvent();
        if (contentStoreType != null) {
            this.mPlaybackPmetMetricReporter.reportContentStoreTypeMetric(z ? ContentTypePivot.LIVE : ContentTypePivot.VOD, contentStoreType);
        }
    }

    @Nonnull
    final String getWeblabInformation() {
        StringBuilder sb = new StringBuilder();
        sb.append(PlaybackWeblabs.getRunningExperimentsString());
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        String string = sb.toString();
        return string.isEmpty() ? "NoRunningWeblab" : string;
    }

    public void onPlaybackUpdate(TimeSpan timeSpan) {
        if (this.mAmountStreamedThrottle.shouldSendEvent()) {
            this.mAmountStreamedThrottle.onEvent();
            MetricsBuilder metricsBuilder = getMetricsBuilder();
            metricsBuilder.millisecondsStreamed(Long.valueOf(getReporterContext().getPlayedDuration(timeSpan)));
            JSONObject jSONObject = new JSONObject();
            try {
                this.mDeviceResources.appendMemoryInformationToJsonObject(jSONObject);
                metricsBuilder.note(jSONObject.toString());
            } catch (JSONException e) {
                DLog.logf("Exception creating json object. %s", e);
            }
            reportMetric(QoSMetric.PlaybackUpdate, metricsBuilder);
        }
    }

    public void onPeriodicPlaybackSession(TimeSpan timeSpan) {
        if (this.mPlaybackSessionThrottle.shouldSendEvent()) {
            this.mPlaybackSessionThrottle.onEvent();
            reportSession(timeSpan);
        }
    }

    public void onPlaybackStop(@Nonnull TimeSpan timeSpan, @Nonnull String str, @Nonnull String str2, @Nullable String str3) {
        ReporterContext reporterContext = getReporterContext();
        if (reporterContext.getPlaybackStarted()) {
            BatteryInfo batteryInfo = this.mBatteryInfoProvider.get();
            int performanceEventCount = reporterContext.getPerformanceEventCount();
            MetricsBuilder metricsBuilder = getMetricsBuilder();
            reporterContext.buildSessionMetrics(metricsBuilder, timeSpan);
            String str4 = String.format(Locale.US, "%s|%s", str, str2);
            String preferredAbi = getPreferredAbi();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("RendererScheme", str4);
                jSONObject.put("Abi", preferredAbi);
                jSONObject.put("performanceDegradationEvents", performanceEventCount);
                if (batteryInfo != null) {
                    addBatteryInfoToJson(jSONObject, batteryInfo);
                    if (getReporterContext().getBatteryInfo() != null) {
                        jSONObject.put("levelDrop", batteryInfo.getLevel() - r4.getLevel());
                    }
                }
                if (str3 != null) {
                    jSONObject.put("streamMetaData", str3);
                }
            } catch (JSONException unused) {
                DLog.errorf("Could not create Json");
            }
            metricsBuilder.note(jSONObject.toString()).eventSubtype(reporterContext.getPlaybackType()).eventDuration(Long.valueOf(timeSpan.getTotalMilliseconds())).millisecondsStreamed(Long.valueOf(reporterContext.getPlayedDuration(timeSpan)));
            reportMetric(QoSMetric.PlaybackStop, metricsBuilder);
            QoSMetric qoSMetric = QoSMetric.Information;
            reportMetric(qoSMetric, getMetricsBuilder().eventSubtype("RendererScheme").note(str4));
            reportMetric(qoSMetric, getMetricsBuilder().eventSubtype("Abi").note(preferredAbi));
            this.mPlaybackPmetMetricReporter.reportTotalDroppedFramesMetric(reporterContext.getTotalDroppedFrames(), str, str2);
        }
    }

    @Nonnull
    private String getPreferredAbi() {
        String[] strArr = Build.SUPPORTED_ABIS;
        String str = strArr.length > 0 ? strArr[0] : "emptyList";
        return (this.mRendererSchemeResolverConfig.isChromeOsDetectionEnabled() && this.mRendererSchemeResolverConfig.isChromeOs(this.mContext.getAppContext())) ? String.format(Locale.US, "%s_%s", "chromeos", str) : str;
    }

    public void onLowPerformanceReported(@Nonnull PlaybackPerformanceReport playbackPerformanceReport, @Nonnull TimeSpan timeSpan) {
        Preconditions.checkNotNull(playbackPerformanceReport, "performanceReport");
        Preconditions.checkNotNull(timeSpan, "eventTimeStamp");
        Preconditions.checkArgument(playbackPerformanceReport.getSeverity() != PlaybackPerformanceReport.PlaybackPerformanceSeverity.NONE);
        if (playbackPerformanceReport.getSeverity() != PlaybackPerformanceReport.PlaybackPerformanceSeverity.MINOR) {
            Locale locale = Locale.US;
            onLowPerformanceReported(String.format(locale, "Rendered time: %s, Performance severity: %s Reason: %s", timeSpan, playbackPerformanceReport.getSeverity().getDescription(), playbackPerformanceReport.getReason()), String.format(locale, "Detailed info: %s", playbackPerformanceReport.getDetails()));
        }
        getReporterContext().incrementPerfCount(playbackPerformanceReport.getSeverity());
    }

    public void onLowPerformanceReported(@Nonnull String str, @Nonnull String str2) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        if (this.mLowPerformanceEventThrottle.shouldSendEvent()) {
            this.mLowPerformanceEventThrottle.onEvent();
            DLog.logf("Low playback performance reported: %s - Details: %s", str, str2);
            processErrorEvent(TimeSpan.now(), 3, "ExcessiveDroppedFrames", str, str2, null);
        }
    }

    public void onLowFps(TimeSpan timeSpan, String str, String str2) {
        if (this.mFpsEventThrottle.shouldSendEvent()) {
            this.mFpsEventThrottle.onEvent();
            processErrorEvent(timeSpan, 3, "MediaPlayerError", str, str2, null);
        }
    }

    public void onPlaybackError(TimeSpan timeSpan, int i, String str, String str2, @Nullable String str3) {
        processErrorEvent(timeSpan, i, "MediaPlayerError", str, str2, this.mDeviceResources.getMemoryUsageAsJsonString(), str3);
    }

    public void onPlaybackError(MediaInternalErrorCode mediaInternalErrorCode, TimeSpan timeSpan, int i, String str, String str2, @Nullable String str3) {
        this.mPlaybackPmetMetricReporter.reportPlaybackPLSMErrorEvent(mediaInternalErrorCode, getContentType());
        processErrorEvent(timeSpan, i, "MediaPlayerError", str, str2, this.mDeviceResources.getMemoryUsageAsJsonString(), str3);
    }

    public void setIsDolbyDigitalPlusStream(boolean z) {
        TimeSpan timeSpanNow = TimeSpan.now();
        if (z) {
            getReporterContext().timedEventStart(ReporterContext.TimedEventType.DOLBYDIGITAL, null, timeSpanNow);
        } else {
            getReporterContext().timedEventEnd(ReporterContext.TimedEventType.DOLBYDIGITAL, null, timeSpanNow);
        }
    }

    public void onBitrateChange(@Nullable DataRate dataRate) {
        if (dataRate == null) {
            return;
        }
        if (this.mBitrate != null) {
            if (dataRate.getBitsPerSecond() > this.mBitrate.getBitsPerSecond()) {
                getReporterContext().incrementUpshiftCount();
            } else if (dataRate.getBitsPerSecond() < this.mBitrate.getBitsPerSecond()) {
                getReporterContext().incrementDownshiftCount();
            }
        }
        this.mBitrate = dataRate;
    }

    public void onResolutionChanged(@Nullable VideoResolution videoResolution) {
        if (videoResolution == null) {
            return;
        }
        getReporterContext().setResolution(videoResolution.getWidth(), videoResolution.getHeight());
    }

    public void onPerformanceDataReported(int i, int i2, double d) {
        Preconditions.checkArgument(i >= 0);
        Preconditions.checkArgument(i2 >= 0);
        Preconditions.checkArgument(d >= 0.0d);
        getReporterContext().updatePerformanceData(i, i2, d);
    }

    public void reportSeekStart(TimeSpan timeSpan, TimeSpan timeSpan2) {
        getReporterContext().timedEventStart(ReporterContext.TimedEventType.SEEK, timeSpan, timeSpan2);
    }

    public void reportSeekEnd(TimeSpan timeSpan, TimeSpan timeSpan2, @Nullable String str) {
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        TimeSpan seekStartTime = getReporterContext().getSeekStartTime();
        TimeSpan timeSpanTimedEventEnd = getReporterContext().timedEventEnd(ReporterContext.TimedEventType.SEEK, timeSpan, timeSpan2);
        if (timeSpanTimedEventEnd != null) {
            metricsBuilder.millisecondsStreamed(Long.valueOf(getReporterContext().getPlayedDuration(timeSpan2))).fromTimecode(Integer.valueOf(seekStartTime.getTotalSeconds())).toTimecode(Integer.valueOf(timeSpan.getTotalSeconds())).eventDuration(Long.valueOf(timeSpanTimedEventEnd.getTotalMilliseconds())).eventSubtype("Seeking").note(str);
            reportMetric(QoSMetric.PlaybackBuffering, metricsBuilder);
        }
    }

    public void reportBufferStart(TimeSpan timeSpan, TimeSpan timeSpan2, @Nullable BufferingAnalysis bufferingAnalysis) {
        this.mPlaybackPmetMetricReporter.reportPlaybackUnexpectedBufferingEvent(getContentType(), getCdn(), getOrigin(), getTitleId(), bufferingAnalysis);
        getReporterContext().timedEventStart(ReporterContext.TimedEventType.BUFFER, timeSpan, timeSpan2);
        if (this.mPlatformMetricsSender.canReportNow()) {
            MetricsBuilder metricsBuilder = getMetricsBuilder();
            getReporterContext().buildCommonMetrics(metricsBuilder);
            getReporterContext().buildSessionMetrics(metricsBuilder, timeSpan2);
            this.mPlatformMetricsSender.reportBufferingMetric(metricsBuilder);
        }
        if (bufferingAnalysis != null) {
            MetricsBuilder metricsBuilder2 = getMetricsBuilder();
            metricsBuilder2.eventSubtype("Analysis").note(bufferingAnalysis.toString());
            reportMetric(QoSMetric.PlaybackBuffering, metricsBuilder2);
        }
    }

    public void reportBufferEnd(TimeSpan timeSpan, TimeSpan timeSpan2) {
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        TimeSpan timeSpanTimedEventEnd = getReporterContext().timedEventEnd(ReporterContext.TimedEventType.BUFFER, timeSpan, timeSpan2);
        if (timeSpanTimedEventEnd != null) {
            metricsBuilder.millisecondsStreamed(Long.valueOf(getReporterContext().getPlayedDuration(timeSpan2))).eventDuration(Long.valueOf(timeSpanTimedEventEnd.getTotalMilliseconds())).eventSubtype("Buffer").note("Buffer");
            reportMetric(QoSMetric.PlaybackBuffering, metricsBuilder);
        }
    }

    @Nonnull
    private void addBatteryInfoToJson(@Nonnull JSONObject jSONObject, @Nonnull BatteryInfo batteryInfo) throws JSONException {
        jSONObject.put("stat", batteryInfo.getStatus().name());
        jSONObject.put("health", batteryInfo.getHealth().name());
        jSONObject.put("power", batteryInfo.getPowerSource().name());
        jSONObject.put("level", batteryInfo.getLevel());
        jSONObject.put("temp", batteryInfo.getTemperature());
        jSONObject.put("volt", batteryInfo.getVoltage());
    }

    public void reportRendererStatus(@Nonnull String str) {
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        metricsBuilder.eventSubtype("RendererReuse");
        metricsBuilder.note(str);
        reportMetric(QoSMetric.RendererStatus, metricsBuilder);
    }

    public void reportSubtitlesAggregate(@Nonnull String str) {
        Preconditions.checkNotNull(str, "qosSummary");
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        metricsBuilder.eventSubtype("SubtitlesAggregator").note(str);
        reportMetric(QoSMetric.Information, metricsBuilder);
    }

    public void reportLiveManifestRefresh(@Nonnull TimeSpan timeSpan, @Nonnull TimeSpan timeSpan2, @Nonnull TimeSpan timeSpan3, @Nonnull String str) {
        Preconditions.checkNotNull(timeSpan, "targetTime");
        Preconditions.checkNotNull(timeSpan2, "epochTime");
        Preconditions.checkNotNull(str, "note");
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        metricsBuilder.millisecondsStreamed(Long.valueOf(getReporterContext().getPlayedDuration(timeSpan2))).fromTimecode(Integer.valueOf(getReporterContext().getSeekStartTime().getTotalSeconds())).toTimecode(Integer.valueOf(timeSpan.getTotalSeconds())).eventSubtype("LiveManifestRefresh").eventDuration(Long.valueOf(timeSpan3.getTotalMilliseconds())).note(str);
        reportMetric(QoSMetric.PlaybackBuffering, metricsBuilder);
    }
}
