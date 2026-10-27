package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class RemoveFromQueueInput {
    private final String customerId;
    private final List<String> entityReferenceIds;
    private final String queueId;

    public interface BuildStep {
    }

    public static class Builder implements CustomerIdStep, QueueIdStep, EntityReferenceIdsStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CustomerIdStep {
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

    public List<String> getEntityReferenceIds() {
        return this.entityReferenceIds;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RemoveFromQueueInput.class != obj.getClass()) {
            return false;
        }
        RemoveFromQueueInput removeFromQueueInput = (RemoveFromQueueInput) obj;
        return ObjectsCompat.equals(getCustomerId(), removeFromQueueInput.getCustomerId()) && ObjectsCompat.equals(getQueueId(), removeFromQueueInput.getQueueId()) && ObjectsCompat.equals(getEntityReferenceIds(), removeFromQueueInput.getEntityReferenceIds());
    }

    public int hashCode() {
        return (getCustomerId() + getQueueId() + getEntityReferenceIds()).hashCode();
    }
}
