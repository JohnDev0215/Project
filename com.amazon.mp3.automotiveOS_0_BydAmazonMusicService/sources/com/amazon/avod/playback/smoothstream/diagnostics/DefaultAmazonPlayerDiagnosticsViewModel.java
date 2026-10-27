package com.amazon.avod.playback.smoothstream.diagnostics;

import android.os.Build;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.playback.capability.DeviceResources;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultAmazonPlayerDiagnosticsViewModel.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u001a\u001a\u00020\nH\u0016J\b\u0010\u001b\u001a\u00020\nH\u0016J\b\u0010\u001c\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u001d\u001a\u00020\nH\u0016J\b\u0010\u001e\u001a\u00020\nH\u0016J\b\u0010\u001f\u001a\u00020\nH\u0016J\b\u0010 \u001a\u00020\nH\u0016J\b\u0010!\u001a\u00020\nH\u0016J\b\u0010\"\u001a\u00020\nH\u0016J\b\u0010#\u001a\u00020\nH\u0016J\b\u0010$\u001a\u00020\nH\u0016J\b\u0010%\u001a\u00020\nH\u0016J\b\u0010&\u001a\u00020\nH\u0016J\b\u0010'\u001a\u00020\nH\u0016J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0016J\b\u0010,\u001a\u00020\nH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/amazon/avod/playback/smoothstream/diagnostics/DefaultAmazonPlayerDiagnosticsViewModel;", "Lcom/amazon/avod/playback/smoothstream/diagnostics/AmazonPlayerDiagnosticsViewModel;", "deviceResources", "Lcom/amazon/avod/playback/capability/DeviceResources;", "config", "Lcom/amazon/avod/content/config/SmoothStreamingPlaybackConfig;", "formatter", "Lcom/amazon/avod/playback/smoothstream/diagnostics/DiagnosticsTextFormatter;", "(Lcom/amazon/avod/playback/capability/DeviceResources;Lcom/amazon/avod/content/config/SmoothStreamingPlaybackConfig;Lcom/amazon/avod/playback/smoothstream/diagnostics/DiagnosticsTextFormatter;)V", "audioStreamDiagnostic", "", "bufferingCountDiagnostic", "cdnInfoDiagnostic", "completeDiagnosticsString", "contentTypeDiagnostic", "deliveryProtocolDiagnostic", "deviceModelNameDiagnostic", "deviceProfileNameDiagnostic", "droppedFramesDiagnostic", "heuristicsDiagnostic", "javaHeapDiagnostic", "manifestUrlDiagnostic", "nativeHeapDiagnostic", "rendererDrmSchemeDiagnostic", "sessionTypeDiagnostic", "videoStreamDiagnostic", "audioStreamDiagnosticString", "bufferingCountDiagnosticString", "cdnInfoDiagnosticString", "contentTypeDiagnosticString", "deliveryProtocolDiagnosticString", "deviceModelDiagnosticString", "deviceProfileNameDiagnosticString", "droppedFramesDiagnosticString", "heuristicsDiagnosticString", "javaHeapDiagnosticString", "manifestUrlDiagnosticString", "nativeHeapDiagnosticString", "rendererDrmSchemeDiagnosticString", "sessionTypeDiagnosticString", "updateDiagnostics", "", "collector", "Lcom/amazon/avod/playback/smoothstream/diagnostics/DiagnosticDataCollector;", "videoStreamDiagnosticString", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultAmazonPlayerDiagnosticsViewModel implements AmazonPlayerDiagnosticsViewModel {
    private String audioStreamDiagnostic;
    private String bufferingCountDiagnostic;
    private String cdnInfoDiagnostic;
    private String completeDiagnosticsString;
    private final SmoothStreamingPlaybackConfig config;
    private String contentTypeDiagnostic;
    private String deliveryProtocolDiagnostic;
    private String deviceModelNameDiagnostic;
    private String deviceProfileNameDiagnostic;
    private final DeviceResources deviceResources;
    private String droppedFramesDiagnostic;
    private final DiagnosticsTextFormatter formatter;
    private String heuristicsDiagnostic;
    private String javaHeapDiagnostic;
    private String manifestUrlDiagnostic;
    private String nativeHeapDiagnostic;
    private String rendererDrmSchemeDiagnostic;
    private String sessionTypeDiagnostic;
    private String videoStreamDiagnostic;

    public DefaultAmazonPlayerDiagnosticsViewModel() {
        this(null, null, null, 7, null);
    }

    public DefaultAmazonPlayerDiagnosticsViewModel(DeviceResources deviceResources, SmoothStreamingPlaybackConfig config, DiagnosticsTextFormatter formatter) {
        Intrinsics.checkNotNullParameter(deviceResources, "deviceResources");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(formatter, "formatter");
        this.deviceResources = deviceResources;
        this.config = config;
        this.formatter = formatter;
        this.deliveryProtocolDiagnostic = "";
        this.videoStreamDiagnostic = "";
        this.audioStreamDiagnostic = "";
        this.bufferingCountDiagnostic = "";
        this.javaHeapDiagnostic = "";
        this.nativeHeapDiagnostic = "";
        this.droppedFramesDiagnostic = "";
        this.rendererDrmSchemeDiagnostic = "";
        this.deviceProfileNameDiagnostic = "";
        this.deviceModelNameDiagnostic = "";
        this.sessionTypeDiagnostic = "";
        this.cdnInfoDiagnostic = "";
        this.contentTypeDiagnostic = "";
        this.heuristicsDiagnostic = "";
        this.manifestUrlDiagnostic = "";
        this.completeDiagnosticsString = "";
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ DefaultAmazonPlayerDiagnosticsViewModel(DeviceResources deviceResources, SmoothStreamingPlaybackConfig INSTANCE, DiagnosticsTextFormatter diagnosticsTextFormatter, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            deviceResources = DeviceResources.getInstance();
            Intrinsics.checkNotNullExpressionValue(deviceResources, "getInstance()");
        }
        if ((i & 2) != 0) {
            INSTANCE = SmoothStreamingPlaybackConfig.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(INSTANCE, "INSTANCE");
        }
        this(deviceResources, INSTANCE, (i & 4) != 0 ? DefaultDiagnosticsTextFormatter.INSTANCE : diagnosticsTextFormatter);
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsViewModel
    public void updateDiagnostics(DiagnosticDataCollector collector) {
        Intrinsics.checkNotNullParameter(collector, "collector");
        ContentSessionContext context = collector.getContext();
        DiagnosticsTextFormatter diagnosticsTextFormatter = this.formatter;
        Intrinsics.checkNotNullExpressionValue(context, "context");
        this.deliveryProtocolDiagnostic = diagnosticsTextFormatter.formattedDeliveryProtocolText(context);
        this.manifestUrlDiagnostic = this.formatter.formattedManifestUrlText(context);
        this.audioStreamDiagnostic = this.formatter.formattedAudioStreamText(collector);
        this.bufferingCountDiagnostic = this.formatter.formattedBufferingCountText(collector.getBufferingCount());
        this.javaHeapDiagnostic = this.formatter.formattedJavaHeapText(this.deviceResources);
        this.nativeHeapDiagnostic = this.formatter.formattedNativeHeapText(this.deviceResources);
        this.droppedFramesDiagnostic = this.formatter.formattedDroppedFramesText(collector);
        this.rendererDrmSchemeDiagnostic = this.formatter.formattedRendererDrmSchemeText(collector);
        this.deviceProfileNameDiagnostic = this.formatter.formattedDeviceProfileNameText(this.deviceResources);
        this.sessionTypeDiagnostic = this.formatter.formattedSessionTypeText(context);
        this.cdnInfoDiagnostic = this.formatter.formattedCdnInfoText(collector);
        this.contentTypeDiagnostic = this.formatter.formattedContentTypeText(this.deviceResources, this.config);
        this.videoStreamDiagnostic = this.formatter.formattedVideoStreamText(collector, this.deviceResources);
        this.heuristicsDiagnostic = this.formatter.formattedHeuristicText(context, this.deviceResources);
        this.completeDiagnosticsString = this.formatter.formattedDiagnosticsString(collector, this.deviceResources, this.config);
        DiagnosticsTextFormatter diagnosticsTextFormatter2 = this.formatter;
        String MANUFACTURER = Build.MANUFACTURER;
        Intrinsics.checkNotNullExpressionValue(MANUFACTURER, "MANUFACTURER");
        String MODEL = Build.MODEL;
        Intrinsics.checkNotNullExpressionValue(MODEL, "MODEL");
        this.deviceModelNameDiagnostic = diagnosticsTextFormatter2.formattedDeviceModelText(MANUFACTURER, MODEL);
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsViewModel
    /* JADX INFO: renamed from: completeDiagnosticsString, reason: from getter */
    public String getCompleteDiagnosticsString() {
        return this.completeDiagnosticsString;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsViewModel
    /* JADX INFO: renamed from: audioStreamDiagnosticString, reason: from getter */
    public String getAudioStreamDiagnostic() {
        return this.audioStreamDiagnostic;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsViewModel
    /* JADX INFO: renamed from: videoStreamDiagnosticString, reason: from getter */
    public String getVideoStreamDiagnostic() {
        return this.videoStreamDiagnostic;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsViewModel
    /* JADX INFO: renamed from: deliveryProtocolDiagnosticString, reason: from getter */
    public String getDeliveryProtocolDiagnostic() {
        return this.deliveryProtocolDiagnostic;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsViewModel
    /* JADX INFO: renamed from: bufferingCountDiagnosticString, reason: from getter */
    public String getBufferingCountDiagnostic() {
        return this.bufferingCountDiagnostic;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsViewModel
    /* JADX INFO: renamed from: javaHeapDiagnosticString, reason: from getter */
    public String getJavaHeapDiagnostic() {
        return this.javaHeapDiagnostic;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsViewModel
    /* JADX INFO: renamed from: nativeHeapDiagnosticString, reason: from getter */
    public String getNativeHeapDiagnostic() {
        return this.nativeHeapDiagnostic;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsViewModel
    /* JADX INFO: renamed from: droppedFramesDiagnosticString, reason: from getter */
    public String getDroppedFramesDiagnostic() {
        return this.droppedFramesDiagnostic;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsViewModel
    /* JADX INFO: renamed from: rendererDrmSchemeDiagnosticString, reason: from getter */
    public String getRendererDrmSchemeDiagnostic() {
        return this.rendererDrmSchemeDiagnostic;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsViewModel
    /* JADX INFO: renamed from: deviceProfileNameDiagnosticString, reason: from getter */
    public String getDeviceProfileNameDiagnostic() {
        return this.deviceProfileNameDiagnostic;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsViewModel
    /* JADX INFO: renamed from: deviceModelDiagnosticString, reason: from getter */
    public String getDeviceModelNameDiagnostic() {
        return this.deviceModelNameDiagnostic;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsViewModel
    /* JADX INFO: renamed from: sessionTypeDiagnosticString, reason: from getter */
    public String getSessionTypeDiagnostic() {
        return this.sessionTypeDiagnostic;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsViewModel
    /* JADX INFO: renamed from: cdnInfoDiagnosticString, reason: from getter */
    public String getCdnInfoDiagnostic() {
        return this.cdnInfoDiagnostic;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsViewModel
    /* JADX INFO: renamed from: contentTypeDiagnosticString, reason: from getter */
    public String getContentTypeDiagnostic() {
        return this.contentTypeDiagnostic;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsViewModel
    /* JADX INFO: renamed from: heuristicsDiagnosticString, reason: from getter */
    public String getHeuristicsDiagnostic() {
        return this.heuristicsDiagnostic;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsViewModel
    /* JADX INFO: renamed from: manifestUrlDiagnosticString, reason: from getter */
    public String getManifestUrlDiagnostic() {
        return this.manifestUrlDiagnostic;
    }
}
