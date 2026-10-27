package com.amazon.avod.media.diagnostics;

import com.amazon.avod.media.playback.VideoRenderingSettings;

/* JADX INFO: loaded from: classes2.dex */
public interface DiagnosticsController {

    public enum BandwidthScale {
        LINEAR,
        LOGARITHMIC,
        EQUISPACED
    }

    boolean diagnosticCdnGraphVisible();

    boolean diagnosticInfoStringVisible();

    void dispose();

    BandwidthScale getBandwidthScale();

    void hideDiagnosticCdnGraph();

    void hideDiagnosticGraph();

    void hideDiagnosticInfoString();

    void hideDiagnosticToast();

    void hideInternalState();

    void hideQualityScoreGraph();

    boolean internalStateVisible();

    boolean isCompactViewEnabled();

    boolean isEnabled();

    boolean qualityScoreGraphVisible();

    void setRenderingSettings(VideoRenderingSettings videoRenderingSettings);

    void showDiagnosticCdnGraph();

    void showDiagnosticGraph(BandwidthScale bandwidthScale, boolean z);

    void showDiagnosticInfoString();

    void showDiagnosticToast();

    void showInternalState();

    void showQualityScoreGraph();
}
