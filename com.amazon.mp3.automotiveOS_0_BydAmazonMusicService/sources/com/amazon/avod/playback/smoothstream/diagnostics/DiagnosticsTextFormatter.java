package com.amazon.avod.playback.smoothstream.diagnostics;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.playback.capability.DeviceResources;
import kotlin.Metadata;

/* JADX INFO: compiled from: DiagnosticsTextFormatter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0018\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003H&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J \u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0010\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0018\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\u001f"}, d2 = {"Lcom/amazon/avod/playback/smoothstream/diagnostics/DiagnosticsTextFormatter;", "", "formattedAudioStreamText", "", "collector", "Lcom/amazon/avod/playback/smoothstream/diagnostics/DiagnosticDataCollector;", "formattedBufferingCountText", "count", "", "formattedCdnInfoText", "formattedContentTypeText", "deviceResources", "Lcom/amazon/avod/playback/capability/DeviceResources;", "config", "Lcom/amazon/avod/content/config/SmoothStreamingPlaybackConfig;", "formattedDeliveryProtocolText", "context", "Lcom/amazon/avod/content/ContentSessionContext;", "formattedDeviceModelText", "manufacturer", "model", "formattedDeviceProfileNameText", "formattedDiagnosticsString", "formattedDroppedFramesText", "formattedHeuristicText", "formattedJavaHeapText", "formattedManifestUrlText", "formattedNativeHeapText", "formattedRendererDrmSchemeText", "formattedSessionTypeText", "formattedVideoStreamText", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface DiagnosticsTextFormatter {
    String formattedAudioStreamText(DiagnosticDataCollector collector);

    String formattedBufferingCountText(int count);

    String formattedCdnInfoText(DiagnosticDataCollector collector);

    String formattedContentTypeText(DeviceResources deviceResources, SmoothStreamingPlaybackConfig config);

    String formattedDeliveryProtocolText(ContentSessionContext context);

    String formattedDeviceModelText(String manufacturer, String model);

    String formattedDeviceProfileNameText(DeviceResources deviceResources);

    String formattedDiagnosticsString(DiagnosticDataCollector collector, DeviceResources deviceResources, SmoothStreamingPlaybackConfig config);

    String formattedDroppedFramesText(DiagnosticDataCollector collector);

    String formattedHeuristicText(ContentSessionContext context, DeviceResources deviceResources);

    String formattedJavaHeapText(DeviceResources deviceResources);

    String formattedManifestUrlText(ContentSessionContext context);

    String formattedNativeHeapText(DeviceResources deviceResources);

    String formattedRendererDrmSchemeText(DiagnosticDataCollector collector);

    String formattedSessionTypeText(ContentSessionContext context);

    String formattedVideoStreamText(DiagnosticDataCollector collector, DeviceResources deviceResources);
}
