package com.amazon.avod.playback.smoothstream.diagnostics;

import android.view.ViewGroup;
import com.amazon.avod.media.diagnostics.BaseDiagnosticsController;
import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.framework.platform.DeviceConfiguration;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamingDiagnosticsController extends BaseDiagnosticsController {
    private AmazonPlayerDiagnosticsView mAmazonPlayerDiagnosticsView;
    private CdnGraphView mCdnGraphView;
    private final DiagnosticDataCollector mDataCollector;
    private final DeviceConfiguration mDeviceConfig;
    private DiagnosticToastView mDiagnosticToastView;
    private final DiagnosticsConfig mDiagnosticsConfig;
    private GraphSurfaceView mGraphView;
    private DiagnosticInternalStateView mInternalStateView;
    private QualityScoreView mQualityScoreView;
    private SmoothStreamDiagnosticTextView mSmoothStreamingDiagnosticText;

    public SmoothStreamingDiagnosticsController(DeviceConfiguration deviceConfiguration, DiagnosticDataCollector diagnosticDataCollector, DiagnosticsConfig diagnosticsConfig) {
        this.mDeviceConfig = (DeviceConfiguration) Preconditions.checkNotNull(deviceConfiguration, "deviceConfig");
        this.mDataCollector = (DiagnosticDataCollector) Preconditions.checkNotNull(diagnosticDataCollector, "dataCollector");
        this.mDiagnosticsConfig = (DiagnosticsConfig) Preconditions.checkNotNull(diagnosticsConfig, "diagnosticsConfig");
    }

    @Override // com.amazon.avod.media.diagnostics.BaseDiagnosticsController, com.amazon.avod.media.diagnostics.DiagnosticsController
    public void showDiagnosticGraph(DiagnosticsController.BandwidthScale bandwidthScale, boolean z) {
        if (this.mAnchorView == null) {
            return;
        }
        if (!this.mDiagnosticGraphIsVisible) {
            if (this.mGraphView == null) {
                GraphSurfaceView graphSurfaceView = new GraphSurfaceView(this.mAnchorView.getContext());
                this.mGraphView = graphSurfaceView;
                graphSurfaceView.init(this.mDeviceConfig.getScreenWidth(), this.mDeviceConfig.getScreenHeight(), z);
                this.mAnchorView.addView(this.mGraphView);
                SmoothStreamDiagnosticTextView smoothStreamDiagnosticTextView = this.mSmoothStreamingDiagnosticText;
                if (smoothStreamDiagnosticTextView != null) {
                    smoothStreamDiagnosticTextView.bringToFront();
                    this.mAnchorView.invalidate();
                }
                AmazonPlayerDiagnosticsView amazonPlayerDiagnosticsView = this.mAmazonPlayerDiagnosticsView;
                if (amazonPlayerDiagnosticsView != null) {
                    amazonPlayerDiagnosticsView.bringToFront();
                    this.mAnchorView.invalidate();
                }
                CdnGraphView cdnGraphView = this.mCdnGraphView;
                if (cdnGraphView != null) {
                    cdnGraphView.bringToFront();
                    this.mAnchorView.invalidate();
                }
                QualityScoreView qualityScoreView = this.mQualityScoreView;
                if (qualityScoreView != null) {
                    qualityScoreView.bringToFront();
                    this.mAnchorView.invalidate();
                }
                this.mDataCollector.registerDiagnosticUpdateListener(this.mGraphView);
            }
            this.mGraphView.setVisibility(0);
            this.mDiagnosticGraphIsVisible = true;
        }
        this.mBandwidthScale = bandwidthScale;
        this.mGraphView.setAxisScaleForBandWidth(bandwidthScale);
    }

    @Override // com.amazon.avod.media.diagnostics.BaseDiagnosticsController, com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideDiagnosticGraph() {
        if (this.mDiagnosticGraphIsVisible) {
            this.mGraphView.setVisibility(8);
            this.mDiagnosticGraphIsVisible = false;
            this.mGraphView = null;
        }
        this.mBandwidthScale = null;
    }

    @Override // com.amazon.avod.media.diagnostics.BaseDiagnosticsController, com.amazon.avod.media.diagnostics.DiagnosticsController
    public void showDiagnosticInfoString() {
        if (this.mDiagnosticInfoStringEnabled || this.mAnchorView == null) {
            return;
        }
        showDiagnosticInfoView();
        this.mDiagnosticInfoStringEnabled = true;
    }

    @Override // com.amazon.avod.media.diagnostics.BaseDiagnosticsController, com.amazon.avod.media.diagnostics.DiagnosticsController
    public void showDiagnosticToast() {
        if (this.mDiagnosticToastView == null) {
            DiagnosticToastView diagnosticToastView = new DiagnosticToastView(this.mAnchorView.getContext());
            this.mDiagnosticToastView = diagnosticToastView;
            this.mDataCollector.registerDiagnosticUpdateListener(diagnosticToastView);
        }
    }

    @Override // com.amazon.avod.media.diagnostics.BaseDiagnosticsController, com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideDiagnosticToast() {
        DiagnosticToastView diagnosticToastView = this.mDiagnosticToastView;
        if (diagnosticToastView != null) {
            this.mDataCollector.unregisterDiagnosticUpdateListener(diagnosticToastView);
            this.mDiagnosticToastView = null;
        }
    }

    @Override // com.amazon.avod.media.diagnostics.BaseDiagnosticsController, com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideDiagnosticInfoString() {
        if (!this.mDiagnosticInfoStringEnabled || this.mAnchorView == null) {
            return;
        }
        hideDiagnosticInfoView();
        this.mDiagnosticInfoStringEnabled = false;
    }

    @Override // com.amazon.avod.media.diagnostics.BaseDiagnosticsController, com.amazon.avod.media.diagnostics.DiagnosticsController
    public void showDiagnosticCdnGraph() {
        if (this.mAnchorView != null && this.mCdnGraphView == null) {
            CdnGraphView cdnGraphView = new CdnGraphView(this.mAnchorView.getContext());
            this.mCdnGraphView = cdnGraphView;
            cdnGraphView.init(this.mDeviceConfig.getScreenWidth(), this.mDeviceConfig.getScreenHeight());
            this.mAnchorView.addView(this.mCdnGraphView);
            this.mDataCollector.registerDiagnosticUpdateListener(this.mCdnGraphView);
            this.mCdnGraphView.setVisibility(0);
            this.mDiagnosticCdnGraphEnabled = true;
        }
    }

    @Override // com.amazon.avod.media.diagnostics.BaseDiagnosticsController, com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideDiagnosticCdnGraph() {
        CdnGraphView cdnGraphView = this.mCdnGraphView;
        if (cdnGraphView != null) {
            cdnGraphView.setVisibility(8);
            this.mCdnGraphView = null;
            this.mDiagnosticCdnGraphEnabled = false;
        }
    }

    @Override // com.amazon.avod.media.diagnostics.BaseDiagnosticsController, com.amazon.avod.media.diagnostics.DiagnosticsController
    public void showQualityScoreGraph() {
        if (this.mAnchorView != null && this.mQualityScoreView == null) {
            QualityScoreView qualityScoreView = new QualityScoreView(this.mAnchorView.getContext());
            this.mQualityScoreView = qualityScoreView;
            qualityScoreView.init(this.mDeviceConfig.getScreenWidth(), this.mDeviceConfig.getScreenHeight());
            this.mAnchorView.addView(this.mQualityScoreView);
            this.mDataCollector.registerDiagnosticUpdateListener(this.mQualityScoreView);
            this.mQualityScoreView.setVisibility(0);
            this.mQualityScoreGraphEnabled = true;
        }
    }

    @Override // com.amazon.avod.media.diagnostics.BaseDiagnosticsController, com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideQualityScoreGraph() {
        QualityScoreView qualityScoreView = this.mQualityScoreView;
        if (qualityScoreView != null) {
            qualityScoreView.setVisibility(8);
            this.mQualityScoreView = null;
            this.mQualityScoreGraphEnabled = false;
        }
    }

    @Override // com.amazon.avod.media.diagnostics.BaseDiagnosticsController, com.amazon.avod.media.diagnostics.DiagnosticsController
    public void dispose() {
        super.dispose();
        ViewGroup viewGroup = this.mAnchorView;
        if (viewGroup == null) {
            return;
        }
        GraphSurfaceView graphSurfaceView = this.mGraphView;
        if (graphSurfaceView != null) {
            viewGroup.removeView(graphSurfaceView);
        }
        SmoothStreamDiagnosticTextView smoothStreamDiagnosticTextView = this.mSmoothStreamingDiagnosticText;
        if (smoothStreamDiagnosticTextView != null) {
            this.mAnchorView.removeView(smoothStreamDiagnosticTextView);
        }
        AmazonPlayerDiagnosticsView amazonPlayerDiagnosticsView = this.mAmazonPlayerDiagnosticsView;
        if (amazonPlayerDiagnosticsView != null) {
            this.mAnchorView.removeView(amazonPlayerDiagnosticsView);
        }
        CdnGraphView cdnGraphView = this.mCdnGraphView;
        if (cdnGraphView != null) {
            this.mAnchorView.removeView(cdnGraphView);
        }
        QualityScoreView qualityScoreView = this.mQualityScoreView;
        if (qualityScoreView != null) {
            this.mAnchorView.removeView(qualityScoreView);
        }
    }

    @Override // com.amazon.avod.media.diagnostics.BaseDiagnosticsController, com.amazon.avod.media.diagnostics.DiagnosticsController
    public void showInternalState() {
        ViewGroup viewGroup;
        if (this.mInternalStateVisible || (viewGroup = this.mAnchorView) == null) {
            return;
        }
        if (this.mInternalStateView == null) {
            DiagnosticInternalStateView diagnosticInternalStateView = new DiagnosticInternalStateView(viewGroup.getContext(), this.mAnchorView);
            this.mInternalStateView = diagnosticInternalStateView;
            this.mDataCollector.registerDiagnosticUpdateListener(diagnosticInternalStateView);
        }
        this.mInternalStateView.setVisibility(0);
        this.mInternalStateVisible = true;
    }

    @Override // com.amazon.avod.media.diagnostics.BaseDiagnosticsController, com.amazon.avod.media.diagnostics.DiagnosticsController
    public void hideInternalState() {
        if (!this.mInternalStateVisible || this.mAnchorView == null) {
            return;
        }
        this.mInternalStateView.setVisibility(8);
        this.mInternalStateVisible = false;
    }

    private void showDiagnosticInfoView() {
        if (useAmazonDiagnosticInfoView()) {
            showAmazonPlayerDiagnosticInfoView();
        } else {
            showLegacyDiagnosticInfoView();
        }
    }

    private void hideDiagnosticInfoView() {
        AmazonPlayerDiagnosticsView amazonPlayerDiagnosticsView = this.mAmazonPlayerDiagnosticsView;
        if (amazonPlayerDiagnosticsView != null) {
            amazonPlayerDiagnosticsView.setVisibility(8);
            this.mAmazonPlayerDiagnosticsView = null;
        }
        SmoothStreamDiagnosticTextView smoothStreamDiagnosticTextView = this.mSmoothStreamingDiagnosticText;
        if (smoothStreamDiagnosticTextView != null) {
            smoothStreamDiagnosticTextView.setVisibility(8);
            this.mSmoothStreamingDiagnosticText = null;
        }
    }

    private void showAmazonPlayerDiagnosticInfoView() {
        if (this.mAmazonPlayerDiagnosticsView == null) {
            AmazonPlayerDiagnosticsView amazonPlayerDiagnosticsView = new AmazonPlayerDiagnosticsView(this.mAnchorView.getContext());
            this.mAmazonPlayerDiagnosticsView = amazonPlayerDiagnosticsView;
            this.mAnchorView.addView(amazonPlayerDiagnosticsView);
            this.mDataCollector.registerDiagnosticUpdateListener(this.mAmazonPlayerDiagnosticsView);
        }
        this.mAmazonPlayerDiagnosticsView.setVisibility(0);
    }

    private void showLegacyDiagnosticInfoView() {
        if (this.mSmoothStreamingDiagnosticText == null) {
            SmoothStreamDiagnosticTextView smoothStreamDiagnosticTextView = new SmoothStreamDiagnosticTextView(this.mAnchorView.getContext());
            this.mSmoothStreamingDiagnosticText = smoothStreamDiagnosticTextView;
            this.mAnchorView.addView(smoothStreamDiagnosticTextView);
            this.mDataCollector.registerDiagnosticUpdateListener(this.mSmoothStreamingDiagnosticText);
        }
        this.mSmoothStreamingDiagnosticText.setVisibility(0);
    }

    private boolean useAmazonDiagnosticInfoView() {
        return this.mDiagnosticsConfig.isAmazonPlayerDiagnosticsViewEnabled() && this.mAnchorView.getContext().getPackageManager().hasSystemFeature("android.hardware.touchscreen");
    }
}
