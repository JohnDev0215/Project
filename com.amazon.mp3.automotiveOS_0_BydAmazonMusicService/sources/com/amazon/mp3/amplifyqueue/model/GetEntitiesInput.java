package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class GetEntitiesInput {
    private final String customerId;
    private final DeviceContextInput deviceContext;
    private final List<String> entityReferenceIds;
    private final String queueId;

    public interface BuildStep {
    }

    public static class Builder implements QueueIdStep, CustomerIdStep, DeviceContextStep, EntityReferenceIdsStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CustomerIdStep {
    }

    public interface DeviceContextStep {
    }

    public interface EntityReferenceIdsStep {
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

    public List<String> getEntityReferenceIds() {
        return this.entityReferenceIds;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetEntitiesInput.class != obj.getClass()) {
            return false;
        }
        GetEntitiesInput getEntitiesInput = (GetEntitiesInput) obj;
        return ObjectsCompat.equals(getQueueId(), getEntitiesInput.getQueueId()) && ObjectsCompat.equals(getCustomerId(), getEntitiesInput.getCustomerId()) && ObjectsCompat.equals(getDeviceContext(), getEntitiesInput.getDeviceContext()) && ObjectsCompat.equals(getEntityReferenceIds(), getEntitiesInput.getEntityReferenceIds());
    }

    public int hashCode() {
        return (getQueueId() + getCustomerId() + getDeviceContext() + getEntityReferenceIds()).hashCode();
    }
}
