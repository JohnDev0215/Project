package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class GetQueueMetadataResponse {
    private final DevicePlaybackState devicePlaybackState;
    private final GenericQueueEntity entity;
    private final QueueMetadata queueMetadata;
    private final GetQueueMetadataResponseStatusEnum status;

    public interface BuildStep {
    }

    public static class Builder implements StatusStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface StatusStep {
    }

    public GetQueueMetadataResponseStatusEnum getStatus() {
        return this.status;
    }

    public QueueMetadata getQueueMetadata() {
        return this.queueMetadata;
    }

    public DevicePlaybackState getDevicePlaybackState() {
        return this.devicePlaybackState;
    }

    public GenericQueueEntity getEntity() {
        return this.entity;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetQueueMetadataResponse.class != obj.getClass()) {
            return false;
        }
        GetQueueMetadataResponse getQueueMetadataResponse = (GetQueueMetadataResponse) obj;
        return ObjectsCompat.equals(getStatus(), getQueueMetadataResponse.getStatus()) && ObjectsCompat.equals(getQueueMetadata(), getQueueMetadataResponse.getQueueMetadata()) && ObjectsCompat.equals(getDevicePlaybackState(), getQueueMetadataResponse.getDevicePlaybackState()) && ObjectsCompat.equals(getEntity(), getQueueMetadataResponse.getEntity());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStatus());
        sb.append(getQueueMetadata());
        sb.append(getDevicePlaybackState());
        sb.append(getEntity());
        return sb.toString().hashCode();
    }
}
