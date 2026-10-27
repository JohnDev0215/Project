package com.amazon.avod.pmet;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum PlaybackPmetMetric {
    SESSION_PLAYING("Playback:SessionPlaying"),
    UNEXPECTED_BUFFER("Playback:UnexpectedBuffer"),
    PLAYBACK_AD_SKIP("Playback:AdSkip"),
    FATAL_ERROR("Playback:FatalError"),
    AD_CLIP_ERROR("Playback:AdClipError"),
    AD_BREAK_ERROR("Playback:AdBreakError"),
    SUBTITLE_ERROR("Playback:SubtitleError"),
    SUBTITLE_METRICS("Playback:SubtitleMetrics"),
    SERVER_INSERTED_STREAM_METRICS("Playback:ServerInsertedStreamMetrics"),
    CRSM_ERROR("Playback:CRSM-Error"),
    AVSM_ERROR("Playback:AVSM-Error"),
    VDSM_ERROR("Playback:VDSM-Error"),
    PLSM_ERROR("Playback:PLSM-Error"),
    STALE_SAME_MANIFEST("Playback:Stale-Same-Manifest"),
    STALE_OLD_MANIFEST("Playback:Stale-Old-Manifest"),
    SLOW_MANIFEST("Playback:Slow-Manifest"),
    FRAGMENT_DOWNLOAD("Playback:FragmentDownload"),
    FRAGMENT_URL_RESOLUTION_ERROR("Playback:FragmentURLResolutionError"),
    MANIFEST_DOWNLOAD("Playback:ManifestDownload"),
    FRAGMENT_GAINED("Playback:FragmentGained"),
    FRAGMENT_QUALITY("Playback:FragmentQuality"),
    PLAYER_RESTART("Playback:PlayerRestart"),
    HARDWARE_ACCELERATION("Playback:HardwareAcceleration"),
    RENDERER_SCHEME_AVAILABILITY("Playback:RendererSchemeAvailability"),
    PLAYBACK_VOICE_CONTROL("Playback:PlaybackVoiceControl"),
    IN_PLAYBACK_RATING("Playback:InPlaybackRating"),
    IMAGE_DOWNLOADER("Playback:ImageDownloader"),
    LIVE_TRICKPLAY("Playback:LiveTrickplay"),
    NATIVE_HEAP_USED("Playback:NativeHeapUsedMB"),
    NATIVE_HEAP_ALLOCATED("Playback:NativeHeapAllocatedMB"),
    PERCENT_NATIVE_HEAP_USED("Playback:PercentNativeHeapUsed"),
    JAVA_HEAP_USED("Playback:JavaHeapUsedMB"),
    JAVA_HEAP_ALLOCATED("Playback:JavaHeapAllocatedMB"),
    CACHE_OPERATION("Playback:CacheOperation"),
    CACHE_STATUS("Playback:CacheStatus"),
    CACHE_EVICTION("Playback:CacheEviction"),
    TTFF_TRACE("Playback:TTFFTrace"),
    PRESENTATION_TYPE_METRICS("Playback:PresentationTypeMetrics"),
    CONTENT_STORE_TYPE("Playback:ContentStoreType"),
    TOTAL_DROPPED_FRAMES("Playback:TotalDroppedFrames"),
    UPDATE_STREAM_WITH_SESSION_TOKEN("Playback:UpdateStreamWithSessionToken"),
    BUFFER_FULLNESS_BREACH("Playback:BufferFullnessBreach");

    private final String mMetricName;

    PlaybackPmetMetric(@Nonnull String str) {
        this.mMetricName = (String) Preconditions.checkNotNull(str, "metricName");
    }

    public String getMetricName() {
        return this.mMetricName;
    }
}
