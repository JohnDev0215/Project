package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class GetEntityResponse {
    private final GenericQueueEntityMetadata entity;
    private final QueueMetadata queueMetadata;
    private final GetEntityResponseStatusEnum status;

    public interface BuildStep {
    }

    public static class Builder implements StatusStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface StatusStep {
    }

    public GenericQueueEntityMetadata getEntity() {
        return this.entity;
    }

    public QueueMetadata getQueueMetadata() {
        return this.queueMetadata;
    }

    public GetEntityResponseStatusEnum getStatus() {
        return this.status;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetEntityResponse.class != obj.getClass()) {
            return false;
        }
        GetEntityResponse getEntityResponse = (GetEntityResponse) obj;
        return ObjectsCompat.equals(getEntity(), getEntityResponse.getEntity()) && ObjectsCompat.equals(getQueueMetadata(), getEntityResponse.getQueueMetadata()) && ObjectsCompat.equals(getStatus(), getEntityResponse.getStatus());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getEntity());
        sb.append(getQueueMetadata());
        sb.append(getStatus());
        return sb.toString().hashCode();
    }
}
