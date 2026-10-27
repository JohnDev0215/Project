package com.amazon.avod.playback.smoothstream.diagnostics;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.media.framework.event.EventDispatcher;
import com.amazon.avod.playback.event.PlaybackEvent;
import java.util.TreeSet;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface DiagnosticDataCollector {
    TreeSet<AggregatedDataPoint> getAggregatedDataPoints();

    int getAudioQualityCount();

    int getBufferingCount();

    TreeSet<BufferingDataPoint> getBufferingDataPoints();

    TreeSet<CdnSwitchedDataPoint> getCdnSwitchedDataPoints();

    ContentSessionContext getContext();

    TreeSet<DiagnosticDataPoint> getDiagnosticDataPoints();

    @Nullable
    String getDrmScheme();

    TreeSet<ErrorDataPoint> getErrorDataPoints();

    double getFramesPerSecond();

    int getLastAudioBitrate();

    int getLastVideoBitrate();

    TreeSet<ManifestRefreshDataPoint> getManifestRefreshDataPoints();

    double getMaxQualityScore();

    @Nullable
    String getPeriodId();

    long getPlayHeadInNanos();

    TreeSet<QualityScoreDataPoint> getQualityScoreDataPoints();

    @Nullable
    PlaybackEvent getQueuedPlaybackEvent();

    @Nullable
    String getRendererScheme();

    int getTotalDecoderDroppedCount();

    int getTotalRendererDroppedCount();

    VideoFragmentQualityCounter getVideoFragmentQualityCounter();

    int getVideoQualityCount();

    void initialize(EventDispatcher eventDispatcher, ContentSessionContext contentSessionContext);

    void registerDiagnosticUpdateListener(DiagnosticUpdateListener diagnosticUpdateListener);

    void shutdown();

    void unregisterDiagnosticUpdateListener(DiagnosticUpdateListener diagnosticUpdateListener);
}
