package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class SourceDeviceTransferSeedContextInput {
    private final MetricsContextInput metricsContext;
    private final DeviceIdentifierInput sourceDevice;

    public interface BuildStep {
    }

    public static class Builder implements SourceDeviceStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface SourceDeviceStep {
    }

    public DeviceIdentifierInput getSourceDevice() {
        return this.sourceDevice;
    }

    public MetricsContextInput getMetricsContext() {
        return this.metricsContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SourceDeviceTransferSeedContextInput.class != obj.getClass()) {
            return false;
        }
        SourceDeviceTransferSeedContextInput sourceDeviceTransferSeedContextInput = (SourceDeviceTransferSeedContextInput) obj;
        return ObjectsCompat.equals(getSourceDevice(), sourceDeviceTransferSeedContextInput.getSourceDevice()) && ObjectsCompat.equals(getMetricsContext(), sourceDeviceTransferSeedContextInput.getMetricsContext());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getSourceDevice());
        sb.append(getMetricsContext());
        return sb.toString().hashCode();
    }
}
