package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class QueueIdTransferSeedInput {
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueueIdTransferSeedInput.class != obj.getClass()) {
            return false;
        }
        QueueIdTransferSeedInput queueIdTransferSeedInput = (QueueIdTransferSeedInput) obj;
        return ObjectsCompat.equals(getQueueId(), queueIdTransferSeedInput.getQueueId()) && ObjectsCompat.equals(getSourceDevice(), queueIdTransferSeedInput.getSourceDevice()) && ObjectsCompat.equals(getShouldManagePlayback(), queueIdTransferSeedInput.getShouldManagePlayback());
    }

    public int hashCode() {
        return (getQueueId() + getSourceDevice() + getShouldManagePlayback()).hashCode();
    }
}
