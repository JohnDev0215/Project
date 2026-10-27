package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class MoveQueueEntitiesInput {
    private final String customerId;
    private final List<String> entityReferenceIds;
    private final LocationInput location;
    private final String queueId;

    public interface BuildStep {
    }

    public static class Builder implements CustomerIdStep, QueueIdStep, EntityReferenceIdsStep, LocationStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CustomerIdStep {
    }

    public interface EntityReferenceIdsStep {
    }

    public interface LocationStep {
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

    public LocationInput getLocation() {
        return this.location;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MoveQueueEntitiesInput.class != obj.getClass()) {
            return false;
        }
        MoveQueueEntitiesInput moveQueueEntitiesInput = (MoveQueueEntitiesInput) obj;
        return ObjectsCompat.equals(getCustomerId(), moveQueueEntitiesInput.getCustomerId()) && ObjectsCompat.equals(getQueueId(), moveQueueEntitiesInput.getQueueId()) && ObjectsCompat.equals(getEntityReferenceIds(), moveQueueEntitiesInput.getEntityReferenceIds()) && ObjectsCompat.equals(getLocation(), moveQueueEntitiesInput.getLocation());
    }

    public int hashCode() {
        return (getCustomerId() + getQueueId() + getEntityReferenceIds() + getLocation()).hashCode();
    }
}
