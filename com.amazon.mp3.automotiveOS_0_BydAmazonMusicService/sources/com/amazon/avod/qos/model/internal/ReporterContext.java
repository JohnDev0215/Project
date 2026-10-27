package com.amazon.avod.qos.model.internal;

import com.amazon.avod.battery.BatteryInfo;
import com.amazon.avod.http.internal.TokenKey;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.util.MovingAverage;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.playback.PlaybackPerformanceReport;
import com.amazon.avod.playback.capability.DeviceResources;
import com.amazon.avod.qos.QoSConfig;
import com.amazon.avod.qos.QosReportingTag;
import com.amazon.avod.qos.internal.HostnameResolver;
import com.amazon.avod.qos.metadata.DeliveryType;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.qos.model.QosClientContext;
import com.amazon.avod.qos.model.internal.PrimitiveSessionContext;
import com.amazon.avod.qos.reporter.internal.data.TimedEventData;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class ReporterContext {
    private AudioAdaptationSetSwitchContext mAudioAdaptationSetSwitchContext;
    private String mAudioLanguage;
    private final TokenKey mAuthenticationTokenKey;
    private BatteryInfo mBatteryInfo;
    private long mBitrateKbps;
    private String mCdnName;
    private final Map<String, QosReportingTag<?>> mCustomTags;
    private DeliveryType mDeliveryType;
    private final DeviceResources mDeviceResources;
    private float mDownloadPercentage;
    private boolean mDownloadStarted;
    private String mHeuristicsSettingsId;
    private final HostnameResolver mHostnameResolver;
    private final MovingAverage mJavaHeapSessionAverageMb;
    private TimeSpan mLastSeekStartTime;
    private final Object mMutex;
    private final MovingAverage mNativeMemorySessionAverageMb;
    private String mOrigin;
    private final EnumMap<PlaybackPerformanceReport.PlaybackPerformanceSeverity, Integer> mPerformanceMap;
    private String mPlaybackAuthorityId;
    private boolean mPlaybackStarted;
    private final PrimitiveSessionContext.Factory mPrimitiveContextFactory;
    private PrimitiveSessionContext mPrimitiveSessionContext;
    private final QosClientContext mQosClientContext;
    private final QosCommonMetricsContext mQosCommonMetricsContext;
    private final QoSConfig mQosConfig;
    private StreamSwitchContext mStreamSwitchContext;
    private final ImmutableMap<TimedEventType, TimedEventData> mTimedEventMap;
    private String mUrl;
    private String mUrlSetId;
    private TimeSpan mVideoDuration;
    private Integer mVideoHeight;
    private Integer mVideoWidth;

    public enum TimedEventType {
        BUFFER,
        SEEK,
        PAUSE,
        PLAY,
        START,
        HDMI,
        DOLBYDIGITAL
    }

    public ReporterContext(QoSConfig qoSConfig, ExecutorService executorService, QosCommonMetricsContext qosCommonMetricsContext, QosClientContext qosClientContext, @Nullable TokenKey tokenKey) {
        this(qoSConfig, new HostnameResolver(executorService), DeviceResources.getInstance(), qosCommonMetricsContext, qosClientContext, PrimitiveSessionContext.FACTORY, new TimedEventData(), new TimedEventData(), new TimedEventData(), new TimedEventData(), new TimedEventData(), new TimedEventData(), new TimedEventData(), tokenKey);
    }

    ReporterContext(QoSConfig qoSConfig, HostnameResolver hostnameResolver, @Nonnull DeviceResources deviceResources, QosCommonMetricsContext qosCommonMetricsContext, QosClientContext qosClientContext, PrimitiveSessionContext.Factory factory, TimedEventData timedEventData, TimedEventData timedEventData2, TimedEventData timedEventData3, TimedEventData timedEventData4, TimedEventData timedEventData5, TimedEventData timedEventData6, TimedEventData timedEventData7, @Nullable TokenKey tokenKey) {
        this.mMutex = new Object();
        this.mCustomTags = Maps.newHashMap();
        this.mPerformanceMap = Maps.newEnumMap(PlaybackPerformanceReport.PlaybackPerformanceSeverity.class);
        this.mVideoWidth = null;
        this.mVideoHeight = null;
        this.mBitrateKbps = 0L;
        this.mDownloadPercentage = 0.0f;
        this.mPlaybackStarted = false;
        this.mDownloadStarted = false;
        this.mHeuristicsSettingsId = null;
        this.mJavaHeapSessionAverageMb = new MovingAverage();
        this.mNativeMemorySessionAverageMb = new MovingAverage();
        this.mHostnameResolver = hostnameResolver;
        this.mDeviceResources = (DeviceResources) Preconditions.checkNotNull(deviceResources, "DeviceResources was null");
        this.mQosConfig = qoSConfig;
        this.mQosCommonMetricsContext = qosCommonMetricsContext;
        this.mQosClientContext = qosClientContext;
        this.mPrimitiveContextFactory = factory;
        this.mTimedEventMap = ImmutableMap.builder().put(TimedEventType.BUFFER, timedEventData).put(TimedEventType.SEEK, timedEventData2).put(TimedEventType.PAUSE, timedEventData3).put(TimedEventType.PLAY, timedEventData4).put(TimedEventType.START, timedEventData5).put(TimedEventType.HDMI, timedEventData6).put(TimedEventType.DOLBYDIGITAL, timedEventData7).build();
        this.mPrimitiveSessionContext = factory.createContext();
        this.mAuthenticationTokenKey = tokenKey;
    }

    public void startAudioTrackSwitching(StreamSwitchContext streamSwitchContext) {
        synchronized (this.mMutex) {
            this.mStreamSwitchContext = streamSwitchContext;
        }
    }

    public void finishAudioTrackSwitch() {
        synchronized (this.mMutex) {
            this.mStreamSwitchContext = null;
        }
    }

    public boolean isAudioTrackSwitching() {
        boolean z;
        synchronized (this.mMutex) {
            z = this.mStreamSwitchContext != null;
        }
        return z;
    }

    public StreamSwitchContext getStreamSwitchContext() {
        StreamSwitchContext streamSwitchContext;
        synchronized (this.mMutex) {
            streamSwitchContext = this.mStreamSwitchContext;
        }
        return streamSwitchContext;
    }

    public void startAudioAdaptationSetSwitching(@Nonnull AudioAdaptationSetSwitchContext audioAdaptationSetSwitchContext) {
        synchronized (this.mMutex) {
            this.mAudioAdaptationSetSwitchContext = (AudioAdaptationSetSwitchContext) Preconditions.checkNotNull(audioAdaptationSetSwitchContext, "audioAdaptationSetSwitchContext");
        }
    }

    public void finishAudioAdaptationSetSwitching() {
        synchronized (this.mMutex) {
            this.mAudioAdaptationSetSwitchContext = null;
        }
    }

    public boolean isAudioAdaptationSetSwitching() {
        boolean z;
        synchronized (this.mMutex) {
            z = this.mAudioAdaptationSetSwitchContext != null;
        }
        return z;
    }

    public AudioAdaptationSetSwitchContext getAudioAdaptationSetSwitchContext() {
        AudioAdaptationSetSwitchContext audioAdaptationSetSwitchContext;
        synchronized (this.mMutex) {
            audioAdaptationSetSwitchContext = this.mAudioAdaptationSetSwitchContext;
        }
        return audioAdaptationSetSwitchContext;
    }

    public void setCdnInfo(String str, String str2, String str3, String str4) {
        synchronized (this.mMutex) {
            if (this.mUrlSetId != null || this.mCdnName != null) {
                beginNewSession();
            }
            this.mUrl = str3;
            this.mUrlSetId = str;
            this.mCdnName = str2;
            this.mOrigin = str4;
            this.mHostnameResolver.resolveAsync(str3);
        }
    }

    public void setBatteryInfo(BatteryInfo batteryInfo) {
        synchronized (this.mMutex) {
            this.mBatteryInfo = batteryInfo;
        }
    }

    public void setPlaybackAuthorityInfo(String str) {
        synchronized (this.mMutex) {
            this.mPlaybackAuthorityId = str;
        }
    }

    public void setVideoDuration(TimeSpan timeSpan) {
        synchronized (this.mMutex) {
            this.mVideoDuration = timeSpan;
        }
    }

    public ContentType getContentType() {
        ContentType contentType;
        synchronized (this.mMutex) {
            contentType = this.mQosClientContext.getVideoSpec().getContentType();
        }
        return contentType;
    }

    @Nullable
    public String getCdnName() {
        String str;
        synchronized (this.mMutex) {
            str = this.mCdnName;
        }
        return str;
    }

    @Nullable
    public String getOrigin() {
        String str;
        synchronized (this.mMutex) {
            str = this.mOrigin;
        }
        return str;
    }

    @Nullable
    public String getTitleId() {
        String titleId;
        synchronized (this.mMutex) {
            titleId = this.mQosClientContext.getVideoSpec().getTitleId();
        }
        return titleId;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        synchronized (this.mMutex) {
            this.mDeliveryType = deliveryType;
        }
    }

    public void setAudioLanguage(String str) {
        synchronized (this.mMutex) {
            this.mAudioLanguage = str;
        }
    }

    public void setPlaybackStarted(boolean z) {
        synchronized (this.mMutex) {
            this.mPlaybackStarted = z;
        }
    }

    public boolean getPlaybackStarted() {
        boolean z;
        synchronized (this.mMutex) {
            z = this.mPlaybackStarted;
        }
        return z;
    }

    public void setDownloadStarted(boolean z) {
        synchronized (this.mMutex) {
            this.mDownloadStarted = z;
        }
    }

    public boolean getDownloadStarted() {
        boolean z;
        synchronized (this.mMutex) {
            z = this.mDownloadStarted;
        }
        return z;
    }

    public BatteryInfo getBatteryInfo() {
        BatteryInfo batteryInfo;
        synchronized (this.mMutex) {
            batteryInfo = this.mBatteryInfo;
        }
        return batteryInfo;
    }

    public void setInitialPlaybackIndexSeconds(long j) {
        synchronized (this.mMutex) {
            this.mPrimitiveSessionContext.setInitialPlaybackIndexSeconds(Long.valueOf(j));
        }
    }

    public void setFinalPlaybackIndexSeconds(long j) {
        synchronized (this.mMutex) {
            this.mPrimitiveSessionContext.setFinalPlaybackIndexSeconds(Long.valueOf(j));
        }
    }

    public boolean timedEventStart(TimedEventType timedEventType, TimeSpan timeSpan, TimeSpan timeSpan2) {
        boolean zEventStart;
        synchronized (this.mMutex) {
            zEventStart = ((TimedEventData) Preconditions.checkNotNull(this.mTimedEventMap.get(timedEventType))).eventStart(timeSpan2);
            if (zEventStart) {
                int i = AnonymousClass1.$SwitchMap$com$amazon$avod$qos$model$internal$ReporterContext$TimedEventType[timedEventType.ordinal()];
                if (i == 1) {
                    this.mLastSeekStartTime = timeSpan;
                } else if (i == 2) {
                    this.mPrimitiveSessionContext.incrementPauseCount();
                } else if (i == 3) {
                    this.mPrimitiveSessionContext.incrementBufferCount();
                }
            }
        }
        return zEventStart;
    }

    /* JADX INFO: renamed from: com.amazon.avod.qos.model.internal.ReporterContext$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$qos$model$internal$ReporterContext$TimedEventType;

        static {
            int[] iArr = new int[TimedEventType.values().length];
            $SwitchMap$com$amazon$avod$qos$model$internal$ReporterContext$TimedEventType = iArr;
            try {
                iArr[TimedEventType.SEEK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$qos$model$internal$ReporterContext$TimedEventType[TimedEventType.PAUSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$qos$model$internal$ReporterContext$TimedEventType[TimedEventType.BUFFER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public TimeSpan timedEventEnd(TimedEventType timedEventType, TimeSpan timeSpan, TimeSpan timeSpan2) {
        TimeSpan timeSpanEventEnd;
        synchronized (this.mMutex) {
            timeSpanEventEnd = ((TimedEventData) Preconditions.checkNotNull(this.mTimedEventMap.get(timedEventType))).eventEnd(timeSpan2);
            if (timedEventType == TimedEventType.SEEK && timeSpanEventEnd != null) {
                if (timeSpan.compareTo(this.mLastSeekStartTime) > 0) {
                    this.mPrimitiveSessionContext.incrementSeekForwardCount();
                } else {
                    this.mPrimitiveSessionContext.incrementSeekBackwardsCount();
                }
            }
        }
        return timeSpanEventEnd;
    }

    public TimeSpan getSeekStartTime() {
        TimeSpan timeSpan;
        synchronized (this.mMutex) {
            timeSpan = this.mLastSeekStartTime;
        }
        return timeSpan;
    }

    public void setResolution(int i, int i2) {
        synchronized (this.mMutex) {
            this.mVideoWidth = Integer.valueOf(i);
            this.mVideoHeight = Integer.valueOf(i2);
        }
    }

    public void updateCurrentBitrateKbps(long j) {
        synchronized (this.mMutex) {
            this.mBitrateKbps = j;
        }
    }

    public void setDownloadPercentage(float f) {
        synchronized (this.mMutex) {
            this.mDownloadPercentage = f;
        }
    }

    public float getDownloadPercentage() {
        float f;
        synchronized (this.mMutex) {
            f = this.mDownloadPercentage;
        }
        return f;
    }

    public void setReportingTag(QosReportingTag<?> qosReportingTag) {
        synchronized (this.mMutex) {
            this.mCustomTags.put(qosReportingTag.getName(), qosReportingTag);
        }
    }

    public void incrementUpshiftCount() {
        synchronized (this.mMutex) {
            this.mPrimitiveSessionContext.incrementUpshiftCount();
        }
    }

    public void incrementDownshiftCount() {
        synchronized (this.mMutex) {
            this.mPrimitiveSessionContext.incrementDownshiftCount();
        }
    }

    public void incrementErrorCount() {
        synchronized (this.mMutex) {
            this.mPrimitiveSessionContext.incrementErrorCount();
        }
    }

    public void updatePerformanceData(int i, int i2, double d) {
        synchronized (this.mMutex) {
            this.mPrimitiveSessionContext.updatePerformanceData(i, i2, d);
        }
    }

    @Nullable
    public String getHeuristicsSettingsId() {
        String str;
        synchronized (this.mMutex) {
            str = this.mHeuristicsSettingsId;
        }
        return str;
    }

    public void setHeuristicsSettingsId(@Nonnull String str) {
        synchronized (this.mMutex) {
            this.mHeuristicsSettingsId = (String) Preconditions.checkNotNull(str, "heuristicsSettingsId");
        }
    }

    public void incrementPerfCount(@Nonnull PlaybackPerformanceReport.PlaybackPerformanceSeverity playbackPerformanceSeverity) {
        synchronized (this.mMutex) {
            this.mPerformanceMap.put(playbackPerformanceSeverity, Integer.valueOf((this.mPerformanceMap.containsKey(playbackPerformanceSeverity) ? this.mPerformanceMap.get(playbackPerformanceSeverity).intValue() : 0) + 1));
        }
    }

    public void updateStreamingStatistics(long j, long j2, long j3, long j4, long j5, long j6) {
        synchronized (this.mMutex) {
            updateMemoryUsageStatistics();
            this.mPrimitiveSessionContext.updateStreamingStatistics(j, j2, j3, j4, j5, j6);
        }
    }

    public long getPlayedDuration(TimeSpan timeSpan) {
        long cumulativeTime;
        synchronized (this.mMutex) {
            cumulativeTime = ((this.mTimedEventMap.get(TimedEventType.PLAY).getCumulativeTime(timeSpan) - this.mTimedEventMap.get(TimedEventType.PAUSE).getCumulativeTime(timeSpan)) - this.mTimedEventMap.get(TimedEventType.BUFFER).getCumulativeTime(timeSpan)) - this.mTimedEventMap.get(TimedEventType.SEEK).getCumulativeTime(timeSpan);
        }
        return cumulativeTime;
    }

    public DeliveryType getDeliveryType() {
        DeliveryType deliveryType;
        synchronized (this.mMutex) {
            deliveryType = this.mDeliveryType;
        }
        return deliveryType;
    }

    public String getPlaybackType() {
        String str;
        synchronized (this.mMutex) {
            if (this.mDeliveryType == DeliveryType.DOWNLOAD) {
                str = this.mDownloadStarted ? "Progressive" : "FullyDownloaded";
            } else {
                str = "Streaming";
            }
        }
        return str;
    }

    public String getApplicationSessionId() {
        return this.mQosCommonMetricsContext.getApplicationSessionId();
    }

    public String getPrimitiveSessionId() {
        String primitiveSessionId;
        synchronized (this.mMutex) {
            primitiveSessionId = this.mPrimitiveSessionContext.getPrimitiveSessionId();
        }
        return primitiveSessionId;
    }

    public void buildCommonMetrics(MetricsBuilder metricsBuilder) {
        buildCommonMetrics(metricsBuilder, false);
    }

    public void buildCommonMetrics(MetricsBuilder metricsBuilder, boolean z) {
        synchronized (this.mMutex) {
            DeliveryType deliveryType = this.mDeliveryType;
            Integer numValueOf = null;
            String reportingName = deliveryType == null ? null : deliveryType.getReportingName();
            TimeSpan timeSpan = this.mVideoDuration;
            if (timeSpan != null) {
                numValueOf = Integer.valueOf(timeSpan.getTotalSeconds());
            }
            String str = (this.mPlaybackStarted && this.mPlaybackAuthorityId == null) ? this.mUrlSetId : this.mPlaybackAuthorityId;
            this.mPrimitiveSessionContext.buildCommonMetrics(metricsBuilder);
            this.mQosCommonMetricsContext.buildCommonMetrics(metricsBuilder);
            metricsBuilder.asin(this.mQosClientContext.getUniqueVideoName()).asinOwned(Boolean.valueOf(this.mQosClientContext.isTitleOwned())).cdn(this.mCdnName).clientHeight(this.mVideoHeight).clientWidth(this.mVideoWidth).customTags(ImmutableMap.copyOf((Map) this.mCustomTags)).deliveryType(reportingName).language(this.mAudioLanguage).playbackAuthorityId(str).serverId(this.mHostnameResolver.lookup(this.mUrl)).streamingBitRate(Long.valueOf(this.mBitrateKbps)).urlSetId(this.mUrlSetId).userWatchSessionId(this.mQosClientContext.getUserWatchSessionId()).videoDuration(numValueOf);
            if (!z) {
                metricsBuilder.url(this.mUrl);
            }
            metricsBuilder.downloadSessionId(this.mUrlSetId);
        }
    }

    public void buildSessionMetrics(MetricsBuilder metricsBuilder, TimeSpan timeSpan) {
        synchronized (this.mMutex) {
            this.mPrimitiveSessionContext.buildSessionMetrics(metricsBuilder);
            TimedEventData timedEventData = this.mTimedEventMap.get(TimedEventType.PAUSE);
            metricsBuilder.millisecondsStreamed(Long.valueOf(getPlayedDuration(timeSpan))).note(buildSessionNote(timeSpan)).sessionEndTime(Long.valueOf(timeSpan.getTotalMilliseconds())).streamingParameters(this.mQosConfig.getReportingConfigVersion()).timeSpentBuffering(Long.valueOf(this.mTimedEventMap.get(TimedEventType.BUFFER).getCumulativeTime(timeSpan))).timeSpentPaused(Long.valueOf(timedEventData.getCumulativeTime(timeSpan)));
        }
    }

    public int getTotalDroppedFrames() {
        int totalDroppedFrames;
        synchronized (this.mMutex) {
            totalDroppedFrames = this.mPrimitiveSessionContext.getTotalDroppedFrames();
        }
        return totalDroppedFrames;
    }

    public int getPerformanceEventCount() {
        int perfCount;
        synchronized (this.mMutex) {
            perfCount = getPerfCount(PlaybackPerformanceReport.PlaybackPerformanceSeverity.MAJOR) + getPerfCount(PlaybackPerformanceReport.PlaybackPerformanceSeverity.CRITICAL);
        }
        return perfCount;
    }

    private int getPerfCount(@Nonnull PlaybackPerformanceReport.PlaybackPerformanceSeverity playbackPerformanceSeverity) {
        if (this.mPerformanceMap.containsKey(playbackPerformanceSeverity)) {
            return this.mPerformanceMap.get(playbackPerformanceSeverity).intValue();
        }
        return 0;
    }

    private String buildSessionNote(TimeSpan timeSpan) {
        JSONObject jSONObject = new JSONObject();
        try {
            TimedEventData timedEventData = this.mTimedEventMap.get(TimedEventType.HDMI);
            TimedEventData timedEventData2 = this.mTimedEventMap.get(TimedEventType.DOLBYDIGITAL);
            long cumulativeTime = timedEventData.getCumulativeTime(timeSpan);
            long cumulativeTime2 = timedEventData2.getCumulativeTime(timeSpan);
            jSONObject.put("additionalDeviceProperties", this.mQosCommonMetricsContext.getAdditionalDeviceProperties());
            if (cumulativeTime > 0) {
                jSONObject.put("hdmiTimeInMillis", cumulativeTime);
            }
            if (cumulativeTime2 > 0) {
                jSONObject.put("ddPlusTimeInMillis", cumulativeTime2);
            }
            jSONObject.put("minorPerfCount", getPerfCount(PlaybackPerformanceReport.PlaybackPerformanceSeverity.MINOR));
            jSONObject.put("majorPerfCount", getPerfCount(PlaybackPerformanceReport.PlaybackPerformanceSeverity.MAJOR));
            jSONObject.put("criticalPerfCount", getPerfCount(PlaybackPerformanceReport.PlaybackPerformanceSeverity.CRITICAL));
            jSONObject.put("javaHeapMax", this.mJavaHeapSessionAverageMb.getCurrentMax());
            jSONObject.put("nativeMemoryMax", this.mNativeMemorySessionAverageMb.getCurrentMax());
            jSONObject.put("javaHeapSessionAverage", this.mJavaHeapSessionAverageMb.getCurrentAverage());
            jSONObject.put("nativeMemorySessionAverage", this.mNativeMemorySessionAverageMb.getCurrentAverage());
        } catch (JSONException unused) {
            DLog.errorf("Could not create Json");
        }
        return jSONObject.toString();
    }

    private void beginNewSession() {
        if (this.mQosConfig.getMultiSessionReportingEnabled()) {
            Long finalPlaybackIndexSeconds = this.mPrimitiveSessionContext.getFinalPlaybackIndexSeconds();
            PrimitiveSessionContext primitiveSessionContextCreateContext = this.mPrimitiveContextFactory.createContext();
            this.mPrimitiveSessionContext = primitiveSessionContextCreateContext;
            primitiveSessionContextCreateContext.setInitialPlaybackIndexSeconds(finalPlaybackIndexSeconds);
            this.mPrimitiveSessionContext.setFinalPlaybackIndexSeconds(finalPlaybackIndexSeconds);
            UnmodifiableIterator<TimedEventData> it = this.mTimedEventMap.values().iterator();
            while (it.hasNext()) {
                TimedEventData next = it.next();
                boolean zIsStarted = next.isStarted();
                next.reset();
                if (zIsStarted) {
                    next.eventStart(TimeSpan.now());
                }
            }
        }
    }

    private void updateMemoryUsageStatistics() {
        this.mJavaHeapSessionAverageMb.addSample(this.mDeviceResources.getCurrentApplicationMemoryUsageInMB());
        this.mNativeMemorySessionAverageMb.addSample(DataUnit.BYTES.toMegaBytes(this.mDeviceResources.getNativeHeapUsedSizeInBytes()));
    }

    @Nullable
    public TokenKey getAuthenticationTokenKey() {
        return this.mAuthenticationTokenKey;
    }
}
