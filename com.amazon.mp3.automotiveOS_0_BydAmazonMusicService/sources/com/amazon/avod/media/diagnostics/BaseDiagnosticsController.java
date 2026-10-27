package com.amazon.avod.media.diagnostics;

import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;

/* JADX INFO: loaded from: classes2.dex */
public class BaseDiagnosticsController implements DiagnosticsController {
    protected ViewGroup mAnchorView;
    protected DiagnosticsController.BandwidthScale mBandwidthScale;
    protected boolean mDiagnosticCdnGraphEnabled;
    protected boolean mDiagnosticGraphIsVisible;
    protected boolean mDiagnosticInfoStringEnabled;
    protected TextView mDiagnosticText;
    protected boolean mInternalStateVisible;
    protected boolean mIsCompactViewEnabled;
    protected boolean mQualityScoreGraphEnabled;
    protected VideoSpecification mSpecification;

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideDiagnosticGraph() {
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideDiagnosticToast() {
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideInternalState() {
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public boolean isEnabled() {
        return true;
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

    protected BaseDiagnosticsController() {
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void showDiagnosticGraph(DiagnosticsController.BandwidthScale bandwidthScale, boolean z) {
        this.mBandwidthScale = bandwidthScale;
        this.mIsCompactViewEnabled = z;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void showDiagnosticCdnGraph() {
        this.mDiagnosticCdnGraphEnabled = true;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideDiagnosticCdnGraph() {
        this.mDiagnosticCdnGraphEnabled = false;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void showQualityScoreGraph() {
        this.mQualityScoreGraphEnabled = true;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideQualityScoreGraph() {
        this.mQualityScoreGraphEnabled = false;
    }

    public void showDiagnosticInfoString(String str) {
        ViewGroup viewGroup;
        if (this.mDiagnosticInfoStringEnabled || (viewGroup = this.mAnchorView) == null || str == null) {
            return;
        }
        if (viewGroup.indexOfChild(this.mDiagnosticText) == -1) {
            TextView textView = new TextView(this.mAnchorView.getContext());
            this.mDiagnosticText = textView;
            this.mAnchorView.addView(textView);
        }
        this.mDiagnosticText.setText(str);
        this.mDiagnosticText.invalidate();
        this.mDiagnosticText.setVisibility(0);
        this.mDiagnosticInfoStringEnabled = true;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideDiagnosticInfoString() {
        if (!this.mDiagnosticInfoStringEnabled || this.mAnchorView == null) {
            return;
        }
        this.mDiagnosticText.setVisibility(8);
        this.mDiagnosticInfoStringEnabled = false;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public boolean isCompactViewEnabled() {
        return this.mIsCompactViewEnabled;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void setRenderingSettings(VideoRenderingSettings videoRenderingSettings) {
        this.mAnchorView = videoRenderingSettings.getParentView();
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public void dispose() {
        TextView textView;
        ViewGroup viewGroup = this.mAnchorView;
        if (viewGroup == null || (textView = this.mDiagnosticText) == null) {
            return;
        }
        viewGroup.removeView(textView);
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public DiagnosticsController.BandwidthScale getBandwidthScale() {
        return this.mBandwidthScale;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public boolean diagnosticInfoStringVisible() {
        return this.mDiagnosticInfoStringEnabled;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public boolean diagnosticCdnGraphVisible() {
        return this.mDiagnosticCdnGraphEnabled;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public boolean qualityScoreGraphVisible() {
        return this.mQualityScoreGraphEnabled;
    }

    public void setVideoSpecification(VideoSpecification videoSpecification) {
        this.mSpecification = videoSpecification;
    }

    @Override // com.amazon.avod.media.diagnostics.DiagnosticsController
    public boolean internalStateVisible() {
        return this.mInternalStateVisible;
    }
}
