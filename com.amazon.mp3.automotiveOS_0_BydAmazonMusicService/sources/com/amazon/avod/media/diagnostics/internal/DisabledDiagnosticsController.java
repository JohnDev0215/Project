package com.amazon.avod.media.diagnostics.internal;

import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class DisabledDiagnosticsController implements DiagnosticsController {
    public static final DisabledDiagnosticsController INSTANCE = new DisabledDiagnosticsController();

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public boolean diagnosticCdnGraphVisible() {
        return false;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public boolean diagnosticInfoStringVisible() {
        return false;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void dispose() {
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public DiagnosticsController.BandwidthScale getBandwidthScale() {
        return null;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideDiagnosticCdnGraph() {
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideDiagnosticGraph() {
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideDiagnosticInfoString() {
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideDiagnosticToast() {
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideInternalState() {
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideQualityScoreGraph() {
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public boolean internalStateVisible() {
        return false;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public boolean isCompactViewEnabled() {
        return false;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public boolean isEnabled() {
        return false;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public boolean qualityScoreGraphVisible() {
        return false;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void setRenderingSettings(VideoRenderingSettings videoRenderingSettings) {
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void showDiagnosticCdnGraph() {
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void showDiagnosticGraph(DiagnosticsController.BandwidthScale bandwidthScale, boolean z) {
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void showDiagnosticInfoString() {
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void showDiagnosticToast() {
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void showInternalState() {
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void showQualityScoreGraph() {
    }
}
