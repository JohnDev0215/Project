package com.amazon.avod.media.diagnostics;

import android.os.Handler;
import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.framework.platform.Handlers;

/* JADX INFO: loaded from: classes2.dex */
public class DiagnosticsOverlayToggler {
    private final Handler mUIHandler;

    public DiagnosticsOverlayToggler() {
        this(Handlers.getUIHandler());
    }

    DiagnosticsOverlayToggler(Handler handler) {
        this.mUIHandler = handler;
    }

    public void toggle(final DiagnosticsController diagnosticsController, final DiagnosticsController diagnosticsController2) {
        if (diagnosticsController.isEnabled() && diagnosticsController2.isEnabled()) {
            final DiagnosticsController.BandwidthScale bandwidthScale = diagnosticsController.getBandwidthScale();
            final boolean zIsCompactViewEnabled = diagnosticsController.isCompactViewEnabled();
            this.mUIHandler.post(new Runnable() { // from class: com.amazon.avod.media.diagnostics.DiagnosticsOverlayToggler.1
                @Override // java.lang.Runnable
                public void run() {
                    if (bandwidthScale != null) {
                        diagnosticsController.hideDiagnosticGraph();
                        diagnosticsController2.showDiagnosticGraph(bandwidthScale, zIsCompactViewEnabled);
                    }
                    if (diagnosticsController.diagnosticInfoStringVisible()) {
                        diagnosticsController.hideDiagnosticInfoString();
                        diagnosticsController2.showDiagnosticInfoString();
                    }
                    if (diagnosticsController.diagnosticCdnGraphVisible()) {
                        diagnosticsController.hideDiagnosticCdnGraph();
                        diagnosticsController2.showDiagnosticCdnGraph();
                    }
                    if (diagnosticsController.qualityScoreGraphVisible()) {
                        diagnosticsController.hideQualityScoreGraph();
                        diagnosticsController2.showQualityScoreGraph();
                    }
                    if (diagnosticsController.internalStateVisible()) {
                        diagnosticsController.hideInternalState();
                        diagnosticsController2.showInternalState();
                    }
                }
            });
        }
    }
}
