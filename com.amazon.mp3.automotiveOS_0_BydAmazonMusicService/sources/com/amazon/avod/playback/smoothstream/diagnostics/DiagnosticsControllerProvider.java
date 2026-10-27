package com.amazon.avod.playback.smoothstream.diagnostics;

import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.diagnostics.internal.DisabledDiagnosticsController;
import com.amazon.avod.media.framework.platform.DeviceConfiguration;

/* JADX INFO: loaded from: classes2.dex */
public class DiagnosticsControllerProvider {
    private final DiagnosticsConfig mConfig;
    private final DeviceConfiguration mDeviceConfig;

    public DiagnosticsControllerProvider(DeviceConfiguration deviceConfiguration) {
        this(DiagnosticsConfig.getInstance(), deviceConfiguration);
    }

    DiagnosticsControllerProvider(DiagnosticsConfig diagnosticsConfig, DeviceConfiguration deviceConfiguration) {
        this.mConfig = diagnosticsConfig;
        this.mDeviceConfig = deviceConfiguration;
    }

    public DiagnosticsController newController(DiagnosticDataCollector diagnosticDataCollector) {
        if (this.mConfig.isDiagnosticsViewAvailable()) {
            return new SmoothStreamingDiagnosticsController(this.mDeviceConfig, diagnosticDataCollector, this.mConfig);
        }
        return new DisabledDiagnosticsController();
    }
}
