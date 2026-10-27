package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class GetQueueMetadataInput {
    private final String customerId;
    private final DeviceContextInput deviceContext;
    private final EntityContextInput entityContext;
    private final String queueId;

    public interface BuildStep {
    }

    public static class Builder implements QueueIdStep, CustomerIdStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CustomerIdStep {
    }

    public interface QueueIdStep {
    }

    public String getQueueId() {
        return this.queueId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public DeviceContextInput getDeviceContext() {
        return this.deviceContext;
    }

    public EntityContextInput getEntityContext() {
        return this.entityContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetQueueMetadataInput.class != obj.getClass()) {
            return false;
        }
        GetQueueMetadataInput getQueueMetadataInput = (GetQueueMetadataInput) obj;
        return ObjectsCompat.equals(getQueueId(), getQueueMetadataInput.getQueueId()) && ObjectsCompat.equals(getCustomerId(), getQueueMetadataInput.getCustomerId()) && ObjectsCompat.equals(getDeviceContext(), getQueueMetadataInput.getDeviceContext()) && ObjectsCompat.equals(getEntityContext(), getQueueMetadataInput.getEntityContext());
    }

    public int hashCode() {
        return (getQueueId() + getCustomerId() + getDeviceContext() + getEntityContext()).hashCode();
    }
}
