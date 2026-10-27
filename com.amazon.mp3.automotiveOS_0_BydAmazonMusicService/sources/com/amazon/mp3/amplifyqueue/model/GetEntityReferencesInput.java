package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class GetEntityReferencesInput {
    private final String customerId;
    private final DeviceContextInput deviceContext;
    private final List<String> entityReferenceIds;
    private final String queueId;

    public interface BuildStep {
    }

    public static class Builder implements CustomerIdStep, QueueIdStep, DeviceContextStep, EntityReferenceIdsStep, BuildStep {
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

    public String getCustomerId() {
        return this.customerId;
    }

    public String getQueueId() {
        return this.queueId;
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
        if (obj == null || GetEntityReferencesInput.class != obj.getClass()) {
            return false;
        }
        GetEntityReferencesInput getEntityReferencesInput = (GetEntityReferencesInput) obj;
        return ObjectsCompat.equals(getCustomerId(), getEntityReferencesInput.getCustomerId()) && ObjectsCompat.equals(getQueueId(), getEntityReferencesInput.getQueueId()) && ObjectsCompat.equals(getDeviceContext(), getEntityReferencesInput.getDeviceContext()) && ObjectsCompat.equals(getEntityReferenceIds(), getEntityReferencesInput.getEntityReferenceIds());
    }

    public int hashCode() {
        return (getCustomerId() + getQueueId() + getDeviceContext() + getEntityReferenceIds()).hashCode();
    }
}
