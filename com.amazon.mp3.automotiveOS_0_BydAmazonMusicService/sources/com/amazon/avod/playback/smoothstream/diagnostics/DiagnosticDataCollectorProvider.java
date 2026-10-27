package com.amazon.avod.playback.smoothstream.diagnostics;

import com.amazon.avod.event.PlaybackEventTransport;

/* JADX INFO: loaded from: classes2.dex */
public class DiagnosticDataCollectorProvider {
    private final DiagnosticsConfig mConfig;
    private final PlaybackEventTransport mPlaybackEventTransport;

    public DiagnosticDataCollectorProvider(PlaybackEventTransport playbackEventTransport) {
        this(DiagnosticsConfig.getInstance(), playbackEventTransport);
    }

    DiagnosticDataCollectorProvider(DiagnosticsConfig diagnosticsConfig, PlaybackEventTransport playbackEventTransport) {
        this.mConfig = diagnosticsConfig;
        this.mPlaybackEventTransport = playbackEventTransport;
    }

    public DiagnosticDataCollector newCollector() {
        if (this.mConfig.isDiagnosticsViewAvailable()) {
            return new DefaultDiagnosticDataCollector(this.mPlaybackEventTransport);
        }
        return new NoDiagnosticDataCollector();
    }
}
