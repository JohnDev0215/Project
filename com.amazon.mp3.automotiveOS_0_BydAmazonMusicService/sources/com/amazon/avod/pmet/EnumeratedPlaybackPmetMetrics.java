package com.amazon.avod.pmet;

import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.framework.error.MediaInternalErrorCode;
import com.amazon.avod.metrics.InPlaybackRatingMetrics;
import com.amazon.avod.metrics.pmet.EnumeratedCounterMetricTemplate;
import com.amazon.avod.metrics.pmet.MetricParameter;
import com.amazon.avod.metrics.pmet.PlaybackVoiceControls;
import com.amazon.avod.metrics.pmet.PlayerMetricComponent;
import com.amazon.avod.metrics.pmet.internal.MetricNameTemplate;
import com.amazon.avod.metrics.pmet.internal.MetricValueTemplates;
import com.amazon.avod.metrics.pmet.internal.ValidatedCounterMetric;
import com.amazon.avod.metrics.pmet.util.ReportableString;
import com.amazon.avod.metrics.pmet.util.Separator;
import com.amazon.avod.perf.MetricPriority;
import com.amazon.avod.playback.BufferType;
import com.amazon.avod.playback.BufferingAnalysis;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'PLAYBACK_SESSION_PLAYING_PRIORITY_NORMAL' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes2.dex */
public final class EnumeratedPlaybackPmetMetrics implements EnumeratedCounterMetricTemplate {
    private static final /* synthetic */ EnumeratedPlaybackPmetMetrics[] $VALUES;
    public static final EnumeratedPlaybackPmetMetrics BUFFER_FULLNESS_BREACH;
    public static final EnumeratedPlaybackPmetMetrics BUFFER_FULLNESS_BREACH_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics BUFFER_FULLNESS_BREACH_TITLEID_CDN_ORIGIN_TYPE;
    public static final EnumeratedPlaybackPmetMetrics IMAGE_DOWNLOADER_MULTIPASS;
    public static final EnumeratedPlaybackPmetMetrics IMAGE_DOWNLOADER_SERIAL;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_AD_BREAK_ERROR;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_AD_CLIP_ERROR;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_AD_SKIP;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_AVSM_ERROR_CODE;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_CONTENT_STORE;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_CRSM_ERROR_CODE;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_FATAL_ERROR;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_FATAL_ERROR_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_FATAL_ERROR_TITLEID_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_FRAGMENT_QUALITY;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_FRAGMENT_QUALITY_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_FRAGMENT_QUALITY_TITLEID_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_FRAGMENT_URL_RESOLUTION_ERROR;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_HARDWARE_ACCELERATION;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_PLAYER_RESTART;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_PLAYER_RESTART_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_PLAYER_RESTART_TITLEID_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_PLSM_ERROR_CODE;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_PRESENTATION_TYPE_METRICS;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_RATING;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_RENDERER_SCHEME_AVAILABILITY;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_SERVER_INSERTED_STREAM_METRICS;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_SESSION_PLAYING_PRIORITY_HIGH;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_SESSION_PLAYING_PRIORITY_HIGH_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_SESSION_PLAYING_PRIORITY_HIGH_TITLEID_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_SESSION_PLAYING_PRIORITY_NORMAL;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_SESSION_PLAYING_PRIORITY_NORMAL_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_SESSION_PLAYING_PRIORITY_NORMAL_TITLEID_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_SESSION_TOKEN_HEARTBEAT_COUNTER;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_SESSION_TOKEN_HEARTBEAT_TOTAL_SKIPPED;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_SLOW_MANIFEST;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_SLOW_MANIFEST_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_SLOW_MANIFEST_TITLEID_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_STALE_OLD_MANIFEST;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_STALE_OLD_MANIFEST_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_STALE_OLD_MANIFEST_TITLEID_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_STALE_SAME_MANIFEST;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_STALE_SAME_MANIFEST_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_STALE_SAME_MANIFEST_TITLEID_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_SUBTITLE_ERROR;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_SUBTITLE_METRICS;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_UNEXPECTED_BUFFER;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_UNEXPECTED_BUFFER_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_UNEXPECTED_BUFFER_CDN_ORIGIN_TYPE;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_UNEXPECTED_BUFFER_TITLEID_CDN_ORIGIN;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_UNEXPECTED_BUFFER_TITLEID_CDN_ORIGIN_TYPE;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_UNEXPECTED_BUFFER_TYPE;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_VDSM_ERROR;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_VDSM_ERROR_CODE;
    public static final EnumeratedPlaybackPmetMetrics PLAYBACK_VOICE_CONTROL;
    final MetricNameTemplate mNameTemplate;
    final MetricPriority mPriority;
    final MetricValueTemplates mValueTemplates;

