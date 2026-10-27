package com.amazon.avod.playback.smoothstream.diagnostics;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.media.framework.event.EventDispatcher;
import com.amazon.avod.playback.event.PlaybackEvent;
import com.google.common.collect.Sets;
import java.util.TreeSet;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class NoDiagnosticDataCollector implements DiagnosticDataCollector {
    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    @Nullable
    public TreeSet<AggregatedDataPoint> getAggregatedDataPoints() {
        return null;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public int getAudioQualityCount() {
        return 0;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public int getBufferingCount() {
        return 0;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public TreeSet<CdnSwitchedDataPoint> getCdnSwitchedDataPoints() {
        return null;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public ContentSessionContext getContext() {
        return null;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    @Nullable
    public String getDrmScheme() {
        return null;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public double getFramesPerSecond() {
        return 0.0d;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public int getLastAudioBitrate() {
        return 0;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public int getLastVideoBitrate() {
        return 0;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    @Nullable
    public TreeSet<ManifestRefreshDataPoint> getManifestRefreshDataPoints() {
        return null;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public double getMaxQualityScore() {
        return 0.0d;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    @Nullable
    public String getPeriodId() {
        return null;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public long getPlayHeadInNanos() {
        return 0L;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    @Nullable
    public PlaybackEvent getQueuedPlaybackEvent() {
        return null;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    @Nullable
    public String getRendererScheme() {
        return null;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public int getTotalDecoderDroppedCount() {
        return 0;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public int getTotalRendererDroppedCount() {
        return 0;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public VideoFragmentQualityCounter getVideoFragmentQualityCounter() {
        return null;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public int getVideoQualityCount() {
        return 0;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public void initialize(EventDispatcher eventDispatcher, ContentSessionContext contentSessionContext) {
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public void registerDiagnosticUpdateListener(DiagnosticUpdateListener diagnosticUpdateListener) {
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public void shutdown() {
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public void unregisterDiagnosticUpdateListener(DiagnosticUpdateListener diagnosticUpdateListener) {
    }

    NoDiagnosticDataCollector() {
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public TreeSet<DiagnosticDataPoint> getDiagnosticDataPoints() {
        return Sets.newTreeSet();
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public TreeSet<ErrorDataPoint> getErrorDataPoints() {
        return Sets.newTreeSet();
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public TreeSet<BufferingDataPoint> getBufferingDataPoints() {
        return Sets.newTreeSet();
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public TreeSet<QualityScoreDataPoint> getQualityScoreDataPoints() {
        return Sets.newTreeSet();
    }
}
