package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class GetPreviousEntityResponse {
    private final ActiveDevicePlayback activeDevicePlayback;
    private final GenericQueueEntity entity;
    private final QueueMetadata queueMetadata;
    private final GetPreviousQueueEntityResponseStatusEnum status;

    public interface BuildStep {
    }

    public static class Builder implements StatusStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface StatusStep {
    }

    public GetPreviousQueueEntityResponseStatusEnum getStatus() {
        return this.status;
    }

    public GenericQueueEntity getEntity() {
        return this.entity;
    }

    public QueueMetadata getQueueMetadata() {
        return this.queueMetadata;
    }

    public ActiveDevicePlayback getActiveDevicePlayback() {
        return this.activeDevicePlayback;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetPreviousEntityResponse.class != obj.getClass()) {
            return false;
        }
        GetPreviousEntityResponse getPreviousEntityResponse = (GetPreviousEntityResponse) obj;
        return ObjectsCompat.equals(getStatus(), getPreviousEntityResponse.getStatus()) && ObjectsCompat.equals(getEntity(), getPreviousEntityResponse.getEntity()) && ObjectsCompat.equals(getQueueMetadata(), getPreviousEntityResponse.getQueueMetadata()) && ObjectsCompat.equals(getActiveDevicePlayback(), getPreviousEntityResponse.getActiveDevicePlayback());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStatus());
        sb.append(getEntity());
        sb.append(getQueueMetadata());
        sb.append(getActiveDevicePlayback());
        return sb.toString().hashCode();
    }
}
