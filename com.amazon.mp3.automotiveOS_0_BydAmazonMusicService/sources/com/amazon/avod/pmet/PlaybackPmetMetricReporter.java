package com.amazon.avod.pmet;

import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.framework.error.MediaInternalErrorCode;
import com.amazon.avod.media.playback.support.AvailabilityStatus;
import com.amazon.avod.media.playback.support.HardwareAccelerationState;
import com.amazon.avod.metrics.pmet.MetricParameter;
import com.amazon.avod.metrics.pmet.util.ReportableString;
import com.amazon.avod.metrics.pmet.util.Separator;
import com.amazon.avod.perf.DurationMetric;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.BufferType;
import com.amazon.avod.playback.BufferingAnalysis;
import com.amazon.avod.playback.capability.DeviceResources;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackPmetMetricReporter {
    static final ReportableString UNAVAILABLE_REPORTABLE_STRING = new ReportableString("", ImmutableSet.of(), "Unavailable");
    private final boolean isPmetReportingOriginPivotsEnabled;
    private final DeviceResources mDeviceResources;
    private final boolean mIsAdPmetReportingEnabled;
    private final boolean mIsBufferingAnalysisPmetReportingEnabled;
    private final boolean mIsBufferingEventPmetReportingEnabled;
    private final boolean mIsCacheEvictionPmetReportingEnabled;
    private final boolean mIsCacheOperationPmetReportingEnabled;
    private final boolean mIsCacheStatusPmetReportingEnabled;
    private final boolean mIsFragmentURLResolutionErrorsPmetReportingEnabled;
    private final boolean mIsPlaybackSessionsPmetReportingHighPriority;
    private final boolean mIsPmetReportingCdnOriginPivotsEnabled;
    private final boolean mIsPmetReportingCoreMetricsTitleIdPivotEnabled;
    private final boolean mIsPmetReportingSecondaryMetricsTitleIdPivotEnabled;
    private final boolean mIsPmetReportingVodOriginPivotsEnabled;
    private final boolean mIsSubtitleErrorPmetReportingEnabled;
    private final boolean mIsSubtitleMetricsPmetReportingEnabled;
    private final Runtime mRuntime;
    private final Set<String> mTitleIdPivotsWhitelist;

    private static final class SingletonHolder {
        private static final PlaybackPmetMetricReporter INSTANCE = new PlaybackPmetMetricReporter(DeviceResources.getInstance(), Runtime.getRuntime(), PlaybackPmetReportingConfig.getInstance());

        private SingletonHolder() {
        }
    }

    private PlaybackPmetMetricReporter(@Nonnull DeviceResources deviceResources, @Nonnull Runtime runtime, @Nonnull PlaybackPmetReportingConfig playbackPmetReportingConfig) {
        this.mDeviceResources = (DeviceResources) Preconditions.checkNotNull(deviceResources, "deviceResources");
        this.mRuntime = (Runtime) Preconditions.checkNotNull(runtime, "runtime");
        Preconditions.checkNotNull(playbackPmetReportingConfig, "playbackPmetReportingConfig");
        this.mIsBufferingEventPmetReportingEnabled = playbackPmetReportingConfig.isBufferingEventPmetReportingEnabled();
        this.mIsBufferingAnalysisPmetReportingEnabled = playbackPmetReportingConfig.isBufferingAnalysisPmetReportingEnabled();
        this.mIsPlaybackSessionsPmetReportingHighPriority = playbackPmetReportingConfig.isPlaybackSessionsPmetReportingHighPriority();
        this.mIsPmetReportingCdnOriginPivotsEnabled = playbackPmetReportingConfig.isPmetReportingCdnOriginPivotsEnabled();
        this.mIsPmetReportingVodOriginPivotsEnabled = playbackPmetReportingConfig.isPmetReportingVodOriginPivotsEnabled();
        this.isPmetReportingOriginPivotsEnabled = playbackPmetReportingConfig.isPmetReportingOriginPivotsEnabled();
        this.mIsPmetReportingCoreMetricsTitleIdPivotEnabled = playbackPmetReportingConfig.isPmetReportingCoreMetricsTitleIdPivotEnabled();
        this.mIsPmetReportingSecondaryMetricsTitleIdPivotEnabled = playbackPmetReportingConfig.isPmetReportingSecondaryMetricsTitleIdPivotEnabled();
        this.mTitleIdPivotsWhitelist = playbackPmetReportingConfig.getPmetTitleIdPivotsWhitelist();
        this.mIsSubtitleErrorPmetReportingEnabled = playbackPmetReportingConfig.isSubtitleErrorPmetReportingEnabled();
        this.mIsSubtitleMetricsPmetReportingEnabled = playbackPmetReportingConfig.isSubtitleMetricsPmetReportingEnabled();
        this.mIsAdPmetReportingEnabled = playbackPmetReportingConfig.isAdErrorPmetReportingEnabled();
        this.mIsFragmentURLResolutionErrorsPmetReportingEnabled = playbackPmetReportingConfig.mIsFragmentURLResolutionErrorsPmetReportingEnabled();
        this.mIsCacheOperationPmetReportingEnabled = playbackPmetReportingConfig.isCacheOperationPmetReportingEnabled();
        this.mIsCacheStatusPmetReportingEnabled = playbackPmetReportingConfig.isCacheStatusPmetReportingEnabled();
        this.mIsCacheEvictionPmetReportingEnabled = playbackPmetReportingConfig.isCacheEvictionPmetReportingEnabled();
    }

    @Nonnull
    public static PlaybackPmetMetricReporter getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void reportPlaybackUnexpectedBufferingEvent(@Nonnull ContentTypePivot contentTypePivot, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable BufferingAnalysis bufferingAnalysis) {
        Preconditions.checkNotNull(contentTypePivot, "contentType");
        if (this.mIsBufferingEventPmetReportingEnabled) {
            reportCounterWithContentTypePivot(EnumeratedPlaybackPmetMetrics.PLAYBACK_UNEXPECTED_BUFFER, ImmutableList.of(), contentTypePivot);
            reportCounterWithCdnOriginPivots(EnumeratedPlaybackPmetMetrics.PLAYBACK_UNEXPECTED_BUFFER_CDN_ORIGIN, ImmutableList.of(), contentTypePivot, str, str2);
            reportCounterWithTitleIdCdnOriginPivots(this.mIsPmetReportingCoreMetricsTitleIdPivotEnabled, EnumeratedPlaybackPmetMetrics.PLAYBACK_UNEXPECTED_BUFFER_TITLEID_CDN_ORIGIN, ImmutableList.of(), contentTypePivot, str, str2, str3);
            if (!this.mIsBufferingAnalysisPmetReportingEnabled || bufferingAnalysis == null) {
                return;
            }
            EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics = EnumeratedPlaybackPmetMetrics.PLAYBACK_UNEXPECTED_BUFFER_TYPE;
            Separator separator = Separator.COLON;
            reportCounterWithContentTypePivot(enumeratedPlaybackPmetMetrics, ImmutableList.of((BufferingAnalysis.BufferType) separator, bufferingAnalysis.getBufferType()), contentTypePivot);
            reportCounterWithCdnOriginPivots(EnumeratedPlaybackPmetMetrics.PLAYBACK_UNEXPECTED_BUFFER_CDN_ORIGIN_TYPE, ImmutableList.of((BufferingAnalysis.BufferType) separator, bufferingAnalysis.getBufferType()), contentTypePivot, str, str2);
            reportCounterWithTitleIdCdnOriginPivots(this.mIsPmetReportingCoreMetricsTitleIdPivotEnabled, EnumeratedPlaybackPmetMetrics.PLAYBACK_UNEXPECTED_BUFFER_TITLEID_CDN_ORIGIN_TYPE, ImmutableList.of((BufferingAnalysis.BufferType) separator, bufferingAnalysis.getBufferType()), contentTypePivot, str, str2, str3);
        }
    }

    public void reportPlaybackFatalErrorEvent(@Nonnull ContentTypePivot contentTypePivot, @Nullable String str, @Nullable String str2, @Nullable String str3) {
        Preconditions.checkNotNull(contentTypePivot, "contentType");
        reportCounterWithContentTypePivot(EnumeratedPlaybackPmetMetrics.PLAYBACK_FATAL_ERROR, ImmutableList.of(), contentTypePivot);
        reportCounterWithCdnOriginPivots(EnumeratedPlaybackPmetMetrics.PLAYBACK_FATAL_ERROR_CDN_ORIGIN, ImmutableList.of(), contentTypePivot, str, str2);
        reportCounterWithTitleIdCdnOriginPivots(this.mIsPmetReportingCoreMetricsTitleIdPivotEnabled, EnumeratedPlaybackPmetMetrics.PLAYBACK_FATAL_ERROR_TITLEID_CDN_ORIGIN, ImmutableList.of(), contentTypePivot, str, str2, str3);
    }

    public void reportSubtitleMetrics(@Nonnull ReportableString reportableString) {
        Preconditions.checkNotNull(reportableString, "metric");
        if (this.mIsSubtitleMetricsPmetReportingEnabled) {
            Profiler.reportCounterWithParameters(EnumeratedPlaybackPmetMetrics.PLAYBACK_SUBTITLE_METRICS, ImmutableList.of((ReportableString) Separator.COLON, reportableString), ImmutableList.of(ImmutableList.of()));
        }
    }

    public void reportServerInsertedStreamMetrics(@Nonnull ServerInsertedStreamPmetMetrics serverInsertedStreamPmetMetrics) {
        Preconditions.checkNotNull(serverInsertedStreamPmetMetrics, "metric");
        Profiler.reportCounterWithValueParameters(EnumeratedPlaybackPmetMetrics.PLAYBACK_SERVER_INSERTED_STREAM_METRICS, ImmutableList.of(ImmutableList.of(serverInsertedStreamPmetMetrics)));
    }

    public void reportPresentationMetrics(@Nonnull PresentationMetrics presentationMetrics) {
        Preconditions.checkNotNull(presentationMetrics, "metric");
        Profiler.reportCounterWithValueParameters(EnumeratedPlaybackPmetMetrics.PLAYBACK_PRESENTATION_TYPE_METRICS, ImmutableList.of(ImmutableList.of(presentationMetrics)));
    }

    public void reportFragmentURLResolutionErrorMetrics(@Nonnull ReportableString reportableString) {
        Preconditions.checkNotNull(reportableString, "metric");
        if (this.mIsFragmentURLResolutionErrorsPmetReportingEnabled) {
            Profiler.reportCounterWithParameters(EnumeratedPlaybackPmetMetrics.PLAYBACK_FRAGMENT_URL_RESOLUTION_ERROR, ImmutableList.of((ReportableString) Separator.COLON, reportableString), ImmutableList.of(ImmutableList.of()));
        }
    }

    public void reportPlaybackHardwareAccelerationToggled(@Nonnull HardwareAccelerationState hardwareAccelerationState, @Nonnull MediaInternalErrorCode mediaInternalErrorCode) {
        Preconditions.checkNotNull(hardwareAccelerationState, "newState");
        Preconditions.checkNotNull(mediaInternalErrorCode, "errorCode");
        Profiler.reportCounterWithParameters(EnumeratedPlaybackPmetMetrics.PLAYBACK_HARDWARE_ACCELERATION, ImmutableList.of((ReportableString) Separator.COLON, HardwareAccelerationState.toReportableString(hardwareAccelerationState)), ImmutableList.of(ImmutableList.of(mediaInternalErrorCode)));
    }

    public void reportRendererSchemeAvailabilityStatus(@Nonnull String str, @Nonnull AvailabilityStatus availabilityStatus) {
        Preconditions.checkNotNull(str, "rendererScheme");
        Preconditions.checkNotNull(availabilityStatus, "availabilityStatus");
        Profiler.reportCounterWithValueParameters(EnumeratedPlaybackPmetMetrics.PLAYBACK_RENDERER_SCHEME_AVAILABILITY, ImmutableList.of(ImmutableList.of(new RendererSchemeAvailabilityPivot(str, availabilityStatus))));
    }

    public void reportPlaybackPLSMErrorEvent(@Nonnull MediaInternalErrorCode mediaInternalErrorCode, @Nonnull ContentTypePivot contentTypePivot) {
        Preconditions.checkNotNull(mediaInternalErrorCode, "errorCode");
        Preconditions.checkNotNull(contentTypePivot, "contentType");
        reportCounterWithContentTypePivot(EnumeratedPlaybackPmetMetrics.PLAYBACK_PLSM_ERROR_CODE, ImmutableList.of((MediaInternalErrorCode) Separator.COLON, mediaInternalErrorCode), contentTypePivot);
    }

    public void reportLivePlaybackStaleManifestErrorEvent(long j, @Nullable String str, @Nullable String str2, @Nullable String str3) {
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics = j < 0 ? EnumeratedPlaybackPmetMetrics.PLAYBACK_STALE_OLD_MANIFEST : EnumeratedPlaybackPmetMetrics.PLAYBACK_STALE_SAME_MANIFEST;
        ImmutableList<MetricParameter> immutableListOf = ImmutableList.of();
        ContentTypePivot contentTypePivot = ContentTypePivot.LIVE;
        reportCounterWithContentTypePivot(enumeratedPlaybackPmetMetrics, immutableListOf, contentTypePivot);
        reportCounterWithCdnOriginPivots(j < 0 ? EnumeratedPlaybackPmetMetrics.PLAYBACK_STALE_OLD_MANIFEST_CDN_ORIGIN : EnumeratedPlaybackPmetMetrics.PLAYBACK_STALE_SAME_MANIFEST_CDN_ORIGIN, ImmutableList.of(), contentTypePivot, str, str2);
        reportCounterWithTitleIdCdnOriginPivots(this.mIsPmetReportingSecondaryMetricsTitleIdPivotEnabled, j < 0 ? EnumeratedPlaybackPmetMetrics.PLAYBACK_STALE_OLD_MANIFEST_TITLEID_CDN_ORIGIN : EnumeratedPlaybackPmetMetrics.PLAYBACK_STALE_SAME_MANIFEST_TITLEID_CDN_ORIGIN, ImmutableList.of(), contentTypePivot, str, str2, str3);
    }

    public void reportLivePlaybackSlowManifestEvent(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics = EnumeratedPlaybackPmetMetrics.PLAYBACK_SLOW_MANIFEST;
        ImmutableList<MetricParameter> immutableListOf = ImmutableList.of();
        ContentTypePivot contentTypePivot = ContentTypePivot.LIVE;
        reportCounterWithContentTypePivot(enumeratedPlaybackPmetMetrics, immutableListOf, contentTypePivot);
        reportCounterWithCdnOriginPivots(EnumeratedPlaybackPmetMetrics.PLAYBACK_SLOW_MANIFEST_CDN_ORIGIN, ImmutableList.of(), contentTypePivot, str, str2);
        reportCounterWithTitleIdCdnOriginPivots(this.mIsPmetReportingSecondaryMetricsTitleIdPivotEnabled, EnumeratedPlaybackPmetMetrics.PLAYBACK_SLOW_MANIFEST_TITLEID_CDN_ORIGIN, ImmutableList.of(), contentTypePivot, str, str2, str3);
    }

    public void reportLivePlaybackFragmentQuality(@Nonnull VideoResolution.ResolutionBand resolutionBand, @Nullable String str, @Nullable String str2, @Nullable String str3) {
        Preconditions.checkNotNull(resolutionBand, "videoResolution");
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics = EnumeratedPlaybackPmetMetrics.PLAYBACK_FRAGMENT_QUALITY;
        Separator separator = Separator.COLON;
        ImmutableList<MetricParameter> immutableListOf = ImmutableList.of((VideoResolution.ResolutionBand) separator, resolutionBand);
        ContentTypePivot contentTypePivot = ContentTypePivot.LIVE;
        reportCounterWithContentTypePivot(enumeratedPlaybackPmetMetrics, immutableListOf, contentTypePivot);
        reportCounterWithCdnOriginPivots(EnumeratedPlaybackPmetMetrics.PLAYBACK_FRAGMENT_QUALITY_CDN_ORIGIN, ImmutableList.of((VideoResolution.ResolutionBand) separator, resolutionBand), contentTypePivot, str, str2);
        reportCounterWithTitleIdCdnOriginPivots(this.mIsPmetReportingSecondaryMetricsTitleIdPivotEnabled, EnumeratedPlaybackPmetMetrics.PLAYBACK_FRAGMENT_QUALITY_TITLEID_CDN_ORIGIN, ImmutableList.of((VideoResolution.ResolutionBand) separator, resolutionBand), contentTypePivot, str, str2, str3);
    }

    public void reportPlayerRestartEvent(@Nonnull ReportableString reportableString, @Nonnull ContentTypePivot contentTypePivot, @Nullable String str, @Nullable String str2, @Nullable String str3) {
        Preconditions.checkNotNull(reportableString, "restartType");
        Preconditions.checkNotNull(contentTypePivot, "contentType");
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics = EnumeratedPlaybackPmetMetrics.PLAYBACK_PLAYER_RESTART;
        Separator separator = Separator.COLON;
        reportCounterWithContentTypePivot(enumeratedPlaybackPmetMetrics, ImmutableList.of((ReportableString) separator, reportableString), contentTypePivot);
        reportCounterWithCdnOriginPivots(EnumeratedPlaybackPmetMetrics.PLAYBACK_PLAYER_RESTART_CDN_ORIGIN, ImmutableList.of((ReportableString) separator, reportableString), contentTypePivot, str, str2);
        reportCounterWithTitleIdCdnOriginPivots(this.mIsPmetReportingSecondaryMetricsTitleIdPivotEnabled, EnumeratedPlaybackPmetMetrics.PLAYBACK_PLAYER_RESTART_TITLEID_CDN_ORIGIN, ImmutableList.of((ReportableString) separator, reportableString), contentTypePivot, str, str2, str3);
    }

    private void reportCounterWithContentTypePivot(@Nonnull EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics, @Nonnull ImmutableList<MetricParameter> immutableList, @Nonnull ContentTypePivot contentTypePivot) {
        Preconditions.checkNotNull(enumeratedPlaybackPmetMetrics, "metric");
        Preconditions.checkNotNull(immutableList, "requiredNameParameters");
        Preconditions.checkNotNull(contentTypePivot, "contentType");
        Profiler.reportCounterWithParameters(enumeratedPlaybackPmetMetrics, immutableList, ImmutableList.of(ImmutableList.of(), ImmutableList.of(contentTypePivot)));
    }

    private void reportCounterWithCdnOriginPivots(@Nonnull EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics, @Nonnull ImmutableList<MetricParameter> immutableList, @Nonnull ContentTypePivot contentTypePivot, @Nullable String str, @Nullable String str2) {
        Preconditions.checkNotNull(enumeratedPlaybackPmetMetrics, "metric");
        Preconditions.checkNotNull(immutableList, "requiredNameParameters");
        Preconditions.checkNotNull(contentTypePivot, "contentType");
        if (this.mIsPmetReportingCdnOriginPivotsEnabled) {
            if (contentTypePivot == ContentTypePivot.LIVE || (this.mIsPmetReportingVodOriginPivotsEnabled && contentTypePivot == ContentTypePivot.VOD)) {
                Profiler.reportCounterWithParameters(enumeratedPlaybackPmetMetrics, immutableList, ImmutableList.of(ImmutableList.of(new CdnPivot(str)), ImmutableList.of(new OriginPivot(str2))));
            }
        }
    }

    private void reportCounterWithTitleIdCdnOriginPivots(boolean z, @Nonnull EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics, @Nonnull ImmutableList<MetricParameter> immutableList, @Nonnull ContentTypePivot contentTypePivot, @Nullable String str, @Nullable String str2, @Nullable String str3) {
        Preconditions.checkNotNull(enumeratedPlaybackPmetMetrics, "metric");
        Preconditions.checkNotNull(immutableList, "requiredNameParameters");
        Preconditions.checkNotNull(contentTypePivot, "contentType");
        if (this.mIsPmetReportingCdnOriginPivotsEnabled && z && contentTypePivot == ContentTypePivot.LIVE && str3 != null && this.mTitleIdPivotsWhitelist.contains(str3)) {
            ImmutableList.Builder builder = ImmutableList.builder();
            UnmodifiableIterator<MetricParameter> it = immutableList.iterator();
            while (it.hasNext()) {
                builder.add(it.next());
            }
            builder.add(new TitleIdPivot(str3, this.mTitleIdPivotsWhitelist));
            Profiler.reportCounterWithParameters(enumeratedPlaybackPmetMetrics, builder.build(), ImmutableList.of(ImmutableList.of(contentTypePivot), ImmutableList.of(new CdnPivot(str)), ImmutableList.of(new OriginPivot(str2))));
        }
    }

    public void reportTimerMetric(@Nonnull PlaybackPmetMetric playbackPmetMetric, @Nullable String str, long j, @Nonnull ContentTypePivot contentTypePivot, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        char c;
        String str5;
        char c2;
        Preconditions.checkNotNull(playbackPmetMetric, "metric");
        Preconditions.checkNotNull(contentTypePivot, "contentType");
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        objArr[0] = playbackPmetMetric.getMetricName();
        String str6 = "";
        objArr[1] = str != null ? String.format(locale, ":%s", str) : "";
        Profiler.reportTimerMetric(new DurationMetric(String.format(locale, "%s%s", objArr), ImmutableList.of("Counter", String.format(locale, "%s%s", "ContentType:", contentTypePivot)), j));
        if (this.mIsPmetReportingCdnOriginPivotsEnabled && contentTypePivot == ContentTypePivot.LIVE) {
            Object[] objArr2 = new Object[2];
            objArr2[0] = playbackPmetMetric.getMetricName();
            if (str != null) {
                c = 1;
                str5 = String.format(locale, ":%s", str);
            } else {
                c = 1;
                str5 = "";
            }
            objArr2[c] = str5;
            Profiler.reportTimerMetric(new DurationMetric(String.format(locale, "%s%s", objArr2), ImmutableList.of(String.format(locale, "%s%s", "CDN:", new CdnPivot(str2).toReportableString()), String.format(locale, "%s%s", "Origin:", new OriginPivot(str3).toReportableString())), j));
            if (this.mIsPmetReportingSecondaryMetricsTitleIdPivotEnabled && str4 != null && this.mTitleIdPivotsWhitelist.contains(str4)) {
                Object[] objArr3 = new Object[3];
                objArr3[0] = playbackPmetMetric.getMetricName();
                if (str != null) {
                    c2 = 1;
                    str6 = String.format(locale, ":%s", str);
                } else {
                    c2 = 1;
                }
                objArr3[c2] = str6;
                objArr3[2] = new TitleIdPivot(str4, this.mTitleIdPivotsWhitelist).toReportableString();
                Profiler.reportTimerMetric(new DurationMetric(String.format(locale, "%s%s%s", objArr3), ImmutableList.of(contentTypePivot.toReportableString(), String.format(locale, "%s%s", "CDN:", new CdnPivot(str2).toReportableString()), String.format(locale, "%s%s", "Origin:", new OriginPivot(str3).toReportableString())), j));
            }
        }
    }

    public void reportImageDownloaderCounterMetric(@Nonnull LiveTrickplayMetrics$ImageDownloaderCounterMetrics liveTrickplayMetrics$ImageDownloaderCounterMetrics, @Nonnull String str, long j) {
        Preconditions.checkNotNull(liveTrickplayMetrics$ImageDownloaderCounterMetrics, "imageDownloaderCounterMetrics");
        Preconditions.checkNotNull(str, "imageDownloaderStrategy");
        if (str.equals("MULTIPASS")) {
            Profiler.reportCounterWithValueParameters(EnumeratedPlaybackPmetMetrics.IMAGE_DOWNLOADER_MULTIPASS, ImmutableList.of(ImmutableList.of(liveTrickplayMetrics$ImageDownloaderCounterMetrics)), j);
        } else {
            Profiler.reportCounterWithValueParameters(EnumeratedPlaybackPmetMetrics.IMAGE_DOWNLOADER_SERIAL, ImmutableList.of(ImmutableList.of(liveTrickplayMetrics$ImageDownloaderCounterMetrics)), j);
        }
    }

    public void reportImageDownloaderTimerMetric(@Nonnull LiveTrickplayMetrics$ImageDownloaderTimerMetrics liveTrickplayMetrics$ImageDownloaderTimerMetrics, @Nonnull String str, long j) {
        Preconditions.checkNotNull(liveTrickplayMetrics$ImageDownloaderTimerMetrics, "imageDownloaderTimerMetrics");
        Preconditions.checkNotNull(str, "imageDownloaderStrategy");
        Profiler.reportTimerMetric(new DurationMetric(PlaybackPmetMetric.IMAGE_DOWNLOADER.getMetricName(), ImmutableList.of(String.format(Locale.US, "%s:%s", str, liveTrickplayMetrics$ImageDownloaderTimerMetrics.toReportableString())), j));
    }

    public void reportLiveTrickplayTimerMetric(@Nonnull LiveTrickplayMetrics$LiveTrickplayTimerMetrics liveTrickplayMetrics$LiveTrickplayTimerMetrics, @Nonnull String str, long j) {
        Preconditions.checkNotNull(liveTrickplayMetrics$LiveTrickplayTimerMetrics, "liveTrickplayTimerMetrics");
        Preconditions.checkNotNull(str, "trickplayEventType");
        Profiler.reportTimerMetric(new DurationMetric(PlaybackPmetMetric.LIVE_TRICKPLAY.getMetricName(), ImmutableList.of(String.format(Locale.US, "%s:%s", str, liveTrickplayMetrics$LiveTrickplayTimerMetrics.toReportableString())), j));
    }

    public void reportCacheOperationTimerMetric(@Nonnull WhisperCacheMetrics$CacheOperation whisperCacheMetrics$CacheOperation, long j) {
        Preconditions.checkNotNull(whisperCacheMetrics$CacheOperation, "cacheOperationTimerMetric");
        if (this.mIsCacheOperationPmetReportingEnabled) {
            Profiler.reportTimerMetric(new DurationMetric(PlaybackPmetMetric.CACHE_OPERATION.getMetricName(), ImmutableList.of(whisperCacheMetrics$CacheOperation.toReportableString()), j));
        }
    }

    public void reportCacheStatusTimerMetric(@Nonnull WhisperCacheMetrics$CacheStatus whisperCacheMetrics$CacheStatus, long j) {
        Preconditions.checkNotNull(whisperCacheMetrics$CacheStatus, "cacheStatusTimerMetric");
        if (this.mIsCacheStatusPmetReportingEnabled) {
            Profiler.reportTimerMetric(new DurationMetric(PlaybackPmetMetric.CACHE_STATUS.getMetricName(), ImmutableList.of(whisperCacheMetrics$CacheStatus.toReportableString()), j));
        }
    }

    public void reportCacheEvictionTimerMetric(@Nonnull WhisperCacheMetrics$CacheEviction whisperCacheMetrics$CacheEviction, long j) {
        Preconditions.checkNotNull(whisperCacheMetrics$CacheEviction, "evictionReason");
        if (this.mIsCacheEvictionPmetReportingEnabled) {
            Profiler.reportTimerMetric(new DurationMetric(PlaybackPmetMetric.CACHE_EVICTION.getMetricName(), ImmutableList.of(whisperCacheMetrics$CacheEviction.toReportableString()), j));
        }
    }

    public void reportTotalDroppedFramesMetric(int i, @Nonnull String str, @Nonnull String str2) {
        Preconditions.checkNotNull(str, "rendererScheme");
        Preconditions.checkNotNull(str2, "drmScheme");
        Profiler.reportTimerMetric(new DurationMetric(PlaybackPmetMetric.TOTAL_DROPPED_FRAMES.getMetricName(), ImmutableList.of("Counter", String.format(Locale.US, "%s%s_%s", "RendererDrm:", str, str2)), i));
    }

    public void reportContentStoreTypeMetric(@Nonnull ContentTypePivot contentTypePivot, @Nonnull ContentStoreType contentStoreType) {
        Preconditions.checkNotNull(contentTypePivot, "contentType");
        Preconditions.checkNotNull(contentStoreType, "storeType");
        Profiler.reportCounterWithParameters(EnumeratedPlaybackPmetMetrics.PLAYBACK_CONTENT_STORE, ImmutableList.of((ContentStoreType) Separator.COLON, contentStoreType), ImmutableList.of(ImmutableList.of(), ImmutableList.of(contentTypePivot)));
    }

    public void reportTtffTracesTimerMetric(@Nonnull LifecycleProfilerMetrics lifecycleProfilerMetrics, long j) {
        Profiler.reportTimerMetric(new DurationMetric(PlaybackPmetMetric.TTFF_TRACE.getMetricName(), ImmutableList.of(lifecycleProfilerMetrics.toReportableString()), j));
    }

    public void reportBufferFullnessBreachThresholdEvent(@Nonnull ContentTypePivot contentTypePivot, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nonnull BufferType bufferType) {
        Preconditions.checkNotNull(contentTypePivot, "contentType");
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics = EnumeratedPlaybackPmetMetrics.BUFFER_FULLNESS_BREACH;
        Separator separator = Separator.COLON;
        reportCounterWithContentTypePivot(enumeratedPlaybackPmetMetrics, ImmutableList.of((BufferType) separator, bufferType), contentTypePivot);
        reportCounterWithCdnOriginPivots(EnumeratedPlaybackPmetMetrics.BUFFER_FULLNESS_BREACH_CDN_ORIGIN, ImmutableList.of((BufferType) separator, bufferType), contentTypePivot, str, str2);
        reportCounterWithTitleIdCdnOriginPivots(this.mIsPmetReportingCoreMetricsTitleIdPivotEnabled, EnumeratedPlaybackPmetMetrics.BUFFER_FULLNESS_BREACH_TITLEID_CDN_ORIGIN_TYPE, ImmutableList.of((BufferType) separator, bufferType), contentTypePivot, str, str2, str3);
    }
}