    public static EnumeratedPlaybackPmetMetrics valueOf(String str) {
        return (EnumeratedPlaybackPmetMetrics) Enum.valueOf(EnumeratedPlaybackPmetMetrics.class, str);
    }

    public static EnumeratedPlaybackPmetMetrics[] values() {
        return (EnumeratedPlaybackPmetMetrics[]) $VALUES.clone();
    }

    static {
        PlaybackPmetMetric playbackPmetMetric = PlaybackPmetMetric.SESSION_PLAYING;
        MetricNameTemplate metricNameTemplate = new MetricNameTemplate(playbackPmetMetric.getMetricName());
        MetricValueTemplates metricValueTemplatesBuild = MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build();
        MetricPriority metricPriority = MetricPriority.NORMAL;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics = new EnumeratedPlaybackPmetMetrics("PLAYBACK_SESSION_PLAYING_PRIORITY_NORMAL", 0, metricNameTemplate, metricValueTemplatesBuild, metricPriority);
        PLAYBACK_SESSION_PLAYING_PRIORITY_NORMAL = enumeratedPlaybackPmetMetrics;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics2 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_SESSION_PLAYING_PRIORITY_NORMAL_CDN_ORIGIN", 1, new MetricNameTemplate(playbackPmetMetric.getMetricName()), MetricValueTemplates.emptyBuilder().add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority);
        PLAYBACK_SESSION_PLAYING_PRIORITY_NORMAL_CDN_ORIGIN = enumeratedPlaybackPmetMetrics2;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics3 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_SESSION_PLAYING_PRIORITY_NORMAL_TITLEID_CDN_ORIGIN", 2, new MetricNameTemplate(playbackPmetMetric.getMetricName(), ImmutableList.of(TitleIdPivot.class)), MetricValueTemplates.emptyBuilder().add("ContentType:", ContentTypePivot.class).add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority);
        PLAYBACK_SESSION_PLAYING_PRIORITY_NORMAL_TITLEID_CDN_ORIGIN = enumeratedPlaybackPmetMetrics3;
        MetricNameTemplate metricNameTemplate2 = new MetricNameTemplate(playbackPmetMetric.getMetricName());
        MetricValueTemplates metricValueTemplatesBuild2 = MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build();
        MetricPriority metricPriority2 = MetricPriority.HIGH;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics4 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_SESSION_PLAYING_PRIORITY_HIGH", 3, metricNameTemplate2, metricValueTemplatesBuild2, metricPriority2);
        PLAYBACK_SESSION_PLAYING_PRIORITY_HIGH = enumeratedPlaybackPmetMetrics4;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics5 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_SESSION_PLAYING_PRIORITY_HIGH_CDN_ORIGIN", 4, new MetricNameTemplate(playbackPmetMetric.getMetricName()), MetricValueTemplates.emptyBuilder().add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority2);
        PLAYBACK_SESSION_PLAYING_PRIORITY_HIGH_CDN_ORIGIN = enumeratedPlaybackPmetMetrics5;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics6 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_SESSION_PLAYING_PRIORITY_HIGH_TITLEID_CDN_ORIGIN", 5, new MetricNameTemplate(playbackPmetMetric.getMetricName(), ImmutableList.of(TitleIdPivot.class)), MetricValueTemplates.emptyBuilder().add("ContentType:", ContentTypePivot.class).add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority2);
        PLAYBACK_SESSION_PLAYING_PRIORITY_HIGH_TITLEID_CDN_ORIGIN = enumeratedPlaybackPmetMetrics6;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics7 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_PRESENTATION_TYPE_METRICS", 6, new MetricNameTemplate(PlaybackPmetMetric.PRESENTATION_TYPE_METRICS.getMetricName()), MetricValueTemplates.emptyBuilder().add("", PresentationMetrics.class).build(), metricPriority);
        PLAYBACK_PRESENTATION_TYPE_METRICS = enumeratedPlaybackPmetMetrics7;
        PlaybackPmetMetric playbackPmetMetric2 = PlaybackPmetMetric.UNEXPECTED_BUFFER;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics8 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_UNEXPECTED_BUFFER", 7, new MetricNameTemplate(playbackPmetMetric2.getMetricName()), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority2);
        PLAYBACK_UNEXPECTED_BUFFER = enumeratedPlaybackPmetMetrics8;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics9 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_UNEXPECTED_BUFFER_CDN_ORIGIN", 8, new MetricNameTemplate(playbackPmetMetric2.getMetricName()), MetricValueTemplates.emptyBuilder().add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority2);
        PLAYBACK_UNEXPECTED_BUFFER_CDN_ORIGIN = enumeratedPlaybackPmetMetrics9;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics10 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_UNEXPECTED_BUFFER_TITLEID_CDN_ORIGIN", 9, new MetricNameTemplate(playbackPmetMetric2.getMetricName(), ImmutableList.of(TitleIdPivot.class)), MetricValueTemplates.emptyBuilder().add("ContentType:", ContentTypePivot.class).add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority2);
        PLAYBACK_UNEXPECTED_BUFFER_TITLEID_CDN_ORIGIN = enumeratedPlaybackPmetMetrics10;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics11 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_UNEXPECTED_BUFFER_TYPE", 10, new MetricNameTemplate(playbackPmetMetric2.getMetricName(), ImmutableList.of(Separator.class, BufferingAnalysis.BufferType.class)), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority2);
        PLAYBACK_UNEXPECTED_BUFFER_TYPE = enumeratedPlaybackPmetMetrics11;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics12 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_UNEXPECTED_BUFFER_CDN_ORIGIN_TYPE", 11, new MetricNameTemplate(playbackPmetMetric2.getMetricName(), ImmutableList.of(Separator.class, BufferingAnalysis.BufferType.class)), MetricValueTemplates.emptyBuilder().add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority2);
        PLAYBACK_UNEXPECTED_BUFFER_CDN_ORIGIN_TYPE = enumeratedPlaybackPmetMetrics12;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics13 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_UNEXPECTED_BUFFER_TITLEID_CDN_ORIGIN_TYPE", 12, new MetricNameTemplate(playbackPmetMetric2.getMetricName(), ImmutableList.of(Separator.class, BufferingAnalysis.BufferType.class, TitleIdPivot.class)), MetricValueTemplates.emptyBuilder().add("ContentType:", ContentTypePivot.class).add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority2);
        PLAYBACK_UNEXPECTED_BUFFER_TITLEID_CDN_ORIGIN_TYPE = enumeratedPlaybackPmetMetrics13;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics14 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_AD_SKIP", 13, new MetricNameTemplate(PlaybackPmetMetric.PLAYBACK_AD_SKIP.getMetricName()), MetricValueTemplates.emptyBuilder().add("Origin:", OriginPivot.class).build(), metricPriority);
        PLAYBACK_AD_SKIP = enumeratedPlaybackPmetMetrics14;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics15 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_AD_CLIP_ERROR", 14, new MetricNameTemplate(PlaybackPmetMetric.AD_CLIP_ERROR.getMetricName(), ImmutableList.of(Separator.class, ReportableString.class)), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority);
        PLAYBACK_AD_CLIP_ERROR = enumeratedPlaybackPmetMetrics15;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics16 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_AD_BREAK_ERROR", 15, new MetricNameTemplate(PlaybackPmetMetric.AD_BREAK_ERROR.getMetricName(), ImmutableList.of(Separator.class, ReportableString.class)), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority);
        PLAYBACK_AD_BREAK_ERROR = enumeratedPlaybackPmetMetrics16;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics17 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_SUBTITLE_ERROR", 16, new MetricNameTemplate(PlaybackPmetMetric.SUBTITLE_ERROR.getMetricName(), ImmutableList.of(Separator.class, ReportableString.class)), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority);
        PLAYBACK_SUBTITLE_ERROR = enumeratedPlaybackPmetMetrics17;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics18 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_SUBTITLE_METRICS", 17, new MetricNameTemplate(PlaybackPmetMetric.SUBTITLE_METRICS.getMetricName(), ImmutableList.of(Separator.class, ReportableString.class)), MetricValueTemplates.defaultBuilder().build(), metricPriority);
        PLAYBACK_SUBTITLE_METRICS = enumeratedPlaybackPmetMetrics18;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics19 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_SERVER_INSERTED_STREAM_METRICS", 18, new MetricNameTemplate(PlaybackPmetMetric.SERVER_INSERTED_STREAM_METRICS.getMetricName()), MetricValueTemplates.emptyBuilder().add("", ServerInsertedStreamPmetMetrics.class).build(), metricPriority);
        PLAYBACK_SERVER_INSERTED_STREAM_METRICS = enumeratedPlaybackPmetMetrics19;
        PlaybackPmetMetric playbackPmetMetric3 = PlaybackPmetMetric.FATAL_ERROR;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics20 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_FATAL_ERROR", 19, new MetricNameTemplate(playbackPmetMetric3.getMetricName()), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority2);
        PLAYBACK_FATAL_ERROR = enumeratedPlaybackPmetMetrics20;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics21 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_FATAL_ERROR_CDN_ORIGIN", 20, new MetricNameTemplate(playbackPmetMetric3.getMetricName()), MetricValueTemplates.emptyBuilder().add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority2);
        PLAYBACK_FATAL_ERROR_CDN_ORIGIN = enumeratedPlaybackPmetMetrics21;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics22 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_FATAL_ERROR_TITLEID_CDN_ORIGIN", 21, new MetricNameTemplate(playbackPmetMetric3.getMetricName(), ImmutableList.of(TitleIdPivot.class)), MetricValueTemplates.emptyBuilder().add("ContentType:", ContentTypePivot.class).add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority2);
        PLAYBACK_FATAL_ERROR_TITLEID_CDN_ORIGIN = enumeratedPlaybackPmetMetrics22;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics23 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_CRSM_ERROR_CODE", 22, new MetricNameTemplate(PlaybackPmetMetric.CRSM_ERROR.getMetricName(), ImmutableList.of(Separator.class, MediaErrorCode.class)), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority);
        PLAYBACK_CRSM_ERROR_CODE = enumeratedPlaybackPmetMetrics23;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics24 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_AVSM_ERROR_CODE", 23, new MetricNameTemplate(PlaybackPmetMetric.AVSM_ERROR.getMetricName(), ImmutableList.of(Separator.class, MediaErrorCode.class)), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority);
        PLAYBACK_AVSM_ERROR_CODE = enumeratedPlaybackPmetMetrics24;
        PlaybackPmetMetric playbackPmetMetric4 = PlaybackPmetMetric.VDSM_ERROR;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics25 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_VDSM_ERROR_CODE", 24, new MetricNameTemplate(playbackPmetMetric4.getMetricName(), ImmutableList.of(Separator.class, MediaErrorCode.class)), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority);
        PLAYBACK_VDSM_ERROR_CODE = enumeratedPlaybackPmetMetrics25;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics26 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_VDSM_ERROR", 25, new MetricNameTemplate(playbackPmetMetric4.getMetricName(), ImmutableList.of(Separator.class, ReportableString.class)), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority);
        PLAYBACK_VDSM_ERROR = enumeratedPlaybackPmetMetrics26;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics27 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_PLSM_ERROR_CODE", 26, new MetricNameTemplate(PlaybackPmetMetric.PLSM_ERROR.getMetricName(), ImmutableList.of(Separator.class, MediaInternalErrorCode.class)), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority);
        PLAYBACK_PLSM_ERROR_CODE = enumeratedPlaybackPmetMetrics27;
        PlaybackPmetMetric playbackPmetMetric5 = PlaybackPmetMetric.STALE_SAME_MANIFEST;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics28 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_STALE_SAME_MANIFEST", 27, new MetricNameTemplate(playbackPmetMetric5.getMetricName()), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority);
        PLAYBACK_STALE_SAME_MANIFEST = enumeratedPlaybackPmetMetrics28;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics29 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_STALE_SAME_MANIFEST_CDN_ORIGIN", 28, new MetricNameTemplate(playbackPmetMetric5.getMetricName()), MetricValueTemplates.emptyBuilder().add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority);
        PLAYBACK_STALE_SAME_MANIFEST_CDN_ORIGIN = enumeratedPlaybackPmetMetrics29;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics30 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_STALE_SAME_MANIFEST_TITLEID_CDN_ORIGIN", 29, new MetricNameTemplate(playbackPmetMetric5.getMetricName(), ImmutableList.of(TitleIdPivot.class)), MetricValueTemplates.emptyBuilder().add("ContentType:", ContentTypePivot.class).add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority);
        PLAYBACK_STALE_SAME_MANIFEST_TITLEID_CDN_ORIGIN = enumeratedPlaybackPmetMetrics30;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics31 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_FRAGMENT_URL_RESOLUTION_ERROR", 30, new MetricNameTemplate(PlaybackPmetMetric.FRAGMENT_URL_RESOLUTION_ERROR.getMetricName(), ImmutableList.of(Separator.class, ReportableString.class)), MetricValueTemplates.defaultBuilder().build(), metricPriority2);
        PLAYBACK_FRAGMENT_URL_RESOLUTION_ERROR = enumeratedPlaybackPmetMetrics31;
        PlaybackPmetMetric playbackPmetMetric6 = PlaybackPmetMetric.STALE_OLD_MANIFEST;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics32 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_STALE_OLD_MANIFEST", 31, new MetricNameTemplate(playbackPmetMetric6.getMetricName()), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority);
        PLAYBACK_STALE_OLD_MANIFEST = enumeratedPlaybackPmetMetrics32;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics33 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_STALE_OLD_MANIFEST_CDN_ORIGIN", 32, new MetricNameTemplate(playbackPmetMetric6.getMetricName()), MetricValueTemplates.emptyBuilder().add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority);
        PLAYBACK_STALE_OLD_MANIFEST_CDN_ORIGIN = enumeratedPlaybackPmetMetrics33;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics34 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_STALE_OLD_MANIFEST_TITLEID_CDN_ORIGIN", 33, new MetricNameTemplate(playbackPmetMetric6.getMetricName(), ImmutableList.of(TitleIdPivot.class)), MetricValueTemplates.emptyBuilder().add("ContentType:", ContentTypePivot.class).add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority);
        PLAYBACK_STALE_OLD_MANIFEST_TITLEID_CDN_ORIGIN = enumeratedPlaybackPmetMetrics34;
        PlaybackPmetMetric playbackPmetMetric7 = PlaybackPmetMetric.SLOW_MANIFEST;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics35 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_SLOW_MANIFEST", 34, new MetricNameTemplate(playbackPmetMetric7.getMetricName()), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority);
        PLAYBACK_SLOW_MANIFEST = enumeratedPlaybackPmetMetrics35;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics36 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_SLOW_MANIFEST_CDN_ORIGIN", 35, new MetricNameTemplate(playbackPmetMetric7.getMetricName()), MetricValueTemplates.emptyBuilder().add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority);
        PLAYBACK_SLOW_MANIFEST_CDN_ORIGIN = enumeratedPlaybackPmetMetrics36;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics37 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_SLOW_MANIFEST_TITLEID_CDN_ORIGIN", 36, new MetricNameTemplate(playbackPmetMetric7.getMetricName(), ImmutableList.of(TitleIdPivot.class)), MetricValueTemplates.emptyBuilder().add("ContentType:", ContentTypePivot.class).add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority);
        PLAYBACK_SLOW_MANIFEST_TITLEID_CDN_ORIGIN = enumeratedPlaybackPmetMetrics37;
        PlaybackPmetMetric playbackPmetMetric8 = PlaybackPmetMetric.FRAGMENT_QUALITY;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics38 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_FRAGMENT_QUALITY", 37, new MetricNameTemplate(playbackPmetMetric8.getMetricName(), ImmutableList.of(Separator.class, VideoResolution.ResolutionBand.class)), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority);
        PLAYBACK_FRAGMENT_QUALITY = enumeratedPlaybackPmetMetrics38;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics39 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_FRAGMENT_QUALITY_CDN_ORIGIN", 38, new MetricNameTemplate(playbackPmetMetric8.getMetricName(), ImmutableList.of(Separator.class, VideoResolution.ResolutionBand.class)), MetricValueTemplates.emptyBuilder().add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority);
        PLAYBACK_FRAGMENT_QUALITY_CDN_ORIGIN = enumeratedPlaybackPmetMetrics39;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics40 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_FRAGMENT_QUALITY_TITLEID_CDN_ORIGIN", 39, new MetricNameTemplate(playbackPmetMetric8.getMetricName(), ImmutableList.of(Separator.class, VideoResolution.ResolutionBand.class, TitleIdPivot.class)), MetricValueTemplates.emptyBuilder().add("ContentType:", ContentTypePivot.class).add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority);
        PLAYBACK_FRAGMENT_QUALITY_TITLEID_CDN_ORIGIN = enumeratedPlaybackPmetMetrics40;
        PlaybackPmetMetric playbackPmetMetric9 = PlaybackPmetMetric.PLAYER_RESTART;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics41 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_PLAYER_RESTART", 40, new MetricNameTemplate(playbackPmetMetric9.getMetricName(), ImmutableList.of(Separator.class, ReportableString.class)), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority);
        PLAYBACK_PLAYER_RESTART = enumeratedPlaybackPmetMetrics41;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics42 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_PLAYER_RESTART_CDN_ORIGIN", 41, new MetricNameTemplate(playbackPmetMetric9.getMetricName(), ImmutableList.of(Separator.class, ReportableString.class)), MetricValueTemplates.emptyBuilder().add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority);
        PLAYBACK_PLAYER_RESTART_CDN_ORIGIN = enumeratedPlaybackPmetMetrics42;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics43 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_PLAYER_RESTART_TITLEID_CDN_ORIGIN", 42, new MetricNameTemplate(playbackPmetMetric9.getMetricName(), ImmutableList.of(Separator.class, ReportableString.class, TitleIdPivot.class)), MetricValueTemplates.emptyBuilder().add("ContentType:", ContentTypePivot.class).add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority);
        PLAYBACK_PLAYER_RESTART_TITLEID_CDN_ORIGIN = enumeratedPlaybackPmetMetrics43;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics44 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_HARDWARE_ACCELERATION", 43, new MetricNameTemplate(PlaybackPmetMetric.HARDWARE_ACCELERATION.getMetricName(), ImmutableList.of(Separator.class, ReportableString.class)), MetricValueTemplates.emptyBuilder().add("", MediaInternalErrorCode.class).build(), metricPriority);
        PLAYBACK_HARDWARE_ACCELERATION = enumeratedPlaybackPmetMetrics44;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics45 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_RENDERER_SCHEME_AVAILABILITY", 44, new MetricNameTemplate(PlaybackPmetMetric.RENDERER_SCHEME_AVAILABILITY.getMetricName()), MetricValueTemplates.emptyBuilder().add("", RendererSchemeAvailabilityPivot.class).build(), metricPriority);
        PLAYBACK_RENDERER_SCHEME_AVAILABILITY = enumeratedPlaybackPmetMetrics45;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics46 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_VOICE_CONTROL", 45, new MetricNameTemplate(PlaybackPmetMetric.PLAYBACK_VOICE_CONTROL.getMetricName()), MetricValueTemplates.emptyBuilder().add("", PlaybackVoiceControls.class).build(), metricPriority);
        PLAYBACK_VOICE_CONTROL = enumeratedPlaybackPmetMetrics46;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics47 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_RATING", 46, new MetricNameTemplate(PlaybackPmetMetric.IN_PLAYBACK_RATING.getMetricName()), MetricValueTemplates.emptyBuilder().add("", InPlaybackRatingMetrics.class).build(), metricPriority);
        PLAYBACK_RATING = enumeratedPlaybackPmetMetrics47;
        PlaybackPmetMetric playbackPmetMetric10 = PlaybackPmetMetric.IMAGE_DOWNLOADER;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics48 = new EnumeratedPlaybackPmetMetrics("IMAGE_DOWNLOADER_MULTIPASS", 47, new MetricNameTemplate(playbackPmetMetric10.getMetricName()), MetricValueTemplates.emptyBuilder().add("MULTIPASS:", LiveTrickplayMetrics$ImageDownloaderCounterMetrics.class).build(), metricPriority);
        IMAGE_DOWNLOADER_MULTIPASS = enumeratedPlaybackPmetMetrics48;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics49 = new EnumeratedPlaybackPmetMetrics("IMAGE_DOWNLOADER_SERIAL", 48, new MetricNameTemplate(playbackPmetMetric10.getMetricName()), MetricValueTemplates.emptyBuilder().add("SERIAL:", LiveTrickplayMetrics$ImageDownloaderCounterMetrics.class).build(), metricPriority);
        IMAGE_DOWNLOADER_SERIAL = enumeratedPlaybackPmetMetrics49;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics50 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_CONTENT_STORE", 49, new MetricNameTemplate(PlaybackPmetMetric.CONTENT_STORE_TYPE.getMetricName(), ImmutableList.of(Separator.class, ContentStoreType.class)), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority);
        PLAYBACK_CONTENT_STORE = enumeratedPlaybackPmetMetrics50;
        PlaybackPmetMetric playbackPmetMetric11 = PlaybackPmetMetric.UPDATE_STREAM_WITH_SESSION_TOKEN;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics51 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_SESSION_TOKEN_HEARTBEAT_COUNTER", 50, new MetricNameTemplate(playbackPmetMetric11.getMetricName(), ImmutableList.of(Separator.class, SessionTokenHeartbeatMetrics$BookmarkType.class)), MetricValueTemplates.emptyBuilder().add("Counter:", SessionTokenHeartbeatMetrics$TokenStatus.class).build(), metricPriority);
        PLAYBACK_SESSION_TOKEN_HEARTBEAT_COUNTER = enumeratedPlaybackPmetMetrics51;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics52 = new EnumeratedPlaybackPmetMetrics("PLAYBACK_SESSION_TOKEN_HEARTBEAT_TOTAL_SKIPPED", 51, new MetricNameTemplate(playbackPmetMetric11.getMetricName(), ImmutableList.of(Separator.class, SessionTokenHeartbeatMetrics$BookmarkType.class)), MetricValueTemplates.emptyBuilder().add("TimeoutsPerSession:", SessionTokenHeartbeatMetrics$TokenStatus.class).build(), metricPriority);
        PLAYBACK_SESSION_TOKEN_HEARTBEAT_TOTAL_SKIPPED = enumeratedPlaybackPmetMetrics52;
        PlaybackPmetMetric playbackPmetMetric12 = PlaybackPmetMetric.BUFFER_FULLNESS_BREACH;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics53 = new EnumeratedPlaybackPmetMetrics("BUFFER_FULLNESS_BREACH", 52, new MetricNameTemplate(playbackPmetMetric12.getMetricName(), ImmutableList.of(Separator.class, BufferType.class)), MetricValueTemplates.defaultBuilder().add("ContentType:", ContentTypePivot.class).build(), metricPriority2);
        BUFFER_FULLNESS_BREACH = enumeratedPlaybackPmetMetrics53;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics54 = new EnumeratedPlaybackPmetMetrics("BUFFER_FULLNESS_BREACH_CDN_ORIGIN", 53, new MetricNameTemplate(playbackPmetMetric12.getMetricName(), ImmutableList.of(Separator.class, BufferType.class)), MetricValueTemplates.emptyBuilder().add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority2);
        BUFFER_FULLNESS_BREACH_CDN_ORIGIN = enumeratedPlaybackPmetMetrics54;
        EnumeratedPlaybackPmetMetrics enumeratedPlaybackPmetMetrics55 = new EnumeratedPlaybackPmetMetrics("BUFFER_FULLNESS_BREACH_TITLEID_CDN_ORIGIN_TYPE", 54, new MetricNameTemplate(playbackPmetMetric12.getMetricName(), ImmutableList.of(Separator.class, BufferType.class, TitleIdPivot.class)), MetricValueTemplates.emptyBuilder().add("ContentType:", ContentTypePivot.class).add("CDN:", CdnPivot.class).add("Origin:", OriginPivot.class).build(), metricPriority2);
        BUFFER_FULLNESS_BREACH_TITLEID_CDN_ORIGIN_TYPE = enumeratedPlaybackPmetMetrics55;
        $VALUES = new EnumeratedPlaybackPmetMetrics[]{enumeratedPlaybackPmetMetrics, enumeratedPlaybackPmetMetrics2, enumeratedPlaybackPmetMetrics3, enumeratedPlaybackPmetMetrics4, enumeratedPlaybackPmetMetrics5, enumeratedPlaybackPmetMetrics6, enumeratedPlaybackPmetMetrics7, enumeratedPlaybackPmetMetrics8, enumeratedPlaybackPmetMetrics9, enumeratedPlaybackPmetMetrics10, enumeratedPlaybackPmetMetrics11, enumeratedPlaybackPmetMetrics12, enumeratedPlaybackPmetMetrics13, enumeratedPlaybackPmetMetrics14, enumeratedPlaybackPmetMetrics15, enumeratedPlaybackPmetMetrics16, enumeratedPlaybackPmetMetrics17, enumeratedPlaybackPmetMetrics18, enumeratedPlaybackPmetMetrics19, enumeratedPlaybackPmetMetrics20, enumeratedPlaybackPmetMetrics21, enumeratedPlaybackPmetMetrics22, enumeratedPlaybackPmetMetrics23, enumeratedPlaybackPmetMetrics24, enumeratedPlaybackPmetMetrics25, enumeratedPlaybackPmetMetrics26, enumeratedPlaybackPmetMetrics27, enumeratedPlaybackPmetMetrics28, enumeratedPlaybackPmetMetrics29, enumeratedPlaybackPmetMetrics30, enumeratedPlaybackPmetMetrics31, enumeratedPlaybackPmetMetrics32, enumeratedPlaybackPmetMetrics33, enumeratedPlaybackPmetMetrics34, enumeratedPlaybackPmetMetrics35, enumeratedPlaybackPmetMetrics36, enumeratedPlaybackPmetMetrics37, enumeratedPlaybackPmetMetrics38, enumeratedPlaybackPmetMetrics39, enumeratedPlaybackPmetMetrics40, enumeratedPlaybackPmetMetrics41, enumeratedPlaybackPmetMetrics42, enumeratedPlaybackPmetMetrics43, enumeratedPlaybackPmetMetrics44, enumeratedPlaybackPmetMetrics45, enumeratedPlaybackPmetMetrics46, enumeratedPlaybackPmetMetrics47, enumeratedPlaybackPmetMetrics48, enumeratedPlaybackPmetMetrics49, enumeratedPlaybackPmetMetrics50, enumeratedPlaybackPmetMetrics51, enumeratedPlaybackPmetMetrics52, enumeratedPlaybackPmetMetrics53, enumeratedPlaybackPmetMetrics54, enumeratedPlaybackPmetMetrics55};
    }

