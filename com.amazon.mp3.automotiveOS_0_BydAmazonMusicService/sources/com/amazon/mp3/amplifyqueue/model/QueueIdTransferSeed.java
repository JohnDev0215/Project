package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class QueueIdTransferSeed {
    private final String queueId;
    private final Boolean shouldManagePlayback;
    private final DeviceIdentifier sourceDevice;

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

    public DeviceIdentifier getSourceDevice() {
        return this.sourceDevice;
    }

    public Boolean getShouldManagePlayback() {
        return this.shouldManagePlayback;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueueIdTransferSeed.class != obj.getClass()) {
            return false;
        }
        QueueIdTransferSeed queueIdTransferSeed = (QueueIdTransferSeed) obj;
        return ObjectsCompat.equals(getQueueId(), queueIdTransferSeed.getQueueId()) && ObjectsCompat.equals(getSourceDevice(), queueIdTransferSeed.getSourceDevice()) && ObjectsCompat.equals(getShouldManagePlayback(), queueIdTransferSeed.getShouldManagePlayback());
    }

    public int hashCode() {
        return (getQueueId() + getSourceDevice() + getShouldManagePlayback()).hashCode();
    }
}
