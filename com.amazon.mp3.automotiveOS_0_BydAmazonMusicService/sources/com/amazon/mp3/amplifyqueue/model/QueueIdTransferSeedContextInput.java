package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class QueueIdTransferSeedContextInput {
    private final MetricsContextInput metricsContext;
    private final String queueId;
    private final Boolean shouldManagePlayback;
    private final DeviceIdentifierInput sourceDevice;

    public interface BuildStep {
    }

    public static class Builder implements QueueIdStep, SourceDeviceStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface QueueIdStep {
    }

    public interface SourceDeviceStep {
    }

    public String getQueueId() {
        return this.queueId;
    }

    public DeviceIdentifierInput getSourceDevice() {
        return this.sourceDevice;
    }

    public Boolean getShouldManagePlayback() {
        return this.shouldManagePlayback;
    }

    public MetricsContextInput getMetricsContext() {
        return this.metricsContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueueIdTransferSeedContextInput.class != obj.getClass()) {
            return false;
        }
        QueueIdTransferSeedContextInput queueIdTransferSeedContextInput = (QueueIdTransferSeedContextInput) obj;
        return ObjectsCompat.equals(getQueueId(), queueIdTransferSeedContextInput.getQueueId()) && ObjectsCompat.equals(getSourceDevice(), queueIdTransferSeedContextInput.getSourceDevice()) && ObjectsCompat.equals(getShouldManagePlayback(), queueIdTransferSeedContextInput.getShouldManagePlayback()) && ObjectsCompat.equals(getMetricsContext(), queueIdTransferSeedContextInput.getMetricsContext());
    }

    public int hashCode() {
        return (getQueueId() + getSourceDevice() + getShouldManagePlayback() + getMetricsContext()).hashCode();
    }
}