    private EnumeratedPlaybackPmetMetrics(@Nonnull String str, @Nonnull int i, @Nonnull MetricNameTemplate metricNameTemplate, MetricValueTemplates metricValueTemplates, MetricPriority metricPriority) {
        this.mNameTemplate = (MetricNameTemplate) Preconditions.checkNotNull(metricNameTemplate, "nameTemplate");
        this.mValueTemplates = (MetricValueTemplates) Preconditions.checkNotNull(metricValueTemplates, "valueTemplates");
        this.mPriority = (MetricPriority) Preconditions.checkNotNull(metricPriority, "priority");
    }

    @Override // com.amazon.avod.metrics.pmet.EnumeratedCounterMetricTemplate
    public ValidatedCounterMetric format(ImmutableList<MetricParameter> immutableList, ImmutableList<ImmutableList<MetricParameter>> immutableList2) {
        return new ValidatedCounterMetric(this.mNameTemplate.format(immutableList), this.mValueTemplates.format(immutableList2), PlayerMetricComponent.PLAYBACK, this.mPriority);
    }

    @Override // com.amazon.avod.metrics.pmet.EnumeratedCounterMetricTemplate
    @Nonnull
    public ValidatedCounterMetric format(@Nonnull ImmutableList<MetricParameter> immutableList, @Nonnull ImmutableList<ImmutableList<MetricParameter>> immutableList2, long j) {
        return new ValidatedCounterMetric(this.mNameTemplate.format(immutableList), this.mValueTemplates.format(immutableList2), j, PlayerMetricComponent.PLAYBACK, this.mPriority);
    }
}
