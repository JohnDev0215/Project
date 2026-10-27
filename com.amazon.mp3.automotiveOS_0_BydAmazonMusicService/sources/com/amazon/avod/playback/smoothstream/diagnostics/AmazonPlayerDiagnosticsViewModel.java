package com.amazon.avod.playback.smoothstream.diagnostics;

import kotlin.Metadata;

/* JADX INFO: compiled from: AmazonPlayerDiagnosticsViewModel.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u0003H&J\b\u0010\u000b\u001a\u00020\u0003H&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&J\b\u0010\u0010\u001a\u00020\u0003H&J\b\u0010\u0011\u001a\u00020\u0003H&J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\u0003H&¨\u0006\u0017"}, d2 = {"Lcom/amazon/avod/playback/smoothstream/diagnostics/AmazonPlayerDiagnosticsViewModel;", "", "audioStreamDiagnosticString", "", "bufferingCountDiagnosticString", "cdnInfoDiagnosticString", "completeDiagnosticsString", "contentTypeDiagnosticString", "deliveryProtocolDiagnosticString", "deviceModelDiagnosticString", "deviceProfileNameDiagnosticString", "droppedFramesDiagnosticString", "heuristicsDiagnosticString", "javaHeapDiagnosticString", "manifestUrlDiagnosticString", "nativeHeapDiagnosticString", "rendererDrmSchemeDiagnosticString", "sessionTypeDiagnosticString", "updateDiagnostics", "", "collector", "Lcom/amazon/avod/playback/smoothstream/diagnostics/DiagnosticDataCollector;", "videoStreamDiagnosticString", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface AmazonPlayerDiagnosticsViewModel {
    String audioStreamDiagnosticString();

    String bufferingCountDiagnosticString();

    String cdnInfoDiagnosticString();

    String completeDiagnosticsString();

    String contentTypeDiagnosticString();

    String deliveryProtocolDiagnosticString();

    String deviceModelDiagnosticString();

    String deviceProfileNameDiagnosticString();

    String droppedFramesDiagnosticString();

    String heuristicsDiagnosticString();

    String javaHeapDiagnosticString();

    String manifestUrlDiagnosticString();

    String nativeHeapDiagnosticString();

    String rendererDrmSchemeDiagnosticString();

    String sessionTypeDiagnosticString();

    void updateDiagnostics(DiagnosticDataCollector collector);

    String videoStreamDiagnosticString();
}
